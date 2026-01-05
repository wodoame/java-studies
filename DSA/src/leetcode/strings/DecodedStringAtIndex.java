package leetcode.temp.strings;

public class DecodedStringAtIndex {
    static void main() {
        System.out.println(decodeAtIndex("leet2code3", 10));
    }
    public static String decodeAtIndex(String s, int k) {
        int totalLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i)))totalLength++;
            else totalLength *= s.charAt(i) - '0';
        }
        int j = s.length() - 1;
        while(j >= 0){
            int current = s.charAt(j);
            if(Character.isLetter(current)){
                if(totalLength % k == 0)return Character.toString(current);
                totalLength--;
            }
            else{
                totalLength /= current - '0';
                k %= totalLength;
            }
            j--;
        }

        System.out.println(totalLength);
        return "";
    }
}
