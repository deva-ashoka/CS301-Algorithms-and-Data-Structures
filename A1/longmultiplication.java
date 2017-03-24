import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class longmultiplication {

    public static String multiplyBinaryInputs(String[] array1, String[] array2) {

        String result = "";
        int productLength = array1.length + array2.length;
        int[] product = new int[productLength];

        for (int i = array2.length - 1; i >= 0; i--) {
            int carry = 0;
            int tempj = 0;
            for (int j = array1.length - 1; j >= 0; j--) {
                product[i + j + 1] += carry + (Integer.parseInt(array1[j]) * Integer.parseInt(array2[i]));
                carry = product[j + i + 1] / 2;
                product[i + j + 1] = product[i + j + 1] % 2;
                tempj = j;
            }
            product[i + tempj] += carry;
        }

        for (int i = product.length - 1; i >= 0; i--) {
            result = Integer.toString(product[i]) + result;
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

            System.out.println("Number 1: " + inputNum[0]);
            System.out.println("Number 2: " + inputNum[1]);

            String[] array1 = inputNum[0].split("");
            String[] array2 = inputNum[1].split("");

            String output = multiplyBinaryInputs(array1, array2);
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
