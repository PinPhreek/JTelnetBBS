package pinphreek.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Load {

	public static int port = 23;
	public static String motd = "Welcome to this server!";

	private static File cfgFile = new File("config.txt");;

	public static void readConfigFile() throws IOException {

		if (!cfgFile.exists()) {
			/**
			 * Creates a new File if not existing and writes default values to it.
			 */
			cfgFile.createNewFile();

			FileWriter w = new FileWriter(cfgFile);
			w.write(PORT + ":" + port);
			w.write(MOTD + ":" + motd);
			w.flush();
			w.close();
			return;
		}

		Scanner sc = new Scanner(cfgFile);
		String s = "", s2 = "";

		while (sc.hasNextLine()) {

			s = sc.nextLine();
			if (s.toLowerCase().contains(PORT.toLowerCase())) {
				s2 = s.replace(" ", "");
				try {
					port = Integer.valueOf(s2.split(":")[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (s.toLowerCase().contains(MOTD.toLowerCase())) {
				s2 = s.replace(" ", "");
				port = Integer.valueOf(s2.split(":")[1]);
			}
		}
		sc.close();
	}
	public static void readConfigFile(String path) throws IOException {

		if (path.isEmpty())return;

		cfgFile = new File(path);
		if (!cfgFile.exists()) {
			/**
			 * Creates a new File if not existing and writes default values to it.
			 */
			cfgFile.createNewFile();

			FileWriter w = new FileWriter(cfgFile);
			w.write(PORT + ":" + port);
			w.write(MOTD + ":" + motd);
			w.flush();
			w.close();
			return;
		}

		Scanner sc = new Scanner(cfgFile);
		String s = "", s2 = "";

		while (sc.hasNextLine()) {

			s = sc.nextLine();
			if (s.toLowerCase().contains(PORT.toLowerCase())) {
				s2 = s.replace(" ", "");
				try {
					port = Integer.valueOf(s2.split(":")[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (s.toLowerCase().contains(MOTD.toLowerCase())) {
				s2 = s.replace(" ", "");
				port = Integer.valueOf(s2.split(":")[1]);
			}
		}
		sc.close();
	}

	// TODO put in array #short code
	public static String PORT = "PORT";
	public static String MOTD = "MOTD";

}
