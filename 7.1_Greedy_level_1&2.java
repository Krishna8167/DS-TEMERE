/******************************************************************************
GREEDY LEVEL 1 AND LEVEL 2.

LEVEL - 1
1. Assign Cookies Problem
    Given two arrays: g (children's greed) and s (cookie sizes). A child is content if a cookie's size ≥ their greed.
    Each child can get at most one cookie. Return the maximum number of content children.
    Input: g = [1,2,3], s = [1,1]
    Output: 1

2. Lemonade ChangeProblem
    Each lemonade costs $5. Customers buy one by one, paying with a $5, $10, or $20 bill. You start with $0 cash.
    Return true if you can provide the exact change to every customer in order, else false.
    Input: bills = [5,5,5,10,20]
    Output: true

3. Maximum Units on a TruckProblem
    Given boxTypes where boxTypes[i] = [boxCount, unitsPerBox], and a maximum capacity truckSize (total boxes allowed).
    Return the maximum total units you can put on the truck.
    Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
    Output: 8

LEVEL - 2

4. Jump Game I Problem
    Given an integer array nums. You start at index 0. Each element represents your maximum jump distance from that position.
    Return true if you can reach the last index, else false.
    Input: nums = [2,3,1,1,4]
    Output: true

5. Jump Game II Problem
    Given an integer array nums where you start at index 0. Each element represents your maximum jump distance.
    Return the minimum number of jumps needed to reach the last index. (Assume you can always reach it).
    Input: nums = [2,3,1,1,4]
    Output: 2

6. Gas Station Problem
    There are n gas stations along a circular route. Given arrays gas[i] (fuel available) and cost[i] (fuel needed to reach station i+1).
    Return the starting station index to complete one clockwise circuit. Return -1 if impossible.
    Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    Output: 3

*******************************************************************************/
import java.util.*;

public class Main
{
	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3};
		int[] nums1b = {1, 1};

		int[] nums2 = {5,5,5,10,20};
		
		int[][] nums3 = {
		    {1, 3},
		    {2, 2},
		    {3, 1}
		};
		int trucksize = 4;
		
		int[] nums4 = {2,3,1,1,4};
		
		int[] nums5 = {2,3,1,1,4};
		
		int[] gas = {1, 2, 3, 4, 5};
		int[] cost = {3, 4, 5, 1, 2};

		System.out.println(findContentChildren(nums1, nums1b));
		System.out.println(lemonadeChange(nums2));
		System.out.println(maximumUnits(nums3, trucksize));
		System.out.println(canJump(nums4));
		System.out.println(jump(nums5));
		System.out.println(canCompleteCircuit(gas, cost));

	}

	public static int findContentChildren(int[] greed, int[] s) {

		Arrays.sort(greed);
		Arrays.sort(s);

		int i = 0;
		int j = 0;

		while (i < greed.length && j < s.length) {

			if (s[j] >= greed[i]) {
				i++;
				j++;
			} else {
				j++;
			}
		}

		return i;
	}

	public static boolean lemonadeChange(int[] bills) {

		int five = 0;
		int ten = 0;

		for (int bill : bills) {

			if (bill == 5) {
				five++;
			} else if (bill == 10) {

				if (five == 0) {
					return false;
				}

				five--;
				ten++;
			} else {

				if (ten > 0 && five > 0) {
					ten--;
					five--;
				} else if (five >= 3) {
					five -= 3;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int maximumUnits(int[][] boxType, int trucksize) {
	    
	    Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
	    
	    int units = 0;
	    
	    for (int[] box : boxTypes) {
	        
	        int boxesTaken = Math.min(box[0], truckSize);
	        
	        units += boxesTaken * box[1];
	        
	        truckSize -= boxesTaken;
	        
	        if (truckSize == 0) {
	            break;
	        }
	    }
	    
	    return units;
	}
	
	public static boolean canJump(int[] nums) {
	    
	    int farthest = 0;
	    
	    for (int i = 0; i < nums.length; i++) {
	        
	        if (i > farthest) {
	            return false;
	        }
	        
	        farthest = Math.max(farthest, nums[i] + i);
	        
	        if (farthest >= nums.length - 1)
	            return true;
	    }
	    
	    return true;
	}
	
	public static int jump(int[] nums) {
	    
	    int jumps = 0;
	    int farthest = 0;
	    int currentend = 0;
	    
	    for (int i = 0; i < nums.length - 1; i++ ) {
	        
	        farthest = Math.max(farthest, i + nums[i]);
	        
	        if (i == currentend) {
	            jumps++;
	            currentend = farthest;
	        }
	    }
	    
	    return jumps;
	}
	
	public static boolean canCompleteCircuit(int[] gas, int[] cost) {
	    
	    int totalgas = 0;
	    int totalcost = 0;
	    
	    int tank = 0;
	    int start = 0;
	    
	    for (int i = 0; i < gas.length; i++) {
	        
	        totalGas += gas[i];
	        totalcost += cost[i];
	        
	        tank += gas[i] - cost[i];
	        
	        if(tank < 0) {
	            start = i + 1;
	            tank = 0;
	        }
	    }
	    
	    if (totalGas < totalCost)
	        return -1;
	        
	        return start;
	}
}
