/******************************************************************************
 *                           TWO POINTER PATTERNS
 * ---------------------------------------------------------------------------
 * LEVEL 1
 *
 * 1. Opposite-End Pointers
 *    • Reverse String
 *    • Valid Palindrome
 *    • Two Sum II
 *    • Container With Most Water
 *
 * 2. Fast & Slow Pointers
 *    • Move Zeroes
 *    • Remove Duplicates from Sorted Array
 *    • Remove Element
 *    • Sort Array By Parity
 *
 * 3. Two Pointers + Output Pointer
 *    • Merge Sorted Array
 *    • Squares of a Sorted Array
 ******************************************************************************/

import java.util.*;

public class Main {

    public static void main(String[] args) {

        /* ---------- Strings ---------- */

        String word = "suppose";
        String palindrome = "nitin";

        /* ---------- Arrays ---------- */

        int[] numbers = {2, 7, 11, 15};
        int target = 18;

        int[] zeroes = {0, 0, 7, 2, 9};

        int[] duplicates = {1, 1, 2, 3, 3, 4, 4};

        int[] parity = {0, 1, 2, 3, 4, 5, 6, 7, 6, 15};

        int[] elements = {0, 1, 2, 2, 3, 4, 0, 2};
        int remove = 2;

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = {3, 5, 6};
        int n = 3;

        int[] squares = {-4, -3, -1, 0, 3, 10};

        /* ---------- Function Calls ---------- */

        System.out.println(reverseString(word));
        System.out.println(isPalindrome(palindrome));
        System.out.println(Arrays.toString(twoSumPointerPattern(numbers, target)));
        System.out.println(maxWaterContainer(numbers));
        System.out.println(Arrays.toString(moveZeroes(zeroes)));
        System.out.println(removeDuplicateSortedArray(duplicates));
        System.out.println(Arrays.toString(removeElement(elements, remove)));
        System.out.println(Arrays.toString(sortByParity(parity)));
        System.out.println(Arrays.toString(mergeSortedArray(nums1, nums2, m, n)));
        System.out.println(Arrays.toString(squareSortedArray(squares)));
    }
	
/* ==========================================================
                OPPOSITE-END POINTERS
   ========================================================== */

public static String reverseString(String str) {

    char[] chars = str.toCharArray();

    int left = 0;
    int right = chars.length - 1;

    while (left < right) {

        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;

        left++;
        right--;
    }

    return new String(chars);
}

public static boolean isPalindrome(String str) {

    int left = 0;
    int right = str.length() - 1;

    while (left < right) {

        while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
            left++;
        }

        while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
            right--;
        }

        if (Character.toLowerCase(str.charAt(left)) !=
            Character.toLowerCase(str.charAt(right))) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}

public static int[] twoSumPointerPattern(int[] arr, int target) {

    int left = 0;
    int right = arr.length - 1;

    while (left < right) {

        int sum = arr[left] + arr[right];

        if (sum == target) {
            return new int[] {left, right};
        }

        if (sum > target) {
            right--;
        } else {
            left++;
        }
    }

    return new int[] {-1, -1};
}

public static int maxWaterContainer(int[] arr) {

    int left = 0;
    int right = arr.length - 1;

    int maxArea = 0;

    while (left < right) {

        int width = right - left;
        int height = Math.min(arr[left], arr[right]);
        int currentArea = width * height;

        maxArea = Math.max(maxArea, currentArea);

        if (arr[left] <= arr[right]) {
            left++;
        } else {
            right--;
        }
    }

    return maxArea;
}

/* ==========================================================
                FAST & SLOW POINTERS
   ========================================================== */

public static int[] moveZeroes(int[] arr) {

    int slow = 0;

    for (int fast = 0; fast < arr.length; fast++) {

        if (arr[fast] != 0) {

            int temp = arr[fast];
            arr[fast] = arr[slow];
            arr[slow] = temp;

            slow++;
        }
    }

    return arr;
}

public static int removeDuplicateSortedArray(int[] arr) {

    if (arr.length == 0) {
        return 0;
    }

    int slow = 0;

    for (int fast = 1; fast < arr.length; fast++) {

        if (arr[fast] != arr[slow]) {

            slow++;
            arr[slow] = arr[fast];
        }
    }

    return slow + 1;
}

public static int[] removeElement(int[] arr, int target) {

    int slow = 0;

    for (int fast = 0; fast < arr.length; fast++) {

        if (arr[fast] != target) {

            int temp = arr[fast];
            arr[fast] = arr[slow];
            arr[slow] = temp;

            slow++;
        }
    }

    return Arrays.copyOfRange(arr, 0, slow);
}

public static int[] sortByParity(int[] arr) {

    int slow = 0;

    for (int fast = 0; fast < arr.length; fast++) {

        if (arr[fast] % 2 == 0) {

            int temp = arr[fast];
            arr[fast] = arr[slow];
            arr[slow] = temp;

            slow++;
        }
    }

    return arr;
}

/* ==========================================================
            TWO POINTERS + OUTPUT POINTER
   ========================================================== */

public static int[] mergeSortedArray(int[] arr1, int[] arr2, int m, int n) {

    int p1 = m - 1;
    int p2 = n - 1;
    int write = m + n - 1;

    while (p2 >= 0) {

        if (p1 >= 0 && arr1[p1] > arr2[p2]) {

            arr1[write] = arr1[p1];
            p1--;

        } else {

            arr1[write] = arr2[p2];
            p2--;
        }

        write--;
    }

    return arr1;
}

public static int[] squareSortedArray(int[] arr) {

    int left = 0;
    int right = arr.length - 1;

    int write = arr.length - 1;

    int[] result = new int[arr.length];

    while (left <= right) {

        int leftSquare = arr[left] * arr[left];
        int rightSquare = arr[right] * arr[right];

        if (leftSquare > rightSquare) {

            result[write] = leftSquare;
            left++;

        } else {

            result[write] = rightSquare;
            right--;
        }

        write--;
    }

    return result;
}
}
