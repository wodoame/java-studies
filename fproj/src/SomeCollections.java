import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
public class SomeCollections {
    public static void main(String[] args) {
        // Example of using a List
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("List: " + list);
        System.out.println("list contains Banana: " + list.contains("Banana"));

        // Example of using a Set
        Set<String> set = new HashSet<>();
        set.add("Dog");
        set.add("Cat");
        System.out.println("Set: " + set);
        System.out.println("set contains Banana: " + set.contains("Banana"));

        // Example of using a Map
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        System.out.println("Map: " + map);
    }
}
