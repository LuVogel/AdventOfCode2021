package com.main.day9;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Day9 {

    private static int[][] readInput() throws IOException, URISyntaxException {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_9_test.txt");
        } else if (os.equals("Windows")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_8.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String map = "";
        String line = "";
        int lineLength = 0;
        while ((line = reader.readLine()) != null) {
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
        return array;
    }

    private static ArrayList<LowPointObject> getLowPoints() throws IOException, URISyntaxException {
        int[][] array = readInput();
        ArrayList<LowPointObject> lowPointList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i == 0 && j == 0) {
                    //checkForTopLeftCorner
                    if ((array[i][j] < array[i][j+1]) && (array[i][j] < array[i+1][j])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                } else if (i == 0 && j > 0 && j < array[0].length-1) {
                    //checkForTopBorder
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                } else if (i == 0 && j == array[0].length-1) {
                    //checkForTopRightCorner
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                } else if (i > 0 && i < array.length-1 && j == 0) {
                    //checkForLeftBorder
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i-1][j]) && array[i][j] < array[i][j+1]) {
                        lowPointList.add(new LowPointObject(i,j,array[i][j]));
                    }
                } else if (i > 0 && i < array.length-1 && j == array[0].length-1) {
                    //checkForRightBorder
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i+1][j])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                } else if (i == array.length-1 && j == 0) {
                    //checkForDownLeftCorner
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                } else if (i == array.length-1 && j == array[0].length-1) {
                    //checkForDownRightCorner
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1])) {
                        lowPointList.add(new LowPointObject(i,j,array[i][j]));
                    }
                } else if (i == array.length-1 && j > 0 && j < array[0].length-1) {
                    //checkForDownBorder
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new LowPointObject(i,j,array[i][j]));
                    }
                } else {
                    //checkForAllOtherPoints (middle)
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1])
                            && (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new LowPointObject(i, j, array[i][j]));
                    }
                }

            }
        }
        return lowPointList;
    }


    /**
     * right answer 9.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException, URISyntaxException {
        ArrayList<LowPointObject> list = getLowPoints();
        int riskLevel = 0;
        for (LowPointObject lpo : list) {
            riskLevel += lpo.LOWPOINT + 1;
        }
        System.out.println(riskLevel);
    }

    public static void getPuzzle2() throws IOException, URISyntaxException {
        int[][] array = readInput();
        ArrayList<BasinObject> basinLineList = new ArrayList<>();
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            int start = -1;
            for (int j = 0; j < array[0].length; j++) {

                if (array[i][j] != 9) {
                    if (start == -1) {
                        start = j;
                    }
                    tmp++;
                    if (j == array[0].length-1) {
                        basinLineList.add(new BasinObject(start, j, tmp, i));
                        tmp = 0;
                    }
                } else if (array[i][j] == 9 && tmp != 0) {
                        basinLineList.add(new BasinObject(start, j-1, tmp, i));
                        tmp = 0;
                        start = -1;
                }
            }
        }
        for (BasinObject obj : basinLineList) {
            System.out.println("start = " + obj.startPoint + ", end = " + obj.endPoint +
                    ", sum = " + obj.sum + ", level = " + obj.level);
        }
        HashMap<Integer, ArrayList<Integer>> basinMap = new HashMap<>();
        int id = 0;
        //TODO: change int l = 1 to int l = 0 --> start at lowest level and compare with l+1. If no more unions are
        // found go to next group in same level and increase id. Since current group is compared with all possible
        // successor groups, id can be increased, after level increase: what to do with id? --> id 0 from level 0 is in
        // same group as obj on level 2 --> level 0 & 1 object are already in group (id 0), but current id is increased,
        // maybe recursive: check for all successors of current object in level 0, if successor in same group, check
        // successor of successor recursively, until there are no successors in same group. (this should work)

        ArrayList<Integer> sumList = getGroups(basinLineList);
    }

    public static ArrayList<Integer> getGroups(ArrayList<BasinObject> basinLineList) {
        ArrayList<Integer> sumList = new ArrayList<>();
        ArrayList<BasinObject> unvisited = new ArrayList<>(basinLineList);
        int maxLevel = basinLineList.get(basinLineList.size()).level;
        return getGroupsRecursive(sumList, unvisited,  0, maxLevel, 0);
    }

    public static ArrayList<Integer> getGroupsRecursive(ArrayList<Integer> sumList, ArrayList<BasinObject> unvisited,
                                                        int currentLevel, int maxLevel, int currentSum) {
        for (BasinObject unv: unvisited) {
            if (unv.level == currentLevel) {
                if (currentLevel == 0) {
                    currentSum += unv.sum;
                } else {

                }
            }
        }
        return sumList;
    }

    private static class BasinObject {
        int startPoint, endPoint, sum, level;

        public BasinObject(int startPoint, int endPoint, int sum, int level) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.sum = sum;
            this.level = level;
        }
    }

    private static class LowPointObject {
        int ROW, COL, LOWPOINT;

        public LowPointObject(int row, int col, int lowPoint) {
            this.ROW = row;
            this.COL = col;
            this.LOWPOINT = lowPoint;
        }
    }
}






