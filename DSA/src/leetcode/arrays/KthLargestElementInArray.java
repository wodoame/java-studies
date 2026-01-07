package leetcode.arrays;

import java.util.PriorityQueue;

public class KthLargestElementInArray {

    static void main() {
       int[] nums = {1, 2, 3, 3};
        System.out.println(findKthLargest(nums, 2));
    }

    // Using Min-Heap approach: O(n log k) time, O(k) space
    // Maintain a min-heap of size k with the k largest elements
    // The root of the heap will be the kth largest element
    public static int findKthLargest(int[] nums, int k) {
        // Min-heap to keep track of k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            // Keep only k largest elements in the heap
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element
            }
        }

        // The root of min-heap is the kth largest element
        return minHeap.isEmpty() ? -1 : minHeap.peek();
    }
}
