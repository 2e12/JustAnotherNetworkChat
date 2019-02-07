package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = primaryStage;
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller, model);
        primaryStage.setTitle("Just Another Network Chat");
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}