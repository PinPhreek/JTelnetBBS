package pinphreek.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import pinphreek.config.Config;

public class Server {

	private ServerSocket servSocket;
	private Socket client = null;
	
	public Server() {}
	
	public void start() throws IOException {
		
		servSocket = new ServerSocket(Config.port);
		System.out.println("Server has started!");
		client = servSocket.accept();//need a handler here
		System.out.println("Client: " + client.getRemoteSocketAddress().toString());
		String msg;
		while(true) {
			msg = readMessage(client);
			if(msg.isEmpty()) continue;
			System.out.println("[" + java.time.LocalTime.now() + "] Recieved: " + msg);
			writeMessage(client, msg);
		}
		
	}
	private String readMessage(Socket s) throws IOException {//halts program-flow!! needs to be threaded later
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
	 	char[] buffer = new char[200];
	 	bufferedReader.read(buffer, 0, 200);
	 	String ret =new String(buffer, 0, bufferedReader.read(buffer, 0, 200)).replace("\n", "").replace("\r", ""); 
	 	for(int i = 0; i < buffer.length; i++) {buffer[i] = '\0';}
	 	return ret;
	}
	public void writeMessage(Socket s, String message) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
	 	printWriter.print(message);
	 	printWriter.flush();
	}
}
