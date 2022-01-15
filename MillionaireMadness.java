/*
PSEUDOCODE
1. Create a Coordinate class with Comparable
2. Perform BFS at source from entrance to coin
   2.1. add source into priority queue, BFS(0,0,0)
   2.2. add the neighbours of current coordinate
        2.2.1. the height value to be added would the edge from current coordinate to neighbour
   2.3. In while loop, only mark coordinate as visited when it is dequed!
      2.4.1 break out of loop when coin is reached
        2.4.1.1. return the minHeight of the current coordinate
3. In main method, populate 2 2D arrays.
   3.1 visitedArray with 0
   3.2 intArray with input values
   3.3 print out minHeight after calling BFS
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class MillionaireMadness {
    public static int[][] visitedArray;
    public static int[][] intArray;
    public static int minHeight;

    public static class Coordinate implements Comparable<Coordinate> {
        private int x;
        private int y;
        private int height;

        public Coordinate(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public int compareTo(Coordinate other) {
            // ascending order
            // smaller -1(left). this < other
            // bigger 1(right). this > other
            // 0. this == other
            if (this.height > other.height) {
                return 1;
            } else if (this.height < other.height) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void BFS(Coordinate z) {
        PriorityQueue<Coordinate> pq = new PriorityQueue<>();
        pq.offer(z); // add source into priority queue
        visitedArray[z.getX()][z.getY()] = 1; // mark as visited

        while(!pq.isEmpty()) {
            // Deque
            Coordinate c = pq.poll();
            visitedArray[c.getX()][c.getY()] = 1;

            // break out of loop when coin is reached
            if(c.getX() == intArray.length - 1 && c.getY() == intArray[0].length - 1) {
                minHeight = c.getHeight(); // store the min height of ladder
                break;
            }

            // add valid left neighbour
            if(c.getX() > 0 && visitedArray[c.getX() - 1][c.getY()] == 0) {
                pq.offer(new Coordinate(c.getX() - 1,
                        c.getY(),
                        Math.max(c.height, intArray[c.getX() - 1][c.getY()] - intArray[c.getX()][c.getY()])));
            }

            // add valid right neighbour
            if(c.getX() < intArray.length - 1 && visitedArray[c.getX() + 1][c.getY()] == 0) {
                pq.offer(new Coordinate(c.getX() + 1,
                        c.getY(),
                        Math.max(c.height, intArray[c.getX() + 1][c.getY()] - intArray[c.getX()][c.getY()])));
            }

            // add valid top neighbour
            if(c.getY() > 0 && visitedArray[c.getX()][c.getY() - 1] == 0) {
                pq.offer(new Coordinate(c.getX(),
                        c.getY() - 1,
                        Math.max(c.height, intArray[c.getX()][c.getY() - 1] - intArray[c.getX()][c.getY()])));
            }

            // add valid bottom neighbour
            if(c.getY() < intArray[0].length - 1 && visitedArray[c.getX()][c.getY() + 1] == 0) {
                pq.offer(new Coordinate(c.getX(),
                        c.getY() + 1,
                        Math.max(c.height, intArray[c.getX()][c.getY() + 1] - intArray[c.getX()][c.getY()])));
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

        // initialise a int array
        intArray = new int[row][col];

        // populate character array with input
        for (int i = 0; i < row; i++) {
            String numStr = br.readLine();
            String[] numStrArr = numStr.split("\\s+");
            for (int j = 0; j < col; j++) {
                intArray[i][j] = Integer.parseInt(numStrArr[j]);
                visitedArray[i][j] = 0; // populate with 0
            }
        }

        BFS(new Coordinate(0,0,0));
        writer.println(minHeight);
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
