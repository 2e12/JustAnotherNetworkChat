package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.*;

public class JancProtocolHandler {


    private ServerClientConnection connectionInfo;
    private static JancProtocolHandler instance;

    public static JancProtocolHandler getInstance() {
        if (instance == null) {
            instance = new JancProtocolHandler();
        }

        return instance;
    }



    //Map for the protocl commands
    static Map<String, Class> commands = new HashMap<>();

    //All open connections
    static List<ServerClientConnection> connections = new ArrayList<>();

    //Constructor. Registers Commands
    private JancProtocolHandler() {
        JancProtocolHandler.registerCommand("lgn", RequestSessionKey.class);
        JancProtocolHandler.registerCommand("msg", SendMessage.class);
    }

    public void broadcastToAllClients(String command){
        for(ServerClientConnection connection: connections){
            try {
                var out = new PrintWriter(connection.getClientConnection().getOutputStream(), true);
                out.println(command);
            }catch (IOException e){}
        }
    }

    public void putUserConnection(String username, Socket socket, String sessionKey) {
        ServerClientConnection connection = new ServerClientConnection(socket, sessionKey);
        connections.add(connection);
        connection.start();
    }

    //Parse the Command and passing the Arguments to the registerd command
    public void ParseFromString(String input, Socket socket, ServerClientConnection connectionInfo) {
        String[] parts = input.split(";");
        this.connectionInfo = connectionInfo;
        this.ExecuteCommand(parts, socket);
    }

    private void ExecuteCommand(String[] inputParts, Socket socket) {
        try {
            //Get from command string the command class and pass the parameters and socket connection
            Constructor constructor = JancProtocolHandler.commands.get(inputParts[0]).getConstructor(String[].class, Socket.class);
            JancCommand obj = (JancCommand) constructor.newInstance((Object) inputParts, socket);
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
