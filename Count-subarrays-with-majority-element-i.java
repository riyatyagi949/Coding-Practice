/**
 * PROBLEM STATEMENT: 3737. Count Subarrays With Majority Element I
 * --------------------------------------------------------------------------------
 * Given an array 'nums' and an integer 'target', return the number of subarrays 
 * where 'target' is the majority element.
 * A majority element in a subarray of length L appears strictly more than L/2 times.
 * * Logic:
 * Let 'x' be the count of 'target' in a subarray and 'y' be the count of non-target 
 * elements. The condition is: x > y, which is equivalent to x > (length - x), 
 * or 2*x - length > 0.
 * If we map 'target' to +1 and all other elements to -1, the condition 
 * becomes: sum(subarray) > 0.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Prefix Sum + Fenwick Tree / Balanced BST (for O(N log N))
 * --------------------------------------------------------------------------------
 * 1. Transform 'nums' into 'val' array: +1 if nums[i] == target, -1 otherwise.
 * 2. Calculate prefix sums of the 'val' array. Let P[i] be prefix sum up to index i.
 * A subarray [j, i] has sum > 0 if P[i] - P[j-1] > 0, or P[j-1] < P[i].
 * 3. We iterate through the array and count how many previous prefix sums are 
 * strictly less than the current prefix sum.
 * 4. Since the prefix sum can range from -N to +N, we use a Fenwick Tree (or 
 * Frequency Array with offset) to count occurrences efficiently.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the array (using a frequency 
 * array with offset since N is small, 1000).
 * Space Complexity: O(N) to store the frequency/Fenwick tree.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int countSubarrays(int[] nums, int target) {
        int n = nums.length;
        // Frequency array to store counts of prefix sums. 
        // Offset is 'n' to handle negative indices (range -n to n).
        int[] freq = new int[2 * n + 1];
        
        int currentPrefixSum = 0;
        int count = 0;
        
        // Base case: prefix sum of 0 appears once (before the start of the array)
        freq[0 + n]++;
        
        for (int num : nums) {
            // Update prefix sum: +1 if target, -1 otherwise
            currentPrefixSum += (num == target) ? 1 : -1;
            
            // We need P[j-1] < P[i]. 
            // So we sum up frequencies of all values from -n to currentPrefixSum - 1.
            for (int val = -n; val < currentPrefixSum; val++) {
                count += freq[val + n];
            }
            
            // Record the current prefix sum
            freq[currentPrefixSum + n]++;
        }
        
        return count;
    }
}
