/**
 * PROBLEM STATEMENT: 2033. Minimum Operations to Make a Uni-Value Grid
 * --------------------------------------------------------------------------------
 * You are given a 2D integer grid of size m x n and an integer x. 
 * In one operation, you can add x to or subtract x from any element in the grid.
 * A uni-value grid is a grid where all elements are equal.
 * Return the minimum operations to make the grid uni-value. If impossible, return -1.
 
 * * OPTIMAL SOLUTION: Median-Based Approach
 * --------------------------------------------------------------------------------
 * 1. Modulo Check: For any two numbers to become equal by adding/subtracting 'x', 
 * they MUST have the same remainder when divided by 'x'. If they don't, 
 * it's impossible to make them equal.
 * * 2. Flatten and Sort: Convert the 2D grid into a 1D array to easily find the median.
 * * 3. Why the Median? The sum of absolute differences |a_i - target| is minimized 
 * when 'target' is the median. Using the average (mean) would minimize the 
 * squared differences, but here we need absolute steps.
 * * 4. Calculate Operations: The number of operations for each element is: 
 * Math.abs(element - median) / x.
 
 * * TIME COMPLEXITY: O(N log N) 
 * - Where N = m * n (total elements). 
 * - Sorting the flattened array takes O(N log N).
 * - Modulo check and final summation take O(N).
 
 * * SPACE COMPLEXITY: O(N)
 * - To store the flattened grid elements in a 1D array/list.
 * --------------------------------------------------------------------------------
 */
// Code ------------------------

import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        int mod = list.get(0) % x;
        
        for (int num : list) {
            if (num % x != mod)
            return -1;
        }
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        
        int operations = 0;
        
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }
        return operations;
    }
}

import java.util.Arrays;

