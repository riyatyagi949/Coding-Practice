/**
 * PROBLEM STATEMENT: 2075. Decode the Slanted Ciphertext
 * --------------------------------------------------------------------------------
 * A string originalText is encoded into encodedText using a slanted transposition 
 * cipher with a fixed number of rows.
 * 1. originalText is placed in a matrix row by row, but shifted: 
 * (row 0, col 0), (row 1, col 1), (row 2, col 2)...
 * 2. All empty cells are filled with ' '.
 * 3. encodedText is formed by reading the matrix row-wise.
 * * Task: Given encodedText and rows, return the originalText (without trailing spaces).
 * * Example:
 * Input: encodedText = "ch   ie   pr", rows = 3
 * Output: "cipher"
 * Matrix structure for "cipher":
 * c . . . 
 * . i . .
 * . . p .
 * h . . .
 * . e . .
 * . . r .
 * (Flattened row-wise, this becomes "ch   ie   pr")
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LINEAR STRING TRAVERSAL
 * --------------------------------------------------------------------------------
 * 1. Calculate Columns:
 * The total length of encodedText is rows * cols. 
 * Therefore, cols = encodedText.length() / rows.
 * * 2. Traversal Logic:
 * The original text was placed diagonally. To decode, we start at the top of 
 * each potential diagonal.
 * - Start at col 'c' from 0 to cols - 1 (the top row).
 * - For each starting column 'c', move diagonally: (row 0, col c), (row 1, col c+1)...
 * - In the flattened encodedText, the index of (r, c) is: (r * cols) + c.
 * * 3. Diagonal Step:
 * Moving from (r, c) to (r+1, c+1) in the flat string:
 * Index_next = ((r+1) * cols) + (c+1) = (r * cols) + cols + c + 1 = Index_curr + cols + 1.
 * * 4. Cleaning:
 * The problem specifies that originalText does not have trailing spaces. We use
 * a strip/trim operation on the final result.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - N is the length of encodedText. We visit each character needed for the 
 * original text at most once.
 * Space Complexity: O(N)
 * - To store the result in a StringBuilder before converting to the final string.
 * --------------------------------------------------------------------------------
 */

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        // Edge case: empty string or single row
        if (encodedText.length() == 0 || rows == 1) {
            return encodedText;
        }

        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();

        // The original text starts at each column of the first row (row 0)
        // and moves diagonally down-right.
        for (int c = 0; c < cols; c++) {
            int curRow = 0;
            int curCol = c;
            
            // Traverse the diagonal starting at (0, c)
            // We stop if the diagonal goes out of column bounds or row bounds
            while (curRow < rows && curCol < cols) {
                int index = curRow * cols + curCol;
                sb.append(encodedText.charAt(index));
                
                // Move to next diagonal element: (row + 1, col + 1)
                curRow++;
                curCol++;
            }
        }

        // The problem states originalText does not have trailing spaces.
        // We remove spaces from the right side.
        int lastChar = sb.length() - 1;
        while (lastChar >= 0 && sb.charAt(lastChar) == ' ') {
            lastChar--;
        }

        return sb.substring(0, lastChar + 1);
    }
}
