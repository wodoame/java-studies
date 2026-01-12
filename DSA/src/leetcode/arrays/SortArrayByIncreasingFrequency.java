package leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SortArrayByIncreasingFrequency {
    static void main() {
        int[] nums = {1,1,2,2,2,3};
        System.out.println("Original: " + Arrays.toString(nums));
        System.out.println("frequencySort (bucket): " + Arrays.toString(frequencySort(nums.clone())));
        System.out.println("frequencySortUsingComparator: " + Arrays.toString(frequencySortUsingComparator(nums.clone())));
        System.out.println("frequencySortUsingPriorityQueue: " + Arrays.toString(frequencySortUsingPriorityQueue(nums.clone())));
    }
    public static int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> freqs = new HashMap<>();
        ArrayList<Integer>[] freqGroups = new ArrayList[nums.length + 1];
        for(int num: nums){
            int count = freqs.getOrDefault(num, 0);
            freqs.put(num, count + 1);
        }
       for(var freq: freqs.entrySet()){
           var group = freqGroups[freq.getValue()];
           if(group == null){
               ArrayList<Integer> newGroup = new ArrayList<>();
               newGroup.add(freq.getKey());
               group = newGroup;
           }else group.add(freq.getKey());
           freqGroups[freq.getValue()] = group;
       }
       int i = 0;
       for(var group: freqGroups){
           if(group != null) {
               group.sort((a, b) -> b - a);
               for(int num: group){
                   for (int j = 0; j < freqs.get(num); j++) {
                       nums[i++] = num;
                   }
               }
           }
       }
       return nums;
    }


    public static int[] frequencySortUsingComparator(int[] nums) {
        HashMap<Integer, Integer> freqs = new HashMap<>();
        // Build frequency map
        for(int num: nums){
            int count = freqs.getOrDefault(num, 0);
            freqs.put(num, count + 1);
        }

        // Convert to Integer[] to use Comparator
        Integer[] boxedNums = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++){
            boxedNums[i] = nums[i];
        }

        // Sort by frequency (ascending), then by value (descending) for same frequency
        Arrays.sort(boxedNums, (a, b) -> {
            if (freqs.get(a).equals(freqs.get(b))) {
                return Integer.compare(b, a); // Descending order for values with same frequency
            }
            return Integer.compare(freqs.get(a), freqs.get(b)); // Ascending order by frequency
        });

        // Convert back to int[]
        for(int i = 0; i < boxedNums.length; i++){
            nums[i] = boxedNums[i];
        }

        return nums;
    }

    /**
     * Solution using Priority Queue (Min-Heap)
     *
     * Priority Queue automatically maintains sorted order based on the comparator.
     * This is elegant because:
     * - The heap handles sorting for us as we add elements
     * - We only need to poll() elements in order to build the result
     * - Time: O(n log n) for heap operations
     * - Space: O(n) for the heap and frequency map
     *
     * This approach is cleaner than sorting the entire array when you need
     * to process elements in a specific order.
     */
    public static int[] frequencySortUsingPriorityQueue(int[] nums) {
        // Build frequency map
        HashMap<Integer, Integer> freqs = new HashMap<>();
        for (int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        // Create a min-heap (priority queue) with custom comparator
        // Sort by frequency (ascending), then by value (descending)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int freqCompare = Integer.compare(freqs.get(a), freqs.get(b));
            if (freqCompare != 0) {
                return freqCompare; // Ascending by frequency
            }
            return Integer.compare(b, a); // Descending by value for same frequency
        });

        // Add all unique numbers to the priority queue
        pq.addAll(freqs.keySet());

        // Build result by polling from priority queue
        int i = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            int frequency = freqs.get(num);
            // Add the number 'frequency' times to the result
            for (int j = 0; j < frequency; j++) {
                nums[i++] = num;
            }
        }

        return nums;
    }
}
