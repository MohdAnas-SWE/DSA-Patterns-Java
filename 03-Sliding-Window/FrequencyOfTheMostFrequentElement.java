import java.util.Arrays;

/**
 * Problem: Frequency of the Most Frequent Element (LeetCode 1838)
 *
 * Given an integer array {@code nums} and an integer {@code k}, in one operation
 * you can choose an index and increment its value by 1. Return the maximum possible
 * frequency of an element after performing at most {@code k} operations.
 *
 * Approach: Sorting + Sliding Window
 * - Sort the array so that elements closest to any target value lie immediately to its left.
 * - Expand the window using {@code right}, treating {@code nums[right]} as the target value
 *   that all elements in {@code [left..right]} will be incremented to match.
 * - Shrink the window from {@code left} whenever the required operations
 *   {@code (windowLength * target - windowSum)} exceed {@code k}.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N) due to sorting. The sliding window traversal takes O(N).
 * - Space Complexity: O(1) auxiliary space (ignoring the space used by Java's Dual-Pivot Quicksort).
 */
public class FrequencyOfTheMostFrequentElement {

    /**
     * Calculates the maximum achievable frequency of any element after at most {@code k} increments.
     *
     * @param nums Input integer array.
     * @param k    Maximum allowed increment operations.
     * @return Maximum frequency of a single element.
     */
    public static int maxFrequency(int[] nums, int k) {
        // Sort ascending so candidate elements to increment are adjacent to the target
        Arrays.sort(nums);

        int left = 0;
        int maxFreq = 0;
        long windowSum = 0; // Use 'long' to prevent integer overflow on large constraints

        for (int right = 0; right < nums.length; right++) {
            long target = nums[right];
            windowSum += target;

            // Total sum needed if all elements in [left..right] become equal to 'target'
            // Condition: (windowLength * target) > (windowSum + k)
            while ((long) (right - left + 1) * target > windowSum + k) {
                windowSum -= nums[left];
                left++;
            }

            // Record the largest valid window size found so far
            int currentWindowSize = right - left + 1;
            maxFreq = Math.max(maxFreq, currentWindowSize);
        }

        return maxFreq;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        System.out.println("Max frequency (Example 1): " + maxFrequency(nums1, k1)); // Expected: 3

        int[] nums2 = {1, 4, 8, 13};
        int k2 = 5;
        System.out.println("Max frequency (Example 2): " + maxFrequency(nums2, k2)); // Expected: 2
    }
}
