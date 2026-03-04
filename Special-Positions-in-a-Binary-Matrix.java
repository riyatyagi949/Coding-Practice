/**
 * PROBLEM STATEMENT: 1582. Special Positions in a Binary Matrix
 * --------------------------------------------------------------------------------
 * Given an m x n binary matrix 'mat', return the number of special positions.
 * A position (i, j) is called "special" if:
 * 1. mat[i][j] == 1
 * 2. All other elements in row 'i' are 0.
 * 3. All other elements in column 'j' are 0.
 * * Example 1:
 * Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
 * Output: 1
 * Explanation: (1, 2) is special because mat[1][2] == 1 and its row/column are otherwise 0.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: PRE-COMPUTED FREQUENCIES (Two-Pass)
 * --------------------------------------------------------------------------------
 * A naive approach would be to check every '1' found and iterate through its 
 * entire row and column, resulting in O(M*N * (M+N)) complexity.
 * * The optimal approach uses O(M + N) extra space to pre-calculate the count of 
 * ones in every row and every column:
 * 1. Pass 1: Iterate through the entire matrix. For every mat[i][j] == 1, 
 * increment rowCount[i] and colCount[j].
 * 2. Pass 2: Iterate through the matrix again. A position (i, j) is special 
 * ONLY IF:
 * - mat[i][j] == 1
 * - rowCount[i] == 1 (meaning no other 1s in this row)
 * - colCount[j] == 1 (meaning no other 1s in this column)
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - We traverse the matrix twice (once to count, once to verify).
 * - M is the number of rows, N is the number of columns.
 * * Space Complexity: O(M + N)
 * - We store two auxiliary arrays: rowCount of size M and colCount of size N.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        int count = 0;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {
                    count++;
                }
            }
        }
          return count;
    }
}

