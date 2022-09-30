package com.main.DayEighteen;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DayEighteen {

    public Node globalExplodingNode;
    public boolean globalFound;
    public boolean globalCheck;
    public boolean globalSplit;
    public boolean globalMagCheck;

    private class Node {
        Node left;
        Node right;
        Node parent;
        String val;

        public Node(String val, Node parent) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }
    }



    String puzzleNumber;
    boolean testCase;

    public DayEighteen(String puzzleNumber, boolean testCase) {
        this.puzzleNumber = puzzleNumber;
        this.testCase = testCase;
        ArrayList<Node> nodeList = readInput();
        if (puzzleNumber.equals("1")) {
            Puzzle1(nodeList);
        } else if (puzzleNumber.equals("2")) {
            Puzzle2(nodeList);
        }
    }

    private class TestObject {
        public Node testNode;
        public int testI;
        public int testJ;
        public String fish1;
        public String fish2;

        public TestObject(Node testNode, int testI, int testJ, String fish1, String fish2) {
            this.testNode =  testNode;
            this.testI = testI;
            this.testJ = testJ;
            this.fish1 = fish1;
            this.fish2 = fish2;
        }
    }

    public Node CopyTree(Node node, Node currentParent) {
        if (node == null) {
            return null;
        }
        Node n = new Node(node.val, currentParent);
        n.left = CopyTree(node.left, n);
        n.right = CopyTree(node.right, n);
        return n;
    }

    private void Puzzle2(ArrayList<Node> list) {
        ArrayList<Integer> sumList = new ArrayList<>();
        ArrayList<Integer> nodes = new ArrayList<>();
        ArrayList<Node> nodeList;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    nodeList = new ArrayList<>(list);
                    Node n1 = CopyTree(nodeList.get(i), null);
                    Node n2 = CopyTree(nodeList.get(j), null);
                    Node sumNode = addTree(n1, n2);
                    while (explodeNotMet(sumNode) || splitNotMet(sumNode)) {
                        if (explodeNotMet(sumNode)) {
                            explode(sumNode);
                        } else if (splitNotMet(sumNode)) {
                            split(sumNode);
                        }
                    }
                    sumList.add(calculateMagnitude(sumNode));
                }
            }
        }
        int maxMagnitude = Collections.max(sumList);
        System.out.println("\n---------------------------------\n" +
                "the largest magnitude of any sum of two different numbers is " + maxMagnitude
                + "\n---------------------------------");

    }

    private void Puzzle1(ArrayList<Node> nodeList) {
        Node current = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            Node toAdd = nodeList.get(i);
            current = addTree(current, toAdd);
            while (explodeNotMet(current) || splitNotMet(current)) {
                if (explodeNotMet(current)) {
                    explode(current);
                } else if (splitNotMet(current)) {
                    split(current);
                }
            }
        }
        int magnitude = calculateMagnitude(current);
        System.out.println("\n---------------------------------\n" +
                "the magnitude of the final sum is " + magnitude + "\n---------------------------------");
    }

    private ArrayList<Node> readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            if (testCase) {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_18_test.txt");
            } else {
                file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_18.txt");
            }
        } else if (os.equals("Windows 10")) {
            if (testCase) {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_18_test.txt");
            } else {
                file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_18.txt");
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
        ArrayList<Node> nodeList = new ArrayList<>();
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Node tmp = getPairFromLine(line);
            nodeList.add(tmp);
        }
        return nodeList;
    }

    private int calculateMagnitude(Node node) {
        int res = 0;
        int height = heightOfTree(node);
        while (height != -1 && height != 0) {
            globalMagCheck = false;
            calculateRecMagnitude(node, 0, height);
            height = heightOfTree(node);
        }
        return Integer.parseInt(node.val);
    }

    private void calculateRecMagnitude(Node node, int level, int height) {
        if (node == null) {
            return;
        }
        calculateRecMagnitude(node.left, level+1, height);
        if (node.val.equals("root") && height == level+1 && !globalMagCheck) {
           int first = 3 * Integer.parseInt(node.left.val);
           int second = 2 * Integer.parseInt(node.right.val);
           int sum = first + second;
           node.val = String.valueOf(sum);
           node.left = null;
           node.right = null;
           globalMagCheck = true;
        }
        calculateRecMagnitude(node.right, level+1, height);

    }

    private void explode(Node current) {
        globalFound = false;
        explodeRecursive(current, 0);
        Node toExplode = globalExplodingNode;
        int leftDigit;
        int rightDigit;
        ArrayList<Node> orderedNodeList = getOrderedList(current);
        int indexLeftVal = orderedNodeList.indexOf(toExplode.left);
        int indexRightVal = orderedNodeList.indexOf(toExplode.right);
        if (indexLeftVal == 0) {
            //node has no left digit
            Node tmp = orderedNodeList.get(indexRightVal+1);
            rightDigit = Integer.parseInt(tmp.val);
            rightDigit += Integer.parseInt(toExplode.right.val);
            tmp.val = String.valueOf(rightDigit);

        } else if (indexRightVal == orderedNodeList.size()-1) {
            // node has no right digit
            Node tmp = orderedNodeList.get(indexLeftVal-1);
            leftDigit = Integer.parseInt(tmp.val);
            leftDigit += Integer.parseInt(toExplode.left.val);
            tmp.val = String.valueOf(leftDigit);
        } else {
            // node has left and right digit
            Node tmpL = orderedNodeList.get(indexLeftVal-1);
            Node tmpR = orderedNodeList.get(indexRightVal+1);
            leftDigit = Integer.parseInt(tmpL.val);
            rightDigit = Integer.parseInt(tmpR.val);
            leftDigit += Integer.parseInt(toExplode.left.val);
            rightDigit += Integer.parseInt(toExplode.right.val);
            tmpL.val = String.valueOf(leftDigit);
            tmpR.val = String.valueOf(rightDigit);
        }

        toExplode.val = "0";
        toExplode.left = null;
        toExplode.right = null;
    }

    private ArrayList<Node> getOrderedList(Node node) {
        ArrayList<Node> list = new ArrayList<>();
        getInorderList(node, list);
        return list;
    }

    private void getInorderList(Node node, ArrayList<Node> list) {
        if (node == null) {
            return;
        }
        getInorderList(node.left, list);
        if (!node.val.equals("root")) {
            list.add(node);
        }
        getInorderList(node.right, list);
    }

    private void explodeRecursive(Node node, int height) {
        if (node == null) {
            return;
        }
        if (height == 4) {
            if (node.val.equals("root") && !globalFound) {
                globalFound = true;
                globalExplodingNode = node;
                return;

            }
        }
        explodeRecursive(node.left, height+1);
        explodeRecursive(node.right, height+1);
    }

    private void split(Node current) {
        globalSplit = false;
        splitRecursive(current);
    }

    private void splitRecursive(Node node) {
        if (node == null) {
            return;
        }
        if (!node.val.equals("root")) {
            int i = Integer.parseInt(node.val);
            if (i >= 10 && !globalSplit) {
                int roundDown = i / 2;
                int roundUp = i / 2;
                if (roundUp * 2 < i) {
                    roundUp++;
                }
                node.val = "root";
                Node nodeDown = new Node(String.valueOf(roundDown), node);
                Node nodeUp = new Node(String.valueOf(roundUp), node);
                node.left = nodeDown;
                node.right = nodeUp;
                globalSplit = true;
                return;

            }
        }
        splitRecursive(node.left);
        splitRecursive(node.right);
    }

    private boolean splitNotMet(Node current) {
        globalCheck = false;
        getSplitBool(current);
        return globalCheck;
    }

    private void getSplitBool(Node node) {
        if (node == null) {
            return;
        }
        if (!node.val.equals("root")) {
            int i = Integer.parseInt(node.val);
            if (i >= 10) {
               globalCheck = true;
            }
        }
        getSplitBool(node.left);
        getSplitBool(node.right);
    }

    private boolean explodeNotMet(Node current) {
        if (heightOfTree(current) >= 5) {
            return true;
        }
        return false;
    }

    private int heightOfTree(Node root) {
        if (root == null) {
            return -1;
        }
        int heightLeftSubtree = heightOfTree(root.left);
        int heightRightSubtree = heightOfTree(root.right);
        return Math.max(heightLeftSubtree, heightRightSubtree) + 1;
    }




    private Node addTree(Node current, Node toAdd) {
        Node node = new Node("root", null);
        current.parent = node;
        toAdd.parent = node;
        node.left = current;
        node.right = toAdd;
        return node;
    }

    private Node getPairFromLine(String line) {
        String[] split = line.split("");
        Node node = new Node("root", null);
        for (int i = 1; i < split.length; i++) {
            String current = split[i];
            if (current.equals("]")) {
                node = node.parent;
            } else if (!current.equals("[")) {
                if (current.equals(",")) {
                    node = node.parent;
                    if (split[i+1].equals("[")) {
                        node.right = new Node("root", node);
                        node = node.right;
                        i++;
                    }
                } else {
                    if (node.left == null) {
                        node.left = new Node(current, node);
                        node = node.left;
                    } else {
                        node.right = new Node(current, node);
                        node = node.right;

                    }
                }
            } else {
                    node.left = new Node("root", node);
                    node = node.left;
                }
            }
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }
}
