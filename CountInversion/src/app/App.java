package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

//Implement the O(n log n) algorithm that solves the Counting Inversions problem from Chapter 5.

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\tCounting Inversions\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input3.txt";
            ArrayList<Integer> list = new ArrayList<>();

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            // Use a delimiter to read characters without whitespace
            scan.useDelimiter(Pattern.compile(" "));

            // Parse and store data into ArrayList - O(n)
            while (scan.hasNextLine()) {
                list.add(scan.nextInt());
            }

            System.out.println();

            System.out.println("A list of numbers: " + list);

            System.out.println();

            // Create a new array for mergeSort
            int arr[] = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }

            // Print a number of counting inversions
            System.out.println("Number of inversions are " + mergeSort(arr));

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Driver method of MergeSort
    static int mergeSort(int arr[]) {

        int numArray = arr.length;

        int temp[] = new int[numArray];

        return mergeSorts(arr, temp, 0, numArray - 1);
    }

    // returns the number of inversions in the array - O(n log n)
    static int mergeSorts(int arr[], int temp[], int left, int right) {

        int mid, count = 0;

        if (right > left) {

            // Divide and insert an integer as middle
            mid = (right + left) / 2;

            count = mergeSorts(arr, temp, left, mid);
            count += mergeSorts(arr, temp, mid + 1, right);

            // Merge two parts
            count += merge(arr, temp, left, mid + 1, right);
        }

        return count;
    }

    // This method merges two sorted arrays and returns inversion count in the
    // arrays.
    static int merge(int arr[], int temp[], int left, int mid, int right) {
        int i, j, k;
        int count = 0;

        i = left;
        j = mid;
        k = left;

        // O(n)
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count = count + (mid - i);
            }

        }

        // O(n)
        while (i <= mid - 1)
            temp[k++] = arr[i++];

        // O(n)
        while (j <= right)
            temp[k++] = arr[j++];

        // Copy elements to original array - O(n)
        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return count;

    }

}