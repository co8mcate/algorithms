package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
//import java.util.stream.Stream;

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\t2D Matrix\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input8.txt";

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

            System.out.println(matrix(value));

            System.out.println();

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int matrix(int[] num){

        //obtain the numbers for row and column
        int row = num[0];
        int col = num[1];
 
        //create 2D array with the size of row and column
        int[][] arr = new int[row][col];
        
        //Fill 2D array with 1s to know that we are not done
        for(int i = 0; i < row; i++)
            arr[i][0] = 1;
        for(int i = 0; i < col; i++)
            arr[0][i] = 1;

        //If it's not 1, 
        for(int i = 0; i < row; i++){
             for(int j = 0; j < col; j++ ){
                 if(arr[i][j] != 1 ){
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
 
        return arr[row-1][col-1];
    }
}
