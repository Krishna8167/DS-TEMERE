/******************************************************************************
GREEDY LEVEL 3 AND LEVEL 4

Level 3: Intervals
    
1. Insert Interval Problem
    Given a sorted array of non-overlapping intervals and a newInterval = [start, end]. 
    Insert newInterval into the correct position such that the intervals remain sorted and merged if they overlap.
    Input: intervals = [[1,3],[6,9]], newInterval = [[2,5]]
    Output: [[1,5],[6,9]]
    
2. Non-overlapping Intervals Problem
    Given an array of intervals. Return the minimum number of intervals 
    you need to remove to make the rest of the intervals non-overlapping.
    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    
3. Minimum Number of Arrows to Burst Balloons Problem
    Balloons are represented as horizontal intervals [xStart, xEnd]. 
    An arrow shot vertically at coordinate x bursts all balloons where xStart <= x <= xEnd. 
    Return the minimum arrows needed to burst all balloons.
    Input: points = [[10,16],[2,8],[1,6],[7,12]]
    Output: 2 
    
Level 4: Favorites VERY IMPORTANT...

4. Candy Problem
    There are n children standing in a line. Each child has a rating score. 
    Give candies to children such that every child has at least one candy, 
    and children with a higher rating than their neighbors get more candies than them. 
    Return the minimum total candies needed.
    Input: ratings = [1,0,2]
    Output: 5
    
5. Task Scheduler Problem
    Given a character array of CPU tasks and a cooling period n. Identical tasks must be separated by at least n intervals due to cooling. 
    Return the minimum total time units required to finish all tasks in any order.
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8

*******************************************************************************/
import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
	    int[][] intervals = {
	        {1, 3},
	        {6, 9}
	    };
	    
	    int[] newinterval = {2, 5};
	    
	    int[][] intervals2 = {
	        {1, 2},
	        {2, 3},
	        {3, 4},
	        {1, 3}
	    };
	    
	    int[][] points = {
	        {10, 16},
	        {2, 8},
	        {1, 6},
	        {7, 12}
	    };
	    
	    int[] rate = {1, 0, 2};
	    
	    char[]  intervals = {"A","A","A","B","B","B"};
	    int n5 = 2;
	    
		System.out.println(insert(intervals, newinterval));
		System.out.println(eraseOverlapIntervals(intervals2));
		System.out.println(findMinArrowShots(points));
		System.out.println(candy(rate));
		System.out.println(leastInterval(intervals, n5));
	}
	
	public static int[][] insert(int[][] intervals, int[] newInterval) {
	    
	    List<int[]> result = new ArrayList<>();
	    
	    int i = 0;
	    int n = intervals.length;
	    
	    while (i < n && intervals[i][1] <= newInterval[1]) {
	        
	        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
	        newInterval[1] = Math.min(newInterval[1], intervals[i][1]);
	        
	        i++;
	        
	    }
	    
	    result.add(newInterval);
	    
	     while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
	}
	
	public static int eraseOverlapIntervals(int[][] intervals) {
	    
	    Arrays.sort(intervals, (a, b) -> Interger.compare(a[0], b[0]));
	    
	    int removed = 0;
	    
	    int[] previous = intervals[0];
	    
	    for (int i = 1; i < intervals.length; i++) {
	        
	        int [] current = intervals[i];
	        
	        if (current[0] < previous[1]) {
	            
	            removed++;
	            
	            if (current[1] < previous[1]) {
	                previous = current;
	                
	            }
	        } else {
	            previous = current;
	        }
	    }
	    
	    return removed;
	}
	
	public static int findMinArrowShots(int[][] points) {
	    
	    Array.sort(points, (a, b) -> Integer.compare(a[1],b[1]));
	    
	    int arrows = 1;
	    int arrowPosition = points[0][1];
	    
	    for (int i = 1; i < points.length; i++ ) {
	        
	        if (points[i][0] > arrowPosition) {
	            
	            arrows++;
	            arrowPosition = points[i][1];
	            
	        }
	    }
	    
	    return arrows;
	}
	
	public static int candy(int[] ratings) {
	    
	    int n = ratings.length;
	    
	    int[] candies = new int[n];
	    
	    Arrays.fill(candies, 1);
	    
	    //left to right
	    for (int i = 1; i < n ; i++) {
	        
	        if (ratings[i] > rating[i - 1]) {
	            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
	        }
	    }
	    
	    //right to left
	    for (int i = n - 2 ; i >= 0; i-- ) {
	        
	        if (ratings[i] > ratings[i + 1]) {
	            
	            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
	        }
	    }
	    
	    int total = 0;
	    
	    for (int candy : candies) {
	        total += candy;
	    }
	    
	    return total;
	}
	
	public static int leastInterval(char[] tasks, int n) {
	    
	    int[] freq = new int[26];
	    
	    for (char task : tasks) {
	        freq[task - 'A']++;
	    }
	    
	    for (int f : freq) {
	        maxFreq = Math.max(maxFreq, f);
	        countMax++;
	    }
	    
	    int intervals = (maxFreq - 1) * (n + 1) + countMax;
	    
	    return Math.max(intervals, tasks.length);
	}
}
