/*
Name: Deva Surya Vivek
CS301: Algorithms and Data Structures
Programming Assignment 3
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BFS {

    public static List<Integer> breadthFirstSearch(int numberOfVertices, int adjacencyMatrix[][], int startVertex) {

        List<Integer> reachableVertices = new ArrayList<>(); //all the reachable vertices after BFS will be added to this list

        boolean visitedVertex[] = new boolean[numberOfVertices]; //array containing visited/not visited status of vertices

        //intializing all the vertices to not visited
        for (int i = 0; i < numberOfVertices; i++) {
            visitedVertex[i] = false;
        }

        //list to store the vertices to be explored
        List<Integer> list = new ArrayList<>();

        list.add(startVertex);
        reachableVertices.add(startVertex);
        visitedVertex[startVertex] = true;


        while (list.size() != 0) {

            int vertex = list.get(0);
            list.remove(0);

            for (int i = 0; i < numberOfVertices; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    if (!visitedVertex[i]) {
                        list.add(i);
                        visitedVertex[i] = true;
                        reachableVertices.add(i);
                    }
                }
            }
        }
        return reachableVertices;
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

            List<Integer> reachableVertices = breadthFirstSearch(numberOfVertices, adjacencyMatrix, startVertex);
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
            System.out.println("Time taken for BFS: " + (endTime - startTime) + " milli seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
