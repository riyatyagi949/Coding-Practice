/**
 * PROBLEM STATEMENT: 1306. Jump Game III
 * --------------------------------------------------------------------------------
 * Given an array of non-negative integers 'arr' and a 'start' index, you are 
 * initially positioned at arr[start]. When you are at index i, you can jump 
 * to i + arr[i] or i - arr[i].
 * * Determine if you can reach any index with a value of 0.
 * * Notice that you cannot jump outside the bounds of the array at any time.
 * * Constraints:
 * - 1 <= arr.length <= 5 * 10^4
 * - 0 <= arr[i] < arr.length
 * - 0 <= start < arr.length
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Depth-First Search (DFS) / Breadth-First Search (BFS)
 * --------------------------------------------------------------------------------
 * 1. Graph Traversal:
 * We can treat this problem as finding a path in a directed graph where each 
 * index i has directed edges to (i + arr[i]) and (i - arr[i]).
 * * 2. In-Place Visited Tracking:
 * To avoid cycles (infinite jumping between the same indices) and optimize memory, 
 * we can mark an index as visited by flipping its value to negative (e.g., arr[i] = -arr[i]). 
 * Since all initial values are non-negative, any negative value indicates a 
 * previously visited node.
 * * 3. Search:
 * From the current index, we check if the value is 0. If it is, we return true.
 * Otherwise, we recursively explore both valid left and right jumps.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - Each index of the array is visited at most once due to the visited tracking mechanism.
 * Space Complexity: O(N)
 * - The space complexity is determined by the recursion call stack during DFS, 
 * which can go up to N elements deep in the worst-case scenario. No extra space 
 * is used for a visited array.
 * --------------------------------------------------------------------------------
 */

class Solution {
    int n;
    private boolean dfs(int[] arr, int i) {
        if(i < 0 || i >= n || arr[i] < 0) {
            return false;
        }
        if(arr[i] == 0)
            return true;

        arr[i] *= -1;
        boolean left  = dfs(arr, i - arr[i]);
        boolean right = dfs(arr, i + arr[i]);
        return left || right;
    }
    public boolean canReach(int[] arr, int start) {
        n = arr.length;
        return dfs(arr, start);
    }
}
