/******************************************************************************

1. Binary Search
    Given a sorted integer array nums and a target, return the index of target. 
    Return -1 if it does not exist. Run in (O(log n)) time.
    Input: nums = [-1,0,3,5,9,12], target = 9
    Output: 4

2. Lower Bound / Upper Bound
    Given a sorted array nums and a target:
        Lower Bound: Find the index of the first element ≥ target.
        Upper Bound: Find the index of the first element > target.
    Input: nums = [1,2,4,4,5,6,8], target = 4
    Output: Lower Bound = 2, Upper Bound = 4
    
*. Search Insert Position - Same as binarySearch but if index not found return -1;
    
3. First & Last Position
    Given a sorted array nums and a target, find the starting and ending index of target. 
    Return [-1, -1] if not found. Run in (O(log n)) time.
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3, 4]
    
4. Search in Rotated Sorted Array
    An array of distinct integers is rotated at an unknown pivot. 
    Given this array and a target, return the index of target, 
    or -1 if it is not present. Run in (O(log n)) time.
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
    
5. Search in Rotated Sorted Array II
    An array containing duplicates is rotated at an unknown pivot. 
    Given this array and a target, return true if target is in the array, or false otherwise.
    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true
    
6. Find Peak Element
    A peak element is strictly greater than its neighbors ((nums[-1] = nums[n] = - infinity)). 
    Find any peak element in an integer array and return its index. Run in (O(log n)) time.
    Input: nums = [1,2,3,1]
    Output: 2 (index of 3)
    
7. Koko Eating Bananas
    Given n piles of bananas where the i-th pile has piles[i] bananas, 
    and h hours to eat them all. Find the minimum integer eating 
    speed k (bananas/hour) to finish all piles within h hours.
    Input: piles = [3,6,7,11], h = 8
    Output: 4
    
8. Capacity to Ship Packages
    Given an array weights of packages to ship in the given order, 
    find the minimum ship weight capacity needed to transfer all packages within days.
    Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
    Output: 15
    
9. Split Array Largest Sum
    Given an array nums and an integer k, split the array into k non-empty contiguous subarrays. 
    Find the minimum possible value of the maximum subarray sum.
    Input: nums = [7,2,5,10,8], k = 2
    Output: 18 (split into [7,2,5] and [10,8])

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
		int[] nums1 = {-1, 0, 3, 5, 9, 12};
		int target1 = 9;
		
		int[] nums2 = {1, 2, 4, 4, 5, 6, 8};
		int target2 = 4;
		
		int[] nums3 = {5,7,7,8,8,10};
		int target3 = 8;
		
		int[] nums4 = {4,5,6,7,0,1,2};
		int target4 = 4;
		
		int[] nums5 = {2,5,6,0,0,1,2};
		int target5 = 0;
		
		int[] nums6 = {1, 2, 3, 1};
		
		int[] nums7 = {3, 6, 7, 11};
		int hours = 8
		
		int[] nums8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int days8 = 5;
		
		int[] nums9 = {7, 2, 5, 10, 8};
		int k9 = 2;
		
		System.out.println(binarySearch(nums1, target1));
		System.out.println(lowerBound(nums2, target2) + " " + upperbound(nums2, target2));
		System.out.println(firstAndLastposition(nums3, target3));
		System.out.println(rotatedSearch(nums4, target4));
		System.out.println(duplicatRotatedSearch(nums5, target5));
		System.out.println(findPeakElement(nums6));
		System.out.println(minEatingSpeed(nums7, hours));
		System.out.println(shipWithinDays(nums8, days8));
		System.out.println(splitArray(nums9, k9));
	}
	
	public static int binarySearch(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
    
    public static int lowerBound(int[] nums, int target) {
    
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
    
        while (left <= right) {
    
            int mid = left + (right - left) / 2;
    
            if (nums[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return ans;
    }
    
    public static int upperBound(int[] nums, int target) {
    
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
    
        while (left <= right) {
    
            int mid = left + (right - left) / 2;
    
            if (nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    
        return ans;
    }
    
    public static int[] firstAndLastposition(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        int first = -1;
        int last = -1;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                first = mid;
                right = mid - 1;

            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        left = 0;
        right = nums.length - 1;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                last = mid;
                last = mid + 1;

            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return new int[] {first, last};
    }
    
    public static int rotatedSearch(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) return mid;
            
            if (nums[left] <= nums[mid]) {
                
                if (target >= nums[left] && target < nums[mid]) {
                    
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            else {
                
                if (target > nums[mid] && target <= nums[right]) {
                    
                    left = mid + 1;
                } else {
                    
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    public static boolean duplicatRotatedSearch(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                true;
            }
            
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            }
            
            else if (nums[left] <= nums[mid]) {
                
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                
            }
            
            else {
                
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return false;
    }
    
    public static int findPeakElement(int[] nums) {
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    public static int minEatingSpeed(int[] piles, int h) {
        
        int left = 1;
        int right = 0;
        
        for(int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            
            int mid = left + (right - left) / 2;
            
            int hours = 0;
            
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // Math.ceil(pile / mid)
            }
            
            if (hours <= h) {
                right = mid;
                
            } else {
                
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    public static int shipWithinDays(int[] weights, int days) {
        
        int left = 0;
        int right = 0;
        
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        
        
        while (left < right) {
            
            int mid = left + (right - left) / 2;
            
            int requiredDays = 1;
            int currentLoad = 0;
            
            for (int weight : weights) {
                
                if (currentLoad + weight > mid) {
                    requiredDays++;
                    currentLoad = 0;
                }
                
                currentLoad += weight;
            }
            
            if (requiredDays <= days) {
                
                right = mid;
            } else {
                
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    public static int splitArray(int[] nums, int k) {
        
        int left = 0;
        int right = 0;
        
        for (int num : nums) {
            
            left = math.max(left, num);
            right += num;
        }
        
        while (left < right) {
            
            int mid = left + (right - left) / 2;
            
            int subarray = 1;
            int currentSum = 0;
            
            for (int num : nums) {
                
                if (currentSum + num > mid) {
                    subarray++;
                    currentSum = 0;
                }
                
                currentSum += num;
            }
            
            if (subarray <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}
