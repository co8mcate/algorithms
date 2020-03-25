package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
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

        String filePath = "input1.txt";

        try {

            // Create an empty list for free men and women
            List<List<Integer>> S = new ArrayList();

            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);
            System.out.println("Found your file!");

            // Parse data and store in data structures - O(n)
            int numOfPeople;
            numOfPeople = Integer.parseInt(scan.nextLine());
            System.out.println("Number of both men and women: " + numOfPeople);

            // while (scan.hasNext()) {
            // Split the line into array using spaces as delimiter

            // }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}