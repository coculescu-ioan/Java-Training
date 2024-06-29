import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String host, int port, String file_path) {
		
		final String HOST = host;
		final int PORT = port;
		final String FILE_PATH = file_path;
		
		try(Socket socket = new Socket(HOST, PORT);
			OutputStream out = 
					new BufferedOutputStream(socket.getOutputStream())
		){
			File file = new File(FILE_PATH);
			FileInputStream fis = new FileInputStream(file);
			byte[] fileBytes = fis.readAllBytes();
			fis.close();
			
			out.write(fileBytes);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
