package model;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Translog")
public class Translog {
	private int accNo;
	private int logID;
	private Date time;
	private String task;
	 private String encodedAmount;
	private float PostBalance;
	
	public int getAccNo() {
		return accNo;
	}


	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}


	@Id//khoa chinh
	@GeneratedValue//tu dong tang
	public int getLogID() {
		return logID;
	}


	public void setLogID(int logID) {
		this.logID = logID;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	

	public float getPostBalance() {
		return PostBalance;
	}


	public void setPostBalance(float postBalance) {
		PostBalance = postBalance;
	}
	public String getAmount() {
        // Giải mã trường amount khi lấy giá trị
        byte[] decodedBytes = Base64.getDecoder().decode(encodedAmount);
        return new String(decodedBytes);
    }

    public void setAmount(String amount) {
        // Mã hóa trường amount khi thiết lập giá trị
        this.encodedAmount = Base64.getEncoder().encodeToString(amount.getBytes());
    }

   

}
