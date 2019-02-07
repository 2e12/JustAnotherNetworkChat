package sample;


import javafx.beans.property.SimpleBooleanProperty;

public class Model {
    private SimpleBooleanProperty changeSite = new SimpleBooleanProperty(false);

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
