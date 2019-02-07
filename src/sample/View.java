package sample;

import javafx.scene.Scene;

public class View{
    private final Controller controller;
    private final Model model;
    private Home home;
    private Chat chat;
    private Scene scene;

    public View(Controller controller, Model model){
        this.controller = controller;
        this.model = model;
        //home = new Home();
        chat = new Chat();

        scene = chat.getSceneChat();
    }

    public Scene getScene() {
        return scene;
    }
}