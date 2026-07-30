/******************************************************************************
QUEUE...

1. Implement Queue using StacksProblem
    Implement a first-in-first-out (FIFO) queue using only standard stack operations (push, peek/pop from top, size, is empty).
    Input: ["MyQueue", "push", "push", "peek", "pop", "empty"], [[], [1], [2], [], [], []]
    Output: [null, null, null, 1, 1, false]
    
2. Design Circular Queue Problem
    Design a queue with a fixed size that connects the end position back to the first position to reuse wasted space. 
    Implement operations to enqueue, dequeue, front, rear, isFull, and isEmpty.
    Input: ["MyCircularQueue", "enQueue", "enQueue", "deQueue", "Front"], [[3], [1], [2], [], []]
    Output: [null, true, true, true, 2]BFS Style Queue
    
3. Number of Recent Calls Problem
    Design a counter class that counts recent requests within a moving time window of 3000 milliseconds. 
    Every call to ping(t) records a request at time t and returns the number of requests in the range [t - 3000, t].
    Input: ["RecentCounter", "ping", "ping", "ping"], [[], [1], [100], [3001]]
    Output: [null, 1, 2, 3]
    
4. First Unique Character in a Stream Problem
    Given a continuous stream of characters. Find and return the first character that has appeared exactly once so far. 
    If no unique character exists, return #.
    Input: stream = ["a", "b", "a", "c"]
    Output: ["a", "a", "b", "b"]

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
		 String[] operations = {
                "MyQueue",
                "push",
                "push",
                "peek",
                "pop",
                "empty"
        };

        int[][] values = {
                {},
                {1},
                {2},
                {},
                {},
                {}
        };
        
        String[] operations2 = {
                "MyCircularQueue",
                "enQueue",
                "enQueue",
                "deQueue",
                "Front"
        };

        int[][] values2 = {
                {3},
                {1},
                {2},
                {},
                {}
        };
        
        String[] operations3 = {
                "RecentCounter",
                "ping",
                "ping",
                "ping"
        };

        int[][] values3 = {
                {},
                {1},
                {100},
                {3001}
        };
        
        String[] stream = {
                "a",
                "b",
                "a",
                "c"
        };
		
		System.out.println(implementQueue(operations, values));
		System.out.println(circularQueue(operations, values));
		System.out.println(recentCounter(operations, values));
		System.out.println(firstUniqueCharacter(stream));
	}
	
	public static List<Object> implementQueue(String[] operations, int[][] values) {

        Stack<Integer> input = new Stack<>();
        Stack<Integer> output = new Stack<>();

        List<Object> ans = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "MyQueue":
                    ans.add(null);
                    break;

                case "push":
                    input.push(values[i][0]);
                    ans.add(null);
                    break;

                case "pop":

                    if (output.isEmpty()) {
                        while (!input.isEmpty()) {
                            output.push(input.pop());
                        }
                    }

                    ans.add(output.pop());
                    break;

                case "peek":

                    if (output.isEmpty()) {
                        while (!input.isEmpty()) {
                            output.push(input.pop());
                        }
                    }

                    ans.add(output.peek());
                    break;

                case "empty":

                    ans.add(input.isEmpty() && output.isEmpty());
                    break;
            }
        }

        return ans;
    }
    
    public static List<Object> circularQueue(String[] operations, int[][] values) {

        int[] queue = null;
        int front = 0;
        int rear = -1;
        int size = 0;
        int capacity = 0;

        List<Object> ans = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "MyCircularQueue":

                    capacity = values[i][0];
                    queue = new int[capacity];

                    front = 0;
                    rear = -1;
                    size = 0;

                    ans.add(null);
                    break;

                case "enQueue":

                    if (size == capacity) {
                        ans.add(false);
                    } else {

                        rear = (rear + 1) % capacity;

                        queue[rear] = values[i][0];

                        size++;

                        ans.add(true);
                    }

                    break;

                case "deQueue":

                    if (size == 0) {
                        ans.add(false);
                    } else {

                        front = (front + 1) % capacity;

                        size--;

                        ans.add(true);
                    }

                    break;

                case "Front":

                    if (size == 0)
                        ans.add(-1);
                    else
                        ans.add(queue[front]);

                    break;

                case "Rear":

                    if (size == 0)
                        ans.add(-1);
                    else
                        ans.add(queue[rear]);

                    break;

                case "isEmpty":

                    ans.add(size == 0);
                    break;

                case "isFull":

                    ans.add(size == capacity);
                    break;
            }
        }

        return ans;
    }
    
    public static List<Object> recentCounter(String[] operations, int[][] values) {

        Queue<Integer> queue = new LinkedList<>();
        List<Object> ans = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "RecentCounter":
                    ans.add(null);
                    break;

                case "ping":

                    int t = values[i][0];

                    queue.offer(t);

                    while (!queue.isEmpty() && queue.peek() < t - 3000) {
                        queue.poll();
                    }

                    ans.add(queue.size());
                    break;
            }
        }

        return ans;
    }
    
    public static List<Character> firstUniqueCharacter(String[] stream) {

        Queue<Character> queue = new LinkedList<>();
        HashMap<Character, Integer> freq = new HashMap<>();

        List<Character> ans = new ArrayList<>();

        for (String s : stream) {

            char ch = s.charAt(0);

            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            queue.offer(ch);

            while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
                queue.poll();
            }

            if (queue.isEmpty())
                ans.add('#');
            else
                ans.add(queue.peek());
        }

        return ans;
    }
}
