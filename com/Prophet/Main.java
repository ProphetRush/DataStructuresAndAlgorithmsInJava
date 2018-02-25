package com.Prophet;

import com.Prophet.pList.pArrayList;
import com.Prophet.pList.pLinkedList;
import com.Prophet.pList.pList;
import com.Prophet.pMap.pHashMap;
import com.Prophet.pMap.pMap;
import com.Prophet.pSet.pHashSet;
import com.Prophet.pSet.pSet;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        pSet<String> set = new pHashSet<>();
        set.add("Smith");
        set.add("Anderson");
        set.add("Lewis");
        set.add("Cook");
        set.add("Smith");

        System.out.println("Elements in set: " + set);
        System.out.println("Number of elements in set: " + set.size());
        System.out.println("Is Smith in set? " + set.contains("Smith"));

        set.remove("Smith");
        System.out.print("Names in set in uppercase are ");
        for (String s: set)
            System.out.print(s.toUpperCase() + " ");

        set.clear();
        System.out.println("\nElements in set: " + set);
    }
}
