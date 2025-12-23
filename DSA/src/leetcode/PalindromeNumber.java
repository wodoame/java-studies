package leetcode;

public class PalindromeNumber {
    static void main() {

    }

    public static boolean isPalindrome(int x){
        return x >= 0 && x == reverse(x);
    }

    static int reverse(int x){
        int reversed = 0;
        while(x != 0) {
            int lastDigit = x % 10; // this formula effectively gets us the last digit of the number x
            reversed = reversed * 10 + lastDigit; // this effectively makes us concatenate the last digit to the reversed digits
            x /= 10; // this effectively removes the last digit from the number x
        }
        return reversed;
    }
}
