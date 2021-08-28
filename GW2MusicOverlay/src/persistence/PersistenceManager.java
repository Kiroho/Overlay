package persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class PersistenceManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	public ArrayList<String> loadFile(String path) throws IOException, ClassNotFoundException {
		ArrayList<String> inputRead= new ArrayList<String>();
		Scanner fileScanner = new Scanner(new File(path));
		while (fileScanner.hasNext()){
			inputRead.add(fileScanner.nextLine());
		}
		fileScanner.close();
	return inputRead;
	}
	
	//Noch ungetestet
	public void saveSong ( String path, ArrayList<String[]> data) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		OutputStreamWriter objectOut = new OutputStreamWriter(fileOut);
		for(int i=0; i<data.size(); i++) {
			if(data.get(i)[0]== "input" || data.get(i)[0] == "sleep") {
				objectOut.write(data.get(i)[0] + " " + data.get(i)[1] + " " + data.get(i)[2] + "\n");
			}
		}
		objectOut.close();
	}
	
	//Noch ungetestet
	public void saveConfig(String path, ArrayList<String> data) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		for(int i=0; i<data.size(); i++) {
			objectOut.writeObject(data.get(i));
		}
		objectOut.close();
	}
	
	
	
}
