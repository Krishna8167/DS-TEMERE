/******************************************************************************
Level 3 — Advanced
Longest Consecutive Sequence - Given an unsorted array of integers, return the length of the longest sequence of consecutive integers. The solution should run in O(n) time.

Group Anagrams - Given an array of strings, group the strings that are anagrams of each other. Return the grouped anagrams in any order.

Top K Frequent Elements - Given an integer array and an integer k, return the k most frequent elements. The answer may be returned in any order.

Subarray Sum Equals K ⭐⭐⭐Give - n an integer array nums and an integer k, return the total number of continuous subarrays whose sum equals k.

Longest Subarray with Sum K - Given an integer array and an integer k, return the length of the longest continuous subarray whose sum is exactly k.
*******************************************************************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] arr = {100, 4, 200, 1, 3, 2};
        int[] nums = {1, 1, 1, 2, 2, 3};

        int k = 2;
        int k2 = 4;

        String[] st = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(longestConsecutiveSeq(arr));
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        System.out.println(groupAnagram(st));
        System.out.println(subarraySumK(arr, k2));
        System.out.println(longestSubArrayK(arr, k2));
    }

    // Longest Consecutive Sequence
    public static int longestConsecutiveSeq(int[] arr) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        int longest = 0;

        for (int num : arr) {

            if (!set.contains(num - 1)) {

                int current = num;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }

    // Top K Frequent Elements
    public static int[] topKFrequent(int[] arr, int k) {

        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));

        for (int key : freq.keySet()) {
            maxHeap.offer(key);
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = maxHeap.poll();
        }

        return ans;
    }

    // Group Anagrams
    public static List<List<String>> groupAnagram(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {

            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }

    // Subarray Sum Equals K
    public static int subarraySumK(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix Sum -> Frequency
        map.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int num : arr) {

            sum += num;

            int need = sum - k;

            if (map.containsKey(need)) {
                count += map.get(need);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    // Longest Subarray with Sum K
    public static int longestSubArrayK(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix Sum -> First Index
        map.put(0, -1);

        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            int need = sum - k;

            if (map.containsKey(need)) {

                int length = i - map.get(need);
                maxLen = Math.max(maxLen, length);
            }

            // Store only the first occurrence
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLen;
    }
}
