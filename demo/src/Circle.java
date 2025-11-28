public class Circle extends Shape{
    private double radius;
    private final double PI = 3.142;
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
        this.name = "Circle";
    }

    @Override
    public double calculateArea() {
        return this.PI * radius * radius;
    }
}
