import java.io.*;
import java.net.*;

public class LilyAntServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Lily Ant Server listening on port " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Oliver Ant connected: " + clientSocket.getInetAddress());

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while ((clientMessage = clientReader.readLine()) != null) {
                        System.out.println(clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            String consoleInput;
            while ((consoleInput = consoleReader.readLine()) != null) {
                clientWriter.println("Lily Ant Server: " + consoleInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
