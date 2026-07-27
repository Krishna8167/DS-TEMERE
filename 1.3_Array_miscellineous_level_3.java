/******************************************************************************
Core Array patterns:

 1. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, 
    compute how much water it can trap after raining.
    Example: Input [0,1,0,2,1,0,1,3,2,1,2,1] 
    Output 6.
    
2. Rotate Array
    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
    Example: Input nums = [1,2,3,4,5,6,7], 
    k = 3
    Output [5,6,7,1,2,3,4].
    
3. Majority Element - (O(1) auxillary space - boyers moore voting algorithm)
    Given an array nums of size n, return the majority element. The majority element is the element that appears more than ⌊n / 2⌋ times.
    You may assume that the majority element always exists in the array.
    Example: Input [3,2,3]
    Output 3
    
4. Next Permutation
    A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
    The next permutation of an array of integers is the next lexicographically greater permutation of its integer. 
    If such arrangement is not possible, the array must be rearranged as the lowest possible order (sorted in ascending order). 
    The replacement must be in-place.
    Example: Input [1,2,3]
    Output [1,3,2].
    
5. Merge Intervals
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
    and return an array of the non-overlapping intervals that cover all the intervals in the input.
    Example: Input [[1,3],[2,6],[8,10],[15,18]]
    Output [[1,6],[8,10],[15,18]] because [1,3] and [2,6] overlap.
    
6. Set Matrix Zeroes
    Given an m x n integer matrix matrix, 
    if an element is 0, set its entire row and column to 0's. You must do it in-place.
    
7. Spiral Matrix
    Given an m x n matrix, return all elements of the matrix in spiral order.
    Example: Input [[1,2,3],[4,5,6],[7,8,9]]
    Output [1,2,3,6,9,8,7,4,5]
    
*******************************************************************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // ==========================================================
        // Test Data
        // ==========================================================

        int[] rainHeights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        int[] rotateNums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        int[] majorityNums = {2, 2, 1, 1, 1, 2, 2};

        int[] permutation = {1, 2, 3};

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] zeroMatrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        int[][] spiralMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // ==========================================================
        // 1. Trapping Rain Water
        // ==========================================================

        // System.out.println(trapRainWater(rainHeights));

        // ==========================================================
        // 2. Rotate Array
        // ==========================================================

        // System.out.println(Arrays.toString(rotateArray(rotateNums, k)));

        // ==========================================================
        // 3. Majority Element
        // ==========================================================

        // System.out.println(majorityElement(majorityNums));

        // ==========================================================
        // 4. Next Permutation
        // ==========================================================

        // System.out.println(Arrays.toString(nextPermutation(permutation)));

        // ==========================================================
        // 5. Merge Intervals
        // ==========================================================

        // int[][] merged = mergeIntervals(intervals);
        // for (int[] interval : merged) {
        //     System.out.println(Arrays.toString(interval));
        // }

        // ==========================================================
        // 6. Set Matrix Zeroes
        // ==========================================================

        // int[][] result = setMatrixZeroes(zeroMatrix);
        // printMatrix(result);

        // ==========================================================
        // 7. Spiral Matrix
        // ==========================================================

        List<Integer> spiral = spiralOrder(spiralMatrix);
        System.out.println(spiral);
    }

    // ==========================================================
    // 1. Trapping Rain Water
    // Time : O(n)
    // Space: O(1)
    // ==========================================================

    public static int trapRainWater(int[] heights) {

        int left = 0;
        int right = heights.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            leftMax = Math.max(leftMax, heights[left]);
            rightMax = Math.max(rightMax, heights[right]);

            if (leftMax <= rightMax) {

                water += leftMax - heights[left];
                left++;

            } else {

                water += rightMax - heights[right];
                right--;
            }
        }

        return water;
    }

    // ==========================================================
    // 2. Rotate Array
    // Time : O(n)
    // Space: O(n)
    // ==========================================================

    public static int[] rotateArray(int[] nums, int k) {

        int n = nums.length;
        k %= n;

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }

        return result;
    }

    // ==========================================================
    // 3. Majority Element (Boyer-Moore)
    // Time : O(n)
    // Space: O(1)
    // ==========================================================

    public static int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    // ==========================================================
    // Helper Method
    // ==========================================================

    public static void reverse(int[] nums, int left, int right) {

        while (left < right) {

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    // ==========================================================
    // 4. Next Permutation
    // Time : O(n)
    // Space: O(1)
    // ==========================================================

    public static int[] nextPermutation(int[] nums) {

        int n = nums.length;
        int pivot = -1;

        for (int i = n - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        if (pivot == -1) {

            reverse(nums, 0, n - 1);
            return nums;
        }

        for (int i = n - 1; i > pivot; i--) {

            if (nums[i] > nums[pivot]) {

                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot] = temp;
                break;
            }
        }

        reverse(nums, pivot + 1, n - 1);

        return nums;
    }

    // ==========================================================
    // 5. Merge Intervals
    // Time : O(n log n)
    // Space: O(n)
    // ==========================================================

    public static int[][] mergeIntervals(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return new int[][] {};
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            if (current[1] >= intervals[i][0]) {

                current[1] = Math.max(current[1], intervals[i][1]);

            } else {

                result.add(current);
                current = intervals[i];
            }
        }

        result.add(current);

        return result.toArray(new int[result.size()][]);
    }

    // ==========================================================
    // 6. Set Matrix Zeroes
    // Time : O(m × n)
    // Space: O(1)
    // ==========================================================

    public static int[][] setMatrixZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstColZero = false;

        // Mark rows and columns

        for (int i = 0; i < rows; i++) {

            if (matrix[i][0] == 0) {
                firstColZero = true;
            }

            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == 0) {

                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Fill zeroes

        for (int i = rows - 1; i >= 0; i--) {

            for (int j = cols - 1; j >= 1; j--) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (firstColZero) {
                matrix[i][0] = 0;
            }
        }

        return matrix;
    }

    // ==========================================================
    // 7. Spiral Matrix
    // Time : O(m × n)
    // Space: O(1) excluding output
    // ==========================================================

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // Left -> Right

            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }

            top++;

            // Top -> Bottom

            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }

            right--;

            // Right -> Left

            if (top <= bottom) {

                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }

                bottom--;
            }

            // Bottom -> Top

            if (left <= right) {

                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }

                left++;
            }
        }

        return result;
    }

    // ==========================================================
    // Utility Method
    // ==========================================================

    public static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {

            for (int value : row) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }
}
