/******************************************************************************
Optimization of Arrays:

1. Kadane's Algorithm
    Problem Statement:Given an integer array nums, find the subarray with the largest sum and return its sum.
    Example Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Expected Output Concept: The algorithm should identify [4,-1,2,1] as the contiguous subarray with the maximum sum of 6.

2. Best Time to Buy & Sell Stock
    Problem Statement:You are given an array prices where prices[i] is the price of a given stock on the \(i^{th}\) day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. 
    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
    Example Input: prices = [7,1,5,3,6,4]
    Expected Output Concept: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
    
3. Maximum Product Subarray
    Problem Statement:Given an integer array nums, find a contiguous non-empty subarray that has the largest product, and return the product. The test cases are generated so that the answer will fit in a 32-bit integer.
    Example Input: nums = [2,3,-2,4]
    Expected Output Concept: The subarray [2,3] has the maximum product of 6.
    
4. Product of Array Except Self
    Problem Statement:Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i]. You must write an algorithm that runs in \(O(n)\) time and without using the division operation.
    Example Input: nums = [1,2,3,4]
    Expected Output Concept: answer[0] is \(2 \times 3 \times 4 = 24\), answer[1] is \(1 \times 3 \times 4 = 12\), and so on, resulting in [24,12,8,6].

*******************************************************************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // 1. Kadane's Algorithm
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // 2. Best Time to Buy & Sell Stock
        int[] arr2 = {7, 1, 5, 3, 6, 4};

        // 3. Maximum Product Subarray
        int[] arr3 = {2, 3, -2, 4};

        // 4. Product of Array Except Self
        int[] arr4 = {1, 2, 3, 4};

        // System.out.println(kadane(arr1));

        // System.out.println(bestTimeToBuyAndSellStock(arr2));

         System.out.println(maximumProductSubarray(arr3));

        // System.out.println(Arrays.toString(productExceptSelf(arr4)));
    }

    /**************************************************************************
     * 1. Kadane's Algorithm
     * Time  : O(n)
     * Space : O(1)
     **************************************************************************/
    public static int kadane(int[] arr) {

        int currentSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(currentSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**************************************************************************
     * 2. Best Time to Buy & Sell Stock
     * Time  : O(n)
     * Space : O(1)
     **************************************************************************/
    public static int bestTimeToBuyAndSellStock(int[] arr) {

        int minPrice = arr[0];
        int maxProfit = 0;

        for (int i = 1; i < arr.length; i++) {

            minPrice = Math.min(minPrice, arr[i]);
            maxProfit = Math.max(maxProfit, arr[i] - minPrice);
        }

        return maxProfit;
    }

    /**************************************************************************
     * 3. Maximum Product Subarray
     * NOTE:
     * This implementation is NOT complete.
     * It fails when negative numbers change the maximum product.
     * The correct solution tracks both maximum and minimum products.
     **************************************************************************/
    public static int maximumProductSubarray(int[] arr) {

        int maxProduct = arr[0];
        int minProduct = arr[0];
        int answer = arr[0];

        for (int i = 1; i < arr.length; i++) {
            
            int prev_max = maxProduct;

            maxProduct = Math.max(
                arr[i],
                Math.max(maxProduct * arr[i], minProduct * arr[i])
                );
                
            minProduct = Math.min(
                arr[i],
                Math.min(prev_max * arr[i], minProduct * arr[i])
                );
                
                answer = Math.max(answer, maxProduct);
        }

        return answer;
    }

    /**************************************************************************
     * 4. Product of Array Except Self
     * Time  : O(n)
     * Space : O(n)
     **************************************************************************/
    public static int[] productExceptSelf(int[] arr) {

        int n = arr.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];

        prefix[0] = 1;
        suffix[n - 1] = 1;

        // Prefix Product
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * arr[i - 1];
        }

        // Suffix Product
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * arr[i + 1];
        }

        // Final Answer
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }
}
