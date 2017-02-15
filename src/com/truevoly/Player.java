package com.truevoly;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Player {

    private MidiChannel[] channels = null;
    private Synthesizer synth = null;
    private int bpm = 120;

    public Player(int bpm) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
            channels[0].programChange(1);
            this.bpm = bpm;
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        synth.close();
    }

    public void playSong(ArrayList<Note> song) {
        for (Note note : song) {
            channels[0].noteOn(note.getTone(), note.getVolume());
            channels[0].noteOn(note.getTone() - 12, note.getVolume());
        try {
            Thread.sleep(60000 / (note.getDuration() * bpm));
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
            channels[0].noteOff(note.getTone());
            channels[0].noteOff(note.getTone() - 12);
        }
        close();
    }

    //public void playSound
}