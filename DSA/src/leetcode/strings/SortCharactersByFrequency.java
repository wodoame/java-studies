package leetcode.temp.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// leetcode 451
public class SortCharactersByFrequency {
    static void main() {
        System.out.println(frequencySort("cccaaa"));
    }
    public static String frequencySort(String s) {
        HashMap<Character, Integer> freqs = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int count = freqs.getOrDefault(current, 0);
            freqs.put(current, count + 1);
        }

        List<Character>[] groups = new ArrayList[n + 1];
        for (var entry: freqs.entrySet()) {
            var group = groups[entry.getValue()];
            if(group==null)group = new ArrayList<>();
            group.add(entry.getKey());
            groups[entry.getValue()] = group;
        }
        StringBuilder res = new StringBuilder();
        for (int i = groups.length - 1; i >= 0 ; i--) {
           var group = groups[i];
           if(group != null){
               for(char c: group)res.append(Character.toString(c).repeat(i));
           }
        }
     return res.toString();
    }
}
