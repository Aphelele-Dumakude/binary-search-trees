import binarytree.concrete.BinarySearchTree;

public class Main {
    public static void main(String[] args) {

        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");

        // Tree Traversals
        System.out.println("--------------------------------------------");
        System.out.println("Inorder traversal: ");
        tree.inOrder();
        System.out.println("--------------------------------------------");
        System.out.println("PostOrder traversal: ");
        tree.postOrder();
        System.out.println("--------------------------------------------");
        System.out.println("PreOrder Traversal: ");
        tree.preOrder();
        System.out.println("--------------------------------------------");
        System.out.println("BreadthFirst Traversal: ");
        tree.breadthFirst();
        System.out.println("--------------------------------------------");
    }
}