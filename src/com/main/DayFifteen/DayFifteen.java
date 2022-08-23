package com.main.DayFifteen;

import java.io.*;
import java.util.*;

public class DayFifteen {

    // TODO: implement dijkstra

    private static class SearchNode implements Comparable<SearchNode> {


        String name;
        int pathCost;
        SearchNode parent;

        public static SearchNode makeRootNode(String currentName) {
            SearchNode node = new SearchNode();
            node.name = currentName;
            node.pathCost = 0;
            node.parent = null;
            return node;
        }




        public static SearchNode makeNode(String currentName, SearchNode parent, int cost) {
            SearchNode node = new SearchNode();
            node.parent = parent;
            node.name = currentName;
            node.pathCost = cost + parent.pathCost;
            return node;
        }

        public static ArrayList<SearchNode> extractPath(SearchNode node) {
            ArrayList<SearchNode> path = new ArrayList<>();
            while (node.parent != null) {
                path.add(node);
                node = node.parent;
            }
            Collections.reverse(path);
            return path;
        }


        @Override
        public int compareTo(SearchNode o) {
            int x1 = Integer.parseInt(this.name);
            int x2 = Integer.parseInt(o.name);
            if (x1 < x2) {
                return 1;
            } else if (x1 > x2) {
                return -1;
            }
            return 0;
        }
    }

    static Map<String, Map<String, Integer>> graph = new HashMap<>();
    // MAP<StateName, Map<Successor name, cost>>
    int vertexCount;
    int maxI;
    int maxJ;
    int startCost;

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
        List<String> vertexList = new ArrayList<>();
        for (String vertex : graph.keySet()) {
            vertexList.add(vertex);
        }
        Collections.sort(vertexList);

    }

    private ArrayList<SearchNode> UniformCostSearch(String startNode, String endNode) {
        PriorityQueue<SearchNode> open = new PriorityQueue<>();
        open.add(SearchNode.makeRootNode(startNode));
        HashMap<String, Integer> closed = new HashMap<>();
        while (!open.isEmpty()) {
            SearchNode n = open.poll();
            String currentNode = n.name;
            int currentCost = n.pathCost;
            if (!closed.containsKey(currentNode)) {
                closed.put(currentNode, currentCost);
                if (currentNode.equals(endNode)) {
                    return SearchNode.extractPath(n);
                }
                Set<String> successors = graph.get(currentNode).keySet();
                for (String s : successors) {
                    SearchNode nTemp = SearchNode.makeNode(s, n, graph.get(currentNode).get(s));
                    open.add(nTemp);
                }
            }
        }
        System.out.println("unsolvable");
        return null;
    }

    private ArrayList<SearchNode> BestFirstSearch(String startNode, String endNode) {
        PriorityQueue<SearchNode> open = new PriorityQueue<>();
        open.add(SearchNode.makeRootNode(startNode));
        HashMap<String, Integer> distances = new HashMap<>();
        while (!open.isEmpty()) {
            SearchNode n = open.poll();
            String currentNode = n.name;
            int currentCost = n.pathCost;
            if (!distances.containsKey(currentNode) || currentCost < distances.get(currentNode)) {
                if (!distances.containsKey(currentNode)) {
                    distances.put(currentNode, currentCost);
                } else {
                    distances.replace(currentNode, currentCost);
                }
                if (currentNode.equals(endNode)) {
                    return SearchNode.extractPath(n);
                }
                Set<String> successors = graph.get(currentNode).keySet();
                for (String s : successors) {
                    if (graph.get(currentNode).get(s) < Integer.MAX_VALUE) {
                        SearchNode nTemp = SearchNode.makeNode(s, n, graph.get(currentNode).get(s));
                        open.add(nTemp);
                    }
                }
            }
        }
        System.out.println("unsolvable");
        return null;
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
        for (String s : graph.keySet()) {
            System.out.println("node: " + s + ", succ: " + graph.get(s).keySet());
        }
        String start = maxI + String.valueOf(maxJ);
        ArrayList<SearchNode> solution = BestFirstSearch(start, "00");
        int sum = 0;
        for (SearchNode s : solution) {
            if (sum < s.pathCost) {
                sum = s.pathCost;
            }
        }
        System.out.println("pathCost = " + (sum - startCost));
    }

    private void readInput() {
        vertexCount = 0;
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
        int[][] array = new int[sArray.length][lineLength];
        for (int i = 0; i < array.length; i++) {
            String[] tmp = sArray[i].split("");
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int tmpIUp = i -1;
                int tmpIDown = i + 1;
                int tmpJLeft = j - 1;
                int tmpJRight = j + 1;

                if (i < array.length-1 && j < array[0].length-1 && i > 0 && j > 0) {
                    //no border
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j] );
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j] );
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j] );
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j] );
                } else if (i == array.length-1 && j < array[0].length-1 && j > 0){
                    //down border (no corners)
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j]);
                } else if (i < array.length-1 && j == array[0].length-1 && i > 0) {
                    //right border (no corners)
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j]);
                } else if (i < array.length - 1 && i > 0 && j == 0) {
                    //left border (no corners)
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j]);
                } else if (i == 0 && j < array[0].length - 1 && j > 0) {
                    //top border (no corners)
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j]);
                } else if (i == 0 && j == 0) {
                    //topLeft corner
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j]);
                } else if (i == array.length - 1 && j == 0) {
                    //downLeftCorner
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJRight), array[i][j]);
                } else if (i == array.length - 1 && j == array[0].length - 1) {
                    //downRightCorner
                    addEdge(i + String.valueOf(j),
                            tmpIUp + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j]);
                } else if (i == 0 && j == array[0].length - 1) {
                    //topRightCorner
                    addEdge(i + String.valueOf(j),
                            tmpIDown + String.valueOf(j), array[i][j]);
                    addEdge(i + String.valueOf(j),
                            i + String.valueOf(tmpJLeft), array[i][j]);
                }
            }
        }
        startCost = array[0][0];
        maxI = array.length - 1;
        maxJ = array[0].length - 1;
    }


}
