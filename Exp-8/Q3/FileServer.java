import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 6969; // Change to your desired port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client request in a separate thread
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String requestedFileName = reader.readLine();
            System.out.println("Client requested file: " + requestedFileName);

            // Read the file and send its contents to the client
            File file = new File(requestedFileName);
            if (file.exists()) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        writer.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                writer.println("File not found");
            }

            System.out.println("File transfer completed.");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}