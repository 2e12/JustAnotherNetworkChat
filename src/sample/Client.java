package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
    Scene root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller, model);
        root = view.getHome().getSceneHome();
        primaryStage.setTitle("Just Another Network Chat");
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}