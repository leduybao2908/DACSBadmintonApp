package view;

import java.awt.EventQueue;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.AdminItemDAO;
import dao.PaymentItemDAO;
import database.JDBCUtil;
import model.ItemModelSell;
import model.productModel;
import model.billitemModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paymentscreen extends JFrame {

	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textFieldChange;
	public JTextField textFieldCustomerMoney;
	public AdminScreen adminScreen;
	private JLabel lblNewLabel;
	private DefaultTableModel tableModelBill;
	private JTable TableBillList;
	private JTextField txtFieldCustomerName;
	private JTextField textField;
	
	public Paymentscreen(AdminScreen adminScreen) {
	    this.adminScreen = adminScreen;
	}

	public Paymentscreen() {
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableModelBill = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableBillList = new JTable(tableModelBill);
		TableBillList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableBillList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {            
	        }
	    });    
		SaleTable();
		
		JScrollPane scrollPaneTablebILLList = new JScrollPane(TableBillList);
		scrollPaneTablebILLList.setBounds(52, 409, 975, 193);
		scrollPaneTablebILLList.setToolTipText("");
		contentPane.add(scrollPaneTablebILLList);
		TableDachSachTongSell();
		
		JLabel LabelMain = new JLabel("ENTER THE AMOUNT OF MONEY GIVEN BY THE CUSTOMER");
		LabelMain.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelMain.setBounds(110, 39, 557, 40);
		contentPane.add(LabelMain);
		
		JButton ButtonCalculateMoney = new JButton("TÍNH TIỀN");
		ButtonCalculateMoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonCalculateMoney.setBounds(730, 106, 140, 43);
		ButtonCalculateMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {          
            	float totalCostInTable = getTotalCostFromTable();
            	float cost = Float.parseFloat(textFieldCustomerMoney.getText());
            	  AdminItemDAO.getInstanitemDAO().deleteTableSell(); 
            	  float costchange =cost - totalCostInTable;
            	  textFieldChange.setText(String.valueOf(costchange));
            }
        });
		contentPane.add(ButtonCalculateMoney);
		
		textFieldChange = new JTextField();
		textFieldChange.setBounds(390, 160, 255, 40);
		contentPane.add(textFieldChange);
		textFieldChange.setColumns(10);
		
		textFieldCustomerMoney = new JTextField();
		textFieldCustomerMoney.setColumns(10);
		textFieldCustomerMoney.setBounds(390, 110, 255, 40);
		contentPane.add(textFieldCustomerMoney);
		
		JLabel LabelCustomerMoney = new JLabel("MONEY CUSTOMER GIVES");
		LabelCustomerMoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelCustomerMoney.setBounds(158, 107, 209, 40);
		contentPane.add(LabelCustomerMoney);
		
		JLabel LabelChange = new JLabel("CHANGE");
		LabelChange.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelChange.setBounds(158, 157, 94, 40);
		contentPane.add(LabelChange);
		
		txtFieldCustomerName = new JTextField();
		txtFieldCustomerName.setColumns(10);
		txtFieldCustomerName.setBounds(390, 210, 255, 40);
		contentPane.add(txtFieldCustomerName);
		
		JLabel LblCustomerName = new JLabel("CUSTOMER NAME");
		LblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LblCustomerName.setBounds(158, 207, 209, 40);
		contentPane.add(LblCustomerName);
		
		JLabel lblCustomerPhonenumber = new JLabel("CUSTOMER PHONENUMBER");
		lblCustomerPhonenumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCustomerPhonenumber.setBounds(158, 260, 209, 40);
		contentPane.add(lblCustomerPhonenumber);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(390, 260, 255, 40);
		contentPane.add(textField);
		
		/*
		contentPane.add(ButtonNewTrade);
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginScreenn.class.getResource("badminton.jpg")));
		lblNewLabel.setBounds(10, 10, 776, 464);
		contentPane.add(lblNewLabel);
	}
	 */
	}
//Method to handle the closing of the NewScreen dialog
	
public void SaleTable() {
		
		tableModelBill = (DefaultTableModel) TableBillList.getModel();
	    ArrayList<ItemModelSell> items = new PaymentItemDAO().getInstanitemDAO().selectProductCart();
	    tableModelBill.getDataVector().removeAllElements();
	    tableModelBill.setRowCount(0); // Clear existing data
	    for (int i = 0; i < items.size(); i++) {
	    	tableModelBill.addRow(items.get(i).toObject());
	    }
	}	

public void handleClosing() {
    int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {      
        dispose();
    }
}

public void openNewScreen() {
    // Close all existing instances of adminScreen  
    AdminScreen NewScreen = new AdminScreen();  
    NewScreen.setVisible(true);
dispose();   
}

private float getTotalCostFromTable() {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        con = JDBCUtil.getConnection();
        String sql = "SELECT SUM(costinputsell * countsell) AS totalCost FROM itemsell";
        preparedStatement = con.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getFloat("totalCost");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng tất cả các resource
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return 0; // Trả về 0 nếu có lỗi
}

public void TableDachSachTongSell() {
	tableModelBill = (DefaultTableModel) TableBillList.getModel();
	    ArrayList<billitemModel> itemDAO = new AdminItemDAO().getInstanitemDAO().selectAllBillItem() ;
	    tableModelBill.getDataVector().removeAllElements();
	    tableModelBill.setRowCount(0); // Clear existing data
	    for(int i = 0; i < itemDAO.size();i++) {
	    	tableModelBill.addRow(itemDAO.get(i).toObject());
	    		    	
	    }
	} 

public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
            //	AdminScreen frame = new AdminScreen();
               // Now create the ChangeScreen instance
                Paymentscreen newScreenInstance = new Paymentscreen();
                newScreenInstance.setLocationRelativeTo(null); 
                newScreenInstance.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
}
