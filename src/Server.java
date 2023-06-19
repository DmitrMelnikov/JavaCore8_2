import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8082);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println("New connection accepted");
            String name = in.readLine();
            String userName = null;
            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            out.println("Write your name?");

            while (true) { //  цикл Сервер будет ждать сообщений от Клиента.
                name = in.readLine(); //получаем сообщение от клиента
                if (!name.equals("no") && !name.equals("yes") && userName == null) {
                    userName = name;
                    out.println("Are you child? (yes/no)");
                } else if (name.equals("no") && userName != null) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName));
                    break;
                } else if (name.equals("yes") && userName != null) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", userName));
                    break;
                }else {
                    out.println("Are you child? (yes/no)");
                }
            }
        }
    }

}