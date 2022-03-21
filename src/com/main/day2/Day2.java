package com.main.day2;

import java.io.*;
import java.util.ArrayList;

public class Day2 {

    /**
     * right answer for 2.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_2_drive.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;

        int sumForward = 0;
        int sumDown = 0;
        int sumUp = 0;
        while ((s = reader.readLine()) != null) {
           String[] splitString = s.split(" ");
           if (splitString[0].equals("forward")) {
               sumForward += Integer.parseInt(splitString[1]);
           } else if (splitString[0].equals("up")) {
               sumUp += Integer.parseInt(splitString[1]);
           } else if (splitString[0].equals("down")) {
               sumDown += Integer.parseInt(splitString[1]);
           }
        }
        int sumDepth = Math.abs((sumDown - sumUp));
        System.out.println("multiply = " + (sumForward * sumDepth));

    }

    /**
     * right answer for 2.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_2_drive.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        int sumForward = 0;
        int down = 0;
        int up = 0;
        int aim = 0;
        int depth = 0;
        while ((s = reader.readLine()) != null) {
            String[] splitString = s.split(" ");
            if (splitString[0].equals("forward")) {
                sumForward += Integer.parseInt(splitString[1]);
                depth += (aim * Integer.parseInt(splitString[1]));
            } else if (splitString[0].equals("up")) {
                up = Integer.parseInt(splitString[1]);
                aim -= up;
            } else if (splitString[0].equals("down")) {
                down = Integer.parseInt(splitString[1]);
                aim += down;
            }
        }
        //int sumDepth = Math.abs((sumDown - sumUp));
        System.out.println("multiply = " + (sumForward * depth));

    }
}
