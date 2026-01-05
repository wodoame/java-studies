import java.util.HashSet;

public class test_longest {
    public static int longestConsecutive(int[] nums) {
        // [100, 4, 200, 1, 3, 2]
        if(nums == null || nums.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);

        int longest = 0;
        for(int num: nums){
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

    public static void main(String[] args) {
        int[] test1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Test 1: " + longestConsecutive(test1) + " (expected 4)");
        
        int[] test2 = {0, 3, 7,2,5,8,4,6,0,1};
        System.out.println("Test 2: " + longestConsecutive(test2) + " (expected 9)");
        
        int[] test3 = {};
        System.out.println("Test 3: " + longestConsecutive(test3) + " (expected 0)");
    }
}

