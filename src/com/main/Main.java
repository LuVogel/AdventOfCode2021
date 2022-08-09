package com.main;



import com.main.DayFourteen.DayFourteen;
import com.main.DayThirteen.DayThirteen;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter puzzle number (DAY,PUZZLE) \n (eg. 10,1 for puzzle 1, day10 or 13,2 for puzzle 2, day 13: ");
        String userInput = scanner.nextLine();
        String[] puzzleArray = userInput.split(",");
        String day = puzzleArray[0];
        String puzzleNumber = puzzleArray[1];

        if (day.equals("13")) {
            DayThirteen dayThirteen = new DayThirteen(puzzleNumber);
        } else if (day.equals("14")) {
            DayFourteen dayFourteen = new DayFourteen(puzzleNumber);
        }
    }
}
