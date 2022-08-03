package com.main.DayThirteen;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DayThirteen {

    ArrayList<int[]> pointList;
    ArrayList<int[]> foldMap;
    int maxX = 0;
    int maxY = 0;

    private void readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_13_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_13_test.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        pointList = new ArrayList<>();
        foldMap = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("fold")) {
                    String[] tmp = line.split("");
                    if (line.contains("x")) {
                        foldMap.add(new int[]{0, Integer.parseInt(tmp[tmp.length-1])});
                        // add 0 for x and value
                    } else if (line.contains("y")) {
                        foldMap.add(new int[]{1, Integer.parseInt(tmp[tmp.length-1])});
                    }
                } else if (!line.isEmpty()) {
                    String[] tmp = line.split(",");
                    pointList.add(new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[tmp.length-1])});
                    if (Integer.parseInt(tmp[0]) > maxX) {
                        maxX = Integer.parseInt(tmp[0]);
                    } else if (Integer.parseInt(tmp[tmp.length-1]) > maxY) {
                        maxY = Integer.parseInt(tmp[tmp.length-1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        maxX += 1;
        maxY += 1;
    }

    public void Puzzle1() {
        readInput();
        String[][] currentMap = new String[maxY][maxX];
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                currentMap[i][j] = "0";
            }

        }

        for (int[] tmp : pointList) {
            int i = tmp[0];
            int j = tmp[tmp.length-1];
            currentMap[j][i] = "X";
        }
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(currentMap[i][j]);
            }
            System.out.println();
        }
        while (!foldMap.isEmpty()) {
            int[] currentFold = foldMap.get(0);
            foldMap.remove(0);
            System.out.println(currentFold[0]+ ", " + currentFold[1]);
        }
    }









}
