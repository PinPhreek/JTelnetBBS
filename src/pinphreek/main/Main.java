package pinphreek.main;

import java.io.IOException;

import pinphreek.io.Load;
import pinphreek.network.ClientHandler;
import pinphreek.network.Server;

public class Main {

	public static void main(String[] args){
		/**
		 * Read config file and updates defaults in io.Load
		 * */
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
			ClientHandler handler = new ClientHandler();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
