package janc.frontend;

import java.util.regex.Pattern;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void handleButtonClickSideSwitch(String adress, String username, String passwd) {
        Boolean validIp = false;
        Pattern pattern = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if (pattern.matcher(adress).matches()) {
            validIp = true;
        }
        if (adress.isEmpty() || username.isEmpty() || passwd.isEmpty() || !validIp) {
            model.setWarning(true);
        } else {
            model.setChangeSite(true);
        }
    }

    public void scroll() {
        model.setSroll(model.getSroll() + 1);
    }

    public void sendMessage(String message) {
        message = message.replace(';', ';');
        if (message.equals("")){

        }
        else {
            model.setWrittenMessage(message);
        }
    }
}
