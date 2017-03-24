import java.io.*;
import java.lang.Integer;

class Node {
    public int key;//This denotes the key of the entry stored at the node
    public String value;//This denotes the value of the entry stored at the node
    public Node leftChild;//This is a reference to the left child in the heap
    public Node rightChild;//This is a reference to the right child in the heap
    public Node parent;//This is a reference to the parent of the node in the heap

    public Node() {
        parent = leftChild = rightChild = null;
    }
}

public class MinHeap {
    public Node root;//This is the root node
    public int size;//This is the number of entries in the heap
    public Node lastNode;//This indicates the node where the next insert will take place

    //This is the initialization method for Binary Tree. This reads the tree description
    //from a file named tree.txt and initializes the tree.
    public MinHeap() {
        size = 0;
        root = lastNode = null;
    }

    //This method swaps the key-value pairs stored at given nodes
    public void swapVals(Node p, Node q) {
        String temp1 = p.value;
        int temp2 = p.key;
        p.key = q.key;
        p.value = q.value;
        q.key = temp2;
        q.value = temp1;
    }


    //This is the min() method that returns the minimum element in the heap
    public String min() {
        return (root.value);
    }


    //We maintain the last Node pointer that points to the node where the new insertions are
    //supposed to take place. The incrementLastNode method is executed "after" every insertion.
    //This updates the lastNode pointer
    public void incrementLastNode() {
        if (lastNode == null) {
            lastNode = root;
            return;
        }

        //If the right child of the last node is null, then there is nothing to be done.
        if (lastNode.rightChild == null) return;

        Node p = lastNode;
        while (p.parent != null && p.parent.rightChild == p) p = p.parent;
        if (p.parent != null) p = p.parent.rightChild;
        while (p.leftChild != null) p = p.leftChild;
        lastNode = p;
    }

    //We maintain the last Node pointer that points to the node where the new insertions are
    //supposed to take place. The decrementLastNode method is executed "before" every removeMin() operation.
    //This updates the lastNode pointer
    public void decrementLastNode() {
        //If there is a left child or a right child of the current node, then there is
        //nothing to be done.
        if (lastNode.leftChild != null || lastNode.rightChild != null) return;

        Node p = lastNode;
        while (p.parent != null && p.parent.leftChild == p) p = p.parent;
        if (p.parent != null) p = p.parent.leftChild;
        while (p.rightChild != null) p = p.rightChild;
        lastNode = p.parent;
    }


    //This method returns whether the heap is empty or not
    public boolean isEmpty() {
        if (size == 0) return (true);
        else return (false);
    }


    //This method implements the up-heap bubble
    public void upHeapBubble(Node v) {
        if (v.parent == null) {
            return;
        } else if (v.parent != null) {
            if (v.key < v.parent.key) {
                swapVals(v, v.parent);
                upHeapBubble(v.parent);
            }
        }
    }


    //This method implements the down heap bubble
    public void downHeapBubble(Node v) {
        if (v.leftChild == null && v.rightChild == null) {
            return;
        }
        if (v.leftChild != null && v.rightChild != null) {
            if (v.key > v.rightChild.key || v.key > v.leftChild.key) {
                if (v.leftChild.key < v.rightChild.key) {
                    swapVals(v, v.leftChild);
                    downHeapBubble(v.leftChild);
                } else if (v.rightChild.key <= v.leftChild.key) {
                    swapVals(v, v.rightChild);
                    downHeapBubble(v.rightChild);
                }
            }
        }
        if (v.leftChild != null && v.rightChild == null) {
            if (v.key > v.leftChild.key) {
                swapVals(v, v.leftChild);
                downHeapBubble(v.leftChild);
            }
        }
    }


    //This method is used to insert a new entry into the min heap
    public void insert(int k, String v) {
        Node N = new Node();
        N.key = k;
        N.value = v;

        //If this is the very first element of the heap
        if (size == 0) {
            root = N;
            lastNode = N;
            size++;
            return;
        }
        if (lastNode.leftChild == null) lastNode.leftChild = N;
        else lastNode.rightChild = N;
        N.parent = lastNode;
        size++;
        upHeapBubble(N);
        incrementLastNode();
    }


    //This method implements the removeMin() operation
    public String removeMin() {

        String result = root.value;
        if (size == 1) {
            root = lastNode = null;
            size--;
            return (result);
        }

        decrementLastNode();
        Node N;
        if (lastNode.rightChild != null) {
            N = lastNode.rightChild;
            lastNode.rightChild = null;
        } else {
            N = lastNode.leftChild;
            lastNode.leftChild = null;
        }

        //Copy the entry in N to the root
        root.key = N.key;
        root.value = N.value;
        size--;
        downHeapBubble(root);
        return (result);
    }


    //This method deletes a given node N
    public void deleteNode(Node N) {
        if (size == 1 && N == root) {
            root = lastNode = null;
            size--;
        }
        decrementLastNode();
        Node P;
        if (lastNode.rightChild != null) {
            P = lastNode.rightChild;
            lastNode.rightChild = null;
        } else {
            P = lastNode.leftChild;
            lastNode.leftChild = null;
        }
        N.key = P.key;
        N.value = P.value;
        size--;
        downHeapBubble(N);
    }


    //This method modifies the key of a given node
    public void modifyKeyOfNode(Node N, int k) {
        N.key = k;
        if (N.parent != null) {
            if (N.key < N.parent.key) {
                upHeapBubble(N);
            }
        }
        if (N.leftChild != null) {
            downHeapBubble(N);
        }

    }


//This method does the pre-order traversal of the subtree rooted at node v
//Since heap is basically a binary tree, this method may be used to list the
//elements of the heap and hence used to debug the other methods.

public void preOrderTraversal(Node v){
        System.out.println("("+v.key+", "+v.value+")");
        if(v.leftChild!=null)preOrderTraversal(v.leftChild);
        if(v.rightChild!=null)preOrderTraversal(v.rightChild);
        }
        }