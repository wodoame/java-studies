public class BestPCodeQ {
    void main(){
        // SOLID principles
        // 1. Single Responsibility Principle: A class should have only one reason to change, meaning it should have only one job or responsibility.
        // 2. Open/Closed Principle: Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
        // 3. Liskov Substitution Principle: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
        // 4. Interface Segregation Principle: Clients should not be forced to depend on interfaces they do not use.
        // Instead of one large interface, many small, specific interfaces are preferred.
        // 5. Dependency Inversion Principle: High-level modules should not depend on low-level modules.
        // Both should depend on abstractions. Abstractions should not depend on details.
        // Details should depend on abstractions.



    }
}
interface Printer {
    void print(String document);
}

interface Scanner {
    void scan(String document);
}

interface FaxMachine {
    void fax(String document);
}

// Now the simple printer only depends on what it actually uses.
class SimplePrinter implements Printer {
    public void print(String document) { /* ... */ }
}

// A full-fledged device implements all necessary interfaces.
class AllInOneDevice implements Printer, Scanner, FaxMachine {
    public void print(String document) { /* ... */ }
    public void scan(String document) { /* ... */ }
    public void fax(String document) { /* ... */ }
}