/**
 * PROBLEM STATEMENT: 2452. Words Within Two Edits of Dictionary
 * --------------------------------------------------------------------------------
 * You are given two string arrays, queries and dictionary. 
 * All words in each array have the same length.
 * * In one edit, you can change any letter in a word to any other letter.
 * Find all words from queries that, after a maximum of TWO edits, match 
 * some word from the dictionary.
 * * Return the list of matches in the same order they appear in queries.
 * * Example:
 * Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * Output: ["word","note","wood"]
 * Explanation:
 * - "word" -> "wood" (1 edit: 'r' to 'o') -> Valid
 * - "note" -> "joke" (2 edits: 'n' to 'j', 't' to 'k') -> Valid
 * - "ants" -> needs more than 2 edits to match any dictionary word -> Invalid
 * - "wood" -> "wood" (0 edits) -> Valid
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Brute Force Comparison
 * --------------------------------------------------------------------------------
 * 1. Since the constraints are small (queries and dictionary size <= 100, 
 * word length <= 100), a brute-force approach comparing each query word 
 * against every dictionary word is efficient.
 * 2. For each query word 'q':
 * - Iterate through each dictionary word 'd'.
 * - Count the number of character differences (Hamming Distance).
 * - If the difference is <= 2, 'q' is valid. Add it to the result and 
 * immediately move to the next query word (break the inner loop).
 * 3. Return the collected list of valid queries.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(Q * D * N)
 * - Q = number of queries (up to 100)
 * - D = size of dictionary (up to 100)
 * - N = length of each word (up to 100)
 * - Total operations: ~1,000,000, which is well within the 1-second limit.
 * * Space Complexity: O(1) 
 * - Excluding the space required for the output list, we only use a few 
 * primitive variables for counting and looping.
 * --------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        
        for (String q : queries) {
         for (String d : dictionary) {
                int diff = 0;
                for (int i = 0; i < q.length(); i++) {
                    if (q.charAt(i) != d.charAt(i)) {
                        diff++;
                        if (diff > 2) 
                        break;
                    }
                }
                 if (diff <= 2) {
                    ans.add(q);
                    break;
                }
            }
        }
        
        return ans;
    }
}

