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
import model.productModel;
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

public class CasherScreen extends JFrame {

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
//	ArrayList<productModel> result = new ArrayList<productModel>();
	public JTextField textFieldIDItemSell;
	public JTextField textFieldNameItemSell;
	public JTextField textFieldCostSell;
	public JTextField textFieldCountNeedBuy;
	public DefaultTableModel tableModel2;
	public int currentCount;
    public static final String TOTAL_KEY = "totalKey";
	public float totalCost = 0.0f;
//	public productModel itemModel;
	private JTextField textFieldCountSellWarehouse;
	private JTextField textFieldNewCount;
	private JTextField textFieldFind;
	
	public CasherScreen() {
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
		URL url = CasherScreen.class.getResource("52745-badminton-icon.png");
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
		 ButtonAddToCart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CasherScreen.class.getResource("Cart_icon.png"))));
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
                productModel itemud = new productModel(iditemsell, nameitemsell, countsellnew, costinputsell);
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
		ButtonBuy.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CasherScreen.class.getResource("cash-icon.png"))));

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
                    JOptionPane.showMessageDialog(CasherScreen.this, "Please select row you want to delete!","Loi",JOptionPane.ERROR_MESSAGE);
                  }
                  else
                  {
                        int Confirm = JOptionPane.showConfirmDialog(CasherScreen.this, "Are you sure ?");
                        if (Confirm==JOptionPane.YES_OPTION)
                        {    
                      /*  	productModel itemud = new productModel(iditemsell, nameitemsell, countsellnew, costinputsell);
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
		btnDeleteItemCart.setIcon(new ImageIcon(CasherScreen.class.getResource("/view/Delete_icon.png")));
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

	}
	
/*	public void openNewScreen() {
		 
        ChangeScreen NewScreen = new ChangeScreen();    
        NewScreen.setVisible(true);
        dispose();
    }
	
	public void TableDachSachTongSell() {
	   tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
	    ArrayList<productModel> itemDAO = new ItemDAO().getInstanItemDAO().selectAll() ;
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0); // Clear existing data
	    for(int i = 0; i < itemDAO.size();i++) {
	    	tableModel.addRow(itemDAO.get(i).toObject());
	    		    	
	    }
	} 
	public void TableDachSachTongManage() {
	   tableModel1 = (DefaultTableModel) TableTotalManagementList.getModel();
	    ArrayList<productModel> itemDAO = new ItemDAO().getInstanItemDAO().selectAll() ;
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
	
	public void refreshTable() {
	    TableDachSachTongSell();		    
	    TableDachSachTongManage();
	}
	
	public void updateTextTongTienManage(float newTotal) {
	    // Format the float value with two decimal places
	    String formattedTotal = String.format("%.2f", newTotal);
	    TextT0talManage.setText(formattedTotal);
	    Preferences.userNodeForPackage(BigExerciseView.class).putFloat(TOTAL_KEY, newTotal);
	}	
	
	private void searchByName(String searchTerm) {
	    ArrayList<productModel> searchResults = ItemDAO.getInstanItemDAO().searchByName(searchTerm);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0);

	    for (productModel result : searchResults) {
	        tableModel.addRow(result.toObject());
	    }
	}
	
	public float getTotalCost() {
        return totalCost;
    } */
	
	public void clearFields() {
        textFieldIDItem.setText("");
        textFieldNameItem.setText("");
        textFieldOldCount.setText("");
        textFieldCostInput.setText("");
    }
	
	public void refreshbutton() {
		CasherScreen NewScreen = new CasherScreen();
		   
	    NewScreen.setVisible(true);
	dispose();
	}
	
	public void openLoginScreen() {
		 
		LoginScreen loginNewScreen = new LoginScreen();    
		loginNewScreen.setVisible(true);
        dispose();
    }
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CasherScreen frame = new CasherScreen();
					  frame.setLocationRelativeTo(null); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
