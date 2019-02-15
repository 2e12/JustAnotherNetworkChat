package janc.backend;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;


    /**
     * Gets password.
     *
     * @return Value of password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets new password.
     *
     * @param password New value of password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets new username.
     *
     * @param username New value of username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets salt.
     *
     * @return Value of salt.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets new salt.
     *
     * @param salt New value of salt.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Gets username.
     *
     * @return Value of username.
     */
    public String getUsername() {
        return username;
    }
}
