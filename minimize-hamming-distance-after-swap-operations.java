// Leetcode -1722 Minimize Hamming Distance After Swap Operations

// Problem
// You are given two arrays source and target of the same length. You are also given allowedSwaps where each pair of indices means you can swap those positions in source any number of times.

// The goal is to find the minimum Hamming distance between source and target after performing any swaps.

// Hamming distance is the number of positions where source[i] is not equal to target[i].

// Key idea
// Indices that are connected through allowed swaps form a group. Inside a group, elements can be rearranged freely. So instead of matching positions, we match frequencies of values within each group.

// Approach

// 1. Use Union Find to group indices based on allowedSwaps.
// 2. For each group, store frequency of elements from source.
// 3. Traverse target array:
//    if the required value is present in the same group, reduce its count
//    otherwise increase mismatch count

// Java code

class Solution {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        DSU dsu = new DSU(n);

        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int parent = dsu.find(i);
            map.putIfAbsent(parent, new HashMap<>());
            Map<Integer, Integer> freq = map.get(parent);
            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int parent = dsu.find(i);
            Map<Integer, Integer> freq = map.get(parent);

            if (freq.getOrDefault(target[i], 0) > 0) {
                freq.put(target[i], freq.get(target[i]) - 1);
            } else {
                ans++;
            }
        }

        return ans;
    }

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                parent[px] = py;
            }
        }
    }
}

// Complexity
// Time complexity is almost linear O(n)
// Space complexity is O(n)

Summary
Group indices using Union Find. Within each group, compare frequencies instead of positions. Count elements that cannot be matched.
