/**
 * PROBLEM STATEMENT: Pythagorean Triplet
 * --------------------------------------------------------------------------------
 * Given an array arr[], return true if there is a triplet (a, b, c) from the array 
 * (where a, b, and c are on different indexes) that satisfies a^2 + b^2 = c^2, 
 * otherwise return false.
 * * Examples:
 * Input: arr[] = [3, 2, 4, 6, 5]
 * Output: true
 * Explanation: a=3, b=4, and c=5 forms a pythagorean triplet (9 + 16 = 25).
 * * Input: arr[] = [3, 8, 5]
 * Output: false
 * * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= arr[i] <= 10^3
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Frequency Array / Hashing Approach
 * --------------------------------------------------------------------------------
 * Since the values in the array are small (arr[i] <= 1000), we can optimize 
 * significantly by using a frequency array (or boolean presence array).
 * * 1. Squaring and Hashing: We represent the existence of squares in a boolean array.
 * - Find the maximum value in the input array.
 * - Create a boolean array 'exists' of size (maxVal * maxVal + 1).
 * - For each element x in the input, set exists[x*x] = true.
 * * 2. Pairwise Check: Iterate through all possible values of 'a' and 'b' up to maxVal.
 * - For every i where i^2 exists and every j where j^2 exists:
 * - Check if (i^2 + j^2) exists in our boolean array.
 * * Why this is optimal: 
 * Even though the input size N is 10^5, the number of *distinct* values is 
 * at most 1000. Checking pairs of values (1 to 1000) is much faster than 
 * checking pairs of indices in a 10^5 size array.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(maxVal^2 + N)
 * - N to find max value and populate the existence array.
 * - O(maxVal^2) to iterate through pairs of possible values (1000 * 1000).
 * Space Complexity: O(maxVal^2)
 * - To store the existence of squared values up to 1000^2.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    boolean pythagoreanTriplet(int[] arr) {
        int n = arr.length;
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i=0;i<n;i++){
            arr[i] = arr[i]*arr[i];
            set.add(arr[i]);
        }
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr[i] + arr[j];
                if(set.contains(sum))
                    return true;
            }
        }
        
        return false;
    }
}

