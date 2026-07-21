/*
 * PROBLEM STATEMENT:
 * You are given a binary string 's' of length 'n', where '1' represents an active section 
 * and '0' represents an inactive section. You can perform at most one trade to maximize 
 * the number of active sections:
 * - Convert a contiguous block of '1's surrounded by '0's to all '0's.
 * - Afterward, convert a contiguous block of '0's surrounded by '1's to all '1's.
 * Return the maximum number of active sections in 's' after making the optimal trade.
 * Note: Treat 's' as augmented with a '1' at both ends.
 * 
 * OPTIMAL SOLUTION APPROACH:
 * 1. Count the initial total number of active sections ('1's).
 * 2. Identify all contiguous blocks of inactive sections ('0's).
 * 3. A trade allows us to turn a block of zeros into ones by consuming a block of ones 
 *    that separates two zero-blocks. Specifically, choosing two adjacent zero blocks of sizes 
 *    'A' and 'B' allows us to merge them and flip the intervening ones into zeros, 
 *    effectively gaining the length of these two zero blocks (A + B).
 * 4. Thus, the problem reduces to finding the maximum sum of lengths of any two adjacent 
 *    inactive blocks, and adding this sum to the initial active count.
 * 
 * Time Complexity: O(N), where N is the length of the string.
 * Space Complexity: O(N) to store the lengths of inactive blocks.
 */

import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        // Count initial active sections ('1's)
        int activeCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') activeCount++;
        }

        // Collect lengths of all contiguous blocks of '0's
        List<Integer> inactiveBlocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                inactiveBlocks.add(i - start);
            } 
            else {
                i++;
            }
        }

        // Find the maximum sum of any two adjacent inactive blocks
        int maxPairSum = 0;
        for (int j = 1; j < inactiveBlocks.size(); j++) {
            maxPairSum = Math.max(maxPairSum, inactiveBlocks.get(j) + inactiveBlocks.get(j - 1));
        }

        // Total active sections after optimal trade
        return maxPairSum + activeCount;
    }
}
