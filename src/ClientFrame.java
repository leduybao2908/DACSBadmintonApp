import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame {
	public static JTextArea msgArea = new JTextArea();

	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField msgTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			s = new Socket("127.0.0.1",1201);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			while(!msgin.equals("exit"))
			{
				msgin = din.readUTF();
				msgArea.setText(msgArea.getText().trim()+"\n Server:\t"+msgin);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		setTitle("Client Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msgArea.setBounds(10, 10, 561, 250);
		contentPane.add(msgArea);
		
		msgTxt = new JTextField();
		msgTxt.setBounds(10, 273, 473, 62);
		contentPane.add(msgTxt);
		msgTxt.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msgout = "";
				msgout = msgTxt.getText().trim();
				dout.writeUTF(msgout);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnSend.setBounds(486, 273, 102, 62);
		contentPane.add(btnSend);
	}
}
