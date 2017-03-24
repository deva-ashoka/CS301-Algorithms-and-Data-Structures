import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class longaddition {

    public static String[] makeStringsEqualLength(String input1, String input2) {
        String[] input = new String[2];
        int length1 = input1.length();
        int length2 = input2.length();
        if (length1 < length2) {
            for (int i = 0; i < length2 - length1; i++) {
                input1 = '0' + input1;
            }
            input[0] = input1;
            input[1] = input2;
            return input;

        } else if (length2 < length1) {
            for (int i = 0; i < length1 - length2; i++) {
                input2 = '0' + input2;
            }
        }
        input[0] = input1;
        input[1] = input2;
        return input;
    }

    public static String addBinaryInputs(String[] array1, String[] array2) {

        String result = "";

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

            String[] input = makeStringsEqualLength(inputNum[0], inputNum[1]);

            String[] array1 = input[0].split("");
            String[] array2 = input[1].split("");

            String output = addBinaryInputs(array1, array2);

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
