The primary difference between an **Interface** and an **Abstract Class** in Java lies in their ability to provide **implementation details** and the way they enforce the class structure, particularly concerning **multiple inheritance**.

Both are crucial tools for achieving **abstraction** and defining a **contract** for subclasses/implementing classes, but they operate under different rules.

---

## Key Differences

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Type of Methods** | Can have **Abstract methods** (no body) AND **Concrete methods** (with body). | Primarily contains **Abstract methods** (no body). *Can* have `default` and `static` methods (with body) since Java 8. |
| **Inheritance** | A class can only **extend** a **single** abstract class (**Single Inheritance**). | A class can **implement** **multiple** interfaces (**Multiple Inheritance** of behavior). |
| **Instance Variables** | Can have **instance variables** (`final`, non-`final`, `static`, non-`static`). | Can only have **constants** (`public static final` by default). |
| **Constructors** | Can have **constructors**. These are used by subclasses via `super()`. | **Cannot** have constructors. |
| **Access Modifiers** | Methods and fields can use any access modifier (`public`, `protected`, `private`). | All methods are implicitly **`public abstract`** (before Java 8) and all fields are implicitly **`public static final`**. |
| **Keyword** | Defined using the `abstract` keyword. Inherited using `extends`. | Defined using the `interface` keyword. Implemented using `implements`. |



---

## When to Use Which

The choice between an Abstract Class and an Interface often comes down to what you are trying to model:

### 🅰️ Use an **Abstract Class** When:

1.  **"Is A" Relationship (Strong Inheritance):** You want to model a group of closely related objects that share a **common base class** and some **common default behavior** and **state** (fields). *(e.g., `Car` **is a** `Vehicle`, and all `Vehicles` share an `engineSize` field and a `startEngine()` method with a default implementation).*
2.  **Shared Implementation is Needed:** You need to provide a **partial implementation** (concrete methods) that subclasses can inherit directly and only require them to implement the specific abstract methods.
3.  **State is Required:** You need to define non-final fields that represent the object's **state**.

### 🤝 Use an **Interface** When:

1.  **"Can Do" Relationship (Contract/Capability):** You want to define a **contract** or a **capability** that completely unrelated classes can promise to fulfill. *(e.g., A `Dog` and a `Robot` might both implement the `CanMove` interface, even though they are not related by class hierarchy).*
2.  **Multiple Behavior Inheritance:** You need a class to inherit behavior from **multiple sources**. Since Java supports single class inheritance, interfaces are the solution for achieving **multiple inheritance of behavior**.
3.  **Pure Abstraction:** You want to enforce **complete separation** between the definition of the behavior (the method signatures) and its implementation (the method body).
