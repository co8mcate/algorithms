package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

/*
* Psuedocode for Gale-Shapley Algorithm "Marriage Matching"
Initially all m E M and w E W are free
WHILE there is a man m who is "free" and hasn't proposed to every woman
    Choose such a man m
    Let w be the highest-ranked woman in m's preference list to whom m has not yet proposed
    IF w is "free" then
        (m,w) become engaged
    ELSE w is currently engaged to m'
        IF w prefers m' to m then
           m remains "free"
        ELSE w prefers m to m'
            (m,w) become engaged
             m' becomes "free"
        ENDIF
    ENDIF
ENDWHILE
*
*
*/

public class App {
    public static void main(final String[] args) throws Exception {

        System.out.println("\n================================\n");
        System.out.println("\tStable Matching\n");
        System.out.println("================================\n");

        String filePath = "input2.txt";

        try {

            // Create an empty list for free men and women
            List<List<Integer>> freeMen = new ArrayList();

            // Signal if the matching is stable or not
            boolean flag = true;

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            // Parse data and store in data structures - O(1) for number of people
            int numOfPeople;
            numOfPeople = Integer.parseInt(scan.nextLine());
            System.out.println("Number of people in a group of men or women: " + numOfPeople);

            // Parse data and store in data structures - O(n^2) for MEN
            int menList[][] = new int[numOfPeople][numOfPeople];
            for (int i = 0; i < numOfPeople; i++) {
                for (int j = 0; j < numOfPeople; j++) {
                    menList[i][j] = scan.nextInt();
                }
            }

            // Parse data and store in data structures - O(n^2) for WOMEN
            int womenList[][] = new int[numOfPeople][numOfPeople];
            for (int i = 0; i < numOfPeople; i++) {
                for (int j = 0; j < numOfPeople; j++) {
                    womenList[i][j] = scan.nextInt();
                }
            }

            // Print men list for test
            for (int[] row : menList) {
                System.out.println(Arrays.toString(row));
            }

            System.out.println();

            // Print women list for test
            for (int[] row : womenList) {
                System.out.println(Arrays.toString(row));
            }

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}