public abstract class Shape {
    private String color;
    protected String name ;
    public Shape(String color){
        this.color = color;
    }

    public abstract double calculateArea();

    public String getColor(){
        return color;
    }

    public String getName(){return name;}
}
