package binarytree.abstractclasses;

import binarytree.interfaces.Tree;

/**
 * @author Aphelele Dumakude
 * @param <E>
 */
public abstract class AbstractTree<E> implements Tree<E> {
    protected int size;

    protected AbstractTree() {
    }
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
    @Override
    public int getSize() {
        return size;
    }
}
