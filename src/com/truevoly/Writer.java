package com.truevoly;

import sun.rmi.runtime.Log;

import javax.sound.midi.Synthesizer;
import javax.sound.midi.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class Writer {

    private int tonic;
    private int bpm;
    private boolean major = true;
    private int[] notes = new int[8];
    private ArrayList<Note> song = new ArrayList<>();
    private int beat = 0;
    private int currentTime = 0;
    private Random rand = new Random(new Date().getTime());
    private Logger log = Logger.getLogger(Writer.class.getName());

    public Writer(){}

    public Writer(char tonic, int bpm){
        this.bpm = bpm;
        this.tonic = tonic;
        createGamma(this.major);
    }

    private void createGamma(boolean major) {
        if (major) {
            notes[0] = this.tonic;
            notes[1] = notes[0] + 2;
            notes[2] = notes[1] + 2;
            notes[3] = notes[2] + 1;
            notes[4] = notes[3] + 2;
            notes[5] = notes[4] + 2;
            notes[6] = notes[5] + 2;
            notes[7] = notes[6] + 1;
        }
        else {
            notes[0] = this.tonic;
            notes[1] = notes[0] + 2;
            notes[2] = notes[1] + 1;
            notes[3] = notes[2] + 2;
            notes[4] = notes[3] + 2;
            notes[5] = notes[4] + 1;
            notes[6] = notes[5] + 2;
            notes[7] = notes[6] + 2;
        }
    }

    public Writer(char tonic, int bpm, boolean major){
        this.tonic = tonic;
        this.bpm = bpm;
        this.major = major;
        createGamma(major);
    }

    public ArrayList<Note> write(){
        //TODO 2. Написать алгоритм создания
        firstNote();
        while (currentTime < 512){
            System.out.print("\n" + currentTime + "\n");
            nextNote();
            switch (currentTime){
                case 128: System.out.print("Четверть"); break;
                case 256: System.out.print("Половина"); break;
                case 384: System.out.print("Три четверти"); break;
                case 512: System.out.print("Готово"); break;
            }
        }
        song.add(new Note(tonic, 16));
        return song;
    }

    private void firstNote(){
        song.add(new Note(tonic, 8));
        beat +=8;
        currentTime += beat;
    }

    private void nextNote(){
        int length = 0;
        int firstNote, secondNote;
        int timesInWhile = 0;
        while (length == 0){
            length = getNoteDuration();
            //log.info("length: " + length);
            timesInWhile++;
            if (timesInWhile > 20) {
                length = 4;
            }
        }
        if (length == 16) {
            for (int i = 0; i < 2; i++) {
                if ((song.get(song.size() - 1).getDuration()) == (song.get(song.size() - 2).getTone())) {
                    firstNote = notes[rand.nextInt(notes.length)];
                    secondNote = notes[rand.nextInt(notes.length)];
                } else {
                    firstNote = notes[rand.nextInt(notes.length)];
                    secondNote = firstNote;
                }

                beat += (length * 2);
                currentTime += beat;

                song.add(new Note(firstNote, length));
                song.add(new Note(secondNote, length));

                if (beat == 16) beat = 0;
            }
        }
        else if (length == 8){
            for (int i = 0; i < 2; i++){
                song.add(new Note(notes[rand.nextInt(notes.length)], length));
                beat += length;
                currentTime += beat;
                if (beat == 16) beat = 0;
            }
        }
        else {
            song.add(new Note(notes[rand.nextInt(notes.length)], length));
            beat += length;
            currentTime += beat;
            if (beat == 16) beat = 0;
        }
    }

    private int getNoteDuration() {
        int length = song.get(song.size() - 1).getDuration();
        int type = rand.nextInt(3);
        if (type == 0) {
            if ((beat + length) > 16) {//same
                return 0;
            }
            else {
                return length;
            }
        } else if (type == 1) {//inc
            if ((beat + (length * 2)) > 16) {
                return 0;
            } else {
                return (length * 2);
            }
        } else if (type == 2) {//dec
            if ((beat + (length / 2)) > 16) {
                return 0;
            } else {
                return (length / 2);
            }
        }
        else return 0;
    }
}
