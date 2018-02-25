package com.Prophet;

import com.Prophet.pList.pArrayList;
import com.Prophet.pList.pLinkedList;
import com.Prophet.pList.pList;
import com.Prophet.pMap.pHashMap;
import com.Prophet.pMap.pMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        pMap<String, Integer> map = new pHashMap<>();
        map.put("Smith", 30);
        map.put("Anderson", 31);
        map.put("Lewis", 29);
        map.put("Cook", 29);
        map.put("Smith", 65);

        System.out.println("Entries in map: " + map);

        System.out.println("The age for " + "Lewis is " +
                map.get("Lewis"));

        System.out.println("Is Smith in the map? " +
                map.containsKey("Smith"));
        System.out.println("Is age 33 in the map? " +
                map.containsValue(33));

        map.remove("Smith");
        System.out.println("Entries in map: " + map);

        map.clear();
        System.out.println("Entries in map: " + map);
    }
}
