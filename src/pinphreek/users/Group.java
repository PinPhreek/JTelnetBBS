package pinphreek.users;

import java.util.ArrayList;

import pinphreek.io.Load;

public class Group {

	private ArrayList<User> users = new ArrayList<User>();
	private String name = null;
	
	public Group(String name, User args[]) {
		this.name = name;
		for (User u : args) 
			users.add(u);
	}
	public Group(String name, User u) {
		this.name = name;
		users.add(u);
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	public void removeUser(User u) {
		users.remove(u);
	}
	public User getUser(String name) {
		for (User u : users) {
			if(u.getName().equals(name)) {
				return u;
			}
		}
		return null; //better check for errors
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static User[] loadUsersFromString(String users) {
		ArrayList<User> ret = new ArrayList<User>();

		User u;
		for(String s : users.replace("{", "").replace("}", "").split(" ")) {
			u = Load.loadUser(s, "users.txt", "");
			if(u.getName().equals(null)) 				
				continue;
			ret.add(u);
		}
		return (User[]) ret.toArray();
	}
	
	@Override
	public String toString() {
		String s = "[" + this.getName() + "]{";
		for(User u : users) {
			s = s + " " + u.getName();
		}
		return s + "}";
		
	}
	
	public static final String NAME = "NAME";
}

