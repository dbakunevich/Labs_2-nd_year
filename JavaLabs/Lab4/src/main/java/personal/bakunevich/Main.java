package personal.bakunevich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;


interface ChatClient {
    String getClientName();
    void sendMessage(String what);
}

interface Chat {
    void broadcast(ChatClient who, String what);
}

class ClientHandler extends Thread implements ChatClient {
    private Socket socket = null;
    private String login = null;
    private Chat chat = null;

    public ClientHandler(Socket socket, Chat owner) {
        this.socket = socket;
        this.chat = owner;
    }

    @Override
    public String getClientName() {
        return login;
    }

    @Override
    public void sendMessage(String what) {
        try {
            socket.getOutputStream().write((what + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();
                if (line.startsWith("login ")) {
                    login = line.replace("login ", "");
                    chat.broadcast(this, getClientName() + " joined chat");
                }
                else if   (line.toLowerCase(Locale.ROOT).startsWith("quit") ||
                          line.toLowerCase(Locale.ROOT).startsWith("exit")) {
                    chat.broadcast(this, getClientName() + " left chat");
                    break;
                }
                else if (login != null) {
                    chat.broadcast(this, getClientName() + ": " + line);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class GameServer implements Chat {

    private final ArrayList<ChatClient> clients = new ArrayList<>();
    //private Game game;

    public GameServer() {
        //game = new Game();
    }

    @Override
    public synchronized void broadcast(ChatClient who, String what) {
        clients.stream().filter(x -> x != who).forEach(
                x-> x.sendMessage(what)
        );
    }

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 4211));

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("client connecting");
            ClientHandler handler = new ClientHandler(client, this);
            synchronized (this) {
                clients.add(handler);
            }
            handler.start();
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.run();

    }
}
