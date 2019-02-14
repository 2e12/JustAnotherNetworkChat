package janc.frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application{
    private static Stage stage;
    private static View view;
    private static Model model;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setResizable(false);
        model = new Model();
        controller = new Controller(model);
        view = new View(controller, model);
        primaryStage.setTitle("Just Another Network Chat");
        primaryStage.setScene(view.getHome().getSceneHome());
        primaryStage.show();
    }

    public static void switchToScene() {
        stage.setScene(view.getChat().getSceneChat());
        view.getChat().setUserName(view.getHome().getTxtfdUsername().getText());
        view.getChat().setConnectedIP(view.getHome().getTxtfdAdress().getText());
        view.getChat().setTxtConnectionText("chatting @ " + view.getHome().getTxtfdAdress().getText());
        view.getChat().setConnection(view.getHome().getTxtfdAdress().getText(), view.getHome().getTxtfdUsername().getText(), view.getHome().getTxtfdPassword().getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}