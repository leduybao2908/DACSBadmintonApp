import java.sql.Date;

public class WorkLog {
    private int logID;
    private int employeeID;
    private Date logDate;
    private int workHours;
    public int getLogID() {
        return logID;
    }
    public void setLogID(int logID) {
        this.logID = logID;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public Date getLogDate() {
        return logDate;
    }
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    public int getWorkHours() {
        return workHours;
    }
    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }
    public WorkLog(int logID, Date logDate, int workHours) {
        this.logID = logID;
        this.employeeID = employeeID;
        this.logDate = logDate;
        this.workHours = workHours;
    }

}
