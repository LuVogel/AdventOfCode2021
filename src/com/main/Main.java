package com.main;



import com.main.DayFifteen.DayFifteen;

import com.main.DaySixteen.DaySixteen;
import com.main.DayThirteen.DayThirteen;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class Main {

    static String day;
    static String puzzleNumber;
    static boolean testCase;

    public static void main(String[] args) throws IOException, URISyntaxException {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter puzzle number in format XX,X {DAY,PART} or 'quit' to exit program");
            String userInput = scanner.nextLine();

            if (userInput.equals("quit")) {
                break;
            } else {
                String[] puzzleArray = userInput.split(",");
                if (puzzleArray.length >= 2) {
                    day = puzzleArray[0];
                    puzzleNumber = puzzleArray[1];
                }
                if (puzzleArray.length == 3) {
                    testCase = true;
                } else {
                    testCase = false;
                }
            }
            if (day.equals("13")) {
                DayThirteen dayThirteen = new DayThirteen(puzzleNumber);
            } /*else if (day.equals("14")) {
                DayFourteen dayFourteen = new DayFourteen(puzzleNumber);
            }*/ else if (day.equals("15")) {
                DayFifteen dayFifteen = new DayFifteen(puzzleNumber);
            } else if (day.equals("16")) {
                DaySixteen daySixteen = new DaySixteen(puzzleNumber, testCase);
            }
        }


    }
}
