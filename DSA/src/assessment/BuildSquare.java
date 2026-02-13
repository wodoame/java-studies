package assessment;

import java.util.Arrays;

public class BuildSquare {
    static void main() {
        for(String row: buildSquare(8)){
            System.out.println(row);
        }
    }
    public static String[] buildSquare(int n){
       String[] res = new String[n];
       res[0] = "*".repeat(n);
       res[n - 1] = "*".repeat(n);
       for (int i = 1; i < n - 1; i++) {
           res[i] = "*" + " ".repeat(n - 2) + "*";
       }
       return res;
    }
}