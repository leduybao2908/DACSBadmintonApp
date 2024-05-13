package model;

public class productModel {
	private int ProductID;
	private int categoryId;
	private String Name;
	private int StockQuantity;
	private float Price;
	
	public productModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public productModel(int productID, String name, float price, int stockQuantity, int categoryId) {
		super();
		ProductID = productID;
		this.categoryId = categoryId;
		Name = name;
		StockQuantity = stockQuantity;
		Price = price;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int ProductID) {
		this.ProductID = ProductID;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public int getStockQuantity() {
		return StockQuantity;
	}
	public void setStockQuantity(int StockQuantity) {
		this.StockQuantity = StockQuantity;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float Price) {
		this.Price = Price;
	}
	public Object[] toObject() {
		return new Object[]{ "" + ProductID, Name, Price, StockQuantity, categoryId};
	}

}
