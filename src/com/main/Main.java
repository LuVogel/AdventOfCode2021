package com.main;


import com.main.day10.Day10;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Day10 day10 = new Day10();
        ArrayList<Stack<String>> helpFor2 = day10.Puzzle1();
        day10.Puzzle2(helpFor2);
    }
}
