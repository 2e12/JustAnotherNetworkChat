package janc.frontend;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class Controller {
    private Model model;

    /**
     * The constructor sets the instance variable model to the parameter model.
     * @param model the given model from the view class.
     */
    public Controller(Model model){
        this.model = model;
    }

    /**
     * This method handles the click on the 'connect'-button.
     * If every parameter is valid, the method changes a SimpleBooleanProperty in the Model class.
     * @param adress this parameter is used as the ip-adress of the chat-server and the method proofs if the ip-adress is valid.
     * @param username this parameter is only used to avoid users without usernames on the server.
     * @param passwd this parameter is used to avoid multiple users with the same username.
     */
    public void handleButtonClickSideSwitch(String adress, String username, String passwd) {
        Boolean validIp = false;
        Boolean reachableIp = false;
        Pattern pattern = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if (pattern.matcher(adress).matches()) {
            validIp = true;
        }
        Socket s = null;
        try {
            try {
                s = new Socket(adress, 9980);
                s.setSoTimeout(2 * 100);
                reachableIp = true;
            } catch (UnknownHostException ue) {
                System.out.println("Error!");
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                }
            }
        } catch (IOException ie) {
            System.out.println("Error!");
        }
        if (adress.isEmpty() || username.isEmpty() || passwd.isEmpty() || !validIp || !reachableIp) {
            model.setWarning(true);
        } else {
            model.setChangeSite("chat");
        }
    }

    /**
     * This method is used to change a SimpleStringProperty in the Model class.
     * @param message this parameter gets written in the SimpleStringProperty.
     */
    public void sendMessage(String message) {
        message = message.replace(';', ';');
        if (message.equals("")){

        }
        else {
            model.setWrittenMessage(message);
        }
    }

    /**
     * This method sets a SimpleStringProperty in the Model class equal to a user defined theme.
     * @param themeName The theme which the user has defined.
     */
    public void setColorTheme(String themeName) {
        model.setColorTheme(themeName);
    }
}
