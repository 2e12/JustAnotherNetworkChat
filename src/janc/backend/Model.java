package janc.backend;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleBooleanProperty changeSite = new SimpleBooleanProperty(false);
    private SimpleStringProperty writtenMessage = new SimpleStringProperty("");
    private static SimpleStringProperty output = new SimpleStringProperty("");

    public static String getOutput() {
        return output.get();
    }

    public static SimpleStringProperty outputProperty() {
        return output;
    }

    public static void setOutput(String output) {
        Model.output.set(output);
    }

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
