/*
PSEUDOCODE
1. Get the departure time of researcher
2. Create 2 priority queues to store arrival and departure times
3. Create a variable to count the number of times Penelope will save
4. while arrivalArr not empty {
    while difference is greater than idleTimeLimit, poll and check against next element
   if departure time is less than arrival time, increment timesSaved
}
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class AssigningWorkstations {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String inputs = br.readLine();
        String[] strs = inputs.trim().split("\\s+");
        int numOfResearchers = Integer.parseInt(strs[0]);
        int idleTimeLimit = Integer.parseInt(strs[1]);

        PriorityQueue<Integer> arrivalArr = new PriorityQueue<>();
        PriorityQueue<Integer> departureArr = new PriorityQueue<>();

        for (int i = 0; i < numOfResearchers; i++) {
            String info = br.readLine();
            String[] strs1 = info.trim().split("\\s+");
            int arrivalDuration = Integer.parseInt(strs1[0]);
            int stayDuration = Integer.parseInt(strs1[1]);
            int departureTime = arrivalDuration + stayDuration; // get the departure time of researcher

            arrivalArr.add(arrivalDuration); // push into arrivalArr
            departureArr.add(departureTime); // push into departureArr
        }

        // create a variable to count the number of times Penelope will save
        int timesSaved = 0;
        int originalArrSize = arrivalArr.size();
        int arrivalCounter = 0; // to keep track of element in arrivalArr

        // arrivalArr not empty
        while(arrivalCounter < originalArrSize) {
            int arrivalValue = arrivalArr.poll();
            arrivalCounter++;
            while(arrivalValue - departureArr.peek() > idleTimeLimit) { // if difference is greater than idleTimeLimit, poll and check against next element
                departureArr.poll();
            }
            if(departureArr.peek() <= arrivalValue) { // if departure time is less than arrival time, increment timesSaved
                departureArr.poll();
                timesSaved++;
            }
        }
        writer.println(timesSaved);

        writer.flush();
        writer.close();
        br.close();
        r.close();
    }
}
