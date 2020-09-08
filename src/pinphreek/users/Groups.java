package pinphreek.users;

import java.util.ArrayList;

public class Groups {

	private ArrayList<Group> groups = new ArrayList<Group>();

	public Groups(ArrayList<Group> data) {
		this.groups = data;
	}

	public Groups() {

	}

	public Group getGroup(String name) {//TODO do errorchecking!!!!!!!!!
		for (Group g : groups) {
			if (g.getName().equalsIgnoreCase(name))
				return g;
		}
		return null;
	}

	public void addGroup(Group g) {
		groups.add(g);
	}

	public void removeGroup(Group g) {
		groups.remove(g);
	}

	public ArrayList<Group> getAllGroups() {
		return this.groups;
	}
	public String[] getAllGroupsInString() {
		String ret[] = new String[this.groups.size()];
		for(int i = 0; i < this.groups.size(); i++) {
			ret[i] = this.groups.get(i).toString();
		}
		return ret;
	}
}
