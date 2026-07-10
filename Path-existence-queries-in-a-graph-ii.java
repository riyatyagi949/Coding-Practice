/*
===========================================================
LeetCode 3534 - Path Existence Queries in a Graph II (Hard)
===========================================================

Problem Statement
-----------------
We are given:

1. n nodes numbered from 0 to n-1.
2. nums[] where nums[i] is the value of node i.
3. maxDiff.

There is an undirected edge between nodes i and j if

    |nums[i] - nums[j]| <= maxDiff

For every query [u, v], return the minimum number of
edges in the shortest path from u to v.

If no path exists, return -1.


Example

nums = [5,3,1,9,10]
maxDiff = 2

Edges

5 <-> 3
3 <-> 1
9 <-> 10

Query

0 -> 2

Answer

0 -> 1 -> 2

Distance = 2


----------------------------------------------------------
Why Brute Force Fails
----------------------------------------------------------

For every query we can run BFS.

Time

O(Q × (N + E))

N = 100000
Q = 100000

Impossible.


----------------------------------------------------------
Key Observation
----------------------------------------------------------

Suppose we sort nodes according to nums.

Example

nums = [1,8,3,4,2]

Sorted

Value  Index

1      0
2      4
3      2
4      3
8      1

Now suppose maxDiff = 3.

From every value,
we can directly jump to the farthest value
whose difference is at most maxDiff.

Instead of checking every edge repeatedly,
precompute these farthest jumps.

This converts graph traversal into
jumping through sorted values.


----------------------------------------------------------
Idea
----------------------------------------------------------

Step 1

Store

(value, originalIndex)

for every node.

Step 2

Sort according to value.

Step 3

Using two pointers,

find the farthest reachable value
within maxDiff.

Example

Values

1 2 3 4 8

For value 1

maximum reachable value = 4

For value 2

maximum reachable value = 4

For value 4

maximum reachable value = 4

For value 8

maximum reachable value = 8


----------------------------------------------------------
Binary Lifting
----------------------------------------------------------

Suppose

jump[i][0]

stores the farthest node reachable
in ONE jump.

Then

jump[i][1]

stores destination after TWO jumps.

jump[i][2]

stores destination after FOUR jumps.

jump[i][3]

stores destination after EIGHT jumps.

Formula

jump[i][k] =
jump[ jump[i][k-1] ][k-1]

Exactly same as Binary Lifting.


----------------------------------------------------------
Answering Query
----------------------------------------------------------

Suppose

source = i
destination = j

If nums[i] > nums[j]

swap them.

Now repeatedly take the biggest jump
that still keeps us before destination.

Finally

If next jump reaches destination

answer = jumps + 1

Otherwise

destination cannot be reached.

Return -1.


----------------------------------------------------------
Dry Run
----------------------------------------------------------

nums = [5,3,1,9,10]

Sorted

1
3
5
9
10

maxDiff = 2

Maximum jumps

1 -> 5

3 -> 5

5 -> 5

9 -> 10

10 -> 10

Query

5 -> 1

After ordering

1 -> 5

Take one jump

1 -> 5

Distance = 2


----------------------------------------------------------
Time Complexity
----------------------------------------------------------

Sorting

O(N log N)

Building Binary Lifting Table

O(N log N)

Each Query

O(log N)

Overall

O((N + Q) log N)


----------------------------------------------------------
Space Complexity
----------------------------------------------------------

O(N log N)


----------------------------------------------------------
Optimal Java Solution
----------------------------------------------------------
*/

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        // Store (value, original index)
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        // Sort nodes according to value
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        // Maximum power needed since 2^20 > 100000
        int LOG = 20;

        // Binary lifting table
        int[][] jump = new int[n][LOG];

        // Two pointer to find farthest reachable node
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {

            // Shrink right until values differ by at most maxDiff
            while (pairs[right][0] - pairs[left][0] > maxDiff) {
                right--;
            }

            // Original indices
            int from = pairs[left][1];
            int to = pairs[right][1];

            // One jump
            jump[from][0] = to;

            // Binary lifting table
            for (int k = 1; k < LOG; k++) {
                jump[from][k] = jump[jump[from][k - 1]][k - 1];
            }
        }

        int[] ans = new int[queries.length];

        // Process every query
        for (int q = 0; q < queries.length; q++) {

            int u = queries[q][0];
            int v = queries[q][1];

            // Always move from smaller value
            if (nums[u] > nums[v]) {
                int temp = u;
                u = v;
                v = temp;
            }

            // Same node
            if (u == v) {
                ans[q] = 0;
                continue;
            }

            // Same value means direct edge
            if (nums[u] == nums[v]) {
                ans[q] = 1;
                continue;
            }

            int distance = 0;

            // Binary lifting
            for (int k = LOG - 1; k >= 0; k--) {

                // Take biggest jump that still stays before destination
                if (nums[jump[u][k]] < nums[v]) {
                    distance += (1 << k);
                    u = jump[u][k];
                }
            }

            // Cannot reach destination
            if (nums[jump[u][0]] < nums[v]) {
                ans[q] = -1;
            }
            // Final jump reaches destination
            else {
                ans[q] = distance + 1;
            }
        }

        return ans;
    }
}
```
