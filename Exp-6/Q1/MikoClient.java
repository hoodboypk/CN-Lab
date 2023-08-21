import java.io.*;
import java.net.*;

public class MikoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to Leo the Lion!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                String receivedMessage = reader.readLine();
                System.out.println("Leo: " + receivedMessage);

                if (receivedMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                System.out.print("Miko: ");
                message = consoleReader.readLine();
                writer.println(message);

                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
