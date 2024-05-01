package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
//import org.jdesktop.swingx.border.DropShadowBorder;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JPasswordField textFieldConfirmpass;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;
	private JTextField textFieldGmail;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnCashier;
	private JRadioButton rdbtnAdmin;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnCashier = new JRadioButton("Cashier");
        rdbtnCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnCashier.setBounds(64, 305, 104, 33);
        contentPane.add(rdbtnCashier);
        buttonGroup.add(rdbtnCashier);

        rdbtnAdmin = new JRadioButton("Admin");
        rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAdmin.setBounds(283, 305, 104, 33);
        contentPane.add(rdbtnAdmin);
        buttonGroup.add(rdbtnAdmin);
		
		JLabel LabelUsername = new JLabel("Username");
		LabelUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelUsername.setBounds(10, 67, 90, 37);
		contentPane.add(LabelUsername);
		
		textFieldConfirmpass = new JPasswordField();
		textFieldConfirmpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldConfirmpass.setBounds(185, 244, 248, 37);
		contentPane.add(textFieldConfirmpass);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsername.setBounds(185, 67, 248, 37);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton ButtonLogin = new JButton("LOGIN");
//		ButtonLogin.setBackground(SystemColor.activeCaption);
		ButtonLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	            	openNewScreen();
	            }
	        });
		
		ButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonLogin.setBounds(150, 386, 145, 53);
		contentPane.add(ButtonLogin);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPassword.setBounds(185, 185, 248, 37);
		contentPane.add(textFieldPassword);
		
		textFieldGmail = new JTextField();
		textFieldGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldGmail.setBounds(185, 124, 248, 37);
		contentPane.add(textFieldGmail);
		
		JLabel LabelGmail = new JLabel("Gmail");
		LabelGmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelGmail.setBounds(10, 124, 90, 37);
		contentPane.add(LabelGmail);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelPassword.setBounds(10, 185, 90, 37);
		contentPane.add(LabelPassword);
		
		JLabel LabelConfirmPass = new JLabel("Confirm Password");
		LabelConfirmPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelConfirmPass.setBounds(10, 244, 158, 37);
		contentPane.add(LabelConfirmPass);
		
		JButton ButtonRegister = new JButton("REGISTER");
		ButtonRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonRegister.setBounds(150, 466, 145, 53);
		ButtonRegister.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String USERNAME = textFieldUsername.getText();
	                String GMAIL = textFieldGmail.getText();
	                String PASS = textFieldPassword.getText();
	                String CONFIRM = textFieldConfirmpass.getText();

	                // Modify the username based on selection
	                if (rdbtnCashier.isSelected()) {
	                    USERNAME += " : Cashier";
	                } else if (rdbtnAdmin.isSelected()) {
	                    USERNAME += " : Admin";
	                }
	                clearFields();
	            }
	        });
		
	/*	DropShadowBorder shadow = new DropShadowBorder();
		shadow.setShadowSize(5); // Đặt kích thước bóng
		shadow.setShowLeftShadow(true); // Hiển thị bóng bên trái
		shadow.setShowRightShadow(true); // Hiển thị bóng bên phải
		shadow.setShowBottomShadow(true); // Hiển thị bóng phía dưới
		ButtonRegister.setBorder(shadow); // Đặt hiệu ứng bóng cho nút */
	        contentPane.add(ButtonRegister);
		
		JLabel LabelRegister = new JLabel(" BADMINTON STORE ");
		LabelRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		LabelRegister.setBounds(103, 6, 330, 51);
		contentPane.add(LabelRegister);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	//	lblNewLabel.setIcon(new ImageIcon(LoginScreenn.class.getResource("badminton3.png")));
		lblNewLabel.setBounds(0, 0, 459, 640);
		contentPane.add(lblNewLabel);	
		
		setLocationRelativeTo(null);
		
	}
	
	public void openNewScreen() {
      LoginScreen NewScreen = new LoginScreen();      
        NewScreen.setVisible(true);
        dispose();
    }
	
	private void clearFields() {
        textFieldUsername.setText("");
        textFieldGmail.setText("");
        textFieldPassword.setText("");
        textFieldConfirmpass.setText("");
    }
		}
		
