/**
 * Pattern: Sliding Window
 * Problem: Contains Duplicate II (LeetCode #219)
 * Link: https://leetcode.com
 *
 * Approach:
 * Maintain a sliding window of size at most 'k' using a HashSet.
 * As we iterate through the array, if the current element already exists
 * in the set, a duplicate within distance 'k' is found. If the window size
 * exceeds 'k', remove the oldest element (nums[i - k]).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, k))
 */

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Edge cases: duplicate is impossible if array has <= 1 element or k <= 0
        if (nums == null || nums.length <= 1 || k <= 0) {
            return false;
        }
        
        int n = nums.length;
        Set<Integer> window = new HashSet<>();

        for (int i = 0; i < n; i++) {
            // If element already exists in the current window of size k, return true
            if (!window.add(nums[i])) {
                return true;
            }
            // Keep the sliding window size limited to k elements
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}
