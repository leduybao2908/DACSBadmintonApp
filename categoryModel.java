package model;

public class categoryModel {
	private int categoryid;
	private String categoryname;
	public categoryModel(int categoryid, String categoryname) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
	}
	public categoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
public Object[] toObject() {
	return new Object[] {"" + categoryid, categoryname};
}
}
