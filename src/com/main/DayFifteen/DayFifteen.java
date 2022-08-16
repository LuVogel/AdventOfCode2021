package com.main.DayFifteen;

import java.io.*;
import java.util.HashMap;

public class DayFifteen {

    CustomTree tree;

    private class CustomTree {

        Node root;
        private static class Node {
            int cost;
            Node left;
            Node right;

            public Node(int cost) {
                this.cost = cost;
                left = null;
                right = null;
            }
        }
    }


    public DayFifteen(String puzzleNumber) {
        readInput();
        if (puzzleNumber.equals("1")) {
            Puzzle(10);
            System.out.println("Solution Day15, Part 1 :");
        } else if (puzzleNumber.equals("2")) {
            Puzzle(40);
            System.out.println("Solution Day15, Part 2: ");
        }
    }

    private void Puzzle(int i) {
    }

    private void readInput() {
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

        tree = new CustomTree();
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
        tree.root = new CustomTree.Node(array[0][0]);
        int i = 1;
        int j = 1;
        while (i < array.length && j < array[0].length) {
            CustomTree.Node currentNode = tree.root;

        }
    }
}
