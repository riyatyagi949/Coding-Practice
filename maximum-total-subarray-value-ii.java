/**
 * PROBLEM STATEMENT: 3691. Maximum Total Subarray Value II
 * --------------------------------------------------------------------------------
 * Choose exactly k DISTINCT subarrays nums[l..r] to maximize the total value
 * (sum of max - min for each).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Segment Tree + Priority Queue
 * --------------------------------------------------------------------------------
 * 1. Precompute Min/Max queries using Segment Trees for O(log n) access.
 * 2. Use a Priority Queue to store the best value for every possible starting 
 * position 'l'.
 * 3. In each of the k steps, pick the best value from the PQ, add to total, 
 * and replace it with the next best value by narrowing the subarray range 
 * (e.g., shrinking the right bound 'r').
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n log n + k log n)
 * - Building segment trees: O(n).
 * - Initial PQ fill: O(n log n).
 * - k extractions/insertions: O(k log n).
 * Space Complexity: O(n)
 * - O(n) to store the segment trees and PQ.
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class SegmentTree {
    int[] segmentTree;
    boolean isMinTree;

    SegmentTree(int[] nums, boolean flag) {
        int n = nums.length;
        this.isMinTree = flag;
        segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, nums);
    }

    void buildSegmentTree(int i, int l, int r, int[] nums) {
        if (l == r) {
            segmentTree[i] = nums[l];
            return;
        }

        int mid = l + (r - l) / 2;

        buildSegmentTree(2 * i + 1, l, mid, nums);
        buildSegmentTree(2 * i + 2, mid + 1, r, nums);

        if (isMinTree) {
            segmentTree[i] = Math.min(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        } 
        else {
            segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }
    }

    int querySegmentTree(int start, int end, int i, int l, int r) {
        if (l > end || r < start) {
            return isMinTree ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        if (l >= start && r <= end) {
            return segmentTree[i];
        }

        int mid = l + (r - l) / 2;

        int a = querySegmentTree(start, end, 2 * i + 1, l, mid);
        int b = querySegmentTree(start, end, 2 * i + 2, mid + 1, r);

        if (isMinTree) {
            return Math.min(a, b);
        }

        return Math.max(a, b);
    }

    int query(int l, int r, int n) {
        return querySegmentTree(l, r, 0, 0, n - 1);
    }
}

class Solution {
    long getValue(int l, int r, SegmentTree minST, SegmentTree maxST, int n) {
        int minEl = minST.query(l, r, n);
        int maxEl = maxST.query(l, r, n);
        return (long) maxEl - minEl;
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        SegmentTree minST = new SegmentTree(nums, true);   
        SegmentTree maxST = new SegmentTree(nums, false); 

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            long value = getValue(l, n - 1, minST, maxST, n); 
            pq.offer(new long[]{value, l, n - 1});
        }

        long result = 0;
        while (k-- > 0) {
            long[] top = pq.poll();
            long value = top[0];
            int l = (int) top[1];
            int r = (int) top[2];

            result += value;

            long nextBestValue = getValue(l, r - 1, minST, maxST, n); 
             pq.offer(new long[]{nextBestValue, l, r - 1}); 
        }
         return result;
    }
}


