/*
PSEUDOCODE
1. This is a Kruskal MST problem 
2. Create a Road class which has the start, end and distance
3. Copy over code from Prof's lecture
4. Create a UFDS class
5. Store the edges in an arraylist and sort it in ascending distance
6. Process the edges, if no cycle, union and print the start and end. else continue
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class LostMap {

    public static class Road implements Comparable<Road> {
        private int start;
        private int end;
        private int distance;

        public Road(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road other) {
            // ascending order
            // smaller -1(left). this < other
            // bigger 1(right). this > other
            // 0. this == other
            if (this.distance > other.distance) {
                return 1;
            } else if (this.distance < other.distance) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    // UFDS
    public static class UnionFind {
        public int[] p;
        public int[] rank;
        public int[] setSize;
        public int numSets;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            setSize = new int[N];
            numSets = N;
            for (int i = 0; i < N; i++) {
                p[i] = i;
                rank[i] = 0;
                setSize[i] = 1;
            }
        }

        public int findSet(int i) {
            if (p[i] == i) return i;
            else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                numSets--;
                int x = findSet(i), y = findSet(j);
                // rank is used to keep the tree short
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    setSize[x] = setSize[x] + setSize[y];
                }
                else {
                    p[x] = y;
                    setSize[y] = setSize[x] + setSize[y];
                    if (rank[x] == rank[y])
                        rank[y] = rank[y]+1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int numOfVillages = Integer.parseInt(br.readLine());

        ArrayList<Road> EdgeList = new ArrayList <>();
        for (int i = 0; i < numOfVillages; i++) { // simply populate this EdgeList
            // we decrease index by 1 to change input to 0-based indexing
            String inputs = br.readLine();
            String[] strs = inputs.split(" ");
            for (int j = 0; j < numOfVillages; j++) {
                EdgeList.add(new Road(i, j, Integer.parseInt(strs[j]))); // we store this information as (w, u, v)
            }
        }

        Collections.sort(EdgeList);
        UnionFind UF = new UnionFind(numOfVillages); // all V are disjoint sets at the beginning
        for (int i = 0; i < EdgeList.size(); i++) { // process all edges, O(E)
            Road e = EdgeList.get(i);
            int u = e.start, v = e.end; // note that we have re-ordered the roads
            if (!UF.isSameSet(u, v)) { // if no cycle
                UF.unionSet(u, v); // link these two vertices
                writer.println((u + 1) + " " + (v + 1));
            }
        }
        writer.flush();
        writer.close();
        br.close();
        r.close();
    }
}
