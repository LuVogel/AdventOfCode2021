package com.main;



import com.main.Day8.Day8;
import com.main.DayEleven.DayEleven;
import com.main.DayFifteen.DayFifteen;

import com.main.DayFourteen.DayFourteen;
import com.main.DaySeventeen.DaySeventeen;
import com.main.DaySixteen.DaySixteen;
import com.main.DayThirteen.DayThirteen;
import com.main.DayTwelve.DayTwelve;
import com.main.day1.Day1;
import com.main.day10.Day10;
import com.main.day2.Day2;
import com.main.day3.Day3;
import com.main.day4.Day4;
import com.main.day5.Day5;
import com.main.day6.Day6;
import com.main.day7.Day7;
import com.main.day9.Day9;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class  Main {

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
            if (day.equals("1")) {
                Day1 day1 = new Day1(puzzleNumber, testCase);
            } else if (day.equals("8")) {
                Day2 day2 = new Day2();
            }else if (day.equals("3")) {
                Day3 day3 = new Day3();
            } else if (day.equals("4")) {
                Day4 day4 = new Day4();
            } else if (day.equals("5")) {
                Day5 day5 = new Day5();
            }else if (day.equals("6")) {
                Day6 day6 = new Day6();
            } else if (day.equals("7")) {
                Day7 day7 = new Day7();
            } else if (day.equals("8")) {
                Day8 day8 = new Day8();
            }else if (day.equals("9")) {
                Day9 day9 = new Day9();
            } else if (day.equals("10")) {
                Day10 day10 = new Day10();
            } else if (day.equals("11")) {
                DayEleven dayEleven = new DayEleven();
            }else if (day.equals("12")) {
                DayTwelve dayTwelve = new DayTwelve();
            }else if (day.equals("13")) {
                DayThirteen dayThirteen = new DayThirteen(puzzleNumber);
            } else if (day.equals("14")) {
                DayFourteen dayFourteen = new DayFourteen(puzzleNumber);
            }else if (day.equals("15")) {
                DayFifteen dayFifteen = new DayFifteen(puzzleNumber);
            } else if (day.equals("16")) {
                DaySixteen daySixteen = new DaySixteen(puzzleNumber, testCase);
            } else if (day.equals("17")) {
                DaySeventeen daySeventeen = new DaySeventeen(puzzleNumber, testCase);
            }
        }


    }
}
