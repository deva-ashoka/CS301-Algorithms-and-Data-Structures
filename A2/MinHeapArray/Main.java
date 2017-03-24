public class Main {

    //This method prints a given array of entries in sorted order as per the keys.
//    //It uses heap sort method fr sorting.
    public static void HeapSortPrint(Entry A[], int n){
        MinHeapArray H = new MinHeapArray(A, n);

        for(int i = 0;i < n;++i){
            System.out.println(H.removeMin());
        }
    }

    public static void main(String args[]) {
        MinHeapArray H = new MinHeapArray();

        H.insert(7, "Q");
        H.insert(6, "Y");
        H.insert(14, "E");
        H.insert(12, "H");
        H.insert(4, "C");
        H.insert(5, "A");
        H.insert(15, "K");
        H.insert(9, "F");
        H.insert(20, "B");
        H.insert(16, "X");
        H.insert(25, "J");
        H.insert(11, "S");
        H.insert(13, "W");

        if (!H.isEmpty()) H.printArray();
        System.out.println(H.size);

        H.deleteNode(1);

        if (!H.isEmpty()) H.printArray();
        System.out.println(H.size);

        H.modifyKeyOfNode(3, 100);



        int sizeOfHeap = H.size;

        for (int i = 1; i <= sizeOfHeap; i++) {
            System.out.println(H.removeMin());
        }

        System.out.println("..........");


        Entry E[] = new Entry[14];
        E[1] = new Entry(7, "Q");
        E[2] = new Entry(6, "Y");
        E[3] = new Entry(14, "E");
        E[4] = new Entry(12, "H");
        E[5] = new Entry(4, "C");
        E[6] = new Entry(5, "A");
        E[7] = new Entry(15, "K");
        E[8] = new Entry(9, "F");
        E[9] = new Entry(20, "B");
        E[10] = new Entry(16, "X");
        E[11] = new Entry(25, "J");
        E[12] = new Entry(11, "S");
        E[13] = new Entry(13, "W");
        HeapSortPrint(E, 13);
    }
}