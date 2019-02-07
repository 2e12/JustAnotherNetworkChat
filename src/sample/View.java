package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

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

        scene = home.getSceneHome();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}