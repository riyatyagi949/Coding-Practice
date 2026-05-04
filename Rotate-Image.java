/**
 * PROBLEM STATEMENT: 48. Rotate Image
 * --------------------------------------------------------------------------------
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Requirement:
 * - You must rotate the image in-place.
 * - Do NOT allocate another 2D matrix for the rotation.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: TRANSPOSE AND REVERSE
 * --------------------------------------------------------------------------------
 * A 90-degree clockwise rotation can be achieved through two linear transformations:
 * 
 * 1. Transpose: Swap elements across the main diagonal (top-left to bottom-right).
 *    This converts rows into columns. (matrix[i][j] <-> matrix[j][i])
 * 
 * 2. Reverse Rows: Reverse the order of elements in each individual row.
 *    This flips the columns into the correct clockwise position.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n^2)
 * - Transposing takes O(n^2) as we visit half the matrix elements.
 * - Reversing rows takes O(n^2) as we visit all elements.
 * - Total time is quadratic relative to the matrix side length.
 * 
 * Space Complexity: O(1)
 * - All modifications are performed in-place using a single temporary variable.
 * --------------------------------------------------------------------------------
 */
// Code ----------------

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int temp = matrix[i][j];

                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i = 0; i < n; i++){
            int left = 0, right = n - 1;
            
            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}

