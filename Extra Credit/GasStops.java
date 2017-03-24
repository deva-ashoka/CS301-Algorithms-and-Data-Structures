//Name: Deva
//Extra credit test

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class GasStops {


    public static List<Integer> gasStops(int n, int D, int e, int A[]) {

        List<Integer> stations = new ArrayList<>();

        int currentLocation = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (A[i] > A[j])
                {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }

        while (e - currentLocation > D) {
            int nextStationPosition = 0;
            for (int i = 0; i < n; i++) {
                if (A[i] > currentLocation + D) {
                    nextStationPosition = i - 1;
                    break;
                }
                nextStationPosition = i;
            }
            stations.add(A[nextStationPosition]);
            currentLocation = A[nextStationPosition];
        }

        return stations;

    }


    public static void main(String args[]) {
        try {

            long startTime = System.currentTimeMillis();
            BufferedReader BR = new BufferedReader(new FileReader("input.txt"));

            String line = BR.readLine();

            int n = Integer.parseInt(line);

            line = BR.readLine();
            int D = Integer.parseInt(line);

            line = BR.readLine();
            int e = Integer.parseInt(line);

            int A[] = new int[n];
            int initialArray[] = new int[n];

            line = BR.readLine();
            String lineSplit[] = line.split(",");
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(lineSplit[i]);
                initialArray[i] = Integer.parseInt(lineSplit[i]);
            }

            List<Integer> stations =  gasStops(n, D, e, A);

            int size = stations.size();

            for(int i=0; i<size; i++){
                int station = stations.get(i);
                for(int j=0; j<n; j++){
                    if(station == initialArray[j]){
                        System.out.print(j + ",");
                    }
                }
            }
            System.out.println();
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + " milli seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
