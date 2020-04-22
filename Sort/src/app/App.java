package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
//import java.util.stream.Stream;

public class App {

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\tSort the integers\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input5.txt";

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            String str = scan.nextLine();
            String[] val = str.split(" ");
            int num = val.length;

            Integer[] value = new Integer[num];

            // parse and store data in array - O(n)
            for (int i = 0; i < num; i++) {
                value[i] = Integer.parseInt(val[i]);
            }

            // Arrays.sort(value, (a, b) -> (Integer.compare(Math.abs(a), Math.abs(b))));
            // Arrays.sort(value, (a, b) -> (Math.abs(a) - Math.abs(b)));
            Arrays.sort(value, (a, b) -> (rearrange(a, b)));

            // Arrays.sort(value, Comparator.comparingInt(Math::abs));
            // Arrays.sort(absoluteValueComparison(value));

            System.out.println();
            System.out.println(Arrays.toString(value));
            // Stream.of(value).forEach(a -> System.out.print(a));
            // Arrays.asList(value).forEach(System.out::println);

            System.out.println();

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int rearrange(int a, int b) {

        if (Math.abs(a) == Math.abs(b)) {
            return a;
        }

        return Math.abs(a) - Math.abs(b);
    }

}
