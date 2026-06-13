/**
 * PROBLEM STATEMENT: 3838. Weighted Word Mapping
 * --------------------------------------------------------------------------------
 * Given an array of strings 'words' and an integer array 'weights' (length 26, 
 * mapping 'a'-'z'), calculate the total weight for each word.
 * For each word, map the (total_weight % 26) to a character in reverse 
 * alphabetical order (where 0 maps to 'z', 1 to 'y', ..., 25 to 'a').
 * Return the resulting string.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Simulation
 * --------------------------------------------------------------------------------
 * 1. For each word in the input array, iterate through its characters.
 * 2. Calculate the sum of weights for all characters in the word using 
 * weights[char - 'a'].
 * 3. Calculate 'mod' = sum % 26.
 * 4. The resulting character is obtained by 'z' - mod.
 * 5. Append this character to the result string and return.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the total number of characters across all 
 * strings in the words array. We visit every character exactly once.
 * Space Complexity: O(1) auxiliary space, as the mapping and calculation use a 
 * fixed number of variables. The output string space is typically not counted.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
             int sum = 0;
         for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];
            }
            int mod = sum % 26;
            ans.append((char)('z' - mod));
        }
        return ans.toString();
    }
}
