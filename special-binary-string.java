/**
 * PROBLEM STATEMENT: 761. Special Binary String
 * --------------------------------------------------------------------------------
 * Special binary strings are binary strings with two properties:
 * 1. The number of 0's is equal to the number of 1's.
 * 2. Every prefix has at least as many 1's as 0's.
 * (This is identical to the definition of a valid parentheses string, 
 * where '1' is '(' and '0' is ')')
 * * You can swap two consecutive, non-empty, special substrings. 
 * Goal: Return the lexicographically largest resulting string possible.
 * * Example 1:
 * Input: s = "11011000"
 * Output: "11100100"
 * * Constraints:
 * 1 <= s.length <= 50
 * s is a special binary string.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Recursive Decomposition and Greedy Sorting
 * --------------------------------------------------------------------------------
 * 1. Decomposition into Atomic Special Substrings:
 * Any special string can be viewed as a sequence of "atomic" special substrings.
 * An atomic substring starts with '1', ends with '0', and the part inside 
 * (between the first 1 and last 0) is also a special string.
 * * 2. Recursive Property:
 * To make the entire string lexicographically largest, we must:
 * a. Recursively make each "inner" part of the atomic substrings as large as possible.
 * b. Sort these atomic substrings in descending order.
 * * 3. Strategy:
 * - Iterate through the string and maintain a counter (+1 for '1', -1 for '0').
 * - When counter reaches 0, we've found an atomic special substring.
 * - Recurse on the substring excluding the outermost '1' and '0'.
 * - Collect these processed substrings in a list.
 * - Sort the list in reverse lexicographical order (descending).
 * - Join them back together.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - N is the length of the string (up to 50).
 * - Each level of recursion processes the string. Sorting strings of length N 
 * takes O(N log N) comparisons, and each comparison takes O(N).
 * Space Complexity: O(N^2) 
 * - Due to the creation of substrings and the recursion stack.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Code -

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    /**
     * Recursively transforms a special binary string into its lexicographically largest version.
     * @param s The special binary string.
     * @return The largest possible special binary string.
     */
    public String makeLargestSpecial(String s) {
        // List to store the atomic special components of the current string
        List<String> specials = new ArrayList<>();
        int count = 0;
        int start = 0;

        // Step 1: Decompose the string into atomic special substrings
        for (int i = 0; i < s.length(); i++) {
            // Treat '1' as +1 and '0' as -1
            count += (s.charAt(i) == '1') ? 1 : -1;

            // When count hits 0, we've found a complete special substring
            if (count == 0) {
                // An atomic special string always starts with '1' and ends with '0'.
                // We recursively process the "inner" part: s[start+1 ... i-1]
                String inner = s.substring(start + 1, i);
                
                // Reconstruct the component: '1' + largest_version_of_inner + '0'
                String processed = "1" + makeLargestSpecial(inner) + "0";
                specials.add(processed);

                // Move the start pointer to the next potential special substring
                start = i + 1;
            }
        }

        // Step 2: To get the lexicographically largest string, 
        // we sort the atomic components in descending order.
        Collections.sort(specials, Collections.reverseOrder());

        // Step 3: Concatenate the sorted components
        StringBuilder result = new StringBuilder();
        for (String str : specials) {
            result.append(str);
        }

        return result.toString();
    }
}
