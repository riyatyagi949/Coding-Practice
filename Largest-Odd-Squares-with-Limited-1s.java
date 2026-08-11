/*
 * ==========================================
 * PROBLEM STATEMENT: Largest Odd Squares with Limited 1s
 * ==========================================
 * Given a binary matrix mat[][] of size n * m and an integer k, process a list of queries queries[][]. 
 * Each query contains coordinates [i, j] of the center of a square.
 * 
 * For every query, find the side length of the largest odd-sized square centered at cell (i, j) 
 * such that the square contains at most k ones.
 * 
 * A square centered at (i, j) expands outward symmetrically in all four directions by the same 
 * number of cells, so its side length is always odd.
 * 
 * Note: If no odd-sized square centered at the given cell satisfies the condition of containing 
 * at most k ones, return -1 for that query.
 * 
 * Constraints:
 * 1 <= mat.size(), mat[0].size() <= 500
 * 1 <= queries.size() <= 10^4
 * 0 <= queries[q][0] < mat.size()
 * 0 <= queries[q][1] < mat[0].size()
 * 0 <= k <= mat.size() * mat[0].size()
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (2D Prefix Sum + Binary Search)
 * ==========================================
 * - Approach:
 *   1. 2D Prefix Sum: Precompute a prefix sum matrix to find the sum of any submatrix in O(1) time.
 *      prefix[i][j] stores the sum of elements from (0,0) to (i-1,j-1).
 *   2. Binary Search on Radius: For each query centered at (r, c), the maximum possible radius (offset from center) 
 *      is bounded by the matrix boundaries: min(r, n - 1 - r, c, m - 1 - c).
 *      We use binary search on the radius `mid` where the side length of the square is (2 * mid + 1).
 *   3. Range Sum Query: For a chosen radius `mid`, the submatrix boundaries are:
 *      - top = r - mid
 *      - bottom = r + mid
 *      - left = c - mid
 *      - right = c + mid
 *      We check if the number of 1s in this region is <= k. If yes, we try a larger radius; otherwise, a smaller one.
 * 
 * - Complexity:
 *   - Time Complexity: O(n * m + Q log(min(n, m))), where n*m is for building the prefix sum and Q is the number of queries.
 *   - Space Complexity: O(n * m) for storing the 2D prefix sum array.
 */

import java.util.ArrayList;

class Solution {
    ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Build the 2D prefix sum matrix
        int[][] prefix = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prefix[i + 1][j + 1] = mat[i][j] + prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j];
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        // Step 2: Process each query using binary search on the radius
        for (int[] query : queries) {
            int r = query[0];
            int c = query[1];

            // Maximum possible radius before hitting matrix boundaries
            int maxRadius = Math.min(Math.min(r, n - 1 - r), Math.min(c, m - 1 - c));

            int low = 0;
            int high = maxRadius;
            int best = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                int top = r - mid;
                int bottom = r + mid;
                int left = c - mid;
                int right = c + mid;

                // Get the number of 1s in the current square using the prefix sum array
                int ones = getSum(prefix, top, left, bottom, right);

                if (ones <= k) {
                    best = mid;     // Valid square found, try expanding to a larger radius
                    low = mid + 1;
                } 
                else {
                    high = mid - 1; // Too many 1s, try a smaller radius
                }
            }

            // Convert the best radius to side length: side = 2 * radius + 1
            ans.add(best == -1 ? -1 : 2 * best + 1);
        }

        return ans;
    }

    // Helper method to retrieve the sum of elements in a submatrix in O(1) time
    private int getSum(int[][] prefix, int top, int left, int bottom, int right) {
        return prefix[bottom + 1][right + 1] 
             - prefix[top][right + 1] 
             - prefix[bottom + 1][left] 
             + prefix[top][left];
    }
}
