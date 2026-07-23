/*
Level 1 — Warm-up

Frequency Count - Given an array of integers, return the frequency of every distinct element.

Count Distinct Elements - Given an array of integers, determine the number of distinct elements present in the array.

Contains Duplicate - Given an integer array, determine whether any value appears at least twice. Return true if any duplicate exists; otherwise, return false.

First Repeating Element - Given an array of integers, find the first element that repeats (the element whose second occurrence appears first while traversing the array). Return -1 if no repeating element exists.

First Non-Repeating Element - Given an array of integers, find the first element that occurs exactly once. Return -1 if no such element exists.

*/

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] arr = {5, 12, 3, 5, 8, 12, 1, 3, 9};

        // Build frequency map once
        HashMap<Integer, Integer> map = buildFrequencyMap(arr);

        frequencyCount(map);
        countDistinctElements(map);
        containsDuplicate(map);
        firstRepeatingElement(arr);
        firstNonRepeatingElement(arr, map);
    }

    // -----------------------------
    // Build Frequency Map
    // -----------------------------
    public static HashMap<Integer, Integer> buildFrequencyMap(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map;
    }

    // -----------------------------
    // 1. Frequency Count
    // -----------------------------
    public static void frequencyCount(HashMap<Integer, Integer> map) {

        System.out.println("----- Frequency Count -----");

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();
    }

    // -----------------------------
    // 2. Count Distinct Elements
    // -----------------------------
    public static void countDistinctElements(HashMap<Integer, Integer> map) {

        System.out.println("----- Count Distinct Elements -----");
        System.out.println("Distinct Elements = " + map.size());
        System.out.println();
    }

    // -----------------------------
    // 3. Contains Duplicate
    // -----------------------------
    public static void containsDuplicate(HashMap<Integer, Integer> map) {

        System.out.println("----- Contains Duplicate -----");

        boolean found = false;

        for (Integer key : map.keySet()) {

            if (map.get(key) > 1) {
                found = true;
                break;
            }

        }

        System.out.println(found);
        System.out.println();
    }

    // -----------------------------
    // 4. First Repeating Element
    // -----------------------------
    public static void firstRepeatingElement(int[] arr) {

        System.out.println("----- First Repeating Element -----");

        HashSet<Integer> seen = new HashSet<>();

        for (int num : arr) {

            if (seen.contains(num)) {
                System.out.println(num);
                return;
            }

            seen.add(num);
        }

        System.out.println(-1);
        System.out.println();
    }

    // -----------------------------
    // 5. First Non-Repeating Element
    // -----------------------------
    public static void firstNonRepeatingElement(int[] arr,
                                                HashMap<Integer, Integer> map) {

        System.out.println("----- First Non-Repeating Element -----");

        for (int num : arr) {

            if (map.get(num) == 1) {
                System.out.println(num);
                return;
            }

        }

        System.out.println(-1);
        System.out.println();
    }

}
