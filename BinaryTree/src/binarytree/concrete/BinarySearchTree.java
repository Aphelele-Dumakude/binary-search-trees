package binarytree.concrete;

import binarytree.abstractclasses.AbstractTree;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;

    /**
     * Create a default binary search tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary search tree from an array of objects
     * @param objects
     */
    public BinarySearchTree(E[] objects) {
        for (E object : objects) {
            insert(object);
        }
    }
    /**
     * @param element
     * @return true if the specified element is in the tree
     */
    @Override
    public boolean search(E element) {
        if (root == null) {
            return false;
        }
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (currentNode.element.compareTo(element) > 0) {
                currentNode = currentNode.left;
            }
            else if (currentNode.element.compareTo(element) < 0) {
                currentNode = currentNode.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * @param element
     * @return true if the element is added successfully
     */
    @Override
    public boolean insert(E element) {
        if (root == null) {
            root = new TreeNode<>(element);
            return true;
        }
        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;

        while (currentNode != null) {
            parentNode = currentNode;
            if (currentNode.element.compareTo(element) > 0) {
                currentNode = currentNode.left;
            }
            else if (currentNode.element.compareTo(element) < 0) {
                currentNode = currentNode.right;
            }
            else {
                return false;
            }
        }
        if (parentNode.element.compareTo(element) > 0) {
            parentNode.left = new TreeNode<>(element);
        }
        else {
            parentNode.right = new TreeNode<>(element);
        }
        size++;
        return true;
    }

    /**
     * @param element
     * @return true if the element is removed from the tree successfully
     */
    @Override
    public boolean delete(E element) {
        if (root == null) {
            return false;
        }
        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;
        while (currentNode != null) {
            if (currentNode.element.compareTo(element) > 0) {
                parentNode = currentNode;
                currentNode = currentNode.left;
            }
            else if (currentNode.element.compareTo(element) < 0) {
                parentNode = currentNode;
                currentNode = currentNode.right;
            }
            else {
                break;
            }
        }
        // element is not in the BST
        if (currentNode == null) {
            return false;
        }
        // case 1: The current node does not have a left child
        if (currentNode.left == null) {
            if (parentNode == null) {
                root = currentNode.right;
            }
            else {
                if (parentNode.element.compareTo(element) > 0) {
                    parentNode.left = currentNode.right;
                }
                else {
                    parentNode.right = currentNode.right;
                }
            }
        }
        // case 2: The current node has a left child
        else {
            TreeNode<E> parentOfRightMost = currentNode;
            TreeNode<E> rightMost = currentNode.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            currentNode.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            }
            else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    /**
     * Prints the nodes in inorder traversal
     */
    @Override
    public void inOrder() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> currentNode = root;

        while (!stack.isEmpty() || currentNode != null) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            System.out.println(currentNode.element);
            currentNode = currentNode.right;
        }
    }

    /**
     * Prints the nodes in preorder traversal
     */
    @Override
    public void preOrder() {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);

        TreeNode<E> currentNode;
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            System.out.println(currentNode.element);

            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    /**
     * Prints the nodes in postorder traversal
     */
    @Override
    public void postOrder() {

        if (root == null) {
            return;
        }
        Stack<TreeNode<E>> stack1 = new Stack<>();
        Stack<TreeNode<E>> stack2 = new Stack<>();

        TreeNode<E> currentNode;
        stack1.push(root);

        while (!stack1.isEmpty()) {
            currentNode = stack1.pop();
            stack2.push(currentNode);

            if (currentNode.left != null) {
                stack1.push(currentNode.left);
            }
            if (currentNode.right != null) {
                stack1.push(currentNode.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().element);
        }
    }

    /**
     * Prints the nodes in breadthFirst traversal
     */
    @Override
    public void breadthFirst() {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        TreeNode<E> currentNode;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.println(currentNode.element);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    /**
     *
     * @param element
     * @return a path from the root leading to the specified element
     */
    public ArrayList<TreeNode<E>> path(E element) {
        if (root == null) {
           return null;
        }
        ArrayList<TreeNode<E>> nodes = new ArrayList<>();
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            nodes.add(currentNode);
            if (currentNode.element.compareTo(element) > 0) {
                currentNode = currentNode.left;
            }
            else if (currentNode.element.compareTo(element) < 0) {
                currentNode = currentNode.right;
            }
            else {
                break;
            }
        }
        return nodes;
    }

    /**
     * Removes all elements from the tree
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new InOrderIterator();
    }
    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E element) {
            this.element = element;
        }
    }
    private class InOrderIterator implements Iterator<E> {

        private final ArrayList<E> list;
        private int current;

        InOrderIterator() {
            list = new ArrayList<>();
            current = 0;
            this.inOrder();
        }

        /**
         * Inorder traversal from the root
         */
        private void inOrder() {
            inOrder(root);
        }

        private void inOrder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            list.add(root.element);
            inOrder(root.right);
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            return list.get(current++);
        }
    }

    /**
     *
     * @return the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }
}
