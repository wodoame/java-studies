**Object-Oriented Programming (OOP)** is the fundamental paradigm in Java. It's a way of designing software using **classes** and **objects**.

Here is a tutorial covering the four main pillars of Java OOP: **Encapsulation**, **Inheritance**, **Polymorphism**, and **Abstraction**.

-----

## 1\. 🛡️ Encapsulation

Encapsulation is the mechanism of **bundling the data (variables) and the methods (functions) that operate on the data into a single unit, the class**. It also involves **restricting direct access** to some of an object's components.

### **Key Concepts**

  * **Data Hiding:** Making the instance variables of a class `private`. This prevents external code from directly modifying them.
  * **Accessors/Mutators (Getters and Setters):** Providing `public` methods to **read** (get) and **write** (set) the private variables. This gives you **control** over how the data is accessed and modified.

### **Example**

```java
public class Student {
    // 1. Data is made private (Data Hiding)
    private String name;
    private int age;

    // Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 2. Public Getters (Accessors) to read the data
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 3. Public Setter (Mutator) to modify the data with validation
    public void setAge(int newAge) {
        if (newAge > 0) { // Validation logic
            this.age = newAge;
        } else {
            System.out.println("Age must be positive.");
        }
    }
}
```

-----

## 2\. 🧬 Inheritance

Inheritance is the mechanism where one class **acquires the properties and behavior (fields and methods)** of another class. The class that inherits is called the **subclass** (or *child* class), and the class being inherited from is the **superclass** (or *parent* class).

### **Key Concepts**

  * **`extends` Keyword:** Used to make a class inherit from another class.
  * **Code Reusability:** Common fields and methods are defined in the superclass, so they don't need to be written again in every subclass.
  * **"Is-A" Relationship:** Inheritance models the "Is-A" relationship. (e.g., A Dog **Is-A** Animal).

### **Example**

```java
// Superclass (Parent)
public class Animal {
    public void eat() {
        System.out.println("The animal is eating.");
    }
}

// Subclass (Child)
public class Dog extends Animal {
    public void bark() {
        System.out.println("The dog barks.");
    }
}

// Usage
// Dog dog = new Dog();
// dog.eat(); // Inherited method from Animal
// dog.bark(); // Own method
```

-----

## 3\. 🔄 Polymorphism

Polymorphism means **"many forms."** In Java OOP, it allows an object to take on multiple forms, primarily through a **single interface** or method name that can perform different actions depending on the context.

### **Types of Polymorphism**

#### **A. Compile-time Polymorphism (Method Overloading)**

  * Defining **multiple methods** in the **same class** with the **same name** but different **parameter lists** (number or type of arguments).

#### **B. Runtime Polymorphism (Method Overriding)**

  * Defining a method in the **subclass** that has the **same signature** (name and parameters) as a method in its **superclass**.
  * The subclass's version of the method is executed (overrides) the superclass's version when called on a subclass object.

### **Example**

```java
// Method Overriding (Runtime Polymorphism)
public class Cat extends Animal { // Animal is the parent class from the Inheritance section
    @Override // Annotation is optional, but good practice
    public void eat() {
        // Overrides the eat() method from the Animal class
        System.out.println("The cat is hunting and eating.");
    }
}

// Method Overloading (Compile-time Polymorphism)
public class Calculator {
    public int add(int a, int b) { // Method 1
        return a + b;
    }
    
    public double add(double a, double b) { // Method 2 - Different parameter types
        return a + b;
    }
}
```

-----

## 4\. 👻 Abstraction

Abstraction is the concept of **showing only essential information** to the user and **hiding the internal complexity** and implementation details.

### **Key Concepts**

  * **Focus on *What*:** The user only needs to know *what* the object does, not *how* it does it.
  * **Achieved via:**
      * **Abstract Classes:** Classes that contain one or more **abstract methods** (methods without a body/implementation). They cannot be instantiated directly. Subclasses *must* provide the implementation for all abstract methods.
      * **Interfaces:** Blueprints of a class. They define a contract consisting of a set of public abstract methods (and constants). A class that `implements` an interface must provide the implementation for all of its methods.

### **Example (Abstract Class)**

```java
// Abstract Class (cannot be instantiated)
public abstract class Shape {
    private String color;
    
    // Abstract method (no body) - must be implemented by subclasses
    public abstract double calculateArea(); 
    
    // Concrete method (with body)
    public void displayColor() {
        System.out.println("Color is: " + color);
    }
}

public class Circle extends Shape {
    private double radius;
    
    // Must implement the abstract method
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

This four-pillar structure forms the core of programming effectively in Java.

-----

Would you like to see a practical example of how all four concepts work together in a single application?
