import java.util.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
      List<String> names = Arrays.asList("Zebra", "Apple", "Mango");
      // Sort by length instead of alphabetical order
      names.sort(Comparator.comparingInt(String::length));}
}
