package com.Prophet.pList;

public abstract class pAbstactList<E> implements pList<E> {
    protected int size = 0;

    //Only the implements inside the List package can visit the constructor of this abstract list.
    protected pAbstactList(){}

    protected pAbstactList(E[] obj){
        for (int i = 0; i < obj.length; i++) {
            add(obj[i]);
        }
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(E e) {
        if(indexOf(e) >= 0){
            remove(indexOf(e));
            return true;
        }else{
            return false;
        }
    }
}
