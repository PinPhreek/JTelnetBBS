package pinphreek.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Server implements Runnable {

	public Socket client = null;
	
	@SuppressWarnings("unused")
	private BufferedWriter out = null;
	@SuppressWarnings("unused")
	private BufferedReader in = null;

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
			while (true) {
				writeMessage(">");
				msg = readMessage();
				if (msg.isEmpty())
					continue;
				System.out.println("[" + java.time.LocalTime.now() + "] Recieved: " + msg);
				if(msg.startsWith("!b")) 
					for(int i = 2; i < msg.length(); i++) {
						if(msg.charAt(i) != ' ') {
							ClientHandler.broadcast(msg.substring(i));
							break;
						}
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
