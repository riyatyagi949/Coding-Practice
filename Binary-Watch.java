/**
 * PROBLEM STATEMENT: 401. Binary Watch
 * --------------------------------------------------------------------------------
 * A binary watch has 4 LEDs on the top representing hours (0-11) and 6 LEDs on 
 * the bottom representing minutes (0-59). Each LED represents a binary bit (1 or 0).
 * * Given an integer 'turnedOn' representing the total number of LEDs currently ON, 
 * return all possible times the watch could represent in any order.
 * * Rules:
 * - Hour must not have leading zeros (e.g., "1:00", not "01:00").
 * - Minutes must be two digits (e.g., "10:02", not "10:2").
 * * Example:
 * Input: turnedOn = 1
 * Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Exhaustive Search with Bit Counting
 * --------------------------------------------------------------------------------
 * 1. Constraint Analysis:
 * The total number of possible times is very small (12 hours * 60 minutes = 720).
 * Instead of generating combinations of bits, it is much simpler to iterate 
 * through all possible valid times and check if their bit representation matches 
 * the 'turnedOn' count.
 * * 2. Logic:
 * - Loop through hours from 0 to 11.
 * - Loop through minutes from 0 to 59.
 * - For each pair (h, m), count the number of set bits (1s) in 'h' and 'm'.
 * - In Java, `Integer.bitCount(n)` efficiently returns the number of set bits.
 * - If (bitCount(h) + bitCount(m) == turnedOn), format the string and add to result.
 * * 3. Formatting:
 * Use string concatenation or `String.format` to ensure minutes < 10 have 
 * a leading zero (e.g., "02" instead of "2").
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(1)
 * - We always iterate through a fixed number of combinations (12 * 60 = 720).
 * - Since the input size does not grow (hours and minutes are constant limits), 
 * the execution time is constant.
 * * Space Complexity: O(1)
 * - Excluding the output list, we only use a few variables.
 * - The output list size is also bounded by the maximum possible valid times (720).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn)
                {
                   StringBuilder time = new StringBuilder();
                    time.append(h).append(":");
                    if (m < 10) {
                        time.append("0");
                    }
                    time.append(m);
                    
                    result.add(time.toString());
                }
            }
        }
       return result;
    }
}
