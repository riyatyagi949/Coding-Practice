/**
 * PROBLEM STATEMENT: 3635. Earliest Finish Time for Land and Water Rides II
 * --------------------------------------------------------------------------------
 * Similar to Version I, you must take exactly one Land Ride and one Water Ride.
 * The constraints have increased to N, M <= 5 * 10^4.
 * Goal: Minimize the time to finish both rides.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Greedy with Sorting
 * --------------------------------------------------------------------------------
 * To minimize the finish time for an order (Category A -> Category B):
 * 1. To finish Category A as early as possible, we only care about the ride 
 * that has the minimum finish time (startTime + duration).
 * 2. Let minFinishA = min(startTime_A[i] + duration_A[i]) for all i.
 * 3. Once we finish the best ride in Category A at time minFinishA, we can 
 * start any ride in Category B at max(minFinishA, startTime_B[j]).
 * 4. We iterate through all rides in B and calculate their finish time 
 * (max(minFinishA, startTime_B[j]) + duration_B[j]), picking the minimum.
 * 5. We calculate this for both orders (Land -> Water) and (Water -> Land) 
 * and return the overall minimum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N + M)
 * - We iterate through the arrays once to find the minimum finish time for 
 * Category A, and once through Category B.
 * Space Complexity: O(1)
 * - We only store a few variables for the minimum finish times.
 * --------------------------------------------------------------------------------
 */
class Solution {
      public int earliestFinishTime(
        int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {

        int landThenWater = calculateFinishTime(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterThenLand = calculateFinishTime(waterStartTime, waterDuration, landStartTime, landDuration);

        return Math.min(landThenWater, waterThenLand);
    }

    private int calculateFinishTime(int[] firstStartTimes, int[] firstDurations,
                                   int[] secondStartTimes, int[] secondDurations) {

        int earliestFirstCategoryEnd = Integer.MAX_VALUE;
        for (int i = 0; i < firstStartTimes.length; i++) {
            int taskEndTime = firstStartTimes[i] + firstDurations[i];
            earliestFirstCategoryEnd = Math.min(earliestFirstCategoryEnd, taskEndTime);
        }

        int minimumTotalTime = Integer.MAX_VALUE;
        for (int i = 0; i < secondStartTimes.length; i++) {
            int secondTaskStart = Math.max(earliestFirstCategoryEnd, secondStartTimes[i]);
            int totalTime = secondTaskStart + secondDurations[i];
            minimumTotalTime = Math.min(minimumTotalTime, totalTime);
        }

        return minimumTotalTime;
    }
}
