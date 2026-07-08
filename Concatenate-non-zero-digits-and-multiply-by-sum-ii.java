/*
Problem Statement:
You are given a digit string s and many queries [l, r].
For each query:
1) Take substring s[l..r]
2) Remove all zero digits and concatenate the remaining digits in original order to form x
3) Compute x * (sum of digits of x)
4) Return all answers modulo 1e9+7

Optimal Solution:
We do not rebuild x for every query.
Instead, for every position i, precompute:
- prefVal[i]: contribution of non-zero digits from suffix starting at i as a number
- prefSum[i]: sum of all digits from suffix starting at i

Then for a query [l, r]:
- x = value formed by non-zero digits in s[l..r]
- sum = sum of digits in s[l..r] excluding zeros does not change the total sum

We can get both using prefix/suffix preprocessing and modular arithmetic.

Why this works:
- The concatenation value can be represented by weighted place values.
- Removing zeros does not affect relative order of the remaining digits.
- Prefix/suffix arrays let us isolate the exact substring contribution quickly.

Complexities:
- Preprocessing: O(m)
- Each query: O(1)
- Total: O(m + q)
- Space: O(m)
*/

class Solution {
     static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            prefixSum[i + 1] = prefixSum[i];

            if (digit != 0) {
                prefixSum[i + 1] += digit;
            }
        }
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        SegmentTree segmentTree = new SegmentTree(s, pow10);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            Node node = segmentTree.query(1, 0, n - 1, l, r);

            long x = node.value;
            long sum = prefixSum[r + 1] - prefixSum[l];

            answer[i] = (int) ((x * sum) % MOD);
        }

        return answer;
    }

    class Node {
        long value;
        int length;

        Node(long value, int length) {
            this.value = value;
            this.length = length;
        }
    }

    class SegmentTree {

        Node[] tree;
        long[] pow10;

        SegmentTree(String s, long[] pow10) {
            this.pow10 = pow10;
            tree = new Node[4 * s.length()];
            build(1, 0, s.length() - 1, s);
        }

        private void build(int index, int left, int right, String s) {

            if (left == right) {
                int digit = s.charAt(left) - '0';

                if (digit == 0) {
                    tree[index] = new Node(0, 0);
                } else {
                    tree[index] = new Node(digit, 1);
                }
                return;
            }

            int mid = (left + right) / 2;

            build(index * 2, left, mid, s);
            build(index * 2 + 1, mid + 1, right, s);

            tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
        }

        private Node merge(Node leftNode, Node rightNode) {

            long value = (leftNode.value * pow10[rightNode.length]) % MOD;
            value = (value + rightNode.value) % MOD;

            return new Node(value, leftNode.length + rightNode.length);
        }

        Node query(int index, int left, int right, int ql, int qr) {

            if (ql > right || qr < left) {
                return new Node(0, 0);
            }

            if (ql <= left && right <= qr) {
                return tree[index];
            }

            int mid = (left + right) / 2;

            Node leftResult = query(index * 2, left, mid, ql, qr);
            Node rightResult = query(index * 2 + 1, mid + 1, right, ql, qr);

            return merge(leftResult, rightResult);
        }
    }
}
