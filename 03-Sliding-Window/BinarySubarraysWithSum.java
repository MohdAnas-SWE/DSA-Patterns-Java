/**
 * Problem: Binary Subarrays With Sum (LeetCode 930)
 *
 * Given a binary array {@code nums} and an integer {@code goal}, return the number
 * of non-empty subarrays with a sum equal to {@code goal}.
 *
 * Approach: Optimal Single-Pass Sliding Window with Leading Zero Tracking
 * - Uses two pointers ({@code start} and {@code end}) to maintain a valid window.
 * - Tracks leading zeros ({@code prefixZeros}) immediately preceding the tight window,
 *   as each leading zero forms an additional valid subarray without altering the sum.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of {@code nums}. Both {@code start}
 *   and {@code end} pointers traverse the array at most once.
 * - Space Complexity: O(1), using only constant extra variables.
 */

public class BinarySubarraysWithSum {

  static int countSubarrays (int[] nums, int goal) {
    int totalCount = 0;
    int start = 0;
    int prefixZeros = 0; // Tracks leading zeros before the current valid window
    int currentSum = 0;

    for (int end = 0; end < nums.length; end++) {
      // 1. Expand the window by adding the current element
      currentSum += nums[end];
      
      // 2. Shrink the window if sum exceeds 'goal', or tighten it by skipping leading zeros.
      // 'start < end' ensures the window remains non-empty (at least 1 element).
      while (start < end && (currentSum > goal || nums[start] == 0)) {
        if (nums[start] == 1) {
          prefixZeros = 0; // Removing a '1' invalidates previously tracked leading zeros
        } else {
          prefixZeros++; // Each leading '0' skipped represents an extra valid starting point
        }
        currentSum -= nums[start];
        start++;
      }
      
      // 3. Record valid subarrays: 1 (current tight window) + prefixZeros (extra combinations)
      if (currentSum == goal) {
        totalCount += 1 + prefixZeros;
      }
    }

    return totalCount;
  }

  public static void main(String[] args) {
    // Test Case 1: Standard case with multiple leading and trailing zeros
    int[] nums1 = {0,1,0,1,0,1,0};
    int goal1 = 2;

    // Test Case 2: Edge case where goal is 0
    int[] nums2 = {0,1,0,0};
    int goal2 = 0;
    System.out.println("Total number of non-empty subarrays with a sum that meets the goal-"+goal1+" are: "+countSubarrays(nums1, goal1)); // Expected: 8
    System.out.println("Total number of non-empty subarrays with a sum that meets the goal-"+goal2+" are: "+countSubarrays(nums2, goal2)); // Expected: 4
  }

  
}
