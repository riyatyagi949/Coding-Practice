/*
 * ==========================================
 * PROBLEM STATEMENT: Find X Value of Array II
 * ==========================================
 * You are given an array of positive integers `nums`, a positive integer `k`, and a 2D array `queries`. 
 * Each query contains `[indexi, valuei, starti, xi]`.
 * 
 * Operations and Rules:
 * - Update `nums[indexi]` to `valuei` (persists for subsequent queries).
 * - Remove the prefix from index `0` to `starti - 1`.
 * - The x-value of nums for a given remainder `xi` is defined as the number of ways to remove any suffix 
 *   such that the product of the remaining elements leaves a remainder of `xi` modulo `k`.
 * 
 * Return an array of size `queries.length` containing the answer for each query.
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= k <= 5
 * 1 <= queries.length <= 2 * 10^4
 * 
 * ==========================================
 * OPTIMAL SOLUTION & APPROACH (Segment Tree with Modular Arithmetic)
 * ==========================================
 * - Approach:
 *   1. Segment Tree Node Representation: Each node in the segment tree tracks:
 *      - `prod`: The product of all elements in its range modulo `k`.
 *      - `cnt`: An array of size `k` storing the count of prefix/suffix products leaving each remainder modulo `k`.
 *   2. Tree Merging: When combining two segment tree nodes (left and right children), the resulting remainder frequencies 
 *      are computed by multiplying the left node's product with each remainder frequency of the right node.
 *   3. Point Updates: Efficiently update individual elements in $O(\log N)$ time using the segment tree.
 *   4. Range Queries: Query the segment tree from `starti` to `n - 1` to get the aggregated counts for the valid suffix range, 
 *      then extract the count corresponding to `xi`.
 * 
 * - Complexity:
 *   - Time Complexity: $O(Q \cdot \log N + N \cdot k)$, where $N$ is the array length and $Q$ is the number of queries.
 *   - Space Complexity: $O(N \cdot k)$ to store the segment tree nodes.
 */

class Node {
    int[] cnt = new int[5];
    int prod = 0;
}

class SegmentTree {
    int n, k;
    Node[] segTree;

    SegmentTree(int[] nums, int k) {
        this.k = k;
        this.n = nums.length;
        segTree = new Node[4 * n];

        for (int i = 0; i < 4 * n; i++) {
            segTree[i] = new Node();
        }
        build(0, 0, n - 1, nums);
    }

    void build(int i, int l, int r, int[] nums) {
        if (l == r) {
            leafNode(i, nums[l]);
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * i + 1, l, mid, nums);
        build(2 * i + 2, mid + 1, r, nums);
        segTree[i] = mergeNodes(segTree[2 * i + 1], segTree[2 * i + 2]);
    }

    void leafNode(int i, int value) { 
        for (int x = 0; x < k; x++) {
            segTree[i].cnt[x] = 0;
        }

        int r = value % k;
        segTree[i].cnt[r] = 1;
        segTree[i].prod = r;
    }

    Node mergeNodes(Node left, Node right) {
        Node result = new Node();
        result.prod = (left.prod * right.prod) % k;

        for (int x = 0; x < k; x++) {
            result.cnt[x] = left.cnt[x];
        }
        for (int x = 0; x < k; x++) {
            int newRem = (left.prod * x) % k;
            result.cnt[newRem] += right.cnt[x];
        }
        return result;
    }

    void segTreeUpdate(int i, int l, int r, int index, int value) {
        if (l == r) {
            leafNode(i, value);
            return;
        }
        int mid = l + (r - l) / 2;

        if (index <= mid) {
            segTreeUpdate(2 * i + 1, l, mid, index, value);
        } else {
            segTreeUpdate(2 * i + 2, mid + 1, r, index, value);
        }
        segTree[i] = mergeNodes(segTree[2 * i + 1], segTree[2 * i + 2]);
    }

    void update(int index, int value) {
        segTreeUpdate(0, 0, n - 1, index, value);
    }

    Node segTreeQuery(int start, int end, int i, int l, int r) {
        if (l >= start && r <= end) {
            return segTree[i];
        }

        int mid = l + (r - l) / 2;
        if (end <= mid) {
            return segTreeQuery(start, end, 2 * i + 1, l, mid);
        }
        if (start > mid) {
            return segTreeQuery(start, end, 2 * i + 2, mid + 1, r);
        }
        Node left  = segTreeQuery(start, end, 2 * i + 1, l, mid);
        Node right = segTreeQuery(start, end, 2 * i + 2, mid + 1, r);

        return mergeNodes(left, right);
    }

    Node query(int start, int end) {
        return segTreeQuery(start, end, 0, 0, n - 1);
    }
}

class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree segTree = new SegmentTree(nums, k);
        int[] result = new int[queries.length];

        for (int idx = 0; idx < queries.length; idx++) {
            int index = queries[idx][0];
            int value = queries[idx][1];
            int start = queries[idx][2];
            int x     = queries[idx][3];

            segTree.update(index, value);
            Node node = segTree.query(start, n - 1);

            result[idx] = node.cnt[x];
        }

        return result;
    }
}
