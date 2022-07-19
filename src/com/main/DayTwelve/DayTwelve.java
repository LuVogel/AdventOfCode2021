package com.main.DayTwelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayTwelve {






    private ArrayList<String[]> readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_12_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_12_test.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        ArrayList<String[]> input = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] tmp = line.split("-");
                input.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void Puzzle1() {
       ArrayList<String[]> inputList = readInput();
       ArrayList<String> paths = getAllPaths(inputList, "start", "end", "");

    }

    private ArrayList<String> getAllPaths(ArrayList<String[]> graph, String start, String end, String path) {
        path += start;
        if (start == end) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(path);
            return tmp;
        }
        ArrayList<String> paths = new ArrayList<>();
        for (String[] s : graph) {
            String s0 = s[0];
            String s1 = s[1];
            if (s0.equals(start)) {
                if (isSmallCave(s1)) {
                    if (!path.contains(s1)) {
                        String newPaths = getAllPaths()
                    }
                }
            }
        }

    }


    public boolean isSmallCave(String s)  {
        char[] c = s.toCharArray();
        for (char tmp : c) {
            if (!Character.isLowerCase(tmp)) {
                return false;
            }
        }
        return true;
    }
}
