package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.*;

public class JancProtocolHandler {


    private ServerClientConnection connectionInfo;
    private static JancProtocolHandler instance;

    /**
     * This method returns a singleton instance of this class. In case there isn't an instance, it creates one.
     *
     * @return This returns a singleton instance of JancProtocolHandler.
     */
    public static JancProtocolHandler getInstance() {
        if (instance == null) {
            instance = new JancProtocolHandler();
        }

        return instance;
    }



    //Map for the protocl commands
    private static Map<String, Class> commands = new HashMap<>();

    //All open connections
    private static List<ServerClientConnection> connections = new ArrayList<>();

    /**
     * Registers all commands that needed to be handled
     */
    private JancProtocolHandler() {
        JancProtocolHandler.registerCommand("lgn", RequestSessionKey.class);
        JancProtocolHandler.registerCommand("msg", SendMessage.class);
        JancProtocolHandler.registerCommand("bye", CloseConnection.class);
        JancProtocolHandler.registerCommand("req", RequestMessages.class);
    }

    /**
     * Send a command/message to all clients
     * @param command The command as string
     */
    public void broadcastToAllClients(String command){
        for(ServerClientConnection connection: connections){
            try {
                var out = new PrintWriter(connection.getClientConnection().getOutputStream(), true);
                out.println(command);
            }catch (IOException e){}
        }
    }

    /**
     * Stores an user connection
     * @param user The user object
     * @param socket The socket to the user
     */
    public void putUserConnection(User user, Socket socket) {
        ServerClientConnection connection = new ServerClientConnection(socket, user);
        connections.add(connection);
        connection.start();
    }

    /**
     * Splits the string to an array and pass it further to executeCommand method and from there to the command class
     *
     * @param input      The input command
     * @param connection The connection information form the user
     */
    public void parseFromString(String input, ServerClientConnection connection) {
        String[] parts = input.split(";");
        this.executeCommand(parts, connection);
    }

    private void executeCommand(String[] inputParts, ServerClientConnection connection) {
        try {
            //Get from command string the command class and pass the parameters and socket connection
            Constructor constructor = JancProtocolHandler.commands.get(inputParts[0]).getConstructor(String[].class, ServerClientConnection.class);
            JancCommand obj = (JancCommand) constructor.newInstance((Object) inputParts, connection);
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
