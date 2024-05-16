package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import database.JDBCUtil;
import model.ItemModelSell;
import model.productModel;

public class CashierItemDAO {

	public static AdminItemDAO getInstanitemDAO()
	{
	return new AdminItemDAO();
	}
	
	public ArrayList<productModel> selectAll() {	
		 ArrayList<productModel> result = new ArrayList<productModel>();
     try {
          Connection con = JDBCUtil.getConnection();
   
          Statement st = con.createStatement();
          String sql = "Select * from products" ;
          ResultSet rs = st.executeQuery(sql);
          while(rs.next())
          { 
       	   int ProductID = rs.getInt("ProductID");
       	   String Name = rs.getString("Name");    
       	   float Price = rs.getFloat("Price");
       	   int StockQuantity = rs.getInt("StockQuantity");
       	   int categoryId = rs.getInt("categoryId");

        productModel item = new productModel(ProductID, Name, Price, StockQuantity, categoryId);
        result.add(item);
          }
     } catch (Exception e) {
     }
  return result;
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

	public int deletesalecart(int ProductIDcart) {
		   try {
		        Connection con = JDBCUtil.getConnection();
		        String sql = "DELETE FROM productcart WHERE ProductIDcart = ?";
		        try (PreparedStatement pst = con.prepareStatement(sql)) {
		            pst.setInt(1, ProductIDcart);
		            int result = pst.executeUpdate();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return 0;	
	}
	
	public ArrayList<productModel> searchByName(String itemName) {
        ArrayList<productModel> result = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products WHERE Name LIKE ?";
            
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, "%" + itemName + "%");

                ResultSet rs = pst.executeQuery();
                
                while (rs.next()) {
                	int ProductID = rs.getInt("ProductID");
                	 int categoryId = rs.getInt("categoryId");
                	 String Name = rs.getString("Name");
                	 int StockQuantity = rs.getInt("StockQuantity");
                	 float Price = rs.getFloat("Price");

                    productModel item = new productModel(ProductID, Name, Price, StockQuantity, categoryId);
                    result.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        }
	
	public int update(productModel t) {
	    try {
	        Connection con = JDBCUtil.getConnection();
	        
	        // Sử dụng PreparedStatement để tránh SQL Injection và tăng hiệu suất
	        String sql = "UPDATE products "
	                + "SET Name = ?, Price = ?, StockQuantity = ?, categoryId= ? "
	                + "WHERE ProductID = ?";

	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	        	 pst.setString(1, t.getName());
		            pst.setFloat(2, t.getPrice());
		            pst.setInt(3, t.getStockQuantity());
		            pst.setInt(4, t.getCategoryId());
		            pst.setInt(5, t.getProductID());

	            // Thực hiện câu lệnh SQL và lấy kết quả
	            int result = pst.executeUpdate();
	            return result;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Cannot update item, please check information.");
	    }
	    return 0;
	}
}
