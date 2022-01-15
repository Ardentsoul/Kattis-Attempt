/*
PSEUDOCODE
1. Make a CircularDeque class
2. Make a Treque class consisting of 2 circular deque classes
3. Implement the respective methods in each of the static classes
4. Take in the inputs and print
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Teque {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String totalInputs = br.readLine();
        int totalInputsNum = Integer.parseInt(totalInputs);
        // initialise the treque
        Treque t = new Treque(totalInputsNum);

        for (int i = 0; i < totalInputsNum; i++) {
            String lines = br.readLine();
            String[] strs = lines.trim().split("\\s+");
            if(strs[0].equals("push_back")) {
                t.pushBack(Integer.parseInt(strs[1]));
            } else if(strs[0].equals("push_front")) {
                t.pushFront(Integer.parseInt(strs[1]));
            } else if(strs[0].equals("push_middle")) {
                t.pushMiddle(Integer.parseInt(strs[1]));
            } else {
                int x = t.get(Integer.parseInt(strs[1]));
                writer.println(x);
            }
        }
        writer.flush();
        writer.close();
        br.close();
        r.close();
    }

    public static class CircularDeque {
        private int[] intArr;
        private int frontIndex = -1;
        private int backIndex = -1;
        private int size = 0;

        public CircularDeque(int length) {
            intArr = new int[length];
        }

        public int dequeueFront() {
            int temp = intArr[frontIndex];
            if(frontIndex == intArr.length - 1) { // front index at the end of the queue
                frontIndex = 0;
            } else {
                frontIndex++;
            }
            size--;
            return temp;
        }

        public int dequeueBack() {
            int temp = intArr[backIndex];
            if(backIndex == 0) { // back index at the front of the queue
                backIndex = intArr.length - 1;
            } else {
                backIndex--;
            }
            size--;
            return temp;
        }

        public void enqueueFront(int value) {
            if(frontIndex == -1 && backIndex == -1) { // nothing in array
                frontIndex = 0;
                backIndex = 0;
                intArr[frontIndex] = value;
            } else if(frontIndex == 0) { // set front to length - 1
                frontIndex = intArr.length - 1;
                intArr[frontIndex] = value;
            } else {
                frontIndex--;
                intArr[frontIndex] = value;
            }
            size++;
        }

        public void enqueueBack(int value) {
            if(frontIndex == -1 && backIndex == -1) { // nothing in array
                frontIndex = 0;
                backIndex = 0;
                intArr[backIndex] = value;
            } else if(backIndex == intArr.length - 1) { // set back to 0
                backIndex = 0;
                intArr[backIndex] = value;
            } else {
                backIndex++;
                intArr[backIndex] = value;
            }
            size++;
        }

        public int get(int index) {
            return intArr[(index + frontIndex) % intArr.length];
        }
    }

    public static class Treque {
        private CircularDeque leftCircularDeque;
        private CircularDeque rightCircularDeque;

        public Treque(int length) {
            this.leftCircularDeque = new CircularDeque(length);
            this.rightCircularDeque = new CircularDeque(length);
        }

        public int get(int index) {
            if(index < leftCircularDeque.size) {
                return leftCircularDeque.get(index);
            } else {
                return rightCircularDeque.get(index - leftCircularDeque.size);
            }
        }

        public void pushBack(int value) {
            rightCircularDeque.enqueueBack(value);
            balance();
        }

        public void pushFront(int value) {
            leftCircularDeque.enqueueFront(value);
            balance();
        }

        public void pushMiddle(int value) {
            // left > right
            if(leftCircularDeque.size - rightCircularDeque.size == 1) {
                rightCircularDeque.enqueueFront(value);
            } else if(leftCircularDeque.size - rightCircularDeque.size == 0) { // left == right
                leftCircularDeque.enqueueBack(value);
            }
        }

        public void balance() {
            if(leftCircularDeque.size - rightCircularDeque.size > 1) {
                // dequeue the end of leftqueue and insert to front of rightqueue
                int temp = leftCircularDeque.dequeueBack();
                rightCircularDeque.enqueueFront(temp);
            } else if(rightCircularDeque.size - leftCircularDeque.size > 0) {
                // dequeue the front of rightqueue and insert to back of leftqueue
                int temp = rightCircularDeque.dequeueFront();
                leftCircularDeque.enqueueBack(temp);
            }
        }
    }
}
