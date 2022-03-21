package com.main.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {


    /**
     * right answer for 4.12.21 puzzle 1
     * @return
     * @throws IOException
     */
    public static int getPuzzle1() throws IOException {

        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_4_giant_squid.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // contains all inputs for the bingo
        ArrayList<String[]> arrayList = new ArrayList<>();
        ArrayList<BingoBoard> boardList = new ArrayList<>();
        String line = "";
        int firstLineCounter = 0;
        int boardLineCounter = 0;
        BingoBoard currentBoard = new BingoBoard();
        while ((line = reader.readLine()) != null) {
            if (line.length() > 0) {
                if (firstLineCounter == 0) {
                    String[] temp = line.split(",");
                    arrayList.add(temp);

                } else {
                    String[] currentLine = line.trim().split("\\s+");
                    /**
                    if (currentLine.length > 4) {
                        for (int i = 0; i < 5; i++) {
                            if (currentLine[i].equals("")) {
                                currentLine[i] = currentLine[i+1];
                                if (i < 1) {
                                    currentLine[i+1] = currentLine[i+2];
                                    currentLine[i+2] = currentLine[i+3];
                                    currentLine[i+3] = currentLine[i+4];
                                    currentLine[i+4] = currentLine[i+5];
                                } else if (i < 2) {
                                    currentLine[i+1] = currentLine[i+2];
                                    currentLine[i+2] = currentLine[i+3];
                                    currentLine[i+3] = currentLine[i+4];
                                } else if (i < 3) {
                                    currentLine[i+1] = currentLine[i+2];
                                    currentLine[i+2] = currentLine[i+3];
                                } else if (i < 4) {
                                    currentLine[i+1] = currentLine[i+2];
                                }
                            }
                        }
                    }
                     */
                    for (int i = 0; i < 5; i++) {
                        BingoNumber numberToAdd = new BingoNumber(Integer.parseInt(currentLine[i]));
                        currentBoard.addNumberToBoard(numberToAdd, (i+boardLineCounter));
                    }
                }
                if (boardLineCounter == 20) {
                    BingoBoard tempBoard = new BingoBoard();
                    tempBoard = currentBoard.getCopy();
                    boardList.add(tempBoard);
                    currentBoard.clearCurrentBoard();
                    boardLineCounter = 0;
                } else if (firstLineCounter == 1){
                    boardLineCounter += 5;
                }
                firstLineCounter = 1;
            }

        }

        String[] numbersToMark = arrayList.get(0);
        int sumUnmarked = 0;
        int numbersToWin = 0;
        for (String mark: numbersToMark) {
            numbersToWin++;
            for (BingoBoard boards: boardList) {
                for (BingoNumber numbers: boards.getBoard()) {
                    if (numbers.getNumber() == Integer.parseInt(mark)) {
                        numbers.setMarked(true);
                        if (boards.checkBoardForWin(numbers)) {
                            System.out.println("board has win");
                            for (BingoNumber number: boards.getBoard()) {
                                if (number.isMarked() == false) {
                                    sumUnmarked += number.getNumber();
                                }
                            }
                            return (sumUnmarked * numbers.getNumber());
                        }
                    }
                }
            }
        }
        return 0;

    }


    /**
     * right answer for 4.12.21 puzzle 2
     * @return
     * @throws IOException
     */
    public static int getPuzzle2() throws IOException {

        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_4_giant_squid.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // contains all inputs for the bingo
        ArrayList<String[]> arrayList = new ArrayList<>();
        ArrayList<BingoBoard> boardList = new ArrayList<>();
        String line = "";
        int firstLineCounter = 0;
        int boardLineCounter = 0;
        BingoBoard currentBoard = new BingoBoard();
        while ((line = reader.readLine()) != null) {
            if (line.length() > 0) {
                if (firstLineCounter == 0) {
                    String[] temp = line.split(",");
                    arrayList.add(temp);

                } else {
                    String[] currentLine = line.trim().split("\\s+");
                    for (int i = 0; i < 5; i++) {
                        BingoNumber numberToAdd = new BingoNumber(Integer.parseInt(currentLine[i]));
                        currentBoard.addNumberToBoard(numberToAdd, (i+boardLineCounter));
                    }
                }
                if (boardLineCounter == 20) {
                    BingoBoard tempBoard = new BingoBoard();
                    tempBoard = currentBoard.getCopy();
                    boardList.add(tempBoard);
                    currentBoard.clearCurrentBoard();
                    boardLineCounter = 0;
                } else if (firstLineCounter == 1){
                    boardLineCounter += 5;
                }
                firstLineCounter = 1;
            }

        }

        String[] numbersToMark = arrayList.get(0);

        ArrayList<BingoBoard> tempList = new ArrayList<>();
        int sumUnmarked = 0;
        int numbersToWin = 0;
        boolean alreadyWon = true;
        BingoNumber lastNumber = new BingoNumber(0);
        for (String mark: numbersToMark) {
            numbersToWin++;
            for (BingoBoard boards: boardList) {
                if (!boards.hasWon) {
                    for (BingoNumber numbers: boards.getBoard()) {
                        if (numbers.getNumber() == Integer.parseInt(mark)) {
                            numbers.setMarked(true);
                            if (boards.checkBoardForWin(numbers)) {
                                lastNumber = new BingoNumber(numbers.getNumber());
                                if (!boards.hasWon) {
                                    boards.setHasWon(true);
                                    tempList.add(boards);
                                }
                            }

                        }
                    }
                }

            }
        }
        System.out.println("last board has won");
        BingoBoard lastBoard = tempList.get(tempList.size()-1);
        for (BingoNumber number : lastBoard.getBoard()) {
            if (number.isMarked() == false) {
                sumUnmarked += number.getNumber();
            }
        }
        return (sumUnmarked * lastNumber.getNumber());

    }
}
