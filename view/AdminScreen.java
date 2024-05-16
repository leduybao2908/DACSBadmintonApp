package view;

import java.awt.EventQueue;


import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
//import database.JDBCUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
 /*import dao.ItemDAO;
import model.ItemModel;
import model.ItemModelSell;*/
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
	public DefaultTableModel tableModel1;
//	public ItemDAO ItemDAO = new ItemDAO() ; 
//	ArrayList<ItemModel> result = new ArrayList<ItemModel>();
	public JTextField textFieldIDItemSell;
	public JTextField textFieldNameItemSell;
	public JTextField textFieldCostSell;
	public JTextField textFieldCountNeedBuy;
	public DefaultTableModel tableModel2;
	public int currentCount;
    public static final String TOTAL_KEY = "totalKey";
	public float totalCost = 0.0f;
//	public ItemModel itemModel;
	private JTextField textFieldCountSellWarehouse;
	private JTextField textFieldNewCount;
	private JTextField textFieldFind;
	
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
		
		tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID ITEM", "ITEM NAME", "INVENTORY", "PRICE"});
		TableTotalSalesList = new JTable(tableModel);
		TableTotalSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableTotalSalesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
	            // Get the selected row index
	            int selectedRow = TableTotalSalesList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {

	            	Object iditem = TableTotalSalesList.getValueAt(selectedRow, 0);
	                Object nameitem = TableTotalSalesList.getValueAt(selectedRow, 1);
	                Object  count = TableTotalSalesList.getValueAt(selectedRow, 2);
	                Object costinput = TableTotalSalesList.getValueAt(selectedRow, 3);

	                textFieldIDItemSell.setText(iditem.toString());
	                textFieldNameItemSell.setText(nameitem.toString());	 
	                textFieldCountSellWarehouse.setText(count.toString());	 
	                textFieldCostSell.setText(costinput.toString());
	            }
	        }
	    });    
		JScrollPane scrollPaneTableTotalSalesList = new JScrollPane(TableTotalSalesList);
		scrollPaneTableTotalSalesList.setBounds(10, 129, 975, 194);
		panelSale.add(scrollPaneTableTotalSalesList);
		
		tableModel2 = new DefaultTableModel(new Object[][]{}, new String[]{"ID ITEM", "ITEM NAME", "QUANTITY", "PRICE"});
		TableSalesList = new JTable(tableModel2);
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
	                Object  count = TableSalesList.getValueAt(selectedRow, 2);
	                Object  countofTableTotalSalesList = TableTotalSalesList.getValueAt(selectedRow, 2);
	                Object costinput = TableSalesList.getValueAt(selectedRow, 3);

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
	//	TableDachSachTongSell();
	//	TableMua();
		
		JButton ButtonAddToCart = new JButton("ADD TO CART");	
		ButtonAddToCart.setBounds(222, 452, 221, 40);
		ButtonAddToCart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Cart_icon.png"))));
		ButtonAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int count = Integer.parseInt(textFieldCountSellWarehouse.getText());
                float itemTotalCost = costinputsell * countsell;               
                totalCost +=  itemTotalCost;  
                int newCount = count - countsell;
                TextTotalCart.setText(String.valueOf(totalCost));            
               textFieldCountSellWarehouse.setText(String.valueOf(newCount));
               int countsellnew = Integer.parseInt(textFieldCountSellWarehouse.getText());
          /*      ItemModelSell item = new ItemModelSell(iditemsell, nameitemsell, countsell,costinputsell);
                ItemDAO.getInstanItemDAO().inserttablesell(item);
                ItemModel itemud = new ItemModel(iditemsell, nameitemsell, countsellnew, costinputsell);
                ItemDAO.getInstanItemDAO().update(itemud); */ 
                    // Cập nhật bảng ngay tại đây
                DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                int selectedRow = TableTotalSalesList.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(iditemsell, selectedRow, 0);
                    model.setValueAt(nameitemsell, selectedRow, 1);
                    model.setValueAt(countsellnew, selectedRow, 2);
                    model.setValueAt(costinputsell, selectedRow, 3);
                }
  //             TableMua();
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
		textFieldIDItemSell.setBounds(222, 339, 221, 38);
		panelSale.add(textFieldIDItemSell);
		
		textFieldNameItemSell = new JTextField();
		textFieldNameItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItemSell.setColumns(10);
		textFieldNameItemSell.setBounds(224, 393, 221, 38);
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
		//		refreshbutton();
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
                
            	  int row = TableSalesList.getSelectedRow();
                  if(row ==-1)
                  {
                    JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Loi",JOptionPane.ERROR_MESSAGE);
                  }
                  else
                  {
                        int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                        if (Confirm==JOptionPane.YES_OPTION)
                        {    
                      /*  	ItemModel itemud = new ItemModel(iditemsell, nameitemsell, countsellnew, costinputsell);
                        ItemDAO.getInstanItemDAO().update(itemud); */
                        // Cập nhật bảng ngay tại đây
                    DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                    int selectedRow = TableTotalSalesList.getSelectedRow();
                    if (selectedRow != -1) {
                        model.setValueAt(iditemsell, selectedRow, 0);
                        model.setValueAt(nameitemsell, selectedRow, 1);
                        model.setValueAt(countsellnew, selectedRow, 2);
                        model.setValueAt(costinputsell, selectedRow, 3);
                    }                        
               /*     int itemid =Integer.valueOf(String.valueOf( TableSalesList.getValueAt(row,0)));                
                    ItemDAO.getInstanItemDAO().deletesalecart(itemid); */
                    tableModel2.removeRow(row);                     
          }
                  }	                 
     //             clearFields();
             }
         });
		btnDeleteItemCart.setIcon(new ImageIcon(AdminScreen.class.getResource("/view/Delete_icon.png")));
		btnDeleteItemCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteItemCart.setBounds(612, 452, 267, 40);
		panelSale.add(btnDeleteItemCart);
		
		textFieldFind = new JTextField();
		textFieldFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldFind.setBounds(209, 27, 346, 40);
		panelSale.add(textFieldFind);
		textFieldFind.setColumns(10);
		
		JButton btnFind = new JButton("Search");
		btnFind.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = textFieldFind.getText();
	//	        searchByName(searchTerm);
		    }
		});

		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFind.setBounds(631, 26, 135, 43);
		panelSale.add(btnFind);
		
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
		
		JPanel panelManage = new JPanel();
		tabbedPane.addTab("MANAGE", null, panelManage, null);
		panelManage.setLayout(null);
		JLabel LabelTotalManagementList = new JLabel("LIST OF ITEMS");
		LabelTotalManagementList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelTotalManagementList.setBounds(10, 35, 184, 44);
		panelManage.add(LabelTotalManagementList);
				
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
	           /*     ItemModel item = new ItemModel(iditem, nameitem, currentCount, costinput);
	                ItemDAO.getInstanItemDAO().insert(item);	       */                 
	               float currentTotal = Float.parseFloat(TextT0talManage.getText());
	               float newTotal = currentTotal - costinput*currentCount;
	//               updateTextTongTienManage(newTotal);

	//               TableDachSachTongManage();
	 //              clearFields();
	            }
	        });
		panelManage.add(ButtonAddManage);
		
		JLabel LabelTotalManage = new JLabel("CURRENT AMOUNT:");
		LabelTotalManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelTotalManage.setBounds(10, 610, 184, 44);
		panelManage.add(LabelTotalManage);
		
		TextT0talManage = new JTextField();
		TextT0talManage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		float savedTotal = Preferences.userNodeForPackage(AdminScreen.class).getFloat(TOTAL_KEY, 1000000000f);
        TextT0talManage.setText(String.valueOf(savedTotal));

		TextT0talManage.setBounds(218, 612, 366, 38);
		panelManage.add(TextT0talManage);
		TextT0talManage.setColumns(10);

		tableModel1 = new DefaultTableModel(new Object[][]{}, new String[]{"ID ITEM", "ITEM NAME", "INVENTORY", "PRICE"});
		TableTotalManagementList = new JTable(tableModel1);
		TableTotalManagementList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		           
		JScrollPane scrollPaneTotalManagementList = new JScrollPane(TableTotalManagementList);
		scrollPaneTotalManagementList.setBounds(0, 83, 995, 269);
		panelManage.add(scrollPaneTotalManagementList);		
	//	TableDachSachTongManage();
		
		JLabel LabelIdItemManage = new JLabel("ID ITEM");
		LabelIdItemManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelIdItemManage.setBounds(32, 375, 95, 44);
		panelManage.add(LabelIdItemManage);
		
		JLabel LabelNameItem = new JLabel("ITEM NAME");
		LabelNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNameItem.setBounds(32, 427, 95, 44);
		panelManage.add(LabelNameItem);
		
		JLabel LabelOldCount = new JLabel("OLD QUANTITY");
		LabelOldCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelOldCount.setBounds(32, 481, 116, 44);
		panelManage.add(LabelOldCount);
		
		JLabel LabelCostInput = new JLabel("IMPORT PRICE");
		LabelCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelCostInput.setBounds(32, 535, 144, 44);
		panelManage.add(LabelCostInput);
		
		textFieldIDItem = new JTextField();
		textFieldIDItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDItem.setColumns(10);
		textFieldIDItem.setBounds(186, 375, 221, 38);
		panelManage.add(textFieldIDItem);
		
		textFieldNameItem = new JTextField();
		textFieldNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItem.setColumns(10);
		textFieldNameItem.setBounds(186, 427, 221, 38);
		panelManage.add(textFieldNameItem);
		
		textFieldOldCount = new JTextField();
		textFieldOldCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldOldCount.setColumns(10);
		textFieldOldCount.setBounds(172, 484, 57, 38);
		panelManage.add(textFieldOldCount);
		
		textFieldCostInput = new JTextField();
		textFieldCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCostInput.setColumns(10);
		textFieldCostInput.setBounds(186, 534, 221, 38);
		panelManage.add(textFieldCostInput);
		
		JLabel LabelVND = new JLabel("VND");
		LabelVND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelVND.setBounds(594, 610, 40, 44);
		panelManage.add(LabelVND);
		
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

	     /*           ItemModel itemud = new ItemModel(iditem, nameitem, count, costinput);
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
		panelManage.add(ButtonUpdateManage);

		JButton ButtonXoaManage = new JButton("DELETE ITEMS");
		ButtonXoaManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonXoaManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Delete_icon.png"))));
		ButtonXoaManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonXoaManage.setBounds(491, 513, 301, 50);
		panelManage.add(ButtonXoaManage);
		
		JLabel LabelNewCount = new JLabel("NEW QUANTITY");
		LabelNewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNewCount.setBounds(239, 481, 125, 44);
		panelManage.add(LabelNewCount);
		
		textFieldNewCount = new JTextField();
		textFieldNewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNewCount.setColumns(10);
		textFieldNewCount.setBounds(374, 484, 57, 38);
		panelManage.add(textFieldNewCount);
		
		JButton btnLogout = new JButton("LOG OUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                if (Confirm==JOptionPane.YES_OPTION)
                {    
	//			openLoginScreen();
                }
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(828, 612, 138, 44);
		panelManage.add(btnLogout);
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
                        tableModel1.removeRow(row);   
                        float currentTotal = Float.parseFloat(TextT0talManage.getText());
 		               float newTotal = currentTotal + costinput*count;
 	//	               updateTextTongTienManage(newTotal);
              }
	                  }	
	           //       clearFields();
	             }
	         });		
	}
	
/*	public void openNewScreen() {
		 
        ChangeScreen NewScreen = new ChangeScreen();    
        NewScreen.setVisible(true);
        dispose();
    }
	public void openLoginScreen() {
		 
		LoginScreenn loginNewScreen = new LoginScreenn();    
		loginNewScreen.setVisible(true);
        dispose();
    }
	
	public void TableDachSachTongSell() {
	   tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
	    ArrayList<ItemModel> itemDAO = new ItemDAO().getInstanItemDAO().selectAll() ;
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0); // Clear existing data
	    for(int i = 0; i < itemDAO.size();i++) {
	    	tableModel.addRow(itemDAO.get(i).toObject());
	    		    	
	    }
	} 
	public void TableDachSachTongManage() {
	   tableModel1 = (DefaultTableModel) TableTotalManagementList.getModel();
	    ArrayList<ItemModel> itemDAO = new ItemDAO().getInstanItemDAO().selectAll() ;
	    tableModel1.getDataVector().removeAllElements();
	    tableModel1.setRowCount(0); // Clear existing data
	    for(int i = 0; i < itemDAO.size();i++) {
	    	tableModel1.addRow(itemDAO.get(i).toObject());
	    }
	}
	public void TableMua() {
	    ArrayList<ItemModelSell> items = new ItemDAO().getInstanItemDAO().selectAlltablesell();
	    tableModel2.getDataVector().removeAllElements();
	    tableModel2.setRowCount(0); // Clear existing data
	    for (int i = 0; i < items.size(); i++) {
	    	tableModel2.addRow(items.get(i).toObject());
	    }
	}
	
	public void clearFields() {
        textFieldIDItem.setText("");
        textFieldNameItem.setText("");
        textFieldOldCount.setText("");
        textFieldCostInput.setText("");
    }
	
	public void refreshTable() {
	    TableDachSachTongSell();		    
	    TableDachSachTongManage();
	}
	
	public void updateTextTongTienManage(float newTotal) {
	    // Format the float value with two decimal places
	    String formattedTotal = String.format("%.2f", newTotal);
	    TextT0talManage.setText(formattedTotal);
	    Preferences.userNodeForPackage(AdminScreen.class).putFloat(TOTAL_KEY, newTotal);
	}
	
	public void refreshbutton() {
		AdminScreen NewScreen = new AdminScreen();
		   
	    NewScreen.setVisible(true);
	dispose();
	}
	
	private void searchByName(String searchTerm) {
	    ArrayList<ItemModel> searchResults = ItemDAO.getInstanItemDAO().searchByName(searchTerm);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0);

	    for (ItemModel result : searchResults) {
	        tableModel.addRow(result.toObject());
	    }
	}
	
	public float getTotalCost() {
        return totalCost;
    } */

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
