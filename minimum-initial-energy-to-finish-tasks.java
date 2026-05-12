/**
 * PROBLEM STATEMENT: 1665. Minimum Initial Energy to Finish Tasks
 * --------------------------------------------------------------------------------
 * You are given an array 'tasks' where tasks[i] = [actual_i, minimum_i].
 * - 'actual_i' is the energy spent to finish the task.
 * - 'minimum_i' is the energy required to start the task.
 * 
 * You can finish tasks in any order. Return the minimum initial energy required 
 * to complete all tasks.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY APPROACH
 * --------------------------------------------------------------------------------
 * The core intuition is to prioritize tasks that have a larger "requirement gap."
 * The gap is defined as $(minimum_i - actual_i)$. 
 * 
 * Why?
 * Tasks with a large gap require much more energy to START than they actually 
 * CONSUME. By doing these tasks first, we use our high initial energy to satisfy 
 * the 'minimum' requirement, and since we only spend 'actual', we preserve as 
 * much energy as possible for subsequent tasks.
 * 
 * 1. Sort the tasks in descending order of (minimum - actual).
 * 2. Maintain two variables:
 *    - 'ans': The total initial energy we've had to "fund" so far.
 *    - 'currentEnergy': The energy currently available to perform the next task.
 * 3. For each task:
 *    - If 'currentEnergy' is less than the 'minimum' required, we must increase 
 *      our starting pool (ans) and our current pool by the difference.
 *    - Subtract the 'actual' energy spent from 'currentEnergy'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: $O(N \log N)$
 * - Sorting the tasks array takes $O(N \log N)$.
 * - The single pass through the array takes $O(N)$.
 * 
 * Space Complexity: $O(\log N)$ or $O(1)$
 * - $O(\log N)$ is typically required for the recursion stack of the sorting 
 *   algorithm (Arrays.sort). No additional significant space is used.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int ans = 0;         
        int currentEnergy = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            if (currentEnergy < minimum) {
                ans += (minimum - currentEnergy);
                currentEnergy = minimum;
            }
            currentEnergy -= actual;
        }

        return ans;
    }
}
