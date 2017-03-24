import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dcmultiply {

    public static String addBinaryInputs(String input1, String input2) {
        String result = "";
        if (input1.length() != input2.length()) {
            int length1 = input1.length();
            int length2 = input2.length();
            if (length1 < length2) {
                for (int i = 0; i < length2 - length1; i++) {
                    input1 = '0' + input1;
                }
            } else if (length2 < length1) {
                for (int i = 0; i < length1 - length2; i++) {
                    input2 = '0' + input2;
                }
            }
        }
        String[] array1 = input1.split("");
        String[] array2 = input2.split("");
        int carry = 0;
        for (int i = array1.length - 1; i >= 0; i--) {
            int bit1 = Integer.parseInt(array1[i]);
            int bit2 = Integer.parseInt(array2[i]);
            int sum = (bit1 ^ bit2 ^ carry);
            result = sum + result;
            carry = (bit1 & bit2) | (bit2 & carry) | (bit1 & carry);
        }
        if (carry == 1) {
            result = '1' + result;
        }
        while (result.substring(0, 1).equals("0") && result.length() > 1) {
            result = result.substring(1);
        }
        return result;
    }

    public static String DCMultiply(String input1, String input2) {

        String result = "";

        //padding zeroes if the sizes are not equal
        if (input1.length() != input2.length()) {
            int length1 = input1.length();
            int length2 = input2.length();
            if (length1 < length2) {
                for (int i = 0; i < length2 - length1; i++) {
                    input1 = '0' + input1;
                }
            } else if (length2 < length1) {
                for (int i = 0; i < length1 - length2; i++) {
                    input2 = '0' + input2;
                }
            }
        }

        int length = input1.length();

        if (length == 1) {
            int product = Integer.parseInt(input1) * Integer.parseInt(input2);
            result = Integer.toString(product);
            return result;
        }

        //to include the cases that are not 2^n
        int mid = (length + 1) / 2;
        int mid1 = length / 2;

        String P = DCMultiply(input1.substring(0, mid), input2.substring(0, mid));
        String Q = DCMultiply(input1.substring(0, mid), input2.substring(mid, length));
        String R = DCMultiply(input1.substring(mid, length), input2.substring(0, mid));
        String S = DCMultiply(input1.substring(mid, length), input2.substring(mid, length));

        for (int i = 0; i < (2 * mid1); i++) {
            P = P + "0";
        }

        String QR = addBinaryInputs(Q, R);

        for (int i = 0; i < (mid1); i++) {
            QR = QR + "0";
        }

        result = addBinaryInputs(P, QR);
        result = addBinaryInputs(result, S);

        return result;
    }

    public static void main(String args[]) {

        long startTime = System.currentTimeMillis();

        try {

            String inputFilePath = "input.txt";

            String[] inputNum = new String[2];

            FileReader inputFile = new FileReader(inputFilePath);
            BufferedReader inputBr = new BufferedReader(inputFile);
            inputNum[0] = inputBr.readLine();
            inputNum[1] = inputBr.readLine();
            inputBr.close();

            System.out.println("Number 1: " + inputNum[0]);
            System.out.println("Number 2: " + inputNum[1]);

            String output = DCMultiply(inputNum[0], inputNum[1]);
            System.out.println("Product is: " + output);

            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println(output);
            writer.close();

            long endTime = System.currentTimeMillis();

            long totalTime = endTime - startTime;

            System.out.println("Time taken: " + totalTime + " milli seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
