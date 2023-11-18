import java.io.IOException;

import com.tightvnc.vncviewer.VncViewer;
import com.tightvnc.vncviewer.VncViewerThread;

public class RemoteDesktopClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change to the server's IP or hostname
        int serverPort = 5900; // VNC default port

        try {
            VncViewer viewer = new VncViewer();
            VncViewerThread viewerThread = new VncViewerThread(viewer, serverAddress, serverPort);
            viewerThread.start();
            viewerThread.join(); // Wait for the viewer to finish (you can implement your own handling)
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
