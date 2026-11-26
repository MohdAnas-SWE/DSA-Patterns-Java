import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Subarrays with K Different Integers (LeetCode 992)
 *
 * Approach: Sliding Window (AtMost(K) - AtMost(K - 1))
 * - Computes subarrays containing at most 'k' distinct integers and subtracts
 *   subarrays containing at most 'k - 1' distinct integers to get the exact count.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of {@code nums}. Both pointers
 *   traverse the array at most once per helper call (2 * O(N) = O(N)).
 * - Space Complexity: O(K), as the frequency map holds at most K + 1 distinct elements.
 */
public class SubarraysWithKDifferentIntegers {

    /**
     * Returns the number of contiguous subarrays with exact {@code k} distinct integers.
     *
     * @param nums Input integer array.
     * @param k    Target number of distinct integers.
     * @return Total count of good subarrays.
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return countSubarraysAtMostK(nums, k) - countSubarraysAtMostK(nums, k - 1);
    }

    /**
     * Helper method using a sliding window to count subarrays
     * containing at most {@code k} distinct integers.
     */
    private static int countSubarraysAtMostK(int[] nums, int k) {
        if (k <= 0) {
            return 0;
        }

        int start = 0;
        int totalSubarrays = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int end = 0; end < nums.length; end++) {
            // 1. Expand window: add the right element to the frequency map
            int rightElement = nums[end];
            freqMap.put(rightElement, freqMap.getOrDefault(rightElement, 0) + 1);

            // 2. Shrink window: ensure at most 'k' distinct elements remain
            while (freqMap.size() > k) {
                int leftElement = nums[start];
                freqMap.put(leftElement, freqMap.get(leftElement) - 1);

                // Remove element completely if its frequency drops to zero
                if (freqMap.get(leftElement) == 0) {
                    freqMap.remove(leftElement);
                }
                start++;
            }

            // 3. Record: all subarrays ending at 'end' starting from 'start..end' are valid
            totalSubarrays += (end - start + 1);
        }

        return totalSubarrays;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3};
        int k = 2;
        System.out.println("Subarrays with exact " + k + " distinct integers: " 
                + subarraysWithKDistinct(nums, k)); // Expected: 4
    }
}

