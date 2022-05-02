public class User {
    private final String username;
    //If we have time I would like to store passwords more securely
    private final String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Get method for the user's username
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get method for the user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }
}
