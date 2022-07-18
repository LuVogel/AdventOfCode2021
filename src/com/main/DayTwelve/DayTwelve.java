package com.main.DayTwelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DayTwelve {

    private class CustomGraph<T> {

        private Map<T, LinkedList<T>> map = new HashMap();
        public void addNewVertex(T s) {
            map.put(s, new LinkedList<T>());
        }
        public void addNewEdge(T source, T destination, boolean bidirectional) {
            if (!map.containsKey(source)) {
                addNewVertex(source);
            }
            if (!map.containsKey(destination)) {
                addNewVertex(destination);
            }
            map.get(source).add(destination);
            if (bidirectional == true) {
                map.get(destination).add(source);
            }
        }
        public int getSize() {
            return map.size();

        }

    }

    private CustomGraph<String> readInput() {
        CustomGraph customGraph = new CustomGraph();
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_11_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_11.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        String map = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] tmp = line.split("-");
                customGraph.addNewEdge(tmp[0], tmp[1], true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customGraph;
    }

    public void Puzzle1() {
        CustomGraph customGraph = readInput();
        String[] solution;
        Object root = customGraph.map.get("start");
        //TODO: get all paths from start-to-end,
        // maybe state space search instead of graph search,
        // Generic Graph Search Foundations AI

    }

    public ArrayList<String> getPaths(Object node) {
        return null;
    }
}
