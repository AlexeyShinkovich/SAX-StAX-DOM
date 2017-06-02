package by.htp.family;

public class Child extends Person {

	private String gender;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return getName() + " " + getSurname() + " " + gender + " " + date;

	}
}