package demo;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Rules {
    public static Predicate<User> isValidUser = (user)->user.getEmail().contains("@") && !user.getName().isBlank();
    public static BiPredicate<String, String> isValidPassword = (password1, password2)->password1.length() >= 8 && password1.equals(password2);
}
