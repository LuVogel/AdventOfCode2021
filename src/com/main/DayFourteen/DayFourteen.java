package com.main.DayFourteen;

import jdk.dynalink.linker.LinkerServices;

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
        this.puzzleNumber = puzzleNumber;
        readInput();
        Puzzle();
    }

    private void Puzzle() {
        int steps = 0;
        int maxSteps = 0;
        if (puzzleNumber.equals("1")) {
            maxSteps = 10;
        } else if (puzzleNumber.equals("2")) {
            maxSteps = 40;
        }

        while (steps < maxSteps) {
            steps++;
            // TODO: go through each instruction: for each instruction check if in String (maybe Hashmap instead of
            //  string. If Instruction is in String: insert into String/Map (insertion.contains(key) --> faster than string)
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
            System.out.println(steps + " , " + formulaAsString.length());
        }

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
        System.out.println(maxAmount - minAmount);

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
