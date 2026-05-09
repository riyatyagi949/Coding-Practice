/**
 * PROBLEM STATEMENT: 1914. Cyclically Rotating a Grid
 * --------------------------------------------------------------------------------
 * You are given an m x n integer matrix 'grid', where m and n are both even, and 
 * an integer k. The matrix is composed of several layers.
 * * A cyclic rotation involves moving each element in a layer to the place of 
 * its adjacent element in the counter-clockwise direction.
 * * Return the matrix after applying k cyclic rotations.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LAYER-BY-LAYER EXTRACTION AND ROTATION
 * --------------------------------------------------------------------------------
 * 1. Layers: The number of layers in an m x n matrix is min(m, n) / 2.
 * 2. Extraction: For each layer, we traverse its elements in a fixed order (e.g., 
 * Clockwise) and store them in a linear list. 
 * 3. Modulo Optimization: Since k can be very large (up to 10^9), we calculate 
 * the effective rotation using k % layerSize. This prevents redundant rotations.
 * 4. Index Mapping: 
 * - If we extract elements in Clockwise (CW) order: [top, right, bottom, left].
 * - Rotating a layer Counter-Clockwise (CCW) by 1 is equivalent to shifting the 
 * CW list elements left (the element at index i moves to index i-1).
 * - Alternatively, to find the NEW value for a position at index 'i' in our 
 * CW list, we look at the element originally at index (i + k) % size.
 * 5. Reinsertion: Fill the matrix back using the rotated values.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M * N)
 * - Each element in the grid is visited exactly twice: once to extract into a 
 * list and once to put back.
 * Space Complexity: O(M + N)
 * - The list used to store elements of a single layer has at most 2(M+N) elements.
 * --------------------------------------------------------------------------------
 */
// ### Key Logic Highlights -

// 1.  Layer Boundaries: We use `layer` as the offset for the top-left corner and calculate the bottom-right corner as `(m - 1 - layer, n - 1 - layer)`.
// 2.  Clockwise Order: By extracting elements in Clockwise order, a Counter-Clockwise rotation of the grid manifests as a "forward" offset in our list. If you rotate CCW, the element at index $1$ moves to index $0$, which is the same as saying the new value at index $0$ is the old value at index $1$.
// 3.  Space Efficiency: Instead of creating a new rotated list, we directly access the `(i + k) % size` element from the original list during the reinsertion phase.


// Code --------------

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            ArrayList<Integer> list = new ArrayList<>();

            int top = layer;
            int left = layer;
            int bottom = m - layer - 1;
            int right = n - layer - 1;

            for (int j = left; j <= right; j++) {
                list.add(grid[top][j]);
            }

            for (int i = top + 1; i <= bottom - 1; i++) {
                list.add(grid[i][right]);
            }

            for (int j = right; j >= left; j--) {
                list.add(grid[bottom][j]);
            }

            for (int i = bottom - 1; i >= top + 1; i--) {
                list.add(grid[i][left]);
            }

            int size = list.size();
            int rotate = k % size;

            ArrayList<Integer> rotated = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                rotated.add(list.get((i + rotate) % size));
            }

            int idx = 0;

            for (int j = left; j <= right; j++) {
                grid[top][j] = rotated.get(idx++);
            }

            for (int i = top + 1; i <= bottom - 1; i++) {
                grid[i][right] = rotated.get(idx++);
            }

            for (int j = right; j >= left; j--) {
                grid[bottom][j] = rotated.get(idx++);
            }

            for (int i = bottom - 1; i >= top + 1; i--) {
                grid[i][left] = rotated.get(idx++);
            }
        }

        return grid;
    }
}
