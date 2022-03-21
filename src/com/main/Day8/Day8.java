package com.main.Day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8 {

    /**
     * right answer 8.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_8.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<Integer> list = new ArrayList<>();
        String line = "";
        int uniqueCounter = 0;
        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split("\\|");
            String[] firstHalf = tempStringArray[0].trim().split("\\s+");
            String[] secHalf = tempStringArray[1].trim().split("\\s+");
            StringBuffer firstHalfSorted  = new StringBuffer();
            for (String first: firstHalf) {
                char[] charArray = first.toCharArray();
                Arrays.sort(charArray);
                for (int i = 0; i < charArray.length; i++) {
                    firstHalfSorted.append(charArray[i]);
                }
                firstHalfSorted.append(" ");
            }
            StringBuffer secHalfSorted = new StringBuffer();
            for (String sec: secHalf) {
                char[] charArray = sec.toCharArray();
                Arrays.sort(charArray);
                for (int i = 0; i < charArray.length; i++) {
                    secHalfSorted.append(charArray[i]);
                }
                secHalfSorted.append(" ");
            }
            boolean f4 = false;
            boolean f2 = false;
            boolean f3 = false;
            boolean f7 = false;
            String[] firstHalfString = firstHalfSorted.toString().split(" ");
            String[] secHalfString = secHalfSorted.toString().split(" ");
            for (String s: secHalfString) {
                if (s.length() == 2) {
                    for (String firstTest: firstHalfString) {
                        if (firstTest.contains(s.substring(0,1)) && firstTest.contains(s.substring(1,2))) {
                            f2 = true;
                        }
                    }
                } else if (s.length() == 3) {
                    for (String firstTest: firstHalfString) {
                        if (firstTest.contains(s.substring(0,1)) && firstTest.contains(s.substring(1,2)) &&
                        firstTest.contains(s.substring(2,3))) {
                            f3 = true;
                        }
                    }
                } else if (s.length() == 4) {
                    for (String firstTest: firstHalfString) {
                        if (firstTest.contains(s.substring(0,1)) && firstTest.contains(s.substring(1,2)) &&
                        firstTest.contains(s.substring(2,3)) && firstTest.contains(s.substring(3,4))) {
                            f4 = true;
                        }
                    }
                } else if (s.length() == 7) {
                    for (String firstTest: firstHalfString) {
                        if (firstTest.contains(s.substring(0,1)) && firstTest.contains(s.substring(1,2)) &&
                                firstTest.contains(s.substring(2,3)) && firstTest.contains(s.substring(3,4)) &&
                                firstTest.contains(s.substring(4,5)) && firstTest.contains(s.substring(5,6)) &&
                                firstTest.contains(s.substring(6,7))) {
                            f7 = true;
                        }
                    }
                }
                if (f2) {
                    uniqueCounter++;
                }
                if (f3) {
                    uniqueCounter++;
                }
                if (f4) {
                    uniqueCounter++;
                }
                if (f7) {
                    uniqueCounter++;
                }
                f2 = false;
                f3 = false;
                f4 = false;
                f7 = false;
            }
        }
        System.out.println(uniqueCounter);
    }

    /**
     * right answer for 8.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_8.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";
        long sum = 0;
        while ((line = reader.readLine()) != null) {
            String[] tempStringArray = line.split("\\|");
            String[] firstHalf = tempStringArray[0].trim().split("\\s+");
            String[] secHalf = tempStringArray[1].trim().split("\\s+");

            String up = "";
            String down = "";
            String mid = "";
            String upLeft = "";
            String upRight = "";
            String downLeft = "";
            String downRight = "";
            // length: 2 = 1,
            // length: 3 = 7
            // length: 4 = 4
            // length: 5 = 2,3,5
            // length: 6 = 0,6,9
            // length: 7 = 8

            String otherAlternative = "";
            for (String s: firstHalf) {
                if (s.length() == 2) {
                    upRight = s.substring(0,1);
                    otherAlternative = s.substring(1,2);
                    break;
                }
            }
            boolean changeUpRight = false;
            int fiveCounter = 0;
            int sixCounter = 0;
            for (String s: firstHalf) {
                if (s.length() == 3) {
                    if (!s.contains(upRight)) {
                        changeUpRight = true;
                    }
                } else if (s.length() == 4) {
                    if (!s.contains(upRight)) {
                        changeUpRight = true;
                    }
                } else if (s.length() == 5) {
                    if (s.contains(upRight)) {
                        fiveCounter++;
                    }
                } else if (s.length() == 6) {
                    if (s.contains(upRight)) {
                        sixCounter++;
                    }
                }
            }
            if (fiveCounter != 2 || sixCounter != 2) {
                changeUpRight = true;
            }
            if (changeUpRight) {
                downRight = upRight;
                upRight = otherAlternative;
            } else {
                downRight = otherAlternative;
            }
            // upRight and downRight are known

            for (String s: firstHalf) {
                if (s.length() == 3) {
                    String temp = s;
                    temp = temp.replace(upRight, "");
                    temp = temp.replace(downRight, "");
                    up = temp.substring(0,1);
                    break;
                }
            }
            // up, upRight and downRight are known

            for (String s: firstHalf) {
                if (s.length() == 4) {
                    String temp = s;
                    temp = temp.replace(upRight, "");
                    temp = temp.replace(downRight, "");
                    upLeft = temp.substring(0,1);
                    otherAlternative = temp.substring(1,2);
                    break;
                }
            }
            boolean changeUpLeft = false;
            fiveCounter = 0;
            for (String s: firstHalf) {
                if (s.length() == 2) {
                    if (s.contains(upLeft)) {
                        changeUpLeft = true;
                    }
                } else if (s.length() == 3) {
                    if (s.contains(upLeft)) {
                        changeUpLeft = true;
                    }
                } else if (s.length() == 5) {
                    if (s.contains(upLeft)) {
                        fiveCounter++;
                    }
                } else if (s.length() == 6) {
                    if (!s.contains(upLeft)) {
                        changeUpLeft = true;
                    }
                }
            }
            if (fiveCounter != 1) {
                changeUpLeft = true;
            }
            if (changeUpLeft) {
                mid = upLeft;
                upLeft = otherAlternative;
            } else {
                mid = otherAlternative;
            }
            // mid, upLeft, upRight, downRight and up are known

            otherAlternative = "";
            for (String s: firstHalf) {
                if (s.length() == 5) {
                    String temp = s;
                    temp = temp.replace(up, "");
                    temp = temp.replace(mid, "");
                    temp = temp.replace(upLeft, "");
                    temp = temp.replace(downRight, "");
                    temp = temp.replace(upRight, "");
                    if (temp.length() == 1) {
                        down = temp.substring(0,1);
                        break;
                    } else {
                        down = temp.substring(0,1);
                        otherAlternative = temp.substring(1,2);
                        break;
                    }
                }
            }
            boolean changeDown = false;
            if (!otherAlternative.isEmpty()) {
                for (String s: firstHalf) {
                    if (s.length() == 2) {
                        if (s.contains(down)) {
                            changeDown = true;
                        }
                    } else if (s.length() == 3) {
                        if (s.contains(down)) {
                            changeDown = true;
                        }
                    } else if (s.length() == 4) {
                        if (s.contains(down)) {
                            changeDown = true;
                        }
                    } else if (s.length() == 5) {
                        if (!s.contains(down)) {
                            changeDown = true;
                        }
                    } else if (s.length() == 6) {
                        if (!s.contains(down)) {
                            changeDown = true;
                        }
                    }
                }
            }
            if (changeDown) {
                downLeft = down;
                down = otherAlternative;
            }
            if (downLeft.isEmpty()) {
                for (String s: firstHalf) {
                    if (s.length() == 7) {
                        String temp = s;
                        temp = temp.replace(up, "");
                        temp = temp.replace(upLeft, "");
                        temp = temp.replace(upRight, "");
                        temp = temp.replace(mid, "");
                        temp = temp.replace(downRight, "");
                        temp = temp.replace(down, "");
                        downLeft = temp.substring(0,1);
                        break;
                    }
                }
            }

            //all values are known



            String currentNumber = "";
            // length: 2 = 1,
            // length: 3 = 7
            // length: 4 = 4
            // length: 5 = 2,3,5
            // length: 6 = 0,6,9
            // length: 7 = 8
            for (String s: secHalf) {
                if (s.length() == 2) {
                    currentNumber += "1";
                } else if (s.length() == 3) {
                    currentNumber += "7";
                } else if (s.length() == 4) {
                    currentNumber += "4";
                } else if (s.length() == 7) {
                    currentNumber += "8";
                } else if (s.length() == 5) {
                    if (s.contains(up)) {
                        if (s.contains(upLeft)) {
                            currentNumber += "5";
                        } else if (s.contains(upRight)) {
                            if (s.contains(downLeft)) {
                                currentNumber += "2";
                            } else if (s.contains(downRight)) {
                                currentNumber += "3";
                            }
                        }
                    }
                } else if (s.length() == 6) {
                    if (s.contains(mid)) {
                        if (s.contains(upRight)) {
                            currentNumber += "9";
                        } else if (s.contains(downLeft)) {
                            currentNumber += "6";
                        }
                    } else {
                        currentNumber += "0";
                    }
                }
            }
            sum += Long.parseLong(currentNumber);
        }
        System.out.println(sum);
    }
}
