package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import database.JDBCUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.itemDAO;
import model.ItemModelSell;
import model.employeeModel;
import model.productModel;
import javax.swing.JTabbedPane; 
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class AdminScreen extends JFrame {

	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textField;
	public JTextField TextT0talManage;
	public JTextField TextTotalCart;
	public JTable TableTotalSalesList;
	public JTable TableSalesList;
	public JTable TableTotalManagementList;
	public JTextField textFieldIDItem;
	public JTextField textFieldNameItem;
	public JTextField textFieldOldCount;
	public JTextField textFieldCostInput;
	public DefaultTableModel tableModel;
	public DefaultTableModel tableModelTotalManage;
	public itemDAO itemDAO = new itemDAO() ; 
//	ArrayList<productModel> result = new ArrayList<productModel>();
	public JTextField textFieldIDItemSell;
	public JTextField textFieldNameItemSell;
	public JTextField textFieldCostSell;
	public JTextField textFieldCountNeedBuy;
	public DefaultTableModel tableModelSale;
	public int currentCount;
    public static final String TOTAL_KEY = "totalKey";
	public float totalCost = 0.0f;
	public JTextField textFieldCountSellWarehouse;
	public JTextField textFieldNewCount;
	public JTextField textFieldSearchSell;
	private JTextField textFieldCateID;
	private DefaultTableModel tableModelEmployeeManage;
	private JTable TableEmployeeList;
	private JTextField TextEmployeeName;
	private JTextField TextEmployeePhone;
	private JTextField TextEmployeeGmail;
	private JTextField TextEmployeeWorkHour;
	private JTextField textFieldSearchEmployee;
	private JTextField textFieldFindManageProduct;
	
	public AdminScreen() {
	        // ...
	    //    ChangeScreen newScreenInstance = new ChangeScreen(this); // Pass 'this' as the argument
	        // ...
	    setTitle("Badminton Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		URL url = AdminScreen.class.getResource("52745-badminton-icon.png");
		if (url != null) {
		    Image imagethemgiohang = Toolkit.getDefaultToolkit().createImage(url);
		    this.setIconImage(imagethemgiohang);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setBounds(40, 0, 1000, 700);
		contentPane.add(tabbedPane);
		
		JPanel panelSale = new JPanel();
		tabbedPane.addTab("SELL", null, panelSale, null);
		panelSale.setLayout(null);
		
		JLabel LabelTotalSalesList = new JLabel("LIST OF ITEMS");
		LabelTotalSalesList.setBounds(405, 79, 205, 27);
		LabelTotalSalesList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSale.add(LabelTotalSalesList);
				
		TextTotalCart = new JTextField();
		TextTotalCart.setBounds(224, 623, 237, 40);
		TextTotalCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(TextTotalCart);
		TextTotalCart.setColumns(10);		
		
		tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ProductID", "Name", "Price", "StockQuantity", "CategoryId"});
		TableTotalSalesList = new JTable(tableModel);
		TableTotalSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableTotalSalesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
	            // Get the selected row index
	            int selectedRow = TableTotalSalesList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {

	            	Object ProductID = TableTotalSalesList.getValueAt(selectedRow, 0);
	                Object Name = TableTotalSalesList.getValueAt(selectedRow, 1);
	                Object Price = TableTotalSalesList.getValueAt(selectedRow, 2);
	                Object StockQuantity = TableTotalSalesList.getValueAt(selectedRow, 3);
	                Object categoryId = TableTotalSalesList.getValueAt(selectedRow, 4);
	                
	                textFieldIDItemSell.setText(ProductID.toString());
	                textFieldNameItemSell.setText(Name.toString());	 
	                textFieldCountSellWarehouse.setText(StockQuantity.toString());	 
	                textFieldCostSell.setText(Price.toString());
	                textFieldCateID.setText(categoryId.toString());
	            }
	        }
	    });  
		
		JScrollPane scrollPaneTableTotalSalesList = new JScrollPane(TableTotalSalesList);
		scrollPaneTableTotalSalesList.setBounds(10, 129, 975, 194);
		panelSale.add(scrollPaneTableTotalSalesList);
		
		tableModelSale = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableSalesList = new JTable(tableModelSale);
		TableSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableSalesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
	            // Get the selected row index
	            int selectedRow = TableSalesList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {
	                // Get data from the selected row
	                Object iditem = TableSalesList.getValueAt(selectedRow, 0);
	                Object nameitem = TableSalesList.getValueAt(selectedRow, 1);
	                Object costinput = TableSalesList.getValueAt(selectedRow, 2);
	                Object  count = TableSalesList.getValueAt(selectedRow, 3);
	                Object  countofTableTotalSalesList = TableTotalSalesList.getValueAt(selectedRow, 3);
	                Object categoryId = TableSalesList.getValueAt(selectedRow, 4);

	                // Display data in TextFields
	                textFieldIDItemSell.setText(iditem.toString());
	                textFieldNameItemSell.setText(nameitem.toString());	 
	                textFieldCountSellWarehouse.setText(countofTableTotalSalesList.toString());	
	                textFieldCountNeedBuy.setText(count.toString());	
	                textFieldCostSell.setText(costinput.toString());
	                int countbfupdate = Integer.parseInt(textFieldCountSellWarehouse.getText());
	                int countadd = Integer.parseInt(textFieldCountNeedBuy.getText());
	            }
	        }
	    });    
		
		JScrollPane scrollPaneTableSalesList = new JScrollPane(TableSalesList);
		scrollPaneTableSalesList.setBounds(10, 502, 975, 76);
		scrollPaneTableSalesList.setToolTipText("");
		panelSale.add(scrollPaneTableSalesList);
		TableDachSachTongSell();
		
		JButton ButtonAddToCart = new JButton("ADD TO CART");	
		ButtonAddToCart.setBounds(222, 452, 221, 40);
		ButtonAddToCart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Cart_icon.png"))));
		ButtonAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Lấy dữ liệu từ bảng vào text
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
            	int idcategory = Integer.parseInt(textFieldCateID.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int count = Integer.parseInt(textFieldCountSellWarehouse.getText());
                // Tính toán dữ liệu
                float itemTotalCost = costinputsell * countsell;               
                totalCost +=  itemTotalCost;  
                int newCount = count - countsell;
                
                TextTotalCart.setText(String.valueOf(totalCost)); 
                
               textFieldCountSellWarehouse.setText(String.valueOf(newCount));
               int countsellnew = Integer.parseInt(textFieldCountSellWarehouse.getText());
                ItemModelSell item = new ItemModelSell(iditemsell, nameitemsell,costinputsell, countsell,idcategory);
                itemDAO.getInstanitemDAO().inserttablesell(item);
                productModel itemud = new productModel(iditemsell, nameitemsell, costinputsell, countsellnew, idcategory);
                itemDAO.getInstanitemDAO().update(itemud);  
                    // Cập nhật bảng ngay tại đây
                DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                int selectedRow = TableTotalSalesList.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(iditemsell, selectedRow, 0);
                    model.setValueAt(nameitemsell, selectedRow, 1);
                    model.setValueAt(costinputsell, selectedRow, 2);
                    model.setValueAt(countsellnew, selectedRow, 3);
                    model.setValueAt(idcategory, selectedRow, 4);
                }
                SaleTable();
            }
        });
		
		ButtonAddToCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(ButtonAddToCart);
		
		JLabel LabelTongTienSell = new JLabel("TOTAL MONEY");
		LabelTongTienSell.setBounds(291, 586, 126, 27);
		LabelTongTienSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(LabelTongTienSell);
		
		JButton ButtonBuy = new JButton("BUY");
		ButtonBuy.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonBuy.setBounds(524, 624, 126, 38);
		ButtonBuy.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("cash-icon.png"))));

		ButtonBuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(ButtonBuy);
		
		textFieldIDItemSell = new JTextField();
		textFieldIDItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDItemSell.setColumns(10);
		textFieldIDItemSell.setBounds(172, 339, 76, 38);
		panelSale.add(textFieldIDItemSell);
		
		textFieldNameItemSell = new JTextField();
		textFieldNameItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItemSell.setColumns(10);
		textFieldNameItemSell.setBounds(172, 393, 271, 38);
		panelSale.add(textFieldNameItemSell);
		
		textFieldCostSell = new JTextField();
		textFieldCostSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCostSell.setColumns(10);
		textFieldCostSell.setBounds(698, 339, 221, 38);
		panelSale.add(textFieldCostSell);
		
		textFieldCountNeedBuy = new JTextField();
		textFieldCountNeedBuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCountNeedBuy.setColumns(10);
		textFieldCountNeedBuy.setBounds(843, 393, 76, 38);
		panelSale.add(textFieldCountNeedBuy);
		
		JLabel LabelIdItemSell = new JLabel("ID ITEM");
		LabelIdItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelIdItemSell.setBounds(52, 333, 95, 44);
		panelSale.add(LabelIdItemSell);
		
		JLabel LabelNameItemSell = new JLabel("ITEM NAME");
		LabelNameItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNameItemSell.setBounds(52, 387, 135, 44);
		panelSale.add(LabelNameItemSell);
		
		JLabel lblCostSell = new JLabel("ITEM PRICE");
		lblCostSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCostSell.setBounds(555, 333, 116, 44);
		panelSale.add(lblCostSell);
		
		JLabel LabelCountSell = new JLabel("QUANTITY");
		LabelCountSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelCountSell.setBounds(555, 387, 95, 44);
		panelSale.add(LabelCountSell);
		
		textFieldCountSellWarehouse = new JTextField();
		textFieldCountSellWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCountSellWarehouse.setBounds(698, 393, 126, 38);
		panelSale.add(textFieldCountSellWarehouse);
		textFieldCountSellWarehouse.setColumns(10);
		
		JButton btnNewButton = new JButton("REFRESH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			refreshbutton();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(739, 623, 126, 40);
		panelSale.add(btnNewButton);
		
		JButton btnDeleteItemCart = new JButton("REMOVE FROM CART");
		btnDeleteItemCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                int countofTableTotalSalesList = Integer.parseInt(textFieldCountSellWarehouse.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int countsellnew = countsell + countofTableTotalSalesList;
                int idcategory = Integer.parseInt(textFieldCateID.getText());
                
            	  int row = TableSalesList.getSelectedRow();
                  if(row ==-1)
                  {
                    JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Error",JOptionPane.ERROR_MESSAGE);
                  }
                  else
                  {
                        int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                        if (Confirm==JOptionPane.YES_OPTION)
                        {    
                        	productModel itemud = new productModel(iditemsell, nameitemsell, costinputsell, countsellnew, idcategory);
                        	itemDAO.getInstanitemDAO().update(itemud); 
                        // Cập nhật bảng ngay tại đây
                    DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                    int selectedRow = TableTotalSalesList.getSelectedRow();
                    if (selectedRow != -1) {
                        model.setValueAt(iditemsell, selectedRow, 0);
                        model.setValueAt(nameitemsell, selectedRow, 1);
                        model.setValueAt(costinputsell, selectedRow, 2);
                        model.setValueAt(countsellnew, selectedRow, 3);
                        model.setValueAt(idcategory, selectedRow, 4);

                    }                        
                    int itemid =Integer.valueOf(String.valueOf( TableSalesList.getValueAt(row,0)));                
                    itemDAO.getInstanitemDAO().deletesalecart(itemid); 
                    tableModelSale.removeRow(row);                     
          }
                  }	                 
                  clearFields();
             }
         });
		
		btnDeleteItemCart.setIcon(new ImageIcon(AdminScreen.class.getResource("/view/Delete_icon.png")));
		btnDeleteItemCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteItemCart.setBounds(612, 452, 267, 40);
		panelSale.add(btnDeleteItemCart);
		
		textFieldSearchSell = new JTextField();
		textFieldSearchSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSearchSell.setBounds(209, 27, 346, 40);
		panelSale.add(textFieldSearchSell);
		textFieldSearchSell.setColumns(10);
		
		JButton btnSearchSell = new JButton("Search");
		btnSearchSell.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = textFieldSearchSell.getText();
		        searchByName(searchTerm);
		    }
		});

		btnSearchSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchSell.setBounds(631, 26, 135, 43);
		panelSale.add(btnSearchSell);
		
		JLabel lblNewLabel = new JLabel("CATE ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(267, 339, 76, 38);
		panelSale.add(lblNewLabel);
		
		textFieldCateID = new JTextField();
		textFieldCateID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCateID.setColumns(10);
		textFieldCateID.setBounds(367, 339, 76, 38);
		panelSale.add(textFieldCateID);
		
	/*	String username = LoginScreenn.getUsername();
		JLabel lblNewLabel = new JLabel(username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(860, 0, 135, 23);
		panelSale.add(lblNewLabel); */
		
		ButtonBuy.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               	
	            	 int count =Integer.parseInt( textFieldCountNeedBuy.getText());   
		             float currentTotal = Float.parseFloat(TextT0talManage.getText());
		             float costinput =Float.parseFloat( textFieldCostSell.getText());
		             float newTotal = currentTotal + costinput*count;		      
		  //             updateTextTongTienManage(newTotal);
	            	  int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
	                  String nameitemsell = textFieldNameItemSell.getText(); 
	                  float costinputsell =Float.parseFloat( textFieldCostSell.getText());
	                  int countsell = Integer.parseInt(textFieldCountSellWarehouse.getText());
		                
	   //         	openNewScreen();
	            }
	        });

		//Phần tab quản lý
		
		JPanel panelManageProduct = new JPanel();
		tabbedPane.addTab("MANAGE PRODUCT", null, panelManageProduct, null);
		panelManageProduct.setLayout(null);
		
		JLabel LabelTotalManagementList = new JLabel("LIST OF ITEMS");
		LabelTotalManagementList.setHorizontalAlignment(SwingConstants.CENTER);
		LabelTotalManagementList.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelTotalManagementList.setBounds(510, 63, 184, 44);
		panelManageProduct.add(LabelTotalManagementList);
				
		JButton ButtonAddManage = new JButton("ADD TO INVENTORY");
		ButtonAddManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonAddManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Add_icon.png"))));
		ButtonAddManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonAddManage.setBounds(491, 372, 301, 50);
		ButtonAddManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int iditem = Integer.parseInt(textFieldIDItem.getText());
	                String nameitem= textFieldNameItem.getText();
	                currentCount = Integer.parseInt(textFieldNewCount.getText());	               																							
	                float costinput =Float.parseFloat( textFieldCostInput.getText());	               
	           /*     productModel item = new productModel(iditem, nameitem, currentCount, costinput);
	                ItemDAO.getInstanItemDAO().insert(item);	       */                 
	               float currentTotal = Float.parseFloat(TextT0talManage.getText());
	               float newTotal = currentTotal - costinput*currentCount;
	//               updateTextTongTienManage(newTotal);

	//               TableDachSachTongManage();
	 //              clearFields();
	            }
	        });
		panelManageProduct.add(ButtonAddManage);
		
		JLabel LabelTotalManage = new JLabel("CURRENT AMOUNT:");
		LabelTotalManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelTotalManage.setBounds(10, 610, 184, 44);
		panelManageProduct.add(LabelTotalManage);
		
		TextT0talManage = new JTextField();
		TextT0talManage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		float savedTotal = Preferences.userNodeForPackage(AdminScreen.class).getFloat(TOTAL_KEY, 1000000000f);
        TextT0talManage.setText(String.valueOf(savedTotal));

		TextT0talManage.setBounds(218, 612, 366, 38);
		panelManageProduct.add(TextT0talManage);
		TextT0talManage.setColumns(10);

		tableModelTotalManage = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableTotalManagementList = new JTable(tableModelTotalManage);
		TableTotalManagementList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		           
		JScrollPane scrollPaneTotalManagementList = new JScrollPane(TableTotalManagementList);
		scrollPaneTotalManagementList.setBounds(10, 117, 995, 231);
		panelManageProduct.add(scrollPaneTotalManagementList);		
		TableDachSachTongManage();
		
		JLabel LabelIdItemManage = new JLabel("ID ITEM");
		LabelIdItemManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelIdItemManage.setBounds(32, 375, 95, 44);
		panelManageProduct.add(LabelIdItemManage);
		
		JLabel LabelNameItem = new JLabel("ITEM NAME");
		LabelNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNameItem.setBounds(32, 427, 95, 44);
		panelManageProduct.add(LabelNameItem);
		
		JLabel LabelOldCount = new JLabel("OLD QUANTITY");
		LabelOldCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelOldCount.setBounds(32, 481, 116, 44);
		panelManageProduct.add(LabelOldCount);
		
		JLabel LabelCostInput = new JLabel("IMPORT PRICE");
		LabelCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelCostInput.setBounds(32, 535, 144, 44);
		panelManageProduct.add(LabelCostInput);
		
		textFieldIDItem = new JTextField();
		textFieldIDItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDItem.setColumns(10);
		textFieldIDItem.setBounds(186, 375, 221, 38);
		panelManageProduct.add(textFieldIDItem);
		
		textFieldNameItem = new JTextField();
		textFieldNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItem.setColumns(10);
		textFieldNameItem.setBounds(186, 427, 221, 38);
		panelManageProduct.add(textFieldNameItem);
		
		textFieldOldCount = new JTextField();
		textFieldOldCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldOldCount.setColumns(10);
		textFieldOldCount.setBounds(172, 484, 57, 38);
		panelManageProduct.add(textFieldOldCount);
		
		textFieldCostInput = new JTextField();
		textFieldCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCostInput.setColumns(10);
		textFieldCostInput.setBounds(186, 534, 221, 38);
		panelManageProduct.add(textFieldCostInput);
		
		JLabel LabelVND = new JLabel("VND");
		LabelVND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelVND.setBounds(594, 610, 40, 44);
		panelManageProduct.add(LabelVND);
		
		JButton ButtonUpdateManage = new JButton("UPDATE QUANTITY OF ITEMS");
		ButtonUpdateManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonUpdateManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("update_icon.png"))));

		ButtonUpdateManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonUpdateManage.setBounds(491, 442, 301, 50);
		
			ButtonUpdateManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int iditem = Integer.parseInt(textFieldIDItem.getText());
	                String nameitem= textFieldNameItem.getText();
	                int count =Integer.parseInt( textFieldNewCount.getText());
	                float costinput =Float.parseFloat( textFieldCostInput.getText());
	                float currentTotal = Float.parseFloat(TextT0talManage.getText());
	                int oldcount =Integer.parseInt( textFieldOldCount.getText());	                
	                float total = (count - oldcount)*costinput;	                
	                float newtotal = currentTotal - total;
	//                updateTextTongTienManage(newtotal); 

	     /*           productModel itemud = new productModel(iditem, nameitem, count, costinput);
	                ItemDAO.getInstanItemDAO().update(itemud);  */
	                    // Cập nhật bảng ngay tại đây
	                DefaultTableModel model = (DefaultTableModel) TableTotalManagementList.getModel();
	                int selectedRow = TableTotalManagementList.getSelectedRow();
	                if (selectedRow != -1) {
	                    model.setValueAt(iditem, selectedRow, 0);
	                    model.setValueAt(nameitem, selectedRow, 1);
	                    model.setValueAt(count, selectedRow, 2);
	                    model.setValueAt(costinput, selectedRow, 3);
	                }
              
	            }
	        });			
		panelManageProduct.add(ButtonUpdateManage);

		JButton ButtonXoaManage = new JButton("DELETE ITEMS");
		ButtonXoaManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonXoaManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Delete_icon.png"))));
		ButtonXoaManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonXoaManage.setBounds(491, 513, 301, 50);
		panelManageProduct.add(ButtonXoaManage);
		
		JLabel LabelNewCount = new JLabel("NEW QUANTITY");
		LabelNewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNewCount.setBounds(239, 481, 125, 44);
		panelManageProduct.add(LabelNewCount);
		
		textFieldNewCount = new JTextField();
		textFieldNewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNewCount.setColumns(10);
		textFieldNewCount.setBounds(374, 484, 57, 38);
		panelManageProduct.add(textFieldNewCount);
		
		JButton btnLogout = new JButton("LOG OUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                if (Confirm==JOptionPane.YES_OPTION)
                {    
				openLoginScreen();
                }
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(828, 612, 138, 44);
		panelManageProduct.add(btnLogout);
		
		textFieldFindManageProduct = new JTextField();
		textFieldFindManageProduct.setBounds(255, 10, 336, 43);
		panelManageProduct.add(textFieldFindManageProduct);
		textFieldFindManageProduct.setColumns(10);
		
		JButton btnFindMangeProduct = new JButton("Search");
		btnFindMangeProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFindMangeProduct.setBounds(647, 10, 135, 43);
		panelManageProduct.add(btnFindMangeProduct);
		TableTotalManagementList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
	            // Get the selected row index
	            int selectedRow = TableTotalManagementList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {
	                // Get data from the selected row
	                Object iditem = TableTotalManagementList.getValueAt(selectedRow, 0);
	                Object nameitem = TableTotalManagementList.getValueAt(selectedRow, 1);
	                Object  count = TableTotalManagementList.getValueAt(selectedRow, 2);
	                Object costinput = TableTotalManagementList.getValueAt(selectedRow, 3);

	                // Display data in TextFields
	                textFieldIDItem.setText(iditem.toString());
	                textFieldNameItem.setText(nameitem.toString());
	                textFieldOldCount.setText(count.toString());
	                textFieldCostInput.setText(costinput.toString());
	                
	            }
	        }
	    });    
		
		ButtonXoaManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	  int row = TableTotalManagementList.getSelectedRow();
	                  if(row ==-1)
	                  {
	                    JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Loi",JOptionPane.ERROR_MESSAGE);
	                  }
	                  else
	                  {
	                        int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
	                        if (Confirm==JOptionPane.YES_OPTION)
	                        {                            
                        int itemid =Integer.valueOf(String.valueOf( TableTotalManagementList.getValueAt(row,0)));
                        int count =Integer.parseInt( textFieldOldCount.getText());
    	                float costinput =Float.parseFloat( textFieldCostInput.getText());
                //        ItemDAO.getInstanItemDAO().delete(itemid);
                        tableModelTotalManage.removeRow(row);   
                        float currentTotal = Float.parseFloat(TextT0talManage.getText());
 		               float newTotal = currentTotal + costinput*count;
 	//	               updateTextTongTienManage(newTotal);
              }
	                  }	
	                  clearFields();
	             }
	            
	         });	
		//Quản lý nhân viên
		
		JPanel panelManageCashier = new JPanel();
		tabbedPane.addTab("MANAGE CASHIER", null, panelManageCashier, null);
		
		tableModelEmployeeManage = new DefaultTableModel(new Object[][]{}, new String[]{"ID EMPLOYEE", "NAME", "GMAIL", "PASSWORD", "PHONENUMBER", "WORKHOUR"});
		TableEmployeeList = new JTable(tableModelEmployeeManage);
		TableEmployeeList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableEmployeeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
			    // Get the selected row index
	            int selectedRow = TableEmployeeList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {

	            	Object EmployeeID = TableEmployeeList.getValueAt(selectedRow, 0);
	                Object EmployeeName = TableEmployeeList.getValueAt(selectedRow, 1);
	                Object EmployeeGmail = TableEmployeeList.getValueAt(selectedRow, 2);
	                Object EmployeePass = TableEmployeeList.getValueAt(selectedRow, 3);
	                Object EmployeePhoneNumber = TableEmployeeList.getValueAt(selectedRow, 5);
	                Object EmployeeWorkHour = TableEmployeeList.getValueAt(selectedRow, 6);

	            }
	        }
	    });  
		panelManageCashier.setLayout(null);
		
		JScrollPane scrollPaneTableEmployeeList = new JScrollPane(TableEmployeeList);
		scrollPaneTableEmployeeList.setBounds(10, 129, 975, 194);
		panelManageCashier.add(scrollPaneTableEmployeeList);
		
		JLabel LabelEmployeeName = new JLabel("NAME");
		LabelEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeeName.setBounds(98, 387, 118, 44);
		panelManageCashier.add(LabelEmployeeName);
		
		JLabel LabelEmployeePhoneNum = new JLabel("PHONENUMBER");
		LabelEmployeePhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeePhoneNum.setBounds(94, 441, 122, 44);
		panelManageCashier.add(LabelEmployeePhoneNum);
		
		JLabel LabelEmployeGmail = new JLabel("GMAIL");
		LabelEmployeGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeGmail.setBounds(98, 495, 122, 44);
		panelManageCashier.add(LabelEmployeGmail);
		
		JLabel LabelEmployeeNameWORKHOUR = new JLabel("WORKHOUR");
		LabelEmployeeNameWORKHOUR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeeNameWORKHOUR.setBounds(98, 549, 122, 44);
		panelManageCashier.add(LabelEmployeeNameWORKHOUR);
		
		TextEmployeeName = new JTextField();
		TextEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeName.setColumns(10);
		TextEmployeeName.setBounds(253, 390, 221, 38);
		panelManageCashier.add(TextEmployeeName);
		
		TextEmployeePhone = new JTextField();
		TextEmployeePhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeePhone.setColumns(10);
		TextEmployeePhone.setBounds(253, 444, 221, 38);
		panelManageCashier.add(TextEmployeePhone);
		
		TextEmployeeGmail = new JTextField();
		TextEmployeeGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeGmail.setColumns(10);
		TextEmployeeGmail.setBounds(253, 498, 221, 38);
		panelManageCashier.add(TextEmployeeGmail);
		
		TextEmployeeWorkHour = new JTextField();
		TextEmployeeWorkHour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeWorkHour.setColumns(10);
		TextEmployeeWorkHour.setBounds(253, 552, 221, 38);
		panelManageCashier.add(TextEmployeeWorkHour);
		
		JButton btnDeleteEmployee = new JButton("DELETE EMPLOYEE");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				
			}
		     	});
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteEmployee.setBounds(610, 384, 301, 50);
		panelManageCashier.add(btnDeleteEmployee);
		
		JButton btnUpdateInformation = new JButton("UPDATE INFORMATION");
		btnUpdateInformation.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				
			}
		     	});
		btnUpdateInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateInformation.setBounds(610, 466, 301, 50);
		panelManageCashier.add(btnUpdateInformation);
		
		JButton btnSearchEmployee = new JButton("SEARCH");
		btnSearchEmployee.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				
			}
		     	});
		btnSearchEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchEmployee.setBounds(623, 10, 133, 38);
		panelManageCashier.add(btnSearchEmployee);
		
		JButton btnChat = new JButton("CHAT");	
		btnChat.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				
			}
		     	});
		btnChat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("chat-2-icon.png"))));
		btnChat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChat.setBounds(679, 546, 182, 50);
		panelManageCashier.add(btnChat);
		
		textFieldSearchEmployee = new JTextField();
		textFieldSearchEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSearchEmployee.setColumns(10);
		textFieldSearchEmployee.setBounds(253, 10, 321, 38);
		panelManageCashier.add(textFieldSearchEmployee);
		
		JLabel lblNewLabel_1 = new JLabel("EMPLOYEE LIST");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(371, 68, 227, 36);
		panelManageCashier.add(lblNewLabel_1);
		TableEmployeeList();
	}
	
	public void TableDachSachTongSell() {
		   tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
		    ArrayList<productModel> itemDAO = new itemDAO().getInstanitemDAO().selectAll() ;
		    tableModel.getDataVector().removeAllElements();
		    tableModel.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModel.addRow(itemDAO.get(i).toObject());
		    		    	
		    }
		} 
	
	public void TableEmployeeList() {
		tableModelEmployeeManage = (DefaultTableModel) TableEmployeeList.getModel();
		    ArrayList<employeeModel> itemDAO = new itemDAO().getInstanitemDAO().selectAllEmployee() ;
		    tableModelEmployeeManage.getDataVector().removeAllElements();
		    tableModelEmployeeManage.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModelEmployeeManage.addRow(itemDAO.get(i).toObject());
		    		    	
		    }
		} 
	
	public void TableDachSachTongManage() {
		   tableModelTotalManage = (DefaultTableModel) TableTotalManagementList.getModel();
		    ArrayList<productModel> itemDAO = new itemDAO().getInstanitemDAO().selectAll() ;
		    tableModelTotalManage.getDataVector().removeAllElements();
		    tableModelTotalManage.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModelTotalManage.addRow(itemDAO.get(i).toObject());
		    }
		}
	
	public void SaleTable() {
		
		tableModelSale = (DefaultTableModel) TableSalesList.getModel();
	    ArrayList<ItemModelSell> items = new itemDAO().getInstanitemDAO().selectProductCart();
	    tableModelSale.getDataVector().removeAllElements();
	    tableModelSale.setRowCount(0); // Clear existing data
	    for (int i = 0; i < items.size(); i++) {
	    	tableModelSale.addRow(items.get(i).toObject());
	    }
	}	

	private void searchByName(String searchTerm) {
	    ArrayList<productModel> searchResults = itemDAO.getInstanitemDAO().searchByName(searchTerm);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0);

	    for (productModel result : searchResults) {
	        tableModel.addRow(result.toObject());
	    }
	}
	public void refreshTable() {
	    TableDachSachTongSell();		    
	    TableDachSachTongManage();
	}
	
	
/*	public void openNewScreen() {
		 
        ChangeScreen NewScreen = new ChangeScreen();    
        NewScreen.setVisible(true);
        dispose();
    }
		
	public void updateTextTongTienManage(float newTotal) {
	    // Format the float value with two decimal places
	    String formattedTotal = String.format("%.2f", newTotal);
	    TextT0talManage.setText(formattedTotal);
	    Preferences.userNodeForPackage(AdminScreen.class).putFloat(TOTAL_KEY, newTotal);
	}
		
	public float getTotalCost() {
        return totalCost;
    } */
	
	public void refreshbutton() {
		
		AdminScreen NewScreen = new AdminScreen();		   
	    NewScreen.setVisible(true);
	dispose();
	}
	
	public void openLoginScreen() {
		 
		LoginScreen loginNewScreen = new LoginScreen();    
		loginNewScreen.setVisible(true);
        dispose();
    }
	
	public void clearFields() {
        textFieldIDItem.setText("");
        textFieldNameItem.setText("");
        textFieldOldCount.setText("");
        textFieldCostInput.setText("");
    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
					  frame.setLocationRelativeTo(null); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
