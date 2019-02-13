package sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
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
    private String userName;

    public void setConnectedIP(String connectedIP) {
        this.connectedIP = connectedIP;
    }

    private Button butSend;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTxtConnectionText(String txt) {
        this.txtConnection.setText(txt);
    }

    private String connectedIP;
    private Connection connection;

    public Scene getSceneChat() {
        return sceneChat;
    }

    public Button getButSend() {
        return butSend;
    }

    public TextField getTxtfdActualMessage() {
        return txtfdActualMessage;
    }

    public String getUserName() {
        return userName;
    }

    public Chat(){
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
        spPane.setMinHeight(600);
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
    public void sendMessage(String message, String uName) {
        connection.sendMessageToServer(message, uName);
        txtfdActualMessage.setText("");
    }

    public void setConnection(String ipAdress, String uName, String pWord) {
        this.connection = new Connection(ipAdress, uName, pWord);
    }


    public void displayMessage(String message) {
        String[] parts = message.split(";");
        Platform.runLater(() -> {
            if (parts[0].equals("dsb")) {
                String owner = parts[1] + ":";
                String time = parts[2];
                String msg = parts[3];
                if (owner.equals(userName)) {
                    Text myText = new Text(msg);
                    Text myTime = new Text(time);
                    VBox myVBox = new VBox();
                    HBox myHBox = new HBox();
                    myText.setFill(Color.web("#FFFFFF"));
                    myText.setFont(Font.font("Arial", 20));
                    myText.setWrappingWidth(500);
                    myTime.setFont(Font.font("Arial", 12));
                    myTime.setFill(Color.web("#FFFFFF"));
                    myVBox.setStyle("-fx-background-color: #FE5F55; -fx-background-radius: 5px;");
                    myHBox.setAlignment(Pos.CENTER_RIGHT);
                    myHBox.getChildren().add(myTime);
                    myVBox.getChildren().add(myText);
                    myVBox.getChildren().add(myHBox);
                    vbxMessages.getChildren().add(myVBox);
                    spPane.setVvalue(spPane.getMaxHeight());
                } else {
                    Text myOwner = new Text(owner);
                    Text myText = new Text(msg);
                    Text myTime = new Text(time);
                    VBox myVBox = new VBox();
                    HBox myHBox = new HBox();
                    myOwner.setFont(Font.font("Arial", 16));
                    myOwner.setFill(Color.web("#FFFFFF"));
                    myText.setFill(Color.web("#FFFFFF"));
                    myText.setFont(Font.font("Arial", 20));
                    myText.setWrappingWidth(500);
                    myVBox.setStyle("-fx-background-color: #7A9E9F; -fx-background-radius: 5px;");
                    myHBox.setAlignment(Pos.CENTER_RIGHT);
                    myHBox.getChildren().add(myTime);
                    myVBox.getChildren().add(myOwner);
                    myVBox.getChildren().add(myText);
                    myVBox.getChildren().add(myHBox);
                    vbxMessages.getChildren().add(myVBox);
                    spPane.setVvalue(spPane.getMaxHeight());
                }

            }else {
                System.out.println("Other Command!");
            }
        });
    }
}