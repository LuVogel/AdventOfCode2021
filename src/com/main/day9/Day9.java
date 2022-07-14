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
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_9.txt");
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

    private static PointsAndInputObject getLowPoints() throws IOException, URISyntaxException {
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
        return new PointsAndInputObject(array, lowPointList);
    }


    /**
     * right answer 9.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException, URISyntaxException {
        PointsAndInputObject paio = getLowPoints();
        ArrayList<LowPointObject> list = paio.LOWPOINTLIST;
        int riskLevel = 0;
        for (LowPointObject lpo : list) {
            riskLevel += lpo.LOWPOINT + 1;
        }
        System.out.println(riskLevel);
    }

    /**
     * correct version: solution from reddit
     * uses getBasins()
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void getPuzzle2() throws IOException, URISyntaxException {
        PointsAndInputObject paio = getLowPoints();
        ArrayList<LowPointObject> unvisited = paio.LOWPOINTLIST;
        ArrayList<Integer> sumList = new ArrayList<>();
        for (LowPointObject lpo : unvisited) {
            sumList.add(getBasins(lpo.ROW, lpo.COL, paio.ARRAY));
        }
        Collections.sort(sumList);
        Collections.reverse(sumList);
        int max1 = sumList.get(0);
        int max2 = sumList.get(1);
        int max3 = sumList.get(2);
        System.out.println("max1, max2, max3 = " + max1 + ", " + max2 + ", " + max3 + ", prod = " + (max1 * max2 * max3));

    }

    public static int getBasins(int x, int y, int[][] inputArray) {
        int currentSum = 0;
        int maxBottom = inputArray.length-1;
        int maxRight = inputArray[0].length-1;
        if (inputArray[x][y] == 9 ||inputArray[x][y] == 100) {
            return currentSum;
        }
        inputArray[x][y] = 100;
        currentSum += 1;
        if (y + 1 <= maxRight) {
            currentSum += getBasins(x, y + 1, inputArray);
        }
        if (y - 1 >= 0) {
            currentSum += getBasins(x, y - 1, inputArray);
        }
        if (x + 1 <= maxBottom) {
            currentSum += getBasins(x + 1, y, inputArray);
        }
        if (x - 1 >= 0) {
            currentSum += getBasins(x - 1, y, inputArray);
        }
        return currentSum;
    }


    private static class LowPointObject {
        int ROW, COL, LOWPOINT;
        public LowPointObject(int row, int col, int lowPoint) {
            this.ROW = row;
            this.COL = col;
            this.LOWPOINT = lowPoint;
        }
    }

    private static class PointsAndInputObject {
        int[][] ARRAY;
        ArrayList<LowPointObject> LOWPOINTLIST;

        public PointsAndInputObject(int[][] array, ArrayList<LowPointObject> lowPointObject) {
            this.ARRAY = array;
            this.LOWPOINTLIST = lowPointObject;
        }
    }
}






