package leetcode.temp.strings;

// TODO: leetcode 796
public class RotateString {
    public boolean rotateStringAlternateSolution(String s, String goal) {
        if(s.length()!=goal.length())return false;
        return (s + s).contains(goal);
    }
}
