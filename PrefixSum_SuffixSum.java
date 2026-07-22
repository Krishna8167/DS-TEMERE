/* 
    Solve these in order:

    Build the Prefix Sum array.
    Return the sum from index L to R.
    Answer multiple range-sum queries using one Prefix Sum array.
    Find the pivot index (uses prefix/suffix thinking).
    Find the equilibrium index.
    
*/
import java.util.*;

public class Main
{
	public static void main(String[] args) {
		
		int[] arr = {4, 2, 7, 1, 9, 3, 5, 8, 12, 10, 6, 11};
		
		int L = 3;
		int R = 9;
		int[] prefixsum = PrefixSum(arr);
		int[] suffixsum = suffixSum(arr);
		
		System.out.println(Arrays.toString(prefixsum));
		System.out.println(SumRange(prefixsum, L, R));
		System.out.println(MultiqueryPrefix(prefixsum, L, R));
		System.out.println(pivot(prefixsum, arr));

		
	}
	
	public static int[] PrefixSum(int[] arr) {
	    
	    int[] prefixsum = new int[arr.length];
	    prefixsum[0] = arr[0];
	    
	    for (int i = 1; i < arr.length; i++) {
	        
	        prefixsum[i] = arr[i] + prefixsum[i - 1];
	        
	    }
	    return prefixsum;
	    
	}
	
    public static int[] suffixSum(int[] arr) {
    	    
    	    int[] suffixsum = new int[arr.length];
    	    
    	    suffixsum[arr.length - 1] = arr[arr.length - 1];
    	    
    	    for (int i = arr.length - 2; i >= 0; i--) {
    	        
    	        suffixsum[i] = arr[i] + suffixsum[i + 1];
    	        
    	    }
    	    return suffixsum;
    	    
    	}
	
	
	public static int SumRange(int[] prefixsum, int L, int R) {
	    
	    if (L < 0 || R < 0 || L >= prefixsum.length || R >= prefixsum.length || L > R) {
        return -1; 
    }
	    
	    if (L == 0) {
        return prefixsum[R];
    }

    return prefixsum[R] - prefixsum[L - 1];
	}
	
	public static int MultiqueryPrefix(int[] prefixsum, int L, int R) {
	    
	    if (L == 0) {
	        return prefixsum[R];
	    }
	    
	    return prefixsum[R] - prefixsum[L - 1];
	}
	
	public static int pivot(int[] prefixsum, int[] arr) {
	    
	    
	    for (int i = 0; i < arr.length; i++) {
	        
	        
	        int leftsum = (i == 0) ? 0 : prefixsum[i - 1];
	        int rightsum = prefixsum[arr.length - 1] - arr[i] - leftsum;
	        
	        if (leftsum == rightsum) {
	            return i;
	        }
	    }
	    
	    return -1;
	}
	
}
