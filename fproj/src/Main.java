public class Main {
    int x; // Create a class attribute;

    public static void main(String[] args) {
        Main myCar = new Main(5); // Create a myCar object
        System.out.println(myCar.x ); // Print the value of x
        myCar.fullThrottle(); // Call the fullThrottle() method
        myCar.speed(200); // Call the speed() method and pass a value
    }

    // Create a class constructor for the Main class
    public Main(int y) {
        x = y;
    }


    // Create a fullThrottle() method
    public void fullThrottle() {
        System.out.println("The car is going as fast as it can!");
    }

    // Create a speed() method and add a parameter
    public void speed(int maxSpeed) {
        System.out.println("Max speed is: " + maxSpeed);
    }

    static void myMethod() {
        System.out.println("Hello from myMethod!");
    }
}

class Person {
    private String name; // private = restricted access

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String newName) {
        this.name = newName;
    }
}
