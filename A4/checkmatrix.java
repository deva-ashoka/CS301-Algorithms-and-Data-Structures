//Name: Deva Surya Vivek
//CS301: Algorithms and Data Structures, Programming Assignment 4


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class checkmatrix {

    public static int n;
    public static int matrix[][];
    public static int R[];
    public static int C[];
    public static int sumTillRowOfColumn[];

    public static int maximumNonZeroIndex(int row, int difference[]) {

        int retVal = 0;
        List<Integer> nonZeroList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (matrix[row][i] == 0) {
                if (difference[i] != 0) {
                    nonZeroList.add(difference[i]);
                }
            }
        }

        int searchFor = Collections.max(nonZeroList);

        for (int i = 0; i < n; i++) {
            if ((searchFor == difference[i]) && (matrix[row][i] == 0)) {
                retVal = i;
            }
        }
        return retVal;
    }

    public static boolean checkMatrix() {

        int sumOfR = 0;
        int sumOfC = 0;
        for (int i = 0; i < n; i++) {
            sumOfR += R[i];
            sumOfC += C[i];
        }
        if (sumOfR != sumOfC) {
            return false;
        }

        sumTillRowOfColumn = new int[n];
        for (int i = 0; i < n; i++) {
            sumTillRowOfColumn[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            int row = i;
            int numberOfOnes = R[i];
            for (int itr = 0; itr < numberOfOnes; itr++) {
                int difference[] = new int[n];
                for (int j = 0; j < n; j++) {
                    difference[j] = C[j] - sumTillRowOfColumn[j];
                }
                int maxIndex = maximumNonZeroIndex(row, difference);

                matrix[row][maxIndex] = 1;
                sumTillRowOfColumn[maxIndex] += 1;

            }
        }

        return true;
    }

    public static void main(String args[]) {
        try {

            long startTime = System.currentTimeMillis();
            BufferedReader BR = new BufferedReader(new FileReader("input.txt"));

            //reading the first line: n
            String line = BR.readLine();
            n = Integer.parseInt(line);

            matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }

            R = new int[n];
            C = new int[n];

            //reading R1.....Rn
            String lineR = BR.readLine();
            String lineRSplit[] = lineR.split(",");
            //reading C1.....Cn
            String lineC = BR.readLine();
            String lineCSplit[] = lineC.split(",");

            for (int i = 0; i < n; i++) {
                R[i] = Integer.parseInt(lineRSplit[i]);
                C[i] = Integer.parseInt(lineCSplit[i]);
            }

            boolean matrixExists = checkMatrix();

            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            if (!matrixExists) {
                writer.println(0);
            }
            if (matrixExists) {
                writer.println(1);
                for (int i = 0; i < n; i++) {

                    String writeLine = "";
                    for (int j = 0; j < n; j++) {
                        if (j != 0) {
                            writeLine += ",";
                        }
                        writeLine += Integer.toString(matrix[i][j]);
                    }
                    writer.println(writeLine);
                }
            }
            writer.close();

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + " milli seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
