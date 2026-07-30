/******************************************************************************
STACK

Level 3: Intermediate
1. Daily Temperatures Problem
    Given an array of integers temperatures representing daily temperatures. 
    Return an array answer where answer[i] is the number of days you have to wait after the (i)-th day to get a warmer temperature. 
    Return 0 if impossible.
    Input: temperatures = [73,74,75,71,69,72]
    Output: [1,1,0,2,1,0]
    
2. Online Stock Span Problem
    Design a class to collect daily price quotes of a stock and return the span of that stock's price for the current day. 
    The span is the maximum number of consecutive days (starting today and going backward) the price was (le ) today's price.
    Input: ["StockSpanner","next","next","next"], [[],[100],[80],[120]]
    Output: [null,1,1,3]
    
3. Asteroid Collision Problem
    Given an array asteroids representing moving space rocks. The absolute value is the size, and the sign (+/-) is the direction (right/left). 
    Rocks move at the same speed. Colliding rocks destroy the smaller one; equal sizes destroy both. 
    Return the final state.
    Input: asteroids = [5,10,-5]
    Output: [5,10]
    
4. Remove K Digits Problem
    Given a non-negative integer represented as a string num and an integer k. 
    Return the smallest possible integer string after removing exactly k digits from num. 
    Remove leading zeros from the final result.
    Input: num = "1432219", k = 3
    Output: "1219"
    
Level 4: Advanced

5. Largest Rectangle in Histogram Problem
    Given an array of integers heights representing the bar heights of a histogram where the width of each bar is 1. 
    Return the area of the largest rectangle that can be formed within the histogram.
    Input: heights = [2,1,5,6,2,3]
    Output: 10
    
6. Maximal Rectangle Problem
    Given a 2D binary matrix filled with '0's and '1's. Find the largest rectangle containing only '1's and return its area.
    Input: matrix = [["1","0"],["1","1"]]
    Output: 2

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
		int[] temperatures = {73,74,75,71,69,72};
		
		String[] operations = {
                "StockSpanner",
                "next",
                "next",
                "next"
        };

        int[][] values = {
                {},
                {100},
                {80},
                {120}
        };
        
        int[] asteroids = {5, 10, -5};
        
        String num = "1432219";
        int k = 3;
		
		System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
		System.out.println(stockSpan(operations, values));
		System.out.println(Arrays.toString(asteroidCollision(asteroids)));
		System.out.println(removeKdigits(num, k));
		System.out.println();
		System.out.println();
	}
	
	 public static int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() &&
                    temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = stack.peek() - i;
            }

            stack.push(i);
        }

        return ans;
    }
    
    public static List<Object> stockSpan(String[] operations, int[][] values) {

        Stack<int[]> stack = new Stack<>();
        List<Object> output = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "StockSpanner":
                    output.add(null);
                    break;

                case "next":

                    int price = values[i][0];
                    int span = 1;

                    while (!stack.isEmpty() && stack.peek()[0] <= price) {
                        span += stack.pop()[1];
                    }

                    stack.push(new int[]{price, span});
                    output.add(span);
                    break;
            }
        }

        return output;
    }
    
    public static int[] asteroidCollision(int[] asteroids) {
        
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            
            boolean destroyed = false;
            
            while (
                !stack.isEmpty()
                && stack.peek() > 0 
                && asteroid < 0) {
                    
                    if (stack.peek() < -asteroid) {
                        stack.pop();
                    }
                    else if (stack.peek() == -asteroid) {
                        stack.pop();
                        destroyed = true;
                        break;
                    }
                    else {
                        destroyed = true;
                        break;
                    }
                
                }
                if (!destroyed) {
                    stack.push(asteroid);
                }
            }
            int[] ans = new int[stack.size()];
            
            for (int i = stack.size() - 1; i >= 0; i-- ) {
                
                ans[i] = stack.pop();
            }
            
        return ans;
    }
    
    public static String removeKdigits(String num, int k) {
         
        Stack<Character> stack = new Stack<>();

        for (char digit : num.toCharArray()) {

            while (!stack.isEmpty()
                    && k > 0
                    && stack.peek() > digit) {

                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        for (char ch : stack) {
            sb.append(ch);
        }

        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        if (sb.length() == 0)
            return "0";

        return sb.toString();
    }
}
