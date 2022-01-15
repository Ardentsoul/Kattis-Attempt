/*
PSEUDOCODE
1. Take in num of runners
2. Loop through the num of runners
3. For each iteration, take in the name, time to run 1st leg, and time to run any of the other legs
4. Store these info into an array. array of sprinters(Objects)
5. Sort the time to run any of the other legs in ascending order.
6. Create an empty string array and finalTime variable for final output
7. Compare each of the firstLegTimings
8. Take the fastest 3 timings for otherLegTimings
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BestRelayTeam {

    public static class Sprinter implements Comparable<Sprinter> {
        private String name;
        private float firstLegTime;
        private float otherLegTime;

        public Sprinter(String name, float firstLegTime, float otherLegTime) {
            this.name = name;
            this.firstLegTime = firstLegTime;
            this.otherLegTime = otherLegTime;
        }

        public String getName() {
            return this.name;
        }

        public float getFirstLegTime() {
            return this.firstLegTime;
        }

        public float getOtherLegTime() {
            return this.otherLegTime;
        }

        // Testing purpose
        @Override
        public String toString() {
            String str = "";
            str = String.format("Name: %s, first: %f, other: %f",this.getName(), this.getFirstLegTime(), this.getOtherLegTime());
            return str;
        }

        @Override
        public int compareTo(Sprinter other) {
            // smaller -1. this < other
            // bigger 1. this > other
            // 0. this == other
            if(this.getOtherLegTime() - other.getOtherLegTime() > 0) {
                return 1;
            } else if (this.getOtherLegTime() - other.getOtherLegTime() < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.nextLine();

        ArrayList<Sprinter> sprinterArr = new ArrayList<>(input); // new empty array of size input
        for(int i = 0; i < input; i++) {
            String name = sc.next();
            float firstTimingLeg = sc.nextFloat();
            float otherTimingLeg = sc.nextFloat();
            Sprinter sprinterObj = new Sprinter(name, firstTimingLeg, otherTimingLeg); // create object of Sprinter
            sprinterArr.add(sprinterObj);
        }

        // Sort the array based on otherTimingLeg
        Collections.sort(sprinterArr);

        // Store the array of names
        String[] finalStrArr = new String[4];

        // Store the total time
        float finalTime = Float.POSITIVE_INFINITY;

        // Compare each of the firstLegTimings
        for(int i = 0; i < input; i++) {
            float totalTime = sprinterArr.get(i).getFirstLegTime();
            String[] strArr = new String[4];
            strArr[0] = sprinterArr.get(i).getName();
            // Take the fastest 3 timings for otherLegTimings
            int counter = 0;
            for(int j = 0; counter < 3; j++) {
                String name = sprinterArr.get(i).getName();
                String otherName = sprinterArr.get(j).getName();
                if(name.equals(otherName)) {
                    continue;
                }
                strArr[counter + 1] = sprinterArr.get(j).getName();
                counter++;
                totalTime += sprinterArr.get(j).getOtherLegTime();
            }

            if(totalTime < finalTime) {
                finalTime = totalTime;
                finalStrArr = strArr;
            }
        }
        System.out.printf("%.2f\n",finalTime);
        for(int i = 0; i < finalStrArr.length; i++) {
            System.out.println(finalStrArr[i]);
        }
    }
}

