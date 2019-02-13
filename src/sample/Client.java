package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
    private static Stage stage;
    static View view;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Model model = new Model();
        Controller controller = new Controller(model);
        view = new View(controller, model);
        primaryStage.setTitle("Just Another Network Chat");
        primaryStage.setScene(view.getHome().getSceneHome());
        primaryStage.show();
    }

    public static void switchToScene() {
        view.setChat(new Chat(view.getHome().getTxtfdAdress().getText(), view.getHome().getTxtfdUsername().getText(), view.getHome().getTxtfdPassword().getText()));
        stage.setScene(view.getChat().getSceneChat());
    }

    public static void main(String[] args){
        launch(args);
    }
}