package demo;

import java.util.List;

public class Main {
     static void main() {
        User user = new User("Bernard Jones", "xxxxxxxx", "bmwodoame@gmail.com");
        ValidationService validationService = new ValidationService();
        var rules = List.of(Rules.isValidUser);
        boolean isValidUser = validationService.validateUser(user, Rules.isValidUser);
        boolean isValidPassword = validationService.validatePassword(user.getPassword(), "abcdedghij", Rules.isValidPassword);
        boolean isMultiValid = validationService.validateMultipleRules(user, rules);
        System.out.println(isValidUser);
        System.out.println(isValidPassword);
        System.out.println(isMultiValid);
    }
}
