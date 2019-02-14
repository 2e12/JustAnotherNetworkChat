package janc.backend;

import java.util.List;

public interface MessageDao {
    public abstract void insertMessage(Message message);

    public abstract List<Message> getAllMessages();
}
