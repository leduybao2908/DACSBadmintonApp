package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.ItemModelSell;

public class PaymentItemDAO {

	public static AdminItemDAO getInstanitemDAO()
	{
	return new AdminItemDAO();
	}
	
	public ArrayList<ItemModelSell> selectProductCart() {	
		 ArrayList<ItemModelSell> result = new ArrayList<ItemModelSell>();
   try {
        Connection con = JDBCUtil.getConnection();
 
        Statement st = con.createStatement();
        String sql = "Select * from productcart" ;
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        { 
       	 int ProductID = rs.getInt("ProductIDcart");
        	 String Name = rs.getString("Namesellcart");    
        	 float Price = rs.getFloat("Pricecart");
        	 int StockQuantity = rs.getInt("StockQuantitycart");
        	 int categoryId = rs.getInt("categoryIdcart");

            ItemModelSell item = new ItemModelSell(ProductID, Name, Price, StockQuantity, categoryId);
      result.add(item);
        }
   } catch (Exception e) {
   }
return result;
	    }	
}
