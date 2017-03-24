public class Main{
    public static void main(String args[]){
        BinaryTree B = new BinaryTree();
        
        System.out.println("Pre Order Traversal:");
        B.preOrderTraversal(B.root);
        
        System.out.println("The height of the given tree is:" + B.computeHeight(B.root));

        System.out.println("Post Order Traversal");
        B.postOrderTraversal(B.root);

        System.out.println("In Order Traversal");
        B.inOrderTraversal(B.root);

        System.out.println("Depth of root: " + B.computeDepth(B.root));
        System.out.println("Depth of root.leftChild.leftChild.rightChild: " + B.computeDepth(B.root.leftChild.leftChild.rightChild));

        System.out.println("Size of root.leftChild.leftChild: " + B.computeSize(B.root.leftChild.leftChild));

        B.addLeft(B.root.leftChild.leftChild.leftChild, 20);
        System.out.println("Pre Order Traversal:");
        B.preOrderTraversal(B.root);

        B.addRight(B.root.leftChild.rightChild, 1000);
        System.out.println("Pre Order Traversal:");
        B.preOrderTraversal(B.root);

        System.out.println(B.computeSize(B.root));

        B.addRight(B.root.leftChild, 100);
        System.out.println("Pre Order Traversal:");
        B.preOrderTraversal(B.root);
    }
}