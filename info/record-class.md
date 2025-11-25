A **Java Record** is a special kind of class introduced in Java 16 as a preview feature and made standard in Java 17. Its primary purpose is to act as a transparent and immutable **data carrier**.

In essence, a record is a concise way to create classes whose main role is to hold data without needing to write a lot of boilerplate code for constructors, getters, `equals()`, `hashCode()`, and `toString()`.

-----

## 🔑 Key Features of a Java Record

A record declaration automatically generates several members for you based on its **record components** (the fields defined in the parentheses):

  * **Final Fields (Private):** For each component, a **private `final` field** is created. This makes the record **immutable**—its state cannot be changed after creation.
  * **Canonical Constructor (Public):** A public constructor whose signature matches the record components. It initializes the private fields.
  * **Accessor Methods:** A public accessor method (like a "getter") for each component, which has the **same name as the field** (e.g., for a component `name`, the accessor is `name()`, not `getName()`).
  * **Standard Methods:** Implementations of `equals()`, `hashCode()`, and `toString()` are automatically generated based on all the record components.

-----

## ✍️ Syntax and Comparison to a Standard Class

### 1\. The Record Declaration (Concise)

The syntax is extremely compact. You simply declare the record and list its components.

```java
public record Book(String title, String author, int publicationYear) {
    // You can optionally add extra methods here, 
    // or a compact constructor (see below)
}
```

### 2\. The Equivalent Standard Java Class (Verbose)

To achieve the same immutable data carrier with a standard class, you would have to write all this code yourself:

```java
public final class BookClass {
    private final String title;
    private final String author;
    private final int publicationYear;

    public BookClass(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    // equals(), hashCode(), and toString() implementations... (omitted for brevity)
}
```

-----

## 🛠️ How to Use a Record

### 1\. Creation and Access

Records are instantiated and accessed just like regular classes, but using the concise accessor methods:

```java
// Creation using the automatically generated constructor
Book javaBook = new Book("Java Records Explained", "B. Gosling", 2023);

// Accessing the components using the concise accessor methods
String bookTitle = javaBook.title(); // Accessor is title(), not getTitle()
int year = javaBook.publicationYear();

System.out.println(javaBook); 
// Output: Book[title=Java Records Explained, author=B. Gosling, publicationYear=2023]
```

### 2\. Customizing the Constructor (Validation)

You can add a **compact constructor** to perform validation or normalization logic. It omits the parentheses and the explicit field assignments, as those are still handled automatically by the compiler.

```java
public record Book(String title, String author, int publicationYear) {
    // Compact Constructor for validation
    public Book {
        if (publicationYear < 1900 || publicationYear > 2025) {
            throw new IllegalArgumentException("Invalid publication year: " + publicationYear);
        }
        // No need for 'this.title = title;'—it's automatic
    }
}
```

-----

## 🚫 Limitations

Records come with specific restrictions because they are designed purely for data carrying:

  * Records are implicitly **`final`**, so they **cannot be extended** by other classes (no inheritance).
  * Records can **implement interfaces**, but they **cannot extend any class** other than `java.lang.Record`.
  * Instance fields must be defined in the record header; you **cannot declare additional instance fields** inside the body.
  * All instance fields are implicitly **`private` and `final`**.
