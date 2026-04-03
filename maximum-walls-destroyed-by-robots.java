/**
 * Problem: 3661. Maximum Walls Destroyed by Robots
 * --------------------------------------------------------------------------------
 * Optimal Solution: Dynamic Programming with Binary Search
 * * 1. Sorting: We sort both robots and walls to enable efficient range queries.
 * 2. Interval Ownership: 
 * Each robot i can fire Left into the gap [P_{i-1}, P_i] or Right into [P_i, P_{i+1}].
 * The walls in a gap [P_i, P_{i+1}] can be destroyed by:
 * - Robot i firing Right.
 * - Robot i+1 firing Left.
 * - Both (counting unique walls in their union).
 * 3. DP State:
 * dp[i][0]: Max walls destroyed using robots 0...i, where robot i fires Left.
 * dp[i][1]: Max walls destroyed using robots 0...i, where robot i fires Right.
 */

// Code -------------------------------

import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        
        // Pair robots with their distances and sort by position
        int[][] roboDist = new int[n][2];
        for (int i = 0; i < n; i++) {
            roboDist[i][0] = robots[i];
            roboDist[i][1] = distance[i];
        }
        Arrays.sort(roboDist, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Sort walls for binary search range counting
        Arrays.sort(walls);
        
        // dp[i][0] -> Robot i fired Left
        // dp[i][1] -> Robot i fired Right
        long[][] dp = new long[n][2];
        
        // Initial state for the first robot
        // Firing Left: Covers [P0 - d0, P0] limited by nothing to the left.
        dp[0][0] = countWalls(walls, roboDist[0][0] - roboDist[0][1], roboDist[0][0]);
        // Firing Right: Only the wall at its own position is "finished" for now.
        // The gap [P0, P1] will be resolved by robot 1.
        dp[0][1] = countWalls(walls, roboDist[0][0], roboDist[0][0]);
        
        for (int i = 1; i < n; i++) {
            int currPos = roboDist[i][0];
            int currDist = roboDist[i][1];
            int prevPos = roboDist[i - 1][0];
            int prevDist = roboDist[i - 1][1];
            
            // --- Case 1: Robot i fires LEFT ---
            // If prev fired Left: i adds walls in (prevPos, currPos] within its range.
            long fromPrevLeft = dp[i - 1][0] + countWalls(walls, Math.max(currPos - currDist, prevPos + 1), currPos);
            
            // If prev fired Right: The gap [prevPos, currPos] is covered by both.
            // Together they cover the union of [prevPos, prevPos + prevDist] and [currPos - currDist, currPos].
            // Because they "meet" in the middle, they effectively cover the entire interval [prevPos, currPos].
            // Since dp[i-1][1] already counted the wall at prevPos, we add (prevPos, currPos].
            long fromPrevRight = dp[i - 1][1] + countWalls(walls, prevPos + 1, currPos);
            
            dp[i][0] = Math.max(fromPrevLeft, fromPrevRight);
            
            // --- Case 2: Robot i fires RIGHT ---
            // If prev fired Left: Gap [prevPos, currPos] is empty. Only currPos wall is destroyed.
            long fromPrevLeftR = dp[i - 1][0] + countWalls(walls, currPos, currPos);
            
            // If prev fired Right: Prev robot covers part of the gap [prevPos, currPos].
            long fromPrevRightR = dp[i - 1][1] + countWalls(walls, prevPos + 1, Math.min(prevPos + prevDist, currPos));
            
            dp[i][1] = Math.max(fromPrevLeftR, fromPrevRightR);
        }
        
        // Final Step: Robot n-1 fires Right or Left.
        // If it fires Right, it can hit walls beyond its own position.
        long finalLeft = dp[n - 1][0];
        long finalRight = dp[n - 1][1] + countWalls(walls, roboDist[n - 1][0] + 1, roboDist[n - 1][0] + roboDist[n - 1][1]);
        
        return (int) Math.max(finalLeft, finalRight);
    }
    // Efficiently counts walls in range [L, R] using binary search
    private int countWalls(int[] walls, int L, int R) {
        if (L > R) return 0;
        int start = lowerBound(walls, L);
        int end = upperBound(walls, R);
        return end - start;
    }
    
    private int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
     private int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}

/**
 * Complexity Analysis:
 * -------------------
 * Time Complexity: O((N + M) log M + N log N)
 * - Sorting Robots: O(N log N)
 * - Sorting Walls: O(M log M)
 * - DP: N iterations, each performing constant number of Binary Searches (log M).
 * * Space Complexity: O(N + M)
 * - Storing sorted robots/distances: O(N).
 * - DP table: O(N).
 */
