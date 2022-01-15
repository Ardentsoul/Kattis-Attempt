/*
PSEUDOCODE
1. Create own custom LinkedList (details below)
2. Create an empty arraylist of newLinkedList
3. Insert the nodes into each LL in the array except index 0
4. Take in input for number combination
5. Make the first value point to the second
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JoinStrings {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        String totalStringInputs = br.readLine();
        int totalStringInputsNum = Integer.parseInt(totalStringInputs);

        // if N == 1
        if(totalStringInputsNum == 1) {
            String onlyString = br.readLine();
            System.out.println(onlyString);
            return;
        }

        // create an empty array of newLinkedList
        ArrayList<NewLinkedList<String>> newLinkedListArr = new ArrayList<>(totalStringInputsNum + 1);
        // make each element an empty LL first
        for (int i = 0; i <= totalStringInputsNum; i++) {
            newLinkedListArr.add(i, new NewLinkedList<>());
        }

        // insert the nodes into each LL in the array except index 0
        for (int i = 1; i < newLinkedListArr.size(); i++) {
            newLinkedListArr.get(i).addToStart(br.readLine());
        }

        // take in input for number combination
        for (int i = 0; i < totalStringInputsNum - 1; i++) {
            String lines = br.readLine();
            String[] strs = lines.trim().split("\\s+");
            int firstStr = Integer.parseInt(strs[0]);
            int secondStr = Integer.parseInt(strs[1]);

            // make the first value point to the second
            newLinkedListArr.get(firstStr).appendToEnd(newLinkedListArr.get(secondStr));

            if(i == totalStringInputsNum - 2) {
                newLinkedListArr.get(firstStr).printString();
            }
        }
        br.close();
        r.close();
    }

    // create own custom LinkedList
    public static class NewLinkedList<T> {
        // attributes
        private Node<T> head;
        private Node<T> tail;

        // create a node
        public static class Node<T> {
            T data;
            Node<T> next;

            public Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        public void addToStart(T string) {
            Node<T> newNode = new Node<>(string); // create new node with string
            head = newNode; // update head pointer to the start of the LinkedList
            tail = newNode; // update tail pointer to the end of the LinkedList
        }

        public void appendToEnd(NewLinkedList<T> nextLL) {
            tail.next = nextLL.head; // link to nextLL.head
            tail = nextLL.tail; // update tail pointer to nextLL.tail
        }

        public void printString() {
            PrintWriter writer = new PrintWriter(System.out);
            Node<T> temp = head;
            while(temp != null) {
                writer.write(temp.data.toString());
                temp = temp.next;
            }
            writer.flush();
            writer.close();
        }
    }
}
