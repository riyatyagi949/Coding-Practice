/**
 * PROBLEM STATEMENT: 2515. Shortest Distance to Target String in a Circular Array
 * --------------------------------------------------------------------------------
 * You are given a circular array of strings 'words', a target string 'target',
 * and a starting index 'startIndex'.
 *
 * From startIndex, you can move:
 * - one step to the right
 * - one step to the left
 *
 * Since the array is circular:
 * - moving right from last index goes to first index
 * - moving left from first index goes to last index
 *
 * Goal: Find the minimum number of steps required to reach any index
 * where words[i] == target.
 *
 * If target does not exist in the array, return -1.
 *
 * Example:
 * Input: words = ["hello","i","am","leetcode","hello"]
 *        target = "hello", startIndex = 1
 * Output: 1
 *
 * Explanation:
 * - Left move: index 1 -> 0 = 1 step
 * - Right move: index 1 -> 2 -> 3 -> 4 = 3 steps
 * Minimum = 1
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Linear Scan + Circular Distance
 * --------------------------------------------------------------------------------
 * 1. Traverse every index of the array.
 * 2. Whenever target is found:
 *    - calculate normal distance = abs(i - startIndex)
 *    - calculate circular distance = n - normal distance
 * 3. Take minimum of both distances.
 * 4. Track the global minimum answer.
 *
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We scan the array once.
 *
 * Space Complexity: O(1)
 * - Only constant extra space used.
 * --------------------------------------------------------------------------------
 */
import java.util.*;

class Solution {
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int directDistance = Math.abs(i - startIndex);
                int circularDistance = n - directDistance;

                minDistance = Math.min(minDistance,
                        Math.min(directDistance, circularDistance));
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
