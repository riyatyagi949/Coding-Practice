/*
 * PROBLEM STATEMENT:
 * You are given a binary string 's' of length 'n', where '1' represents an active section 
 * and '0' represents an inactive section. You can perform at most one trade to maximize 
 * the number of active sections: convert a contiguous block of '1's surrounded by '0's to '0's, 
 * and then convert a contiguous block of '0's surrounded by '1's to '1's.
 * 
 * Additionally, you are given a 2D array 'queries', where each query = [li, ri] specifies a substring s[li...ri].
 * For each query, determine the maximum possible number of active sections after making the optimal 
 * trade restricted to the substring s[li...ri] (augmented with a '1' at both ends).
 * Return an array/list of integers where each element is the result for the corresponding query.
 * 
 * OPTIMAL SOLUTION APPROACH:
 * 1. Count the global active count of '1's in 's'. (Note: The actual trade gain relies on 
 *    the inactive blocks within the query range, and the active count can be derived or adjusted).
 * 2. Identify all contiguous blocks of '0's, noting their start and end indices.
 * 3. Each pair of adjacent zero blocks forms a potential gain sum (blockSize[k] + blockSize[k+1]).
 * 4. For a query [l, r], we find the range of zero blocks that fall within or overlap with [l, r].
 * 5. We use a Segment Tree over the adjacent block pair sums to efficiently query the maximum 
 *    pair sum in range in O(log M) time, while handling boundary partial blocks appropriately.
 * 
 * Time Complexity: O(N + Q log M), where N is string length, Q is number of queries, and M is number of zero blocks.
 * Space Complexity: O(N + M) for storing blocks, pair sums, and the Segment Tree.
 */

import java.util.*;

class Solution {
    static void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] arr) {
        if (l == r) {
            segmentTree[i] = arr[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, segmentTree, arr);
        buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, arr);
        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
    }

    static int[] constructST(int[] arr, int n) {
        int[] segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, segmentTree, arr);
        return segmentTree;
    }

    static int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree) {
        if (l > end || r < start) {
            return Integer.MIN_VALUE;
        }
         if (l >= start && r <= end) {
            return segmentTree[i];
        }

        int mid = l + (r - l) / 2;
        return Math.max(querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree),
                        querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree));
    }

    static int RMQ(int[] st, int n, int a, int b) {
        return querySegmentTree(a, b, 0, 0, n - 1, st);
    }

    static int lowerBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    static int upperBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int activeCount = 0;
        for (int idx = 0; idx < n; idx++) {
            if (s.charAt(idx) == '1') activeCount++;
        }

        int[] blockStart = new int[n];
        int[] blockEnd = new int[n];

        int m = 0;
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') i++;
                blockStart[m] = start;
                blockEnd[m] = i - 1;
                m++;
            }
             else {
                i++;
            }
        }

        if (m < 2) {
            List<Integer> res = new ArrayList<>();
            for (int k = 0; k < queries.length; k++) res.add(activeCount);
            return res;
        }

        int[] blockSize = new int[m];
        for (int k = 0; k < m; k++) {
            blockSize[k] = blockEnd[k] - blockStart[k] + 1;
        }

        int N = m - 1; 
        int[] pairSum = new int[N];
        for (int k = 0; k < N; k++) {
            pairSum[k] = blockSize[k] + blockSize[k + 1];
        }

        int[] st = constructST(pairSum, N);

        List<Integer> result = new ArrayList<>();
        for (int[] q : queries) {            
            int l = q[0];
            int r = q[1];

            int low  = lowerBound(blockEnd, m, l);        
            int high = upperBound(blockStart, m, r) - 1;  

            int maxPairSum = 0;
            if (low < high) {                 
                int firstLen = blockEnd[low] - Math.max(blockStart[low], l) + 1;
                int lastLen  = Math.min(blockEnd[high], r) - blockStart[high] + 1;

                if (high - low == 1) {         
                    maxPairSum = firstLen + lastLen;
                }
                 else {
                    int pair1 = firstLen + blockSize[low + 1];
                    int pair2 = blockSize[high - 1] + lastLen;
                    int rmqMaxPairSum = (low + 1 <= high - 2) ? RMQ(st, N, low + 1, high - 2) : 0; 
                    maxPairSum = Math.max(pair1, Math.max(pair2, rmqMaxPairSum));
                }
            }
            result.add(maxPairSum + activeCount);
        }
         return result;
    }
}
