/**
 * PROBLEM STATEMENT: 1356. Sort Integers by The Number of 1 Bits
 * --------------------------------------------------------------------------------
 * You are given an integer array 'arr'. Sort the integers in the array in 
 * ascending order by the number of 1's in their binary representation.
 * * If two or more integers have the same number of 1's, you must sort them in 
 * ascending order based on their numerical value.
 *
 * * Example 1:
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explanation: 
 * [0] has 0 bits.
 * [1,2,4,8] have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 *
 * * Constraints:
 * - 1 <= arr.length <= 500
 * - 0 <= arr[i] <= 10^4
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Custom Comparator with Bit Counting
 * --------------------------------------------------------------------------------
 * 1. Sorting Strategy:
 * We use a custom sorting logic. For any two numbers 'a' and 'b':
 * - Count the number of set bits (1's) in both.
 * - If count(a) != count(b), the one with fewer bits comes first.
 * - If count(a) == count(b), the one with the smaller numerical value comes first.
 *
 * 2. Bit Counting:
 * In Java, we can efficiently count set bits using `Integer.bitCount(n)`.
 *
 * 3. Implementation Detail:
 * Since `Arrays.sort()` on primitive `int[]` doesn't support custom comparators, 
 * we can either:
 * - Convert the `int[]` to an `Integer[]` wrapper array.
 * - Use a "bit-hack" technique to sort in-place (e.g., storing the bit count in 
 * higher bits of the number, though this is only safe for small ranges).
 * - Here, we provide the clean, standard custom sorting approach.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - N is the length of the array. Sorting takes O(N log N) comparisons.
 * - Each comparison involves `Integer.bitCount()`, which is an O(1) operation 
 * (constant time for a 32-bit integer).
 * Space Complexity: O(N)
 * - To use a custom comparator, we convert the primitive `int[]` to an `Integer[]`.
 * --------------------------------------------------------------------------------*/

 // Optimal Solution in Java -

class Solution {
    public int[] sortByBits(int[] arr) {
         Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        
        Arrays.sort(nums, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            
            if(bitA == bitB) {
                return a - b;
            }
            return bitA - bitB;
        });
        for(int i = 0; i < arr.length; i++) {
            arr[i] = nums[i];
        }
       return arr;
    }
}
