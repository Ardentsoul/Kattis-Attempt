/*
PSEUDOCODE
1. Create a Coordinate class for BFS and main method
2. Create a BFS method 
   2.1 Mark a vertice as visited when added to queue
   2.2 Add valid left, right, top and bottom neighbour with 3 conditions
       - if it's not visited
       - if it's not 'W'
       - if not exceeding the boundaries of the matrix
3. In main method, populate visited array with 0 and populate queue with 'L' coordinates
4. Perform BFS on each coordinate in landQueue after polling
   4.1 if not visited, perform BFS and increment counter, else continue
5. Print counter
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Islands {
    public static int[][] visitedArray;
    public static char[][] characterArray;
    public static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void BFS(Coordinate z) {
        LinkedList<Coordinate> neighbourQueue = new LinkedList<>();
        neighbourQueue.offer(z);
        visitedArray[z.getX()][z.getY()] = 1; // mark vertice as visited when added to queue

        while(neighbourQueue.size() != 0) {
        // Deque
            Coordinate coordinate = neighbourQueue.poll();

            // add valid left neighbour
            if(coordinate.getX() > 0 &&
                    visitedArray[coordinate.getX() - 1][coordinate.getY()] == 0 &&
                    characterArray[coordinate.getX() - 1][coordinate.getY()] != 'W') {
                neighbourQueue.offer(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
                visitedArray[coordinate.getX() - 1][coordinate.getY()] = 1;
            }

            // add valid right neighbour
            if(coordinate.getX() < characterArray.length - 1 &&
                    visitedArray[coordinate.getX() + 1][coordinate.getY()] == 0 &&
                    characterArray[coordinate.getX() + 1][coordinate.getY()] != 'W') {
                neighbourQueue.offer(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
                visitedArray[coordinate.getX() + 1][coordinate.getY()] = 1;
            }

            // add valid top neighbour
            if(coordinate.getY() > 0 &&
                    visitedArray[coordinate.getX()][coordinate.getY() - 1] == 0 &&
                    characterArray[coordinate.getX()][coordinate.getY() - 1] != 'W') {
                neighbourQueue.offer(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
                visitedArray[coordinate.getX()][coordinate.getY() - 1] = 1;
            }

            // add valid bottom neighbour
            if(coordinate.getY() < characterArray[0].length - 1 &&
                    visitedArray[coordinate.getX()][coordinate.getY() + 1] == 0 &&
                    characterArray[coordinate.getX()][coordinate.getY() + 1] != 'W') {
                neighbourQueue.offer(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
                visitedArray[coordinate.getX()][coordinate.getY() + 1] = 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String inputs = br.readLine();
        String[] strs = inputs.split("\\s+");
        int row = Integer.parseInt(strs[0]);
        int col = Integer.parseInt(strs[1]);

        // initialise a visited array
        visitedArray = new int[row][col];

        // initialise a character array
        characterArray = new char[row][col];

        // initialise a counter to keep track of min islands possible
        int minIslands = 0;

        // declare a queue to store all 'L' in the graph
        LinkedList<Coordinate> landQueue = new LinkedList<>();

        // populate character array with input
        for (int i = 0; i < row; i++) {
            characterArray[i] = br.readLine().toCharArray();
        }

        // populate visited array with 0 and populate queue with 'L' coordinates
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visitedArray[i][j] = 0; // populate with 0
                if(characterArray[i][j] == 'L') {
                    landQueue.offer(new Coordinate(i,j));
                }
            }
        }

        // perform BFS on each coordinate in landQueue
        // if not visited, perform BFS and increment counter, else continue
        while(landQueue.size() != 0) {
            Coordinate s = landQueue.poll();
            if(visitedArray[s.getX()][s.getY()] == 0) {
                minIslands++;
                BFS(s);
            }
        }
        writer.println(minIslands);
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
