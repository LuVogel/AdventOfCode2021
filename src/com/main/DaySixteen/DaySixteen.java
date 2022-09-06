package com.main.DaySixteen;

import java.io.*;
import java.util.ArrayList;


public class DaySixteen {


    //TODO: handle literal value right..

    boolean testCase;
    ArrayList<String> binaryList;
    ArrayList<String> partTwoList;
    ArrayList<String> operators;

    private class NumberPacket {
        String number;
        boolean isBinary;

        public NumberPacket(String number, boolean isBinary) {
            this.number = number;
            this.isBinary = isBinary;
        }
    }


    public DaySixteen(String puzzleNumber, boolean testCase) {
        this.testCase = testCase;
        if (puzzleNumber.equals("1")) {
            System.out.println("Solution Day16, Part 1 :");
            Puzzle(1);
        } else if (puzzleNumber.equals("2")) {
            System.out.println("Solution Day16, Part 2: ");
            Puzzle(2);

        }
    }

    private void Puzzle(int part) {
        readInput();
        System.out.println();
        int sum = binaryToLabels(binaryList);
        System.out.println("version sum: " + sum);
        if (part == 2) {
            operators = new ArrayList<>();
            operators.add("+");
            operators.add("*");
            operators.add("min");
            operators.add("max");
            operators.add("g");
            operators.add("l");
            operators.add("e");
            System.out.println("------------------");
            for (String s : partTwoList) {
                System.out.println(s);
            }
            ArrayList<String> transmissionList = getListForTransmission();

            System.out.println("------------------");
            /*for (String s : transmissionList) {
                System.out.println(s);
            }*/
            System.out.println("------------------");
            int transmissionValue = getTransmissionValue(transmissionList);
            System.out.println("transmission value: " + transmissionValue + "\n");

        }

    }

    private int getTransmissionValue(ArrayList<String> transmissionList) {
        ArrayList<NumberPacket> helper = new ArrayList<>();
        ArrayList<NumberPacket> helper1 = new ArrayList<>();
        int transVal = 0;
        while (!transmissionList.isEmpty()) {
            String current = transmissionList.remove(0);
            if (!operators.contains(current)) {
                helper.add(new NumberPacket(current, true));
            } else {
                if (helper.isEmpty()) {
                    helper = (ArrayList<NumberPacket>) helper1.clone();
                    helper1.clear();
                }
                if (current.equals("*")) {
                    transVal = 1;
                    while (!helper.isEmpty()) {
                        NumberPacket n = helper.remove(0);
                        if (n.isBinary) {
                            transVal *= binaryToDecimal(n.number);
                        } else {
                            transVal *= Integer.parseInt(n.number);
                        }

                    }
                } else if (current.equals("+")) {
                    transVal = 0;
                    while (!helper.isEmpty()) {
                        NumberPacket n = helper.remove(0);
                        if (n.isBinary) {
                            transVal += binaryToDecimal(n.number);
                        } else {
                            transVal += Integer.parseInt(n.number);
                        }
                    }
                } else if (current.equals("e")) {
                    NumberPacket n = helper.remove(0);
                    NumberPacket n1 = helper.remove(0);
                    if  (n.number.equals(n1.number)) {
                        transVal = 1;
                    } else {
                        transVal = 0;
                    }
                } else if (current.equals("l")) {
                    NumberPacket n = helper.remove(0);
                    NumberPacket n1 = helper.remove(0);
                    int t1;
                    int t2;
                    if (n.isBinary) {
                        t1 = Integer.parseInt(n.number);
                    } else {
                        t1 = binaryToDecimal(n.number);
                    }
                    if (n1.isBinary) {
                        t2 = Integer.parseInt(n1.number);
                    } else {
                        t2 = binaryToDecimal(n1.number);
                    }
                    if (t1 < t2) {
                        transVal = 1;
                    } else {
                        transVal = 0;
                    }
                } else if (current.equals("g")) {
                    NumberPacket n = helper.remove(0);
                    NumberPacket n1 = helper.remove(0);
                    int t1;
                    int t2;
                    if (n.isBinary) {
                        t1 = Integer.parseInt(n.number);
                    } else {
                        t1 = binaryToDecimal(n.number);
                    }
                    if (n1.isBinary) {
                        t2 = Integer.parseInt(n1.number);
                    } else {
                        t2 = binaryToDecimal(n1.number);
                    }
                    if (t1 > t2) {
                        transVal = 1;
                    } else {
                        transVal = 0;
                    }
                } else if (current.equals("min")) {
                    transVal = Integer.MAX_VALUE;
                    while (!helper.isEmpty()) {
                        NumberPacket n = helper.remove(0);
                        int tmp;
                        if (n.isBinary) {
                            tmp = binaryToDecimal(n.number);
                        } else {
                            tmp = Integer.parseInt(n.number);
                        }
                        if (tmp < transVal) {
                            transVal = tmp;
                        }
                    }
                } else if (current.equals("max")) {
                    transVal = Integer.MIN_VALUE;
                    while (!helper.isEmpty()) {
                        NumberPacket n = helper.remove(0);
                        int tmp;
                        if (n.isBinary) {
                            tmp = binaryToDecimal(n.number);
                        } else {
                            tmp = Integer.parseInt(n.number);
                        }
                        if (tmp > transVal) {
                            transVal = tmp;
                        }
                    }
                }
                System.out.println("added val: " + transVal);
                helper1.add(new NumberPacket(String.valueOf(transVal), false));

            }
        }
        return transVal;
    }

    private ArrayList<String> getListForTransmission() {
        ArrayList<String> helper = new ArrayList<>();
        return getListForTransmissionRec(helper, "");
    }

    private ArrayList<String> getListForTransmissionRec(ArrayList<String> helper, String current) {
        while (!partTwoList.isEmpty()) {
            current = partTwoList.remove(0);
            getListForTransmissionRec(helper, current);
            helper.add(current);


        }
        return helper;
    }


    private int binaryToLabels(ArrayList<String> list) {
        int sum = 0;
        partTwoList = new ArrayList<>();
        while (!list.isEmpty() && list.size() > 6) {
            String packetVersionAsString = list.remove(0) + list.remove(0) + list.remove(0);
            String packetIDAsString = list.remove(0) + list.remove(0) + list.remove(0);
            int packetVersion = binaryToDecimal(packetVersionAsString);
            String subPackNumb = "";
            int packetID = binaryToDecimal(packetIDAsString);
            if (packetID == 4) {
                //packet is Literal
                String lastTracker = list.remove(0);
                //last group
                if (lastTracker.equals("0")) {
                    for (int i = 0; i < 4; i++) {
                        subPackNumb += list.remove(0);
                    }
                } else {
                    //as long as first number of new group is 1, its not last group
                    while (!lastTracker.equals("0")) {
                        //remove 4 last numbers of group
                        for (int i = 0; i < 4; i++) {
                            subPackNumb += list.remove(0);
                        }
                        //remove first of group
                        lastTracker = list.remove(0);
                    }
                    //last group
                    for (int i = 0; i < 4; i++) {
                        subPackNumb += list.remove(0);
                    }
                }
                partTwoList.add(subPackNumb);
            } else {
                //operator
                partTwoList.add(getOperator(packetID));
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

    private String getOperator(int packetID) {
        if (packetID == 0) {
            return "+";
        } else if (packetID == 1) {
            return "*";
        } else if (packetID == 2) {
            return "min";
        } else if (packetID == 3) {
            return "max";
        } else if (packetID == 5) {
            return "g";
        } else if (packetID == 6) {
            return "l";
        } else if (packetID == 7) {
            return "e";
        }
        return null;
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

    private void readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            if (testCase) {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_16_test.txt");
            } else {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_16.txt");
            }
        } else if (os.equals("Windows 10")) {
            if (testCase) {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_16_test.txt");
            } else {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_16.txt");
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
