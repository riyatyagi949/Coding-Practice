// /**
//  * PROBLEM STATEMENT: 3093. Longest Common Suffix Queries
//  * --------------------------------------------------------------------------------
//  * Find the index of the string in `wordsContainer` that has the longest common
//  * suffix with each query string.
//  * Tie-breaking rules:
//  * 1. Shortest length.
//  * 2. Smallest original index in `wordsContainer`.
//  * --------------------------------------------------------------------------------
//  * OPTIMAL SOLUTION: Reverse-Trie
//  * --------------------------------------------------------------------------------
//  * - Insert reversed strings from `wordsContainer` into a Trie.
//  * - Each node in the Trie stores the `bestIndex`—the index of the best candidate 
//  * found so far passing through that node (based on length and insertion order).
//  * - For each query (also reversed), traverse the Trie as deep as possible.
//  * --------------------------------------------------------------------------------
//  * COMPLEXITY ANALYSIS:
//  * --------------------------------------------------------------------------------
//  * Time Complexity: O(L_c + L_q), where L_c is the sum of lengths of wordsContainer
//  * and L_q is the sum of lengths of wordsQuery. Each character is processed once.
//  * Space Complexity: O(L_c * 26) to store the Trie nodes.
 * --------------------------------------------------------------------------------
 
import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int idx = -1;
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        int best = 0;

        for (int i = 1; i < wordsContainer.length; i++) {

            if (wordsContainer[i].length() < wordsContainer[best].length()) {
                best = i;
            }
        }

        root.idx = best;

        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i, wordsContainer);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    void insert(String word, int index, String[] wordsContainer) {

        TrieNode node = root;

        update(node, index, wordsContainer);

        for (int i = word.length() - 1; i >= 0; i--) {

            char ch = word.charAt(i);

            int id = ch - 'a';

            if (node.child[id] == null) {
                node.child[id] = new TrieNode();
            }

            node = node.child[id];

            update(node, index, wordsContainer);
        }
    }

    void update(TrieNode node, int index, String[] wordsContainer) {

        if (node.idx == -1) {
            node.idx = index;
            return;
        }

        int oldLen = wordsContainer[node.idx].length();
        int newLen = wordsContainer[index].length();

        if (newLen < oldLen) {
            node.idx = index;
        }
    }

    int search(String word) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            int id = word.charAt(i) - 'a';

            if (node.child[id] == null) {
                break;
            }

            node = node.child[id];
        }

        return node.idx;
    }
}


