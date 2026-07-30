/******************************************************************************
HEAP - LEVEL 1 AND LEVEL 2

Level 1

1. Last Stone WeightProblem
    Given an array of integers stones representing the weights of stones. 
    In each turn, smash the two heaviest stones together. 
    If their weights x and y are equal, both are destroyed. If x != y, 
    the stone with weight x is destroyed, and the other gets a new weight of y - x. 
    Return the weight of the last remaining stone, or 0 if none are left.
    Input: stones = [2,7,4,1,8,1]
    Output: 1
    
Some of the problems are done previously like K frequent , K largest etc belongs to this pattern.
    
Level 2

2. K Closest Points to Origin Problem
    Given an array of points where points[i] = [x, y] represents a point on the X-Y plane, and an integer k. 
    Return the k closest points to the origin (0, 0). The distance between two points is the standard Euclidean distance.
    Input: points = [[1,3],[-2,2]], k = 1
    Output: [[-2,2]]
    
3. Find K Closest Elements Problem
    Given a sorted integer array arr and two integers k and x. Return the k closest integers to x in the array. 
    Result must be sorted in ascending order. An integer a is closer to x than b if |a - x| < |b - x|, or if |a - x| == |b - x| and a < b.
    Input: arr = [1,2,3,4,5], k = 4, x = 3
    Output: [1,2,3,4]
    
4. Sort Characters By Frequency Problem
    Given a string s. Sort it in decreasing order based on the frequency of the characters. 
    The frequency of a character is the number of times it appears in the string. Return the sorted string.
    Input: s = "tree"
    Output: "eert"

*******************************************************************************/

import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
		int[] stones = {2,7,4,1,8,1};
		
		int[][] points = {
                {1,3},
                {-2,2}
        };

        int k = 1;
        
        int[] arr3 = {1,2,3,4,5};

        int k3 = 4;

        int x3 = 3;
        
        String s = "Tree";
		
		System.out.println(lastStoneWeight(stones));
		System.out.println(Arras.deepToString(KClosest(points, k)));
		System.out.println(findClosestElements(arr3,k3,x3);
		System.out.println(frequencySort(s));
	}
	
	public static int lastStoneWeight(int[] stones) {
	    
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	    
	    for (int stone : stones) {
	        maxheap.offer(stone);
	    }
	    
	    while (maxHeap.size() > 1) {
	        
	        int first = maxHeap.poll();
	        int second = maxHeap.poll();
	        
	        if (first != second) {
	            maxHeap.offer(first - second);
	        }
	    }
	    
	    return maxHeap.isEmpty() ? 0 : maxHeap.peek();
	}
	
	public static int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) ->
                        (a[0] * a[0] + a[1] * a[1]) -
                        (b[0] * b[0] + b[1] * b[1])
        );

        for (int[] point : points) {
            minHeap.offer(point);
        }

        int[][] ans = new int[k][2];

        for (int i = 0; i < k; i++) {
            ans[i] = minHeap.poll();
        }

        return ans;
    }
    
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {

            int distA = Math.abs(a - x);
            int distB = Math.abs(b - x);

            if (distA == distB)
                return b - a;

            return distB - distA;
        });

        for (int num : arr) {

            maxHeap.offer(num);

            if (maxHeap.size() > k)
                maxHeap.poll();
        }

        List<Integer> ans = new ArrayList<>();

        while (!maxHeap.isEmpty())
            ans.add(maxHeap.poll());

        Collections.sort(ans);

        return ans;
    }
    
    public static String frequencySort(String s) {

        HashMap<Character, Integer> freq = new HashMap<>();

        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> maxHeap =
                new PriorityQueue<>(
                        (a, b) -> freq.get(b) - freq.get(a)
                );

        maxHeap.addAll(freq.keySet());

        StringBuilder ans = new StringBuilder();

        while (!maxHeap.isEmpty()) {

            char ch = maxHeap.poll();

            int count = freq.get(ch);

            while (count-- > 0) {
                ans.append(ch);
            }
        }

        return ans.toString();
    }
}
