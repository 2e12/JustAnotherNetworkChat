package janc.frontend;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class View{
    private final Controller controller;
    private final Model model;
    private Home home;
    private Chat chat;

    /**
     * This is the constructor of the View class. Here we set the controller and the model.
     * This method also checks, if there are any button actions and listens to the model.
     * @param controller this param is the linked Controller class.
     * @param model this param is the linked Model class.
     */
    public View(Controller controller, Model model){
        this.controller = controller;
        this.model = model;
        home = new Home();
        chat = new Chat();
        registerButtonActionHandler();
        listenToModelChanges();
    }

    /**
     * This method sets a few action handlers for buttons and key events.
     * @throws NullPointerException
     */
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

    /**
     * This method listens to changes in the Model class and link actions to them.
     */
    private void listenToModelChanges() {
        model.changeSiteProperty().addListener((observable, oldValue, newValue) ->
                Client.switchToScene());
        model.writtenMessageProperty().addListener((observable, oldValue, newValue) ->
                chat.sendMessage(newValue, chat.getUserName()));
        model.outputProperty().addListener((observable, oldValue, newValue) ->
                chat.displayMessage(newValue));
        model.warningProperty().addListener((observable, oldValue, newValue) ->
                home.getTxtWarning().setText("incorrect informations!"));
    }

    /**
     * Gets home.
     *
     * @return Value of home.
     */
    public Home getHome() {
        return home;
    }

    /**
     * Gets chat.
     *
     * @return Value of chat.
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * Sets new home.
     *
     * @param home New value of home.
     */
    public void setHome(Home home) {
        this.home = home;
    }
}