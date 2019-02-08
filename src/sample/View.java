package sample;

import javafx.scene.Scene;

public class View{
    private final Controller controller;
    private final Model model;
    private Home home;
    private Chat chat;
    private Scene scene;

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public View(Controller controller, Model model){
        this.controller = controller;
        this.model = model;
        home = new Home();
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
        if (chat != null) {
            chat.getButSend().setOnAction(actionEvent -> controller.sendMessage(chat.getTxtfdActualMessage().getText()));
        }
    }

    private void listenToModelChanges() {
        model.changeSiteProperty().addListener((observable, oldValue, newValue) ->
                Client.switchToScene());
        model.writtenMessageProperty().addListener((observable, oldValue, newValue) ->
                chat.sendMessage(newValue));
    }
}