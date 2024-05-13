package view;

import java.awt.EventQueue;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.itemDAO;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textUsername;
	private JPasswordField textFieldPassword;
	private JLabel lblNewLabel;
	private JButton btnRegist;
	private JRadioButton rdbtnCashier;
	private JRadioButton rdbtnAdmin;
	private JLabel lblLogin;
	private JLabel lblNewLabel_1;
	
	
	public LoginScreen() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });

		setBounds(100, 100, 473, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUsername.setBounds(174, 172, 242, 41);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnCashier = new JRadioButton("Cashier");
        rdbtnCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnCashier.setBounds(83, 311, 104, 33);
        contentPane.add(rdbtnCashier);
        buttonGroup.add(rdbtnCashier);

        rdbtnAdmin = new JRadioButton("Admin");
        rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAdmin.setBounds(279, 311, 104, 33);
        contentPane.add(rdbtnAdmin);
        buttonGroup.add(rdbtnAdmin);
        
		JButton ButtonLogin = new JButton("LOGIN");
		ButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonLogin.setBounds(120, 384, 208, 57);
		ButtonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String enterUsername = textUsername.getText();
				String enterPassword = textFieldPassword.getText();
								
				  if ( itemDAO.getInstanitemDAO().checkLogin(enterUsername, enterPassword))
				  {          
				   if (rdbtnCashier.isSelected()) {
				 OpenCashierScreen();
	                } else if (rdbtnAdmin.isSelected()) {
	                	OpenAdminScreen();
	                }        	
				                 } else {
				                     JOptionPane.showMessageDialog(contentPane, "Incorrect username, password or ability!",
				                             "Login Failed", JOptionPane.ERROR_MESSAGE);
				                 }				
			}
        });

		
		btnRegist = new JButton("REGISTER");
		btnRegist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegist.setBounds(120, 472, 208, 57);
		contentPane.add(btnRegist);
		btnRegist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				OpenRegisterScreen();
			}
        });

		contentPane.add(ButtonLogin);
		
		JLabel LabelUsername = new JLabel("USERNAME");
		LabelUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelUsername.setBounds(33, 166, 131, 53);
		contentPane.add(LabelUsername);
		
		JLabel LabelPassword = new JLabel("PASSWORD");
		LabelPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LabelPassword.setBounds(33, 233, 130, 53);
		contentPane.add(LabelPassword);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(174, 242, 242, 41);
		contentPane.add(textFieldPassword);
		
	//	lblNewLabel = new JLabel("");
	//	lblNewLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("badminton.jpg")));
	//	lblNewLabel.setBounds(10, 10, 799, 464);
	//	contentPane.add(lblNewLabel);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(204, 87, 78, 52);
		contentPane.add(lblLogin);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginScreen.class.getResource("account.png")));
		lblNewLabel_1.setBounds(194, 10, 78, 97);
		contentPane.add(lblNewLabel_1);
		
		setLocationRelativeTo(null);
		
	}
	public void handleClosing() {
	    int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
	            "Confirmation", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {	       
	        dispose();
	    }
	}
	
	public void OpenRegisterScreen() {
		RegisterScreen registerscreen = new RegisterScreen();
		registerscreen.setVisible(true);
		dispose();
	}
	
	 public void OpenCashierScreen() {
		 CasherScreen cashscreen = new CasherScreen();
		 cashscreen.setVisible(true);
		 dispose();
	 }
	 
	 public void OpenAdminScreen() {
		 AdminScreen adminscreen = new AdminScreen();
		 adminscreen.setVisible(true);
		 dispose();
	 }
    
    public static String getUsername() {
		return textUsername.getText();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
