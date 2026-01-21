1. `ConcurrentHashMap` is used in `ProductCache` for thread safety without sacrificing performance. It prevents data corruption.

Structure
---------
Based on the `main_layout.fxml` file and the logic visible in `UserController.java`, here is the breakdown of the layout's components and their architectural interactions.

### 1. Structural Overview
The file `main_layout.fxml` acts as the **Root Container** for the application. It uses a `BorderPane` layout strategy:
*   **Top**: A global toolbar for navigation and state (Login/Info).
*   **Center**: A `TabPane` that acts as a view switcher between the Customer (User) view and the Admin view.

### 2. Component Breakdown & Interactions

#### A. The Top Toolbar (`<top>`)
*   **UI Components:** `Label` (user name), `Button` (Login/Logout).
*   **Controller:** `com.smecs.controller.MainLayoutController`.
*   **Backend Interaction:**
    *   **`SessionManager`**: The controller uses the singleton `SessionManager` to check if a user is logged in.
    *   **`UserService` / `UserDAO`**: When the "Login" button is clicked (`handleAuthAction`), it triggers authentication logic to verify credentials against the database.

#### B. The Center TabPane (`<center>`)
This area uses `<fx:include>` tags. This is a powerful FXML feature that loads separate FXML files and instantiates their specific controllers automatically.

Binding
-------
### 1. Class Association
The link begins at the root element of your FXML file.
```xml
<BorderPane ... fx:controller="com.smecs.controller.MainLayoutController">
```
When the application loads this file, the `FXMLLoader` reads this attribute, instantiates the `MainLayoutController` class, and establishes it as the handler for this specific UI structure.

### 2. Component Injection (`fx:id`)
JavaFX uses the `fx:id` attribute to inject UI components directly into the controller's fields. The loader looks for fields in the controller class that exactly match the string value of the `fx:id`.

*   **In FXML**: `<Label fx:id="userLabel" .../>`
*   **In Controller**: The loader looks for a field defined like this:
    ```java
    @FXML
    private Label userLabel;
    ```
    Once found, the instance of that Label is assigned to this variable, allowing you to change text (e.g., the username) programmatically.

This applies to specific elements in your file:
*   `userLabel` (Label)
*   `authButton` (Button)
*   `mainTabPane` (TabPane)
*   `adminTab` (Tab)

### 3. Event Binding (`onAction`)
For user interactions, the `#` symbol in the value tells JavaFX to look for a method in the controller.

*   **In FXML**: `<Button ... onAction="#handleAuthAction"/>`
*   **In Controller**: The loader looks for a method with a matching name, typically annotated with `@FXML`:
    ```java
    @FXML
    private void handleAuthAction() {
        // Login/Logout logic here
    }
    // OR
    @FXML
    private void handleAuthAction(ActionEvent event) { ... }
    ```
When the button is clicked, this specific Java method is executed.

### 4. Note on `<fx:include>`
The elements inside the tabs use `<fx:include source="user_view.fxml"/>`.
*   These included files represent **separate** FXML contexts.
*   They are **not** bound to `MainLayoutController`. Instead, `user_view.fxml` binds to `UserController` (as seen in your previous prompt), and `admin_view.fxml` binds to its own defined controller.
*   The `MainLayoutController` typically does not manipulate the internal controls of the user view (like the cart table) directly; it only manages the high-level container (BorderPane and Tabs).

Placing an Order
----------------
Here is a short summary of the process triggered when `placeOrder` is called:
1.  **Validation & Calculation**: The system retrieves the user's cart, ensures it isn't empty, and calculates the total order amount.
2.  **Transaction Start**: A database transaction is opened to ensure data consistency.
3.  **Order Creation**: The main order record (status "PENDING") is saved to the database.
4.  **Item Processing & Stock Deduction**: The system iterates through the cart items, saving them as order items and **immediately reducing the product inventory** for each item.
5.  **Cleanup**: The user's shopping cart is cleared.
6.  **Commit/Rollback**: If all steps succeed, the transaction is committed permanently. If an error occurs (e.g., insufficient stock), all changes are rolled back.

Window Management
-----------------
In the context of the provided `JavaFX` application code, `Stage` and `Scene` are fundamental concepts used to create the graphical user interface (GUI).

**Stage**
The `Stage` is the top-level container for a `JavaFX` application, essentially the **window** itself.
*   **Role:** It represents the native operating system window (the frame, title bar, minimize/close buttons).
*   **In your code:** `primaryStage` is the main window created by the `JavaFX` runtime and passed to the `start` method. You call `primaryStage.setTitle(...)` to set the window title and `primaryStage.show()` to make it visible.

**Scene**
The `Scene` is the container for all content **inside** the window.
*   **Role:** It holds the UI elements (called nodes) like buttons, text fields, and layouts. Consider the `Stage` as the theater stage (the physical area) and the `Scene` as the specific play or setting currently visible on that stage.
*   **In your code:** You are creating a `Scene` using a root layout loaded from an FXML file (`main_layout.fxml`) and then attaching it to the window via `primaryStage.setScene(...)`.

In JavaFX, the `Parent` class is a **base class for all nodes that can have children** in the scene graph. It serves as a generic container for UI elements.

*   **Role:** It acts as the common superclass for layouts (like `StackPane`, `VBox`, `AnchorPane`) and UI controls that manage children. Because almost all containers inherit from it, it provides a flexible way to reference the "root" of your visual hierarchy without needing to know exactly what kind of layout was loaded from the FXML file.

*   **In your code:**
    `Parent root` holds the entire UI structure loaded from `main_layout.fxml`. This `root` object is then passed to the `Scene` constructor because a `Scene` requires a `Parent` as its starting point.

    ```java
    // Parent acts as a generic handle for whatever layout (e.g., AnchorPane) is inside the FXML
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/main_layout.fxml")));
    
    // The Scene wraps this Parent
    primaryStage.setScene(new Scene(root));
    ```

Essentially, `Parent` is the glue between your loaded FXML content and the `Scene`.

BigDecimal
----------
Using `BigDecimal` for currency and financial calculations is a standard best practice in Java for several reasons:

**1. Precision and Accuracy**
Floating-point types like `float` and `double` are approximations. They store numbers in binary format, which cannot accurately represent many decimal fractions (like 0.1).
*   **With `double`:** `0.1 + 0.2` might result in `0.30000000000000004` due to floating-point errors.
*   **With `BigDecimal`:** The result is exactly `0.3`. In financial applications, essentially losing "pennies" due to rounding errors is unacceptable.

**2. Controlled Rounding**
`BigDecimal` provides full control over rounding behavior. When performing division or tax calculations, you can specify exactly how to handle values that don't divide evenly (e.g., `RoundingMode.HALF_UP`, `RoundingMode.FLOOR`), which is critical for complying with accounting standards.

**3. Database Compatibility**
SQL databases typically use types like `DECIMAL` or `NUMERIC` to store money for the exact same receive reasons (precision). In JDBC, the `java.math.BigDecimal` class maps directly and losslessly to these SQL types via `pstmt.setBigDecimal(...)` and `rs.getBigDecimal(...)`.

POJO
----
A POJO stands for **Plain Old Java Object**.

It is an ordinary Java object that is not bound by any special restrictions other than those forced by the Java Language Specification. It does not require extending a specific class (like `HttpServlet`) or implementing any specific interfaces from a framework.

Characteristics of the `User` class that make it a POJO:
*   It does not extend any pre-specified class (e.g., `extends javax.servlet.http.HttpServlet`).
*   It does not implement any pre-specified interface (e.g., `implements javax.ejb.EntityBean`).
*   It does not contain pre-specified annotations (though modern POJOs often do use annotations like JPA, strict POJOs do not require them).
*   It has private fields, a public no-arg constructor, and getters/setters (following JavaBean conventions), which makes it easy to use for data transfer.

Sorting
-------


## 1. SORTING ALGORITHMS

### 1.1 TimSort (Default - Java Collections.sort)
**Location:** `src/main/java/com/smecs/util/ProductSorter.java` (lines 47-54)

```java
public static void sort(List<Product> products, SortCriteria criteria) {
    if (products == null || products.isEmpty()) {
        return;
    }
    Comparator<Product> comparator = getComparator(criteria);
    products.sort(comparator);  // Uses TimSort internally
}
```

**Characteristics:**
- **Algorithm Type:** Hybrid sorting algorithm (Merge Sort + Insertion Sort)
- **Time Complexity:** 
  - Best Case: O(n)
  - Average Case: O(n log n)
  - Worst Case: O(n log n)
- **Space Complexity:** O(n)
- **Stable:** Yes
- **Use Case:** Default sorting algorithm for all product displays
- **Performance:** ~2.4 ms for 1,000 items

**Where Used:**
- `ProductService.getAllProductsSorted()` (line 60)
- `ProductService.searchProductsSorted()` (line 100)
- `UserController.handleSearch()` (line 531)
- `UserController.handleSortChange()` (line 557)

---

### 1.2 QuickSort (Educational Implementation)
**Location:** `src/main/java/com/smecs/util/ProductSorter.java` (lines 110-142)

```java
public static void quickSort(List<Product> products, SortCriteria criteria) {
    if (products == null || products.size() <= 1) {
        return;
    }
    Comparator<Product> comparator = getComparator(criteria);
    quickSortHelper(products, 0, products.size() - 1, comparator);
}
```

**Implementation Details:**
- Uses partition method with last element as pivot
- Recursive divide-and-conquer approach
- In-place sorting with swap operations

**Characteristics:**
- **Algorithm Type:** Divide and Conquer
- **Time Complexity:**
  - Best Case: O(n log n)
  - Average Case: O(n log n)
  - Worst Case: O(n²)
- **Space Complexity:** O(log n) for recursion stack
- **Stable:** No
- **Use Case:** Comparison benchmarking
- **Performance:** ~1.8 ms for 1,000 items (fastest for large datasets)

**Where Used:**
- `PerformanceBenchmark.runAllBenchmarks()` (line 113) - for performance testing

---

### 1.3 MergeSort (Educational Implementation)
**Location:** `src/main/java/com/smecs/util/ProductSorter.java` (lines 148-206)

```java
public static void mergeSort(List<Product> products, SortCriteria criteria) {
    if (products == null || products.size() <= 1) {
        return;
    }
    Comparator<Product> comparator = getComparator(criteria);
    mergeSortHelper(products, 0, products.size() - 1, comparator);
}
```

**Implementation Details:**
- Recursive divide-and-conquer approach
- Uses temporary arrays for merging
- Stable sorting with predictable performance

**Characteristics:**
- **Algorithm Type:** Divide and Conquer
- **Time Complexity:**
  - Best Case: O(n log n)
  - Average Case: O(n log n)
  - Worst Case: O(n log n)
- **Space Complexity:** O(n)
- **Stable:** Yes
- **Use Case:** When stability is required
- **Performance:** ~3.1 ms for 1,000 items

**Where Used:**
- `PerformanceBenchmark.runAllBenchmarks()` (line 120) - for performance testing

---

### Sort Criteria Supported
**Location:** `src/main/java/com/smecs/util/ProductSorter.java` (lines 17-41)

All sorting algorithms support these criteria:
1. **NAME_ASC** - Name (A-Z)
2. **NAME_DESC** - Name (Z-A)
3. **PRICE_ASC** - Price (Low to High)
4. **PRICE_DESC** - Price (High to Low)
5. **CATEGORY_ASC** - Category (A-Z)
6. **CATEGORY_DESC** - Category (Z-A)
7. **DATE_ASC** - Oldest First
8. **DATE_DESC** - Newest First

---

## 2. SEARCHING ALGORITHMS

### 2.1 Hash-Based Search (Primary)
**Location:** `src/main/java/com/smecs/util/ProductSearcher.java` (lines 79-131)

```java
public List<Product> search(String query) {
    // Uses three hash indexes:
    // 1. exactNameIndex - O(1) lookup for exact matches
    // 2. nameIndex - O(1) word-based lookups
    // 3. categoryIndex - O(1) category-based lookups
}
```

**Implementation Details:**
- Builds inverted indexes for fast lookups
- Uses HashMap data structures
- Supports partial matching and word tokenization

**Characteristics:**
- **Algorithm Type:** Hash-based indexing
- **Time Complexity:** O(1) average for exact match, O(k) for word matches where k = result size
- **Space Complexity:** O(n) for storing indexes
- **Index Types:**
  1. `exactNameIndex` - Maps exact product names to products
  2. `nameIndex` - Maps individual words to products containing that word
  3. `categoryIndex` - Maps category names/words to products

**Where Used:**
- `ProductService.searchProductsInMemory()` (line 111)
- `ProductSearcher.search()` - main search method

**Index Building:**
- `ProductSearcher.buildIndex()` (lines 35-43)
- Called when cache is populated: `ProductService.getAllProducts()` (line 49)

---

### 2.2 Database Search (SQL LIKE)
**Location:** `src/main/java/com/smecs/dao/ProductDAO.java` (lines 97-120)

```java
public List<Product> searchProducts(String query) {
    String sql = "SELECT p.*, c.category_name FROM Products p " +
            "LEFT JOIN Categories c ON p.category_id = c.category_id " +
            "WHERE LOWER(p.product_name) LIKE ? OR LOWER(c.category_name) LIKE ?";
}
```

**Characteristics:**
- **Algorithm Type:** Pattern matching with SQL LIKE operator
- **Time Complexity:** O(n) without index, O(log n) with B-tree index
- **Implementation:** Uses SQL prepared statements
- **Features:** Case-insensitive, partial matching with wildcards

**Where Used:**
- `ProductService.searchProducts()` (line 86)
- Falls back to database when cache miss occurs

---

### 2.3 Binary Search (Educational Implementation)
**Location:** `src/main/java/com/smecs/util/ProductSearcher.java` (lines 137-167)

```java
public static int binarySearchByName(List<Product> sortedProducts, String targetName) {
    String lowerTarget = targetName.toLowerCase();
    int left = 0;
    int right = sortedProducts.size() - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        // ... comparison logic
    }
}
```

**Characteristics:**
- **Algorithm Type:** Divide and Conquer
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Prerequisite:** List must be sorted by product name
- **Use Case:** Comparison benchmarking

**Where Used:**
- `PerformanceBenchmark.runAllBenchmarks()` (line 144) - for performance comparison

---

### 2.4 Linear Search (Baseline Implementation)
**Location:** `src/main/java/com/smecs/util/ProductSearcher.java` (lines 172-186)

```java
public static Product linearSearch(List<Product> products, String targetName) {
    String lowerTarget = targetName.toLowerCase();
    for (Product product : products) {
        if (product.getProductName() != null && 
            product.getProductName().toLowerCase().equals(lowerTarget)) {
            return product;
        }
    }
    return null;
}
```

**Characteristics:**
- **Algorithm Type:** Sequential scan
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Use Case:** Baseline for performance comparison

**Where Used:**
- `PerformanceBenchmark.runAllBenchmarks()` (line 138) - for performance testing

---

## 3. FILTERING ALGORITHMS

### 3.1 Stream-Based Filtering (Java Streams)
**Location:** `src/main/java/com/smecs/controller/AdminController.java` (lines 555-562)

```java
@FXML
private void handleShowLowStock() {
    // Filter inventory to show only low stock items (quantity <= 10)
    inventoryList.setAll(
        inventoryService.getAllInventory().stream()
            .filter(inv -> inv.getQuantity() <= 10)
            .collect(java.util.stream.Collectors.toList())
    );
}
```

**Characteristics:**
- **Algorithm Type:** Lazy evaluation with Stream API
- **Time Complexity:** O(n)
- **Use Case:** Low stock inventory filtering

**Other Stream Filters in Project:**
1. **Sentiment Analysis Filter** (`NoSQLDemo.java`, lines 262-264):
   ```java
   long positiveCount = allReviews.stream()
       .filter(r -> "positive".equals(r.getSentiment())).count();
   ```

2. **Null Filtering** (`ProductCache.java`, line 101):
   ```java
   .filter(Objects::nonNull)
   ```

3. **Index Usage Filter** (`QueryPerformanceAnalyzer.java`, line 307):
   ```java
   .filter(c -> c.optimized.usesIndex)
   ```

---

### 3.2 SQL WHERE Clause Filtering
**Location:** Multiple DAO files

**Examples:**

1. **Search Filtering** (`ProductDAO.java`, line 102):
   ```sql
   WHERE LOWER(p.product_name) LIKE ? OR LOWER(c.category_name) LIKE ?
   ```

2. **Category Filtering** (Implicit through joins):
   ```sql
   LEFT JOIN Categories c ON p.category_id = c.category_id
   ```

**Characteristics:**
- **Algorithm Type:** Database engine optimization (usually B-tree index scans)
- **Time Complexity:** O(log n) with indexes, O(n) without
- **Use Case:** Server-side filtering before data retrieval

---

## 4. PERFORMANCE METRICS

### Sorting Performance (1,000 items)
| Algorithm | Execution Time | Best Case | Worst Case | Stable |
|-----------|---------------|-----------|------------|--------|
| **TimSort** | 2.4 ms | O(n) | O(n log n) | Yes |
| **QuickSort** | 1.8 ms | O(n log n) | O(n²) | No |
| **MergeSort** | 3.1 ms | O(n log n) | O(n log n) | Yes |

### Search Performance Comparison
| Search Type | Before Optimization | After Optimization | Speedup |
|------------|--------------------|--------------------|---------|
| Name Search | 156.8 ms | 18.4 ms | **8.5x** |
| Category Filter | 92.3 ms | 11.2 ms | **8.2x** |
| Price Range | 78.5 ms | 9.8 ms | **8.0x** |

**Source:** `docs/PERFORMANCE_REPORT.md` (lines 168-192)

---

## 5. ALGORITHM SELECTION STRATEGY

### When TimSort is Used (Default):
- User product browsing
- General purpose sorting
- When stability matters
- Default UI sorting

### When QuickSort is Used:
- Performance benchmarking
- Large dataset comparisons
- When stability is not required

### When MergeSort is Used:
- Performance benchmarking
- When guaranteed O(n log n) is needed
- Educational demonstrations

### When Hash-Based Search is Used:
- In-memory cached data searches
- Fast lookups by name or category
- After initial data load

### When Database Search is Used:
- Cache miss scenarios
- Initial data retrieval
- When freshest data is required

### When Stream Filters are Used:
- UI-level data filtering
- Post-retrieval refinement
- Business logic conditions (e.g., low stock)

---

## 6. OPTIMIZATION TECHNIQUES APPLIED

### 6.1 Caching Strategy
**Location:** `src/main/java/com/smecs/service/ProductService.java`

- Reduces database calls by ~90%
- Cache invalidation on data mutations
- Supports both full catalog and search result caching

### 6.2 Index Building
**Location:** `src/main/java/com/smecs/util/ProductSearcher.java`

- Inverted index for O(1) word lookups
- Built once during initial load
- Refreshed on cache invalidation

### 6.3 Database Indexing
**Location:** `src/main/resources/sql/indexes.sql`

- B-tree indexes on frequently searched columns
- Composite indexes for JOIN operations
- Dramatically improves SQL query performance

### 6.4 Comparator Reuse
**Location:** `src/main/java/com/smecs/util/ProductSorter.java` (lines 59-104)

- Single `getComparator()` method for all algorithms
- Reduces code duplication
- Supports all sort criteria uniformly

---

## 7. ALGORITHM USAGE FLOW

### User Search Flow:
```
1. User types in search field
   ↓
2. UserController.handleSearch() (line 530)
   ↓
3. ProductService.searchProductsSorted(query, sortCriteria) (line 98)
   ↓
4. Check cache → ProductCache.getSearchResults()
   ↓
5a. [CACHE HIT] → Return cached results (Hash-based index used)
   ↓
5b. [CACHE MISS] → ProductDAO.searchProducts() (SQL LIKE)
   ↓
6. ProductSorter.sort(results, criteria) (TimSort)
   ↓
7. Display results in TableView
```

### Admin Inventory Filter Flow:
```
1. Admin clicks "Show Low Stock"
   ↓
2. AdminController.handleShowLowStock() (line 555)
   ↓
3. Stream filter: .filter(inv -> inv.getQuantity() <= 10)
   ↓
4. Update UI with filtered results
```

---

## 8. FILES CONTAINING ALGORITHMS

### Core Algorithm Files:
1. **`ProductSorter.java`** - All sorting algorithms (TimSort, QuickSort, MergeSort)
2. **`ProductSearcher.java`** - All search algorithms (Hash, Binary, Linear)
3. **`ProductDAO.java`** - Database search with SQL LIKE
4. **`ProductService.java`** - Integration and caching layer
5. **`UserController.java`** - UI search/sort orchestration
6. **`AdminController.java`** - Stream-based filtering

### Supporting Files:
7. **`PerformanceBenchmark.java`** - Algorithm performance testing
8. **`ProductCache.java`** - Caching infrastructure
9. **`QueryPerformanceAnalyzer.java`** - SQL query optimization

---

## 9. KEY INSIGHTS

### Algorithm Selection Rationale:

1. **TimSort as Default**: Best overall performance with stability guarantees
2. **Hash-Based Search**: Provides O(1) lookups for common queries
3. **SQL Indexing**: Leverages database engine optimizations
4. **Stream Filtering**: Elegant, readable code for simple conditions

### Performance Optimizations:

1. **8.5x speedup** on name searches through hash indexing
2. **Caching reduces** database load by ~90%
3. **QuickSort is fastest** for pure sorting speed (1.8ms vs 2.4ms)
4. **TimSort preferred** for production due to stability and safety

### Educational Value:

- All three major O(n log n) algorithms implemented
- Binary and linear search for comparison
- Real-world performance metrics captured
- Demonstrates tradeoffs between algorithms

---

## 10. FUTURE OPTIMIZATION OPPORTUNITIES

As noted in `PERFORMANCE_REPORT.md`:

1. **Full-text search** using specialized libraries
2. **Elasticsearch integration** for advanced search
3. **Trie data structure** for autocomplete
4. **Shorter TTL caching** for search results
5. **Partitioning** for tables > 1M rows

---

## Summary

The SMECS project implements a comprehensive suite of sorting, searching, and filtering algorithms:

- **3 Sorting Algorithms**: TimSort (default), QuickSort, MergeSort
- **4 Search Algorithms**: Hash-based, SQL LIKE, Binary Search, Linear Search
- **2 Filtering Approaches**: Java Streams, SQL WHERE clauses

All algorithms are production-ready with proper error handling, null safety, and performance monitoring. The hybrid approach (cache + database + indexes) provides both speed and data freshness.


Indexes
-------

## What are Database Indexes?

**Database indexes** are special data structures that dramatically improve the speed of data retrieval operations on database tables. They work similarly to an index in a book - instead of reading every page to find a topic, you look it up in the index at the back, which tells you exactly which pages to visit.

### The Problem They Solve

Without indexes, databases must perform a **full table scan** - examining every single row to find matching records. For a table with 100,000 products:

- **Without Index:** Check all 100,000 rows → O(n) time complexity
- **With Index:** Use organized lookup structure → O(log n) time complexity

This can mean the difference between a query taking **5 seconds vs 50 milliseconds**.

---


## In-Memory Indexes in SMECS

While database indexes work at the **storage layer**, our project also implements **in-memory indexes** for cached data to achieve even faster lookups.

### Implementation

**Location:** `src/main/java/com/smecs/util/ProductSearcher.java`

```java
public class ProductSearcher {
    
    // Hash-based indexes (in-memory)
    private final Map<String, List<Product>> nameIndex;      // Word → Products
    private final Map<String, List<Product>> categoryIndex;  // Category → Products
    private final Map<String, Product> exactNameIndex;       // Exact name → Product
    
    /**
     * Build search indexes from a list of products.
     * This enables O(1) hash-based lookups for common searches.
     */
    public void buildIndex(List<Product> products) {
        nameIndex.clear();
        categoryIndex.clear();
        exactNameIndex.clear();
        
        for (Product product : products) {
            indexProduct(product);
        }
    }
    
    private void indexProduct(Product product) {
        // Index by exact product name (lowercase)
        if (product.getProductName() != null) {
            String lowerName = product.getProductName().toLowerCase();
            exactNameIndex.put(lowerName, product);
            
            // Index by individual words in product name
            String[] words = lowerName.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    nameIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(product);
                }
            }
        }
        
        // Index by category name
        if (product.getCategoryName() != null) {
            String lowerCategory = product.getCategoryName().toLowerCase();
            categoryIndex.computeIfAbsent(lowerCategory, k -> new ArrayList<>()).add(product);
        }
    }
    
    /**
     * Search products using the hash index.
     * O(1) average time complexity for lookups.
     */
    public List<Product> search(String query) {
        String lowerQuery = query.toLowerCase().trim();
        Map<Integer, Product> results = new HashMap<>();
        
        // First try exact name match - O(1)
        Product exactMatch = exactNameIndex.get(lowerQuery);
        if (exactMatch != null) {
            results.put(exactMatch.getProductId(), exactMatch);
        }
        
        // Search by individual words - O(k) where k = results
        String[] queryWords = lowerQuery.split("\\s+");
        for (String word : queryWords) {
            List<Product> nameMatches = nameIndex.get(word);
            if (nameMatches != null) {
                for (Product p : nameMatches) {
                    results.put(p.getProductId(), p);
                }
            }
        }
        
        return new ArrayList<>(results.values());
    }
}
```

Daemon Thread
-------------

A **daemon thread** is a low-priority background thread in Java that provides services to user threads. It's automatically terminated by the JVM when all non-daemon (user) threads finish execution.

## Key Characteristics:

### 1. **Automatic Termination**
- JVM doesn't wait for daemon threads to finish before shutting down
- When all user threads complete, the JVM exits immediately, killing any running daemon threads
- They "die" abruptly without completing their work

### 2. **Background Services**
- Used for tasks like garbage collection, monitoring, logging, and background maintenance
- Should NOT be used for critical operations (like database writes or file I/O that must complete)

### 3. **In Your Project**

In `ReportScheduler.java` line 26:

```java
this.timer = new Timer("ReportScheduler-Thread", true);  // Daemon thread
```

The `true` parameter makes this a daemon thread. This means:

**Why daemon?**
- ✅ Report generation is a **background service** - nice to have, but not critical
- ✅ When the application shuts down, you don't want it hanging while waiting for a report to finish
- ✅ The JVM can exit cleanly without waiting for the timer to complete its scheduled tasks

**What happens:**
- If a user closes SMECS while a report is being generated, the application exits immediately
- The report generation is interrupted (acceptable since reports are periodic and non-critical)
- No orphaned threads keep the JVM running after the UI closes

## Comparison:

| Feature | Daemon Thread | User Thread (Non-Daemon) |
|---------|--------------|--------------------------|
| **JVM Waits?** | No, kills immediately | Yes, waits to finish |
| **Use Case** | Background services | Critical business logic |
| **Example** | Garbage collector, your report scheduler | Main application thread, database operations |
| **Creation** | `thread.setDaemon(true)` or `Timer(..., true)` | Default behavior |

## Example in Your Logs:

When you see this in your runtime logs:
```
Process finished with exit code 130 (interrupted by signal 2:SIGINT)
```

Your daemon `ReportScheduler-Thread` was killed immediately when you interrupted the application (Ctrl+C), which is exactly the desired behavior! If it were a user thread, the JVM would wait for the timer task to complete before exiting.

## Best Practice:

Your implementation is **correct** for this use case. Report generation is a monitoring/diagnostic feature that should never block application shutdown.
