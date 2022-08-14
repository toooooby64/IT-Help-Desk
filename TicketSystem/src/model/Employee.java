package model;

public class Employee {



	private long employeeID;
	private String firstName;
	private String lastName;
	private String title;
	private String password;

	public Employee(long employeeID, String firstName, String lastName, String title, String password) {
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.password = password;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName + ", title="
				+ title + ", password=" + password + "]";
	}

}
