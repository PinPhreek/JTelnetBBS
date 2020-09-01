package pinphreek.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Server implements Runnable {

	public Socket client = null;

	public Server(Socket s) {
		this.client = s;
	}

	@Override
	public void run() {

		try {
			// System.out.println("Client: " + client.getRemoteSocketAddress().toString());
			String msg;
			while (true) {
				Server.writeMessage(client, ">");
				msg = readMessage(client);
				if (msg.isEmpty())
					continue;
				System.out.println("[" + java.time.LocalTime.now() + "] Recieved: " + msg);
				writeMessage(client, msg);
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

	private static String readMessage(Socket s) throws IOException {// halts program-flow!! needs to be threaded later
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		char[] buffer = new char[200];
		bufferedReader.read(buffer, 0, 200);
		String ret = new String(buffer, 0, bufferedReader.read(buffer, 0, 200));
		/*for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '\0';
		}*/
		return ret.replace("\n", "").replace("\r", "");
	}

	public static void writeMessage(Socket s, String message) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		printWriter.print(message);
		printWriter.flush();
	}
	
	public static void sendMessage(Socket s, String message) throws IOException {
		writeMessage(s, message);
	}
}
