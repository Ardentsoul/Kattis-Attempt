/*
PSEUDOCODE
1. Create a hashmap to store keys(combination) and values(occurrences)
2. Sort the string input (BufferedReader)
3. Collapse the string array and use it as a key for the hashmap(15-digit key for hashmap)
4. Update hashmap if key already exists, add occurrences + 1, otherwise add into hashmap
5. Find the max occurrences of the value. Do this by .get(key)
6. Iterate through the max occurrences and increment the numOfStudents variable.
7. Output numOfStudents
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Conformity {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String totalFrosh= br.readLine();
        int totalFroshNum = Integer.parseInt(totalFrosh);

        // create a hashmap
        HashMap<String, Integer> froshCombinations = new HashMap<>();

        for (int i = 0; i < totalFroshNum; i++) {
            String lines = br.readLine();
            String[] strs = lines.trim().split("\\s+");

            // Sort the String array
            Arrays.sort(strs);

            // key for hashmap (collapse the string array), value = num of occurrences
            String key = strs[0]+ strs[1] + strs[2] + strs[3] + strs[4];

            // update hashmap if key already exists, add occurrences
            if(froshCombinations.containsKey(key)) {
                froshCombinations.compute(key, (keys, val)
                                -> (val == null) // handle nullPointerException
                                    ? 1
                                    : val + 1);
            } else {
                froshCombinations.put(key, 1);
            }
        }

        // find max occurrence
        int maxOccurrence = 0;
        for(String key : froshCombinations.keySet()) { // geek4geeks for loop
            if(froshCombinations.get(key) > maxOccurrence) {
                maxOccurrence = froshCombinations.get(key);
            }
        }

        // find the num of max occurrence and input into numOfStudents
        int numOfStudents = 0;
        for(String key : froshCombinations.keySet()) { // geek4geeks for loop
            if(froshCombinations.get(key)  == maxOccurrence) {
                numOfStudents += maxOccurrence;
            }
        }
        // Output the total number of students taking some combination of courses that is most popular
        writer.println(numOfStudents);

        writer.flush();
        writer.close();
        br.close();
        r.close();
    }
}
