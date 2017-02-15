package com.truevoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    //private char note;
    //private int bpm;

    public static void main(String[] args){
        //Setupper setup = new Setupper();
        //writer.Play();
        Setup();
    }

    private static void Setup(){
        Scanner in = new Scanner(System.in);
        boolean major = true;
        System.out.print("Введите тональность (C, D, E, F, G, A, B)");
        String a = in.nextLine();
        System.out.print("Мажор? (y, n)");
        //char b = ;
        switch (in.nextLine().toCharArray()[0]){
            case 'n': major = false; break;
        }
        System.out.print("Введите темп");
        int bpm = in.nextInt();
        in.nextLine();
        Writer writer = new Writer(a.toCharArray()[0], bpm);
        System.out.print("Начинаем работу");
        ArrayList<Note> song = writer.write();
        System.out.print("Готовы? (y, n)");
        switch (in.nextLine().toCharArray()[0]) {
            case 'y':
                Player player = new Player(bpm);
                player.playSong(song);
                break;
            case 'n': break;
        }
    }
}