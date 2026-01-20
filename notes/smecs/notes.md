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
