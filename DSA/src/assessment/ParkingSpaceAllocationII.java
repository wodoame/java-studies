package assessment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingSpaceAllocationII {
    static void main() {
        System.out.println("Enter inputs");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String line2 = scanner.nextLine();
        String line3 = scanner.nextLine();
        Map<String, Integer> spaces = InputParser.getMap(line2);
        Map<String, Integer> cars = InputParser.getMap(line3);
        allocateSpace(spaces, cars);
    }

    static void allocateSpace(Map<String, Integer> spaces, Map<String, Integer> cars){
        TreeMap<Integer, List<String>> spaceMap = new TreeMap<>();
        Map<String, String> assignments = new TreeMap<>();
        spaces.forEach((id, size)->{
            List<String> spaceArray = spaceMap.get(size);
            if(spaceArray == null)spaceMap.put(size, new ArrayList<>(List.of(id)));
            else spaceArray.add(id);
        });
        var sortedCars = cars.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toList();
//        for (var car: sortedCars){
            // TODO: continue implementation
//        }
    }
}



class InputParser{
    static Map<String, Integer> getMap(String line){
        String[] string =  line.split(" ");
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        while(i < string.length){
            map.put(string[i], Integer.parseInt(string[i + 1]));
            i += 2;
        }
        return map;
    }
}
