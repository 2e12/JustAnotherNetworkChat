package janc.frontend;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat{
    private Controller controller;
    private Scene sceneChat;
    private BorderPane bpPane;
    private HBox hbxHeader;
    private Text txtHeader;
    private Image imgBack;
    private Button butBackHome;
    private Text txtConnection;
    private VBox vbxHeader;
    private ScrollPane spPane;
    private VBox vbxMessages;
    private TextField txtfdActualMessage;
    private VBox vbxContent;
    private String userName;
    private HBox hbxSend;
    private Text myText;
    private Text myTime;
    private Text myOwner;
    private VBox myVBox;
    private HBox myHBox;
    private Button butSend;
    private ComboBox cbxEmojis;
    private HBox hbxSendingItems;
    private String connectedIP;
    private ServerConnection serverConnection;
    private final ToggleGroup tgThemes;
    private RadioButton rbutBright;
    private RadioButton rbutDark;
    private Label lblBright;
    private Label lblDark;
    private HBox hbxBright;
    private HBox hbxDark;
    private HBox hbxThemes;
    private String messages;

    /**
     * This method is the constructor of the Chat class. Here the whole GUI for the chat tab gets built.
     */
    public Chat(){
        messages = " ";
        //Create the header content
        imgBack = new Image("file:resources/icons/arrow32.png");
        butBackHome = new Button();
        butBackHome.setGraphic(new ImageView(imgBack));
        butBackHome.setPrefHeight(32);
        butBackHome.setPrefWidth(32);
        txtHeader = new Text("chatroom:");
        txtHeader.setFont(new Font("Arial", 35));
        txtHeader.setFill(Color.web("#4f6367"));
        hbxHeader = new HBox();
        hbxHeader.getChildren().addAll(txtHeader, butBackHome);
        txtConnection = new Text("chatting @ " + connectedIP);
        txtConnection.setFont(new Font("Arial", 16));
        txtConnection.setFill(Color.web("#4f6367"));
        vbxHeader = new VBox();
        vbxHeader.getChildren().addAll(hbxHeader, txtConnection);
        hbxHeader.setSpacing(458);

        //Create the chat
        vbxMessages = new VBox();
        vbxMessages.setPrefWidth(15);
        vbxMessages.setSpacing(15);
        spPane = new ScrollPane();
        spPane.setMaxHeight(600);
        spPane.setMinHeight(600);
        spPane.setMaxWidth(660);
        spPane.setMinWidth(660);
        spPane.setContent(vbxMessages);
        spPane.vvalueProperty().bind(vbxMessages.heightProperty());

        //Create the content of the input field and the emoji combobox
        txtfdActualMessage = new TextField();
        txtfdActualMessage.setPromptText("message");
        txtfdActualMessage.setPrefWidth(550);
        cbxEmojis = new ComboBox();
        cbxEmojis.setEditable(false);
        cbxEmojis.setPrefWidth(100);
        cbxEmojis.setPromptText("Emojis");
        cbxEmojis.setPrefHeight(txtfdActualMessage.getHeight());
        cbxEmojis.getItems().addAll(" (ㆆ _ ㆆ) ", " •`_´• ", " (‿|‿) ", " •͡˘㇁•͡˘ ", " (-_-) ", " ✔ ", " (｡◕‿‿◕｡) ", " (︶︹︶) ", " <(^_^)> ");
        hbxSendingItems = new HBox();
        hbxSendingItems.getChildren().addAll(txtfdActualMessage, cbxEmojis);
        hbxSendingItems.setPrefWidth(650);

        //Create the send-button and the HBox
        butSend = new Button("send");
        butSend.setStyle("-fx-background-color: #7A9E9F; -fx-font-family: Arial; -fx-font-size: 25px; -fx-pref-width: 150px; -fx-pref-height: 23px; -fx-text-fill: #FFFFFF;");
        hbxSend = new HBox();
        hbxSend.getChildren().add(butSend);
        hbxSend.setHgrow(butSend, Priority.ALWAYS);
        hbxSend.setAlignment(Pos.CENTER_RIGHT);

        //Add the side content to a vbox
        vbxContent = new VBox();
        vbxContent.getChildren().addAll(spPane, hbxSendingItems, hbxSend);
        vbxContent.setMargin(spPane, new Insets(0, 70, 0, 70));
        vbxContent.setMargin(hbxSendingItems, new Insets(0, 70, 0, 70));
        vbxContent.setMargin(hbxSend, new Insets(0, 70, 0, 0));
        vbxContent.setSpacing(40);

        //Create nodes for the footer
        tgThemes = new ToggleGroup();
        rbutBright = new RadioButton();
        rbutDark = new RadioButton();
        lblBright = new Label("Bright theme: ");
        lblDark = new Label("Dark theme: ");
        rbutBright.setToggleGroup(tgThemes);
        rbutDark.setToggleGroup(tgThemes);
        rbutBright.setSelected(true);
        rbutDark.setSelected(false);
        hbxBright = new HBox();
        hbxDark = new HBox();
        hbxBright.getChildren().addAll(lblBright, rbutBright);
        hbxDark.getChildren().addAll(lblDark, rbutDark);
        hbxThemes = new HBox();
        hbxThemes.getChildren().addAll(hbxBright, hbxDark);
        hbxThemes.setSpacing(65);

        //Add everything to a Scene
        bpPane = new BorderPane();
        bpPane.setTop(vbxHeader);
        bpPane.setCenter(vbxContent);
        bpPane.setBottom(hbxThemes);
        bpPane.setMargin(vbxHeader, new Insets(10, 0, 20, 70));
        bpPane.setMargin(hbxThemes, new Insets(0, 0, 90, 70));
        sceneChat = new Scene(bpPane, 800, 950);
    }

    /**
     * This method is used for send a message to the server.
     * @param message this parameter is the whole message which to user has made.
     * @param uName this parameter is the name of the user which sent the message. Later the app uses this param for styling the message in the GUI.
     */
    public void sendMessage(String message, String uName) {
        serverConnection.sendMessageToServer(message, uName);
        txtfdActualMessage.setText("");
    }

    /**
     * This method triggers an other method in the ServerConnection class, which sends a 'bye'-command to the server-socket.
     */
    public void sendBye() {
        serverConnection.sendByeToServer();
    }

    /**
     * This method creates a new connection to a specified server.
     * @param ipAdress this is the ip-adress of the server where the user can send his messages to.
     * @param uName this is the name of user who wants to connect to the server. This param is used to identify the sender.
     * @param pWord this is the password of the user. It's also used for the identification.
     */
    public void setConnection(String ipAdress, String uName, String pWord) {
        this.serverConnection = new ServerConnection(ipAdress, uName, pWord);
    }

    /**
     * This huge method adds the messages, which are distributed by the server, to the GUI.
     * @param message For sure the method needs to know, what the content of the message is. This param contains the sending user, a timestamp and the message himself.
     */
    public void displayMessage(String message) {
        String[] parts = message.split(";");
        Platform.runLater(() -> {
            try {
                if (parts[0].equals("dsb")) {
                    String owner = parts[1];
                    Timestamp ts = new Timestamp(Long.parseLong(parts[2]));
                    Date date = new Date(ts.getTime());
                    String msg = parts[3];
                    messages += new SimpleDateFormat("HH:mm").format(date) + " -> " + owner + " : " + msg + "    ";
                    myText = new Text(msg);
                    myTime = new Text(new SimpleDateFormat("HH:mm").format(date));
                    myVBox = new VBox();
                    myHBox = new HBox();
                    if (owner.equals(serverConnection.getUName())) {
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
                    } else {
                        myOwner = new Text(owner + ":");
                        myOwner.setFont(Font.font("Arial", 16));
                        myOwner.setFill(Color.web("#FFFFFF"));
                        myText.setFill(Color.web("#FFFFFF"));
                        myTime.setFill(Color.web("#FFFFFF"));
                        myText.setFont(Font.font("Arial", 20));
                        myText.setWrappingWidth(500);
                        myVBox.setStyle("-fx-background-color: #7A9E9F; -fx-background-radius: 5px;");
                        myHBox.setAlignment(Pos.CENTER_RIGHT);
                        myHBox.getChildren().add(myTime);
                        myVBox.getChildren().add(myOwner);
                        myVBox.getChildren().add(myText);
                        myVBox.getChildren().add(myHBox);
                    }
                    myVBox.setPadding(new Insets(10, 10, 10, 10));
                    vbxMessages.getChildren().add(myVBox);
                    vbxMessages.setPadding(new Insets(20, 0, 20, 15));
                    vbxMessages.setSpacing(40);
                }else {
                    System.out.println("Logging in...");
                }
            } catch (NullPointerException ne) {

            }
        });
    }

    /**
     * This method does nearly the same as the similar method in the Home class.
     * It changes the style of various nodes, that they fit with the new background color, which is defined by the selected theme.
     * @param theme
     */
    public void changeStyle(String theme) {
        String hexcolMainBright = "#4F6367";
        String hexcolSecondBright = "#000000";
        String hexcolMainDark = "#FFFFFF";
        if (theme.equals("bright")) {
            rbutBright.setSelected(true);
            rbutDark.setSelected(false);
            txtHeader.setFill(Color.web(hexcolMainBright));
            txtConnection.setFill(Color.web(hexcolMainBright));
            bpPane.setStyle("-fx-background-color: #FFFFFF");
            lblBright.setTextFill(Color.web(hexcolSecondBright));
            lblDark.setTextFill(Color.web(hexcolSecondBright));
            spPane.setStyle("-fx-background: #FFFFFF");
        } else {

            rbutBright.setSelected(true);
            rbutDark.setSelected(false);
            txtConnection.setFill(Color.web(hexcolMainDark));
            bpPane.setStyle("-fx-background-color: #363636");
            lblBright.setTextFill(Color.web(hexcolMainDark));
            lblDark.setTextFill(Color.web(hexcolMainDark));
            txtHeader.setFill(Color.web(hexcolMainDark));
            spPane.setStyle("-fx-background: #363636");
        }
    }

    /**
     * Sets new txtConnection.
     * @param txt New content for the txtConnection Node.
     */
    public void setTxtConnectionText(String txt) {
        this.txtConnection.setText(txt);
    }

    /**
     * Sets new userName.
     *
     * @param userName New value of userName.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets new connectedIP.
     *
     * @param connectedIP New value of connectedIP.
     */
    public void setConnectedIP(String connectedIP) {
        this.connectedIP = connectedIP;
    }

    /**
     * Gets txtfdActualMessage.
     *
     * @return Value of txtfdActualMessage.
     */
    public TextField getTxtfdActualMessage() {
        return txtfdActualMessage;
    }

    /**
     * Gets userName.
     *
     * @return Value of userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets butSend.
     *
     * @return Value of butSend.
     */
    public Button getButSend() {
        return butSend;
    }

    /**
     * Gets sceneChat.
     *
     * @return Value of sceneChat.
     */
    public Scene getSceneChat() {
        return sceneChat;
    }

    /**
     * Gets spPane.
     *
     * @return Value of spPane.
     */
    public ScrollPane getSpPane() {
        return spPane;
    }

    /**
     * Gets rbutBright.
     *
     * @return Value of rbutBright.
     */
    public RadioButton getRbutBright() {
        return rbutBright;
    }

    /**
     * Gets rbutDark.
     *
     * @return Value of rbutDark.
     */
    public RadioButton getRbutDark() {
        return rbutDark;
    }

    /**
     * Gets cbxEmojis.
     *
     * @return Value of cbxEmojis.
     */
    public ComboBox getCbxEmojis() {
        return cbxEmojis;
    }

    /**
     * Gets butBackHome.
     *
     * @return Value of butBackHome.
     */
    public Button getButBackHome() {
        return butBackHome;
    }

    /**
     * Gets messages.
     *
     * @return Value of messages.
     */
    public String getMessages() {
        return messages;
    }
}