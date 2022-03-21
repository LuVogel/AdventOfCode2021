package com.main.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {





    /**
     * right answer 3.12.21 puzzle 1
     * @throws IOException
     */
    public static void getPuzzle1() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_3_binary_diagnostic.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        ArrayList<String[]> arrayList = new ArrayList<>();


        while ((s = reader.readLine()) != null) {
            String[] splitString = s.split("");
            arrayList.add(splitString);
        }
        int counterZeroFirstRow = 0;
        int counterOneFirstRow = 0;
        int counterZeroSecRow = 0;
        int counterOneSecRow = 0;
        int counterZero3Row = 0;
        int counterOne3Row = 0;
        int counterZero4Row = 0;
        int counterOne4Row = 0;
        int counterZero5Row = 0;
        int counterOne5Row = 0;
        int counterZero6Row = 0;
        int counterOne6Row = 0;
        int counterZero7Row = 0;
        int counterOne7Row = 0;
        int counterZero8Row = 0;
        int counterOne8Row = 0;
        int counterZero9Row = 0;
        int counterOne9Row = 0;
        int counterZero10Row = 0;
        int counterOne10Row = 0;
        int counterZero11Row = 0;
        int counterOne11Row = 0;
        int counterZero12Row = 0;
        int counterOne12Row = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i)[0].equals("0")) {
                counterZeroFirstRow++;
            } else if (arrayList.get(i)[0].equals("1")) {
                counterOneFirstRow++;
            }
            if (arrayList.get(i)[1].equals("0")) {
                counterZeroSecRow++;
            } else if (arrayList.get(i)[1].equals("1")) {
                counterOneSecRow++;
            }
            if (arrayList.get(i)[2].equals("0")) {
                counterZero3Row++;
            } else if (arrayList.get(i)[2].equals("1")) {
                counterOne3Row++;
            }
            if (arrayList.get(i)[3].equals("0")) {
                counterZero4Row++;
            } else if (arrayList.get(i)[3].equals("1")) {
                counterOne4Row++;
            }
            if (arrayList.get(i)[4].equals("0")) {
                counterZero5Row++;
            } else if (arrayList.get(i)[4].equals("1")) {
                counterOne5Row++;
            }
            if (arrayList.get(i)[5].equals("0")) {
                counterZero6Row++;
            } else if (arrayList.get(i)[5].equals("1")) {
                counterOne6Row++;
            }
            if (arrayList.get(i)[6].equals("0")) {
                counterZero7Row++;
            } else if (arrayList.get(i)[6].equals("1")) {
                counterOne7Row++;
            }
            if (arrayList.get(i)[7].equals("0")) {
                counterZero8Row++;
            } else if (arrayList.get(i)[7].equals("1")) {
                counterOne8Row++;
            }
            if (arrayList.get(i)[8].equals("0")) {
                counterZero9Row++;
            } else if (arrayList.get(i)[8].equals("1")) {
                counterOne9Row++;
            }
            if (arrayList.get(i)[9].equals("0")) {
                counterZero10Row++;
            } else if (arrayList.get(i)[9].equals("1")) {
                counterOne10Row++;
            }
            if (arrayList.get(i)[10].equals("0")) {
                counterZero11Row++;
            } else if (arrayList.get(i)[10].equals("1")) {
                counterOne11Row++;
            }
            if (arrayList.get(i)[11].equals("0")) {
                counterZero12Row++;
            } else if (arrayList.get(i)[11].equals("1")) {
                counterOne12Row++;
            }
        }
        String commonFirstRow, commonSecRow, common3Row, common4Row, common5Row, common6Row, common7Row, common8Row,
                common9Row, common10Row, common11Row, common12Row;
        String commonEpsilon1, commonEpsilon2, commonEpsilon3, commonEpsilon4, commonEpsilon5, commonEpsilon6,
                commonEpsilon7, commonEpsilon8, commonEpsilon9, commonEpsilon10, commonEpsilon11, commonEpsilon12;
        if (counterZeroFirstRow > counterOneFirstRow) {
            commonFirstRow = "0";
            commonEpsilon1 = "1";
        } else {
            commonFirstRow = "1";
            commonEpsilon1 = "0";
        }
        if (counterZeroSecRow > counterZeroSecRow) {
            commonSecRow = "0";
            commonEpsilon2 = "1";
        } else {
            commonSecRow = "1";
            commonEpsilon2 = "0";
        }
        if (counterZero3Row > counterOne3Row) {
            common3Row = "0";
            commonEpsilon3 = "1";
        } else {
            common3Row = "1";
            commonEpsilon3 = "0";
        }
        if (counterZero4Row > counterOne4Row) {
            common4Row = "0";
            commonEpsilon4 = "1";
        } else {
            common4Row = "1";
            commonEpsilon4 = "0";
        }
        if (counterZero5Row > counterOne5Row) {
            common5Row = "0";
            commonEpsilon5 = "1";
        } else {
            common5Row = "1";
            commonEpsilon5 = "0";
        }
        if (counterZero6Row > counterOne6Row) {
            common6Row = "0";
            commonEpsilon6 = "1";
        } else {
            common6Row = "1";
            commonEpsilon6 = "0";
        }
        if (counterZero7Row > counterOne7Row) {
            common7Row = "0";
            commonEpsilon7 = "1";
        } else {
            common7Row = "1";
            commonEpsilon7 = "0";
        }
        if (counterZero8Row > counterOne8Row) {
            common8Row = "0";
            commonEpsilon8 = "1";
        } else {
            common8Row = "1";
            commonEpsilon8 = "0";
        }
        if (counterZero9Row > counterOne9Row) {
            common9Row = "0";
            commonEpsilon9 = "1";
        } else {
            common9Row = "1";
            commonEpsilon9 = "0";
        }
        if (counterZero10Row > counterOne10Row) {
            common10Row = "0";
            commonEpsilon10 = "1";
        } else {
            common10Row = "1";
            commonEpsilon10 = "0";
        }
        if (counterZero11Row > counterOne11Row) {
            common11Row = "0";
            commonEpsilon11 = "1";
        } else {
            common11Row = "1";
            commonEpsilon11 = "0";
        }
        if (counterZero12Row > counterOne12Row) {
            common12Row = "0";
            commonEpsilon12 = "1";
        } else {
            common12Row = "1";
            commonEpsilon12 = "0";
        }

        String gammaString = commonFirstRow + commonSecRow + common3Row + common4Row + common5Row + common6Row +
                common7Row + common8Row + common9Row + common10Row + common11Row + common12Row;
        int decimalGamma = Integer.parseInt(gammaString, 2);



        String epsilonString = commonEpsilon1 + commonEpsilon2 + commonEpsilon3 + commonEpsilon4 + commonEpsilon5 +
                commonEpsilon6 + commonEpsilon7 + commonEpsilon8 + commonEpsilon9 + commonEpsilon10 + commonEpsilon11 +
                commonEpsilon12;
        int epsilon = Integer.parseInt(epsilonString, 2);
        int multiply = epsilon * decimalGamma;
        System.out.println("result puzzle 1 = " + multiply);

    }


    /**
     * right answer for 3.12.21 puzzle 2
     * @throws IOException
     */
    public static void getPuzzle2() throws IOException {
        File file = new File("D:\\Dokumente\\Privat\\Programme\\advent_of_code_21\\input_files\\input_day_3_binary_diagnostic.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        ArrayList<String[]> arrayList = new ArrayList<>();


        while ((s = reader.readLine()) != null) {
            String[] splitString = s.split("");
            arrayList.add(splitString);
        }
        ArrayList<String[]> tempOxygen = (ArrayList<String[]>) arrayList.clone();
        ArrayList<String[]> tempScrub = (ArrayList<String[]>) arrayList.clone();
        ArrayList<String[]> resOxy = new ArrayList<>();

        for (int k = 0; k < arrayList.get(0).length; k++) {
            //iterate over all bits
            for (int i = 0; i < tempOxygen.size(); i++) {
                // iterate over all elements
                String mostCommonOxy = getValueForOxy(tempOxygen, k);
                if (tempOxygen.get(i)[k].equals(mostCommonOxy)) {
                    resOxy.add(tempOxygen.get(i));
                }
            }
            for (String[] stringArray: resOxy) {
                tempOxygen.remove(stringArray);
            }
            resOxy.clear();
            if (tempOxygen.size() == 1) {
                break;
            }
        }


        ArrayList<String[]> resScrub = new ArrayList<>();
        System.out.println(tempScrub.size());
        for (int k = 0; k < arrayList.get(0).length; k++) {
            //iterate over all bits
            for (int i = 0; i < tempScrub.size(); i++) {
                // iterate over all elements
                String leastCommonScrub = getValueForScrub(tempScrub, k);
                if (tempScrub.get(i)[k].equals(leastCommonScrub)) {
                    resScrub.add(tempScrub.get(i));
                }
            }
            for (String[] stringArray: resScrub) {
                tempScrub.remove(stringArray);
            }
            resScrub.clear();
            if (tempScrub.size() == 1) {
                break;
            }
        }
        System.out.println("tempOxy length = " + tempOxygen.size() + ", tempScrub length = " + tempScrub.size());
        String[] oxyString = tempOxygen.get(0);
        String oxyRes = "";
        for (String strings: oxyString) {
            oxyRes += strings;
        }
        String scrubRes = "";
        String[] scrubString = tempScrub.get(0);
        for (String strings: scrubString) {
            scrubRes += strings;
        }
        System.out.println("res oxy " + oxyRes + ", res scrub " + scrubRes);
        int oxyDecimal = Integer.parseInt(oxyRes, 2);
        int scrubDecimal = Integer.parseInt(scrubRes, 2);

        System.out.println("result puzzle 2 = " + (oxyDecimal * scrubDecimal));

    }

    public static String getValueForOxy(ArrayList<String[]> list, int position) {
        String res = "";
        int zeroCounter = 0;
        int oneCounter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[position].equals("0")) {
                zeroCounter++;
            } else if (list.get(i)[position].equals("1")) {
                oneCounter++;
            }
        }
        if (zeroCounter > oneCounter) {
            return "0";
        }
        if (zeroCounter <= oneCounter) {
            return "1";
        }
        System.out.println("something went wrong");
        return "";
    }

    public static String getValueForScrub(ArrayList<String[]> list, int position) {
        String res = "";
        int zeroCounter = 0;
        int oneCounter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[position].equals("0")) {
                zeroCounter++;
            } else if (list.get(i)[position].equals("1")) {
                oneCounter++;
            }
        }
        if (zeroCounter > oneCounter) {
            return "1";
        }
        if (zeroCounter <= oneCounter) {
            return "0";
        }
        System.out.println("something went wrong");
        return "";
    }


}
