package demo;

public class UserProfile {
    @MaxLength(10)
    public String username;

    public UserProfile(String username) {
        this.username = username;
    }
}
