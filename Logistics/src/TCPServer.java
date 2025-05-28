import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TCPServer {

	private static final int N_THREADS = 4;

	public static void main(int port) {

		final int PORT = port;
		ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on port " + PORT);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected: " + clientSocket.getInetAddress());

				Runnable task = new HandleClientTask(clientSocket);
				executorService.submit(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}

	}

	static class HandleClientTask implements Runnable {

		Socket clientSocket;

		public HandleClientTask(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try (InputStream in = new BufferedInputStream(clientSocket.getInputStream())) {
				System.out.println("Received from client:");
				byte[] receivedData = in.readAllBytes();
				List<TruckExpense> list = Utils.deserialize(receivedData);
				for (var l : list) {
					System.out.println(l);
				}

				// Sorting the list in descending order without functional programming
				list.sort(Collections.reverseOrder());

				// Bubble Sort
				// for (int i = 0; i < list.size() - 1; i++) {
				// for (int j = i + 1; j < list.size(); j++) {
				// //if (list.get(i) > list.get(j)) {
				// if(list.get(i).compareTo(list.get(j)) < 0) {
				// TruckExpense temp = list.get(i);
				// list.set(i, list.get(j));
				// list.set(j, temp);
				// }
				// }
				// }

				for (var l : list) {
					System.out.println(l);
				}

				// Functional programming
				double result = list.stream().mapToDouble(TruckExpense::getTotal).sum();
				System.out.println("\nThe total value of expenses: " + result);

				double threshold = 25;
				System.out.println("\nThe list of expenses higher than " + threshold);

				List<TruckExpense> filteredList = list.stream()
						.filter(t -> t.getTotal() > threshold)
						.collect(Collectors.toList());

				for (var f : filteredList) {
					System.out.println(f);
				}

				System.out.println("\nDatabase operations:");
				try {
					DatabaseUtils.openConnection();
					DatabaseUtils.createTable();
					for (var f : filteredList) {
						DatabaseUtils.insertExpense(f);
					}
					List<TruckExpense> resultedList = DatabaseUtils.selectAllExpenses();
					for (var r : resultedList) {
						System.out.println(r);
					}
					DatabaseUtils.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
