import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }
    public static int findNumbers(int[] nums) {
        int count = 0;
        for(int num: nums){
            int numDigits = 0;
            int rem = num;
            while(rem > 0){
                numDigits++;
                rem = rem / 10; // repeated division by 10 can be replaced with a logarithm to tbe base of 10 (But this works fine too)
            }
            if(numDigits == 2){
                count++;
            }
}
        return count;
    }

    public static int[] sortedSquares(int[] nums){
            int n = nums.length;
            int l = 0;
            int r = n - 1;
            int idx = n - 1;
            int[] res = new int[n];

            while(l <= r){
                int leftSq = nums[l] * nums[l];
                int rightSq = nums[r] * nums[r];
                if(leftSq > rightSq){
                    res[idx--] = leftSq;
                    l++;
                }
                else{
                    res[idx--] = rightSq;
                    r--;
                }
            }
        return res;
    }
}
