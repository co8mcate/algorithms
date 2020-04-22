package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
//import java.util.stream.Stream;

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n=====================================\n");
        System.out.println("\tThe stairway to heaven\n");
        System.out.println("=====================================\n");

        try {

            String filePath = "input6.txt";

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            System.out.println();

            // Parse data and store in data structures - O(1) for number of people
            int numOfSteps;
            numOfSteps = Integer.parseInt(scan.nextLine());
            System.out.println("Number of steps to the heaven: " + numOfSteps);

            // Dynamic programming - Bottom Up - O(n)
            System.out.println("Number of unique ways of steps: " + steps(numOfSteps));

            System.out.println();

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int steps(int n) {

        if (n <= 1) {
            return n;
        }

        int[] step = new int[n + 1];

        // starting with bottom
        step[1] = 1;
        step[2] = 2;

        for (int i = 3; i <= n; i++) {
            step[i] = step[i - 1] + step[i - 2];
        }

        return step[n];
    }
}
