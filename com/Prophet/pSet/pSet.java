package com.Prophet.pSet;

public interface pSet<E> extends Iterable<E>{

    public void clear();

    public boolean contains(E e);

    public boolean add(E e);

    public boolean remove(E e);

    public boolean isEmpty();

    public int size();

}
