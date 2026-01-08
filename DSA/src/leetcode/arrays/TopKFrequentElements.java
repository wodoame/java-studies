package leetcode.arrays;

import java.util.*;

// leetcode 347
public class TopKFrequentElements {
    static void main() {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 2, 3 , 3}, 2)));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int n: nums) freq.put(n, freq.getOrDefault(n, 0) + 1);
        List<Integer>[] groups = new ArrayList[nums.length + 1];
        for(int num : freq.keySet()) {
            var group = groups[freq.get(num)];
            if(group == null)group = new ArrayList<>();
            group.add(num);
            groups[freq.get(num)] = group;
        }

        int[] result = new int[k];
        int index = 0;
        for(int i = groups.length - 1; i >= 0 && index < k; i--) {
            var group = groups[i];
            if(group != null){
                for(int num : group) {
                    result[index++] = num;
                    if(index == k) break;
                }
            }
        }

        return result;
    }
}
