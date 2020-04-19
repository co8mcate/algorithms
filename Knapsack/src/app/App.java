package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//Implement the O(n log n) algorithm that solves the Counting Inversions problem from Chapter 5.

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\tKnapsack Problem\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input4.txt";

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            int maxcap;
            maxcap = Integer.parseInt(scan.nextLine());

            String str = scan.nextLine();
            String[] val = str.split(" ");
            int num = val.length;

            int[] value = new int[num];

            // parse and store data in array - O(n)
            for (int i = 0; i < num; i++) {
                value[i] = Integer.parseInt(val[i]);
            }

            String str2 = scan.nextLine();
            String[] wgt = str2.split(" ");

            int[] weight = new int[num];

            // parse and store data in array - O(n)
            for (int i = 0; i < num; i++) {
                weight[i] = Integer.parseInt(wgt[i]);
            }

            System.out.println();

            System.out.println(knapsack(maxcap, weight, value, num));

            System.out.println();

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int knapsack(int maxcap, int wt[], int val[], int n) {

        int[][] knap = new int[n + 1][maxcap + 1];

        // Use Bottom-Up approach - O(n^2)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxcap; j++) {
                // Starts with the bottom
                if (i == 0 || j == 0) {
                    knap[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    knap[i][j] = Math.max(val[i - 1] + knap[i - 1][j - wt[i - 1]], knap[i - 1][j]);
                } else
                    knap[i][j] = knap[i - 1][j];
            }
        }

        return knap[n][maxcap];
    }

}