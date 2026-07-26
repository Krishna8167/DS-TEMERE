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

        // System.out.println(maximumProductSubarray(arr3));

        System.out.println(Arrays.toString(productExceptSelf(arr4)));
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

        int currentProduct = arr[0];
        int maxProduct = arr[0];

        for (int i = 1; i < arr.length; i++) {

            currentProduct = Math.max(currentProduct * arr[i], arr[i]);
            maxProduct = Math.max(maxProduct, currentProduct);
        }

        return maxProduct;
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
