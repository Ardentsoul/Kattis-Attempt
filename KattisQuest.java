/*
PSEUDOCODE
1. Create a Pair class with 3 attributes(energy, gold, id) with a Comparable reverse sorted
2. Add every Pair into a treeSet
3. Create a baseline Pair so that its ceiling(because reverse sorted) will give the largest Pair in the treeSet
    See comments below
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class KattisQuest {
    public static class Pair implements Comparable<Pair>{
        private int energy;
        private int gold;
        private int id;

        public Pair(int energy, int gold, int id) {
            this.energy = energy;
            this.gold = gold;
            this.id= id;
        }
        @Override
        public int compareTo(Pair other) {
            // reverse order
            // smaller -1(left). this > other
            // bigger 1(right). this < other
            // 0. this == other
            if(this.energy > other.energy) {
                return -1;
            } else if(this.energy < other.energy) {
                return 1;
            } else {
                if(this.gold > other.gold) {
                    return -1;
                } else if(this.gold < other.gold) {
                    return 1;
                } else {
                    return Integer.compare(this.id, other.id);
                }
            }
        }

        public int getEnergy() {
            return energy;
        }

        public int getGold() {
            return gold;
        }

        public int getId() {
            return id;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String inputs = br.readLine();
        String[] strs = inputs.trim().split("\\s+");
        int numOfCommands = Integer.parseInt(strs[0]);

        // Create a tree
        TreeSet<Pair> listOfQuests = new TreeSet<>();

        int energyForSession = 0;
        for (int i = 0; i < numOfCommands; i++) {
            String info = br.readLine();
            String[] strs1 = info.trim().split("\\s+");
            String command = strs1[0];

            if(command.equals("add")) {
                int energyConsumption = Integer.parseInt(strs1[1]);
                int goldReward = Integer.parseInt(strs1[2]);
                Pair questItem = new Pair(energyConsumption, goldReward, i);
                listOfQuests.add(questItem);
            } else { // command.equals("query")
                energyForSession = Integer.parseInt(strs1[1]);
                long goldEarned = 0;

                while(true) {
                    int currGold = Integer.MAX_VALUE; // max value so that ceiling will give largest gold baseline
                    int currId = -1; // min value so that all Pair id's will be returned
                    Pair currPair = new Pair(energyForSession, currGold, currId);

                    Pair currQuest = listOfQuests.ceiling(currPair); // this will give the largest available Pair
                    if(currQuest == null) {
                        break;
                    } else {
                        energyForSession -= currQuest.getEnergy();
                        goldEarned += currQuest.getGold();
                        listOfQuests.remove(currQuest);
                    }
                }

                writer.println(goldEarned);
            }
        }
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
