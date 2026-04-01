/**
 * PROBLEM STATEMENT: 2751. Robot Collisions
 * --------------------------------------------------------------------------------
 * You have n robots, each with a position, health, and direction ('L' or 'R').
 * All robots move at the same speed. When two robots collide:
 * 1. The robot with lower health is removed.
 * 2. The surviving robot's health decreases by 1.
 * 3. If healths are equal, both are removed.
 * * Goal: Return the health of surviving robots in their original input order.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SORTING + STACK-BASED SIMULATION
 * --------------------------------------------------------------------------------
 * 1. Tracking Indices: Since we must return survivors in the original order, we 
 * store the original indices and sort them based on their positions.
 * 2. Processing Order: We process robots from left to right (sorted by position).
 * 3. Stack Logic:
 * - If a robot moves 'R', we push it onto a stack. It can only collide with 
 * robots to its right moving 'L'.
 * - If a robot moves 'L', it attempts to collide with robots in the stack 
 * (which are all moving 'R' and are to its left).
 * 4. Collision Resolution:
 * - While the 'L' robot is alive and the stack is not empty:
 * - If 'L' health > 'R' health: 'R' is destroyed, 'L' health--, continue.
 * - If 'L' health < 'R' health: 'L' is destroyed, 'R' health--, 'R' stays in stack.
 * - If healths are equal: Both are destroyed.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(n \log n)$
 * - Sorting the indices takes $O(n \log n)$.
 * - The simulation pass takes $O(n)$ because each robot is pushed and popped 
 * from the stack at most once.
 * Space Complexity: $O(n)$
 * - We store the indices array, the stack, and the final result list.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Sort indices based on the robot's position on the line
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));

        // Stack will store indices of robots moving to the Right ('R')
        Deque<Integer> stack = new ArrayDeque<>();

        for (int curr : indices) {
            if (directions.charAt(curr) == 'R') {
                stack.push(curr);
            }
            else {
                // Current robot is moving Left ('L'), resolve collisions with 'R' robots in stack
                while (!stack.isEmpty() && healths[curr] > 0) {
                    int top = stack.pop();

                    if (healths[curr] > healths[top]) {
                        // 'L' robot wins
                        healths[curr] -= 1;
                        healths[top] = 0;
                    }
                    else if (healths[curr] < healths[top]) {
                        // 'R' robot wins
                        healths[top] -= 1;
                        healths[curr] = 0;
                        stack.push(top); // Put the surviving 'R' robot back
                    }
                    else {
                        // Mutual destruction
                        healths[curr] = 0;
                        healths[top] = 0;
                    }
                }
            }
        }

        // Collect healths of survivors in the original input order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }
        return result;
    }
}
