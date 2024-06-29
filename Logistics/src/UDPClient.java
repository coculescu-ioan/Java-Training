import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java UDPClient <host> <port> <file_path>");
            return;
        }

        final String HOST = args[0];
        final int PORT = Integer.parseInt(args[1]);
        final String FILE_PATH = args[2];

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(HOST);

            File file = new File(FILE_PATH);
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes = fis.readAllBytes();
            fis.close();

            DatagramPacket packet = 
            		new DatagramPacket(fileBytes, fileBytes.length, address, PORT);
            
            socket.send(packet);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
