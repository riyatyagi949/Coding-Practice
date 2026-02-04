/**
 * PROBLEM STATEMENT: Last Moment Before All Ants Fall Out
 * --------------------------------------------------------------------------------
 * We have a wooden plank of length 'n' units. Ants are walking on the plank at a 
 * speed of 1 unit per second. Some move left, others move right.
 * * * Collision Logic:
 * When two ants moving in opposite directions meet, they change directions. 
 * HOWEVER, because ants are identical, two ants meeting and "bouncing back" is 
 * mathematically identical to two ants "passing through" each other. 
 * * * Task:
 * Given 'n', an array 'left' (positions of ants moving left), and an array 
 * 'right' (positions of ants moving right), return the time when the last 
 * ant falls off the plank.
 * * * Constraints:
 * 1 <= n <= 10^5
 * 0 <= left.length, right.length <= n + 1
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Brain Teaser / Simulation Simplification
 * --------------------------------------------------------------------------------
 * 1. The Key Insight: 
 * Since all ants move at the same speed and are identical, a collision where 
 * they "swap directions" can be treated as if the ants simply passed through 
 * each other. If Ant A and Ant B meet, and A goes left while B goes right, it 
 * is the same as if Ant A continued its journey to the left and Ant B continued 
 * its journey to the right.
 * * 2. Calculation:
 * - For an ant at position 'p' moving LEFT: 
 * It needs 'p' seconds to reach 0 and fall off.
 * - For an ant at position 'p' moving RIGHT: 
 * It needs 'n - p' seconds to reach 'n' and fall off.
 * * 3. Final Answer:
 * The last moment is simply the maximum of all these individual travel times.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(L + R)
 * - We iterate through the 'left' array (length L) and the 'right' array (length R) once.
 * - Total operations are proportional to the number of ants.
 * * Space Complexity: O(1)
 * - We only use a single variable 'lastTime' to track the maximum value.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int getLastMoment(int n, int left[], int right[]) {
        int lastTime = 0;

        for (int pos : left) {
            lastTime = Math.max(lastTime, pos);
        }
        for (int pos : right) {
            lastTime = Math.max(lastTime, n - pos);
        }
        return lastTime;
    }
}


