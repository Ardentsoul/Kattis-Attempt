/*
PSEUDOCODE
1. Implement a Team class for future balancing in the AVLTree
2. In the main method,
   create an AVL Tree to sort the teams based problemsSolved and penalties, and create an array of teams
   populate the array with team details first
   insert team 1 into the AVLTree so that it will always be in the tree
   for each event ->
    Delete the vertice of team t in the AVLTree if exists
    Update the values of team t in the array
    insert the vertice of team t into the AVLTree
    get the rank of the first team
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GCPC {
    public static class Team implements Comparable<Team>{
        private int teamNumber;
        private int problemsSolved;
        private int penalty;

        public Team(int teamNumber, int problemsSolved, int penalty) {
            this.teamNumber = teamNumber;
            this.problemsSolved = problemsSolved;
            this.penalty = penalty;
        }

        public int incrProblemsSolved() {
            return this.problemsSolved += 1;
        }

        public int incrPenalty(int val) {
            return this.penalty += val;
        }

        @Override
        public int compareTo(Team other) {
            // descending order
            // smaller -1(left). this > other
            // bigger 1(right). this < other
            // 0. this == other
            if(this.problemsSolved > other.problemsSolved) {
                return -1;
            } else if(this.problemsSolved < other.problemsSolved) {
                return 1;
            } else { // ascending order
                if(this.penalty > other.penalty) {
                    return 1;
                } else if(this.penalty < other.penalty) {
                    return -1;
                } else {
                    return Integer.compare(this.teamNumber, other.teamNumber);
                }
            }
        }
    }

    public static class AVLVertex {
        public AVLVertex parent, left, right;
        public Team key;
        public int height;
        public int size;

        AVLVertex(Team t) {
            key = t;
            parent = left = right = null;
            height = 0;
            size = 1;
        }

        public void updateHeight() {
            if(this.left == null && this.right == null) { // no child or leaf
                this.height = 0;
            } else {
                if(this.left != null && this.right == null) { // have left child
                    this.height = this.left.height + 1;
                } else if(this.right != null && this.left == null) { // have right child
                    this.height = this.right.height + 1;
                } else { // have both left and right child
                    this.height = Math.max(this.left.height, this.right.height) + 1;
                }
            }
        }

        public void updateSize() {
            if(this.left == null && this.right == null) { // no child or leaf
                this.size = 1;
            } else {
                if(this.left != null && this.right == null) { // have left child
                    this.size = this.left.size + 1;
                } else if(this.right != null && this.left == null) { // have right child
                    this.size = this.right.size + 1;
                } else { // have both left and right child
                    this.size = this.left.size + this.right.size + 1;
                }
            }
        }
    }

    public static class AVL {
        public AVLVertex root;
        public AVL() {
            root = null;
        }

        // public method called to insert a new key with value v into AVL
        public void insert(Team team) {
            root = insert(root, team);
        }

        // helper recursive method to perform insertion of new vertex into AVL
        public AVLVertex insert(AVLVertex T, Team team) {
            if (T == null) {
                return new AVLVertex(team); // insertion point is found
            }
            if (team.compareTo(T.key) > 0) { // team > vertice (insert to the right)
                T.right = insert(T.right, team);
                T.right.parent = T;
            } else { // team <= vertice (insert to the left)
                T.left = insert(T.left, team);
                T.left.parent = T;
            }
            T.updateSize();
            T.updateHeight();
            T = balanceAVL(T);
            return T; // return the updated AVL
        }

        // public method to delete a vertex containing key with value v from BST
        public void delete(Team team) {
            root = delete(root, team);
        }

        // helper recursive method to perform deletion
        public AVLVertex delete(AVLVertex T, Team team) {
            if (T == null) {
                return null; // cannot find the item to be deleted
            }
            if (team.compareTo(T.key) > 0) { // team > vertice (delete the right)
                T.right = delete(T.right, team);
            } else if (team.compareTo(T.key) < 0) { // team < vertice (delete the left)
                T.left = delete(T.left, team);
            } else { // this is the node to be deleted
                if (T.left == null && T.right == null) { // this is a leaf
                    T = null; // simply erase this node
                } else if (T.left == null && T.right != null) { // only one child at right
                    T.right.parent = T.parent;
                    T = T.right; // bypass T
                } else if (T.left != null && T.right == null) { // only one child at left
                    T.left.parent = T.parent;
                    T = T.left; // bypass T
                } else { // has two children, find successor
                    Team successorV = findMin(T.right);
                    T.key = successorV; // replace this key with the successor's key
                    T.right = delete(T.right, successorV); // delete the old successorV
                }
            }
            if(T != null) {
                T.updateSize();
                T.updateHeight();
                T = balanceAVL(T);
            }
            return T; // return the updated AVL
        }

        // helper method to perform findMin
        public Team findMin(AVLVertex T) {
            if(T == null) {
                return null;
            } else if (T.left == null) {
                return T.key; // this is the min
            } else {
                return findMin(T.left); // go to the left
            }
        }

        public AVLVertex rotateLeft(AVLVertex T) {
            if(T == null) {
                return null;
            }
            if(T.right != null) {
                AVLVertex w = T.right;
                w.parent = T.parent; // change parent pointer
                T.parent = w;
                T.right = w.left;
                if(w.left != null) {
                    w.left.parent = T;
                }
                w.left = T;
                T.updateHeight();
                T.updateSize();
                w.updateHeight();
                w.updateSize();
                return w;
            }
            return null;
        }

        public AVLVertex rotateRight(AVLVertex T) {
            if(T == null) {
                return null;
            }
            if(T.left != null) {
                AVLVertex w = T.left;
                w.parent = T.parent; // change parent pointer
                T.parent = w;
                T.left = w.right;
                if(w.right != null) {
                    w.right.parent = T;
                }
                w.right = T;
                T.updateHeight();
                T.updateSize();
                w.updateHeight();
                w.updateSize();
                return w;
            }
            return null;
        }

        public int balanceFactor(AVLVertex avl) {
            if(avl == null) {
                return 0;
            } else {
                int leftHeight = -1;
                int rightHeight = -1;
                if(avl.left != null && avl.right == null) { // have left child
                    leftHeight = avl.left.height;
                } else if(avl.right != null && avl.left == null) { // have right child
                    rightHeight = avl.right.height;
                } else if(avl.right != null && avl.left != null) { // have 2 child
                    leftHeight = avl.left.height;
                    rightHeight = avl.right.height;
                }
                return leftHeight - rightHeight; // determine whether height balanced
            }
        }

        public AVLVertex balanceAVL(AVLVertex T) {
            int bf = balanceFactor(T);
            int bfLeft = 0;
            int bfRight = 0;

            if(T != null) {
                bfLeft = balanceFactor(T.left);
                bfRight = balanceFactor(T.right);
            }

            if(bf == 2 && bfLeft >= 0 && bfLeft <= 1) {
                T = rotateRight(T);
            } else if(bf == 2 && bfLeft == -1) {
                T.left = rotateLeft(T.left);
                T = rotateRight(T);
            } else if(bf == -2 && bfRight >= -1 && bfRight <= 0) {
                T = rotateLeft(T);
            } else if(bf == -2 && bfRight == 1) {
                T.right = rotateRight(T.right);
                T = rotateLeft(T);
            }
            return T;
        }

        public int rank(Team team) {
            return rank(root, team);
        }

        public int rank(AVLVertex V, Team team) {
            if(team.compareTo(V.key) == 0) {
                int leftSize = 0; // null check whether have left child
                if (V.left != null) {
                    leftSize = V.left.size;
                }
                return leftSize + 1;
            } else if(team.compareTo(V.key) > 0) { // on the right
                int leftSize = 0; // null check whether have left child
                if (V.left != null) {
                    leftSize = V.left.size;
                }
                return leftSize + rank(V.right, team) + 1;
            } else {
                return rank(V.left, team);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        String inputs = br.readLine();
        String[] strs = inputs.split("\\s+");
        int numOfTeams = Integer.parseInt(strs[0]);
        int numOfEvents = Integer.parseInt(strs[1]);

        // Create an AVL Tree and array of teams
        AVL AVLTree = new AVL();
        Team[] teamArr = new Team[numOfTeams + 1];
        // populate the array with team details first
        for (int i = 1; i <= numOfTeams; i++) {
            teamArr[i] = new Team(i, 0, 0);
        }

        // insert team 1 into the AVLTree so that it will always be in the tree
        AVLTree.insert(teamArr[1]);

        for (int i = 0; i < numOfEvents; i++) {
            String info = br.readLine();
            String[] strs1 = info.split("\\s+");
            int t = Integer.parseInt(strs1[0]);
            int p = Integer.parseInt(strs1[1]);

            // Delete the vertice of team t in the AVLTree if exists
            AVLTree.delete(teamArr[t]);

            // Update the values of team t in the array
            teamArr[t].incrProblemsSolved();
            teamArr[t].incrPenalty(p);

            // insert the vertice of team t into the AVLTree
            AVLTree.insert(teamArr[t]);

            // get the rank of the first team
            int teamOneRank = AVLTree.rank(teamArr[1]);
            writer.println(teamOneRank);
        }
        br.close();
        r.close();
        writer.flush();
        writer.close();
    }
}
