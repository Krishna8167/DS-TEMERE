/******************************************************************************
STACK LEVEL 1 AND 2
Level 1: Foundation

1. Valid Parentheses Problem
    Given a string containing just the characters '(', ')', '{', ''}, '[' and ']'.
    Determine if the input string is valid. Brackets must close in the correct order and with the same type.
    Input: s = "()[]{}"
    Output: true

2. Implement Stack using Queues Problem
    Implement a last-in-first-out (LIFO) stack using only standard queue operations (push, peek/pop from front, size, is empty).
    Input: ["MyStack", "push", "push", "pop", "empty"], [[], [1], [2], [], []]
    Output: [null, null, null, 2, false]

3. Min Stack Problem
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time \(O(1)\).
    Input: ["MinStack","push","push","getMin","pop","top","getMin"], [[],[-2],[0],[],[],[],[]]
    Output: [null, null, null, -2, null, 0, -2]

Level 2: Monotonic Stack

4. Next Greater Element I Problem
    Given two distinct integer arrays nums1 and nums2 where nums1 is a subset of nums2. For each element in nums1,
    find the first greater element to its right in nums2. Return -1 if none exists.
    Input: nums1 = [4,1], nums2 = [1,3,4,2]
    Output: [-1,3]

5. Next Greater Element II Problem
    Given a circular integer array nums. Find the first greater element to the right of each element.
    Since the array is circular, search past the end to look at the beginning. Return -1 if none exists.
    Input: nums = [1,2,1]
    Output: [2,-1,2]

6. Previous Greater Element Problem
    Given an integer array nums. For each element, find the closest greater element located to its left.
    Return -1 for elements that have no greater value to their left.
    Input: nums = [2,1,3,2]
    Output: [-1,2,-1,3]

7. Next Smaller Element Problem
    Given an integer array nums. For each element, find the first smaller element located to its right.
    Return -1 if no smaller element exists to its right.
    Input: nums = [4,5,2,10]
    Output: [2,2,-1,-1]

8. Previous Smaller Element Problem
    Given an integer array nums. For each element, find the closest smaller element located to its left.
    Return -1 if no smaller element exists to its left.
    Input: nums = [2,5,3,6]
    Output: [-1,2,2,3]

*******************************************************************************/
import java.util.*;

public class Main
{
	public static void main(String[] args) {

		String s1 = "()[]{}";
		
		String[] operations = {
                "MyStack", "push", "push", "pop", "empty"
        };

        int[][] values = {
                {},
                {1},
                {2},
                {},
                {}
        };
        
        String[] operations2 = {
                "MinStack",
                "push",
                "push",
                "getMin",
                "pop",
                "top",
                "getMin"
        };

        int[][] values2 = {
                {},
                {-2},
                {0},
                {},
                {},
                {},
                {}
        };
        
        int[] nums4a = {4, 1};
        int[] nums4b = {1, 3, 4, 2};
        
        int[] nums5 = {1, 2, 1};
        
        int[] nums6 = {2, 1, 3, 2};
        
        int[] nums7 = {4, 5, 2, 10};
        
        int[] nums8 = {2, 5, 3, 6};

		System.out.println(isValid(s1));
		System.out.println(implementStack(operations, values));
		System.out.println(minStack(operations2, values2));
		System.out.println(Arrays.toString(nextGreaterElement(nums4a, nums4b)));
		System.out.println(Arrays.toString(nextGreaterElement2(nums5)));
		System.out.println(Arrays.toString(previousGreaterElement(nums6)));
		System.out.println(Arrays.toString(nextSmallerElement(nums7)));
		System.out.println(Arrays.toString(previousSmallerElement(nums8)));
	}

	public static boolean isValid(String s) {

		Deque<Character> stack = new ArrayDeque<>();

		if (ch == '(' || ch == '[' || ch == '{') {
			stack.push(ch);
		} else {

			if (stack.isEmpty()) {
				return false;

				char top = stack.pop();

				if(ch == ')' && top !='(')
					return false;

				if (ch == ']' && top != '[')
					return false;

				if (ch == '}' && top != '{')
					return false;
			}
		}
		return  stack.isEmpty();
	}
	
	public static List<Object> implementStack(String[] operations, int[][] values) {

        Queue<Integer> queue = new LinkedList<>();
        List<Object> output = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "MyStack":
                    output.add(null);
                    break;

                case "push":
                    queue.offer(values[i][0]);

                    int size = queue.size();
                    for (int j = 0; j < size - 1; j++) {
                        queue.offer(queue.poll());
                    }

                    output.add(null);
                    break;

                case "pop":
                    output.add(queue.poll());
                    break;

                case "top":
                    output.add(queue.peek());
                    break;

                case "empty":
                    output.add(queue.isEmpty());
                    break;
            }
        }

        return output;
    }
   
   public static List<Object> minStack(String[] operations, int[][] values) {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        List<Object> output = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "MinStack":
                    output.add(null);
                    break;

                case "push":

                    int x = values[i][0];

                    stack.push(x);

                    if (minStack.isEmpty() || x <= minStack.peek()) {
                        minStack.push(x);
                    }

                    output.add(null);
                    break;

                case "pop":

                    if (stack.peek().equals(minStack.peek())) {
                        minStack.pop();
                    }

                    stack.pop();

                    output.add(null);
                    break;

                case "top":

                    output.add(stack.peek());
                    break;

                case "getMin":

                    output.add(minStack.peek());
                    break;
            }
        }

        return output;
    } 
    
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {

            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
    
    public static int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {

            int index = i % n;

            while (!stack.isEmpty() && stack.peek() <= nums[index]) {
                stack.pop();
            }

            if (i < n) {
                if (stack.isEmpty()) {
                    ans[index] = -1;
                } else {
                    ans[index] = stack.peek();
                }
            }

            stack.push(nums[index]);
        }

        return ans;
    }
    
    public static int[] previousGreaterElement(int[] nums) {

        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {

            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }
    
    public static int[] nextSmallerElement(int[] nums) {
        
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }
    
    public static int[] previousSmallerElement(int[] nums) {

        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {

            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }
}
