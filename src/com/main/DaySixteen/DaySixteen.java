package com.main.DaySixteen;

import java.io.*;
import java.util.ArrayList;



public class DaySixteen {

    //Part 2 inspired by Solution of Reddit User Natrium_Benzoat


    boolean testCase;
    ArrayList<String> binaryList;
    ArrayList<String> partTwoList;
    int counter = 0;

    protected class Node {
        final long packetVersion;
        final long packetID;
        long literalValue;
        final ArrayList<Node> childs = new ArrayList<>();

        public Node(long packetVersion, long packetID) {
            this.packetVersion = packetVersion;
            this.packetID = packetID;
        }

        public long getPacketID() {
            return packetID;
        }

        public ArrayList<Node> getChilds() {
            return childs;
        }

        public long getPacketVersion() {
            return packetVersion;
        }

        public long getLiteralValue() {
            return literalValue;
        }

        public void setLiteralValue(long literalValue) {
            this.literalValue = literalValue;
        }

        public void addChild(Node child) {
            childs.add(child);
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
        partTwoList = new ArrayList<>();
        partTwoList.addAll(binaryList);
        long sum = getVersionSum(binaryList);
        System.out.println("version sum: " + sum);
        if (part == 2) {
            Node firstNode = validatePacket();
            calculateLiteralValues(firstNode);
            long val = firstNode.getLiteralValue();
            System.out.println("------------------");
            System.out.println("value of outermost packet is " + val);
        }

    }


    private long calculateLiteralValues(Node node) {
        int currentID = (int) node.getPacketID();
        if (currentID == 0) {
            return calculateSum(node);
        } else if (currentID == 1) {
            return calculateProd(node);
        } else if (currentID ==2) {
            return calculateMin(node);
        } else if (currentID == 3) {
            return calculateMax(node);
        } else if (currentID == 4) {
            return node.getLiteralValue();
        } else if (currentID == 5) {
            return calculateGreater(node);
        } else if (currentID == 6) {
            return calculateLess(node);
        } else if (currentID == 7) {
            return calculateEqual(node);
        } else {
            return 0;
        }
    }

    private long calculateSum(Node node) {
        long sum = 0L;
        for (Node p: node.getChilds()) {
            sum += calculateLiteralValues(p);
        }
        node.setLiteralValue(sum);
        return sum;
    }

    private long calculateEqual(Node node) {
        long first = calculateLiteralValues(node.getChilds().get(0));
        long second = calculateLiteralValues(node.getChilds().get(1));
        if (first == second) {
            node.setLiteralValue(1);
            return 1;
        } else {
            node.setLiteralValue(0);
            return 0;
        }
    }

    private long calculateLess(Node node) {
        long first = calculateLiteralValues(node.getChilds().get(0));
        long second = calculateLiteralValues(node.getChilds().get(1));
        if (first < second) {
            node.setLiteralValue(1);
            return 1;
        } else {
            node.setLiteralValue(0);
            return 0;
        }
    }

    private long calculateGreater(Node node) {
        long first = calculateLiteralValues(node.getChilds().get(0));
        long second = calculateLiteralValues(node.getChilds().get(1));
        if (first > second) {
            node.setLiteralValue(1);
            return 1;
        } else {
            node.setLiteralValue(0);
            return 0;
        }
    }

    private long calculateMax(Node node) {
        long max = Long.MIN_VALUE;
        for (Node p : node.getChilds()) {
            long t = calculateLiteralValues(p);
            if (t > max) {
                max = t;
            }
        }
        node.setLiteralValue(max);
        return max;
    }

    private long calculateMin(Node node) {
        long min = Long.MAX_VALUE;
        for (Node p : node.getChilds()) {
            long t = calculateLiteralValues(p);
            if (t < min) {
                min = t;
            }
        }
        node.setLiteralValue(min);
        return min;
    }

    private long calculateProd(Node node) {
        long sum = 1L;
        for (Node p : node.getChilds()) {
            sum *= calculateLiteralValues(p);
        }
        node.setLiteralValue(sum);
        return sum;
    }

    private Node validatePacket() {
        long currVersion = binaryToDecimal((partTwoList.get(counter) + partTwoList.get(counter+1) + partTwoList.get(counter+2)));
        long currID = binaryToDecimal((partTwoList.get(counter+3) + partTwoList.get(counter+4) + partTwoList.get(counter+5)));
        counter += 6;
        Node newNode = new Node(currVersion, currID);
        if (currID == 4) {
            getLiteralValue(newNode);
        } else {
            getOperatorPacket(newNode);
        }
        return newNode;
    }

    private void getOperatorPacket(Node node) {
        long lengthTypeID = Long.parseLong(partTwoList.get(counter));
        String lengthNumberAsString = "";
        counter++;
        //remove length indicators
        if (lengthTypeID == 0) {
            //15 bits
            for (int i = 0; i < 15; i++) {
                lengthNumberAsString += partTwoList.get(counter);
                counter++;
            }
            long lengthNumber = binaryToDecimal(lengthNumberAsString) + counter;
            while (lengthNumber > counter) {
                node.addChild(validatePacket());
            }
        } else if (lengthTypeID == 1) {
            //11 bits
            for (int i = 0; i < 11; i++) {
                lengthNumberAsString += partTwoList.get(counter);
                counter++;
            }
            long lengthNumber = binaryToDecimal(lengthNumberAsString);
            for (int i = 0; i < lengthNumber; i++) {
                node.addChild(validatePacket());
            }
        }
    }

    private void getLiteralValue(Node node) {
        String literalAsString = "";
        String lastTracker = partTwoList.get(counter);
        counter++;
        while (!lastTracker.equals("0")) {
            for (int i = 0; i < 4; i++) {
                literalAsString += partTwoList.get(counter);
                counter++;
            }
            lastTracker = partTwoList.get(counter);
            counter++;
        }
        for (int i = 0; i < 4; i++) {
            literalAsString += partTwoList.get(counter);
            counter++;
        }

        node.setLiteralValue(binaryToDecimal(literalAsString));

    }


    private long getVersionSum(ArrayList<String> list) {
        long sum = 0;
        while (!list.isEmpty() && list.size() > 6) {
            String packetVersionAsString = list.remove(0) + list.remove(0) + list.remove(0);
            String packetIDAsString = list.remove(0) + list.remove(0) + list.remove(0);
            long packetVersion = binaryToDecimal(packetVersionAsString);
            String subPackNumb = "";
            long packetID = binaryToDecimal(packetIDAsString);
            if (packetID == 4) {
                //packet is Literal
                String lastTracker = list.remove(0);
                //while not last group
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
            } else {
                //add operator to list
                long lengthTypeID = Integer.parseInt(list.remove(0));
                //remove length indicators
                if (lengthTypeID == 0 && list.size() >= 15) {
                    //15 bits
                    for (int i = 0; i < 15; i++) {
                        list.remove(0);
                    }
                } else if (lengthTypeID == 1 && list.size() >= 11) {
                    //11 bits
                    for (int i = 0; i < 11; i++) {
                        list.remove(0);
                    }
                }
            }
            sum += packetVersion;

        }
        return sum;
    }



    private long binaryToDecimal(String s) {
        while (s.length() % 4 != 0) {
            s = "0" + s;
        }
        String[] tmp = s.split("");
        long res = 0;
        long c = tmp.length-1;
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
