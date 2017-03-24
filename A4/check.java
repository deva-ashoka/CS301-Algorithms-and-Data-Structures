import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class check {

    public static void main(String args[]) {
        try {
            BufferedReader BR = new BufferedReader(new FileReader("output.txt"));

            //reading the first line: number of vertices
            String line = BR.readLine();
            int n = Integer.parseInt(line);
            n = 10;

            //creating the required matrix
            int matrix[][] = new int[n][n];

            //reading the matrix from file
            for (int i = 0; i < n; i++) {
                line = BR.readLine();
                String lineSplit[] = line.split(",");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(lineSplit[j]);
                }
            }

            List<Integer> row = new ArrayList<>();
            List<Integer> col = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int rowSum = 0;
                int colSum = 0;
                for (int j = 0; j < n; j++) {
                    rowSum += matrix[i][j];
                    colSum += matrix[j][i];
                }
                row.add(rowSum);
                col.add(colSum);
            }
            System.out.println(row);
            System.out.println(col);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
