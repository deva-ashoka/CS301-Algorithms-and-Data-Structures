import java.io.*;
import java.lang.Integer;

class Entry {
    public int key;//This denotes the key of the entry stored at the node
    public String value;//This denotes the value of the entry stored at the node

    public Entry(int k, String v) {
        key = k;
        value = v;
    }
}

public class MinHeapArray {

    final int MAX_HEAP_SIZE = 1000;//This indicates the maximum size the heap can grow into.
    public Entry[] A;//This is the array of references to entries
    public int size;//This is the number of current entries in the heap

    //This is the initialization method for the array based min-heap implementation.
    public MinHeapArray() {
        size = 0;
        A = new Entry[MAX_HEAP_SIZE + 1];
    }


    //This is the initialization method that takes as input an array of entries
    //and performs the bottom up heap construction
    public MinHeapArray(Entry B[], int n) {

        size = n;
        A = B;
        bottomUpHeapConstruction();
    }


    //This method implements the bottom-up heap construction.
    public void bottomUpHeapConstruction() {
        //To be written as part of Homework.
        for (int i = size / 2; i >= 1; i--) {
            int leftChildIndex = 2 * i;
            int rightChildIndex = 2 * i + 1;
            if ((leftChildIndex <= size)) {
                if (leftChildIndex < size) {
                    if (A[i].key > A[leftChildIndex].key || A[i].key > A[rightChildIndex].key) {
                        downHeapBubble(i);
                    }
                    if (A[i].key > A[leftChildIndex].key) {
                        downHeapBubble(i);
                    }
                }
            }
        }
    }

    //This method swaps the key-value pairs stored at given indices in the array A

    public void swapVals(int p, int q) {
        String temp1 = A[p].value;
        int temp2 = A[p].key;
        A[p].key = A[q].key;
        A[p].value = A[q].value;
        A[q].key = temp2;
        A[q].value = temp1;
    }


    //This is the min() method that returns the minimum element in the heap
    public String min() {
        return (A[1].value);
    }


    //This method returns whether the heap is empty or not
    public boolean isEmpty() {
        if (size == 0) return (true);
        else return (false);
    }


    //This method implements the up-heap bubble

    public void upHeapBubble(int i) {
        //To be written as part of homework
        if (i == 1) {
            return;
        } else {
            int parentIndex = i / 2;
            if (A[i].key < A[parentIndex].key) {
                swapVals(i, parentIndex);
                upHeapBubble(parentIndex);
            }
        }
    }


    //This method implements the down heap bubble

    public void downHeapBubble(int i) {
        //To be written as part of homework
        int leftChildIndex = 2 * i;
        int rightChildIndex = 2 * i + 1;
        if (leftChildIndex > size) {
            return;
        }
        if (leftChildIndex <= size && rightChildIndex <= size) {
            if (A[i].key > A[leftChildIndex].key || A[i].key > A[rightChildIndex].key) {
                if (A[leftChildIndex].key < A[rightChildIndex].key) {
                    swapVals(i, leftChildIndex);
                    downHeapBubble(leftChildIndex);
                } else if (A[rightChildIndex].key < A[leftChildIndex].key) {
                    swapVals(i, rightChildIndex);
                    downHeapBubble(rightChildIndex);
                }
            }
        }
        if (leftChildIndex <= size && rightChildIndex > size) {
            if (A[i].key > A[leftChildIndex].key) {
                swapVals(i, leftChildIndex);
                downHeapBubble(leftChildIndex);
            }
        }
    }


    //This method is used to insert a new entry into the min heap
    public void insert(int k, String v) {
        Entry E = new Entry(k, v);
        A[++size] = E;
        upHeapBubble(size);
    }


    //This method implements the removeMin() operation
    public String removeMin() {

        String result = A[1].value;

        //Copy the entry at index "size" to the root
        A[1].key = A[size].key;
        A[1].value = A[size].value;
        size--;
        downHeapBubble(1);

        return (result);
    }

    //This method deletes a given entry denoted by the index
    //of the entry in the array
    public void deleteNode(int N) {
        //To be written as a part of homework.
        if (size == 1) {
            if (N == 1) {
                A[N] = null;
                size--;
                return;
            }
        } else {
            A[N].key = A[size].key;
            A[N].value = A[size].value;
            downHeapBubble(N);
            size--;
        }
    }


    //This method modifies the key of a given entry denoted
    //by the index of the entry in the array.
    public void modifyKeyOfNode(int N, int k) {
        //To be written as part of homework.
        if (N == 1) {
            A[1].key = k;
            int leftChildIndex = 2 * N;
            int rightChildIndex = 2 * N + 1;
            if (leftChildIndex <= size) {
                downHeapBubble(N);
            }
            return;
        }
        A[N].key = k;
        int parentIndex = N / 2;
        int leftChildIndex = 2 * N;
        int rightChildIndex = 2 * N + 1;
        if (N > 0) {
            if (A[N].key < A[parentIndex].key) {
                upHeapBubble(N);
            }
        }
        if (leftChildIndex <= size) {
            downHeapBubble(N);
        }
    }

    //This method just prints the array storing the heap
    //This may be used for debugging

    public void printArray() {
        for (int i = 1; i <= size; ++i) {
            System.out.print("(" + A[i].key + ", " + A[i].value + "), ");
        }
        System.out.println("");
    }
}