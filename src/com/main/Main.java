package com.main;



import com.main.DayTwelve.DayTwelve;


import java.io.IOException;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        DayTwelve dayTwelve = new DayTwelve();
        System.out.println("puzzle1: \n");
        dayTwelve.Puzzle1();
        System.out.println("\n\npuzzle2: \n");
        dayTwelve.Puzzle2();
    }
}
