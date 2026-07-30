/******************************************************************************

Level 3

1. Merge K Sorted Lists Problem
    Given an array of k linked-lists lists, where each linked-list is sorted in ascending order. 
    Merge all the linked-lists into one sorted linked-list and return its head.
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    
2. Find Median from Data Stream Problem
    Design a data structure that supports adding integers from a continuous data stream and 
    retrieving the median of all elements added so far at any point.
    Input: ["MedianFinder", "addNum", "addNum", "findMedian"], [[], [1], [2], []]
    Output: [null, null, null, 1.5]
    
3. Meeting Rooms II Problem
    Given an array of meeting time intervals intervals where intervals[i] = [start, end]. 
    Return the minimum number of conference rooms required to hold all meetings without any time conflicts.
    Input: intervals = [[0,30],[5,10],[15,20]]
    Output: 2
    
4. Task Scheduler (Heap Solution) Problem
    Given a character array of CPU tasks and a cooling period n. Identical tasks must be separated by at least n idle intervals. 
    Use a max-heap strategy to prioritize tasks with the highest remaining frequency. 
    Return the minimum total time units required to finish all tasks.
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8

*******************************************************************************/
import java.util.*;

public class Main
{
    class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
	public static void main(String[] args) {
		
		ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};

        ListNode ans = mergeKLists(lists);

        print(ans);
        
        
        String[] operations = {
                "MedianFinder",
                "addNum",
                "addNum",
                "findMedian"
        };

        int[][] values = {
                {},
                {1},
                {2},
                {}
        };

        System.out.println(medianFinder(operations, values));
        
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println(minMeetingRooms(intervals));
        
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        System.out.println(leastInterval(tasks, n));
		
	}
	
	public static void print(ListNode head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        
        System.out.peintln();
	}
	
	public static ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>((a, b) -> a.val - b.val);

        // Insert head of every list
        for (ListNode node : lists) {
            if (node != null)
                minHeap.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {

            ListNode current = minHeap.poll();

            tail.next = current;
            tail = tail.next;

            if (current.next != null)
                minHeap.offer(current.next);
        }

        return dummy.next;
    }
    
    public static List<Object> medianFinder(String[] operations, int[][] values) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>();

        List<Object> ans = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            switch (operations[i]) {

                case "MedianFinder":
                    ans.add(null);
                    break;

                case "addNum":

                    int num = values[i][0];

                    if (maxHeap.isEmpty() || num <= maxHeap.peek())
                        maxHeap.offer(num);
                    else
                        minHeap.offer(num);

                    if (maxHeap.size() > minHeap.size() + 1)
                        minHeap.offer(maxHeap.poll());

                    else if (minHeap.size() > maxHeap.size() + 1)
                        maxHeap.offer(minHeap.poll());

                    ans.add(null);
                    break;

                case "findMedian":

                    if (maxHeap.size() == minHeap.size()) {

                        double median =
                                (maxHeap.peek() + minHeap.peek()) / 2.0;

                        ans.add(median);

                    } else if (maxHeap.size() > minHeap.size()) {

                        ans.add((double) maxHeap.peek());

                    } else {

                        ans.add((double) minHeap.peek());
                    }

                    break;
            }
        }

        return ans;
    }
    
    public static int minMeetingRooms(int[][] intervals) {

        if (intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {

            int start = meeting[0];
            int end = meeting[1];

            // Reuse a room if it is already free
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }

            // Occupy a room until 'end'
            minHeap.offer(end);
        }

        return minHeap.size();
    }
    
    public static int leastInterval(char[] tasks, int n) {

        HashMap<Character, Integer> freq = new HashMap<>();

        for (char task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.addAll(freq.values());

        int time = 0;

        while (!maxHeap.isEmpty()) {

            List<Integer> temp = new ArrayList<>();

            int cycle = n + 1;
            int workDone = 0;

            while (cycle > 0 && !maxHeap.isEmpty()) {

                int count = maxHeap.poll();

                count--;
                workDone++;

                if (count > 0)
                    temp.add(count);

                cycle--;
            }

            for (int count : temp)
                maxHeap.offer(count);

            if (maxHeap.isEmpty())
                time += workDone;
            else
                time += n + 1;
        }

        return time;
    }
}
