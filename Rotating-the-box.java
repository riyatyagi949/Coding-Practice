/**
 * PROBLEM STATEMENT: 1861. Rotating the Box
 * --------------------------------------------------------------------------------
 * You are given an m x n matrix 'boxGrid' representing a side-view of a box.
 * Each cell contains:
 * - Stone '#'
 * - Stationary obstacle '*'
 * - Empty '.'
 * 
 * The box is rotated 90 degrees clockwise. Stones fall down until they hit an 
 * obstacle, another stone, or the bottom. Obstacles do not move.
 * 
 * Return the n x m matrix after rotation and gravity application.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Pointer Gravity + Matrix Rotation
 * --------------------------------------------------------------------------------
 * 1. Gravity Simulation (Horizontal):
 * Since the box rotates 90° clockwise, "down" in the final box corresponds to 
 * "right" in the original horizontal box.
 * - For each row, use a pointer 'lowestAvailable' starting at the rightmost column.
 * - Iterate from right to left:
 *   - If cell is '*': Obstacles are fixed. Reset 'lowestAvailable' to the left of it.
 *   - If cell is '#': Move the stone to 'lowestAvailable' and decrement 'lowestAvailable'.
 *   - If cell is '.': Do nothing, just keep looking for stones.
 * 
 * 2. 90° Clockwise Rotation:
 * - A cell at (i, j) in an m x n matrix moves to (j, m - 1 - i) in the n x m result.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - One pass to apply gravity to each row: O(M * N).
 * - One pass to rotate the matrix: O(M * N).
 * Space Complexity: O(M * N)
 * - We need to return a new matrix of size N x M.
 * - Extra space (excluding output): O(1).
 * --------------------------------------------------------------------------------
 */
### Key Logic Highlights:
1.  Gravity Pointer: By iterating backwards (from right to left) and keeping track of the `lowestAvailable` index, we can simulate gravity in a single pass per row. This avoids nested loops for searching for empty spots.
2.  Obstacle Handling: When an obstacle `*` is hit, it acts as a "new floor," so we immediately move our pointer to the position just before it.
3.  In-place Gravity: We modify the original `boxGrid` for gravity first to keep the logic clean, then map those values into the final `rotatedBox`.
  
// Optimal Solution in Java ------------------

  class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        for (int i = 0; i < m; i++) {
            int empty = n - 1;
             for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    empty = j - 1;
                } 
                else if (box[i][j] == '#') {
                    char temp = box[i][empty];
                    box[i][empty] = '#';
                    box[i][j] = temp;
                    empty--;
                }
            }
        }
        char[][] res = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][m - 1 - i] = box[i][j];
            }
        }

        return res;
    }
}
