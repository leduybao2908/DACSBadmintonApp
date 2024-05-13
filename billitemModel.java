package model;
import model.billModel;

public class billitemModel {
	private int BillItemID;
	private int billid;
	private int productid;
	private int countproduct;
	private float saleprice;
	public billitemModel(int billItemID, int billid, int productid, int countproduct, float saleprice) {
		super();
		BillItemID = billItemID;
		this.billid = billid;
		this.productid = productid;
		this.countproduct = countproduct;
		this.saleprice = saleprice;
	}
	public billitemModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBillItemID() {
		return BillItemID;
	}
	public void setBillItemID(int billItemID) {
		BillItemID = billItemID;
	}
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getCountproduct() {
		return countproduct;
	}
	public void setCountproduct(int countproduct) {
		this.countproduct = countproduct;
	}
	public float getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}
	public Object[] toObject() {
		return new Object[]{"" + BillItemID, billid, productid, countproduct, saleprice};
	}
}
