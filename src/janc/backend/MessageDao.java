package janc.backend;

import java.util.List;

public interface MessageDao {

    /**
     * This function addes a new message.
     * @param message This is the message, the needed to be inserted.
     */
    public abstract void insertMessage(Message message);

    /**
     * This function loads all messages from the database.
     * @return List<Message> A list with all messages.
     */
    public abstract List<Message> getAllMessages();
}
