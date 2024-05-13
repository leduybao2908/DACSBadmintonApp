package model;

public class employeeModel {
	private int EmployeeID;
	private String Name;
	private String Gmail;
	private String Password;
	private String PhoneNumber;
	private float WorkHours;
	// private String CONFIRM;

	
	public employeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public employeeModel(int employeeID, String name, String gmail, String password, String phoneNumber, float workHours) {
		super();
		EmployeeID = employeeID;
		Name = name;
		Gmail = gmail;
		Password = password;
		PhoneNumber = phoneNumber;
		WorkHours = workHours;
	//	CONFIRM = cONFIRM;
	}
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGmail() {
		return Gmail;
	}
	public void setGmail(String gmail) {
		Gmail = gmail;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public float getWorkHours() {
		return WorkHours;
	}
	public void setWorkHours(float workHours) {
		WorkHours = workHours;
	}
	/*
	public String getCONFIRM() {
		return CONFIRM;
	}
	public void setCONFIRM(String cONFIRM) {
		CONFIRM = cONFIRM;
	}
	*/
	public Object[] toObject() {
		return new Object[] { "" + EmployeeID, Name, Gmail, Password, PhoneNumber, WorkHours};
	}
}
