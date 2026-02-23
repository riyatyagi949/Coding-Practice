/**
 * PROBLEM STATEMENT: 1461. Check If a String Contains All Binary Codes of Size K
 * --------------------------------------------------------------------------------
 * Given a binary string 's' and an integer 'k', return true if every binary code 
 * of length 'k' is a substring of 's'. Otherwise, return false.
 * * * What are binary codes of length k?
 * They are all possible combinations of 0s and 1s with length k. 
 * The total number of such codes is 2^k.
 * * * Example:
 * Input: s = "00110110", k = 2
 * Output: true
 * Binary codes of length 2: "00", "01", "10", "11".
 * All of them appear in the string.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sliding Window with Hash Set
 * --------------------------------------------------------------------------------
 * 1. Calculate Needed Count:
 * The total number of unique binary strings of length k is 2^k. We can compute 
 * this using the bitwise shift: `1 << k`.
 * * 2. Sliding Window:
 * We iterate through the string 's' and extract every possible substring of 
 * length k using a sliding window.
 * * 3. Store in HashSet:
 * We add each extracted substring to a HashSet. Since a Set only stores unique 
 * values, the size of the set will represent the count of unique binary codes 
 * found in the string.
 * * 4. Early Exit:
 * If the set size reaches 2^k at any point during the iteration, we can 
 * immediately return true, as we have found all possible codes.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * K)
 * - N is the length of string 's'.
 * - We iterate through the string once (N - K times).
 * - In each iteration, `s.substring(i, i + k)` takes O(K) time.
 * - HashSet operations (add) take O(K) on average to compute the hash of the string.
 * * Space Complexity: O(2^K * K)
 * - The HashSet can store up to 2^K unique strings.
 * - Each string has a length of K.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int needed = 1 << k; 
        HashSet<String> set = new HashSet<>();
        
        for(int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            set.add(sub);
            
            if(set.size() == needed)
                return true;
        }
        return set.size() == needed;
    }
}


