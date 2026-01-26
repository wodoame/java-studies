package org.example;

public class Dev {
    private int age;
    private Computer computer;

    public Dev() {
        System.out.println("Dev constructor");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    //    private Computer computer;
    public void build(){
        System.out.println("Building software");
        computer.compile();
    }

}
