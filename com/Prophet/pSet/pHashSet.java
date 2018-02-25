package com.Prophet.pSet;



import com.Prophet.pMap.pMap;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@SuppressWarnings("unchecked")
public class pHashSet<E> implements pSet<E> {

    private static int DEFAULT_INITIAL_CAPACITY = 16;

    private static int MAX_CAPACITY = 1<<30;

    private int capacity;

    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75F;

    private float loadFactorThreshold;

    private int size = 0;

    LinkedList<E>[] data;

    public pHashSet(int initialCapacity, float loadFactorThreshold){
        if(initialCapacity > MAX_CAPACITY) this.capacity = MAX_CAPACITY;
        else this.capacity = trimCapacity(initialCapacity);
        this.loadFactorThreshold = loadFactorThreshold;
        data = new LinkedList[capacity];
    }

    public pHashSet(int initialCapacity){
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public pHashSet(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }


    private static int supplementalHash(int h){
        h ^= (h>>>20)^(h>>>12);
        return h^(h>>>7)^(h>>>4);
    }

    private int hash(E e){
        return supplementalHash(e.hashCode()) & (capacity -1);
    }




    private int trimCapacity(int initialCapacity){
        int capacity = 1;
        while(capacity < initialCapacity) capacity<<=1;
        return capacity;
    }


    private void rehash(){
        ArrayList<E> list = set2List();
        capacity <<= 1;
        data = new LinkedList[capacity];
        size = 0;
        for(E e: list){
            add(e);
        }
    }

    private ArrayList<E> set2List(){
        ArrayList<E> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if(data[i]!=null){
                for(E e:data[i]){
                    list.add(e);
                }
            }
        }
        return list;
    }

    private void removeElements(){
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null){
                data[i].clear();
            }
        }
    }

    @Override
    public void clear() {
        removeElements();
        size=0;
    }

    @Override
    public boolean contains(E e) {
        int bucketIndex = hash(e);
        if(data[bucketIndex] != null){
            LinkedList<E> bucket = data[bucketIndex];
            for(E element: bucket){
                if(element.equals(e)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if(contains(e)) return false;
        if(size + 1 > capacity*loadFactorThreshold){
            if(capacity == MAX_CAPACITY) throw new RuntimeException("Exceeding maximun capacity!");
            rehash();
        }
        int bucketIndex = hash(e);
        if(data[bucketIndex] == null) data[bucketIndex] = new LinkedList<E>();
        data[bucketIndex].add(e);
        size++;
        return  true;
    }

    @Override
    public boolean remove(E e) {
        if(!contains(e)) return false;
        int bucketIndex = hash(e);
        if(data[bucketIndex]!=null){
            LinkedList<E> bucket = data[bucketIndex];
            for(E element: bucket){
                if(element.equals(e)) {
                    bucket.remove(element);
                    break;
                }
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    private class pHashSetIterator implements Iterator<E>{
        private ArrayList<E> list;
        private int cur = 0;
        private pHashSet<E> set;

        public pHashSetIterator(pHashSet<E> set){
            this.set = set;
            list = set2List();
        }

        @Override
        public boolean hasNext() {
            return cur<list.size();
        }

        @Override
        public void remove() {
            set.remove(list.get(cur));
            list.remove(cur);
        }

        @Override
        public E next() {
            return list.get(cur++);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new pHashSetIterator(this);
    }

    @Override
    public String toString() {
        ArrayList<E> list = set2List();
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < list.size()-1; i++) {
            sb.append(list.get(i)+", ");
        }

        if(list.size() == 0) sb.append("]");
        else sb.append(list.get(list.size()-1)+"]");
        return sb.toString();
    }
}
