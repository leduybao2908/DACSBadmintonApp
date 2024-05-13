package model;

public class ItemModelSell {
	private int ProductIDcart;
	private int categoryIdcart;
	private String Namecart;
	private int StockQuantitycart;
	private float Pricecart;
	
	public ItemModelSell() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemModelSell(int productIDcart, String namecart, float pricecart, int stockQuantitycart, int categoryidcart) {
		super();
		ProductIDcart = productIDcart;
		categoryIdcart = categoryidcart;
		Namecart = namecart;
		StockQuantitycart = stockQuantitycart;
		Pricecart = pricecart;
	}
	public int getProductIDcart() {
		return ProductIDcart;
	}
	public void setProductIDcart(int ProductIDcart) {
		this.ProductIDcart = ProductIDcart;
	}
	public int getCategoryIdcart() {
		return categoryIdcart;
	}
	public void setCategoryIdcart(int categoryIdcart) {
		this.categoryIdcart = categoryIdcart;
	}
	public String getNamecart() {
		return Namecart;
	}
	public void setNamecart(String Namecart) {
		this.Namecart = Namecart;
	}
	public int getStockQuantitycart() {
		return StockQuantitycart;
	}
	public void setStockQuantitycart(int StockQuantitycart) {
		this.StockQuantitycart = StockQuantitycart;
	}
	public float getPricecart() {
		return Pricecart;
	}
	public void setPrice(float Pricecart) {
		this.Pricecart = Pricecart;
	}
	public Object[] toObject() {
		return new Object[]{ "" + ProductIDcart, Namecart, Pricecart, StockQuantitycart, categoryIdcart};
	}

}
