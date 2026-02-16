/**
 * PROBLEM STATEMENT: Meeting Rooms
 * --------------------------------------------------------------------------------
 * Given a 2D array arr[][], where arr[i][0] is the starting time and arr[i][1] 
 * is the ending time of the i-th meeting, determine if a person can attend all 
 * meetings. A person can only attend one meeting at a time.
 * * Note: A person can attend a meeting if its start time is greater than or equal 
 * to the previous meeting's end time.
 * * * Example 1:
 * Input: arr[][] = [[1, 4], [10, 15], [7, 10]]
 * Output: true
 * * * Example 2:
 * Input: arr[][] = [[2, 4], [9, 12], [6, 10]]
 * Output: false (Meetings [6, 10] and [9, 12] overlap)
 * * * Constraints:
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i][j] <= 2*10^6
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sorting and Interval Comparison
 * --------------------------------------------------------------------------------
 * 1. Sorting:
 * To detect overlaps efficiently, we first sort all meetings based on their 
 * starting times. This ensures that we only need to compare each meeting with 
 * the one immediately following it.
 * * 2. Overlap Condition:
 * After sorting, if any meeting 'i' starts before the previous meeting 'i-1' 
 * finishes (i.e., arr[i][0] < arr[i-1][1]), then an overlap exists, and it is 
 * impossible to attend all meetings.
 * * 3. Greedy Choice:
 * This is a greedy approach because once sorted, we only need to verify the 
 * "local" constraint of non-overlapping adjacent intervals to guarantee a 
 * "global" solution.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array of N meetings takes O(N log N) time.
 * - The single-pass scan to check for overlaps takes O(N) time.
 * - Overall: O(N log N).
 * * Space Complexity: O(1) or O(log N)
 * - O(1) auxiliary space if sorting is done in-place.
 * - O(log N) space is typically used by the internal stack of sorting algorithms.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    static boolean canAttend(int[][] arr) {
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        
        for(int i = 1; i < arr.length; i++) {
            if(arr[i][0] < arr[i-1][1]) {
                return false;
            }
        }
         return true;
    }
}


