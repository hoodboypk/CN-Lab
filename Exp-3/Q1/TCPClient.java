import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8080);

            // TCP Handshake (Client side)
            // Step 1: Send SYN to the server
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("SYN");
            System.out.println("Sent SYN to server.");

            // Step 2: Receive SYN-ACK from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String synAckMessage = in.readLine();
            System.out.println("Received SYN-ACK from server.");

            // Step 3: Send ACK to the server
            out.println("ACK");
            System.out.println("Sent ACK to server. TCP Handshake completed.");

            // Data transfer can happen now
            // Add your data transfer logic here...

            // Close the streams and socket
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

