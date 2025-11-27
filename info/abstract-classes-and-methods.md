Abstract classes and methods in Java are fundamental concepts used to achieve **abstraction** and define a **common blueprint** for subclasses.

## 🧱 Abstract Classes

An **abstract class** is a class that is declared with the `abstract` keyword.

  * **Cannot be instantiated:** You **cannot** create an object (instance) of an abstract class directly. It must be inherited by other classes.
  * **Purpose:** It is designed to be a **base class** or **template** for subclasses. It represents a general concept where some implementation details are left for the subclasses to provide.
  * **Contents:** It can contain a mix of:
      * **Abstract methods** (methods without a body, which we'll discuss next).
      * **Concrete methods** (regular methods with a body).
      * **Fields** (variables).
      * **Constructors** (though you can't instantiate the class, constructors are used by subclasses during their instantiation via `super()`).

> **Example:** A class named `Animal` could be abstract because the general concept of an "Animal" doesn't perform a specific `makeSound()`—you need a concrete animal like a `Dog` or `Cat` to do that.

```java
abstract class Vehicle {
    private String engineType;

    // Concrete method
    public void startIgnition() {
        System.out.println("Ignition started.");
    }

    // Abstract method (no body)
    public abstract void drive();
}
```

-----

## ⚙️ Abstract Methods

An **abstract method** is a method that is declared without an implementation (it has no method body, just a semicolon) and must be declared within an **abstract class**.

  * **Declaration:** It is declared using the `abstract` keyword.
  * **Signature Only:** It specifies the method signature (return type, name, and parameters) but not the actual code to be executed.
  * **Mandatory Overriding:** Any **concrete (non-abstract) class** that inherits the abstract class **must provide an implementation** (override) for *all* its abstract methods. If the subclass fails to implement all abstract methods, it must also be declared as abstract.
      * This forces a standard behavior onto all inheriting classes, ensuring they all implement a particular function, but allowing each to implement it uniquely.

> **Example:** In the `Vehicle` class above, the `drive()` method is abstract. Every subclass, like `Car` or `Motorcycle`, must implement its own specific `drive()` logic.

```java
class Car extends Vehicle {
    @Override
    public void drive() {
        // Implementation provided by the concrete subclass
        System.out.println("Car is driving on four wheels.");
    }
}

class Motorcycle extends Vehicle {
    @Override
    public void drive() {
        // Different implementation provided by another subclass
        System.out.println("Motorcycle is leaning into the turn.");
    }
}
```

-----

## 🎯 Key Differences and Purpose

| Feature | Abstract Class | Regular (Concrete) Class |
| :--- | :--- | :--- |
| **Instantiation** | Cannot be instantiated directly. | Can be instantiated. |
| **Purpose** | To be a base/template class for inheritance. | To be a fully functional class. |
| **Abstract Methods** | Can contain **zero or more** abstract methods. | Cannot contain any abstract methods. |
| **Inheritance Rule** | A non-abstract subclass **must** implement all abstract methods. | Inheritance is optional and doesn't mandate method implementation (unless overriding). |

The main purpose is to **enforce a contract** among related classes while providing a certain level of common functionality. It is a key tool in Java's support for **Polymorphism**, allowing you to treat objects of different concrete types uniformly through their common abstract base class.

Would you like to see a full runnable code example demonstrating abstract classes and methods?
