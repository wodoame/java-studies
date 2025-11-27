import java.util.Arrays;

//Task
// Create a custom implementation of an array data structure in Java
// The array should be dynamic

// Features
// Be able to insert an element (insert method)
// Be able to get an element by index (getItem method)
// Be able to remove an element by index (removeAt method)
// toString method to display the contents of the array
// indexOf method to find the index of an element

// Task 2
// Implement a getMaximumOccurringCharacter method that returns
// the character that appears most frequently in a given string

public class Array {
    private static final int DEFAULT_CAPACITY = 4;
    private int[] items;
    private int count;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");
        items = new int[capacity];
    }

    public void insert(int item) {
        ensureExtraCapacity();
        items[count++] = item;
    }

    public int getItem(int index) {
        validateIndex(index);
        return items[index];
    }

    public void removeAt(int index) {
        validateIndex(index);
        for (int i = index; i < count - 1; i++)
            items[i] = items[i + 1];
        items[--count] = 0; // clear stale slot
    }

    public int indexOf(int item) {
        for (int i = 0; i < count; i++)
            if (items[i] == item)
                return i;
        return -1;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, count));
    }

    private void ensureExtraCapacity() {
        if (count == items.length)
            items = Arrays.copyOf(items, Math.max(1, items.length * 2));
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("Index out of range: " + index);
    }

    public static char getMaximumOccurringCharacter(String input) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("Input string must not be null or empty");

        int[] frequencies = new int[Character.MAX_VALUE + 1];
        char bestChar = input.charAt(0);
        int maxCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            int newCount = ++frequencies[current];
            if (newCount > maxCount) {
                maxCount = newCount;
                bestChar = current;
            }
        }

        return bestChar;
    }
}
