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
import model.billModel;
import model.productModel;
import view.RoundJTextField;
import model.billitemModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Paymentscreen extends JFrame {

    public static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public JTextField textFieldChange;
    public JTextField textFieldCustomerMoney;
    public AdminScreen adminScreen;
    private DefaultTableModel tableModelBill;
    public JTable TableBillList;
    private JTextField txtFieldCustomerName;
    private JTextField textFieldCustomerPhone;

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

        // Add all other components first
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

        JLabel LabelMain = new JLabel("ENTER THE AMOUNT OF MONEY GIVEN BY THE CUSTOMER");
        LabelMain.setBackground(new Color(255, 255, 255));
        LabelMain.setFont(new Font("Tahoma", Font.BOLD, 18));
        LabelMain.setBounds(271, 39, 557, 40);
        LabelMain.setForeground(Color.WHITE);
        contentPane.add(LabelMain);

        JButton ButtonCalculateMoney = new view.ButtonGradient();
        ButtonCalculateMoney.setText("Cash");
        ButtonCalculateMoney.setFont(new Font("Tahoma", Font.BOLD, 16));
        ButtonCalculateMoney.setBounds(730, 106, 140, 43);
        ButtonCalculateMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime Time = LocalDateTime.now();
                String timebill = Time.toString();
                int BillId = PaymentItemDAO.getInstanitemDAO().getBillCount() + 1;
                String IdofBill = Integer.toString(BillId);
                String NameFile = IdofBill + ".txt";
                String namecustomer = txtFieldCustomerName.getText();
                String phonecustomer = textFieldCustomerPhone.getText();
                float totalprice = PaymentItemDAO.getInstanitemDAO().getTotalCostFromTable();
                float cost = Float.parseFloat(textFieldCustomerMoney.getText());
                float costchange = cost - totalprice;
                textFieldChange.setText(String.valueOf(costchange));
                billModel bill = new billModel(BillId, timebill, totalprice, namecustomer, phonecustomer);
                PaymentItemDAO.getInstanitemDAO().insert(bill);
                PaymentItemDAO.getInstanitemDAO().deleteProductcart();
                String FileLink = "C:\\Users\\LE DUY BAO\\Documents\\DACS-HOADON\\HOADON-";
                String filepath =  FileLink + NameFile;
                PaymentItemDAO.getInstanitemDAO().writeToFile(filepath, timebill, BillId, namecustomer,phonecustomer,cost,totalprice,costchange);
                PaymentItemDAO.getInstanitemDAO().openFileExplorer(filepath);
                PaymentItemDAO.getInstanitemDAO().writeTableToFile(tableModelBill, filepath);
                
                SaleTable();
                clearFields();
                
            }
        });
        contentPane.add(ButtonCalculateMoney);

        textFieldChange = new JTextField();
        textFieldChange.setBounds(425, 632, 255, 40);
        contentPane.add(textFieldChange);
        textFieldChange.setColumns(10);

        textFieldCustomerMoney = new JTextField();
        textFieldCustomerMoney.setColumns(10);
        textFieldCustomerMoney.setBounds(425, 110, 255, 40);
        contentPane.add(textFieldCustomerMoney);

        JLabel LabelCustomerMoney = new JLabel("MONEY CUSTOMER GIVES");
        LabelCustomerMoney.setFont(new Font("Tahoma", Font.BOLD, 16));
        LabelCustomerMoney.setBounds(158, 107, 257, 40);
        LabelCustomerMoney.setForeground(Color.WHITE);
        contentPane.add(LabelCustomerMoney);

        JLabel LabelChange = new JLabel("CHANGE:");
        LabelChange.setFont(new Font("Tahoma", Font.BOLD, 16));
        LabelChange.setBounds(316, 629, 94, 40);
        LabelChange.setForeground(Color.WHITE);
        contentPane.add(LabelChange);

        txtFieldCustomerName = new JTextField();
        txtFieldCustomerName.setColumns(10);
        txtFieldCustomerName.setBounds(425, 186, 255, 40);
        contentPane.add(txtFieldCustomerName);

        JLabel LblCustomerName = new JLabel("CUSTOMER NAME");
        LblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
        LblCustomerName.setBounds(158, 183, 209, 40);
        LblCustomerName.setForeground(Color.WHITE);
        contentPane.add(LblCustomerName);

        JLabel lblCustomerPhonenumber = new JLabel("CUSTOMER PHONENUMBER");
        lblCustomerPhonenumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCustomerPhonenumber.setBounds(158, 260, 257, 40);
        lblCustomerPhonenumber.setForeground(Color.WHITE);
        contentPane.add(lblCustomerPhonenumber);

        textFieldCustomerPhone = new JTextField();
        textFieldCustomerPhone.setColumns(10);
        textFieldCustomerPhone.setBounds(425, 263, 255, 40);
        contentPane.add(textFieldCustomerPhone);

        // Add the background label last
        JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Paymentscreen.class.getResource("bgg.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1086, 713, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, 0, 1086, 713);
        contentPane.add(lblNewLabel);

        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
    }

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

    public void clearFields() {
        textFieldCustomerMoney.setText("");
        textFieldCustomerPhone.setText("");
        txtFieldCustomerName.setText("");
    }

    public void openNewScreen() {
        AdminScreen NewScreen = new AdminScreen();
        NewScreen.setVisible(true);
        dispose();
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
