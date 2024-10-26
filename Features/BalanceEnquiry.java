package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JLabel text, l1, image;
    JButton back;
    String rpin;

    BalanceEnquiry(String pinNumber) {
        this.rpin = pinNumber;
        setLayout(null);

        // Image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Welcome text
        text = new JLabel("Balance Enquiry");
        text.setFont(new Font("Verdana", Font.BOLD, 32));
        text.setForeground(new Color(255, 215, 0));
        text.setBounds(280, 80, 400, 40);
        image.add(text);

        // Back button setup
        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(0, 128, 255));
        back.setFont(new Font("Arial", Font.BOLD, 18));
        back.setBounds(370, 500, 150, 40);
        back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        image.add(back);

        // Fetch balance from database
        int balance = getBalance(pinNumber);

        // Display the calculated balance
        l1 = new JLabel("Current Balance: ₹ " + balance);
        l1.setBounds(200, 400, 500, 40);
        l1.setForeground(new Color(240, 255, 240)); // Honeydew color
        l1.setFont(new Font("SansSerif", Font.BOLD, 22));
        image.add(l1);

        // Frame setup
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    // Method to fetch balance from the database
    public int getBalance(String pinNumber) {
        int balance = 0;
        Conn c = new Conn();

        try {
            // Fetch all transactions for the given pin
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin='" + pinNumber + "'");

            // Process each transaction
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));

                // Add or subtract based on transaction type
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("Withdraw")) {
                    balance -= amount;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return balance;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(rpin).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}
