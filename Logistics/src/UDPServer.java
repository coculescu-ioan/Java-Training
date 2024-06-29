import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPServer {

    private static final int N_THREADS = 4;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java UDPServer <port>");
            return;
        }

        final int PORT = Integer.parseInt(args[0]);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                byte[] buffer = new byte[65535];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);

                System.out.println("Client connected: " + packet.getAddress());

                Runnable task = new HandleClientTask(packet);
                executorService.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    static class HandleClientTask implements Runnable {

        private DatagramPacket packet;

        public HandleClientTask(DatagramPacket packet) {
            this.packet = packet;
        }

        @Override
        public void run() {
        	byte[] receivedData = packet.getData();
            List<TruckExpense> list = Utils.deserialize(receivedData);
            for (var l : list) {
                System.out.println(l);
            }
        }
    }
}
