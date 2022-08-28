package com.main.DayFifteen;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class DayFifteen {

    public int[][] globalMap;
    public Graph cGraph;
    public int startCost;
    public int maxI;
    public int maxJ;

    public static class Graph {

        private Map<String, List<String>> customGraph;

        public Graph() {
            this.customGraph = new HashMap<>();
        }

        public void addVertex(String s) {
            customGraph.put(s, new LinkedList<>());
        }

        public void addEdge(String source, String destination, boolean bidirectional) {
            if (!customGraph.containsKey(source)) {
                addVertex(source);
            }
            if (!customGraph.containsKey(destination)) {
                addVertex(destination);
            }
            customGraph.get(source).add(destination);
            if (bidirectional) {
                customGraph.get(destination).add(source);
            }
        }
        public Map<String, List<String>> getCustomGraph() {
            return customGraph;
        }

        public int getVertexCount() {
            return customGraph.keySet().size();
        }
    }


    public DayFifteen(String puzzleNumber) {
        if (puzzleNumber.equals("1")) {
            System.out.println("Solution Day15, Part 1 :");
            Puzzle(1);
        } else if (puzzleNumber.equals("2")) {
            System.out.println("Solution Day15, Part 2: ");
            Puzzle(2);

        }
    }

    private void Puzzle(int part) {
        cGraph = new Graph();
        if (part == 1 ) {
            readInput1();
        } else if (part == 2) {
            readInput2();
        }

        HashMap<String, Integer> solution = dijkstra(cGraph, "0,0");
        String endNode = maxI + "," + maxJ;
        System.out.println("cost from start to end: " + solution.get(endNode));
    }

    private HashMap<String, Integer > dijkstra(Graph graph, String startNode) {
        HashMap<String, Integer> unvisited = new HashMap<>();
        HashMap<String, Integer> visited = new HashMap<>();
        for (String key : graph.customGraph.keySet()) {
            unvisited.put(key, Integer.MAX_VALUE);
        }
        unvisited.put(startNode, 0);
        while (true) {
            if (unvisited.size() == 0) {
                break;
            } else {
                int small = Integer.MAX_VALUE;
                String currentNode = "";
                for (String tempKey : unvisited.keySet()) {
                    if (unvisited.get(tempKey) < small) {
                        small = unvisited.get(tempKey);
                        currentNode = tempKey;
                    }
                }

                for (String keys : graph.customGraph.keySet()) {
                    if (currentNode.equals(keys)) {
                        for (String neighbour : graph.customGraph.get(keys)) {
                            if (!visited.containsKey(neighbour)) {
                                int cost = unvisited.get(currentNode) +
                                        globalMap[Integer.parseInt(neighbour.split(",")[0])]
                                                [Integer.parseInt(neighbour.split(",")[1])];
                                if (cost < unvisited.get(neighbour)) {
                                    unvisited.put(neighbour, cost);

                                }
                            }
                        }
                    }
                }
                visited.put(currentNode, unvisited.get(currentNode));
                unvisited.remove(currentNode);
            }
        }
        return visited;
    }

    private void readInput1() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_15_test.txt");
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

        globalMap = new int[sArray.length][lineLength];
        maxI = globalMap.length-1;
        maxJ = globalMap[0].length-1;

        for (int i = 0; i <= maxI; i++) {
            String[] tmp = sArray[i].split("");
            for (int j = 0; j <= maxJ; j++) {
                globalMap[i][j] = Integer.parseInt(tmp[j]);
                int tmpIUp = i -1;
                int tmpIDown = i + 1;
                int tmpJLeft = j - 1;
                int tmpJRight = j + 1;
                if (i < maxI && j < maxJ && i > 0 && j > 0) {
                    //no border
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i == maxI && j < maxJ && j > 0){
                    //down border (no corners)
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                } else if (i < maxI && j == maxJ && i > 0) {
                    //right border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i < maxI && i > 0 && j == 0) {
                    //left border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == 0 && j < maxJ && j > 0) {
                    //top border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == 0 && j == 0) {
                    //topLeft corner
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == maxI && j == 0) {
                    //downLeftCorner
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == maxI && j == maxJ) {
                    //downRightCorner
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i == 0 && j == maxJ) {
                    //topRightCorner
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                }
            }
        }

    }

    private void readInput2() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_15_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_15_test.txt");
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

        globalMap = new int[5 * sArray.length][5 * lineLength];
        maxI = sArray.length;
        maxJ = lineLength;
        for (int i = 0; i < maxI; i++) {
            String[] tmp = sArray[i].split("");
            for (int j = 0; j < maxJ; j++) {

                for (int x = 0; x < globalMap.length-1; x+= sArray.length) {
                    int counter = 0;
                    for (int y = 0; y < globalMap[0].length-1; y += sArray.length) {
                        int tempCountAddCost = counter + Integer.parseInt(tmp[j]);
                        if (tempCountAddCost > 9) {
                            tempCountAddCost -= 9;
                        }
                        int tempCountAddBothK = tempCountAddCost + counter;
                        if (tempCountAddBothK > 9 ) {
                            tempCountAddBothK -= 9;
                        }
                        System.out.println("added: " + (x+i) + ", " + j + " with cost: " + tempCountAddCost);
                        System.out.println("added: " + (x+i) + ", " + (y+j) + " with cost: " + tempCountAddBothK);
                        System.out.println("added: " + i + ", " + (y+j) + " with cost: " + tempCountAddCost);
                        globalMap[x + i][j] = tempCountAddCost;
                        globalMap[i][y + j] = tempCountAddCost;
                        globalMap[x + i][y + j] = tempCountAddBothK;
                        counter++;
                    }

                }
            }
        }
        System.out.println("----------------------------------");
        for (int i = 0; i < globalMap.length; i++) {
            for (int j = 0; j < globalMap[0].length; j++) {
                if (globalMap[i][j] == 0) {
                    System.out.println("zero cost value at: " + i + "," + j);
                }
            }
        }
        maxI = globalMap.length-1;
        maxJ = globalMap[0].length-1;
        for (int i = 0; i <= maxI; i++) {
            for (int j = 0; j <= maxJ; j++) {
                int tmpIUp = i -1;
                int tmpIDown = i + 1;
                int tmpJLeft = j - 1;
                int tmpJRight = j + 1;
                if (i < maxI && j < maxJ && i > 0 && j > 0) {
                    //no border
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i == maxI && j < maxJ && j > 0){
                    //down border (no corners)
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                } else if (i < maxI && j == maxJ && i > 0) {
                    //right border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i < maxI && i > 0 && j == 0) {
                    //left border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == 0 && j < maxJ && j > 0) {
                    //top border (no corners)
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == 0 && j == 0) {
                    //topLeft corner
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == maxI && j == 0) {
                    //downLeftCorner
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJRight, false);
                } else if (i == maxI && j == maxJ) {
                    //downRightCorner
                    cGraph.addEdge(i + "," + j, tmpIUp + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                } else if (i == 0 && j == maxJ) {
                    //topRightCorner
                    cGraph.addEdge(i + "," + j, tmpIDown + "," + j, false);
                    cGraph.addEdge(i + "," + j, i + "," + tmpJLeft, false);
                }
            }
        }
    }
}
