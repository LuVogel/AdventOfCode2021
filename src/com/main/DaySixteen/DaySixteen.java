package com.main.DaySixteen;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySixteen {


    //TODO: handle literal value right..

    ArrayList<String> binaryList;
    String[] labelArray;

    public DaySixteen(String puzzleNumber) {
        if (puzzleNumber.equals("1")) {
            System.out.println("Solution Day16, Part 1 :");
            Puzzle(1);
        } else if (puzzleNumber.equals("2")) {
            System.out.println("Solution Day16, Part 2: ");
            Puzzle(2);

        }
    }

    private void Puzzle(int part) {
        if (part == 1 ) {
            readInput1();
        } else if (part == 2) {

        }

       for (String s : binaryList) {
           System.out.print(s);
       }
        System.out.println();
        int sum = binaryToLabels(binaryList);
        System.out.println(sum);

    }

    private int binaryToLabels(ArrayList<String> list) {
        int sum = 0;

        while (!list.isEmpty()) {
            String packetVersionAsString = list.remove(0) + list.remove(0) + list.remove(0);
            String packetIDAsString = list.remove(0) + list.remove(0) + list.remove(0);
            int packetVersion = binaryToDecimal(packetVersionAsString);
            String subPackNumb = "";
            int packetID = binaryToDecimal(packetIDAsString);
            if (packetID == 4) {

                //literal value
                return sum += packetVersion;
            } else {
                //operator
                String lengthTypeID = list.remove(0);
                if (binaryToDecimal(lengthTypeID) == 0 && list.size() >= 15) {
                    //15 bits
                    for (int i = 0; i < 15; i++) {
                        subPackNumb += list.remove(0);
                    }
                } else if (binaryToDecimal(lengthTypeID) == 1 && list.size() >= 11) {
                    //11 bits
                    for (int i = 0; i < 11; i++) {
                        subPackNumb += list.remove(0);
                    }
                }
            }
            sum += packetVersion;

        }
        return sum;
    }

    private int binaryToDecimal(String s) {
        int c = 3;
        while (s.length() % 4 != 0) {
            s = "0" + s;
        }
        String[] tmp = s.split("");
        int res = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].equals("1")) {
                res += Math.pow(2, c);

            }
            c--;
        }
        return res;
    }

    private void readInput1() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_16_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_16_test.txt");
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
        String map = "";
        String line = "";

        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            map += line;
        }
        String[] hex = map.split("");
        binaryList = new ArrayList<>();
        for (int i = 0; i < hex.length; i++) {
            String tmp = hexToBinary(hex[i]);
            for (String s : tmp.split("")) {
                binaryList.add(s);
            }
        }
    }

    private String hexToBinary(String tmp) {
        String bin = "";
        if (tmp.equals("0")) {
            bin = "0000";
        } else if (tmp.equals("1")) {
            bin = "0001";
        } else if (tmp.equals("2")) {
            bin = "0010";
        } else if (tmp.equals("3")) {
            bin = "0011";
        } else if (tmp.equals("4")) {
            bin = "0100";
        } else if (tmp.equals("5")) {
            bin = "0101";
        } else if (tmp.equals("6")) {
            bin = "0110";
        } else if (tmp.equals("7")) {
            bin = "0111";
        } else if (tmp.equals("8")) {
            bin = "1000";
        } else if (tmp.equals("9")) {
            bin = "1001";
        } else if (tmp.equals("A")) {
            bin = "1010";
        } else if (tmp.equals("B")) {
            bin = "1011";
        } else if (tmp.equals("C")) {
            bin = "1100";
        } else if (tmp.equals("D")) {
            bin = "1101";
        } else if (tmp.equals("E")) {
            bin = "1110";
        } else if (tmp.equals("F")) {
            bin = "1111";
        }
        return bin;
    }
}
