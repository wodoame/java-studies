package assessment;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MegaCorp {
    public static void main(String[] args) {
        Department root = loadData("src/company.json");
        if (root != null) {
//            System.out.println("Successfully loaded data for: " + root.name);

            boolean found = checkForEmployee(root, 0); // Intern in Head Office & Mobile
            if(!found){
                System.out.println("Employee not found");
            }
        }
    }

    public static Department loadData(String filename) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filename))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Department.class);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static boolean checkForEmployee(Department dept, int id) {
        // Check current department
        if (searchInterns(dept.interns, id)) {
            System.out.println("Found Intern " + id + " in " + dept.name);
            return true;
        }
        if (searchManagers(dept.managers, id)) {
            System.out.println("Found Manager " + id + " in " + dept.name);
            return true;
        }

        // Recurse
        for (Department sub : dept.subDepartments) {
            if(checkForEmployee(sub, id)){
                return true;
            }
        }
        return false;
    }

    public static boolean searchInterns(List<Integer> interns, int target) {
        for (Integer intern : interns) {
            if (intern == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchManagers(List<Integer> managers, int target) {
        int left = 0;
        int right = managers.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = managers.get(mid);
            if (val == target) {
                return true;
            } else if (val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
