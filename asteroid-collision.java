// Problem - Opposite Sign Pair Reduction (GFG) / Asteroid Collision

// Problem Description
// You are given an array of integers. The task is to repeatedly process adjacent elements from left to right. Whenever two adjacent elements have opposite signs, apply the following rules:

// If their absolute values are different, remove both elements and insert the one with the greater absolute value while keeping its sign.
// If their absolute values are equal, remove both elements and do not insert anything.

// This process continues until no more such operations can be performed. Finally, return the resulting array.

// Examples

// Input
// arr = [10, -5, -8, 2, -5]
// Output
// [10]

// Explanation
// 10 and -5 collide, 10 survives
// 10 and -8 collide, 10 survives
// 2 and -5 collide, -5 survives
// 10 and -5 collide, 10 survives
// Final array becomes [10]

// Input
// arr = [5, -5, -2, -10]
// Output
// [-2, -10]

// Explanation
// 5 and -5 cancel each other
// -2 and -10 have same sign so no operation
// Final array is [-2, -10]

// Input
// arr = [-20, 1, 20]
// Output
// []

// Explanation
// -20 and 1 collide, -20 survives
// -20 and 20 cancel each other
// Final array becomes empty

// Approach

// This problem can be efficiently solved using a stack.
// We traverse the array from left to right and simulate the process using stack operations.

// Steps

// 1. Initialize an empty stack
// 2. Traverse each element in the array
// 3. If stack is empty, push the element
// 4. If the current element and stack top have opposite signs, resolve collision
// 5. Compare absolute values
// 6. Remove elements based on rules
// 7. Continue until no more collisions are possible
// 8. Push the remaining element if it survives

// Why Stack

// Stack helps simulate adjacent interactions efficiently.
// After removing elements, new adjacent pairs may form, which stack handles naturally.

// Algorithm

// For each element
// Check while stack is not empty and top element has opposite sign
// Compare absolute values
// If stack top is larger, discard current element
// If current element is larger, pop stack and continue
// If equal, pop stack and discard current element
// If current element survives, push it into stack

// Code

// Java Implementation

import java.util.*;

class Solution {
public ArrayList<Integer> reducePairs(int[] arr) {
Stack<Integer> st = new Stack<>();

    for (int num : arr) {
        boolean destroyed = false;
        
        while (!st.isEmpty() && st.peek() * num < 0) {
            int top = st.peek();
            
            if (Math.abs(top) > Math.abs(num)) {
                destroyed = true;
                break;
            } 
            else if (Math.abs(top) < Math.abs(num)) {
                st.pop();
            } 
            else {
                st.pop();
                destroyed = true;
                break;
            }
        }
        
        if (!destroyed) {
            st.push(num);
        }
    }
    
    return new ArrayList<>(st);
}
}

// Time Complexity

// O(n)
// Each element is pushed and popped at most once

// Space Complexity

// O(n)
// Stack is used to store intermediate elements

// Key Observations

// Only adjacent elements interact
// Chain reactions can occur after removal
// Stack efficiently handles dynamic adjacency
// Similar pattern appears in collision problems

// Use Cases

// Simulation problems
// Array reduction problems
// Collision based problems
// Stack based pattern recognition

// Conclusion

// This problem is a classic example of using stack to simulate interactions between elements.
// Understanding this pattern helps solve many similar problems efficiently.
