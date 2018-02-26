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
        BST<String> tree = new BST<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");

        // Traverse tree
        System.out.print("Inorder (sorted): ");
        tree.inOrderTraversal();
        System.out.print("\nPostorder: ");
        tree.postOrderTraversal();
        System.out.print("\nPreorder: ");
        tree.preOrderTraversal();
        System.out.print("\nThe number of nodes is " + tree.getSize());

        // Search for an element
        System.out.print("\nIs Peter in the tree? " +
                tree.search("Peter"));

        // Get a path from the root to Peter
        System.out.print("\nA path from the root to Peter is: ");
        java.util.ArrayList<BST.TreeNode<String>> path
                = tree.path("Peter");
        for (int i = 0; path != null && i < path.size(); i++)
            System.out.print(path.get(i).element + " ");

        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> intTree = new BST<>(numbers);
        System.out.print("\nInorder (sorted): ");
        intTree.inOrderTraversal();
    }
}
