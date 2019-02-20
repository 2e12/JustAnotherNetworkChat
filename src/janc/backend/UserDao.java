package janc.backend;

enum LoginState { //Thus enum is required for checking later the login state
    loggedIn,
    worngPassword,
    noUser
}

public interface UserDao {

    /**
     * This function creates a new user in the database.
     *
     * @param user This is the user, the needed to be inserted
     * @return void
     */
    public abstract void insertUser(User user);


    /**
     * Checks if the login credentials are right.
     *
     * @param user This is the user, whose credentials needed to be checked
     * @return LoginState This indicates, if the user is logged in, the password is wrong or the user doesn't exist
     */
    public abstract LoginState checkLoginCredentials(User user);


    /**
     * Loads the ID from an specific user from the database
     *
     * @param username The name of the user
     * @return The user ID
     */
    public abstract int getUserIdByName(String username);
}
