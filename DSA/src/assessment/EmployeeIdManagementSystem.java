package assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeIdManagementSystem {
    private static Employee head;
    static void main() {
        String[][] operations = InputParser.getInput();
        for(String[] op: operations){
            switch (op[0]){
                case "REGISTER":
                    register(Integer.parseInt(op[1]), Integer.parseInt(op[2]) );
                    break;
                case "LOOKUP":
                    lookup(Integer.parseInt(op[1]));
                    break;
                case "HEIGHT":
                    height();
                    break;
                case "PATH":
                    path(Integer.parseInt(op[1]));
                    break;
                case "RANGE":
                    range(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                case "DISPLAY":
                    display();
                    break;
                default:
                    System.out.println("Operation not supported");
            }
        }
    }
    static void register(int id, int salary){
        Employee em = insert(head, id, salary);
        if(head == null)head = em;
        System.out.printf("Registered: ID=%d, Salary:%d\n", id, salary);
    }

    static Employee insert(Employee root, int id, int salary){
        if(root == null){
            return new Employee(id, salary);
        }
        if(id < root.getId())root.setLeftChild(insert(root.getLeftChild(), id, salary));
        else root.setRightChild(insert(root.getRightChild(), id, salary));
        return root;
    }
    static void lookup(int id){
        int foundId = search(head, id);
        if(foundId == -1) System.out.println("Not found: ID=" + id);
        else System.out.printf("Found: ID=%d, Salary=%d\n", id, foundId);
    }
    static int search(Employee root, int id){
       if(root == null)return -1;
       if(root.getId() == id)return root.getSalary();
       if(id < root.getId()) return search(root.getLeftChild(), id);
       else return search(root.getRightChild(), id);
    }
    static void height(){
        System.out.println("Tree Height: " + heightRec(head));
    }
    static int heightRec(Employee root){
        if(root == null)return -1;
        return 1 + Math.max(heightRec(root.getLeftChild()), heightRec(root.getRightChild()));
    }
    static void range(int low, int high){
    List<Integer> ids = new ArrayList<>();
    rangeRec(low, high, head, ids);
    System.out.printf("Range [%d-%d]: %s\n", low, high, ids);
    }

    static void rangeRec(int low, int high, Employee root, List<Integer> ids){
        if(root == null)return;
        rangeRec(low, high, root.getLeftChild(), ids);
        if(root.getId() >= low && root.getId() <= high){
            ids.add(root.getId());
        }
        rangeRec(low, high, root.getRightChild(), ids);
    }

    static void path(int id){
        List<Integer> ids = new ArrayList<>();
        pathRec(head, id, ids);
        System.out.printf("Path to %d: %s\n", id, ids);
    }
    static void pathRec(Employee root, int stopId, List<Integer> ids){
        if(root == null)return;
        ids.add(root.getId());
        if(root.getId() == stopId)return;
        if(stopId < root.getId()) pathRec(root.getLeftChild(), stopId, ids);
        else pathRec(root.getRightChild(), stopId, ids);
    }

    static void display(){
        List<Integer> ids = new ArrayList<>();
        inorderTraversal(head, ids);
        System.out.println("All Employees: " + ids);
    }
    static void inorderTraversal(Employee root, List<Integer> ids){
        if(root == null)return;
        inorderTraversal(root.getLeftChild(), ids);
        ids.add(root.getId());
        inorderTraversal(root.getRightChild(), ids);
    }

    static class InputParser{
        static String[][] getInput(){
            System.out.println("Enter inputs below");
            Scanner scanner = new Scanner(System.in);
            int N = Integer.parseInt(scanner.nextLine());
            String[][] operations = new String[N][3];
            for (int i = 0; i < N; i++) {
                String[] operation = scanner.nextLine().split(" ");
                operations[i] = operation;
            }
            return operations;
        }

    }
}

class Employee{
    private final int salary;
    private final int id;
    private Employee leftChild;
    private Employee rightChild;

    public Employee(int id, int salary) {
        this.id = id;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public Employee getLeftChild() {
        return leftChild;
    }

    public Employee getRightChild() {
        return rightChild;
    }

    public int getId() {
        return id;
    }

    public void setLeftChild(Employee leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Employee rightChild) {
        this.rightChild = rightChild;
    }
}



