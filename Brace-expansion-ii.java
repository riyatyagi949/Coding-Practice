/*
 * ==========================================
 * PROBLEM STATEMENT: Brace Expansion II (LeetCode 1096)
 * ==========================================
 * Under the given grammar, strings can represent a set of lowercase words. 
 * Formally:
 * - Single letters represent a singleton set containing that word.
 * - A comma-delimited list of two or more expressions denotes union.
 * - Concatenation of two expressions denotes the cartesian product of their sets of words.
 * 
 * Return the sorted list of unique words that the expression represents.
 * 
 * Constraints:
 * 1 <= expression.length <= 60
 * expression[i] consists of '{', '}', ',' or lowercase English letters.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Recursive Descent Parser)
 * ==========================================
 * - Approach:
 *   1. Grammar Hierarchy:
 *      - `performUnion()` handles comma-separated expressions (`e1, e2, ...`).
 *      - `performConcat()` handles concatenated expressions (adjacent units).
 *      - `getUnit()` handles single characters or expressions enclosed in curly braces `{...}`.
 *   2. Parsing Algorithm:
 *      - Use a global tracking pointer (`idx`) to parse the expression string recursively.
 *      - `TreeSet` is used automatically to keep words sorted and remove duplicates.
 * 
 * - Complexity:
 *   - Time Complexity: Exponential in the worst case due to nested expansions, but extremely efficient for bounds (length <= 60).
 *   - Space Complexity: O(number of generated words * word length) for storing the resulting sets.
 */

import java.util.*;

class Solution {
    String s;
    int n;
    int idx = 0;

    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;

        Set<String> st = performUnion();
        return new ArrayList<>(st); 
    }

    private Set<String> getUnit() {
        Set<String> result;

        if (s.charAt(idx) == '{') {
            idx++; // skip '{'
            result = performUnion();
        } else { 
            result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(idx)));
        }
        idx++; // skip closing '}' or the character
        return result;
    }

    private Set<String> performConcat() {
        Set<String> result = new TreeSet<>();
        result.add(""); // Base neutral element for concatenation

        while (idx < n && (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            Set<String> temp = getUnit();

            Set<String> concatResult = new TreeSet<>();
            for (String left : result) {
                for (String right : temp) {
                    concatResult.add(left + right);
                }
            }
            result = concatResult;
        }
        return result;
    }

    private Set<String> performUnion() {
        Set<String> result = new TreeSet<>();

        while (true) {
            Set<String> temp = performConcat();
            result.addAll(temp);

            if (idx < n && s.charAt(idx) == ',') {
                idx++; // skip ','
            } else {
                break;
            }
        }
        return result;
    }
}
