package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
//import java.util.stream.Stream;

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\tGreedy Algorithm Game\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input7.txt";

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            String str = scan.nextLine();
            String[] val = str.split(" ");
            int num = val.length;

            int[] value = new int[num];

            // parse and store data in array - O(n)
            for (int i = 0; i < num; i++) {
                value[i] = Integer.parseInt(val[i]);
            }
            System.out.println();

            // boolean test for greedy algorithm - O(n)
            if(jump(value, num) == true){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

            System.out.println();

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean jump(int[] num, int n){

        //Jump in the first index of the array
        int pos = n;

        //Greedy algorithm - count down for the number of jumps
        for(int i = n - 1; i >= 0; i--){
            if(i + num[i] >= pos){
                pos = i;
            }
        }

        //In just case if the last position is equal to 0, it must be true.
        return pos == 0;

    }

}
