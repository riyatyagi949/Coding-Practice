// Leetcode - 3600  Maximize Spanning Tree Stability with Upgrades

// Optimal Solution in Java - 

class Solution {
    class DSU {
        int[] parent;
        int groups;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            groups = n;
        }
         int find(int x) {
            if (parent[x] == x) 
            return x;
            return parent[x] = find(parent[x]);
        }

        boolean unite(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb) 
            return false;

            parent[pb] = pa;
            groups--;
            return true;
        }
    }
     public int maxStability(int n, int[][] edges, int k) {

        DSU dsu = new DSU(n);

        List<Integer> mustStrength = new ArrayList<>();
        List<Integer> optStrength = new ArrayList<>();

        List<int[]> mustEdges = new ArrayList<>();
        List<int[]> optEdges = new ArrayList<>();

        for (int[] e : edges) {
            if (e[3] == 1) {
                mustEdges.add(e);
            }
             else {
                optEdges.add(e);
            }
        }

        for (int[] e : mustEdges) {
            if (dsu.unite(e[0], e[1]) == false) {
                return -1;
            }
            mustStrength.add(e[2]);
        }

        optEdges.sort((a, b) -> Long.compare((long)b[2]*2, (long)a[2]*2));

        for (int[] e : optEdges) {
            if (dsu.unite(e[0], e[1]) == true) {
                optStrength.add(e[2]);
            }
        }

        if (dsu.groups > 1) {
            return -1;
        }

        Collections.sort(optStrength);

        int used = 0;

        for (int i = 0; i < optStrength.size() && used < k; i++) {
            optStrength.set(i, optStrength.get(i) * 2);
            used++;
        }

        int minStrength = Integer.MAX_VALUE;

        for (int v : mustStrength) minStrength = Math.min(minStrength, v);
        for (int v : optStrength) minStrength = Math.min(minStrength, v);

        return minStrength;
    }
}
