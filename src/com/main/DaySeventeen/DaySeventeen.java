package com.main.DaySeventeen;

import java.io.*;
import java.util.ArrayList;

public class DaySeventeen {

    boolean testCase;
    String puzzleNumber;
    int xMinTargetPos;
    int yMaxTargetPos;
    int xMaxTargetPos;
    int yMinTargetPos;

    public DaySeventeen(String puzzleNumber, boolean testCase) {
        this.puzzleNumber = puzzleNumber;
        this.testCase = testCase;
        readInput();
        System.out.println("target area: x=" + xMinTargetPos + ".." + xMaxTargetPos + ", y=" + yMinTargetPos + ".." + yMaxTargetPos);
        if (puzzleNumber.equals("1")) {
            Puzzle1();
        } else if (puzzleNumber.equals("2")) {
            Puzzle2();
        }
    }

    private void Puzzle1() {
        int res = ((yMaxTargetPos * yMaxTargetPos) + yMaxTargetPos) / 2;
        System.out.println("highest y position reached on this trajectory is: " + res);
    }

    private void Puzzle2() {
        int res = 0;
        for (int i = 0; i < 2000; i++) {
            for (int j = -2000; j < 2000; j++) {
                res += shootTargetWithVelocity(i, j);
            }
        }

        System.out.println("amount of distinct velocities: " + res);
    }

    private int shootTargetWithVelocity(int xVelocity, int yVelocity) {
        int xPos = 0;
        int yPos = 0;
        while (true) {
            xPos += xVelocity;
            yPos += yVelocity;
            if (xVelocity < 0) {
                xVelocity += 1;
            } else if (xVelocity > 0) {
                xVelocity -= 1;
            }
            yVelocity -= 1;
            if (checkForWin(xPos, yPos)) {
                return 1;
            } else if (checkForLoose(xPos, yPos)) {
                return 0;
            }
        }
    }

    private boolean checkForLoose(int xPos, int yPos) {
        if ((xPos > xMaxTargetPos) || (yPos < yMaxTargetPos)) {
            return true;
        }
        return false;
    }

    private boolean checkForWin(int xPos, int yPos) {
        if ((xMinTargetPos <= xPos) && (xPos <= xMaxTargetPos) && (yPos <= yMinTargetPos) && (yMaxTargetPos <= yPos)) {
            return true;
        }
        return false;
    }

    private void readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            if (testCase) {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_17_test.txt");
            } else {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_17.txt");
            }
        } else if (os.equals("Windows 10")) {
            if (testCase) {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_17_test.txt");
            } else {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_17.txt");
            }
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
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tmp = line.split("=");
        String[] xHalf = tmp[1].split(",")[0].split("");
        String[] yHalf = tmp[2].split("");
        int i = 0;
        String s = "";
        while (!xHalf[i].equals(".")) {
            s += xHalf[i];
            i++;
        }
        xMinTargetPos = Integer.parseInt(s);
        i = xHalf.length-1;
        s = "";
        while (!xHalf[i].equals(".")) {
            s += xHalf[i];
            i--;
        }
        String[] s2 = s.split("");
        String xT = "";
        for (int j = s2.length-1; j >= 0; j--) {
            xT += s2[j];
        }
        xMaxTargetPos = Integer.parseInt(xT);
        i = 0;
        s = "";
        while (!yHalf[i].equals(".")) {
            s += yHalf[i];
            i++;
        }
        yMaxTargetPos = Integer.parseInt(s);
        i = yHalf.length-1;
        s = "";
        while (!yHalf[i].equals(".")) {
            s += yHalf[i];
            i--;
        }
        String[] s1 = s.split("");
        String yT = "";
        for (int j = s1.length-1; j >= 0; j--) {
            yT += s1[j];
        }
        yMinTargetPos = Integer.parseInt(yT);

    }
}
