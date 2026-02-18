/**
 * PROBLEM STATEMENT: Count Inversions
 * --------------------------------------------------------------------------------
 * Given an array of integers arr[], find the Inversion Count of the array.
 * * Definition:
 * An inversion count is the number of pairs (i, j) such that:
 * 1. i < j
 * 2. arr[i] > arr[j]
 * * Example:
 * Input: arr[] = [2, 4, 1, 3, 5]
 * Output: 3
 * Explanation: The inversions are (2, 1), (4, 1), and (4, 3).
 * * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= arr[i] <= 10^4
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Enhanced Merge Sort
 * --------------------------------------------------------------------------------
 * 1. Logic:
 * A brute force O(N^2) approach would check all pairs, but is too slow for N=10^5.
 * We use the Divide and Conquer strategy of Merge Sort.
 * * 2. How it counts:
 * During the "Merge" step, we have two sorted subarrays: Left and Right.
 * - If arr[leftIndex] <= arr[rightIndex]: No inversion, move leftIndex.
 * - If arr[leftIndex] > arr[rightIndex]: Since the Left subarray is sorted, 
 * every element from leftIndex to the end of the Left subarray is also 
 * greater than arr[rightIndex].
 * - Inversions added = (mid - leftIndex + 1).
 * * 3. Algorithm:
 * - Recursively split the array (Divide).
 * - Count inversions in the left half.
 * - Count inversions in the right half.
 * - Count "split" inversions during the Merge step.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - The array is divided log N times, and each merge step takes O(N) time.
 * Space Complexity: O(N)
 * - A temporary array is required to merge the elements back in sorted order.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    static int inversionCount(int arr[]) {
        return mergeSort(arr, 0, arr.length - 1);
    }
     static int mergeSort(int arr[], int left, int right) {
        int count = 0;
        
        if(left < right) {
            int mid = left + (right - left) / 2;
            
            count += mergeSort(arr, left, mid);
            count += mergeSort(arr, mid + 1, right);
            count += merge(arr, left, mid, right);
        }
         return count;
    }
   static int merge(int arr[], int left, int mid, int right) {
        int temp[] = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int count = 0;
        
        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } 
            else {
                temp[k++] = arr[j++];
                count += (mid - i + 1);
            }
        }
        
        while(i <= mid) temp[k++] = arr[i++];
        while(j <= right) temp[k++] = arr[j++];
        
        for(int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        
        return count;
    }
}


