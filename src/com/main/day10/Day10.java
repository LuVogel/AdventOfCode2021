package com.main.day10;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/**
 * working puzzle1 & puzzle2
 *
 * remarks: long instead of int because of huge numbers in puzzle2
 */
public class Day10 {

    private ArrayList<String> readInput() {
        ArrayList<String> input = new ArrayList<>();
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_10_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_10.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                input.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public ArrayList<Stack<String>> Puzzle1() {
        ArrayList<String> inputList = readInput();
        ArrayList<Stack<String>> puzzle2List = new ArrayList<Stack<String>>();
        int sum = 0;
        for (String s: inputList) {
            String[] tmp = s.split("");
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].equals("(")) {
                    stack.push(tmp[i]);
                } else if (tmp[i].equals("{")) {
                    stack.push(tmp[i]);
                } else if (tmp[i].equals("[")) {
                    stack.push(tmp[i]);
                } else if (tmp[i].equals("<")) {
                    stack.push(tmp[i]);
                } else {
                    String current = tmp[i];
                    String prev = stack.pop();
                    if (current.equals(")") && !prev.equals("(")) {
                        sum += 3;
                        i = tmp.length+1;

                    } else if (current.equals("]") && !prev.equals("[")) {
                        sum += 57;
                        i = tmp.length+1;

                    } else if (current.equals("}") && !prev.equals("{")) {
                        sum += 1197;
                        i = tmp.length+1;

                    } else if (current.equals(">") && !prev.equals("<")) {
                        sum += 25137;
                        i = tmp.length+1;
                    }
                }
                if (i == tmp.length-1 && !stack.isEmpty()) {
                    puzzle2List.add(stack);
                }
            }
        }
        System.out.println("total syntax error corrupted lines = " + sum);
        return puzzle2List;

    }

    public void Puzzle2(ArrayList<Stack<String>> helpFor2) {
        ArrayList<Long> scoreList = new ArrayList<>();
        for (Stack<String> currentStack : helpFor2) {
            long sum = 0;
            while (!currentStack.isEmpty()) {
                String tmp = currentStack.pop();
                if (tmp.equals("(")) {
                    sum *= 5;
                    sum += 1;
                } else if (tmp.equals("[")) {
                    sum *= 5;
                    sum += 2;
                } else if (tmp.equals("{")) {
                    sum *= 5;
                    sum += 3;
                } else if (tmp.equals("<")) {
                    sum *= 5;
                    sum += 4;
                }
            }
            scoreList.add(sum);
        }
        Collections.sort(scoreList);
        int max = scoreList.size();
        System.out.println("middle score incomplete lines = " + scoreList.get(max / 2));
    }
}
