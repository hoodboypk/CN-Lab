import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is listening for incoming connections...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // TCP Handshake (Server side)
            // Step 1: Receive SYN from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String synMessage = in.readLine();
            System.out.println("Received SYN from client.");

            // Step 2: Send SYN-ACK to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("SYN-ACK");
            System.out.println("Sent SYN-ACK to client.");

            // Step 3: Receive ACK from the client
            String ackMessage = in.readLine();
            System.out.println("Received ACK from client. TCP Handshake completed.");

            // Data transfer can happen now
            // Add your data transfer logic here...

            // Close the streams and sockets
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

