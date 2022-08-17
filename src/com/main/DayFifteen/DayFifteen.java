package com.main.DayFifteen;

import java.io.*;
import java.util.*;

public class DayFifteen {

    // TODO: implement dijkstra

    Map<String, Map<String, Integer>> graph = new HashMap<>();
    int vertexCount;
    int maxI;
    int maxJ;

    private void addVertex(String vertex) {
        graph.put(vertex, new HashMap<>());
        vertexCount++;
    }

    private void addEdge(String source, String destination, int cost) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }
        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }
        graph.get(destination).put(source, cost);
    }

    private void printGraph() {
        System.out.println("Graph");
        List<String> vertexList = new ArrayList<>();
        for (String vertex : graph.keySet()) {
            vertexList.add(vertex);
        }
        Collections.sort(vertexList);
        for (String vertex : vertexList) {
            System.out.println(vertex +  ", " + graph.get(vertex).keySet() + ", " + graph.get(vertex).entrySet());
        }
    }

    private void dijkstra() {
        String start =
    }

    private int minDistance(int[] distance, boolean[] shortestPathSet) {
        return 0;
    }


    public DayFifteen(String puzzleNumber) {
        readInput();
        if (puzzleNumber.equals("1")) {
            Puzzle();
            System.out.println("Solution Day15, Part 1 :");
        } else if (puzzleNumber.equals("2")) {
            Puzzle();
            System.out.println("Solution Day15, Part 2: ");
        }
    }

    private void Puzzle() {
        printGraph();
    }

    private void readInput() {
        vertexCount = 0;
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_15_test1.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_15.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String map = "";
        String line = "";
        int lineLength = 0;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            map += line + ",";
            lineLength = line.length();

        }
        String[] sArray = map.split(",");
        int[][] array = new int[sArray.length][lineLength];
        for (int i = 0; i < array.length; i++) {
            String[] tmp = sArray[i].split("");
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int tmpI;
                int tmpJ;

                if (i < array.length-1 && j < array[0].length-1) {
                    //not  right or down borders
                    tmpI = i + 1;
                    tmpJ = j + 1;
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJ), array[i][j] );
                    addEdge(i + String.valueOf(j),
                            tmpI + String.valueOf(j), array[i][j] );
                } else if (i == array.length-1 && j < array[0].length-1){
                    //down border
                    tmpJ = j + 1;
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJ), array[i][j]);
                } else if (i < array.length-1 && j == array[0].length-1) {
                    //right border
                    tmpI = i + 1;
                    addEdge(i + String.valueOf(j),
                            tmpI + String.valueOf(j), array[i][j]);
                }
            }
        }
        maxI = array.length;
        maxJ = array[0].length;
    }
}
