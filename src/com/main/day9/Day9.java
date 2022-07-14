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
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_9_test.txt");
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

        ArrayList<Integer> sumList = getGroups(basinLineList);
        for (Integer x:sumList) {
            System.out.println(x);
        }
    }

    public static ArrayList<Integer> getGroups(ArrayList<BasinObject> basinLineList) {
        ArrayList<Integer> sumList = new ArrayList<>();
        ArrayList<BasinObject> unvisited = new ArrayList<>(basinLineList);
        return getGroupsRecursive(unvisited.get(0), sumList, unvisited, 0);
    }

    public static ArrayList<Integer> getGroupsRecursive(BasinObject bo, ArrayList<Integer> sumList, ArrayList<BasinObject> unvisited,
                                                        int currentSum) {
        if (!unvisited.contains(bo) && !unvisited.isEmpty()) {
            bo = unvisited.get(0);
        }
        currentSum += bo.sum;
        unvisited.remove(bo);
        ArrayList<BasinObject> succList = getSuccessor(bo, unvisited);
        for (BasinObject succ : succList) {
            System.out.println("rec-call on succ-object: " + succ.startPoint + "," + succ.endPoint);
            return getGroupsRecursive(succ, sumList, unvisited, currentSum);

        }
        System.out.println("add final sum" + " " + currentSum);
        sumList.add(currentSum);
        if (!unvisited.isEmpty()) {
            System.out.println("rec-call for new group");
            return  getGroupsRecursive(unvisited.get(0), sumList, unvisited, 0);
        }

        return sumList;
    }

    public static ArrayList<BasinObject> getSuccessor(BasinObject bo, ArrayList<BasinObject> nodes) {
        ArrayList<BasinObject> list = new ArrayList<>();
        for (BasinObject x: nodes) {
            if (bo.level == x.level-1) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = bo.startPoint; i <= bo.endPoint; i++) {
                    tmp.add(i);
                }
                ArrayList<Integer> tmpCurrent = new ArrayList<>();
                for (int i = x.startPoint; i <= x.endPoint; i++) {
                    tmpCurrent.add(i);
                }
                boolean check = false;
                for (Integer i: tmp) {
                    if (tmpCurrent.contains(i)) {
                        check = true;
                    }
                }
                if (check) {
                    list.add(x);
                }
            }
        }
        return list;
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






