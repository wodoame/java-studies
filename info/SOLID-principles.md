The **SOLID principles** are five design principles intended to make software designs more **understandable, flexible, and maintainable**. They are a cornerstone of object-oriented programming (OOP).

Here's an explanation of each principle, with examples in Java:

-----

## 1\. Single Responsibility Principle (SRP) 🎯

The **Single Responsibility Principle** states that a class should have **only one reason to change**. This means a class should only have a single job or responsibility.

### Example (Bad Practice ❌)

A class handling both user management and report generation violates SRP.

```java
// Violates SRP
class UserManagement {
    public void createUser(String username) {
        // Code to save user to the database
    }

    public void generatePDFReport(String username) {
        // Code to format and generate a PDF report
    }
}
```

### Example (Good Practice ✅)

Separate the responsibilities into different classes.

```java
// Responsibility 1: User persistence
class UserService {
    public void createUser(String username) {
        // Code to save user to the database
    }
}

// Responsibility 2: Reporting
class ReportGenerator {
    public void generatePDFReport(String username) {
        // Code to format and generate a PDF report
    }
}
```

-----

## 2\. Open/Closed Principle (OCP) 🔐

The **Open/Closed Principle** states that software entities (classes, modules, functions, etc.) should be **open for extension, but closed for modification**. You should be able to add new functionality without changing existing, working code.

### Example (Bad Practice ❌)

Adding a new shape requires modifying the `AreaCalculator` class.

```java
// Violates OCP
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            return rect.width * rect.height;
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        return 0;
    }
}
```

### Example (Good Practice ✅)

Use an **interface** to allow new shapes to be added without changing the `AreaCalculator`.

```java
// 1. Abstraction (Open for extension)
interface Shape {
    double calculateArea();
}

// 2. Concrete implementations
class Rectangle implements Shape {
    public double width, height;
    public double calculateArea() {
        return width * height;
    }
}

class Circle implements Shape {
    public double radius;
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// 3. Calculator (Closed for modification)
class AreaCalculator {
    public double calculateArea(Shape shape) {
        // Works for any Shape implementation!
        return shape.calculateArea();
    }
}
```

-----

## 3\. Liskov Substitution Principle (LSP) 🤝

The **Liskov Substitution Principle** states that objects of a **superclass** should be replaceable with objects of their **subclasses** without breaking the application. Essentially, a subtype must not change the expected behavior of its supertype.

### Example (Bad Practice ❌)

Imagine a `Bird` class with a `fly()` method. If you create an `Ostrich` subclass that throws an exception in `fly()`, it violates LSP because you cannot substitute an `Ostrich` where a `Bird` is expected to fly.

A common classic violation is the Square-Rectangle problem:

```java
// Violates LSP: If Square inherits from Rectangle, setting the width
// will also change the height, which is an unexpected side effect for a Rectangle.
class Rectangle {
    protected int width, height;
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        this.width = this.height = w; // Unexpected behavior for Rectangle clients
    }
    @Override
    public void setHeight(int h) {
        this.width = this.height = h; // Unexpected behavior for Rectangle clients
    }
}
```

### Example (Good Practice ✅)

Use interfaces or abstract classes to define common, consistent behaviors, and ensure subtypes only extend, not restrict, the supertype's contract.

```java
interface CanWalk {
    void walk();
}

interface CanFly {
    void fly();
}

class Bird implements CanWalk, CanFly {
    public void walk() { /* ... */ }
    public void fly() { /* ... */ }
}

class Ostrich implements CanWalk {
    public void walk() { /* ... */ }
    // Ostrich doesn't implement CanFly, so it can't be mistakenly substituted
    // where a flying bird is expected.
}
```

-----

## 4\. Interface Segregation Principle (ISP) 🔪

The **Interface Segregation Principle** states that **clients should not be forced to depend on interfaces they do not use**. It's better to have many small, specific interfaces (segregated) rather than one large, 'fat' interface.

### Example (Bad Practice ❌)

A single large interface forces simple devices to implement methods they don't use.

```java
// Violates ISP
interface MultiFunctionDevice {
    void print(String document);
    void scan(String document);
    void fax(String document);
}

// A simple printer only needs print(), but is forced to implement scan() and fax().
class SimplePrinter implements MultiFunctionDevice {
    public void print(String document) { /* ... */ }
    public void scan(String document) { /* throws UnsupportedOperationException */ }
    public void fax(String document) { /* throws UnsupportedOperationException */ }
}
```

### Example (Good Practice ✅)

Break the large interface into smaller, role-specific interfaces.

```java
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
```

-----

## 5\. Dependency Inversion Principle (DIP) 🔄

The **Dependency Inversion Principle** states that:

1.  **High-level modules should not depend on low-level modules.** Both should depend on **abstractions** (interfaces or abstract classes).
2.  **Abstractions should not depend on details.** Details (concrete implementations) should depend on **abstractions**.

This principle is often achieved through **Dependency Injection**.

### Example (Bad Practice ❌)

The high-level `Application` class is directly dependent on the low-level, concrete `MySQLDatabase` class.

```java
// Violates DIP: High-level Application depends directly on Low-level MySQLDatabase
class MySQLDatabase { // Low-level module
    public void save(String data) {
        System.out.println("Saving data to MySQL: " + data);
    }
}

class Application { // High-level module
    private MySQLDatabase database = new MySQLDatabase(); // Direct dependency
    public void doSomething() {
        database.save("Important configuration");
    }
}
```

### Example (Good Practice ✅)

Introduce an **abstraction** (interface) that both the high-level and low-level modules depend on.

```java
// 1. Abstraction (Interface)
interface Database {
    void save(String data);
}

// 2. Low-level module depends on Abstraction
class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving data to MySQL: " + data);
    }
}

// 3. High-level module depends on Abstraction
class Application {
    private final Database database;

    // Dependency Injection via constructor
    public Application(Database database) {
        this.database = database; // Database is an abstraction
    }

    public void doSomething() {
        database.save("Important configuration");
    }
}

// Usage: The concrete implementation is injected from the outside
class Main {
    public static void main(String[] args) {
        Database mySql = new MySQLDatabase();
        Application app = new Application(mySql);
        app.doSomething();
    }
}
```

By following DIP, you can easily swap out `MySQLDatabase` for, say, `PostgresDatabase` without changing the `Application` class, making your code much more flexible and testable.
