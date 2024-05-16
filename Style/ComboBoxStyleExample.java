package view.Style;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ComboBoxStyleExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create JFrame
            JFrame frame = new JFrame("ComboBox Style Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(null);

            // Create JPanel
            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 800, 600);
            panel.setLayout(null);

            // Create JComboBox
            JComboBox<String> comboBox = new JComboBox<>();
            comboBox.setBounds(100, 100, 200, 30);
            comboBox.addItem("");
            comboBox.addItem("Vợt");
            comboBox.addItem("Giày");
            comboBox.addItem("Trang phục");
            comboBox.addItem("Vật phẩm khác");

            // Set font for JComboBox
            Font font = new Font("Arial", Font.PLAIN, 16);
            comboBox.setFont(font);

            // Set background color for JComboBox
            comboBox.setBackground(Color.WHITE);

            // Set foreground (text) color for JComboBox
            comboBox.setForeground(Color.BLACK);

            // Set maximum row count for the dropdown list
            comboBox.setMaximumRowCount(4);

            // Add JComboBox to JPanel
            panel.add(comboBox);

            // Add JPanel to JFrame
            frame.add(panel);

            // Set JFrame visible
            frame.setVisible(true);
        });
    }
}
