public class Main{
    public static void main(String args[]){
        MinHeap H = new MinHeap();
        
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

        H.modifyKeyOfNode(H.root.rightChild.leftChild, 100);

        
       for(int i=0;i<20;++i)if(!H.isEmpty())System.out.println(H.removeMin());


        
//        H.insert(7, "Q");
//        H.insert(6, "Y");
//        H.insert(14, "E");
//        H.insert(12, "H");
//        H.insert(4, "C");
//        H.insert(5, "A");
//        H.insert(15, "K");
//
//        for(int i=0;i<20;++i)if(!H.isEmpty())System.out.println(H.removeMin());
    }
}