package janc.frontend;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Client extends Application{
    private static Stage stage;
    private static View view;
    private static Model model;
    private static Controller controller;
    private Image icon;

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
        icon = new Image("file:resources/icons/janc_icon.png");
        stage.getIcons().addAll(icon);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event ->
        {
            File file = new File("resources/chats/newestChat.txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                fr.write(view.getChat().getMessages());
            } catch (IOException io) {
                System.out.println("Writing failed!");
            } finally {
                try {
                    fr.close();
                } catch (IOException ioe) {
                    System.out.println("Writing failed!");
                }
            }
        });
    }

    /**
     * This method changes the scene in the stage and sets a new connection to a specific server.
     */
    public static void switchToScene(String scene) {
        if (scene.equals("chat")) {
            stage.setScene(view.getChat().getSceneChat());
            view.getChat().setUserName(view.getHome().getTxtfdUsername().getText());
            view.getChat().setConnectedIP(view.getHome().getTxtfdAdress().getText());
            view.getChat().setTxtConnectionText("chatting @ " + view.getHome().getTxtfdAdress().getText());
            view.getChat().setConnection(view.getHome().getTxtfdAdress().getText(), view.getHome().getTxtfdUsername().getText(), view.getHome().getTxtfdPassword().getText());
        } else {
            view.getChat().sendBye();
            System.out.println("Socket connection closed!");
            model = new Model();
            controller = new Controller(model);
            view = new View(controller, model);
            stage.setScene(view.getHome().getSceneHome());
        }
    }

    /**
     * This method starts the whole application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}