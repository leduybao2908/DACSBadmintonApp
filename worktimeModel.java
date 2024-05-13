package model;
import java.util.Date; 
import java.sql.*;

public class worktimeModel {
	private int Logid;
	private int CashierId;
	private Date LogDate;
	private Date WorkHour;
	public worktimeModel(int logid, int cashierId, Date logDate, Date workHour) {
		super();
		Logid = logid;
		CashierId = cashierId;
		LogDate = logDate;
		WorkHour = workHour;
	}
	public worktimeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLogid() {
		return Logid;
	}
	public void setLogid(int logid) {
		Logid = logid;
	}
	public int getCashierId() {
		return CashierId;
	}
	public void setCashierId(int cashierId) {
		CashierId = cashierId;
	}
	public Date getLogDate() {
		return LogDate;
	}
	public void setLogDate(Date logDate) {
		LogDate = logDate;
	}
	public Date getWorkHour() {
		return WorkHour;
	}
	public void setWorkHour(Date workHour) {
		WorkHour = workHour;
	}
public Object[] toObject() {
	return new Object[]{"" + Logid, CashierId, LogDate, WorkHour};
}
}
