import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Substring Without Repeating Characters (LeetCode 3)
 *
 * Given a string {@code s}, return the length of the longest contiguous substring
 * without repeating characters.
 *
 * This file includes two optimal O(N) Sliding Window implementations:
 * 1. ASCII Direct-Access Array (int[128]): Best when input is strictly standard ASCII.
 * 2. HashMap Approach: Best for general Unicode characters (emojis, non-Latin scripts).
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Approach 1: Optimal Sliding Window with ASCII Array (O(1) Space)
     * - Time Complexity: O(N), single pass over the string.
     * - Space Complexity: O(1), fixed 128-element integer array.
     */
    public static int lengthOfLongestSubstringAscii(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] lastSeenIndex = new int[128];
        Arrays.fill(lastSeenIndex, -1);

        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (lastSeenIndex[currentChar] >= start) {
                start = lastSeenIndex[currentChar] + 1;
            }

            lastSeenIndex[currentChar] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    /**
     * Approach 2: Sliding Window with HashMap (Supports Full Unicode)
     * - Time Complexity: O(N), single pass over the string.
     * - Space Complexity: O(min(N, M)), where M is the character set size.
     */
    public static int lengthOfLongestSubstringUnicode(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (lastSeenIndex.containsKey(currentChar) && lastSeenIndex.get(currentChar) >= start) {
                start = lastSeenIndex.get(currentChar) + 1;
            }

            lastSeenIndex.put(currentChar, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String[] testCases = {"abcabcbb", "grninpk", "aaaaaaaaa", "pqqstq", "hnlchnnen"};

        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\" | ASCII: " 
                    + lengthOfLongestSubstringAscii(test) + " | Unicode: " 
                    + lengthOfLongestSubstringUnicode(test));
        }
    }
}
