package com.main.DayFourteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayFourteen {

    HashMap<String, Long> formulaAsMap;
    HashMap<String, String[]> instructionsAsList;
    String lastChar;
    public DayFourteen(String puzzleNumber) {
        readInput();
        if (puzzleNumber.equals("1")) {
            Puzzle(10);
            System.out.println("Solution Day14, Part 1 :");
        } else if (puzzleNumber.equals("2")) {
            Puzzle(40);
            System.out.println("Solution Day14, Part 2: ");
        }
        countOccurrences();
    }

    private void countOccurrences() {
        HashMap<String, Long> counterMap = new HashMap<>();
        Long last = counterMap.getOrDefault(lastChar, 0L);
        counterMap.put(lastChar, last + 1);
        for (String s : formulaAsMap.keySet()) {
            String currentPart = s.split("")[0];
            Long countAlreadyInMap = formulaAsMap.get(s);
            Long currentCount = counterMap.getOrDefault(currentPart, 0L);
            counterMap.put(currentPart, currentCount + countAlreadyInMap);
        }
        long maxAmount = Collections.max(counterMap.values());
        long minAmount = Collections.min(counterMap.values());
        System.out.println("\nThe difference between the most common element and the least common element is: " +
                (maxAmount - minAmount));
    }


    private void Puzzle(int maxSteps) {
        int steps = 0;
        while (steps < maxSteps) {
            steps++;
            HashMap<String, Long> currentFormulaAsMap = new HashMap<>();
            for (String currentPolymer : formulaAsMap.keySet()) {
                Long countCurrentPolymer = formulaAsMap.get(currentPolymer);
                if (instructionsAsList.containsKey(currentPolymer)) {
                    String[] resultingPolymers = instructionsAsList.get(currentPolymer);
                    String firstResulting = resultingPolymers[0];
                    String secondResulting = resultingPolymers[1];
                    Long countOfFirst = currentFormulaAsMap.getOrDefault(firstResulting, 0L);
                    Long countOfSecond = currentFormulaAsMap.getOrDefault(secondResulting, 0L);
                    currentFormulaAsMap.put(firstResulting, countOfFirst + countCurrentPolymer);
                    currentFormulaAsMap.put(secondResulting, countOfSecond + countCurrentPolymer);
                } else {
                    currentFormulaAsMap.put(currentPolymer, countCurrentPolymer);
                }
            }
            formulaAsMap = currentFormulaAsMap;
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
        formulaAsMap = new HashMap<>();
        instructionsAsList = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] tmp = line.split(" -> ");
                    String firstOfPair = tmp[0].split("")[0];
                    String secondOfPair = tmp[0].split("")[1];
                    String pair = firstOfPair + secondOfPair;
                    String firstPolymer = firstOfPair + tmp[1];
                    String secondPolymer = tmp[1] + secondOfPair;
                    instructionsAsList.put(pair, new String[]{firstPolymer, secondPolymer});
                } else if (!line.isEmpty()) {
                    String[] tmp = line.split("");
                    for (int i = 0; i < tmp.length-1; i++) {
                        String s = tmp[i] + tmp[i+1];
                        Long n = formulaAsMap.getOrDefault(s, 0L);
                        // add pair of input to map. n is 0 if not already in map, otherwise n is count of pairs in formula
                        formulaAsMap.put(s, n+1);
                    }
                    lastChar = tmp[tmp.length-1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
