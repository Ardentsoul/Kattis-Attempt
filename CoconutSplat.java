/*
PSEUDOCODE
1. Create a Player class with state and PlayerId
2. Create an empty deque of Players
3. Fill up the deque with Players
4. While deque.size() > 1, make the head of the queue to be the player to be last touched and update the head
    Do this by removing the head and inserting it to the back of the deque
    Check the state of the head of the deque and update accordingly
    if state == 0, store the PlayerId of the head and remove it. Then add to head of queue with state == 1
    if state == 1, store the PlayerId of the head and remove it. Then add to back of queue (counting is different)
    if state == 2, remove the head from the deque.
5. When deque.size == 1, then peek the playerId of the head and print.
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class CoconutSplat {
    public static class Player {
        private int state;
        private int playerId;

        public Player(int state, int playerId) {
            // state = 0 means folded hands
            // state = 1 means fists
            // state = 2 means palm down
            this.state = state;
            this.playerId = playerId;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfSyllables = sc.nextInt();
        int numOfPlayers = sc.nextInt();

        // first create an empty deque of players
        Deque<Player> playerQueue = new LinkedList<>();

        // create the first instance of deque with Players
        for(int i = 1; i <= numOfPlayers; i++) {
            playerQueue.offer(new Player(0, i));
        }

        while(playerQueue.size() > 1) {
            // make the head of the queue to be the player to be last touched
            for (int i = 0; i < numOfSyllables - 1; i++) {
                Player temp = playerQueue.poll(); // remove head and store in var
                playerQueue.offerLast(temp); // add to back of queue
            }
            // update head of queue after for loop
            Player headOfQueue = playerQueue.getFirst();

            // when head of queue is in folded arms position
            if(headOfQueue.state == 0) {
                int id = headOfQueue.playerId; // store the player id of the head of queue
                playerQueue.removeFirst(); // remove the head of the queue
                playerQueue.offerFirst(new Player(1,  id)); // add to head of queue
                playerQueue.offerFirst(new Player(1,  id)); // add to head of queue
            }

            // when head of queue is in fist position
            else if(headOfQueue.state == 1) {
                int id = headOfQueue.playerId; // store the player id of the head of queue
                playerQueue.removeFirst(); // remove the head of the queue
                playerQueue.offerLast(new Player(2,  id)); // add to back of queue (counting is different)
            }

            // when head of queue is in palm down position
            else if(headOfQueue.state == 2) {
                playerQueue.poll(); // remove from queue
            }
        }
        int finalPlayerId = playerQueue.peek().playerId;
        System.out.println(finalPlayerId);
    }
}
