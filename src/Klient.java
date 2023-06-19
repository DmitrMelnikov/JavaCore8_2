import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Klient {
    public static void main(String[] args) throws IOException {

        try (Socket clientSocket = new Socket("netology.homework", 8082);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println(clientSocket.getInetAddress()); // отправляю свой адрес
            Scanner inn = new Scanner(System.in);
            String resp = in.readLine();
            System.out.println(resp);
            while (true) {
                resp = in.readLine();
                System.out.println(resp);
                if (resp.contains("Welcome")) {
                    inn.close();
                    break;
                }
                String num = inn.nextLine();
                out.println(num);
            }
        }
    }
}

