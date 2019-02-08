package sample;

import javafx.scene.Scene;

public class View{
    private final Controller controller;
    private final Model model;
    private Home home;
    private Chat chat;
    private Scene scene;

    public View(Controller controller, Model model){
        this.controller = controller;
        this.model = model;
        home = new Home();
        chat = new Chat();
        registerButtonActionHandler();
        listenToModelChanges();
    }

    public Chat getChat() {
        return chat;
    }

    public Home getHome() {
        return home;
    }

    private void registerButtonActionHandler() {
        home.getButConnect().setOnAction(actionEvent -> controller.handleButtonClickSideSwitch());
    }

    private void listenToModelChanges() {
        model.changeSiteProperty().addListener((observable, oldValue, newValue) ->
                Client.switchToScene());
    }
}