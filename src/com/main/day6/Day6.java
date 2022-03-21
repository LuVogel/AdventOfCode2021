package com.main.day6;

import com.main.day4.BingoBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6 {

    /**
     * right answer for 6.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_6.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<Integer> stateArraylist = new ArrayList<>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split(",");
            for (String s: tempStringArray) {
                stateArraylist.add(Integer.parseInt(s));
            }
        }

        int dayCounter = 0;
        ArrayList<Integer>  tempList = new ArrayList<>();
        while (dayCounter != 80) {
            for (int i = 0; i < stateArraylist.size(); i++) {
                if (stateArraylist.get(i) == 0) {
                    tempList.add(8);
                    stateArraylist.set(i, 6);
                } else {
                    stateArraylist.set(i, stateArraylist.get(i) - 1);
                }
            }
            for (int newStates: tempList) {
                stateArraylist.add(newStates);
            }
            tempList.clear();
            dayCounter++;
        }
        System.out.println(stateArraylist.size());
    }

    /**
     * right solution for 6.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_6.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        long[] array = new long[9];
        Arrays.fill(array, 0);

        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split(",");
            for (String s: tempStringArray) {
                int temp = Integer.parseInt(s);
                if (array[temp] == 0) {
                    array[temp] =  1;
                } else {
                    array[temp] += 1;
                }
            }
        }
        int dayCounter = 0;

        while (dayCounter != 256) {
            long temp = 0;
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    temp = array[i];
                } else {
                    array[i-1] = array[i];
                }
                array[i] = 0;
            }
            if (temp != 0) {
                array[6] += temp;
                array[8] += temp;
            }
            dayCounter++;
        }
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println(sum);
    }
}
