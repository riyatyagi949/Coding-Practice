/**
 * PROBLEM STATEMENT: 3043. Find the Length of the Longest Common Prefix
 * --------------------------------------------------------------------------------
 * Given two arrays of positive integers, find the length of the longest integer 
 * that is a prefix of at least one element from 'arr1' and one from 'arr2'.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Trie (Prefix Tree)
 * --------------------------------------------------------------------------------
 * 1. Construction: Create a Trie where each node represents a digit (0-9).
 * 2. Insertion: Insert every number from 'arr1' into the Trie digit by digit. 
 *    Since we only care about common prefixes, the path from the root represents 
 *    the sequence of digits.
 * 3. Search: For every number in 'arr2', traverse the Trie as deep as possible.
 *    The depth at which the traversal stops (or fails to find the next digit) 
 *    represents the length of the longest common prefix for that specific number.
 * 4. Result: Maintain the global maximum length found during searches.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * L1 + M * L2)
 * - N, M are lengths of arr1 and arr2. 
 * - L1, L2 are the average number of digits in the integers (max 8-9).
 * Space Complexity: O(N * L1)
 * - In the worst case, every digit of every number in arr1 is stored as a node in the Trie.
 * --------------------------------------------------------------------------------
 */

class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[10];
    }

    TrieNode root = new TrieNode();

    public void insert(int num) {

        String s = String.valueOf(num);

        TrieNode node = root;

        for (char ch : s.toCharArray()) {

            int idx = ch - '0';

            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }

            node = node.child[idx];
        }
    }

    public int search(int num) {

        String s = String.valueOf(num);

        TrieNode node = root;

        int len = 0;

        for (char ch : s.toCharArray()) {

            int idx = ch - '0';

            if (node.child[idx] == null) {
                break;
            }

            len++;
            node = node.child[idx];
        }

        return len;
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        for (int num : arr1) {
            insert(num);
        }

        int ans = 0;

        for (int num : arr2) {
            ans = Math.max(ans, search(num));
        }

        return ans;
    }
}
