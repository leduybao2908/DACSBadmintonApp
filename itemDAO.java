package dao;
import java.awt.TextField; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import database.JDBCUtil;
import model.productModel;
import model.ItemModelSell;
import model.adminModel;
import model.employeeModel;
import model.productModel;
import view.CasherScreen;
import view.AdminScreen;

public class itemDAO {
	
public AdminScreen AdminitemDAO;	
public CasherScreen CasheritemDAO;	

	public static itemDAO getInstanitemDAO()
		{
		return new itemDAO();
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
	
	public ArrayList<employeeModel> selectAllEmployee() {	
		 ArrayList<employeeModel> result = new ArrayList<employeeModel>();
     try {
          Connection con = JDBCUtil.getConnection();
   
          Statement st = con.createStatement();
          String sql = "Select * from employees" ;
          ResultSet rs = st.executeQuery(sql);
          while(rs.next())
          { 
       	   int EmployeeID = rs.getInt("EmployeeID");
       	   String EmployeeName = rs.getString("Name");    
       	   String EmployeeGmail = rs.getString("Gmail");
       	   String EmployeePass = rs.getString("Password");
       	   String EmployeePhoneNumber = rs.getString("PhoneNumber");
       	   float EmployeeWorkHour = rs.getFloat("WorkHours");

        employeeModel item = new employeeModel (EmployeeID, EmployeeName, EmployeeGmail,EmployeePass, EmployeePhoneNumber, EmployeeWorkHour);
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
	
	/*  
    public ArrayList<ItemModelSell> selectAlltablesell() {	
	 ArrayList<ItemModelSell> result = new ArrayList<ItemModelSell>();
  try {
       Connection con = JDBCUtil.getConnection();

 Statement st = con.createStatement();
  String sql = "Select * from productcart" ;
 ResultSet rs = st.executeQuery(sql);
 while(rs.next())
 { 
	 int ProductID = rs.getInt("ProductIDcart");
 	 String Name = rs.getString("Namecart");    
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
    */
	
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

		public int insert(productModel t) {
			
			  try {
			        Connection con = JDBCUtil.getConnection();
			        String sql = "INSERT INTO products (ProductID, Name, StockQuantity, Price, categoryId) " +
			                     "VALUES (?, ?, ?, ?, ?)";
			        			      
			        try (PreparedStatement pst = con.prepareStatement(sql)) {
			            pst.setInt(1, t.getProductID());
			            pst.setInt(2, t.getCategoryId());
			            pst.setString(3, t.getName());
			            pst.setInt(4, t.getStockQuantity());
			            pst.setFloat(5, t.getPrice());
			            
			            int result = pst.executeUpdate();			            
			            return result;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Cannot insert item, please check information.");
			    }
			    return 0;
		}
		
		public int inserttablesell(ItemModelSell t) {			
			  try {
			        Connection con = JDBCUtil.getConnection();
			        String sql = "INSERT INTO productcart (ProductIDcart ,Namesellcart,Pricecart, StockQuantitycart, categoryIdcart) " +
			                     "VALUES (?, ?, ?, ?, ?)";
			        			      
			        try (PreparedStatement pst = con.prepareStatement(sql)) {
			            pst.setInt(1, t.getProductIDcart());
			            pst.setString(2, t.getNamecart());
			            pst.setFloat(3, t.getPricecart());
			            pst.setFloat(4, t.getStockQuantitycart());
			            pst.setInt(5, t.getCategoryIdcart());

			            int result = pst.executeUpdate();			            
			            return result;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Cannot insert item, please check information.");
			    }
			    return 0;
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
		
		public int delete(int iditem) {
			   try {
			        Connection con = JDBCUtil.getConnection();
			        
			        // Sử dụng PreparedStatement để tránh SQL Injection và tăng hiệu suất
			        String sql = "DELETE FROM item WHERE iditem = ?";
			        try (PreparedStatement pst = con.prepareStatement(sql)) {
			            pst.setInt(1, iditem);

			            int result = pst.executeUpdate();
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        // Xử lý exception (quay lại mã lỗi hoặc thông báo lỗi)
			    }
			    return 0;	
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
		
		public int insertloginCashier(employeeModel t) {
			
			  try {
			        Connection con = JDBCUtil.getConnection();
			        String sql = "INSERT INTO employees (EmployeeID, Name, Gmail ,Password, PhoneNumber, WorkHours) " +
			                     "VALUES (?, ?, ?, ?, ?, ?)";
			        			      
			        try (PreparedStatement pst = con.prepareStatement(sql)) {
			            pst.setInt(1, t.getEmployeeID());
			            pst.setString(2, t.getName());
			            pst.setString(3, t.getGmail());
			            pst.setString(4, t.getPassword());
			            pst.setString(5, t.getPhoneNumber());
			            pst.setFloat(6, t.getWorkHours());
			           
			            System.out.println("Bạn đã thực thi " + sql);
			            int result = pst.executeUpdate();			            
			            return result;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Cannot insert user, please check information.");
			    }
			    return 0;
		}
		
		public int insertloginAdmin(adminModel admin) {
			
			  try {
			        Connection con = JDBCUtil.getConnection();
			        String sql = "INSERT INTO admins (AdminID,Name, Email, Password) " +
			                     "VALUES (?, ?, ?, ?)";
			        			      
			        try (PreparedStatement pst = con.prepareStatement(sql)) {
			            pst.setInt(1, admin.getIdadmin());
			            pst.setString(2, admin.getNameadmin());
			            pst.setString(3, admin.getPasswordadmin());
			            pst.setString(4, admin.getEmailadmin());
			           
			            System.out.println("Bạn đã thực thi " + sql);
			            int result = pst.executeUpdate();			            
			            return result;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Cannot insert user, please check information.");
			    }
			    return 0;
		}
		
		public boolean checkLogin(String username, String password) {
	        try {
	            Connection con = JDBCUtil.getConnection();
	            String sql = "SELECT * FROM employees WHERE Name = ? AND Password = ?";
	            
	            try (PreparedStatement pst = con.prepareStatement(sql)) {
	                pst.setString(1, username);
	                pst.setString(2, password);

	                ResultSet rs = pst.executeQuery();

	                // Check if the result set has a row
	                return rs.next();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
		public int deleteTableSell() {
		    try {
		        Connection con = JDBCUtil.getConnection();
		        // Sử dụng PreparedStatement để tránh SQL Injection và tăng hiệu suất
		        String sql = "DELETE FROM itemsell";

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
		public productModel select(int iditem) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public int getEmployeeCount() {
		    int count = 0;
		    try {
		        Connection con = JDBCUtil.getConnection();
		        String sql = "SELECT COUNT(*) AS count FROM employees";
		        
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
		
		public int getAdminCount() {
		    int count = 0;
		    try {
		        Connection con = JDBCUtil.getConnection();
		        String sql = "SELECT COUNT(*) AS count FROM admins";
		        
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

		 public productModel selectById(int itemId) {
			 Connection con = JDBCUtil.getConnection();
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;
		        productModel item = null;

		        try {
		            // SQL query to select an item by ID
		            String sql = "SELECT * FROM item WHERE iditem = ?";
		            preparedStatement = con.prepareStatement(sql);
		            preparedStatement.setInt(1, itemId);

		            resultSet = preparedStatement.executeQuery();

		            // Check if the result set has a row
		            if (resultSet.next()) {
		                // Extract data from the result set and create an productModel object
		            	int ProductID = resultSet.getInt("ProductID");
		            	 int categoryId = resultSet.getInt("categoryId");
		            	 String Name = resultSet.getString("Name");
		            	 int StockQuantity = resultSet.getInt("StockQuantity");
		            	 float Price = resultSet.getFloat("Price");

		                item = new productModel(ProductID, Name, Price, StockQuantity, categoryId);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle or log the exception as needed
		        } finally {
		            // Close resources (ResultSet, PreparedStatement, and Connection)
		            try {
		                if (resultSet != null) resultSet.close();
		                if (preparedStatement != null) preparedStatement.close();
		                if (con != null) con.close();
		            } catch (SQLException e) {
		                e.printStackTrace(); // Handle or log the exception as needed
		            }
		        }
		        return item;
		    }
}
