/*
PSEUDOCODE
1. Create a Coordinate class to store src, dst and cannon positions
2. Create a Coordinate array to store the coordinates for source, destination and cannon
3. Use adj Matrix to store 'distance array'. source = 0, the rest of the coordinates = INF
4. Fill in all distances from src to all coordinates(cannons) and get time
5. Get dist launching from cannon and then running to destination
6. Use Floyd to relax the edges (Taken from Prof's code)
7. Print the shortest path(time)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HumanCannonBallRun {
    public static final float INF = 1000000000;
    public static float square(float n) {
        return n*n;
    }

    public static float distance(Coordinate a, Coordinate b) {
        return (float) Math.sqrt(square(a.x - b.x) + square(a.y - b.y));
    }

    public static class Coordinate {
        private float x;
        private float y;

        public Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String srcCoordinates = br.readLine();
        String[] srcCoordinatesArr = srcCoordinates.split(" ");
        float srcXCoordinate = Float.parseFloat(srcCoordinatesArr[0]);
        float srcYCoordinate = Float.parseFloat(srcCoordinatesArr[1]);

        String dstCoordinates = br.readLine();
        String[] dstCoordinatesArr = dstCoordinates.split(" ");
        float dstXCoordinate = Float.parseFloat(dstCoordinatesArr[0]);
        float dstYCoordinate = Float.parseFloat(dstCoordinatesArr[1]);

        String input = br.readLine();
        int numOfCannons = Integer.parseInt(input);

        Coordinate[] coordinateArr = new Coordinate[numOfCannons + 2]; // factor in src and dst
        coordinateArr[0] = new Coordinate(srcXCoordinate, srcYCoordinate); // src
        coordinateArr[numOfCannons + 1] = new Coordinate(dstXCoordinate, dstYCoordinate); // dst

        // get all coordinates of cannons and populate coordinateArr
        for (int i = 1; i <= numOfCannons; i++) {
            String cannonCoordinates = br.readLine();
            String[] cannonCoordinatesArr = cannonCoordinates.split(" ");
            float cannonXCoordinate = Float.parseFloat(cannonCoordinatesArr[0]);
            float cannonYCoordinate = Float.parseFloat(cannonCoordinatesArr[1]);
            coordinateArr[i] = new Coordinate(cannonXCoordinate, cannonYCoordinate);
        }

        // graph DS use adj matrix
        float[][] adjMatrix = new float[numOfCannons + 2][numOfCannons + 2];
        for (int i = 0; i < numOfCannons + 2; i++) {
            for (int j = 0; j < numOfCannons + 2; j++) {
                if(i == 0 && j == 0) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = INF;
                }
            }
        }

        // fill in all distances from src to all coordinates(cannons) and get time
        for (int i = 1; i < numOfCannons + 2; i++) {
            adjMatrix[0][i] = distance(coordinateArr[0], coordinateArr[i]) / 5;
        }

        // dist launching from cannon and then running to dest
        for (int i = 1; i < numOfCannons + 1; i++) {
            for (int j = 1; j < numOfCannons + 2; j++) {
                adjMatrix[i][j] = Math.min(2 + Math.abs((distance(coordinateArr[i], coordinateArr[j])- 50) / 5),
                        distance(coordinateArr[i], coordinateArr[j]) / 5);
            }
        }

        // Use Floyd to relax the edges (Prof's code on Floyd)
        for (int k = 0; k < numOfCannons + 2; k++) // k first(by slide 9 lecture 20)
            for (int i = 0; i < numOfCannons + 2; i++)
                for (int j = 0; j < numOfCannons + 2; j++)
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);

        writer.println(adjMatrix[0][numOfCannons + 1]);

        writer.flush();
        writer.close();
        br.close();
        r.close();
    }
}
