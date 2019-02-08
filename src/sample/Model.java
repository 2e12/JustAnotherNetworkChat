package sample;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleBooleanProperty changeSite = new SimpleBooleanProperty(false);
    private SimpleStringProperty writtenMessage = new SimpleStringProperty("");

    public String getWrittenMessage() {
        return writtenMessage.get();
    }

    public SimpleStringProperty writtenMessageProperty() {
        return writtenMessage;
    }

    public void setWrittenMessage(String writtenMessage) {
        this.writtenMessage.set(writtenMessage);
    }

    public boolean isChangeSite() {
        return changeSite.get();
    }

    public SimpleBooleanProperty changeSiteProperty() {
        return changeSite;
    }

    public void setChangeSite(boolean changeSite) {
        this.changeSite.set(changeSite);
    }
}
