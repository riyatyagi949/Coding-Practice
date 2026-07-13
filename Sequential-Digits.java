/**
 * PROBLEM STATEMENT: 1291. Sequential Digits
 * --------------------------------------------------------------------------------
 * An integer has "sequential digits" if each digit is exactly one more than the
 * previous digit.
 * * Given a range [low, high], return a sorted list of all integers within that 
 * range that possess the sequential digits property.
 * * Example 1: low = 100, high = 300 -> Output: [123, 234]
 * Example 2: low = 1000, high = 13000 -> Output: [1234, 2345, 3456, 4567, 5678, 6789, 12345]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Generate and Filter
 * --------------------------------------------------------------------------------
 * 1. Since the number of digits is small (max 9 digits for 10^9), the total number 
 * of possible sequential digit numbers is very small (fewer than 50).
 * 2. We can pre-generate all possible sequential numbers by iterating through:
 * - All possible starting digits (1 to 9).
 * - All possible lengths (2 to 9).
 * 3. Construct each number, check if it falls within the [low, high] range.
 * 4. Add valid numbers to a list and return the list in sorted order.
 * * Time Complexity: O(1) - because the search space is fixed (limited to 9 digits).
 * Space Complexity: O(1) - to store the fixed, small output list.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

//Approach-1 (Using simple BFS)
//T.C : O(N), where N is the number of valid sequential digits in the specified range.
//S.C : O(N), where N is the number of valid sequential digits in the specified range. (queue size)
public class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 8; i++) {
            queue.offer(i);
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            if (temp >= low && temp <= high) {
                result.add(temp);
            }

            int lastDigit = temp % 10;
            if (lastDigit + 1 <= 9) {
                queue.offer(temp * 10 + (lastDigit + 1));
            }
        }

        return result;
    }
}


//Approach-2 (Using workaround)
//T.C : O(1)
//S.C : O(1)
public class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> allPossible = List.of(12, 23, 34, 45, 56, 67, 78, 89,
                                           123, 234, 345, 456, 567, 678, 789,
                                           1234, 2345, 3456, 4567, 5678, 6789,
                                           12345, 23456, 34567, 45678, 56789,
                                           123456, 234567, 345678, 456789,
                                           1234567, 2345678, 3456789,
                                           12345678, 23456789,
                                           123456789);

        List<Integer> result = new ArrayList<>();

        int n = allPossible.size();

        for (int i = 0; i < n; i++) {
            if (allPossible.get(i) < low) continue;

            if (allPossible.get(i) > high) break;

            result.add(allPossible.get(i));
        }
        return result;
    }
}
