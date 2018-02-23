package com.Prophet.pList;

import java.lang.reflect.AccessibleObject;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class pArrayList<E> extends pAbstactList<E>{

    private static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    public pArrayList(){}

    public pArrayList(E[] obj){
        for (int i = 0; i < obj.length; i++) {
            add(obj[i]);
        }
    }

    private void ensureCapacity(){
        if(size >= data.length){
            E[] newData = (E[])new Object[INITIAL_CAPACITY<<1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public void add(int index, E e) {
        ensureCapacity();
        for (int i = size-1; i >= index ; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    @Override
    public void clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        if(indexOf(e)>=0) return true;
        else return false;
    }

    @Override
    public E get(int index) {
        if (index<0 || index>size-1) throw new IndexOutOfBoundsException("index "+index+" Out of Bound!");
        return data[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size-1; i >=0; i--) {
            if(data[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index<0 || index>size-1) throw new IndexOutOfBoundsException("index "+index+" Out of Bound!");
        E e = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return e;
    }

    @Override
    public E set(int index, E e) {
        if (index<0 || index>size-1) throw new IndexOutOfBoundsException("index "+index+" Out of Bound!");
        E oldData = data[index];
        data[index] = e;
        return oldData;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if(i<size-1) result.append(", ");
        }
        return result.toString()+"]";
    }

    public void trimToSize(){
        if(size!=data.length){
            E[] arr = (E[])new Object[size];
            System.arraycopy(data, 0, arr, 0, size);
            data = arr;
        }
    }

    public E[] toArray(){
        trimToSize();
        return data;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E>{
        private int current = 0;
        @Override
        public void remove() {
            pArrayList.this.remove(current);
        }

        @Override
        public boolean hasNext() {
            return current<size;
        }

        @Override
        public E next() {
            return data[current++];
        }
    }
}
