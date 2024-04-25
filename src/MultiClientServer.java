
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiClientServer extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField msgTxt;
    public static JTextArea msgArea = new JTextArea();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MultiClientServer frame = new MultiClientServer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            ServerSocket ss = new ServerSocket(1201);
            while (true) {
                Socket s = ss.accept();
                Thread t = new Thread(new ClientHandler(s));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultiClientServer() {
        setTitle("ServerFrame");
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
                String msgout = msgTxt.getText().trim();
                msgArea.append("Server: " + msgout + "\n");
                sendMessageToAllClients(msgout);
                msgTxt.setText("");
            }
        });
        btnSend.setBounds(486, 273, 102, 62);
        contentPane.add(btnSend);
    }
    
    public static void sendMessageToAllClients(String message) {
        // Iterate through all connected clients and send the message
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private DataInputStream din;
    private DataOutputStream dout;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            din = new DataInputStream(clientSocket.getInputStream());
            dout = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msgin = "";
            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                MultiClientServer.msgArea.append("Client: " + msgin + "\n");
                MultiClientServer.sendMessageToAllClients(msgin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
