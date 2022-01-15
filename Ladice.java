/*
PSEUDOCODE
1. Create a UnionFind class 
2. Modify UnionFind constructor to set the size for each disjoint set to 1
3. Add to additional methods getSize [gives the size of the parent of the disjoint set(drawer)] &
   addToParent [decrements the size of the parent]
4. For the unionSet method, combine the two disjoint sets together &
   set the size of the parent for firstSet to be the total size for both sets and secondSet to 0
5. Under main method, handle offset (- 1) when calling UnionFind Object with any method since 0 based indexing for array
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ladice {
    /*
    Barebones Union-Find Disjoint Sets implementation,
    using both path compression and union by rank heuristics from Lecture 11
     */
    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] rank;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1; // how many remaining items can be put into the drawer
                rank[i] = 0;
            }
        }

        public int findSet(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                parent[i] = findSet(parent[i]);
                return parent[i];
            }
        }

        public boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int x = findSet(i);
                int y = findSet(j);
                // combine the two disjoint sets together
                // set the size of the parent for firstSet to be the total size for both sets and secondSet to 0
                if(rank[x] >= rank[y]) {
                    size[x] += size[y];
                    size[y] = 0;
                    parent[y] = x; // setting y under x
                } else {
                    size[y] += size[x];
                    size[x] = 0;
                    parent[x] = y; // setting x under y
                }
            }
        }

        // gives the size of the parent of the disjoint set(drawer)
        public int getSize(int i) {
            return size[findSet(i)];
        }

        public void addToParent(int i) {
            size[findSet(i)]--;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String inputs = br.readLine();
        String[] strs = inputs.split(" ");
        int numOfItems = Integer.parseInt(strs[0]);
        int numOfDrawers = Integer.parseInt(strs[1]);

        UnionFind UF = new UnionFind(numOfDrawers);
        for (int i = 0; i < numOfItems; i++) {
            String info = br.readLine();
            String[] strs1 = info.split(" ");
            int a = Integer.parseInt(strs1[0]) - 1; // handle offset since 0 based indexing for array
            int b = Integer.parseInt(strs1[1]) - 1; // handle offset since 0 based indexing for array

            UF.unionSet(a, b);

            if(UF.getSize(a) != 0) {
                UF.addToParent(a);
                writer.println("LADICA");
            } else {
                writer.println("SMECE");
            }
        }
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
