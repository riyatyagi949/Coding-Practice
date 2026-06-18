/**
 * PROBLEM STATEMENT: 1344. Angle Between Hands of a Clock
 * --------------------------------------------------------------------------------
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) 
 * formed between the hour and the minute hand.
 *
 * * Key Mechanics:
 * - A clock is a circle of 360 degrees.
 * - The minute hand moves 360 degrees in 60 minutes, which is 6 degrees per minute.
 * - The hour hand moves 360 degrees in 12 hours, which is 30 degrees per hour.
 * - Crucially, the hour hand also moves as the minutes progress: 
 * it moves 30 degrees in 60 minutes, which is 0.5 degrees per minute.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Mathematical Calculation
 * --------------------------------------------------------------------------------
 * 1. Calculate the position of the minute hand (in degrees): 
 * `minAngle = minutes * 6`
 * 2. Calculate the position of the hour hand (in degrees):
 * - Base position from hours: `(hour % 12) * 30`
 * - Additional displacement from minutes: `minutes * 0.5`
 * - Total: `hourAngle = (hour % 12) * 30 + (minutes * 0.5)`
 * 3. Calculate the absolute difference: `diff = Math.abs(hourAngle - minAngle)`
 * 4. The smaller angle is the minimum of `diff` and `360 - diff`.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(1) - Constant time mathematical operations.
 * Space Complexity: O(1) - No extra data structures used.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate degrees for the minute hand
        double minAngle = minutes * 6.0;
        
        // Calculate degrees for the hour hand
        // (hour % 12) handles the 12-hour cycle (12 o'clock is 0 degrees)
        double hourAngle = ((hour % 12) * 30.0) + (minutes * 0.5);
        
        // Find the absolute difference
        double diff = Math.abs(hourAngle - minAngle);
        
        // The problem asks for the smaller angle
        return Math.min(diff, 360.0 - diff);
    }
}
