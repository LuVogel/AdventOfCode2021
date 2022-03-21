package com.main.day4;

public class BingoNumber {

    int number;
    boolean marked;

    public BingoNumber(int number) {
        this.number = number;
        this.marked = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
