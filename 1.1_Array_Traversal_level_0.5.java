import java.util.*;

public class Main
{
	public static void main(String[] args) {
		int[] arr = {23, 45, 56, 67, 989, 6};
		int target = 6;
		
		System.out.println(MaxElement(arr));
		System.out.println(MinElement(arr));
		System.out.println(SumOfElement(arr));
		System.out.println(Arrays.toString(countEvenandOdd(arr)));
		System.out.println(LSearch(arr, target));
		System.out.println(isSorted(arr));
		System.out.println(secondLargest(arr));

	}
	
	public static int MaxElement(int[] arr) {
	     
	     int max = arr[0];
	    
	    for (int i = 1; i <= arr.length - 1; i++) {
	        if (max < arr[i]) {
	            max = arr[i];
	        }
	    }
	    return max;
	}
	
	public static int MinElement(int[] arr) {
	     
	     int min = arr[0];
	    
	    for (int i = 1; i <= arr.length - 1; i++) {
	        if (min > arr[i]) {
	            min = arr[i];
	        }
	    }
	    return min;
	}
	
	public static int SumOfElement(int[] arr) {
	     
	     int sum = arr[0];
	    
	    for (int i = 1; i <= arr.length - 1; i++) {
	        sum += arr[i];
	    }
	    return sum;
	}
	
	public static int[] countEvenandOdd(int[]arr) {
	    
	    int Evencount = 0;
	    
	    for (int i = 0; i < arr.length ; i++) {
	        
	        if (arr[i] % 2 == 0) {
	            Evencount++;
	        }
	    }
	    
	    return new int[] {Evencount, arr.length - Evencount};
	}
	
	public static int LSearch(int[] arr, int target) {
	    
	    for (int i = 0; i < arr.length; i++) {
	        if (arr[i] == target) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public static boolean isSorted(int[] arr) {
	    
	    for (int i = 0 ; i < arr.length - 1; i++) {
	        if (arr[i] > arr[i+1]) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static int secondLargest(int[] arr) {

    if (arr.length < 2) {
        throw new IllegalArgumentException("Array must contain at least two elements.");
    }

    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;

    for (int num : arr) {

        if (num > largest) {
            secondLargest = largest;
            largest = num;
        } 
        else if (num > secondLargest && num != largest) {
            secondLargest = num;
        }

    }

    return secondLargest;
}
}
