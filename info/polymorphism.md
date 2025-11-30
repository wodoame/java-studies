Polymorphism, in the context of Object-Oriented Programming (OOP), is one of the four fundamental principles (along with **Encapsulation**, **Inheritance**, and **Abstraction**). The word itself comes from Greek, meaning **"many forms."**

In Java, polymorphism allows an object to take on **multiple forms** or, more accurately, allows a reference variable of a superclass type to refer to an object of any of its subclasses. This enables you to treat objects of different classes in a uniform way.

There are two main types of polymorphism in Java:

-----

## 1\. Compile-Time Polymorphism (Static Polymorphism)

This type is achieved through **Method Overloading**.

  * **Definition:** Method Overloading occurs when a class has **multiple methods** with the **same name** but **different parameter lists** (i.e., different number of parameters or different data types of parameters). The correct method to call is determined by the **compiler** at **compile time**.
  * **Example (Java):**

<!-- end list -->

```java
class Calculator {
    // Method 1: Takes two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method 2: Takes three integers (Different number of parameters)
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method 3: Takes two doubles (Different data types)
    public double add(double a, double b) {
        return a + b;
    }
}

public class StaticPolyDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("Sum of 5 and 10: " + calc.add(5, 10));         // Calls Method 1
        System.out.println("Sum of 5.5 and 10.5: " + calc.add(5.5, 10.5)); // Calls Method 3
    }
}
```

-----

## 2\. Run-Time Polymorphism (Dynamic Polymorphism)

This type is achieved through **Method Overriding**.

  * **Definition:** Method Overriding occurs when a subclass provides a **specific implementation** for a method that is already defined in its superclass. The decision of which method to call (the superclass's or the subclass's) is made at **run time** based on the actual type of the object being referenced. This is essential for achieving **dynamic method dispatch**.
  * **Key Concept: The Parent Reference**
    The most powerful aspect of run-time polymorphism is using a **superclass reference** to hold a **subclass object**.
  * **Example (Java):**

<!-- end list -->

```java
// Superclass
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// Subclass 1
class Dog extends Animal {
    // Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("Dog barks.");
    }
}

// Subclass 2
class Cat extends Animal {
    // Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("Cat meows.");
    }
}

public class DynamicPolyDemo {
    public static void main(String[] args) {
        // Animal is the superclass reference type
        Animal myAnimal1 = new Dog(); // myAnimal1 holds a Dog object
        Animal myAnimal2 = new Cat();  // myAnimal2 holds a Cat object
        Animal myAnimal3 = new Animal(); // myAnimal3 holds an Animal object

        // The method call is the same (makeSound()), 
        // but the actual behavior (the form) is different at runtime.
        myAnimal1.makeSound(); // Output: Dog barks. (Calls Dog's method)
        myAnimal2.makeSound(); // Output: Cat meows. (Calls Cat's method)
        myAnimal3.makeSound(); // Output: Animal makes a sound. (Calls Animal's method)
    }
}
```

-----

## Summary Table

| Feature | Compile-Time (Overloading) | Run-Time (Overriding) |
| :--- | :--- | :--- |
| **Also Known As** | Static Polymorphism | Dynamic Polymorphism |
| **Mechanism** | Method Overloading | Method Overriding |
| **Decision Time** | Compile Time | Run Time |
| **Signatures** | Same name, **different** parameters | Same name, **same** parameters |
| **Scope** | Within **one class** | Between **two classes** (Superclass/Subclass) |

Would you like to explore how **Interfaces** and **Abstract Classes** enable run-time polymorphism even further?
