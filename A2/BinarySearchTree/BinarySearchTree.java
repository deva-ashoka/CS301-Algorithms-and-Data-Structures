import java.io.*;
import java.lang.Integer;

class Node {
    public int key;//This denotes the key stored at the node
    public String value;//This denotes the value stored in the node.
    public Node leftChild;//This is a reference to the left child in the binary tree
    public Node rightChild;//This is a reference to the right child in the binary tree
    public Node parent;//This is a reference to the parent of the node in the binary tree.

    public Node() {
        parent = leftChild = rightChild = null;
    }
}

public class BinarySearchTree {
    public Node root;
    public int size;

    //This is the initialization method for Binary Search Tree.
    public BinarySearchTree() {

        size = 0;
        //root = null;
    }

    //This method checks if the given node is a leaf
    public boolean isLeaf(Node N) {
        if (N.leftChild == null && N.rightChild == null) return (true);
        else return (false);
    }


    //This method implements the get method discussed in class.
    public String get(int k) {
        Node P = root;
        while (!isLeaf(P)) {
            if (P.key == k) {
                return P.value;
            } else {
                if (k > P.key) {
                    P = P.rightChild;
                } else {
                    P = P.leftChild;
                }
            }
        }
        return "Does not exist";
    }

    //This method implements the put method discussed in class.
    public String put(int k, String v) {
        if (size == 0) {
            Node P = new Node();
            P.key = k;
            P.value = v;
            root = P;

            Node pLeft = new Node();
            P.leftChild = pLeft;
            pLeft.parent = P;

            Node pRight = new Node();
            P.rightChild = pRight;
            pRight.parent = P;

            size++;
            return "Inserted root";
        }
        Node P = root;
        while (!isLeaf(P)) {
            if (k == P.key) {
                String temp = P.value;
                P.value = v;
                return temp;
            }
            if (k > P.key) {
                P = P.rightChild;
            } else {
                P = P.leftChild;
            }
        }
        P.key = k;
        P.value = v;

        Node pLeft = new Node();
        P.leftChild = pLeft;
        pLeft.parent = P;

        Node pRight = new Node();
        P.rightChild = pRight;
        pRight.parent = P;
        size++;
        return "Inserted";
    }

    //This method implements the remove method discussed in class
    public void remove(int k) {
        Node P = root;
        while (!isLeaf(P)) {
            if (P.key == k) {
                deleteNode(P);
                break;
            } else {
                if (k > P.key) {
                    P = P.rightChild;
                } else {
                    P = P.leftChild;
                }
            }
        }
    }

    public void deleteNode(Node P) {
        if(isLeaf(P.leftChild) && isLeaf(P.rightChild)){
            Node newP = new Node();
            Node pParent = P.parent;
            if(pParent.leftChild == P){
                pParent.leftChild = newP;
                newP.parent = pParent;
            }
            if(pParent.rightChild == P){
                pParent.rightChild = newP;
                newP.parent = pParent;
            }
        }
        if (isLeaf(P.leftChild) && !isLeaf(P.rightChild)) {
            P = P.rightChild;
        }
        if (!isLeaf(P.leftChild) && !isLeaf(P.rightChild)) {
            Node temp = P.leftChild;
            while (!isLeaf(temp.rightChild)) {
                temp = temp.rightChild;
            }
            P.key = temp.key;
            P.value = temp.value;
            deleteNode(temp);
        }
    }
}