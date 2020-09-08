package pinphreek.users;

public class User {

	private String name = null;
	private String country = null;
	private int age = 0;
	private int registeredYear = 0;
	private String password = null;
	public boolean isLoggedIn = false;

	public User() {
	}

	public User(String name, String country, int age, int year) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.registeredYear = year;
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

	public int getRegisteredYear() {
		return this.registeredYear;
	}

	public void setRegisteredYear(int year) {
		this.registeredYear = year;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getUserInStringArray() {
		String ret[] = new String[5];
		ret[0] = _NAME + ":" + this.name;
		ret[1] = _COUNTRY + ":" + this.country;
		ret[2] = _AGE + ":" + String.valueOf(this.age);
		ret[3] = _YEAR_REGISTERED + ":" + String.valueOf(this.registeredYear);
		ret[4] = _PASSWORD + ":" + this.password;
		return ret;
	}

	public static final String _NAME = "NAME";
	public static final String _COUNTRY = "COUNTRY";
	public static final String _AGE = "AGE";
	public static final String _YEAR_REGISTERED = "YEAR_REGISTERED";
	public static final String _PASSWORD = "PASSWORD";
}
