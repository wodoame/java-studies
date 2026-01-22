package assessment;

import java.util.*;

public class ParkingSpaceAllocation {

    record Result(Map<String, String> assignments, int wastedSpace) {
        int count() { return assignments.size(); }
    }

    public static void main(String[] args) {
        Map<String, Integer> spaces = Map.of("P1", 15, "P2", 20, "P3", 25, "P4", 10);
        Map<String, Integer> cars = Map.of("V1", 22, "V2", 18, "V3", 10, "V4", 8);

        Result res1 = solveTwoPointer(new HashMap<>(spaces), new HashMap<>(cars));
        Result res2 = solveBestFit(new HashMap<>(spaces), new HashMap<>(cars));

        // Comparison Logic
        Result winner;
        if (res1.count() > res2.count()) {
            winner = res1;
        } else if (res2.count() > res1.count()) {
            winner = res2;
        } else {
            // If counts are equal, pick the one with less waste
            winner = (res1.wastedSpace() <= res2.wastedSpace()) ? res1 : res2;
        }

        System.out.println("--- Final Selection ---");
        System.out.println("Strategy Used: " + (winner == res1 ? "Two-Pointer" : "Best-Fit"));
        System.out.println("Total Parked: " + winner.count());
        System.out.println("Total Wasted: " + winner.wastedSpace());
        System.out.println("Assignments: " + winner.assignments());
    }

    static Result solveTwoPointer(Map<String, Integer> spaces, Map<String, Integer> cars) {
        var sortedSpaces = new ArrayList<>(spaces.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList());
        var sortedCars = cars.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();

        Map<String, String> assignments = new HashMap<>();
        int carIdx = 0, spaceIdx = 0;
        int usedCapacity = 0;

        while (carIdx < sortedCars.size() && spaceIdx < sortedSpaces.size()) {
            if (sortedSpaces.get(spaceIdx).getValue() >= sortedCars.get(carIdx).getValue()) {
                assignments.put(sortedSpaces.get(spaceIdx).getKey(), sortedCars.get(carIdx).getKey());
                usedCapacity += sortedCars.get(carIdx).getValue();
                carIdx++;
            }
            spaceIdx++;
        }
        int totalCap = spaces.values().stream().mapToInt(i -> i).sum();
        return new Result(assignments, totalCap - usedCapacity);
    }

    static Result solveBestFit(Map<String, Integer> spaces, Map<String, Integer> cars) {
        TreeMap<Integer, Stack<String>> spaceMap = new TreeMap<>();
        spaces.forEach((id, size) -> spaceMap.computeIfAbsent(size, k -> new Stack<>()).push(id));

        var sortedCars = cars.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        Map<String, String> assignments = new HashMap<>();
        int usedCapacity = 0;

        for (var car : sortedCars) {
            Integer bestFitSize = spaceMap.ceilingKey(car.getValue());
            if (bestFitSize != null) {
                String spaceId = spaceMap.get(bestFitSize).pop();
                if (spaceMap.get(bestFitSize).isEmpty()) spaceMap.remove(bestFitSize);

                assignments.put(spaceId, car.getKey());
                usedCapacity += car.getValue();
            }
        }
        int totalCap = spaces.values().stream().mapToInt(i -> i).sum();
        return new Result(assignments, totalCap - usedCapacity);
    }
}