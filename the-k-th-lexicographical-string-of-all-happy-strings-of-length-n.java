/**
 * PROBLEM STATEMENT: 1415. The k-th Lexicographical String of All Happy Strings of Length n
 * --------------------------------------------------------------------------------
 * A "happy string" is a string that:
 * 1. Consists only of letters from the set ['a', 'b', 'c'].
 * 2. Does not have the same character at two consecutive indices (s[i] != s[i+1]).
 * * Given two integers n and k, consider a list of all happy strings of length n 
 * sorted in lexicographical order. 
 * * Task: Return the kth string of this list, or an empty string if there are 
 * fewer than k happy strings.
 * * Example:
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: Happy strings are ["aba", "abc", "aca", "acb", "bab", "bac", ...].
 * The 9th one is "cab".
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BACKTRACKING / RECURSIVE GENERATION
 * --------------------------------------------------------------------------------
 * 1. The Strategy:
 * Since we need the strings in lexicographical order, we can use backtracking 
 * to explore the choices 'a', 'b', and 'c' in that specific order.
 * * 2. Pruning/Constraint:
 * During recursion, we only pick a character if it is different from the 
 * last character added to the current string.
 * * 3. Collecting Results:
 * We can store the strings in a list. Since we explore in 'a' -> 'b' -> 'c' 
 * order, the list will automatically be sorted lexicographically.
 * * 4. Optimization Note:
 * For n=10, the total number of happy strings is 3 * 2^(n-1) = 3 * 2^9 = 1536.
 * This is small enough to generate all strings, though we could stop early 
 * once the list size reaches k.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(k * n) or O(2^n)
 * - There are at most 3 * 2^(n-1) happy strings.
 * - We generate them until we find the kth one or exhaust the possibilities.
 * - String concatenation inside recursion takes O(n).
 * * Space Complexity: O(2^n * n)
 * - We store all generated strings in a list. Each string is length n.
 * - The recursion stack depth is O(n).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.ArrayList;
import java.util.List;

class Solution {
   List<String> list = new ArrayList<>();
    public String getHappyString(int n, int k) {
        backtrack(n, "");
        
        if(k > list.size()) 
        return "";
        return list.get(k-1);
    }
    void backtrack(int n, String curr){
        if(curr.length() == n){
            list.add(curr);
            return;
        }
        for(char c : new char[]{'a','b','c'}){
            if(curr.length() > 0 && curr.charAt(curr.length()-1) == c)
                continue;

            backtrack(n, curr + c);
        }
    }
}

