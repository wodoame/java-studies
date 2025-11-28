public class Main{
    public static void main(String[] args){
        Shape[] shapeArray = new Shape[3];
        Rectangle rectangle = new Rectangle("Red", 3, 5);
        Circle circle = new Circle("Green", 3);
        Triangle triangle = new Triangle("Yellow", 3, 4);

        shapeArray[0] = rectangle;
        shapeArray[1] = circle;
        shapeArray[2] = triangle;

        for(Shape shape: shapeArray){
            System.out.println("Name: " + shape.getName());
            System.out.println("Color: " + shape.getColor());
            System.out.printf("Area: %f\n\n", shape.calculateArea());
        }
    }
}