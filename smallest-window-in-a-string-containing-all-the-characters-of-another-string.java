/**
 * PROBLEM STATEMENT: Smallest window containing all characters (Minimum Window Substring)
 * --------------------------------------------------------------------------------
 * Given two strings 's' and 'p'. Find the smallest substring in 's' consisting 
 * of all the characters (including duplicates) of the string 'p'. 
 * * Requirements:
 * 1. Return an empty string if no such substring is present.
 * 2. If multiple substrings of the same minimum length exist, return the one 
 * with the smallest starting index.
 * * Examples:
 * Input: s = "timetopractice", p = "toc"
 * Output: "toprac"
 * * Input: s = "zoom", p = "zooe"
 * Output: ""
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Sliding Window (Two Pointers)
 * --------------------------------------------------------------------------------
 * 1. Frequency Map: Create a frequency array/map for string 'p' to know exactly 
 * which characters (and how many) we need to collect.
 * 2. Expansion (Right Pointer): Move the 'end' pointer across 's'. If the current 
 * character matches one in our frequency map for 'p', decrement a 'requiredCount'.
 * 3. Contraction (Left Pointer): Once 'requiredCount' reaches 0 (meaning the 
 * current window [start, end] contains all characters of 'p'):
 * a. Update the minimum length and store the starting index.
 * b. Shrink the window from the 'start' to see if a smaller valid window exists.
 * c. As we remove characters from the 'start', if a character being removed 
 * was essential for matching 'p', increment 'requiredCount' and stop shrinking.
 * 4. Result: Use the stored starting index and minimum length to return the substring.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(|s| + |p|)
 * - We process string 'p' once to build the frequency map.
 * - Each character in string 's' is visited at most twice (once by the 'end' 
 * pointer and once by the 'start' pointer).
 * Space Complexity: O(1)
 * - We use a fixed-size frequency array of size 256 (for ASCII characters), 
 * which does not grow with input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public static String minWindow(String s, String p) {
        if(s.length() < p.length()) return "";
        
        int[] freq = new int[256];
        
        for(char c : p.toCharArray()){
            freq[c]++;
        }
        
        int start = 0;
        int startIndex = -1;
        int minLen = Integer.MAX_VALUE;
        int count = p.length();
        
        for(int end = 0; end < s.length(); end++){
            
            char ch = s.charAt(end);
            
            if(freq[ch] > 0){
                count--;
            }
            
            freq[ch]--;
            
            while(count == 0){
                
                if(end - start + 1 < minLen){
                    minLen = end - start + 1;
                    startIndex = start;
                }
                
                char startChar = s.charAt(start);
                freq[startChar]++;
                
                if(freq[startChar] > 0){
                    count++;
                }
                
                start++;
            }
        }
        
        if(startIndex == -1) return "";
        
        return s.substring(startIndex, startIndex + minLen);
    }
}

