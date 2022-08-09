package com.main.DayFourteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayFourteen {


    String puzzleNumber;
    String formulaAsString;
    ArrayList<String[]> instructionsAsList;
    public DayFourteen(String puzzleNumber) {
        readInput();
        if (puzzleNumber.equals("1")) {
            Puzzle1();
        } else if (puzzleNumber.equals("2")) {
            Puzzle2();
        }
        countOccurrences();
    }

    private void countOccurrences() {
        HashMap<String, Long> map = new HashMap<>();
        String[] splitFormula = formulaAsString.split("");
        for (String s : splitFormula) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s)+1);
            } else {
                map.put(s, 1L);
            }
        }
        long maxAmount = Collections.max(map.values());
        long minAmount = Collections.min(map.values());
        System.out.println("\nThe difference between the most common element and the least common element is: " +
                (maxAmount - minAmount));
    }

    private void Puzzle1() {
        int steps = 0;
        while (steps < 10) {
            steps++;
            String insertedFormula = "";
            String[] splitFormula = formulaAsString.split("");
            for (int i = 0; i < splitFormula.length - 1; i++) {
                String firstOfPair = splitFormula[i];
                String secondOfPair = splitFormula[i + 1];
                String currentPair = firstOfPair + secondOfPair;
                for (String[] instructions : instructionsAsList) {
                    if (currentPair.equals(instructions[0])) {
                        insertedFormula += firstOfPair + instructions[1];
                    }
                }
            }
            formulaAsString = insertedFormula + splitFormula[splitFormula.length - 1];

        }
        System.out.println("Solution Day14 (Part 1):");
    }

    private void Puzzle2() {
        int steps = 0;
        while (steps < 40) {
            steps++;

        }
    }

    private void readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_14.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_14.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        formulaAsString = "";
        instructionsAsList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] tmp = line.split("");
                    String pair = tmp[0] + tmp[1];
                    String toInsert = tmp[tmp.length-1];
                    instructionsAsList.add(new String[]{pair, toInsert});
                } else if (!line.isEmpty()) {
                    formulaAsString = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
