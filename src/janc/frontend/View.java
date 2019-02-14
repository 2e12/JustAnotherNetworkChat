package janc.frontend;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class View{
    private final Controller controller;
    private final Model model;
    private Home home;
    private Chat chat;

    public View(Controller controller, Model model){
        this.controller = controller;
        this.model = model;
        home = new Home();
        chat = new Chat(controller);
        registerButtonActionHandler();
        listenToModelChanges();
    }

    public Chat getChat() {
        return chat;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    private void registerButtonActionHandler() throws NullPointerException{
        home.getButConnect().setOnAction(actionEvent -> controller.handleButtonClickSideSwitch(home.getTxtfdAdress().getText(), home.getTxtfdUsername().getText(), home.getTxtfdPassword().getText()));
        home.getSceneHome().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    controller.handleButtonClickSideSwitch(home.getTxtfdAdress().getText(), home.getTxtfdUsername().getText(), home.getTxtfdPassword().getText());
                }
            }
        });
        chat.getButSend().setOnAction(actionEvent -> controller.sendMessage(chat.getTxtfdActualMessage().getText()));
        chat.getSceneChat().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    controller.sendMessage(chat.getTxtfdActualMessage().getText());
                }
            }
        });
    }

    private void listenToModelChanges() {
        model.srollProperty().addListener((observable, oldValue, newValue) ->
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chat.getSpPane().setVvalue(1.0);
        });
        model.changeSiteProperty().addListener((observable, oldValue, newValue) ->
                Client.switchToScene());
        model.writtenMessageProperty().addListener((observable, oldValue, newValue) ->
                chat.sendMessage(newValue, chat.getUserName()));
        model.outputProperty().addListener((observable, oldValue, newValue) ->
                chat.displayMessage(newValue));
        model.warningProperty().addListener((observable, oldValue, newValue) ->
                home.getTxtWarning().setText("incorrect informations!"));
    }
}