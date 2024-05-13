package model;

import java.util.Date; 
import java.sql.*;

public class billModel {
private int idbill;
private Date saledate;
private int totalcount;
private String customername;
private String customerphone;
public billModel(int idbill, Date saledate, int totalcount, String customername, String customerphone) {
	super();
	this.idbill = idbill;
	this.saledate = saledate;
	this.totalcount = totalcount;
	this.customername = customername;
	this.customerphone = customerphone;
}
public billModel() {
	super();
	// TODO Auto-generated constructor stub
}
public int getIdbill() {
	return idbill;
}
public void setIdbill(int idbill) {
	this.idbill = idbill;
}
public Date getSaledate() {
	return saledate;
}
public void setSaledate(Date saledate) {
	this.saledate = saledate;
}
public int getTotalcount() {
	return totalcount;
}
public void setTotalcount(int totalcount) {
	this.totalcount = totalcount;
}
public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public String getCustomerphone() {
	return customerphone;
}
public void setCustomerphone(String customerphone) {
	this.customerphone = customerphone;
}
public Object[] toObject() {
	return new Object[]{"" + idbill, saledate, totalcount, customername, customerphone};
}
}
