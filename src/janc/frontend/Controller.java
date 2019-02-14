package janc.frontend;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void handleButtonClickSideSwitch(String adress, String username, String passwd) {
        if (adress.isEmpty() || username.isEmpty() || passwd.isEmpty()) {
            model.setWarning(true);
        } else {
            model.setChangeSite(true);
        }
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
