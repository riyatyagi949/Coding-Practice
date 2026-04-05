/**
 * PROBLEM STATEMENT: 657. Robot Return to Origin
 * --------------------------------------------------------------------------------
 * There is a robot starting at position (0, 0) on a 2D plane. 
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) 
 * after it completes its moves.
 * * You are given a string 'moves' representing the move sequence:
 * - 'U': Up (y + 1)
 * - 'D': Down (y - 1)
 * - 'L': Left (x - 1)
 * - 'R': Right (x + 1)
 * * Return true if the robot returns to the origin (0,0), false otherwise.
 * * Example 1:
 * Input: moves = "UD"
 * Output: true
 * * Example 2:
 * Input: moves = "LL"
 * Output: false
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: COORDINATE SIMULATION
 * --------------------------------------------------------------------------------
 * 1. Initialize two variables, x and y, to 0 to represent the origin.
 * 2. Iterate through each character in the 'moves' string.
 * 3. Update the coordinates based on the character:
 * - 'U' increment y
 * - 'D' decrement y
 * - 'L' decrement x
 * - 'R' increment x
 * 4. After the loop, check if both x and y are back to 0.
 * * Logic Note:
 * For the robot to return to the origin, the number of 'U' moves must equal the 
 * number of 'D' moves, and the number of 'L' moves must equal the number of 'R' moves.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - N is the length of the 'moves' string. We traverse the string once.
 * * Space Complexity: O(1)
 * - We only use two integer variables (x, y) regardless of the input size.
 * --------------------------------------------------------------------------------
 */

class Solution {
    /**
     * Determines if the robot returns to (0,0) after a sequence of moves.
     * @param moves A string containing 'U', 'D', 'L', and 'R'.
     * @return true if ending position is (0,0), false otherwise.
     */
    public boolean judgeCircle(String moves) {
        // Horizontal displacement
        int x = 0;
        // Vertical displacement
        int y = 0;

        // Iterate through each move in the sequence
        for (char move : moves.toCharArray()) {
            // Update vertical position
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } 
            // Update horizontal position
            else if (move == 'L') {
                x--;
            } else if (move == 'R') {
                x++;
            }
        }

        // Return true only if both coordinates are back at zero
        return x == 0 && y == 0;
    }
}
