import java.util.ArrayList;
import java.util.List;

public class SubnetAssignment {
    public static void main(String[] args) {
        // Subnet information for Partition 1
        String subnet = "192.168.0.0";
        int subnetSize = 27; // /27 subnet mask

        // Number of computers in Partition 1
        int numComputers = 20;

        // Generate and print IP addresses for Partition 1
        List<String> ipAddresses = generateIPAddresses(subnet, subnetSize, numComputers);
        for (String ipAddress : ipAddresses) {
            System.out.println("Computer IP: " + ipAddress);
        }
    }

    public static List<String> generateIPAddresses(String subnet, int subnetSize, int numComputers) {
        List<String> ipAddresses = new ArrayList<>();
        String[] parts = subnet.split("\\.");

        int startAddress = Integer.parseInt(parts[3]);

        for (int i = 0; i < numComputers; i++) {
            int currentAddress = startAddress + i;
            String ipAddress = parts[0] + "." + parts[1] + "." + parts[2] + "." + currentAddress;
            ipAddresses.add(ipAddress);
        }

        return ipAddresses;
    }
}
