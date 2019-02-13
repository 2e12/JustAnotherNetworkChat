package janc.backend;

import java.io.IOException;
import java.net.Socket;

public class CloseConnection extends JancCommand{

    public CloseConnection(String[] parts, Socket source){
        super(parts, source);
        try {
            source.close();
        }catch (IOException e){

        }
    }

    @Override
    void handle() {

    }

    @Override
    void send(Socket socket) throws IOException {

    }

    @Override
    void parseFromString(String[] parts) {

    }
}
