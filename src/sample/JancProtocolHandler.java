package sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JancProtocolHandler {
    //Map for the protocl commands
    static Map<String, Class> commands = new HashMap<>();

    //Constructor. Registers Commands
    public JancProtocolHandler(){
        JancCommand.protocol = this;
        JancProtocolHandler.registerCommand("lgn", RequestSessionKey.class);
    }

    //Parse the Command and passing the Arguments to the registerd command
    public void ParseFromString(String input){
        String[] parts = input.split(";");
        System.out.println(parts[0]);
        this.ExecuteCommand(parts);
    }

    private void ExecuteCommand(String[] inputParts){
        try {
            Constructor constructor = JancProtocolHandler.commands.get(inputParts[0]).getConstructor(String[].class);
            JancCommand obj = (JancCommand) constructor.newInstance((Object) inputParts);
            obj.handle();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void registerCommand(String commandName, Class command){
        if (command.getSuperclass() == JancCommand.class) {
            commands.put(commandName, command);
        }
    }
}
