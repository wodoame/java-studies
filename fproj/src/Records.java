public class Records {
    static void main() {
        System.out.println("Hello, World!".hashCode());
        Employee employee = new Employee("Bernard", 22);
        Circle circle = new Circle("Circle");
        Triangle triangle = new Triangle("Triangle");
        Square square = new Square("Square");
        Rectangle rectangle = new Rectangle("Rectangle");
        System.out.println(employee.hashCode());
    }
}

record Employee(String name, int age){ }
record Circle(String name){ }
record Triangle(String name){ }
record Square(String name){ }
record Rectangle(String name){ }
