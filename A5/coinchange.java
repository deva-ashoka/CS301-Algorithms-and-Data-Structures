// Name: Deva Surya Vivek
//Programming Assignment 5


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class coinchange {

    public static int n;
    public static int v[];
    public static int C;
    public static int totalCoins[];
    public static int coinUsed[];
    public static int t[];

    public static boolean coinChange() {

        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < C + 1; j++) {
                if (v[i] <= j) {
                    if (totalCoins[j - v[i]] + 1 < totalCoins[j]) {
                        totalCoins[j] = totalCoins[j - v[i]] + 1;
                        coinUsed[j] = i;
                    }
                }
            }
        }

        if (coinUsed[C] == -1) {
            return false;
        }

        int amount = C;
        while (amount != 0) {
            int coinIndex = coinUsed[amount];
            coins.add(coinIndex);
            amount = amount - v[coinIndex];
        }

        int size = coins.size();
        for (int i = 0; i < size; i++) {
            int indexOfCoin = coins.get(i);
            t[indexOfCoin] += 1;
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

            v = new int[n];

            //getting v1,v2,....,vn
            line = BR.readLine();
            String lineSplit[] = line.split(",");
            for (int i = 0; i < n; i++) {
                v[i] = Integer.parseInt(lineSplit[i]);
            }

            // t contains number of coins used for each coin in v
            t = new int[n];
            for (int i = 0; i < n; i++) {
                t[i] = 0;
            }

            //reading C (amount for which the change must be produced)
            line = BR.readLine();
            C = Integer.parseInt(line);

            //totalCoins keeps a track of number of minimum coins required for 'i' amount
            //coinUsed keeps a track of which coin was used to attain that minimum coins
            totalCoins = new int[C + 1];
            coinUsed = new int[C + 1];
            totalCoins[0] = 0;

            for (int i = 1; i < C + 1; i++) {
                totalCoins[i] = Integer.MAX_VALUE - 1;
                coinUsed[i] = -1;
            }

            boolean retVal = coinChange();

            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

            if (retVal) {

                for (int i = 0; i < n; i++) {
                    if(i!=0){
                        writer.print(",");
                    }
                    writer.print(t[i]);
                }

            } else {
                writer.println("Coin change is not possible for the given amount and denominations");
            }

            writer.close();


            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + " milli seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
