// ### Problem Statement

// Input: A binary string `s` and integers `minJump`, `maxJump`.
// Starting Position: Index 0.
// Rules: You can move from $i$ to $j$ if $i + \text{minJump} \le j \le \min(i + \text{maxJump}, \text{s.length} - 1)$ and `s[j] == '0'`.
// Goal: Return `true` if index `s.length - 1` is reachable, otherwise `false`.

// ### Optimal Solution

// * We use a `boolean[] reachable` array to keep track of indices from which we can reach the end.
// * We maintain a sliding window `count` representing the number of reachable indices within the range $[i - \text{maxJump}, i - \text{minJump}]$.
// * For each index $i$:
// 1. If $i$ is within the range to accept a new potential jump from $i - \text{minJump}$, add it to the count.
// 2. If the index $i - \text{maxJump} - 1$ falls out of the window, decrement the count.
// 3. $i$ is reachable if `count > 0` and `s[i] == '0'`.


// ### Java Implementation

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        
        reachable[0] = true;
        
        int count = 0;
        
        for (int i = 1; i < n; i++) {
             if (i >= minJump && reachable[i - minJump]) {
                count++;
            }
             if (i > maxJump && reachable[i - maxJump - 1]) {
                count--;
            }
             reachable[i] = count > 0 && s.charAt(i) == '0';
        }
        return reachable[n - 1];
    }
}


            
           

// ### Complexity Analysis

// Time Complexity: $O(N)$, where $N$ is the length of the string. We iterate through the string exactly once, and each addition/removal from the sliding window count is an $O(1)$ operation.
// Space Complexity: $O(N)$ for the `reachable` boolean array.
