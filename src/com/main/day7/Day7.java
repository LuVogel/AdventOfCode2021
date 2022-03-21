package com.main.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day7 {

    /**
     * right answer for 7.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_7.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Integer> list = new ArrayList<>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split(",");
            for (String s : tempStringArray) {
                int temp = Integer.parseInt(s);
                list.add(temp);
            }
        }
        int fuel = 0;
        ArrayList<Integer> fuelList = new ArrayList<>();
        int counter = 0;
        int fuelCounter = 0;
        while (counter < list.size()) {
            for (int i = 0; i < list.size(); i++) {
                fuel += Math.abs(list.get(i) - counter);
            }
            fuelList.add(fuel);
            counter++;
            fuel = 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i : fuelList) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println(min);
    }

    /**
     * right answer for 7.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_7.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<Integer> list = new ArrayList<>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split(",");
            for (String s : tempStringArray) {
                int temp = Integer.parseInt(s);
                list.add(temp);
            }
        }
        int fuel = 0;
        ArrayList<Integer> fuelList = new ArrayList<>();
        int counter = 0;
        int fuelCounter = 0;
        while (counter < list.size()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 1; j < Math.abs(list.get(i) - counter)+1; j++) {
                    fuel += j;
                }
            }
            fuelList.add(fuel);
            counter++;
            fuel = 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i : fuelList) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println(min);
    }

}
