package janc.frontend;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Client extends Application{
    private static Stage stage;
    private static View view;
    private static Model model;
    private static Controller controller;

    /**
     * This constructor is a type of a manager for every class which the client uses.
     * @param primaryStage The main stage of the client.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setResizable(false);
        model = new Model();
        controller = new Controller(model);
        view = new View(controller, model);
        primaryStage.setTitle("Just Another Network Chat");
        primaryStage.setScene(view.getHome().getSceneHome());
        primaryStage.setX(Screen.getPrimary().getBounds().getMaxX() / 100 * 36);
        primaryStage.setY(Screen.getPrimary().getBounds().getMaxY() / 100 * 2);
        primaryStage.show();
    }

    /**
     * This method changes the scene in the stage and sets a new connection to a specific server.
     */
    public static void switchToScene() {
        stage.setScene(view.getChat().getSceneChat());
        view.getChat().setUserName(view.getHome().getTxtfdUsername().getText());
        view.getChat().setConnectedIP(view.getHome().getTxtfdAdress().getText());
        view.getChat().setTxtConnectionText("chatting @ " + view.getHome().getTxtfdAdress().getText());
        view.getChat().setConnection(view.getHome().getTxtfdAdress().getText(), view.getHome().getTxtfdUsername().getText(), view.getHome().getTxtfdPassword().getText());
    }

    /**
     * This method starts the whole application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}