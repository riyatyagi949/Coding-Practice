/**
 * PROBLEM STATEMENT: 1732. Find the Highest Altitude
 * --------------------------------------------------------------------------------
 * A biker starts at point 0 with an altitude of 0. You are given an array 'gain'
 * where gain[i] is the net change in altitude between point i and point i+1.
 * Return the highest altitude reached during the trip.
 * * * Example:
 * Input: gain = [-5, 1, 5, 0, -7]
 * Altitudes: [0, -5, -4, 1, 1, -6]
 * Output: 1
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Prefix Sum / Running Accumulation
 * --------------------------------------------------------------------------------
 * 1. Initialize 'currentAltitude' to 0 and 'maxAltitude' to 0.
 * 2. Iterate through each gain value in the input array.
 * 3. Update 'currentAltitude' by adding the current gain.
 * 4. Compare 'currentAltitude' with 'maxAltitude' and update if it's higher.
 * 5. Return 'maxAltitude' after the loop finishes.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N), where N is the length of the gain array. We perform
 * a single pass through the array.
 * Space Complexity: O(1), as we only use two integer variables to keep track 
 * of the altitudes.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int largestAltitude(int[] gain) {
        int currentAltitude = 0;
        int maxAltitude = 0;

        for (int g : gain) {
            // Update the running altitude
            currentAltitude += g;
            // Track the maximum altitude seen so far
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        
        return maxAltitude;
    }
}
