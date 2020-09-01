package pinphreek.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pinphreek.config.Config;
import pinphreek.users.User;

public class Load {



	private static File cfgFile = new File("config.txt");

	public static void readConfigFile(String path) throws IOException {

		if (path.isEmpty())return;

		cfgFile = new File(path);
		if (!cfgFile.exists()) {
			/**
			 * Creates a new File if not existing and writes default values to it.
			 */
			cfgFile.createNewFile();

			FileWriter w = new FileWriter(cfgFile);
			w.write(Config.PORT + ":" + Config.port + "\r\n");
			w.write(Config.MOTD + ":" + Config.motd.replace(" ", "_") + "\r\n");
			w.write(Config.MAX_CONNECTIONS + ":" + Config.maxConnections + "\r\n");
			w.flush();
			w.close();
			return;
		}

		Scanner sc = new Scanner(cfgFile);
		String s = "", s2 = "";

		while (sc.hasNextLine()) {

			s = sc.nextLine();
			if (s.toLowerCase().contains(Config.PORT.toLowerCase())) {
				s2 = s.replace(" ", "");
				try {
					Config.port = Integer.valueOf(s2.split(":")[1]);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Mal-formated config file!");
					sc.close();
					/*I said TAKE THE DEFAULTS!!!!!!*/
					return;
				}
			}
			else if (s.toLowerCase().contains(Config.MOTD.toLowerCase())) {
				s2 = s.replace(" ", "").replace("_", " ");
				Config.motd = s2.split(":")[1];
			}
			else if (s.toLowerCase().contains(Config.MAX_CONNECTIONS.toLowerCase())) {
				s2 = s.replace(" ", "");
				try {
					Config.maxConnections = Integer.valueOf(s2.split(":")[1]);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Mal-formated config file!");
					sc.close();
					/*I said TAKE THE DEFAULTS!!!!!!*/
					return;
				}
			}
		}
		sc.close();
	}

	public static User loadUser(String name, String path, String login) {
		User ret = new User();
		
		File f = new File(path);
		if(!f.exists()) {
			try {
				f.createNewFile();
				FileWriter w = new FileWriter(f);
				w.write(User._NAME + ":admin\r\n");
				w.write(User._COUNTRY + ":DE\r\n");
				w.write(User._AGE + "100\r\n");
				w.write(User._YEAR_REGISTERED + "\r\n");
				w.write("###\r\n");
				w.flush();
				w.close();
				System.out.println("Config-file created successfully!");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Failed to create users-file at " + f.getAbsolutePath());
			}
		}
		/*only return a user when right login-credentials*/
		try {
			Scanner sc = new Scanner(f);
			
			while(sc.hasNextLine()) {
			
				//TODO finish this....
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error reading users-file at " + f.getAbsolutePath());
		}
		return ret;
	}

}









