package janc.backend;

public class Message {
    private int id;
    private int userid;
    private String username;
    private String timestamp;
    private String text;



    /**
     * Set the Message id
     *
     * @param id The id of the message
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get user id
     *
     * @return The userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Set the user id
     * @param userid The id of the user
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Get the timestamp when the message was created
     * @return The timestap as string
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Set the creation date(timestamp) of the message
     * @param timestamp The timestamp of the creation data
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get the content of the message
     * @return the message text as string
     */
    public String getText() {
        return text;
    }

    /**
     * This sets the message text
     * @param text the message text
     */
    public void setText(String text) {
        this.text = text;
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
     * Gets username.
     *
     * @return Value of username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public int getId() {
        return id;
    }
}
