public class Employee {
    private int employeeID;
    private String name;
    private String password;
    private String phoneNumber;
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Employee(int employeeID, String name, String password, String phoneNumber) {
        this.employeeID = employeeID;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    
   
}