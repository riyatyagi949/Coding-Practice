/**
 * PROBLEM STATEMENT: 1980. Find Unique Binary String
 * --------------------------------------------------------------------------------
 * Given an array of strings 'nums' containing 'n' unique binary strings each 
 * of length 'n', return a binary string of length 'n' that does NOT appear in nums. 
 * If there are multiple answers, you may return any of them.
 * * Example 1:
 * Input: nums = ["01","10"]
 * Output: "11" (or "00")
 * * Example 2:
 * Input: nums = ["00","01"]
 * Output: "11" (or "10")
 * * Constraints:
 * - n == nums.length
 * - 1 <= n <= 16
 * - nums[i].length == n
 * - All strings in nums are unique.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Cantor's Diagonalization Argument
 * --------------------------------------------------------------------------------
 * To ensure we construct a binary string that is different from every single 
 * string in the input array 'nums', we can use a diagonal construction strategy:
 * * 1. We want to build a string 'res' of length 'n'.
 * 2. For the i-th character of our result string, we look at the i-th character 
 * of the i-th string in the input array (nums[i]).
 * 3. We simply FLIP that bit. If nums[i].charAt(i) is '0', we set res[i] to '1'. 
 * If it is '1', we set res[i] to '0'.
 * * Why this works:
 * - Our 'res' differs from nums[0] at the 0-th index.
 * - Our 'res' differs from nums[1] at the 1st index.
 * - ...
 * - Our 'res' differs from nums[i] at the i-th index.
 * * Since 'res' differs from every string in 'nums' by at least one bit, it is 
 * guaranteed to be unique and not present in the input.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We iterate through the list of 'n' strings exactly once.
 * - String building takes O(n) total.
 * Space Complexity: O(n) (or O(1) auxiliary space)
 * - We only use a StringBuilder to construct the result string of length 'n'.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder res = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            if(nums[i].charAt(i) == '0')
                res.append('1');
            else
                res.append('0');
        }
         return res.toString();
    }
}

