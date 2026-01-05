package leetcode.temp.strings;

public class DecodedStringAtIndex {
    static void main() {
        System.out.println(decodeAtIndex("t2", 1));
    }
    public static String decodeAtIndex(String s, int k) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if(Character.isDigit(current)){
                int repeatCount = current - '0'; // Convert char digit to numeric value
                buffer = new StringBuilder(buffer.toString().repeat(repeatCount));
            }else buffer.append(current);
        }
        System.out.println(buffer);
        return Character.toString(buffer.charAt(k - 1));
    }
}
