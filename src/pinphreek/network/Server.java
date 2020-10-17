package pinphreek.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDate;

import pinphreek.io.Load;
import pinphreek.io.Save;
import pinphreek.main.Main;
import pinphreek.users.User;

public class Server implements Runnable {

	public Socket client = null;
	
	private BufferedWriter out = null;
	private BufferedReader in = null;
	private User user = null;

	public boolean isPassthrough = false; //yes we will allow this
	public Server(Socket s) {
		this.client = s;
	}

	@Override
	public void run() {

		try {
			// System.out.println("Client: " + client.getRemoteSocketAddress().toString());
			String msg;
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//loginsequence
			writeMessage("Please enter your username. If you are new here, type \"NEW\".");
			msg = readMessage();
			String name = msg;
			if(msg.equalsIgnoreCase("new")) {
				writeMessage("Please enter your name: ");
				this.user = new User();
				this.user.setName(readMessage());
				writeMessage("Please enter your country: ");
				this.user.setCountry(readMessage());
				boolean n = false;
				do {
					writeMessage("Please enter your age: ");
					msg = readMessage();
					try {
						this.user.setAge(Math.abs(Integer.valueOf(msg)));//you can't fool me user!!!!
						n = true;
					} catch (Exception e) {
						continue;
					}
				} while(!n);
				this.user.setRegisteredYear(LocalDate.now().getYear());
				do {
					writeMessage("Please enter your password: ");
					msg = readMessage();
					writeMessage("Please re-enter your password: ");
				} while(!readMessage().equals(msg));
				Save.appendToFile("users.txt", this.user.getUserInStringArray());
				Main.users.add(user);
			}
			else {
				//try to authenticate user
				writeMessage("Please enter your password " + msg);
				user = Load.loadUser(name, "users.txt", msg);
				if(user.getName().isEmpty()) {
					writeMessage("You entered wrong credentials!");
					client.close();
					ClientHandler.peopleOnline--;
					return;
				}
				Main.users.add(user);//just to be able to see who's online
			}
			//
			//we have a valid user here
			//
			writeMessage("Welcome to the server " + this.user.getName() + "!\r\n");
			while (true) {
				writeMessage(">");
				msg = readMessage();
				if (msg.isEmpty())
					continue;
				System.out.println("[" + java.time.LocalTime.now() + "] Recieved: " + msg);
				if(msg.startsWith("!b")) {
					for(int i = 2; i < msg.length(); i++) {
						if(msg.charAt(i) != ' ') {
							ClientHandler.broadcast(msg.substring(i));
							break;
						}
					}
				}
				else if(msg.equalsIgnoreCase("!online")) {
					String s = "Currently online: ";
					for(User u : Main.users) {
						s = s + " " + u.getName();
					}
					writeMessage(s);
					continue;
				}
				else
					writeMessage(msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("FATAL SERVER ERROR!");
				System.exit(-1);
			}
		}
	}

	private String readMessage() throws IOException {// halts program-flow!! needs to be threaded later
		
		return in.readLine().replaceAll("[^!A-Za-z0-9 ]+", "");
	}

	public void writeMessage(String message) throws IOException {
		out.write(message);
		//out.newLine();
		out.flush();
	}
	
	public void sendMessage(String message) throws IOException {
		writeMessage(message);
	}
}
