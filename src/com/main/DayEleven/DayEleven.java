package com.main.DayEleven;

import java.io.*;
import java.util.ArrayList;


public class DayEleven {



    private int[][] readInput() {
        String os = System.getProperty("os.name");
        File file = null;
        if (os.equals("Mac OS X")) {
            file = new File("/Users/lukasvogel/git/adventOfCode/AdventOfCode2021/input_files/input_day_11_test.txt");
        } else if (os.equals("Windows 10")) {
            file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_11.txt");
        } else {
            System.out.println("OS not detected");
            System.exit((-1));
        }


        String map = "";

        int lineLength = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                map += line + ",";
                lineLength = line.length();
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        String[] sArray = map.split(",");
        int[][] array = new int[sArray.length][lineLength];
        for (int i = 0; i < array.length; i++) {
            String[] tmp = sArray[i].split("");
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        return array;
    }

    private class FlashedObject {

        int i, j;

        public FlashedObject(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void Puzzle1() {
        int flashCounter = 0;
        int[][] unflashed = readInput();
        int printSteps = 0;
        int steps = 0;
        while (steps < 100) {
            System.out.println("-------------------------");
            for (int i = 0; i < unflashed.length; i++) {
                for (int j = 0; j < unflashed[0].length; j++) {
                    System.out.print(unflashed[i][j]);
                    unflashed[i][j] += 1;
                }
                System.out.println();
            }
            System.out.println("i-length: " + unflashed.length + ", j-length: " + unflashed[0].length);
            ArrayList<FlashedObject> flashedList = new ArrayList<>();
            boolean checkForFlashed = true;
            while (checkForFlashed) {
                for (int i = 0; i < unflashed.length; i++) {
                    for (int j = 0; j < unflashed[0].length; j++) {
                        if (unflashed[i][j] > 9) {
                            boolean testIfAlreadyFlashed = true;
                           for (FlashedObject tmp : flashedList) {
                               if (tmp.i == i && tmp.j == j) {
                                   testIfAlreadyFlashed = false;
                               }
                           }
                           if (testIfAlreadyFlashed) {
                               unflashed[i][j] = Integer.MIN_VALUE;
                               flashedList.add(new FlashedObject(i, j));
                               if (i == 0 && j == 0) {
                                   // flash topLeft Corner
                                   unflashed[i][j+1] += 1;
                                   unflashed[i+1][j] += 1;
                                   unflashed[i+1][j+1] += 1;
                               } else if (i == 0 && j < unflashed[0].length-1) {
                                   // flash top Border
                                   unflashed[i][j-1] += 1;
                                   unflashed[i][j+1] += 1;
                                   unflashed[i+1][j-1] += 1;
                                   unflashed[i+1][j] += 1;
                                   unflashed[i+1][j+1] += 1;
                               } else if (i == 0 && j == unflashed[0].length-1) {
                                   // flash topRight Corner
                                   unflashed[i][j-1] += 1;
                                   unflashed[i+1][j-1] += 1;
                                   unflashed[i+1][j] += 1;
                               } else if (i == unflashed.length-1 && j == 0) {
                                   // flash bottomLeftCorner
                                   unflashed[i-1][j] += 1;
                                   unflashed[i-1][j+1] += 1;
                                   unflashed[i][j+1] += 1;
                               } else if (i == unflashed.length-1 && j < unflashed[0].length-1) {
                                   // flash bottom Border
                                   unflashed[i-1][j-1] += 1;
                                   unflashed[i-1][j] += 1;
                                   unflashed[i-1][j+1] += 1;
                                   unflashed[i][j-1] += 1;
                                   unflashed[i][j+1] += 1;
                               } else if (i == unflashed.length-1 && j == unflashed[0].length-1) {
                                   // flash bottomRight Corner
                                   unflashed[i-1][j-1] += 1;
                                   unflashed[i-1][j] += 1;
                                   unflashed[i][j-1] += 1;
                               } else if (i < unflashed.length-1 && j == 0) {
                                   // flash LeftBorder
                                   unflashed[i-1][j] += 1;
                                   unflashed[i-1][j+1] += 1;
                                   unflashed[i][j+1] += 1;
                                   unflashed[i+1][j] += 1;
                                   unflashed[i+1][j+1] += 1;
                               } else if (i < unflashed.length-1 && j == unflashed[0].length-1) {
                                   // flash rightBorder
                                   unflashed[i-1][j-1] += 1;
                                   unflashed[i-1][j] += 1;
                                   unflashed[i][j-1] += 1;
                                   unflashed[i+1][j-1] += 1;
                                   unflashed[i+1][j] += 1;
                               } else if (i > 0 && i < unflashed.length-1 && j > 0 && j < unflashed[0].length-1) {
                                   // flash all midpoints
                                   unflashed[i-1][j-1] += 1;
                                   unflashed[i-1][j] += 1;
                                   unflashed[i-1][j+1] += 1;
                                   unflashed[i][j-1] += 1;
                                   unflashed[i][j+1] += 1;
                                   unflashed[i+1][j-1] += 1;
                                   unflashed[i+1][j] += 1;
                                   unflashed[i+1][j+1] += 1;
                               }
                           }
                        }
                    }
                }
                checkForFlashed = false;
                for (int i = 0; i < unflashed.length; i++) {
                    for (int j = 0; j < unflashed[0].length; j++) {
                        if (unflashed[i][j] > 9) {
                            checkForFlashed = true;
                        }
                    }
                }

            }
            for (FlashedObject obj : flashedList) {
                unflashed[obj.i][obj.j] = 0;
                flashCounter++;
            }
            steps++;

        }
        System.out.println("After step " + steps + ", there have been a total of " + flashCounter + " flashes.");
    }

    public void Puzzle2() {
        int flashCounter = 0;
        int[][] unflashed = readInput();
        int steps = 0;
        boolean checkForSimu = true;
        while (checkForSimu) {
            System.out.println("-------------------------");
            for (int i = 0; i < unflashed.length; i++) {
                for (int j = 0; j < unflashed[0].length; j++) {
                    System.out.print(unflashed[i][j]);
                    unflashed[i][j] += 1;
                }
                System.out.println();
            }
            System.out.println("i-length: " + unflashed.length + ", j-length: " + unflashed[0].length);
            ArrayList<FlashedObject> flashedList = new ArrayList<>();
            boolean checkForFlashed = true;
            while (checkForFlashed) {
                for (int i = 0; i < unflashed.length; i++) {
                    for (int j = 0; j < unflashed[0].length; j++) {
                        if (unflashed[i][j] > 9) {
                            boolean testIfAlreadyFlashed = true;
                            for (FlashedObject tmp : flashedList) {
                                if (tmp.i == i && tmp.j == j) {
                                    testIfAlreadyFlashed = false;
                                }
                            }
                            if (testIfAlreadyFlashed) {
                                unflashed[i][j] = Integer.MIN_VALUE;
                                flashedList.add(new FlashedObject(i, j));
                                if (i == 0 && j == 0) {
                                    // flash topLeft Corner
                                    unflashed[i][j+1] += 1;
                                    unflashed[i+1][j] += 1;
                                    unflashed[i+1][j+1] += 1;
                                } else if (i == 0 && j < unflashed[0].length-1) {
                                    // flash top Border
                                    unflashed[i][j-1] += 1;
                                    unflashed[i][j+1] += 1;
                                    unflashed[i+1][j-1] += 1;
                                    unflashed[i+1][j] += 1;
                                    unflashed[i+1][j+1] += 1;
                                } else if (i == 0 && j == unflashed[0].length-1) {
                                    // flash topRight Corner
                                    unflashed[i][j-1] += 1;
                                    unflashed[i+1][j-1] += 1;
                                    unflashed[i+1][j] += 1;
                                } else if (i == unflashed.length-1 && j == 0) {
                                    // flash bottomLeftCorner
                                    unflashed[i-1][j] += 1;
                                    unflashed[i-1][j+1] += 1;
                                    unflashed[i][j+1] += 1;
                                } else if (i == unflashed.length-1 && j < unflashed[0].length-1) {
                                    // flash bottom Border
                                    unflashed[i-1][j-1] += 1;
                                    unflashed[i-1][j] += 1;
                                    unflashed[i-1][j+1] += 1;
                                    unflashed[i][j-1] += 1;
                                    unflashed[i][j+1] += 1;
                                } else if (i == unflashed.length-1 && j == unflashed[0].length-1) {
                                    // flash bottomRight Corner
                                    unflashed[i-1][j-1] += 1;
                                    unflashed[i-1][j] += 1;
                                    unflashed[i][j-1] += 1;
                                } else if (i < unflashed.length-1 && j == 0) {
                                    // flash LeftBorder
                                    unflashed[i-1][j] += 1;
                                    unflashed[i-1][j+1] += 1;
                                    unflashed[i][j+1] += 1;
                                    unflashed[i+1][j] += 1;
                                    unflashed[i+1][j+1] += 1;
                                } else if (i < unflashed.length-1 && j == unflashed[0].length-1) {
                                    // flash rightBorder
                                    unflashed[i-1][j-1] += 1;
                                    unflashed[i-1][j] += 1;
                                    unflashed[i][j-1] += 1;
                                    unflashed[i+1][j-1] += 1;
                                    unflashed[i+1][j] += 1;
                                } else if (i > 0 && i < unflashed.length-1 && j > 0 && j < unflashed[0].length-1) {
                                    // flash all midpoints
                                    unflashed[i-1][j-1] += 1;
                                    unflashed[i-1][j] += 1;
                                    unflashed[i-1][j+1] += 1;
                                    unflashed[i][j-1] += 1;
                                    unflashed[i][j+1] += 1;
                                    unflashed[i+1][j-1] += 1;
                                    unflashed[i+1][j] += 1;
                                    unflashed[i+1][j+1] += 1;
                                }
                            }
                        }
                    }
                }
                checkForFlashed = false;
                for (int i = 0; i < unflashed.length; i++) {
                    for (int j = 0; j < unflashed[0].length; j++) {
                        if (unflashed[i][j] > 9) {
                            checkForFlashed = true;
                        }
                    }
                }

            }
            for (FlashedObject obj : flashedList) {
                unflashed[obj.i][obj.j] = 0;
                flashCounter++;
            }
            steps++;
            if (flashedList.size() == unflashed.length * unflashed[0].length) {
                checkForSimu = false;
            }

        }
        System.out.println("After step " + steps + ", all octopuses will flash simultaneously");
    }
}
