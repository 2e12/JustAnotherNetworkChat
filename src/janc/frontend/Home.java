package janc.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class Home{
    private Scene sceneHome;
    private BorderPane bpPane;
    private Text txtHeader;
    private HBox hbxHeader;
    private VBox vbxInputField;
    private Text txtTitle;
    private TextField txtfdAdress;
    private TextField txtfdUsername;
    private PasswordField txtfdPassword;
    private HBox hbxConnect;
    private Button butConnect;
    private Text txtWarning;


    /**
     * This is the creator of the Home class. This method creates the whole GUI in JavaFX.
     */
    public Home(){
        //Create content of the header element
        hbxHeader = new HBox();
        txtHeader = new Text("Just Another Network Chat");
        txtHeader.setStyle("-fx-font-family: Arial; -fx-font-size: 25px;");
        txtHeader.setFill(Color.web("#4F6367"));
        hbxHeader.getChildren().add(txtHeader);
        hbxHeader.setMargin(txtHeader, new Insets(20, 0, 0, 0));
        hbxHeader.setAlignment(Pos.CENTER);

        //Create content of the selection-menu
        txtTitle = new Text("establish new connection:");
        txtTitle.setStyle("-fx-font-size: 18px");
        txtTitle.setFill(Paint.valueOf("#4F6367"));
        txtWarning = new Text();
        txtWarning.setFill(Color.web("#FC4225"));
        txtWarning.setStyle("-fx-font-family: Arial; -fx-font-size: 18px");
        txtfdAdress = new TextField();
        txtfdAdress.setPromptText("ip-adress of your server");
        txtfdAdress.setMaxWidth(300);
        txtfdAdress.setMinHeight(30);
        txtfdAdress.setStyle("-fx-background-color: #7A9E9F; -fx-background-radius: 50px; -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #FFFFFF; -fx-font-size: 14px");
        txtfdUsername = new TextField();
        txtfdUsername.setPromptText("username");
        txtfdUsername.setMaxWidth(300);
        txtfdUsername.setMinHeight(30);
        txtfdUsername.setStyle("-fx-background-color: #7A9E9F; -fx-background-radius: 50px; -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #FFFFFF; -fx-font-size: 14px");
        txtfdPassword = new PasswordField();
        txtfdPassword.setPromptText("password");
        txtfdPassword.setMaxWidth(300);
        txtfdPassword.setMinHeight(30);
        txtfdPassword.setStyle("-fx-background-color: #7A9E9F; -fx-background-radius: 50px; -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #FFFFFF; -fx-font-size: 14px");

        //Create the button
        butConnect = new Button("connect");
        butConnect.setStyle("-fx-background-color: #7A9E9F; -fx-font-family: Arial; -fx-font-size: 20px; -fx-pref-width: 150px; -fx-pref-height: 23px; -fx-text-fill: #FFFFFF; -fx-background-radius: 50px");

        //Add the button to a hbox
        hbxConnect = new HBox();
        hbxConnect.getChildren().addAll(butConnect);
        hbxConnect.setAlignment(Pos.CENTER_RIGHT);

        //Add the whole content to a vbox
        vbxInputField = new VBox();
        vbxInputField.getChildren().addAll(txtTitle, txtWarning, txtfdAdress, txtfdUsername, txtfdPassword, hbxConnect);
        vbxInputField.setMargin(txtTitle, new Insets(40, 0, 30, 52));
        vbxInputField.setMargin(txtWarning, new Insets(0, 0, 10, 52));
        vbxInputField.setMargin(txtfdAdress, new Insets(10, 0, 10, 52));
        vbxInputField.setMargin(txtfdUsername, new Insets(10, 0, 10, 52));
        vbxInputField.setMargin(txtfdPassword, new Insets(10, 0, 10, 52));
        vbxInputField.setMargin(txtfdPassword, new Insets(10, 0, 10, 52));
        vbxInputField.setMargin(hbxConnect, new Insets(10, 48, 0, 52));

        //Add everything to a pane
        bpPane = new BorderPane();
        bpPane.setTop(hbxHeader);
        bpPane.setCenter(vbxInputField);

        //Add everything to a scene
        sceneHome = new Scene(bpPane, 400, 600);
    }

    /**
     * Gets txtfdPassword.
     *
     * @return Value of txtfdPassword.
     */
    public PasswordField getTxtfdPassword() {
        return txtfdPassword;
    }

    /**
     * Gets txtfdUsername.
     *
     * @return Value of txtfdUsername.
     */
    public TextField getTxtfdUsername() {
        return txtfdUsername;
    }

    /**
     * Gets sceneHome.
     *
     * @return Value of sceneHome.
     */
    public Scene getSceneHome() {
        return sceneHome;
    }

    /**
     * Gets txtfdAdress.
     *
     * @return Value of txtfdAdress.
     */
    public TextField getTxtfdAdress() {
        return txtfdAdress;
    }

    /**
     * Gets txtWarning.
     *
     * @return Value of txtWarning.
     */
    public Text getTxtWarning() {
        return txtWarning;
    }

    /**
     * Gets butConnect.
     *
     * @return Value of butConnect.
     */
    public Button getButConnect() {
        return butConnect;
    }
}