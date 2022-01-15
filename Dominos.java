/*
PSEUDOCODE
1. Num of dominos toppled == num of SCC
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Dominos {
    public static int[] visitedArray;
    public static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    public static Stack<Integer> topoStack = new Stack<>();

    public static void DFS1(int v) {
        visitedArray[v] = 1; // to avoid cycle

        for (int i = 0; i < adjList.get(v).size(); i++) { // order of neighbor
            if(visitedArray[adjList.get(v).get(i)] == 0) { // influences DFS
                DFS1(adjList.get(v).get(i)); // recursive (implicit stack)
            }
        }
        topoStack.push(v);
    }

    public static void DFS2(int v) {
        visitedArray[v] = 1; // to avoid cycle

        for (int i = 0; i < adjList.get(v).size(); i++) { // order of neighbor
            if(visitedArray[adjList.get(v).get(i)] == 0) { // influences DFS
                DFS1(adjList.get(v).get(i)); // recursive (implicit stack)
            }
        }
    }

    public static int kosaraju() {
        for (int i = 0; i < visitedArray.length; i++) {
            visitedArray[i] = 0; // reset the visited array to false
        }

        int SCC = 0;
        while(!topoStack.empty()) {
            int topOfStack = topoStack.pop();
            if(visitedArray[topOfStack] == 0) {
                SCC++;
                DFS2(topOfStack);
            }
        }
        return SCC;
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String input = br.readLine();
        int numOfTestCases = Integer.parseInt(input);

        for (int i = 0; i < numOfTestCases; i++) {
            String info = br.readLine();
            String[] strs = info.split(" ");
            int numOfDominos = Integer.parseInt(strs[0]);
            int numOfLines = Integer.parseInt(strs[1]);

            // initialise visited Array and reset adjList
            visitedArray = new int[numOfDominos];
            adjList = new ArrayList<>();

            // set up visited array and populating adjList
            for (int j = 0; j < numOfDominos; j++) {
                adjList.add(new ArrayList<>());
                visitedArray[j] = 0;
            }

            for (int j = 0; j < numOfLines; j++) {
                String dom = br.readLine();
                strs = dom.split(" ");
                int x = Integer.parseInt(strs[0]) - 1; // input is 1 based index so must minus 1
                int y = Integer.parseInt(strs[1]) - 1; // input is 1 based index so must minus 1
                adjList.get(x).add(y); // add the neighbour into the x's adjList
            }

            // Getting topostack
            topoStack = new Stack<Integer>();  // reset toposort (topostack)

            for (int j = 0; j < numOfDominos; j++) {
                if(visitedArray[j] == 0) {
                    DFS1(j); // push into topostack
                }
            }
            int count = kosaraju();
            writer.println(count);
        }

        br.close();
        writer.flush();
        writer.close();
    }
}
