package leetcode.arrays;

public class MaximumProductSubarray {
    static void main() {
        System.out.println(maxProduct(new int[]{-4, -3, -2}));
    }
    public static int maxProduct(int[] nums) {
        int maxProd = nums[0];
        int minProd = nums[0];
        int answer = maxProd;
        for (int i  = 1; i < nums.length; i++) {
            int current = nums[i];
            if(current < 0){
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(current, maxProd * current);
            minProd = Math.min(current, minProd * current);
            answer = Math.max(answer, maxProd);
        }
        return answer;
    }
}
