package FinalTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class SaveNLoad{

	String filePath;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public SaveNLoad(String n) {
		filePath = n;
	}
	
	public void SaveFile() {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(Bank.accounts);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void LoadFile() {
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			try {
				Object obj = ois.readObject();
				Bank.accounts = (Vector<Account>)obj;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
