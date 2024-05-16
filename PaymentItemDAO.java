package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;  
import javax.swing.JOptionPane;
import view.Paymentscreen;
import database.JDBCUtil;
import model.ItemModelSell;
import model.billModel;
import model.productModel;

public class PaymentItemDAO {

	public static PaymentItemDAO getInstanitemDAO()
	{
	return new PaymentItemDAO();
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
	
	public int insert(billModel t) {
		
		  try {
		        Connection con = JDBCUtil.getConnection();
		        String sql = "INSERT INTO bills (BillID, SaleDate, TotalAmount, CustomerName, CustomerPhone) " +
		                     "VALUES (?, ?, ?, ?, ?)";
		        			      
		        try (PreparedStatement pst = con.prepareStatement(sql)) {
		            pst.setInt(1, t.getIdbill());
		            pst.setString(2, t.getSaledate());
		            pst.setFloat(3, t.getTotalcount());
		            pst.setString(4, t.getCustomername());
		            pst.setString(5, t.getCustomerphone());
		            
		            int result = pst.executeUpdate();			            
		            return result;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Cannot insert , please check information.");
		    }
		    return 0;
	}
	
	public int getBillCount() {
	    int count = 0;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT COUNT(*) AS count FROM bills";
	        
	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            ResultSet rs = pst.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle or log the exception as needed
	    }
	    return count;
	}
	
	public int deleteProductcart() {
	    try {
	        Connection con = JDBCUtil.getConnection();
	        // Sử dụng PreparedStatement để tránh SQL Injection và tăng hiệu suất
	        String sql = "DELETE FROM productcart";

	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            // Thực hiện câu lệnh SQL và lấy kết quả
	            int result = pst.executeUpdate();
	            return result;  // You may want to return the number of affected rows
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Xử lý exception (quay lại mã lỗi hoặc thông báo lỗi)
	    }
	    return 0;
	}	
	
}
