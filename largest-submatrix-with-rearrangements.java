/**
 * PROBLEM STATEMENT: 1727. Largest Submatrix With Rearrangements
 * --------------------------------------------------------------------------------
 * You are given a binary matrix 'matrix' of size m x n. You are allowed to 
 * rearrange the columns of the matrix in any order.
 * * Task: Return the area of the largest submatrix within 'matrix' where every 
 * element is 1 after reordering the columns optimally.
 * * Example:
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: 
 * - Row 0: heights are [0, 0, 1]
 * - Row 1: heights are [1, 1, 2] -> sorted: [1, 1, 2] -> areas: 1*3=3, 1*2=2, 2*1=2
 * - Row 2: heights are [2, 0, 3] -> sorted: [0, 2, 3] -> areas: 0*3=0, 2*2=4, 3*1=3
 * Max Area = 4.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: HISTOGRAM-BASED GREEDY APPROACH
 * --------------------------------------------------------------------------------
 * 1. Height Preprocessing:
 * For each cell (i, j), calculate the height of consecutive 1s ending at that cell.
 * - If matrix[i][j] == 0, height[i][j] = 0.
 * - If matrix[i][j] == 1, height[i][j] = height[i-1][j] + 1.
 * This effectively treats each row as the base of a histogram where bars can be 
 * moved horizontally (since we can rearrange columns).
 * * 2. Greedy Rearrangement:
 * For each row i:
 * - We have a set of heights (heights of consecutive 1s ending at row i).
 * - To maximize the area, we want the tallest bars to be adjacent.
 * - By sorting the heights of the current row in descending order (or ascending 
 * and iterating backwards), we can calculate the area for every possible width.
 * - Area = height * width, where width is the number of columns with height >= 'h'.
 * * 3. Global Maximum:
 * Keep track of the maximum area found across all rows.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N log N)
 * - Preprocessing heights: O(M * N).
 * - For each of the M rows, we sort N column heights: O(M * N log N).
 * - Total: O(M * N log N).
 * * Space Complexity: O(M * N)
 * - We store a 'height' matrix of size M x N. This can be optimized to O(N) 
 * if we only store the current and previous row's heights.
 * --------------------------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] height = new int[m][n];
        
        for (int j = 0; j < n; j++) {
            height[0][j] = matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    height[i][j] = height[i - 1][j] + 1;
                }
                 else {
                    height[i][j] = 0;
                }
            }
        }
        int maxArea = 0;
        
        for (int i = 0; i < m; i++) {
            int[] row = height[i].clone();
            Arrays.sort(row);
            
            for (int j = 0; j < n; j++) {
                int h = row[j];
                int width = n - j;
                maxArea = Math.max(maxArea, h * width);
            }
        }
         return maxArea;
    }
}

