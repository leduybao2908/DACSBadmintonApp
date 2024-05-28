package model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private int employeeID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Gmail")
    private String gmail;

    @Column(name = "Password")
    private String password;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "WorkHours")
    private Integer workHours;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String gmail, String password, String phoneNumber, Integer workHours) {
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.workHours = workHours;
    }

    // Getters and Setters
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
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

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }
}
