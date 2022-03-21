package com.main.day4;

import java.util.Arrays;

public class BingoBoard {


    BingoNumber[] board;
    boolean hasWon;

    public BingoBoard() {
        this.board = new BingoNumber[25];
        Arrays.fill(board, null);
        this.hasWon = false;

    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void addNumberToBoard(BingoNumber number, int position) {
        board[position] = number;
    }

    /**
     *
     * @return true if board is full
     */
    public boolean checkBoardIsFilled() {
        boolean res = true;
        for (BingoNumber number: board) {
            if (number == null) {
                res = false;
            }
        }
        return res;
    }

    public int getPositionOfNumber(BingoNumber number) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkBoardForWin(BingoNumber lastMarkedNumber) {
        boolean res = false;
        int positionInBoard = -1;
        for (BingoNumber number: board) {
            if (number == lastMarkedNumber) {
                positionInBoard = getPositionOfNumber(number);
            }
        }
        if (positionInBoard >= 0 && positionInBoard <= 4) {
            if (board[0].isMarked() && board[1].isMarked() && board[2].isMarked() && board[3].isMarked() &&
                    board[4].isMarked()) {
                res = true;
            }
        } else if (positionInBoard >= 5 && positionInBoard <= 9) {
            if (board[5].isMarked() && board[6].isMarked() && board[7].isMarked() && board[8].isMarked() &&
                    board[9].isMarked()) {
                res = true;
            }
        } else if (positionInBoard >= 10 && positionInBoard <= 14) {
            if (board[10].isMarked() && board[11].isMarked() && board[12].isMarked() && board[13].isMarked() &&
                    board[14].isMarked()) {
                res = true;
            }
        } else if (positionInBoard >= 15 && positionInBoard <= 19) {
            if (board[15].isMarked() && board[16].isMarked() && board[17].isMarked() && board[18].isMarked() &&
                    board[19].isMarked()) {
                res = true;
            }
        } else if (positionInBoard >= 20 && positionInBoard <= 24) {
            if (board[20].isMarked() && board[21].isMarked() && board[22].isMarked() && board[23].isMarked() &&
                    board[24].isMarked()) {
                res = true;
            }
        }
        if (positionInBoard == 0 || positionInBoard == 3 || positionInBoard == 10 ||
                positionInBoard == 15 || positionInBoard == 20) {
            if (board[0].isMarked() && board[3].isMarked() && board[10].isMarked() && board[15].isMarked() &&
                    board[20].isMarked()) {
                res = true;
            }
        } else if (positionInBoard == 1 || positionInBoard == 6 || positionInBoard == 11 ||
                positionInBoard == 16 || positionInBoard == 21) {
            if (board[1].isMarked() && board[6].isMarked() && board[11].isMarked() && board[16].isMarked() &&
                    board[21].isMarked()) {
                res = true;
            }
        } else if (positionInBoard == 2 || positionInBoard == 7 || positionInBoard == 12 ||
                positionInBoard == 17 || positionInBoard == 22) {
            if (board[2].isMarked() && board[7].isMarked() && board[12].isMarked() && board[17].isMarked() &&
                    board[22].isMarked()) {
                res = true;
            }
        } else if (positionInBoard == 3 || positionInBoard == 8 || positionInBoard == 13 ||
                positionInBoard == 18 || positionInBoard == 23) {
            if (board[3].isMarked() && board[8].isMarked() && board[13].isMarked() && board[18].isMarked() &&
                    board[23].isMarked()) {
                res = true;
            }
        } else if (positionInBoard == 4 || positionInBoard == 9 || positionInBoard == 14 ||
                positionInBoard == 19 || positionInBoard == 24) {
            if (board[4].isMarked() && board[9].isMarked() && board[14].isMarked() && board[19].isMarked() &&
                    board[24].isMarked()) {
                res = true;
            }
        }
        return res;
    }

    public void clearCurrentBoard() {
        for (BingoNumber number: board) {
            number = null;
        }
    }

    public BingoNumber[] getBoard() {
        return board;
    }

    public BingoBoard getCopy() {
        int i = 0;
        BingoBoard temp = new BingoBoard();
        for (BingoNumber number: board) {
            temp.addNumberToBoard(number, i);
            i++;
        }
        return temp;
    }
}
