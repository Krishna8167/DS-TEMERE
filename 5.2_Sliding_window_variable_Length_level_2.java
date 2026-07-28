/******************************************************************************

1. Longest Substring Without Repeating Character
    Problem Statement: Given a string s, find the length of the longest substring that contains only unique characters (no duplicates).
    Input:A string s
    Output: An integer representing the length of the longest unique substring.
    Example 1:s = "abcabcbb"
    Expected Result: 3 (The substring is "abc")
    Example 2:s = "pwwkew"
    Expected Result: 3 (The substring is "wke"; note that "pwke" is a subsequence, not a contiguous substring)
    
2. Maximum Consecutive Ones III
    Problem Statement: Given a binary array nums and an integer k, 
        find the maximum number of consecutive 1s you can achieve in the array if you are allowed to flip at most k zeros into 1s.
    Input:An array of integers nums (containing only 0s and 1s)An integer k
    Output: An integer representing the maximum sequence length of 1s.
    Example 1:nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], 
    k = 2
    Expected Result: 6 (By flipping the zeros at index 4 and 5, the segment becomes [1, 1, 1, 1, 0])
    
3. Fruit Into Baskets
    Problem Statement: You are visiting a farm that has a single row of fruit trees arranged from left to right represented by an integer array fruits. 
        You have two baskets, and each basket can only hold a single type of fruit. 
        There is no limit on the amount of fruit each basket can hold. 
        Starting from any tree of your choice, you must pick exactly one fruit from every tree while moving to the right. 
        Once you reach a tree with a fruit type that cannot fit in your baskets, you must stop. 
    Find the maximum total number of fruits you can collect.
    Input:An array of integers fruits where fruits[i] represents the type of fruit on the \(i\)-th tree.
    Output: An integer representing the maximum number of fruits collected.Example:fruits = [1, 2, 3, 2, 2]
    Expected Result: 4 (You can start at index 1 and pick types [2, 3, 2, 2], utilizing both baskets for types 2 and 3)
    
4. Max Consecutive Ones (Standard Version)
    Problem Statement: Given a binary array nums, return the maximum number of consecutive 1s in the array without making any flips.
    Input:An array of integers nums (containing only 0s and 1s)
    Output: An integer representing the longest consecutive sequence of 1s.Example:nums = [1, 1, 0, 1, 1, 1]
    Expected Result: 3 (The trailing sequence [1, 1, 1] is the longest)
    
5. Longest Repeating Character Replacement
    Problem Statement: Given a string s consisting of uppercase English letters and an integer k. 
    You can choose any character of the string and change it to any other uppercase English character. 
    You can perform this operation at most k times. 
    Find the length of the longest substring containing the same letter after performing the operations.
    Input:A string s, An integer k
    Output: An integer representing the maximum sequence length achievable.
    Example:s = "AABABBA", 
    k = 1
    Expected Result: 4 (Replace the 'A' at index 2 with 'B' to get "BBBB")
    
6. Minimum Size Subarray Sum
    Problem Statement: Given an array of positive integers nums and a positive integer target. 
        Find the minimal length of a contiguous subarray whose sum is greater than or equal to target. 
        If there is no such subarray, return 0 instead.
    Input:An integer targetAn array of positive integers nums
    Output: An integer representing the minimal length of the qualifying contiguous block.
    Example:target = 7, nums = [2, 3, 1, 2, 4, 3]
    Expected Result: 2 (The subarray [4, 3] has a sum of 7, which satisfies the threshold with the shortest possible length)

*******************************************************************************/
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String s1 = "abcabcbb";

        int[] arr1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;

        int[] fruits = {1, 2, 3, 2, 2};

        int[] arr2 = {1, 1, 0, 1, 1, 1};

        String s2 = "AABABBA";
        int k2 = 1;

        int[] arr3 = {2, 3, 1, 2, 4, 3};
        int target = 7;

        System.out.println(longestSubstringWithoutRepeat(s1));
        System.out.println(longestOnes(arr1, k1));
        System.out.println(totalFruit(fruits));
        System.out.println(maxConsecutiveOnes(arr2));
        System.out.println(characterReplacement(s2, k2));
        System.out.println(minSubarrayLength(arr3, target));
    }

    // ----------------------------------------------------
    // 1. Longest Substring Without Repeating Characters
    // ----------------------------------------------------

    public static int longestSubstringWithoutRepeat(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // ----------------------------------------------------
    // 2. Maximum Consecutive Ones III
    // ----------------------------------------------------

    public static int longestOnes(int[] nums, int k) {

        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {

                if (nums[left] == 0) {
                    zeroCount--;
                }

                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // ----------------------------------------------------
    // 3. Fruit Into Baskets
    // ----------------------------------------------------

    public static int totalFruit(int[] fruits) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < fruits.length; right++) {

            map.put(fruits[right],
                    map.getOrDefault(fruits[right], 0) + 1);

            while (map.size() > 2) {

                map.put(fruits[left], map.get(fruits[left]) - 1);

                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // ----------------------------------------------------
    // 4. Maximum Consecutive Ones
    // ----------------------------------------------------

    public static int maxConsecutiveOnes(int[] nums) {

        int currentOnes = 0;
        int maxOnes = 0;

        for (int num : nums) {

            if (num == 1) {
                currentOnes++;
                maxOnes = Math.max(maxOnes, currentOnes);
            } else {
                currentOnes = 0;
            }
        }

        return maxOnes;
    }

    // ----------------------------------------------------
    // 5. Longest Repeating Character Replacement
    // ----------------------------------------------------

    public static int characterReplacement(String s, int k) {

        int[] frequency = new int[26];

        int left = 0;
        int maxLength = 0;
        int maxFrequency = 0;

        for (int right = 0; right < s.length(); right++) {

            int index = s.charAt(right) - 'A';

            frequency[index]++;

            maxFrequency = Math.max(maxFrequency, frequency[index]);

            while ((right - left + 1) - maxFrequency > k) {

                frequency[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // ----------------------------------------------------
    // 6. Minimum Size Subarray Sum
    // ----------------------------------------------------

    public static int minSubarrayLength(int[] nums, int target) {

        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            currentSum += nums[right];

            while (currentSum >= target) {

                minLength = Math.min(minLength, right - left + 1);

                currentSum -= nums[left];
                left++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
