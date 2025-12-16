void main() {
    // Use stream to avoid mutating the original lists
    // A stream cannot be reused after it has been operated upon or closed

    // Questions:
    // What is a functional interface?
    // A functional interface is an interface that has exactly one abstract method.
    // They can have multiple default or static methods, but only one abstract method.
    // Functional interfaces are used as the basis for lambda expressions and method references in Java.

    // What is a predicate?
    // A predicate is a functional interface in Java that represents a single argument function
    // that returns a boolean value. It is often used for filtering or matching conditions.
    // The Predicate interface has a single abstract method called test, which takes an object as a parameter
    // and returns true or false based on some condition.

    // What is a comparator?
    // A comparator is a functional interface in Java that defines a method for comparing two objects.
    // It is used to impose a total ordering on a collection of objects.
    // The Comparator interface has a single abstract method called compare, which takes two objects as parameters
    // and returns an integer indicating their relative order.

    // Some terms
    // Anonymous inner class


    List<String> names = Arrays.asList("Bernard", "Alice", "John", "Diana", "Eve", "Frank");
    List<Integer> ages = Arrays.asList(12, 25, 30, 18, 45, 50);
    int sumAges = ages.stream().reduce(0, Integer::sum);
    System.out.println("Sum of ages: " + sumAges);
}
