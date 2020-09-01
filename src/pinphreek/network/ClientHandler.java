package pinphreek.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pinphreek.config.Config;

public class ClientHandler {

	Socket client;
	ServerSocket server; 
	public static ArrayList<Server> clients = new ArrayList<Server>(); 
	public static ExecutorService pool = Executors.newFixedThreadPool(Config.maxConnections);
	public static int peopleOnline = 0;
	
	public ClientHandler() throws IOException {
		server = new ServerSocket(/*Config.port*/1025);
		System.out.println("Waiting for connections....");
		while(true) {
		
			client = server.accept();
			peopleOnline++;
			System.out.println(client.getRemoteSocketAddress() + " has connected to the server.");
			System.out.printf("%3d connections remaining.\n", (Config.maxConnections - peopleOnline));
			Server serv = new Server(client);
			clients.add(serv);
			pool.execute(serv);
			broadcast(client.getInetAddress().getHostName() + " has joined the server!\n");
		}
	}
	public void broadcast(String message) throws IOException {
		
		for(int i = 0; i < clients.size(); i++) {
			clients.get(i);
			Server.writeMessage(clients.get(i).client, message);
		}
		
	}
}
