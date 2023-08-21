import java.io.*;
import java.net.*;

public class LeoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Leo the Lion is waiting for Miko the Monkey...");

            Socket socket = serverSocket.accept();
            System.out.println("Miko the Monkey connected!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Leo: ");
                message = consoleReader.readLine();
                writer.println(message);

                if (message.equalsIgnoreCase("bye")) {
                    break;
                }

                String receivedMessage = reader.readLine();
                System.out.println("Miko: " + receivedMessage);

                if (receivedMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

