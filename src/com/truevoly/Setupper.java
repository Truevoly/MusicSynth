package com.truevoly;

import java.util.Scanner;

public class Setupper {

    private Scanner in = new Scanner(System.in);

    public char askNote(){
        System.out.print("Введите тональность (C, D, E, F, G, A, B)");
        String a = this.in.nextLine();
        return a.toCharArray()[0];
    }

    public int askBPM(){
        System.out.print("Введите темп");
        return in.nextInt();
    }
}
