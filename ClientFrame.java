package view;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;

public class ClientFrame extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField msgTxt;
    private static JTextArea msgArea;
    private static Socket s;
    private static DataInputStream din;
    private static DataOutputStream dout;
    private static final String PREFIX_SERVER = "Server: ";
    private static final String PREFIX_CLIENT = "Client: ";
    private boolean isServer; // Đánh dấu vai trò của client

    public ClientFrame(boolean isServer) {
        this.isServer = isServer;
        setLayout(new BorderLayout());

        msgArea = new JTextArea();
        msgArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msgArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        msgTxt = new JTextField();
        bottomPanel.add(msgTxt, BorderLayout.CENTER);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String msgout = msgTxt.getText().trim();
                    if (isServer) {
                        // Nếu là server, thêm tiền tố "Server: "
                        dout.writeUTF(PREFIX_SERVER + msgout);
                    } else {
                        dout.writeUTF(PREFIX_CLIENT + msgout);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        bottomPanel.add(btnSend, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Chat", new ClientFrame(false)); // Đánh dấu client là false
        
        frame.getContentPane().add(tabbedPane);
        frame.setSize(400, 300);
        frame.setVisible(true);

        try {
            s = new Socket("127.0.0.1", 1201);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgin = "";
			while(!msgin.equals("exit")) {
                msgin = din.readUTF();
                msgArea.append(msgin + "\n"); // Không cần kiểm tra là server hay client
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
