/**
 * PROBLEM STATEMENT: 1622. Fancy Sequence
 * --------------------------------------------------------------------------------
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 * * Implement the Fancy class:
 * - Fancy(): Initializes the object with an empty sequence.
 * - void append(val): Appends an integer val to the end of the sequence.
 * - void addAll(inc): Increments all existing values in the sequence by an integer inc.
 * - void multAll(m): Multiplies all existing values in the sequence by an integer m.
 * - int getIndex(idx): Gets the current value at index idx modulo 10^9 + 7. 
 * If idx is invalid, return -1.
 * * Constraints:
 * - 1 <= val, inc, m <= 100
 * - 0 <= idx <= 10^5
 * - Total calls to all functions <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: MATHEMATICAL LAZY PROPAGATION (Modular Inverse)
 * --------------------------------------------------------------------------------
 * 1. The Challenge:
 * Performing addAll and multAll directly on an array would take O(N) per operation, 
 * leading to O(N^2) total time, which is too slow for 10^5 calls.
 * * 2. Mathematical Transformation:
 * We can maintain two global variables: 'mult' (cumulative multiplier) and 'add' 
 * (cumulative addition). Any value v added at time t should result in:
 * CurrentValue = (v * currentMult + currentAdd)
 * * 3. Inverse Operation for Append:
 * When we append a new value 'val', we need to store it in a "neutral" form 
 * such that when the current 'mult' and 'add' are applied, it equals the original 'val'.
 * Let x be the stored value:
 * (x * mult + add) ≡ val (mod M)
 * x * mult ≡ (val - add) (mod M)
 * x ≡ (val - add) * inv(mult) (mod M)
 * * 4. Modular Multiplicative Inverse:
 * Since M (10^9 + 7) is prime, we use Fermat's Little Theorem:
 * inv(a) = a^(M-2) mod M.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity:
 * - append: O(log M) due to the modular inverse calculation (power function).
 * - addAll: O(1)
 * - multAll: O(1)
 * - getIndex: O(1)
 * Space Complexity: O(N) to store the sequence of processed values.
 * --------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Fancy {
    long M = 1_000_000_007;

    List<Long> seq = new ArrayList<>();
    long add = 0;
    long mult = 1;

    long power(long a, long b) {
        if (b == 0)
            return 1;

        long half   = power(a, b / 2);
        long result = (half * half) % M;

        if (b % 2 == 1) {
            result = (result * a) % M;
        }
        return result;
    }
    public Fancy() {

    }
    public void append(int val) {
        long x = ((val - add) % M + M) * power(mult, M - 2) % M;
        seq.add(x);
    }
    public void addAll(int inc) {
        add = (add + inc) % M;
    }
    public void multAll(int m) {
        mult = (mult * m) % M;
        add  = (add * m) % M;
    }
    public int getIndex(int idx) {
        if (idx >= seq.size())
            return -1;

        return (int) ((seq.get(idx) * mult + add) % M);
    }
}
