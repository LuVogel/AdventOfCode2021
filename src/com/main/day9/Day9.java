package com.main.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day9 {


    /**
     * right answer 9.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_9.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";
        long sum = 0;
        ArrayList<ArrayList <Integer> > list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] temp = line.split("");
            ArrayList<Integer> tempList = new ArrayList<>();
            for (String s: temp) {
                tempList.add(Integer.parseInt(s));
            }
            list.add(tempList);
        }
        int row = list.size();
        int col = list.get(0).size();
        int[][] heightMap = new int[row][col];
        int counter = 0;
        for (ArrayList<Integer> tmp : list) {
            for (int i = 0; i < tmp.size(); i++) {
                heightMap[counter][i] = tmp.get(i);
            }
            counter++;
        }

        // check for low points

        int riskLevel = 0;
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                if (i == 0 && j == 0) {
                    if (heightMap[i][j] < heightMap[i][j+1] && heightMap[i][j] < heightMap[i+1][j]) {
                        //top left corner is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i != 0  && j == 0 && i != (heightMap.length - 1)) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j+1] &&
                        heightMap[i][j] < heightMap[i+1][j]) {
                        //left border (not corners) is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i == heightMap.length-1 && j == 0) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j+1]) {
                        //bottom left corner is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i == 0 && j < heightMap[0].length-1 && j > 0) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                        heightMap[i][j] < heightMap[i+1][j]) {
                        //top border (not corners) is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i == 0 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i+1][j]) {
                        //top right corner is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i != 0 && i != heightMap.length-1 && j != 0 && j != heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                        heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i+1][j]) {
                        // all not border fields are low points
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i == heightMap.length-1 && j != 0 && j != heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                        heightMap[i][j] < heightMap[i-1][j]) {
                        // bottom border (not corners) are low points
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i == heightMap.length-1 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j-1]) {
                        //bottom right corner is low point
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
                if (i != 0 && i != heightMap.length-1 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i+1][j] &&
                        heightMap[i][j] < heightMap[i][j-1]) {
                        //right border (not corners) are low points
                        riskLevel += (heightMap[i][j] + 1);
                    }
                }
            }
        }
        System.out.println(riskLevel);
    }

    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_9_test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";
        long sum = 0;
        ArrayList<ArrayList <Integer> > list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] temp = line.split("");
            ArrayList<Integer> tempList = new ArrayList<>();
            for (String s: temp) {
                tempList.add(Integer.parseInt(s));
            }
            list.add(tempList);
        }
        int row = list.size();
        int col = list.get(0).size();
        int[][] heightMap = new int[row][col];
        int counter = 0;
        for (ArrayList<Integer> tmp : list) {
            for (int i = 0; i < tmp.size(); i++) {
                heightMap[counter][i] = tmp.get(i);
            }
            counter++;
        }

        // check for low points

        int[] basin = new int[3];
        Arrays.fill(basin, 0);
        int riskLevel = 0;
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                if (i == 0 && j == 0) {
                    if (heightMap[i][j] < heightMap[i][j+1] && heightMap[i][j] < heightMap[i+1][j]) {
                        //top left corner is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r++;

                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i != 0  && j == 0 && i != (heightMap.length - 1)) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j+1] &&
                            heightMap[i][j] < heightMap[i+1][j]) {
                        //left border (not corners) is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r++;
                            c = j;
                        }
                        r = i-1;
                        c = j;
                        while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i == heightMap.length-1 && j == 0) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j+1]) {
                        //bottom left corner is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }

                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }

                    }
                }
                if (i == 0 && j < heightMap[0].length-1 && j > 0) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                            heightMap[i][j] < heightMap[i+1][j]) {
                        //top border (not corners) is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c+1] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r++;
                            c = j;
                        }
                        r = i;
                        c = j-1;
                        while (r < heightMap.length && c >= 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r++;
                            c = j-1;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i == 0 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i+1][j]) {
                        //top right corner is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r++;
                            c = j;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i != 0 && i != heightMap.length-1 && j != 0 && j != heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                            heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i+1][j]) {
                        // all not border fields are low points
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r++;
                            c = j;
                        }
                        r = i - 1;
                        c = j;
                        while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c < heightMap[0].length && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }
                        r = i;
                        c = j-1;
                        while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r++;
                            c = j-1;
                        }
                        r = i-1;
                        c = j-1;
                        while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r--;
                            c = j-1;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i == heightMap.length-1 && j != 0 && j != heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i][j-1] && heightMap[i][j] < heightMap[i][j+1] &&
                            heightMap[i][j] < heightMap[i-1][j]) {
                        // bottom border (not corners) are low points
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r > 0 && c < heightMap[0].length-1 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c < heightMap[0].length-1 && heightMap[r][c] != 9) {
                                c++;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }
                        r = i;
                        c = j-1;
                        while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r--;
                            c = j-1;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i == heightMap.length-1 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j-1]) {
                        //bottom right corner is low point
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
                if (i != 0 && i != heightMap.length-1 && j == heightMap[0].length-1) {
                    if (heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i+1][j] &&
                            heightMap[i][j] < heightMap[i][j-1]) {
                        //right border (not corners) are low points
                        int basinLevel = 0;
                        int r = i;
                        int c = j;
                        while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r > 0 && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r--;
                            c = j;
                        }
                        r = i+1;
                        c = j;
                        while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                            basinLevel++;
                            while (r < heightMap.length && c > 0 && heightMap[r][c] != 9) {
                                c--;
                                basinLevel++;
                            }
                            r++;
                            c = j;
                        }
                        int minIndex = getSmallestFromArray(basin);
                        if (basinLevel > basin[minIndex]) {
                            basin[minIndex] = basinLevel;
                        }
                    }
                }
            }
        }
        int res = basin[0] * basin[1] * basin[2];
        System.out.println(res);
    }

    public static int getSmallestFromArray(int[] array) {
        int temp = 0;
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= min) {
                min = array[i];
                temp = i;
            }

        }
        return temp;
    }

}


