package com.Prophet.pTree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E>{
    private TreeNode<E> root;
    private int size = 0;

    public static class TreeNode<E extends Comparable<E>>{
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }

    public BST(){

    }

    public BST(E[] obj){
        for (int i = 0; i < obj.length; i++) {
            insert(obj[i]);
        }
    }


    @Override
    public boolean search(E e) {
        TreeNode<E> cur = root;
        while(cur!=null){
            if(e.compareTo(cur.element)<0) cur = cur.left;
            else if(e.compareTo(cur.element)>0) cur = cur.right;
            else return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if(root == null) root=new TreeNode<>(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> cur = root;
            while(cur != null){
                if(e.compareTo(cur.element) < 0){
                    parent = cur;
                    cur = cur.left;
                }else if(e.compareTo(cur.element) > 0){
                    parent = cur;
                    cur = cur.right;
                }else{
                    return false;
                }
            }
            if(e.compareTo(parent.element) < 0) parent.left = new TreeNode<>(e);
            else parent.right = new TreeNode<>(e);
        }
        size++;
        return true;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> cur = root;
        while(cur != null){
            if(e.compareTo(cur.element) < 0){
                parent = cur;
                cur = cur.left;
            }else if(e.compareTo(cur.element) > 0){
                parent = cur;
                cur = cur.right;
            }else break;
        }

        if(cur == null) return false;

        if(cur.left == null){
            if(parent == null) root = cur.right;
            else{
                if(e.compareTo(parent.element) < 0) parent.left = cur.right;
                else parent.right = cur.right;
            }
        }else{
            TreeNode<E> parentOfBiggest = cur;
            TreeNode<E> biggest = cur.left;

            while(biggest.right != null){
                parentOfBiggest = biggest;
                biggest = biggest.right;
            }

            cur.element = biggest.element;

            if(parentOfBiggest.right == biggest) parentOfBiggest.right = biggest.left;
            else parentOfBiggest.left = biggest.left;

        }

        size--;
        return true;


    }

    @Override
    public void inOrderTraversal() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if(root == null) return;
        inorder(root.left);
        System.out.println(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postOrderTraversal() {
        postOrder(root);
    }

    protected void postOrder(TreeNode<E> root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.element + " ");
    }

    @Override
    public void preOrderTraversal() {
        preOrder(root);
    }


    protected void preOrder(TreeNode<E> root){
        if(root == null) return;
        System.out.println(root.element + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> cur = root;

        while(cur!=null){
            list.add(cur);
            if(e.compareTo(cur.element) < 0) cur = cur.left;
            else if(e.compareTo(cur.element) > 0) cur = cur.right;
            else break;
        }

        return list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int cur = 0;

        private void inorder(TreeNode<E> root){
            if(root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }



        public InOrderIterator(){
            inorder(root);
        }

        @Override
        public boolean hasNext() {
            return cur < list.size();
        }

        @Override
        public E next() {
            return list.get(cur++);
        }

        @Override
        public void remove() {
            delete(list.get(cur));
            list.clear();
            inorder(root);
        }
    }

    public void clear(){
        root = null;
        size = 0;
    }
}
