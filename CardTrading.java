/*
PSEUDOCODE
1. Create a Card class with 4 attributes(quantity, type, buyValue, sellValue) with a Comparable
2. Read the first line of input and store inputs N, T, K into an array
3. Read the second line of input and store the cardType for each element into an array
4. Create an empty ArrayList to store an array of Cards for future steps
5. Read the subsequent inputs lines to retrieve the buy and sell values for each type of card starting from type 1
6. While reading these input lines, store the information of the 4 attributes into card arraylist. Quantity set to 0
7. Increase the quantity in the respective cardObj if card type is present using a for loop
8. Calculate the lost profit based on each card
   Lost profit = how much money spent in completing a pair + how much money lost for not selling cards
9. Sort the card array in ascending order based on lost profit
10. Calculate the amount required to buy to complete K combos
11. Calculate the amount that can be earned by selling the rest of the cards
12. Subtract 10 from 11 to get final profit
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class CardTrading {
    public static class Card implements Comparable<Card> {
        private int quantity;
        private int type;
        private long buyValue;
        private long sellValue;

        public Card(int quantity, int type, long buyValue, long sellValue) {
            this.quantity = quantity;
            this.type = type;
            this.buyValue = buyValue;
            this.sellValue = sellValue;
        }

        public int getQuantity() {
            return quantity;
        }

        public long getBuyValue() {
            return buyValue;
        }

        public long getSellValue() {
            return sellValue;
        }

        public void incrQuantity() {
            this.quantity += 1;
        }

        // calculate the lost profit
        public long lostProfit() {
            if(this.quantity == 0) {
                return 2 * this.buyValue;
            } else if(this.quantity == 1) {
                return this.buyValue + this.sellValue;
            } else {
                return 2 * this.sellValue;
            }
        }

        // Modify sort to lost profit
        @Override
        public int compareTo(Card other) {
            // smaller -1. this < other
            // bigger 1. this > other
            // 0. this == other
            if(this.lostProfit() < other.lostProfit()) {
                return -1;
            } else if (this.lostProfit() > other.lostProfit()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        /*
        Read first line of input and store into array of size 3
        firstLineInput[0] = Number of cards in deck
        firstLineInput[1] = Number of types of cards in deck
        firstLineInput[2] = Must have K combos in deck (eg:2, 2 pairs; A pair has 2 identical cards of the same type)
        */

        int[] firstLineInput = new int[3];
        String line = br.readLine();
        String[] firstStrArr = line.trim().split("\\s");
        for(int i = 0; i < firstStrArr.length; i++) {
            firstLineInput[i] = Integer.parseInt(firstStrArr[i]);
        }

        // Read second line of input and store into array of size N
        int[] secondLineInput = new int[firstLineInput[0]]; // stores the cardType for each element of the array
        String lineOne = br.readLine();
        String[] secondStrArr = lineOne.trim().split("\\s");
        for (int i = 0; i < secondStrArr.length; i++) {
            // value stored in secondLineInput array is the type of card
            secondLineInput[i] = Integer.parseInt(secondStrArr[i]);
        }

        // Retrieve the buy and sell values for each type of card starting from type 1
        // And store the information into card array
        int numOfCardTypes = firstLineInput[1];
        ArrayList<Card> cardArray = new ArrayList<>(numOfCardTypes); // new empty array of size numOfCardTypes

        for(int i = 1; i <= numOfCardTypes; i++) {
            String lineEach = br.readLine();
            String[] thirdStrArr = lineEach.trim().split("\\s");
            Long buyValue = Long.parseLong(thirdStrArr[0]);
            Long sellValue = Long.parseLong(thirdStrArr[1]);
            Card cardObj = new Card(0, i,buyValue,sellValue);
            cardArray.add(cardObj); // add cardObj into every element of cardArray
        }

        // Increase the quantity in the respective cardObj if card type is present
        // 1. get the value in the secondLineInput
        // 2. take that value - 1 to get the position of the cardArray
        // 3. increment the quantity based on the position
        for(int i = 0; i < secondLineInput.length; i++) {
            int cardArrPos = secondLineInput[i] - 1;
            cardArray.get(cardArrPos).incrQuantity();
        }

        // Sort the cardArray based on lost profit(ascending order)
        Collections.sort(cardArray);

        // calculate the amount required to buy to complete K combos
        long buyAmountToComplete = 0;
        for(int i = 0; i < firstLineInput[2]; i++) {
            long buyValueToAdd =0;
            if(cardArray.get(i).getQuantity() == 0) {
                buyValueToAdd = 2 * cardArray.get(i).getBuyValue();
            } else if(cardArray.get(i).getQuantity() == 1) {
                buyValueToAdd = cardArray.get(i).getBuyValue();
            } else { }// quantity = 2
            buyAmountToComplete += buyValueToAdd;
        }

        // calculate the amount that can be earned by selling the rest of the cards
        long sellAmountToBeEarned = 0;
        for(int i = firstLineInput[2]; i < cardArray.size(); i++) {
            long sellValueToAdd = 0;
            if(cardArray.get(i).getQuantity() == 2) {
                sellValueToAdd = 2 * cardArray.get(i).getSellValue();
            } else if(cardArray.get(i).getQuantity() == 1) {
                sellValueToAdd = cardArray.get(i).getSellValue();
            } else { }
            sellAmountToBeEarned += sellValueToAdd;
        }
        // Print the final profit
        long finalProfit = sellAmountToBeEarned - buyAmountToComplete;
        writer.println(finalProfit);

        // checking purposes
//         for (int i = 0; i < cardArray.size(); i++) {
//            System.out.println(cardArray.get(i).lostProfit());
//        }

        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}