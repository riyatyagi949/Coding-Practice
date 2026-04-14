/**
 * PROBLEM STATEMENT: 2463. Minimum Total Distance Traveled
 * --------------------------------------------------------------------------------
 * There are robots and factories on the X-axis.
 * - 'robot[i]' is the position of the ith robot.
 * - 'factory[j] = [position_j, limit_j]' is the position and capacity of the factory.
 * * Robots move at the same speed and can move in either direction. When a robot 
 * reaches a factory with remaining capacity, it is repaired and stops.
 * * Goal: Minimize the total distance traveled by all robots. 
 * Constraints guarantee all robots can be repaired.
 * * Example:
 * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
 * Output: 4
 * Explanation: Robot at 0 -> Factory at 2 (dist 2). Robot at 4 -> Factory at 2 (dist 2).
 * Robot at 6 -> Factory at 6 (dist 0). Total = 2 + 2 + 0 = 4.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Dynamic Programming with Sorting
 * --------------------------------------------------------------------------------
 * 1. Sorting: 
 * First, sort both the robots and the factories. In an optimal assignment, 
 * if robot A is to the left of robot B, robot A will be repaired at a factory 
 * that is at the same position or to the left of the factory repairing robot B.
 * * 2. Flattening Factories:
 * To simplify the DP, we can expand the factories based on their limits. 
 * If factory at pos 2 has limit 2, we treat it as two separate slots at position 2.
 * * 3. DP State:
 * Let dp[i][j] be the minimum distance to repair the first 'i' robots using 
 * the first 'j' factory slots.
 * * 4. Transitions:
 * For each robot i and factory slot j:
 * - Option A: Don't use factory slot j for any robot: dp[i][j-1]
 * - Option B: Use factory slot j for robot i: dp[i-1][j-1] + abs(robot[i] - factory[j])
 * * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * M)
 * - N is the number of robots (up to 100).
 * - M is the total capacity of all factories (up to N * FactoryCount = 100 * 100 = 10,000).
 * - The nested loops run for N * M iterations.
 * * Space Complexity: O(N * M)
 * - We store the DP table. This can be optimized to O(M) using space compression, 
 * but O(N*M) is acceptable given the constraints.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -----

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private long solve(int ri, int fi, List<Integer> robot, List<Integer> positions, long[][] memo) {
        if (ri >= robot.size()) {
            return 0; 
        }
        if (fi >= positions.size()) {
            return (long) 1e12;
        }

        if (memo[ri][fi] != -1) {
            return memo[ri][fi];
        }

        long takeCurrentFactory = Math.abs(robot.get(ri) - positions.get(fi)) + solve(ri + 1, fi + 1, robot, positions, memo);
        long skip = solve(ri, fi + 1, robot, positions, memo);

        return memo[ri][fi] = Math.min(takeCurrentFactory, skip);
    }
     public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        int m = robot.size();

        List<Integer> positions = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int limit = f[1];
            for (int j = 0; j < limit; j++) {
                positions.add(pos);
            }
        }

        int n = positions.size();
        long[][] memo = new long[m + 1][n + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(0, 0, robot, positions, memo);
    }
}

