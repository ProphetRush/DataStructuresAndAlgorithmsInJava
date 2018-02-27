package com.Prophet;

import com.Prophet.pList.pArrayList;
import com.Prophet.pList.pLinkedList;
import com.Prophet.pList.pList;
import com.Prophet.pMap.pHashMap;
import com.Prophet.pMap.pMap;
import com.Prophet.pSet.pHashSet;
import com.Prophet.pSet.pSet;
import com.Prophet.pTree.BST;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        pLinkedList<Integer> list = new pLinkedList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);
        list.reverse();
        System.out.println(list.toString());
    }
}
