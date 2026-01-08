package demo;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ValidationService {
    public boolean validateUser(User user, Predicate<User> rule){
        return rule.test(user);
    }
    public boolean validatePassword(String password, String confirmPassword, BiPredicate<String, String> rule){
        return rule.test(password, confirmPassword);
    }
    public boolean validateMultipleRules(User user, List<Predicate<User>> rules){
        return rules.stream().allMatch((rule)->rule.test(user));
    }
}
