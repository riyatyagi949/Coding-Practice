/**
 * PROBLEM STATEMENT: 2833. Furthest Point From Origin
 * --------------------------------------------------------------------------------
 * You are given a string 'moves' of length n consisting only of characters 
 * 'L', 'R', and '_'. Starting from origin 0 on a number line:
 * - 'L' moves you 1 unit left (-1).
 * - 'R' moves you 1 unit right (+1).
 * - '_' can be treated as either 'L' or 'R'.
 * * Task: Return the maximum possible distance from the origin you can reach 
 * after exactly n moves.
 * * Example:
 * Input: moves = "L_RL__R"
 * Output: 3
 * Explanation: Choosing 'L' for all '_' results in "LLRLLLR", ending at -3. 
 * The distance is | -3 | = 3.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Greedy Counting
 * --------------------------------------------------------------------------------
 * 1. Observations:
 * - To get the maximum distance, we should make all the '_' characters 
 * move in the same direction as the dominant fixed direction ('L' or 'R').
 * - The net displacement from fixed moves is (count of 'R' - count of 'L').
 * - The absolute value |countR - countL| gives our distance from the origin
 * using only fixed moves.
 * - Since '_' can be anything, we simply add the count of '_' to this 
 * absolute difference to maximize the reach in whichever direction was already 
 * ahead (or choose one if they are tied).
 * * 2. Algorithm:
 * - Count the occurrences of 'L', 'R', and '_'.
 * - Result = Math.abs(countL - countR) + countUnderscore.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We perform a single pass over the string of length N to count the characters.
 * Space Complexity: O(1)
 * - We only store three integer counters regardless of the input size.
 * --------------------------------------------------------------------------------
 */
// Code - 

class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blank = 0;

        for (char ch : moves.toCharArray()) {
            if (ch == 'L') 
            left++;
            else if (ch == 'R') 
            right++;
            else blank++;
        }
        return Math.abs(left - right) + blank;
    }
}

