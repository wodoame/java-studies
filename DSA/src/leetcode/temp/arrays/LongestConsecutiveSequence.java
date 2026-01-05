package leetcode.temp.arrays;

import java.util.HashSet;

public class LongestConsecutiveSequence {
      public int longestConsecutive(int[] nums) {
          if(nums == null || nums.length == 0) return 0;

          HashSet<Integer> set = new HashSet<>();
          for(int num: nums) set.add(num);

          int longest = 0;
          for(int num: set){
              // Only count if this is the END of a sequence
              if(!set.contains(num + 1)){
                  int count = 1;
                  int current = num;
                  while(set.contains(current - 1)){
                      count++;
                      current--;
                  }
                  longest = Math.max(count, longest);
              }
          }
          return longest;
    }
}
