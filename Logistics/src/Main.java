import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<TruckExpense> list = new ArrayList<TruckExpense>();
		
		Prices p1 = new Prices(85, 150, 300);
		Prices p2 = new Prices(75, 100, 225);
		Prices p3 = new Prices(80, 120, 275);
		
		list.add(new TruckExpense(1, "ProInsurance", 1000, 350, 175, p1));
		list.add(new TruckExpense(2, "Protect4Real", 1250, 400, 200, p2));
		list.add(new TruckExpense(3, "SafeTravels", 1150, 450, 225, p3));
		
		String fileName = "trucks.dat";
		Utils.serialize(list, fileName);
		
		String host = "localhost";
		int port = 8080;
		
		new Thread(()->{TCPServer.main(port);}).start();
//		new Thread(() -> UDPServer.main(new String[]{String.valueOf(port)})).start();
		
		// Sleep to ensure the server is started before the client sends data
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		TCPClient.main(host, port, fileName);
//        UDPClient.main(new String[]{host, String.valueOf(port), fileName});
	}
}
