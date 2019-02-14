package janc.backend;

import java.util.List;

public interface MessageDao {

    /**
     * This function addes a new message.
     * @param message This is the message, the needed to be inserted.
     */
    public abstract void insertMessage(Message message);


    /**
     * Returns every message since a point X
     * @param timestamp The date point as timestamp
     * @return List<Message> A list with all messages since X.
     */
    public abstract List<Message> getAllMessagesSince(String timestamp);
}
