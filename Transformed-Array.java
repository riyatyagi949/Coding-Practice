/**
 * PROBLEM STATEMENT:
 * Given a circular integer array 'nums', create a new array 'result' of the same size.
 * For each index i:
 * - If nums[i] > 0: Move nums[i] steps to the right. 
 * - If nums[i] < 0: Move abs(nums[i]) steps to the left.
 * - If nums[i] == 0: Stay at the same index.
 * * result[i] should be the value of the index where you land.
 * * Since the array is circular:
 * - Moving past the last element wraps to the beginning.
 * - Moving before the first element wraps to the end.
 */
/**
     * OPTIMAL SOLUTION:
     * We iterate through each element once. To handle circular indexing,
     * we use the formula: (current_index + steps) % length.
     * * In Java, the % operator can return negative values for negative inputs.
     * To ensure we always get a positive index within [0, n-1], we use:
     * targetIndex = ((i + nums[i]) % n + n) % n;
     */

// Code - 
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            // Calculate the target index using circular logic
            // (i + nums[i]) gives the raw destination
            // % n keeps it within the range [-n+1, n-1]
            // + n ensures the value is positive
            // final % n handles cases where (i + nums[i]) was already positive
            int targetIndex = ((i + nums[i]) % n + n) % n;
            
            result[i] = nums[targetIndex];
        }
        return result;
    }
}
// Alternative code - 
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int shift = nums[i] % n;  

            int newIdx = (i + shift) % n;

            if (newIdx < 0) {
                newIdx += n;
            }
            result[i] = nums[newIdx];
        }
        return result;
    }
}

/**
 * COMPLEXITY ANALYSIS:
 * * Time Complexity: O(n)
 * - We iterate through the array 'nums' exactly once.
 * - Inside the loop, all operations (addition, modulo) are O(1).
 * - n is the length of the input array.
 * * Space Complexity: O(n)
 * - We create a 'result' array of size n to store the transformed values.
 * - If the output array is not counted as extra space, the auxiliary 
 * space complexity is O(1).
 */
