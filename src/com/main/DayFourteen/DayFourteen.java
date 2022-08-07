package com.main.DayFourteen;

import jdk.dynalink.linker.LinkerServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayFourteen {


    String puzzleNumber;
    String formulaAsString;
    ArrayList<String[]> instructionsAsList;
    public DayFourteen(String puzzleNumber) {
        this.puzzleNumber = puzzleNumber;
        readInput();
        Puzzle();
    }

    private void Puzzle() {
        int steps = 0;
        while (steps < 4) {
            steps++;
            String insertedFormula = "";
            String[] splittedFormula = formulaAsString.split("");
            for (int i = 0; i < splittedFormula.length-1; i++) {
                String firstOfPair = splittedFormula[i];
                String secondOfPair = splittedFormula[i+1];
                String currentPair = firstOfPair + secondOfPair;
                for (String[] instructions : instructionsAsList) {
                    if (currentPair.equals(instructions[0])) {
                        insertedFormula += firstOfPair + instructions[1];
                    }
                }
            }
            formulaAsString = insertedFormula + splittedFormula[splittedFormula.length-1];
        }
        System.out.println(formulaAsString);
    }

    private void readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_14_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_14_test.txt");
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
