package com.truevoly;

public class Coordinator implements Runnable{

    private int currentTime;
    private int currentBeat;
    private int currentBar;

    public Coordinator(){}

    public int getCurrentBeat() {
        return currentBeat;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getCurrentBar() {
        return currentBar;
    }

    public void setCurrentBar(int currentBar) {
        this.currentBar = currentBar;
    }

    public void setCurrentBeat(int currentBeat) {
        this.currentBeat = currentBeat;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void run() {

    }
}
