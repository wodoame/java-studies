package leetcode.twopointers;

public class BestTimeToBuyAndSellStock {
    static void main() {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
    public static int maxProfit(int[] prices) {
        int left = 0;
        int max = 0;
        for(int i=1; i < prices.length; i++){
            int price = prices[i];
            if(price < prices[left]){
                left = i;
            }
            else max = Math.max(price - prices[left], max);
        }
        return max;
    }
}
