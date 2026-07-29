// Problem Statement:
// You are given a palindromic string s and an integer k.
// Return the k-th lexicographically smallest palindromic permutation of s. 
// If there are fewer than k distinct palindromic permutations, return an empty string.
// Note: Different rearrangements that yield the same palindromic string are considered identical and are counted once.
// Constraints: 1 <= s.length <= 10^4, 1 <= k <= 10^6.

// Optimal Solution in Java:
// Runtime: 2 ms
// Time Complexity: O(L * 26 * log(26)) where L is the half-length of the string, due to multinomial coefficient calculations at each position.
// Space Complexity: O(L) for recursion stack and StringBuilder storage.

class Solution {
    long[] fact; 
    static long maxK = 1000001;  
    
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];  
        
        // Count frequencies of each character
        for (char c : s.toCharArray()) freq[c - 'a']++;
        
        String mid = "";  
         
        // Find the middle character if any character has an odd frequency
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
               mid = String.valueOf((char)(i + 'a'));  
               break;
            }
        }
        
        int[] half = new int[26];  
        int len = 0;  
        
        // Form the frequency array for the left half of the palindrome
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i]; 
        }
        
        computeFactorials(len);  

        StringBuilder halfStr = new StringBuilder();  
        
        // Build the k-th lexicographically smallest half string
        if (!buildKthPalindrome(half, k, halfStr, len))
            return "";  

        // Construct the full palindrome: halfStr + mid + reversed(halfStr)
        StringBuilder rev = new StringBuilder(halfStr).reverse();
        return halfStr.toString() + mid + rev.toString();
    }
    
    // Helper method to recursively build the k-th palindrome half
    private boolean buildKthPalindrome(int[] freq, long k, StringBuilder sb, int len) {
        if (len == 0) 
            return true; 
        
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0)
                continue;  

            freq[i]--;  
            long perms = multinomial(freq); 

            // If k falls within the permutations count starting with character i, choose it
            if (k <= perms) {
                sb.append((char)(i + 'a')); 
                return buildKthPalindrome(freq, k, sb, len - 1); 
            } else {
                // Otherwise, skip these permutations and try the next character
                k -= perms; 
                freq[i]++;  
            }
        }
        return false; 
    }
    
    // Calculates the multinomial coefficient for the remaining character counts
    private long multinomial(int[] counts) {
        int tot = 0;
        for (int x : counts) 
            tot += x;  

        long res = 1;
        for (int i = 0; i < 26; i++) {
            int cnt = counts[i];
            res = res * binom(tot, cnt);  

            if (res >= maxK) 
                return maxK;  

            tot -= cnt; 
        }
        return res;
    }
    
    // Helper method to calculate binomial coefficients with a cap on maxK
    private long binom(int n, int k) {
        if (k > n) 
            return 0;  

        if (k > n - k) 
            k = n - k; 
        
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i; 

            if (res >= maxK) 
                return maxK;  
        }
        return res;
    }
    
    // Precompute factorials helper (retained as part of structure)
    private void computeFactorials(int n) {
        fact = new long[n + 1];
        fact[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }
}
