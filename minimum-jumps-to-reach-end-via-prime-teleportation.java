/**
 * PROBLEM STATEMENT: 3629. Minimum Jumps to Reach End via Prime Teleportation
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums' of length n. You start at index 0.
 * Goal: Reach index n - 1 with minimum jumps.
 * 
 * Operations:
 * 1. Adjacent Step: Jump to i + 1 or i - 1.
 * 2. Prime Teleportation: If nums[i] is a prime p, jump to any index j != i 
 *    such that nums[j] % p == 0 (nums[j] is a multiple of p).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BREADTH-FIRST SEARCH (BFS)
 * --------------------------------------------------------------------------------
 * 1. Shortest Path: Since all jumps have equal weight (1), BFS is optimal.
 * 2. Prime Identification: Use the Sieve of Eratosthenes to precompute primes 
 *    up to the maximum value in 'nums' (up to 10^6).
 * 3. Efficient Teleportation:
 *    - Maintain a mapping from each value to a list of indices where it appears.
 *    - To avoid redundant work, once a prime 'p' has been used for teleportation, 
 *      never process it again.
 *    - Once a specific value (multiple of p) has had its indices added to the 
 *      queue, clear its list in the map to avoid re-scanning.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M log log M + N)
 * - Sieve: O(M log log M), where M is the max value in nums (10^6).
 * - BFS: Each index is added to the queue once. For each prime encountered, 
 *   we iterate through its multiples. The total number of multiple checks 
 *   across all primes is bounded by O(M log log M).
 * Space Complexity: O(M + N)
 * - Sieve array: O(M).
 * - Value-to-Indices Map: O(N).
 * - BFS structures: O(N).
 * --------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    private boolean[] isPrime;
    private void buildSieve(int maxEl) {
        isPrime = new boolean[maxEl + 1];
        Arrays.fill(isPrime, true);

        if(maxEl >= 0) isPrime[0] = false;
        if(maxEl >= 1) isPrime[1] = false;

        for(int num = 2; num * num <= maxEl; num++) {

            if(isPrime[num]) {

                for(int multiple = num * num;
                    multiple <= maxEl;
                    multiple += num) {

                    isPrime[multiple] = false;
                }
            }
        }
    }
    public int minJumps(int[] nums) {
        int n = nums.length;

        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        int maxEl = 0;

        for(int i = 0; i < n; i++) {

            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

            maxEl = Math.max(maxEl, nums[i]);
        }
        buildSieve(maxEl);

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        HashSet<Integer> seen = new HashSet<>();

        int steps = 0;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size-- > 0) {

                int i = queue.poll();

                if(i == n - 1) {
                    return steps;
                }
                if(i - 1 >= 0 && !visited[i - 1]) {

                    queue.offer(i - 1);
                    visited[i - 1] = true;
                }
                if(i + 1 < n && !visited[i + 1]) {

                    queue.offer(i + 1);
                    visited[i + 1] = true;
                }
                if(!isPrime[nums[i]] || seen.contains(nums[i])) {
                    continue;
                }
                for(int multiple = nums[i];
                    multiple <= maxEl;
                    multiple += nums[i]) {

                    if(!mp.containsKey(multiple)) {
                        continue;
                    }

                    for(int j : mp.get(multiple)) {

                        if(!visited[j]) {

                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }

                seen.add(nums[i]);
            }

            steps++;
        }

        return -1;
    }
}

