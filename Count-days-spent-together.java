/*
 * ==========================================
 * PROBLEM STATEMENT: Count Days Spent Together
 * ==========================================
 * Alice and Bob are traveling to Rome for separate business meetings. You are given 4 strings: 
 * `arriveAlice`, `leaveAlice`, `arriveBob`, and `leaveBob` representing their arrival and departure dates 
 * in "MM-DD" format. Return the total number of days that Alice and Bob are in Rome together.
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Day-of-Year Conversion)
 * ==========================================
 * - Approach:
 *   1. Convert each date string into an absolute day-of-the-year integer using a precomputed array 
 *      of days in each month for a non-leap year.
 *   2. The overlapping period starts at the later arrival date: `max(arriveAlice, arriveBob)`.
 *   3. The overlapping period ends at the earlier departure date: `min(leaveAlice, leaveBob)`.
 *   4. Calculate the overlap length as `end_day - start_day + 1`. If the result is negative, 
 *      it means there is no overlap, so we return 0.
 * 
 * - Complexity:
 *   - Time Complexity: O(1) since the year has a fixed 12 months.
 *   - Space Complexity: O(1) auxiliary space.
 */

class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(toDays(arriveAlice), toDays(arriveBob));
        int end = Math.min(toDays(leaveAlice), toDays(leaveBob));

        return Math.max(0, end - start + 1);
    }
    private int toDays(String date) {int[] days = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += days[i];
        }
        return day;
    }
}
