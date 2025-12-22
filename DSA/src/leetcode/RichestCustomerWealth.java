package leetcode;

// leetcode: 1672
public class RichestCustomerWealth {
    static void main() {
        int[][] accounts = {{1,2,3} ,{3,2,1}};
        System.out.println(maximumWealth(accounts));
    }

    static int maximumWealth(int[][] accounts){
        int max = 0;
        for(var account: accounts){
            max = Math.max(max, sumWealth(account));
        }
    return max;
    }

    static int sumWealth(int[] account){
        int sum = 0;
        for (int j : account) {
            sum += j;
        }
     return sum;
    }

}
