package assessment;

import java.util.*;

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
        var sortedSpaces = spaces.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        var sortedCars = cars.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        System.out.println(sortedSpaces);
        Map<String, String> assignments = new HashMap<>();
        int wastedSpace = 0;
        int carIdx = 0;
        int spaceIdx = 0;
        while(carIdx < cars.size() && spaceIdx < spaces.size()){
            Map.Entry<String, Integer> space = sortedSpaces.get(spaceIdx);
            Map.Entry<String, Integer> car = sortedCars.get(carIdx);
            int spaceValue = space.getValue();
            int carValue = car.getValue();

            if(spaceValue >= carValue){
               wastedSpace += spaceValue - carValue;
               assignments.put(car.getKey(), space.getKey());
               carIdx++;
            }else{
                wastedSpace += space.getValue();
            }
            spaceIdx++;
        }
        System.out.println("Parked cars = " + assignments.size());
        System.out.println("Wasted space = " + wastedSpace);
        assignments.forEach((k, v)-> System.out.print(k + " -> " + v + " "));
    }

    static void allocateSpaceBestFit(Map<String, Integer> spaces, Map<String, Integer> cars){
        Map<Integer, Stack<String>> spaceMap = new TreeMap<>();
        spaces.forEach((id, size)->spaceMap.computeIfAbsent(size, k-> new Stack<>()).push(id));
        var sortedCars = cars.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toList();

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
