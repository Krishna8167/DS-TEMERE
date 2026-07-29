/******************************************************************************

TWO-POINTER LEVEL_3

1. Shortest Unsorted Continuous Subarray
    Find the length of the shortest continuous subarray that, if sorted in ascending order, 
    makes the entire array sorted in ascending order.
    Input: nums = [2, 6, 4, 8, 10, 9, 15]
    Output: 5 (Sorting the subarray [6, 4, 8, 10, 9] fixes the whole array)
    
2. Minimum Difference Between Two Arrays
    Find the smallest absolute difference between any single element from 
    the first array and any single element from the second array.
    Input: arr1 = [1, 3, 15, 11, 2], arr2 = [23, 127, 235, 19, 8]
    Output: 3 (The pair [11, 8] yields the minimum difference: 11 - 8 = 3)
        
3. Smallest Pair Difference (K-th Smallest Distance)
    Find the k-th smallest absolute difference among all possible pairs of elements 
    in a given integer array.
    Input: nums = [1, 3, 1], k = 1
    Output: 0 (All possible pair absolute differences are [0, 2, 2]. The 1st smallest is 0)

These introduce new patterns rather than repeating old ones.
Doing it Later... 

Partition Labels (greedy + pointers)
Interval List Intersections (two sorted lists)
Minimum Window Subsequence (advanced two pointers)
Append Characters to String to Make Subsequence
Number of Subsequences That Satisfy the Given Sum Condition
Longest Word in Dictionary Through Deleting
Push Dominoes (simulation using pointer concepts)

*******************************************************************************/
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] nums1 = {2, 6, 4, 8, 10, 9, 15};
        
        int[] nums2a = {1, 3, 15, 11, 2};
        int[] nums2b = {23, 127, 235, 19, 8};
        
        int[] nums3 = {1, 3, 1};
        int k3 = 1;
        
        System.out.println(findUnsortedSubArray(nums));
        System.out.println(minimumDifference(arr1, arr2));
        System.out.println(smallestDistancePair(nums3, k3));
    }
    
    public static int findUnsortedSubArray(int[] nums) {
        
        int n = nums.length;
        
        int right = -1;
        int maxSeen = nums[0];
        
        for (int i = 1; i < n; i++) {
            
            if (nums[i] < maxSeen) {
                right = i;
            } else {
                
                maxSeen = nums[i];
            }
        }
        
        if (right == -1) {
            return 0;
        }
        
        int left = -1;
        int minSeen = nums[n-1];
        
        for (int i = n - 2; i >= 0; i-- ) {
            
            if (nums[i] > minSeen) {
                
                left = i;
                
            } else  {
                
                minSeen = nums[i];
            }
        }
        
        return right - left + 1;
    }
    
    public static int minimumDifference(int[] arr1, int[] arr2) {
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int i = 0;
        int j = 0;
        
        int minDiff = Integer.MAX_VALUE;
        
        while (i < arr.length && j < arr.length) {
            
            int diff = Math.abs(arr1[i] - arr2[j]);
            
            minDiff = Math.min(minDiff, diff);
            
            if (arr1[i] < arr2[j]) {
                
                i++;
            } else {
                
                j++;
            }
        }
        return minDiff;
    }
    
    public static int smallestDistancePair(int[] nums, int k) {
        
        Arrays.sort(nums);
        
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        
        while (low < high) {
            
            int mid = low + (high - low) / 2;
            
            if ( countPairs(nums, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public static int countPairs(int[] nums, int limit) {
        
        int count = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++ ) {
            
            while (nums[right] - nums[left] > limit) {
                left++;
            }
            
            count += right - left;
        }
        
        return count;
    }
}
