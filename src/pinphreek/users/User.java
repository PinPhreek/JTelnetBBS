package pinphreek.users;

public class User {

	private String name = null;
	private String country = null;
	private int age = 0;
	
	public User() {}
	public User(String name, String country, int age) {
		this.name = name;
		this.country = country;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public static final String _NAME = "NAME";
	public static final String _COUNTRY = "COUNTRY";
	public static final String _AGE = "AGE";
	public static final String _YEAR_REGISTERED = "YEAR_REGISTERED";
}
