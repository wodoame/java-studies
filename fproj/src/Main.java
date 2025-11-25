public class Main{
    public static void main(String[] args){
    Student me = new Student("Bernard Wodoame", 20, "S12345");
    System.out.println("Name: " + me.getName());
    System.out.println("Age: " + me.getAge());
    System.out.println("ID: " + me.getId());
    }
}

// For Demonstration Purposes
class Student{
    private final String name;
    private final int age;
    private final String id;

    public Student(String name, int age, String id){
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getId(){
        return id;
    }
}
