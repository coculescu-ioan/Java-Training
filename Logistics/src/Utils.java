import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Utils {

	public static void serialize(List<TruckExpense> list, String fileName) {
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))
		) {
			oos.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<TruckExpense> deserialize(byte[] data) {
		try(ObjectInputStream ois = 
				new ObjectInputStream(new ByteArrayInputStream(data))
		){
			return (List<TruckExpense>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
