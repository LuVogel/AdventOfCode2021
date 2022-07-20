package com.main.DayTwelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayTwelve {

    public int duplicateCounter;
    public int puzzle1_paths;
    public int puzzle2_paths;
    public  Stack<String> currentPathGlobal1;
    public  Stack<String> currentPathGlobal2;

    public Map<String, List<String>> currentPathMap = new HashMap<>();

    public class Graph {

        private Map<String, List<String>> graph = new HashMap<>();

        public void addVertex(String s) {
            graph.put(s, new LinkedList<String>());
        }


        public void addEdge(String source, String destination) {
            if (!graph.containsKey(source)) {
                addVertex(source);
            }
            if (!graph.containsKey(destination)) {
                addVertex(destination);
            }
            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }

        public Map<String, List<String>> getMap() {
            return graph;
        }

        public void countPaths(String source, String destination) {
            currentPathGlobal1 = new Stack<>();
            puzzle1_paths = 0;
            count(source, destination);
            System.out.println("Numb of paths between " + source + " and " + destination + " are: " + puzzle1_paths);

        }

        public void count(String start, String end) {
            if (start.equals(end)) {
                puzzle1_paths++;
            } else {
                currentPathGlobal1.push(start);
                for (String tmp : graph.get(start)) {
                    if (!isSmallCave(tmp)) {
                        count(tmp, end);
                    } else {
                        if (!currentPathGlobal1.contains(tmp)) {
                            count(tmp, end);
                        }
                    }
                }
                currentPathGlobal1.pop();
            }
        }

        public void countPaths2(String source, String destination) {
            currentPathGlobal2 = new Stack<>();
            puzzle2_paths = 0;
            duplicateCounter = 0;
            count2(source, destination);
            System.out.println("Numb of paths between " + source + " and " + destination + " are: " + puzzle2_paths);

        }

        public void count2(String start, String end) {
            if (start.equals(end)) {
                puzzle2_paths++;

            } else {
                currentPathGlobal2.push(start);
                for (String tmp : graph.get(start)) {
                    if (!isSmallCave(tmp)) {
                        count2(tmp, end);
                    } else {
                        if (!currentPathGlobal2.contains(tmp)) {
                            count2(tmp, end);
                        } else if (!tmp.equals("start") && !tmp.equals("end")){
                            if (duplicateCounter == 0) {
                                duplicateCounter++;
                                count2(tmp, end);

                            }

                        }
                    }
                }

                currentPathGlobal2.pop();
                if (!currentPathGlobal2.isEmpty()) {
                    Map<String, Integer> tempMap = new HashMap<>();
                    for (String s : currentPathGlobal2) {
                        if (isSmallCave(s)) {
                            Integer j = tempMap.get(s);
                            tempMap.put(s, (j == null) ? 1 : j+1);
                        }
                    }
                    boolean hasDuplicate = false;
                    for (Map.Entry<String, Integer> val : tempMap.entrySet()) {
                        if (val.getValue() == 2) {
                            hasDuplicate = true;
                        }
                    }
                    if (!hasDuplicate) {
                        duplicateCounter = 0;
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

        public void clear() {
            graph.clear();
        }
    }

    private Graph readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_12_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_12.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        Graph input = new Graph();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] tmp = line.split("-");
                input.addEdge(tmp[0], tmp[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void Puzzle1() {
       Graph graph = readInput();
       graph.countPaths("start", "end");
       graph.clear();

    }

    public void Puzzle2() {
        Graph graph = readInput();
        graph.countPaths2("start", "end");
        graph.clear();
    }







}
