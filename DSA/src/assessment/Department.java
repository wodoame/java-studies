package assessment;

import java.util.ArrayList;
import java.util.List;

public class Department {
    public String name;
    public List<Department> subDepartments;
    public List<Integer> interns;
    public List<Integer> managers;

    public Department() {
        this.subDepartments = new ArrayList<>();
        this.interns = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", subDepartments=" + subDepartments +
                ", interns=" + interns +
                ", managers=" + managers +
                '}';
    }
}
