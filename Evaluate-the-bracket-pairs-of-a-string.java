/*
 * ==========================================
 * PROBLEM STATEMENT: Evaluate the Bracket Pairs of a String (LeetCode 1807)
 * ==========================================
 * You are given a string `s` containing bracket pairs with keys inside. You are also given a 2D string 
 * array `knowledge` where each `knowledge[i] = [keyi, valuei]` indicates the value of a key.
 * 
 * Replace every bracket pair with its corresponding value from `knowledge`. If a key is not found, 
 * replace the bracket pair with a question mark `?`. There are no nested brackets.
 * 
 * Constraints:
 * 1 <= s.length <= 10^5
 * 0 <= knowledge.length <= 10^5
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (HashMap & String Building)
 * ==========================================
 * - Approach:
 *   1. Store all `knowledge` pairs in a `HashMap` for $O(1)$ average-time lookups.
 *   2. Iterate through the string `s` character by character:
 *      - If an opening bracket `(` is encountered, capture all subsequent characters until a closing bracket `)` is found to form the `key`.
 *      - Look up the `key` in the HashMap. Append its value if present, or `?` if absent.
 *      - Otherwise, simply append regular characters to a `StringBuilder`.
 * 
 * - Complexity:
 *   - Time Complexity: $O(N + K)$, where $N$ is the length of string `s` and $K$ is the total number of characters in `knowledge`.
 *   - Space Complexity: $O(K)$ to store the HashMap entries.
 */

import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Map to store keys and their corresponding values for fast lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                // Extract the key inside the brackets
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                
                // Append the evaluated value or '?' if the key is not found
                sb.append(map.getOrDefault(key, "?"));
                
                // Move index past the closing bracket
                i = j + 1;
            } 
            else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }
}
