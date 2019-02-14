package janc.frontend;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleBooleanProperty changeSite = new SimpleBooleanProperty(false);
    private SimpleStringProperty writtenMessage = new SimpleStringProperty("");
    private static SimpleStringProperty output = new SimpleStringProperty("");
    private SimpleBooleanProperty warning = new SimpleBooleanProperty(false);
    private SimpleStringProperty colorTheme = new SimpleStringProperty("bright");

    /**
     * Used for adding Listeners
     * @return the value of colorTheme
     */
    public SimpleStringProperty colorThemeProperty() {
        return colorTheme;
    }

    public int getSroll() {
        return sroll.get();
    }

    public SimpleIntegerProperty srollProperty() {
        return sroll;
    }

    public void setSroll(int sroll) {
        this.sroll.set(sroll);
    }

    public boolean isWarning() {
        return warning.get();
    }

    public SimpleBooleanProperty warningProperty() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning.set(warning);
    }

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

    /**
     * Sets new colorTheme.
     *
     * @param colorTheme New value of colorTheme.
     */
    public void setColorTheme(String colorTheme) {
        this.colorTheme.set(colorTheme);
    }

    /**
     * Gets colorTheme.
     *
     * @return Value of colorTheme.
     */
    public SimpleStringProperty getColorTheme() {
        return colorTheme;
    }
}
