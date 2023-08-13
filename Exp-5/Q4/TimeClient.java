import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1"; // Server's IP address or hostname
        final int SERVER_PORT = 12345; // Server's port
        final int NUM_CLIENTS = 5; // Number of clients to connect

        for (int i = 0; i < NUM_CLIENTS; i++) {
            Thread clientThread = new Thread(() -> {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    String serverResponse = in.readLine();
                    System.out.println("Server says: " + serverResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            clientThread.start();
        }
    }
}
