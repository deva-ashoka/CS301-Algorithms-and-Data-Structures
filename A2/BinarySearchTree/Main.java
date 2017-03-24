public class Main {
    public static void main(String args[]) {
        BinarySearchTree BST = new BinarySearchTree();

        BST.put(5, "A");
        BST.put(6, "B");
        BST.put(3, "C");
        BST.put(4, "D");
        BST.put(1, "E");
        System.out.println(BST.get(4));
        System.out.println(BST.get(1));
        System.out.println(BST.get(6));
        BST.remove(4);
        System.out.println(BST.get(4));
        System.out.println(BST.get(1));

    }
}