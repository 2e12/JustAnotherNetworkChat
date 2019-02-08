package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Chat{
    private Scene sceneChat;
    private BorderPane bpPane;
    private Text txtHeader;
    private Text txtConnection;
    private VBox vbxHeader;
    private ScrollPane spPane;
    private VBox vbxMessages;
    private TextField txtfdActualMessage;
    private VBox vbxContent;
    private Button butSend;
    private String connectedIP;

    public Scene getSceneChat() {
        return sceneChat;
    }

    public Button getButSend() {
        return butSend;
    }

    public TextField getTxtfdActualMessage() {
        return txtfdActualMessage;
    }

    public Chat(){
        connectedIP = "127.0.0.1";

        //Create the header content
        txtHeader = new Text("chatroom:");
        txtHeader.setFont(new Font("Arial", 35));
        txtHeader.setFill(Color.web("#4f6367"));
        txtConnection = new Text("chatting @ " + connectedIP);
        txtConnection.setFont(new Font("Arial", 16));
        txtConnection.setFill(Color.web("#4f6367"));
        vbxHeader = new VBox();
        vbxHeader.getChildren().addAll(txtHeader, txtConnection);


        //Create the chat
        vbxMessages = new VBox();
        vbxMessages.setPrefWidth(15);
        vbxMessages.setSpacing(15);
        spPane = new ScrollPane();
        spPane.setMaxHeight(600);
        spPane.setContent(vbxMessages);

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
        sceneChat = new Scene(bpPane, 800, 950);
    }

    public void sendMessage(String message) {
        Text myText = new Text(message);
        HBox myHBox = new HBox();
        myText.setFill(Color.web("#FFFFFF"));
        myText.setFont(Font.font("Arial", 20));
        myText.setWrappingWidth(500);
        myHBox.setStyle("-fx-background-color: #7A9E9F; ");
        myHBox.getChildren().add(myText);
        vbxMessages.getChildren().add(myHBox);
    }
}