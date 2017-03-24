/*
Name: Deva Surya Vivek
CS301: Algorithms and Data Structures
Programming Assignment 3
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFS {

    public static List<Integer> reachableVertices = new ArrayList<>();

    public static List<Boolean> visitedVertex = new ArrayList<>();

    public static void depthFirstSearch(int numberOfVertices, int adjacencyMatrix[][], int startVertex) {

        reachableVertices.add(startVertex);

        visitedVertex.remove(startVertex);
        visitedVertex.add(startVertex, true);

        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[startVertex][i] == 1) {
                if (!visitedVertex.get(i)) {
                    depthFirstSearch(numberOfVertices, adjacencyMatrix, i);
                }
            }
        }
    }

    public static void main(String args[]) {
        try {
            long startTime = System.currentTimeMillis();
            BufferedReader BR = new BufferedReader(new FileReader("input.txt"));

            //reading the first line: number of vertices
            String line = BR.readLine();
            int numberOfVertices = Integer.parseInt(line);

            //creating the required matrix
            int adjacencyMatrix[][] = new int[numberOfVertices][numberOfVertices];

            //reading the matrix from file
            for (int i = 0; i < numberOfVertices; i++) {
                line = BR.readLine();
                String lineSplit[] = line.split("");
                for (int j = 0; j < numberOfVertices; j++) {
                    adjacencyMatrix[i][j] = Integer.parseInt(lineSplit[j]);
                }
            }

            //reading the source vertex from file
            line = BR.readLine();
            int startVertex = Integer.parseInt(line);

            BR.close();

            startVertex = startVertex - 1;

            for (int i = 0; i < numberOfVertices; i++) {
                visitedVertex.add(false);
            }

            depthFirstSearch(numberOfVertices, adjacencyMatrix, startVertex);

            Collections.sort(reachableVertices);

            int size = reachableVertices.size();
            for (int i = 0; i < size; i++) {
                int temp = reachableVertices.get(i);
                reachableVertices.remove(i);
                temp = temp + 1;
                reachableVertices.add(i, temp);
            }


            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    writer.print(",");
                }
                writer.print(reachableVertices.get(i));
            }
            writer.close();

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for DFS: " + (endTime - startTime) + " milli seconds");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

