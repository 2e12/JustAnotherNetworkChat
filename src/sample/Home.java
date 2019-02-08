package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Home{
    private Scene sceneHome;
    private BorderPane bpPane;
    private Text txtHeader;
    private VBox vbxInputField;
    private Text txtTitle;
    private TextField txtfdAdress;
    private TextField txtfdUsername;
    private PasswordField txtfdPassword;
    private HBox hbxConnect;
    private Button butConnect;

    public Scene getSceneHome(){
        return sceneHome;
    }

    public Button getButConnect() {
        return butConnect;
    }

    public Home(){
        //Create content of the header element
        txtHeader = new Text("Just Another Network Chat");

        //Create content of the selection-menu
        txtTitle = new Text("establish new connection:");
        txtfdAdress = new TextField();
        txtfdAdress.setPromptText("ip-adress of your server");
        txtfdUsername = new TextField();
        txtfdUsername.setPromptText("username");
        txtfdPassword = new PasswordField();
        txtfdPassword.setPromptText("password");

        //Create the button
        butConnect = new Button("connect");

        //Add the button to a hbox
        hbxConnect = new HBox();
        hbxConnect.getChildren().addAll(butConnect);

        //Add the whole content to a vbox
        vbxInputField = new VBox();
        vbxInputField.getChildren().addAll(txtTitle, txtfdAdress, txtfdUsername, txtfdPassword, hbxConnect);

        //Add everything to a pane
        bpPane = new BorderPane();
        bpPane.setTop(txtHeader);
        bpPane.setCenter(vbxInputField);

        //Add everything to a scene
        sceneHome = new Scene(bpPane, 400, 600);
    }
}