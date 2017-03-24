import java.io.*;
import java.lang.Integer;


class Node {
    public int value;//This denotes the value stored in the node
    public Node leftChild;//This is a reference to the left child in the binary tree
    public Node rightChild;//This is a reference to the right child in the binary tree
    public Node parent;//This is a reference to the parent of the node in the binary tree.

    public Node() {
        parent = leftChild = rightChild = null;
    }
}

public class BinaryTree {
    public Node root;
    public int size;

    //This is the initialization method for Binary Tree. This reads the tree description
    //from a file named tree.txt and initializes the tree.
    public BinaryTree() {

        //The tree is initialized by reading a file named tree.txt
        try {
            BufferedReader BR = new BufferedReader(new FileReader("tree.txt"));

            //The first line of the file is the number of nodes in the tree
            String line = BR.readLine();
            size = Integer.parseInt(line);

            //Initialize array A of n node references
            //Create all the nodes of the tree
            Node A[] = new Node[size];
            for (int i = 0; i < size; ++i) {
                A[i] = new Node();
                A[i].value = i;
            }

            //The second line of the input indicates the root node.
            line = BR.readLine();
            root = A[Integer.parseInt(line)];


            //The remaining size-1 lines conntains the edges of the tree
            for (int i = 0; i < size - 1; ++i) {
                line = BR.readLine();
                String[] dataArray = line.split(",");
                int p = Integer.parseInt(dataArray[0]);
                int q = Integer.parseInt(dataArray[1]);
                String rightOrLeft = dataArray[2];
                A[q].parent = A[p];
                if (rightOrLeft.equals("L")) A[p].leftChild = A[q];
                else A[p].rightChild = A[q];
            }

            //Close the file
            BR.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method does the pre-order traversal of the subtree rooted at node v
    public void preOrderTraversal(Node v) {
        System.out.println(v.value);
        if (v.leftChild != null) preOrderTraversal(v.leftChild);
        if (v.rightChild != null) preOrderTraversal(v.rightChild);
    }

    //This method does a post-order traversal of the subtree rooted at node v

    public void postOrderTraversal(Node v) {
        if (v.leftChild != null) postOrderTraversal(v.leftChild);
        if (v.rightChild != null) postOrderTraversal(v.rightChild);
        System.out.println(v.value);
    }


    //This method does a in-order traversal of the subtree rooted at node v

    public void inOrderTraversal(Node v) {
        if (v.leftChild != null) inOrderTraversal(v.leftChild);
        System.out.println(v.value);
        if (v.rightChild != null) inOrderTraversal(v.rightChild);
    }


    //This method computes the height of the subtree rooted at a given node v
    public int computeHeight(Node v) {
        int h1 = 0;
        int h2 = 0;
        if (v.leftChild == null && v.rightChild == null) return (0);
        if (v.leftChild != null) h1 = computeHeight(v.leftChild);
        if (v.rightChild != null) h2 = computeHeight(v.rightChild);
        if (h1 > h2) return (h1 + 1);
        else return (h2 + 1);
    }

    //This method computes the depth of a given node v in the tree

    public int computeDepth(Node v) {
        int depth = 0;
        while (v.parent != null) {
            v = v.parent;
            depth = depth + 1;
        }
        return depth;
    }


    //This method computes the number of nodes in the tree rooted at node v

    public int computeSize(Node v) {
        if (v == null) {
            return (0);
        } else {
            return (computeSize(v.leftChild) + 1 + computeSize(v.rightChild));
        }
    }


    //This method adds a new node in the tree with given value.
    //It adds the new node as a left child of a given leaf node v
    public void addLeft(Node v, int newNodeValue) {
        Node p = new Node();
        p.value = newNodeValue;
        v.leftChild = p;
        p.parent = v;
    }

    //This method adds a new node in the tree with given value.
    //It adds the new node as a right child of a given leaf node v
    public void addRight(Node v, int newNodeValue) {
        Node p = new Node();
        p.value = newNodeValue;
        v.rightChild = p;
        p.parent = v;
    }
}