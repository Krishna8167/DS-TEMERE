/******************************************************************************
1. Maximum Sum Subarray of Size K
    Problem Statement: Find the maximum sum of any contiguous subarray of size K.
    Input:An array of integers numsAn integer K
    Output: An integer representing the maximum sum.
    Example:nums = [2, 1, 5, 1, 3, 2]
    K = 3
    Expected Result: Your code should find the highest sum for 3 consecutive elements.
    
2. First Negative Integer in Every Window of Size K
    Problem Statement: Find the first negative integer in every window of size \(K\).
    Input:An array of integers numsAn integer K
    Output: An array of integers containing the first negative number of each window. 
        If a window has no negative number, use 0.
    Example:nums = [12, -1, -7, 8, -15, 30, 16, 28]
    K = 3
    Expected Result: Your code should return an array of sizes equal to the total number of windows.
    
3. Maximum Average Subarray I
    Problem Statement: Find a contiguous subarray of size K that has the maximum average value.
    Input:An array of integers numsAn integer K
    Output: A floating-point number representing the maximum average.
    Example:nums = [1, 12, -5, -6, 50, 3]
    K = 4
    Expected Result: Your code should calculate and return the largest possible average.
    
4. Number of Subarrays of Size K with Average ≥ Threshold
    Problem Statement: Count the total number of subarrays of size \(K\) with an average greater than or equal to threshold.
    Input:An array of integers numsAn integer KAn integer threshold
    Output: An integer representing the total count of valid subarrays.
    Example:nums = [2, 2, 2, 2, 5, 5, 5, 8]
    K = 3
    threshold = 4
    Expected Result: Your code should return the total count matching this condition.
    
5. Sliding Window Maximum
    Problem Statement: Find the maximum element inside each moving window of size K.
    Input:An array of integers numsAn integer K
    Output: An array of integers containing the maximum value from each window.
    Example:nums = [1, 3, -1, -3, 5, 3, 6, 7]
    K = 3
    Expected Result: Your code should track the highest value per window position.
    
*******************************************************************************/
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // -----------------------------------------------------------------
        // Test Data
        // -----------------------------------------------------------------

        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;

        int[] arr2 = {12, -1, -7, 8, -15, 30, 16, 28};
        int k2 = 3;

        int[] arr3 = {1, 12, -5, -6, 50, 3};
        int k3 = 4;

        int[] arr4 = {2, 2, 2, 2, 5, 5, 5, 8};
        int k4 = 3;
        int threshold = 4;

        int[] arr5 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k5 = 3;

        // System.out.println(maxSumSubarraySizeK(arr1, k1));
        // System.out.println(Arrays.toString(firstNegativeInWindow(arr2, k2)));
        // System.out.println(maxAverageSubarray(arr3, k3));
        // System.out.println(countAverageGreaterThanThreshold(arr4, k4, threshold));
        System.out.println(Arrays.toString(slidingWindowMaximum(arr5, k5)));
    }

    // =====================================================================
    // 1. Maximum Sum Subarray of Size K
    // Time  : O(n)
    // Space : O(1)
    // =====================================================================

    public static int maxSumSubarraySizeK(int[] arr, int k) {

        if (arr == null || arr.length < k) {
            return 0;
        }

        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        int maxSum = currentSum;

        int left = 0;

        for (int right = k; right < arr.length; right++) {

            currentSum = currentSum - arr[left] + arr[right];

            left++;

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // =====================================================================
    // 2. First Negative Integer in Every Window of Size K
    // Time  : O(n)
    // Space : O(k)
    // =====================================================================

    public static int[] firstNegativeInWindow(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k > arr.length) {
            return new int[]{};
        }

        Queue<Integer> queue = new LinkedList<>();

        int[] result = new int[arr.length - k + 1];

        int left = 0;
        int right = 0;
        int index = 0;

        while (right < arr.length) {

            if (arr[right] < 0) {
                queue.offer(arr[right]);
            }

            if (right - left + 1 == k) {

                result[index] = queue.isEmpty() ? 0 : queue.peek();

                if (!queue.isEmpty() && arr[left] == queue.peek()) {
                    queue.poll();
                }

                left++;
                index++;
            }

            right++;
        }

        return result;
    }

    // =====================================================================
    // 3. Maximum Average Subarray
    // Time  : O(n)
    // Space : O(1)
    // =====================================================================

    public static float maxAverageSubarray(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k > arr.length) {
            return 0.0f;
        }

        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        float maxAverage = currentSum / (float) k;

        int left = 0;

        for (int right = k; right < arr.length; right++) {

            currentSum += arr[right] - arr[left];

            left++;

            maxAverage = Math.max(maxAverage, currentSum / (float) k);
        }

        return maxAverage;
    }

    // =====================================================================
    // 4. Number of Windows with Average >= Threshold
    // Time  : O(n)
    // Space : O(1)
    // =====================================================================

    public static int countAverageGreaterThanThreshold(int[] arr,
                                                       int k,
                                                       int threshold) {

        if (arr == null || arr.length == 0 || k > arr.length) {
            return 0;
        }

        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        int count = 0;

        if ((float) currentSum / k >= threshold) {
            count++;
        }

        int left = 0;

        for (int right = k; right < arr.length; right++) {

            currentSum += arr[right] - arr[left];

            left++;

            if ((float) currentSum / k >= threshold) {
                count++;
            }
        }

        return count;
    }

    // =====================================================================
    // 5. Sliding Window Maximum (Monotonic Deque)
    // Time  : O(n)
    // Space : O(k)
    // =====================================================================

    public static int[] slidingWindowMaximum(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k > arr.length) {
            return new int[]{};
        }

        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[arr.length - k + 1];

        int index = 0;

        for (int right = 0; right < arr.length; right++) {

            // Remove indices that are outside the current window
            while (!deque.isEmpty() && deque.peekFirst() <= right - k) {
                deque.pollFirst();
            }

            // Maintain decreasing order in the deque
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[right]) {
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(right);

            // Window formed
            if (right >= k - 1) {
                result[index++] = arr[deque.peekFirst()];
            }
        }

        return result;
    }
}
