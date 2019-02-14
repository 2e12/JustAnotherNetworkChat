package janc.frontend;


import javafx.beans.property.SimpleBooleanProperty;
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

    /**
     * Used for adding Listeners
     * @return the value of changeSite
     */
    public SimpleBooleanProperty changeSiteProperty() {
        return changeSite;
    }

    /**
     * Used for adding Listeners
     * @return the value of writtenMessage
     */
    public SimpleStringProperty writtenMessageProperty() {
        return writtenMessage;
    }

    /**
     * Used for adding Listeners
     * @return the value of output
     */
    public static SimpleStringProperty outputProperty() {
        return output;
    }

    /**
     * Used for adding Listeners
     * @return the value of warning
     */
    public SimpleBooleanProperty warningProperty() {
        return warning;
    }

    /**
     * Gets changeSite.
     *
     * @return Value of changeSite.
     */
    public SimpleBooleanProperty getChangeSite() {
        return changeSite;
    }

    /**
     * Sets new output.
     *
     * @param output New value of output.
     */
    public static void setOutput(String output) {
        Model.output.set(output);
    }

    /**
     * Gets output.
     *
     * @return Value of output.
     */
    public static SimpleStringProperty getOutput() {
        return output;
    }

    /**
     * Sets new warning.
     *
     * @param warning New value of warning.
     */
    public void setWarning(Boolean warning) {
        this.warning.set(warning);
    }

    /**
     * Sets new writtenMessage.
     *
     * @param writtenMessage New value of writtenMessage.
     */
    public void setWrittenMessage(String writtenMessage) {
        this.writtenMessage.set(writtenMessage);
    }

    /**
     * Gets writtenMessage.
     *
     * @return Value of writtenMessage.
     */
    public SimpleStringProperty getWrittenMessage() {
        return writtenMessage;
    }

    /**
     * Gets warning.
     *
     * @return Value of warning.
     */
    public SimpleBooleanProperty getWarning() {
        return warning;
    }

    /**
     * Sets new changeSite.
     *
     * @param changeSite New value of changeSite.
     */
    public void setChangeSite(Boolean changeSite) {
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
