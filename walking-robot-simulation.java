/**
 * PROBLEM STATEMENT: 874. Walking Robot Simulation
 * --------------------------------------------------------------------------------
 * A robot on an infinite XY-plane starts at (0, 0) facing North (+Y direction).
 * It receives commands to move or turn:
 * - -2: Turn left 90 degrees.
 * - -1: Turn right 90 degrees.
 * - 1 <= k <= 9: Move forward k units, one unit at a time.
 * * Obstacles are placed at specific coordinates. If the robot hits an obstacle,
 * it stays at its current location and proceeds to the next command.
 * * Return the maximum squared Euclidean distance (x^2 + y^2) the robot reaches
 * at any point during its path.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: COORDINATE SIMULATION WITH HASHSET
 * --------------------------------------------------------------------------------
 * 1. Obstacle Lookup: Store obstacles in a HashSet for O(1) average lookup.
 * Since coordinates are integers, we can use a string key "x_y" or a 
 * mathematical hash (x * 60001 + y) to represent the 2D point uniquely.
 * 2. Direction Management: Use an array or vectors to represent North, East, 
 * South, and West. Turning left/right becomes an index rotation in this array.
 * - North: (0, 1), East: (1, 0), South: (0, -1), West: (-1, 0)
 * 3. Step-by-Step Movement: For a move command 'k', move one unit at a time. 
 * Check if the next coordinate exists in the obstacle set. If it does, stop
 * the current move command.
 * 4. Tracking Max: After every command (or step), calculate x^2 + y^2 and 
 * update the global maximum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(C + K + O)
 * - O is the number of obstacles (to build the set).
 * - C is the number of commands.
 * - K is the total number of steps moved forward (since k <= 9, K ≈ 9 * C).
 * Space Complexity: O(O)
 * - To store the coordinates of the obstacles in the HashSet.
 * --------------------------------------------------------------------------------
 */
// Code - 
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Use HashSet to store obstacle positions as strings
        HashSet<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            String key = obs[0] + "_" + obs[1];
            obstacleSet.add(key);
        }

        int x = 0;
        int y = 0;
        int maxDistance = 0;

        // Initially pointing North
        int[] dir = {0, 1}; // North

        // Process each command
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -2) 
            { // turn left 90 degrees
                dir = new int[]{-dir[1], dir[0]};

            }
             else if (commands[i] == -1) { 
                // turn right 90 degrees
                dir = new int[]{dir[1], -dir[0]};

            } 
            else { 
                // move forward step by step
                for (int step = 0; step < commands[i]; step++) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    String nextKey = newX + "_" + newY;

                    // If there's an obstacle, stop moving in this direction
                    if (obstacleSet.contains(nextKey)) {
                        break;
                    }

                    // Move to the new position
                    x = newX;
                    y = newY;
                }
            }

            // Update the maximum distance from the origin
            maxDistance = Math.max(maxDistance, x * x + y * y);
        }

        return maxDistance;
    }
}

