/**
 * PROBLEM STATEMENT: 3633. Earliest Finish Time for Land and Water Rides I
 * --------------------------------------------------------------------------------
 * You are given two categories of attractions: Land Rides and Water Rides.
 * - Each ride has an 'startTime' (earliest opening time) and a 'duration'.
 * - You must take exactly one Land Ride and one Water Ride, in any order.
 * - A ride can start at startTime or later. It finishes at (start + duration).
 * - After finishing one ride, you can start the next one immediately or wait 
 * until it opens.
 * * Goal: Return the earliest possible time to finish both rides.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BRUTE FORCE
 * --------------------------------------------------------------------------------
 * Since constraints are small (n, m <= 100), we can explore all possible pairs
 * of (Land Ride, Water Ride) and both possible orders (Land->Water or Water->Land).
 * * Logic for a pair (Land L, Water W):
 * 1. Order (L -> W): 
 * - Start L at L.startTime, Finish at L.finish = L.start + L.duration.
 * - Start W at max(W.startTime, L.finish), Finish at W.finish = W.start + W.duration.
 * 2. Order (W -> L):
 * - Start W at W.startTime, Finish at W.finish = W.start + W.duration.
 * - Start L at max(L.startTime, W.finish), Finish at L.finish = L.start + L.duration.
 * * We calculate the finish time for all permutations and return the minimum.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n * m), where n is the number of land rides and m is the number 
 * of water rides. With n, m <= 100, O(n*m) is 10,000, which is very efficient.
 * * Space Complexity: O(1) auxiliary space.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, 
                                  int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minFinishTime = Integer.MAX_VALUE;

        // Iterate through every pair of (Land Ride i, Water Ride j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                // Option 1: Land Ride first, then Water Ride
                int landFinish = landStartTime[i] + landDuration[i];
                int waterFinish = Math.max(landFinish, waterStartTime[j]) + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, waterFinish);

                // Option 2: Water Ride first, then Land Ride
                int waterFinish2 = waterStartTime[j] + waterDuration[j];
                int landFinish2 = Math.max(waterFinish2, landStartTime[i]) + landDuration[i];
                minFinishTime = Math.min(minFinishTime, landFinish2);
            }
        }

        return minFinishTime;
    }
}
