package pinphreek.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

	public static void saveToFile(String path, String[] data) {
		File f = new File(path);
		try {
			FileWriter w = new FileWriter(f);
			for (String s : data) {
				w.write(s + "\r\n");
			}
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void appendToFile(String path, String[] data) {
		try {
			FileWriter w = new FileWriter(new File(path));
			for(String s : data) {
				w.append(s + "\r\n");
			}
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
