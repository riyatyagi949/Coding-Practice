/*
 * ==========================================
 * PROBLEM STATEMENT: Remove Invalid Parentheses
 * ==========================================
 * Given a string `s` that contains parentheses and letters, remove the minimum number of 
 * invalid parentheses in order to make the input string valid. Return all possible results 
 * in any order.
 * 
 * Constraints:
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (BFS / Level-Order Search)
 * ==========================================
 * - Approach:
 *   1. We can use Breadth-First Search (BFS) to find the minimum removals.
 *   2. Start with the original string and check if it is valid. If yes, return a list containing just this string.
 *   3. If not, generate all possible strings by removing one parenthesis at a time (from left or right) 
 *      and add them to a queue for the next level.
 *   4. Use a HashSet to keep track of visited strings to avoid redundant checks and duplicate work.
 *   5. The moment we find at least one valid string in the current BFS level, we stop exploring deeper levels 
 *      because any string found at a deeper level would require more removals.
 * 
 * - Complexity:
 *   - Time Complexity: O(2^n) in the worst-case where n is the length of the string, but significantly pruned using BFS and visited sets.
 *   - Space Complexity: O(2^n) to store states in the queue and visited set.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }

                if (found) continue; // If we found valid strings at this level, don't generate shorter ones

                // Generate all possible strings by removing one parenthesis
                for (int j = 0; j < curr.length(); j++) {
                    char ch = curr.charAt(j);
                    if (ch != '(' && ch != ')') continue;

                    String next = curr.substring(0, j) + curr.substring(j + 1);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }

            if (found) break;
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}
