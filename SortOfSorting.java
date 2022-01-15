/*
PSEUDOCODE
1. Make a comparator anonymous class to compare substrings(0, 2)
2. Create array to hold the names
3. while number input > 0, sort the array and print out the output
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class SortOfSorting {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        // Make a comparator anonymous class
        Comparator<String> cmp = Comparator.comparing(o -> o.substring(0, 2));

        String inputOfNames = br.readLine();
        int numOfNames = Integer.parseInt(inputOfNames);

        while(numOfNames > 0) {
            // create an array to hold the names
            String[] arrOfNames = new String[numOfNames];

            for (int i = 0; i < numOfNames; i++) {
                arrOfNames[i] = br.readLine();
            }

            // Sort the Array using comparator class created above
            Arrays.sort(arrOfNames, cmp);

            // print the output of sorted array
            for (int i = 0; i < numOfNames; i++) {
                writer.println(arrOfNames[i]);
            }
            writer.println();
            inputOfNames = br.readLine();
            numOfNames = Integer.parseInt(inputOfNames);
        }
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
