package pinphreek.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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

	public static User loadUser(String name, String path, String password) {
		User ret = new User();
		
		File f = new File(path);
		if(!f.exists()) {
			try {
				f.createNewFile();
				FileWriter w = new FileWriter(f);
				w.write(User._NAME + ":admin\r\n");
				w.write(User._COUNTRY + ":DE\r\n");
				w.write(User._AGE + ":100\r\n");
				w.write(User._YEAR_REGISTERED + ":" + LocalDate.now().getYear() + "\r\n");
				w.write(User._PASSWORD + ": \r\n");
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
				String str = sc.nextLine();
				if(str.startsWith(User._NAME) && ret.getName().equals(null)) { //This code has bugs
					if(str.split(":")[1].equalsIgnoreCase(name)) {
						ret.setName(str.split(":")[1]);
					}
				}
				else if(str.startsWith(User._COUNTRY) && ret.getCountry().equals(null) && !ret.getName().equals(null)) {
					ret.setCountry(str.split(":")[1]);
				}
				else if(str.startsWith(User._AGE) && !ret.getName().equals(null)) {
					ret.setAge(ret.getAge() + Integer.valueOf(str.split(":")[1])); //assemble my creations!
				}
				else if(str.startsWith(User._YEAR_REGISTERED) && !ret.getName().equals(null)) {
					ret.setAge(ret.getAge() + Math.abs(Integer.valueOf(str.split(":")[1]) - LocalDate.now().getYear())); //basicly wichcraft
				}
				else if(str.startsWith(User._PASSWORD) && !ret.getName().equals(null)) {
					if(ret.getName().equalsIgnoreCase("admin") && str.split(":")[1].equals(" ")) {
						ret.isLoggedIn = true;
					}
					else {
						ret.isLoggedIn = str.split(":")[1].equals(password) && name.equalsIgnoreCase(ret.getName());
					}
				}
				if(ret.getName().equalsIgnoreCase(name)) {
					break; //to close the scanner propperly
				}
				ret = new User(); //if no user was found, or garbage is in there, get rid of it
			}
			sc.close();
			return ret; //later needs to be checked, if the user requested has a name
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error reading users-file at " + f.getAbsolutePath());
		}
		return ret;
	}

}









