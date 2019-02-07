package sample;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JancProtocolHandler {
    //Map for the protocl commands
    static Map<String, Class> commands = new HashMap<>();

    public JancProtocolHandler(){
        JancProtocolHandler.registerCommand("lgn", RequestSessionKey.class.getClass());
    }

    //Parse the Command and passing the Arguments to the registerd command
    public void ParseFromString(String input){
        String[] parts = input.split(";");
    }

    private void ExecuteCommand(String[] inputParts){
        try {
            JancCommand obj = (JancCommand) commands.get("req").getConstructors()[0].newInstance("");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void registerCommand(String commandName, Class command){
        if (command.getSuperclass() == JancCommand.class) {
            commands.put(commandName, command);
        }
    }
}
