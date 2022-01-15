/*
PSEUDOCODE
1. Make a method to check whether there exists a triangle in the graph
2. if there is an edge, check the respective vertices in the adjMatrix == 1
3. iterate through the rest of the columns in the row
4. return true if neighbours(Refer to paint drawing)
5. In main method,
   create a 2D square matrix of order numOfVertices
   insert the values into the adjMatrix
   run isTriangle method and print out when false
 */


import java.util.Scanner;

public class WeakVertices {
    public static boolean isTriangle(int[][] matrix, int row) {
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[row][i] == 1) { // if there is an edge, check the respective vertices in the adjMatrix == 1
                for (int j = i + 1; j < matrix.length; j++) { // iterate through the rest of the columns in the row
                    if(matrix[i][j] == 1 && matrix[j][row] == 1) { // neighbours(Refer to paint drawing)
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 100; i++) {
            int numOfVertices = sc.nextInt();

            // input -1 means break
            if(numOfVertices == -1) {
                break;
            }

            // create a 2D square matrix of order numOfVertices
            int[][] adjMatrix = new int[numOfVertices][numOfVertices];

            // insert the values into the adjMatrix
            for (int row = 0; row < numOfVertices; row++) {
                for (int column = 0; column < numOfVertices; column++) {
                    adjMatrix[row][column] = sc.nextInt();
                }
            }

            // run isTriangle method and print when false
            for (int j = 0; j < numOfVertices; j++) {
                if(!isTriangle(adjMatrix, j)) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}
