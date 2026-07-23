/******************************************************************************
Level 2 — Intermediate

Two Sum - Given an array of integers nums and an integer target, return the indices of the two numbers such that they add up to target. Assume exactly one valid solution exists, and the same element cannot be used twice.

Valid Anagram - Given two strings s and t, determine whether t is an anagram of s.

Intersection of Two Arrays - Given two integer arrays, return an array containing their unique common elements. The order of the output does not matter.

Happy Number - Given a positive integer n, repeatedly replace the number with the sum of the squares of its digits. Return true if the process eventually reaches 1; otherwise, return false if it enters a cycle.

Isomorphic Strings - Given two strings s and t, determine whether they are isomorphic. Two strings are isomorphic if the characters in one string can be replaced to get the other string while preserving the order and ensuring a one-to-one mapping.
*******************************************************************************/
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int[] arr2 = {2, 11, 18};

        int target = 9;

        String s = "anagram";
        String t = "nagaarm";

        int n = 19;

        System.out.println(Arrays.toString(twoSum(arr, target)));
        System.out.println(isAnagram(s, t));
        System.out.println(intersection(arr, arr2));
        System.out.println(isHappy(n));
        System.out.println(isIsomorphic(s, t));
    }

    // ---------------------------------------------------------
    // 1. Two Sum
    // ---------------------------------------------------------

    public static int[] twoSum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }

    // ---------------------------------------------------------
    // 2. Valid Anagram
    // ---------------------------------------------------------

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {

            char ch = t.charAt(i);

            if (!map.containsKey(ch)) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);

            if (map.get(ch) == 0) {
                map.remove(ch);
            }
        }

        return map.isEmpty();
    }

    // ---------------------------------------------------------
    // 3. Intersection of Two Arrays
    // ---------------------------------------------------------

    public static List<Integer> intersection(int[] arr, int[] arr2) {

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> answer = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        for (int num : arr2) {

            if (set.contains(num)) {
                answer.add(num);
            }
        }

        return new ArrayList<>(answer);
    }

    // ---------------------------------------------------------
    // 4. Happy Number
    // ---------------------------------------------------------

    public static int helperForHappy(int n) {

        int sum = 0;

        while (n > 0) {

            int rem = n % 10;
            sum += rem * rem;
            n /= 10;
        }

        return sum;
    }

    public static boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<>();

        while (n != 1) {

            if (set.contains(n)) {
                return false;
            }

            set.add(n);

            n = helperForHappy(n);
        }

        return true;
    }

    // ---------------------------------------------------------
    // 5. Isomorphic Strings
    // ---------------------------------------------------------

    public static boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> ST = new HashMap<>();
        HashMap<Character, Character> TS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (ST.containsKey(c1)) {

                if (ST.get(c1) != c2) {
                    return false;
                }

            } else {

                ST.put(c1, c2);
            }

            if (TS.containsKey(c2)) {

                if (TS.get(c2) != c1) {
                    return false;
                }

            } else {

                TS.put(c2, c1);
            }
        }

        return true;
    }
}
