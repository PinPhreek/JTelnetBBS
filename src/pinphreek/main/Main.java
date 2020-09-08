package pinphreek.main;

import java.io.IOException;

import pinphreek.io.Load;
import pinphreek.network.ClientHandler;
import pinphreek.users.Groups;

public class Main {

	public static ClientHandler handler;
	public static void main(String[] args){
		/**
		 * Read config file and updates defaults in io.Load
		 * */
		@SuppressWarnings("unused")
		Groups groups;
		try {
			Load.readConfigFile("config.txt");
			groups = new Groups(Load.getSavedGroups("groups.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unexpected File-error occured!\nGoing with default values.");
		}
		
		/**
		 * Start server with parameters read in by Load.readConfigFile()
		 * */
		try {
			handler = new ClientHandler();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
