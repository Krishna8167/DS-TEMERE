/******************************************************************************

TWO-POINTER (LEVEL 2)
1. 3Sum
    Find all unique triplets in an array that sum up to exactly 0.
    Input: nums = [-1, 0, 1, 2, -1, -4]
    Output: [[-1, -1, 2], [-1, 0, 1]]
    Rule: No duplicate triplets in the output.

2. 3Sum Closest
    Find three integers in an array whose sum is closest to a target number.
    Input: nums = [-1, 2, 1, -4],
    target = 1
    Output: 2 (Because -1 + 2 + 1 = 2, which is closest to 1)

3. 4Sum
    Find all unique quadruplets (4 numbers) in an array that sum up to a target number.
    Input: nums = [1, 0, -1, 0, -2, 2],
    target = 0
    Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]

4. Count Pairs Whose Sum is Less Than Target
    Count how many pairs of numbers (nums[i], nums[j]) where i < j have a sum strictly less than the target.
    Input: nums = [-1, 1, 2, 3, 1],
    target = 2
    Output: 3 (Pairs: [-1, 1], [-1, 2], and [-1, 1])

5. Count Unique Pairs
    Find the total number of unique value pairs in an unsorted array that sum up to a target.
    Input: nums = [1, 1, 2, 3, 4],
    target = 5
    Output: 2 (The unique pairs are (1, 4) and (2, 3))

6. Find Closest Pair From Two Sorted Arrays
    Pick one number from arr1 and one number from arr2 such that their sum is as close to x as possible.
    Input: arr1 = [1, 4, 5, 7], arr2 = [10, 20, 30, 40],
    x = 32
    Output: [1, 30] (Sum is 31, which is closest to 32)

7. Boats to Save People
    Rescue people using boats. Each boat can carry at most two people at once, and their combined weight cannot exceed limit.
    Find the minimum boats needed.
    Input: people = [3, 2, 2, 1],
    limit = 3
    Output: 3 (Boats: [1, 2], [2], [3])

(THREE-POINTER)
8. Sort Colors (Dutch National Flag)
    Sort an array containing only 0s (red), 1s (white), and 2s (blue) in-place so that identical numbers are grouped together.
    Do not use built-in sort.
    Input: nums = [2, 0, 2, 1, 1, 0]
    Output: [0, 0, 1, 1, 2, 2]

9. Valid Palindrome II
    Check if a string can become a palindrome (reads the same forward and backward) if you delete at most one character from it.
    Input: s = "abca"
    Output: true (Delete 'c' to get "aba")

10. Backspace String Compare
    Compare two strings containing lowercase letters and # (which acts as a backspace key).
    Return if the final cleared texts match.
    Input: s = "ab#c",
    t = "ad#c"
    Output: true (Both become "ac")

*******************************************************************************/

public class Main
{
	public static void main(String[] args) {
		int[] nums1 = {-1, 0, 1, 2, -1, -4};

		int[] nums2 = {-1, 2, 1, -4};
		int target2 = 1;

		int[] nums3 = {1, 0, -1, 0, -2, 2};
		int target3 = 0;

		int[] nums4 = {-1, 1, 2, 3, 1};
		int target4 = 2;

		int[] nums5 = {1, 1, 2, 3, 4};
		int target5 = 5;

		int[] nums6a = {1, 4, 5, 7};
		int[] nums6b = {10, 20, 30, 40};
		int target6 = 32;
		int[] ans = closestPair(nums61, nums6b, target6);

		int[] nums7 = {3, 2, 2, 1};
		int target7 = 3;

		int[] nums8 = {2, 0, 2, 1, 1, 0};

		String s9 = "abca";

		String s10a = "ab#c";
		String s10b = "ad#c";

		System.out.println(threeSum(nums1));
		System.out.println(threeSumCloset(nums2, target));
		System.out.println(fourSum(nums3, target3));
		System.out.println(countPairs(nums4, target4));
		System.out.println(countUniquePairs(nums5, target5));
		System.out.println(ans[0] + " " + ans[1]);
		System.out.println(numsRescueBoats(nums7, target7));
		sortColor(nums8);
		for (int nums : nums8) {
			System.out.println(nums + " ");
		}
		System.out.println(validPalindrome(s9));
		System.out.println(backspaceCompare(s10a, s10b));

	}

	public static List<List<Integer>> threeSum(int[] nums) {

		Arrays.sort(nums);

		List<List<Integer>> answer = new ArrayList<>();

		for (int i = 0; i < arr.length - 2; i++) {

			if (i > 0 && nums[i] == nums[i-1]) continue;

			int left = i + 1;
			int  right = nums.length -1;

			while (left < right) {

				int sum = nums[i] + nums[left] + nums[right];

				if (sum == 0) {

					answer.add(Arrays.asList(nums[i], nums[left, nums[right]]))

					left++;
					right--;

					while (left < right && nums[left] == nums[left - 1]) {
						left++;
					}

					while (left < right && nums[right] == nums[right + 1])
						right--;
				} else if (sum < 0) {

					left++;
				} else {
					right--;
				}
			}
		}
		return answer;
	}

	public static int threeSumCloset(int[] nums, int target) {

		Arrays.sort(nums);

		int minDiff = Integer.MAX_VALUE;
		int answer = 0;

		for (int i = 0; i < nums.length - 2; i++) {

			int left  = i + 1;
			int right  = nums.length - 1;

			while (left < right) {

				int sum = nums[i] + nums[left] + nums[right];
				int diff = Math.abs(sum - target);

				if (diff < minDiff) {
					minDiff = diff;
					answer = sum;
				}

				if (sum < target) {

					left++;
				} else if (sum > target) [
					    right--;]
				} else {
				return sum;
			}
		}
		return answer;
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);

		List<List<Integer>> answer = new ArrayList<>();

		int n = nums.length;

		for (int i = 0; i< n - 3 ; i++) {

			if (i > 0 && nums[i] == nums[i-1]) {
				continue;
			}

			for (int j = i + 1; j < n - 2; j++) {

				if (j > 0 && nums[j] == nums[j - 1]) {
					continue;
				}

				int left = j + 1;
				int right = n - 1;

				while (left < right) {
					long sum = (long) nums[i]
					           + nums[j]
					           + nums[left]
					           + nums[right];

					if (sum == target) {
						answer.add(Arrays.asList(
						               nums[i],
						               nums[j],
						               nums[left],
						               nums[right]));

						left++;
						right--;

						while (left < right &&
						        nums[left] == nums[right + 1])
							right--;

						while (left < right  &&
						        nums[right] == nums[right] + 1))
							right--;
						}

					else if (sum < target) {

						left++;
					}
					else {
						right--;
					}
				}
			}
		}
		return answer;
	}

	public static int countPairs(int[] nums, int target) {

		Arrays.sort(nums);

		int left = 0;
		int right = nums.length - 1;

		int count = 0;

		while (left < right) {

			int sum = nums[left] + nums[right];

			if (sum < target) {

				count += right - left;
				left++;

			} else {

				right--;

			}
		}
		return count;
	}

	public static  int countUNiquePairs(int[] nums, int target) {

		Arrays.sort(nums);

		int left = 0;
		int right = nums.length - 1;

		int count = 0;

		while (left < right) {

			int sum = nums[left] + nums[right];

			if (sum == target) {

				count++;

				left++;
				right--;

				while (left < right && nums[left] == nums[left - 1]) {
					left++;
				}

				while (left < right && nums[right] == nums[right + 1]) {
					right--;
				}
			} else if (sum < target) {

				left++;
			} else {

				right--;
			}
		}

		return count;
	}

	public static  int[] closestPair(int[] arr1, int[] arr2, int x) {

		int left = 0;
		int right = arr.length - 1;

		int minDiff = Integer.MAX_VALUE;

		int first = 0;
		int second = 0;

		while (left < arr1.length && right >= 0) {

			int sum = arr1[left] + arr2[right];
			int diff = Math.abs(sum - x);

			if (diff < minDiff) {
				minDiff = diff;
				first = arr1[left];
				second = arr2[right];
			}

			if (sum < x) {

				left++;
			} else {

				right--;
			}
		}
		return new int[] {first, second};
	}

	public static int numRescueBoats(int[] people, int limit) {

		Arrays.sort(people);

		int left = 0;
		int right = people.length - 1;

		int boats = 0;

		while (left <= right) {

			if (people[left] + people[right] <= limit) {

				left++;
				right--;

			} else {

				right--;
			}

			boats++;
		}

		return boats;
	}

	publuc static void  sortColor(int[] nums) {

		int low = 0;
		int mid = 0;
		int high = nums.length - 1;

		while (mid <= high) {

			if (nums[mid] == 0) {

				swap(nums, low, mid);
				low++;
				mid++;
			} else if (nums[mid] == 1) {

				mid++;

			} else {
				swap(nums, mid,high);
				high--;
			}
		}
	}
	public  static void swap(int[] nums, int i, int j) {

		int temp = i;
		i = j;
		j = temp;
	}

	public static boolean validPalindrome(String s) {

		int left = 0;
		int right = s.length() - 1;

		while (left < right) {

			if (s.charAt(left) == s.charAt(right)) {

				left++;
				right--;
			} else {

				return isPalindrome(s, left + 1, right) ||
				       isPalindrome(s, left, right - 1);
			}
		}

		return true;
	}

	public static boolean isPalindrome(String s, int left, int right) {

		while (left <  right) {

			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

	public static boolean backspaceCompare(String s, String t) {

		int i = s.length() - 1;
		int j = t.length() - 1;

		while (i >= 0 || j >= 0) {

			i = getNextValidIndex(s, i);
			j = getNextValidIndex(t, j);

			if (i < 0 && j < 0) {
				return true;
			}

			if (i < 0 || j < 0) {
				return false;
			}

			if (s.charAt(i) != t.charAt(j)) {
				return false;
			}

			i--;
			j--;
		}
		return true;
	}

	public static int getNextValidIndex(String str, int index) {

		int skip = 0;

		while (index >= 0) {

			if (str.charAt(index) == '#') {

				skip++;
				index--;
			} else if(skip > 0) {

				skip--;
				index--;
			} else {

				break;
			}
		}

		return index;
	}
}
