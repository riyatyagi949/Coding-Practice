/**
 * PROBLEM STATEMENT: 1840. Maximum Building Height
 * --------------------------------------------------------------------------------
 * You want to build n buildings labeled 1 to n. You have certain restrictions 
 * (restrictions[i] = [id_i, maxHeight_i]) meaning the building at index id_i 
 * cannot have a height greater than maxHeight_i.
 * * Rules:
 * 1. Building 1 has a maximum height of 0.
 * 2. The height difference between adjacent buildings is at most 1.
 * 3. Return the maximum possible height of the tallest building.
 *
 * * Example:
 * Input: n = 5, restrictions = [[2,1],[4,1]]
 * Output: 2
 * Explanation: Heights [0, 1, 2, 1, 2] is a valid configuration.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Pass Greedy approach
 * --------------------------------------------------------------------------------
 * 1. Include building 1 (height 0) in the restrictions list and sort by index.
 * 2. Forward Pass: Ensure the restriction at building `i` respects the 
 * restriction at building `i-1` plus the distance between them:
 * `height[i] = min(height[i], height[i-1] + (id[i] - id[i-1]))`
 * 3. Backward Pass: Do the same from right to left:
 * `height[i] = min(height[i], height[i+1] + (id[i+1] - id[i]))`
 * 4. Final Calculation: Between any two adjacent restricted buildings `i` and `j`,
 * the maximum possible height is reached by climbing up from both ends:
 * `h_i + h_j + (dist_ij) / 2`.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(R log R), where R is the number of restrictions, 
 * due to the initial sorting of restrictions. The passes are O(R).
 * Space Complexity: O(R), to store the modified restrictions list.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;

        int[][] arr = new int[m + 2][2];

        arr[0] = new int[]{1, 0};

        for (int i = 0; i < m; i++) {
            arr[i + 1] = restrictions[i];
        }

        arr[m + 1] = new int[]{n, n - 1};

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        for (int i = 1; i < arr.length; i++) {
            int dist = arr[i][0] - arr[i - 1][0];

            arr[i][1] = Math.min(
                arr[i][1],
                arr[i - 1][1] + dist
            );
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            int dist = arr[i + 1][0] - arr[i][0];

            arr[i][1] = Math.min(
                arr[i][1],
                arr[i + 1][1] + dist
            );
        }

        long ans = 0;

        for (int i = 1; i < arr.length; i++) {

            long h1 = arr[i - 1][1];
            long h2 = arr[i][1];

            long d = arr[i][0] - arr[i - 1][0];

            ans = Math.max(ans, (h1 + h2 + d) / 2 );
        }
         return (int) ans;
    }
}

