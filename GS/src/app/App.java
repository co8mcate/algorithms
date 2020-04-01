package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

/*
* Psuedocode for Gale-Shapley Algorithm "Marriage Matching"
Initially all m E M and w E W are free - check
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
    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n================================\n");
        System.out.println("\tStable Matching\n");
        System.out.println("================================\n");

        String filePath = "input1.txt";

        try {

            // Create an empty list for free men and women
            List<Integer> freeMan = new LinkedList();
            List<Integer> freeWoman = new LinkedList();
            List<List<Integer>> menList = new ArrayList();
            List<List<Integer>> womenList = new ArrayList();

            // Signal if the matching is stable or not
            // boolean flag = true;

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
            for (int i = 0; i < numOfPeople; i++) {
                List<Integer> eachMan = new ArrayList<Integer>();
                for (int j = 0; j < numOfPeople; j++) {
                    eachMan.add(scan.nextInt());
                }
                menList.add(i, eachMan);
                freeMan.add(i + 1);
            }

            // Parse data and store in data structures - O(n^2) for WOMEN
            for (int i = 0; i < numOfPeople; i++) {
                List<Integer> eachWoman = new ArrayList<Integer>();
                for (int j = 0; j < numOfPeople; j++) {
                    eachWoman.add(scan.nextInt());
                }
                womenList.add(i, eachWoman);
            }

            /*
             * 
             * // Parse data and store in data structures - O(n^2) for MEN int menList[][] =
             * new int[numOfPeople][numOfPeople]; for (int i = 0; i < numOfPeople; i++) {
             * for (int j = 0; j < numOfPeople; j++) { menList[i][j] = scan.nextInt(); } }
             * 
             * // Parse data and store in data structures - O(n^2) for WOMEN int
             * womenList[][] = new int[numOfPeople][numOfPeople]; for (int i = 0; i <
             * numOfPeople; i++) { for (int j = 0; j < numOfPeople; j++) { womenList[i][j] =
             * scan.nextInt(); } }
             * 
             */

            // Print men list for test
            for (List<Integer> row : menList) {
                System.out.println(row);
            }

            System.out.println();

            // Print women list for test
            for (List<Integer> row : womenList) {
                System.out.println(row);
            }

            /*
             * // Not sure about this list of Women's rank -- hold it for a while for (int i
             * = 0; i < numOfPeople; i++) { List<Integer> list = new ArrayList<Integer>();
             * for (int rank = 0; rank < numOfPeople; rank++) {
             * list.add(womenList.get(i).get(rank)); } womanRank.add(i, list); }
             */
            Map<Integer, Integer> engaged = new HashMap();
            int count = numOfPeople;
            while (!freeMan.isEmpty()) {
                // Choose such a man m
                int man = freeMan.remove(0);

                // System.out.println();
                // System.out.println("man: " + man);

                List<Integer> manRank = menList.get(man - 1);

                // System.out.println();
                // System.out.println("man's rank: " + manRank);

                for (Integer woman : manRank) {
                    // Let w be the highest-ranked woman in m's preference list to whom m has not
                    // yet proposed
                    // IF w is "free" then
                    // (w,m) become engaged
                    if (engaged.get(woman) == null) {
                        engaged.put(woman, man);
                        // System.out.println("engaged: " + engaged);
                        break;
                    } else {
                        int nextMan = engaged.get(woman);
                        List<Integer> womanRank = womenList.get(woman - 1);
                        if (womanRank.indexOf(man) < womanRank.indexOf(nextMan)) {
                            engaged.put(woman, man);
                            freeMan.add(nextMan);
                            break;
                        }
                        count--;
                    }
                }
            }
            // System.out.println("Stable Matching: " + engaged.size());
            System.out.println();
            System.out.println(count);
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}