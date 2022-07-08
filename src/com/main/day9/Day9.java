package com.main.day9;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Day9 {

    int ROW, COL, LOWPOINT;

    public Day9(int row, int col, int lowPoint) {
        this.ROW = row;
        this.COL = col;
        this.LOWPOINT = lowPoint;
    }




    private static int[][] readInput() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_9_test.txt");
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

    private static ArrayList<Day9> getLowPoints() throws IOException {
        int[][] array = readInput();
        ArrayList<Day9> lowPointList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i == 0 && j == 0) {
                    //checkForTopLeftCorner
                    if ((array[i][j] < array[i][j+1]) && (array[i][j] < array[i+1][j])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
                    }
                } else if (i == 0 && j > 0 && j < array[0].length-1) {
                    //checkForTopBorder
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
                    }
                } else if (i == 0 && j == array[0].length-1) {
                    //checkForTopRightCorner
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
                    }
                } else if (i > 0 && i < array.length-1 && j == 0) {
                    //checkForLeftBorder
                    if ((array[i][j] < array[i+1][j]) && (array[i][j] < array[i-1][j]) && array[i][j] < array[i][j+1]) {
                        lowPointList.add(new Day9(i,j,array[i][j]));
                    }
                } else if (i > 0 && i < array.length-1 && j == array[0].length-1) {
                    //checkForRightBorder
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i+1][j])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
                    }
                } else if (i == array.length-1 && j == 0) {
                    //checkForDownLeftCorner
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
                    }
                } else if (i == array.length-1 && j == array[0].length-1) {
                    //checkForDownRightCorner
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1])) {
                        lowPointList.add(new Day9(i,j,array[i][j]));
                    }
                } else if (i == array.length-1 && j > 0 && j < array[0].length-1) {
                    //checkForDownBorder
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i][j-1]) &&
                            (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new Day9(i,j,array[i][j]));
                    }
                } else {
                    //checkForAllOtherPoints (middle)
                    if ((array[i][j] < array[i-1][j]) && (array[i][j] < array[i+1][j]) && (array[i][j] < array[i][j-1])
                            && (array[i][j] < array[i][j+1])) {
                        lowPointList.add(new Day9(i, j, array[i][j]));
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
    public static void getPuzzle1() throws IOException {
        ArrayList<Day9> list = getLowPoints();
        int riskLevel = 0;
        for (Day9 day : list) {
            riskLevel += day.LOWPOINT + 1;
        }
        System.out.println(riskLevel);
    }

    public static void getPuzzle2() throws IOException {
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

        for (int l = 1; l < array.length; l++) {
            for (BasinObject obj : basinLineList) {
                boolean changeID = false;
                if (obj.level == l) {
                    //on current level
                    ArrayList<Integer> currentList = new ArrayList<>();
                    for (int tmpStart = obj.startPoint; tmpStart <= obj.endPoint; tmpStart++) {
                        currentList.add(tmpStart);
                    }
                    for (BasinObject prevObj : basinLineList) {
                        if (prevObj.level == l-1) {
                            ArrayList<Integer> prevList = new ArrayList<>();
                            for (int prevStart = prevObj.startPoint; prevStart <= prevObj.endPoint; prevStart++) {
                                prevList.add(prevStart);
                            }
                            boolean checkLowerLevel = false;
                            for (Integer check : currentList) {
                                if (prevList.contains(check)) {
                                    checkLowerLevel = true;
                                }
                            }
                            if (checkLowerLevel) {
                                if (basinMap.get(id) != null) {
                                    ArrayList<Integer> a = basinMap.get(id);
                                    a.add(prevObj.sum);
                                    a.add(obj.sum);
                                    basinMap.put(id, a);
                                    changeID = true;
                                } else {
                                    ArrayList<Integer> tempList = new ArrayList<>();
                                    tempList.add(prevObj.sum);
                                    tempList.add(obj.sum);
                                    basinMap.put(id, tempList);
                                    changeID = true;
                                }
                            }
                        }
                    }
                }
                if (changeID) {
                    id++;
                }
            }
        }
        ArrayList<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            ArrayList<Integer> x = basinMap.get(i);
            int sum = 0;
            for (Integer digit : x) {
                sum += digit;
            }
            sumList.add(sum);
        }
        Collections.sort(sumList);
        Collections.reverse(sumList);
        System.out.println("1: " + sumList.get(0) + "\n2: " + sumList.get(1) + "\n3: " + sumList.get(2));
        for (Integer z : sumList) {
            System.out.println(z);
        }
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
}






