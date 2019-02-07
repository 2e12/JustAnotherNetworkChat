package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Chat{
    private Scene sceneChat;
    private BorderPane bpPane;
    private Text txtHeader;
    private Text txtConnection;
    private VBox vbxHeader;
    private ScrollPane spPane;
    private TilePane tpPane;
    private Text txtMessage;
    private TextField txtfdActualMessage;
    private VBox vbxContent;
    private Button butSend;
    private String connectedIP;

    public Scene getSceneChat() {
        return sceneChat;
    }

    public Chat(){
        connectedIP = "127.0.0.1";

        //Create the header content
        txtHeader = new Text("chatroom:");
        txtConnection = new Text("chatting @ " + connectedIP);
        vbxHeader = new VBox();
        vbxHeader.getChildren().addAll(txtHeader, txtConnection);


        //Create the chat
        txtMessage = new Text("Test");
        tpPane = new TilePane();
        tpPane.getChildren().addAll(txtMessage);
        spPane = new ScrollPane();
        spPane.setContent(tpPane);

        //Create the content of the input field
        txtfdActualMessage = new TextField();
        txtfdActualMessage.setPromptText("message");

        //Create the send-button
        butSend = new Button("send");

        //Add the side content to a vbox
        vbxContent = new VBox();
        vbxContent.getChildren().addAll(spPane, txtfdActualMessage, butSend);

        //Add everything to a Scene
        bpPane = new BorderPane();
        bpPane.setTop(vbxHeader);
        bpPane.setCenter(vbxContent);
        sceneChat = new Scene(bpPane);
    }
}