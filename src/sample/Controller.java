package sample;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void handleButtonClickSideSwitch() {
        model.setChangeSite(true);
    }

    public void sendMessage(String message, String owner) {
        if (message.equals("")){

        }
        else {
            model.setWrittenMessage(message);
        }
    }
}
