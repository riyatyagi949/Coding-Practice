/*
===========================================
LeetCode 3532 - Path Existence Queries in a Graph I
===========================================

Problem Statement:
------------------
We are given:
1. n nodes numbered from 0 to n-1.
2. A sorted array nums[].
3. An integer maxDiff.

An undirected edge exists between node i and node j if:

    |nums[i] - nums[j]| <= maxDiff

For every query [u, v], determine whether there exists
a path between node u and node v.

Return a boolean array where:
true  -> path exists
false -> no path exists.


-----------------------------------------------------
Observation
-----------------------------------------------------

A brute-force approach would build the graph and perform
BFS/DFS for every query.

Time Complexity:
O(Q * (N + E))

This is too slow since

N <= 100000
Q <= 100000


-----------------------------------------------------
Key Observation
-----------------------------------------------------

nums[] is already SORTED.

Suppose

nums = [2,5,6,8]
maxDiff = 2

Adjacent differences

2 -> 5 = 3 (Break)
5 -> 6 = 1 (Connected)
6 -> 8 = 2 (Connected)

Notice:

If adjacent elements differ by at most maxDiff,
they belong to the same connected component.

If the adjacent difference becomes greater than maxDiff,
the graph gets disconnected there forever because
all later elements are even larger.

Therefore,
we never need to build the graph.

We only need to identify connected components.


-----------------------------------------------------
Idea
-----------------------------------------------------

Create an array component[].

component[i] tells which connected component
index i belongs to.

Start with component id = 0.

Traverse nums from left to right.

If

nums[i] - nums[i-1] > maxDiff

then a new connected component starts.

Otherwise,
current index belongs to the same component.

Example

nums = [2,5,6,8]
maxDiff = 2

Component IDs

Index : 0 1 2 3
Value : 2 5 6 8
Comp  : 0 1 1 1

Now answer every query by simply checking

component[u] == component[v]


-----------------------------------------------------
Dry Run
-----------------------------------------------------

nums = [2,5,6,8]

component[]

Initially

component[0] = 0

i=1

5-2 = 3 > 2

New component

component[1] = 1

i=2

6-5 = 1 <=2

Same component

component[2] = 1

i=3

8-6 = 2 <=2

Same component

component[3] = 1

Final component array

[0,1,1,1]

Queries

[0,1]

0 != 1

Answer = false

---------------------

[1,3]

1 == 1

Answer = true


-----------------------------------------------------
Algorithm
-----------------------------------------------------

1. Create component[].
2. Give first node component id 0.
3. Traverse nums.
4. Whenever adjacent difference exceeds maxDiff,
   increase component id.
5. Store component id.
6. For every query,
   compare component ids.


-----------------------------------------------------
Time Complexity
-----------------------------------------------------

Building Components : O(N)

Answering Queries : O(Q)

Overall

O(N + Q)


-----------------------------------------------------
Space Complexity
-----------------------------------------------------

O(N)


-----------------------------------------------------
Optimal Java Solution
-----------------------------------------------------
*/

class Solution {

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        // Stores connected component number for every index
        int[] component = new int[n];

        // Current connected component id
        int id = 0;

        // First node belongs to component 0
        component[0] = 0;

        // Build connected components
        for (int i = 1; i < n; i++) {

            // If adjacent difference is greater than maxDiff,
            // graph gets disconnected here.
            if (nums[i] - nums[i - 1] > maxDiff) {
                id++;
            }

            component[i] = id;
        }

        // Result array
        boolean[] ans = new boolean[queries.length];

        // Process each query
        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            // Path exists only if both nodes belong
            // to the same connected component.
            ans[i] = (component[u] == component[v]);
        }

        return ans;
    }
}
```
