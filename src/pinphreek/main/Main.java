package pinphreek.main;

import java.io.IOException;

import pinphreek.io.Load;
import pinphreek.network.Server;

public class Main {

	public static void main(String[] args){
		/**
		 * Open Server
		 * possibly encoding
		 * */
		
		/**
		 * Read config file and updates defaults in io.Load
		 * */
		Server server = new Server();
		try {
			Load.readConfigFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unexpected File-error occured!\nGoing with default values.");
		}
		
		/**
		 * Start server with parameters read in by Load.readConfigFile()
		 * */
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unexpected Server-error!");
			System.exit(-1);
		}
	}

}
