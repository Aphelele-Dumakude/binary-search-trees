package binarytree.interfaces;

/**
 * @author Aphelele Dumakude
 * @param <E>
 */
public interface Tree<E> extends Iterable<E>{
    /**
     *
     * @param element
     * @return true if the specified element is in the tree
     */
    boolean search(E element);

    /**
     *
     * @param element
     * @return true if the element is added successfully
     */
    boolean insert(E element);

    /**
     *
     * @param element
     * @return true if the element is removed from the tree successfully
     */
    boolean delete(E element);

    /**
     * Prints the nodes in inorder traversal
     */
    void inOrder();

    /**
     * Prints the nodes in preorder traversal
     */
    void preOrder();

    /**
     * Prints the nodes in postorder traversal
     */
    void postOrder();

    /**
     *
     * @return the number of elements in the tree
     */
    int getSize();

    /**
     *
     * @return true if the tree is empty
     */
    boolean isEmpty();

    /**
     * Removes all elements from the tree
     */
    void clear();
}
