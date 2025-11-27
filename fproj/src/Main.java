public class Main {
    public static void main(String[] args) {
        Array numbers = new Array();
        numbers.insert(10);
        numbers.insert(20);
        numbers.insert(30);
        numbers.insert(40);
        numbers.insert(50);

        System.out.println("Numbers: " + numbers);
        System.out.println("Item at index 2: " + numbers.getItem(2));
        numbers.removeAt(1);
        System.out.println("After removing index 1: " + numbers);
        System.out.println("Index of 40: " + numbers.indexOf(40));

        String sample = "abracadabra";
        char mostFrequent = Array.getMaximumOccurringCharacter(sample);
        System.out.println("Most frequent char in '" + sample + "' is '" + mostFrequent + "'");
    }
}