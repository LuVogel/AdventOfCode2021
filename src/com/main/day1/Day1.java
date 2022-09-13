package com.main.day1;

import java.io.*;
import java.util.ArrayList;

public class Day1 {

    public boolean testCase;

    public Day1(String puzzleNumber, boolean testCase) {
        this.testCase = testCase;
        if (puzzleNumber.equals("1")) {
            getCountForFirstPuzzle();
        } else if (puzzleNumber.equals("2")) {
            getCountForSecondPuzzle();
        }
    }

    /**
     * right answer for 1.1.21 adventOfCode puzzle 1
     * @throws IOException
     */
    public void getCountForFirstPuzzle() {
        File file;
        if (testCase) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_1_sonar_sweep_test.txt");
        } else {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_1_sonar_sweep.txt");

        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = "";
        int first = 0;
        int sec;
        int counter = 0;
        while (true) {
            try {
                if (!((s = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (first == 0) {
                first = Integer.parseInt(s);
            } else {
                sec = Integer.parseInt(s);
                if (sec > first) {
                    counter++;
                }
                first = sec;
            }
        }
        System.out.println(counter);
    }

    /**
     * right answer for 1.1.21 adventOfCode puzzle 2
     * @throws IOException
     */
    public void getCountForSecondPuzzle() {
        File file;
        if (testCase) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_1_sonar_sweep_test.txt");
        } else {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_1_sonar_sweep.txt");
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> measure = new ArrayList<>();
        String s = "";
        while(true) {
            try {
                if (!((s = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            arrayList.add(s);
        }
        int a, b, c;
        for (int i = 0; i < arrayList.size(); i++) {
            if ((i+2) <= arrayList.size()-1) {
                a = Integer.parseInt(arrayList.get(i));
                b = Integer.parseInt(arrayList.get(i+1));
                c = Integer.parseInt(arrayList.get(i+2));
                String sum = String.valueOf((a+b+c));
                measure.add(sum);
            }
        }
        int counter = 0;
        int first = 0;
        int sec = 0;
        for (int i = 0; i < measure.size(); i++) {
            if (i == 0) {
                first = Integer.parseInt(measure.get(i));

            } else {
                sec = Integer.parseInt(measure.get(i));
                if (sec > first) {
                    counter++;
                }
                first = sec;
            }
        }
        System.out.println(counter);

    }
}


