package com.truevoly;



public class Note {

    private int tone;
    private int duration;
    private int volume = 80;
    private int position;

    public Note(){}

    public Note(int tone, int duration){
        this.tone = tone;
        this.duration = duration;
    }

    public Note(int tone, int duration, int volume){
        this.tone = tone;
        this.duration = duration;
        this.volume = volume;
    }

    public int getDuration() {
        return duration;
    }

    public int getTone() {
        return tone;
    }

    public int getVolume() {
        return volume;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
