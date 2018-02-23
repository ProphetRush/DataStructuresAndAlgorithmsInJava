package com.Prophet.pList;

import java.util.Iterator;

public class pLinkedList<E> extends pAbstactList<E>{

    private Node<E> head, tail;

    private static class Node<E>{
        E e;
        Node<E> next;
        public Node(E e){
            this.e = e;
        }
    }

    public pLinkedList(){}

    public pLinkedList(E[] obj){
        super(obj);
    }

    public E getFirst(){
        if(size == 0) return null;
        return head.e;
    }

    public E getLast(){
        if(size == 0) return null;
        return tail.e;
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if(tail == null) tail=head;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        if(tail == null) tail=head=newNode;
        else{
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public E removeFirst(){
        if(size == 0) return null;
        Node<E> element = head;
        head = head.next;
        size--;
        if (head == null) tail=null;
        return element.e;
    }

    public E removeLast(){
        if(size == 0) return null;
        else if(size == 1) {
            Node<E> tmp = head;
            head = tail = null;
            size=0;
            return tmp.e;
        }else{
            Node<E> cur = head;
            for (int i = 0; i < size-2; i++) {
                cur = cur.next;
            }
            Node<E> tmp = tail;
            tail = cur;
            tail.next = null;
            size--;
            return tmp.e;

        }

    }






    @Override
    public void add(int index, E e) {
        if(index == 0) addFirst(e);
        else if(index >= size) addLast(e);
        else{
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> tmp = current.next;
            current.next = new Node<E>(e);
            current.next.next = tmp;
            size++;
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public boolean contains(E e) {
        Node<E> cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.e.equals(e)) return true;
            else cur = cur.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        Node<E> cur = head;
        for (int i = 1; i <= index; i++) {
            cur = cur.next;
        }
        return cur.e;

    }

    @Override
    public int indexOf(E e) {
        Node<E> cur = head;
        for (int i = 0; i < size; i++) {
            if(cur.e.equals(e)) return i;
            else cur = cur.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int index = -1;
        Node<E> cur = head;
        for (int i = 0; i < size; i++) {
            if(cur.e.equals(e)) index = i;
            else cur = cur.next;
        }
        return index;
    }

    @Override
    public E remove(int index) {
        if(index<0 || index>=size) return null;
        else if(index == 1) return removeFirst();
        else if(index == size-1) return removeLast();
        else{
            Node<E> pre = head;
            for (int i = 1; i < index; i++) {
                pre = pre.next;
            }
            Node<E> tmp = pre.next;
            pre.next = tmp.next;
            size--;
            return tmp.e;
        }
    }

    @Override
    public E set(int index, E e) {
        Node<E> cur = head;
        if(index<0 || index>= size) return null;
        else if(index == 0){
            Node<E> newE = new Node<E>(e);
            newE.next = head.next;
            head = newE;
            return e;
        }else if(index == size-1){
            Node<E> newE = new Node<E>(e);
            for (int i = 0; i < size-2; i++) {
                cur = cur.next;
            }
            cur.next = newE;
            tail = newE;
            return e;
        }else{
            Node<E> newE = new Node<E>(e);
            for (int i = 0; i < size-2; i++) {
                cur = cur.next;
            }
            cur.next = newE;
            return e;
        }
    }


    private class LinkedListIterator implements Iterator<E>{
        private Node<E> cur = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return cur.next != null;
        }

        @Override
        public E next() {
            E e = cur.e;
            cur = cur.next;
            index++;
            return e;
        }

        @Override
        public void remove() {
            pLinkedList.this.remove(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> cur = head;
        for (int i = 0; i < size; i++) {
            result.append(cur.e);
            cur = cur.next;
            if(cur != null) result.append(", ");
            else result.append("]");
        }
        return result.toString();
    }
}
