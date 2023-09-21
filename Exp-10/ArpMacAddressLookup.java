import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class ArpMacAddressLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target IP address: ");
        String targetIp = scanner.nextLine();
        scanner.close();

        String macAddress = getMacAddress(targetIp);

        if (macAddress != null) {
            System.out.println("MAC address of " + targetIp + ": " + macAddress);
        } else {
            System.out.println("Could not find MAC address for " + targetIp);
        }
    }

    public static String getMacAddress(String ipAddress) {
        try {
            ProcessBuilder builder = new ProcessBuilder("arp", "-a", ipAddress);
            builder.redirectErrorStream(true); // Redirect error stream to input stream
            Process process = builder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(ipAddress)) {
                        // Use a regular expression to extract the MAC address
                        Pattern pattern = Pattern.compile("([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            return matcher.group(0);
                        }
                    }
                }
            }

            int exitCode = process.waitFor(); // Wait for the process to complete
            if (exitCode != 0) {
                System.err.println("Error: arp command failed with exit code " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }
}
