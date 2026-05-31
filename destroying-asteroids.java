/**
 * PROBLEM STATEMENT: 2126. Destroying Asteroids
 * --------------------------------------------------------------------------------
 * You have a planet with an initial 'mass'. You are given an array 'asteroids' where
 * each element is the mass of an asteroid.
 * - If planet_mass >= asteroid_mass: The asteroid is destroyed, and the planet gains
 * the mass of that asteroid.
 * - If planet_mass < asteroid_mass: The planet is destroyed.
 * * Goal: Determine if all asteroids can be destroyed by colliding in any order.
 * * Constraints:
 * - 1 <= mass <= 10^5
 * - 1 <= asteroids.length <= 10^5
 * - 1 <= asteroids[i] <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY STRATEGY
 * --------------------------------------------------------------------------------
 * 1. Sorting: To maximize our chances, we should always target the smallest 
 * available asteroid first. This allows the planet to accumulate mass incrementally, 
 * increasing its capacity to destroy larger asteroids later.
 * 2. Greedy Loop: 
 * - Sort the 'asteroids' array in non-decreasing order.
 * - Iterate through the sorted array. If the current planet mass is less than 
 * the asteroid mass, we cannot destroy it (and cannot destroy any subsequent, 
 * even larger asteroids), so return false.
 * - Otherwise, add the asteroid mass to the planet mass and continue.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array takes O(N log N), where N is the number of asteroids.
 * - The single pass through the array takes O(N).
 * Space Complexity: O(1) or O(log N)
 * - Sorting is typically done in-place (or with O(log N) stack space), requiring 
 * negligible extra space.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long currMass = mass;

        for (int asteroid : asteroids) {
            if (currMass < asteroid) {
                return false;
            }
             currMass += asteroid;
        }
        return true;
    }
}

