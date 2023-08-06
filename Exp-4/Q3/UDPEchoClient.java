import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPEchoClient {
    public static void main(String[] args) {
        final String serverHost = "127.0.0.1";
        final int serverPort = 12345;

        try (Scanner scanner = new Scanner(System.in);
             DatagramSocket clientSocket = new DatagramSocket()) {

            InetAddress serverAddress = InetAddress.getByName(serverHost);

            while (true) {
                System.out.print("Enter a message (or 'exit' to quit): ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String echoedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from server: " + echoedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
