package com.Prophet.pTree;

public interface pTree<E> extends Iterable<E> {

    public boolean search(E e);

    public boolean insert(E e);

    public boolean delete(E e);

    public void inOrderTraversal();

    public void postOrderTraversal();

    public void preOrderTraversal();

    public int getSize();

    public boolean isEmpty();
}
