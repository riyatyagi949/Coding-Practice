/**
 * PROBLEM STATEMENT: 1301. Number of Paths with Max Score
 * --------------------------------------------------------------------------------
 * You are given a square board of characters. Start at the bottom right 'S', 
 * reach the top left 'E'. Move Up, Left, or Up-Left. 
 * 'X' is an obstacle. Other squares contain digits 1-9.
 * Return [max_sum, number_of_paths_to_achieve_max_sum] (modulo 10^9 + 7).
 * If no path exists, return [0, 0].
 * * OPTIMAL SOLUTION: Dynamic Programming
 * --------------------------------------------------------------------------------
 * 1. Define dp[i][j] as a pair (max_score, path_count) to reach (i, j) 
 * starting from 'S'.
 * 2. Since moves are Up, Left, or Up-Left, to compute the state at (i, j), 
 * check (i+1, j), (i, j+1), and (i+1, j+1).
 * 3. Max score for current cell = (current cell value) + max(scores of valid predecessors).
 * 4. Number of paths = sum of paths from all predecessors that yielded the 
 * calculated max score.
 * 5. Handle 'S' as 0 and 'E' as 0. 
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2), where N is the length of the board. We traverse 
 * every cell once.
 * Space Complexity: O(N^2) to store scores and counts for each cell.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (board.get(i).charAt(j) == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;
                long cnt = 0;

                if (i > 0 && score[i - 1][j] != -1) {
                    if (score[i - 1][j] > best) {
                        best = score[i - 1][j];
                        cnt = ways[i - 1][j];
                    }
                     else if (score[i - 1][j] == best) {
                        cnt += ways[i - 1][j];
                    }
                }

                if (j > 0 && score[i][j - 1] != -1) {
                    if (score[i][j - 1] > best) {
                        best = score[i][j - 1];
                        cnt = ways[i][j - 1];
                    } 
                    else if (score[i][j - 1] == best) {
                        cnt += ways[i][j - 1];
                    }
                }

                if (i > 0 && j > 0 && score[i - 1][j - 1] != -1) {
                    if (score[i - 1][j - 1] > best) {
                        best = score[i - 1][j - 1];
                        cnt = ways[i - 1][j - 1];
                    } 
                    else if (score[i - 1][j - 1] == best) {
                        cnt += ways[i - 1][j - 1];
                    }
                }

                if (best == -1)
                    continue;

                char ch = board.get(i).charAt(j);
                int val = (ch == 'S' || ch == 'E') ? 0 : ch - '0';

                score[i][j] = best + val;
                ways[i][j] = (int) (cnt % MOD);
            }
        }

        if (ways[n - 1][n - 1] == 0)
            return new int[] {0, 0};

        return new int[] {score[n - 1][n - 1], ways[n - 1][n - 1]};
    }
}
