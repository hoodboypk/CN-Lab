import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RemoteDesktopServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5900); // VNC default port

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                OutputStream outputStream = clientSocket.getOutputStream();
                // Send a basic static image (Pretending to be a remote desktop)
                String imageData = "RFB 003.003\n";
                byte[] imageDataBytes = imageData.getBytes(StandardCharsets.UTF_8);
                outputStream.write(imageDataBytes);

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
