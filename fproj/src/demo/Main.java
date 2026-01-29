package demo;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        UserProfile user1 = new UserProfile("JavaDev123"); // 10 chars - OK
        UserProfile user2 = new UserProfile("Neovim_Enthusiast"); // 17 chars - FAIL
        
        Validator.validate(user1);
        System.out.println("User 1 is valid!");
        Validator.validate(user2); // This will throw the exception
    }
}
