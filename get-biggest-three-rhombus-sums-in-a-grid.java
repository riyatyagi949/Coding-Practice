/**
 * PROBLEM STATEMENT: 1878. Get Biggest Three Rhombus Sums in a Grid
 * --------------------------------------------------------------------------------
 * You are given an m x n integer matrix grid​​​.
 * A rhombus sum is the sum of the elements that form the border of a regular 
 * rhombus shape. The rhombus must have the shape of a square rotated 45 degrees 
 * with each of its corners centered in a grid cell.
 * * - The area can be 0 (a single cell).
 * - Return the biggest three distinct rhombus sums in descending order.
 * - If there are fewer than three distinct values, return all of them.
 * * Constraints:
 * - m == grid.length, n == grid[i].length
 * - 1 <= m, n <= 50
 * - 1 <= grid[i][j] <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: EXHAUSTIVE SEARCH WITH BOUNDARY TRAVERSAL
 * --------------------------------------------------------------------------------
 * 1. Geometry of a Rhombus:
 * A rhombus centered at (i, j) with "radius" d has four vertices:
 * Top: (i-d, j), Bottom: (i+d, j), Left: (i, j-d), Right: (i, j+d).
 * * 2. Iteration Strategy:
 * - For every cell (i, j) in the grid, it acts as a potential center.
 * - First, add the single cell (d=0) to our collection of sums.
 * - Then, expand outwards with d = 1, 2, ... as long as all four vertices 
 * stay within grid boundaries.
 * * 3. Sum Calculation:
 * - Traverse the four edges of the rhombus:
 * Top to Right, Right to Bottom, Bottom to Left, Left to Top.
 * * 4. Tracking Results:
 * - Use a TreeSet with a Reverse Order comparator to automatically store 
 * distinct sums and keep them sorted in descending order.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N * min(M, N)^2)
 * - M*N centers.
 * - For each center, we check up to min(M, N)/2 radii.
 * - For each radius d, we traverse 4*d cells.
 * - Given M, N <= 50, the total operations are roughly 50 * 50 * 25 * 25 ≈ 1.5 million,
 * well within the 1-second time limit.
 * * Space Complexity: O(M * N)
 * - To store distinct sums in the TreeSet. In the worst case, every possible 
 * rhombus could have a unique sum.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java- 

import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                 set.add(grid[i][j]);

        for(int d=1; d<50; d++){
                    if(i-d<0 || i+d>=m || j-d<0 || j+d>=n)
                     break;

                    int sum = 0;

                    int r=i-d, c=j;
                    while(r<i && c<j+d){
                        sum += grid[r][c];
                        r++; c++;
                    }
                    while(r<i+d && c>j) {
                        sum += grid[r][c];
                        r++; c--;
                    }
                    while(r>i && c>j-d) {
                        sum += grid[r][c];
                        r--; c--;
                    }
                     while(r>i-d && c<j) {
                        sum += grid[r][c];
                        r--; c++;
                    }
                     set.add(sum);
                }
            }
        }
        int size = Math.min(3, set.size());
        int[] ans = new int[size];
        int idx = 0;

        for(int val : set){
            if(idx == 3) 
            break;
            ans[idx++] = val;
        }
        return ans;
    }
}

