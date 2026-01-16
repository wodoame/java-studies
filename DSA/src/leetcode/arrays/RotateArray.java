package leetcode.arrays;

import java.util.Arrays;

// leetcode 189
public class RotateArray {
    static void main() {
       int[] arr = {1, 2, 3, 4, 5};
       rotate(arr, 1);
        System.out.println(Arrays.toString(arr));
    }
    static void rotate(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // handle k > n

        // Reverse entire array
        reverse(arr, 0, n - 1);
        // Reverse first k elements
        reverse(arr, 0, k - 1);
        // Reverse remaining elements
        reverse(arr, k, n - 1);
    }

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }}
