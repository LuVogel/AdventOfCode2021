package com.main.day5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Day5 {


    /**
     * right answer 5.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_5.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<ValueChange> valueChangeArrayList = new ArrayList<>();


        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] currentLine = line.trim().split(",");
            int x_1 = Integer.parseInt(currentLine[0]);
            String[] middleString = currentLine[1].trim().split("\\s+");
            int y_1 = Integer.parseInt(middleString[0]);
            int x_2 = Integer.parseInt(middleString[2]);
            int y_2 = Integer.parseInt(currentLine[2]);
            ValueChange valueChange = new ValueChange(x_1, y_1, x_2, y_2);
            valueChangeArrayList.add(valueChange);
        }

        //find maximum
        int maximum_x = valueChangeArrayList.get(0).x_2;
        int maximum_y = valueChangeArrayList.get(0).y_2;
        int maximum = 0;
        if (maximum_x > maximum_y) {
            maximum = maximum_x;
        } else {
            maximum = maximum_y;
        }
        for (int i = 1; i < valueChangeArrayList.size(); i++) {
            ValueChange temp = valueChangeArrayList.get(i);
            int temp_x = 0;
            int temp_y = 0;
            if (temp.x_1 >= temp.x_2) {
                temp_x = temp.x_1;
            } else {
                temp_x = temp.x_2;
            }
            if (temp.y_1 >= temp.y_2) {
                temp_y = temp.y_1;
            } else {
                temp_y = temp.y_2;
            }
            if (temp_x >= temp_y) {
                if (maximum < temp_x) {
                    maximum = temp_x;
                }
            } else {
                if (maximum < temp_y) {
                    maximum = temp_y;
                }
            }
        }
        int[][] board = new int[maximum+1][maximum+1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
        for (ValueChange changesInList: valueChangeArrayList) {
            int x_start = changesInList.getX_1();
            int x_end = changesInList.getX_2();
            int y_start = changesInList.getY_1();
            int y_end = changesInList.getY_2();
            if (x_start == x_end) {
                if (y_start < y_end) {
                    for (int i = y_start; i <= y_end; i++) {
                        board[i][x_start] += 1;
                    }
                } else {
                    for (int i = y_end; i <= y_start; i++) {
                        board[i][x_start] += 1;
                    }
                }
            }else if (y_start == y_end) {
                if (x_start < x_end) {
                    for (int i = x_start; i <= x_end; i++) {
                        board[y_start][i] += 1;
                    }
                } else {
                    for (int i = x_end; i <= x_start; i++) {
                        board[y_start][i] += 1;
                    }
                }
            }
        }
        int point_counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 1) {
                    point_counter++;
                }
            }
        }
        System.out.println(point_counter);

    }


    /**
     * right answer 5.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_5.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<ValueChange> valueChangeArrayList = new ArrayList<>();


        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] currentLine = line.trim().split(",");
            int x_1 = Integer.parseInt(currentLine[0]);
            String[] middleString = currentLine[1].trim().split("\\s+");
            int y_1 = Integer.parseInt(middleString[0]);
            int x_2 = Integer.parseInt(middleString[2]);
            int y_2 = Integer.parseInt(currentLine[2]);
            ValueChange valueChange = new ValueChange(x_1, y_1, x_2, y_2);
            valueChangeArrayList.add(valueChange);
        }

        //find maximum
        int maximum_x = valueChangeArrayList.get(0).x_2;
        int maximum_y = valueChangeArrayList.get(0).y_2;
        int maximum = 0;
        if (maximum_x > maximum_y) {
            maximum = maximum_x;
        } else {
            maximum = maximum_y;
        }
        for (int i = 1; i < valueChangeArrayList.size(); i++) {
            ValueChange temp = valueChangeArrayList.get(i);
            int temp_x = 0;
            int temp_y = 0;
            if (temp.x_1 >= temp.x_2) {
                temp_x = temp.x_1;
            } else {
                temp_x = temp.x_2;
            }
            if (temp.y_1 >= temp.y_2) {
                temp_y = temp.y_1;
            } else {
                temp_y = temp.y_2;
            }
            if (temp_x >= temp_y) {
                if (maximum < temp_x) {
                    maximum = temp_x;
                }
            } else {
                if (maximum < temp_y) {
                    maximum = temp_y;
                }
            }
        }

        int[][] board = new int[maximum+1][maximum+1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }

        for (ValueChange changesInList: valueChangeArrayList) {
            int x_start = changesInList.getX_1();
            int x_end = changesInList.getX_2();
            int y_start = changesInList.getY_1();
            int y_end = changesInList.getY_2();
            if (x_start == x_end) {
                if (y_start < y_end) {
                    for (int i = y_start; i <= y_end; i++) {
                        board[i][x_start] += 1;
                    }
                } else {
                    for (int i = y_end; i <= y_start; i++) {
                        board[i][x_start] += 1;
                    }
                }
            } else if (y_start == y_end) {
                if (x_start < x_end) {
                    for (int i = x_start; i <= x_end; i++) {
                        board[y_start][i] += 1;
                    }
                } else {
                    for (int i = x_end; i <= x_start; i++) {
                        board[y_start][i] += 1;
                    }
                }

            } else {
                if (x_start < x_end && y_start > y_end) {
                    while (x_start <= x_end) {
                        board[y_start][x_start] += 1;
                        y_start--;
                        x_start++;
                    }
                } else if (x_start == y_start && x_end == y_end) {
                    if (x_start < x_end) {
                        while (x_start != x_end) {
                            board[x_start][y_start] += 1;
                            x_start++;
                            y_start++;
                        }
                    } else {
                        while (x_start != x_end) {
                            board[x_start][y_start] += 1;
                            x_start--;
                            y_start--;
                        }
                    }

                } else if (x_start > x_end && y_start < y_end ) {
                    while (x_start >= x_end) {
                        board[y_start][x_start] += 1;
                        y_start++;
                        x_start--;
                    }
                } else if (x_start > x_end && y_start > y_end) {
                    while (x_start >= x_end) {
                        board[y_start][x_start] += 1;
                        y_start--;
                        x_start--;
                    }
                } else if (x_start < x_end && y_start < y_end) {
                    while (x_start <= x_end) {
                        board[y_start][x_start] += 1;
                        y_start++;
                        x_start++;
                    }
                }
            }
        }
        int point_counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 1) {
                    point_counter++;
                }
            }

        }
        System.out.println(point_counter);

    }
}
