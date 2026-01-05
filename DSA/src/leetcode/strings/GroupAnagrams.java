package leetcode.temp.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    static void main() {
        System.out.println(groupAnagrams(new String[]{"tea", "eat", "rat", "cat", "act"}));
    }
    //
   public static List<List<String>> groupAnagrams(String[] strs) {
       HashMap<String, List<String>> groups = new HashMap<>();
       for(String word: strs){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> group = groups.getOrDefault(key, new ArrayList<>());
            group.add(word);
            groups.put(key, group);
       }
       return new ArrayList<>(groups.values());
    }
}
