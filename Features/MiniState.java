package atm.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class MiniState extends JFrame implements ActionListener{

    JLabel titleLabel, bankNameLabel, cardLabel, miniLabel, balanceLabel;
    JPanel statementPanel, mainPanel;
    JButton back;
String pinNumber;
    MiniState(String pinNumber) {
        this.pinNumber= pinNumber;
        // Set layout and title
        setLayout(null);
        setTitle("Mini Statement");

        // Create main panel with border and background color
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(10, 10, 880, 870);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        mainPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        add(mainPanel);

        // Title label with improved styling
        titleLabel = new JLabel("Mini Statement");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue color
        titleLabel.setBounds(300, 30, 400, 50);
        mainPanel.add(titleLabel);

        // Bank name label
        bankNameLabel = new JLabel("MY BANK OF MAHARASHTRA");
        bankNameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        bankNameLabel.setForeground(Color.BLACK);
        bankNameLabel.setBounds(230, 90, 500, 40);
        mainPanel.add(bankNameLabel);

        // Card number label with improved position and font size
        cardLabel = new JLabel();
        cardLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cardLabel.setBounds(50, 150, 400, 30);
        mainPanel.add(cardLabel);

        // Create panel for mini statement details with a titled border
        statementPanel = new JPanel();
        statementPanel.setLayout(null);
        statementPanel.setBounds(50, 200, 780, 400); // Adjusted size for better presentation
        Border statementBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), "Transaction History", 0, 0,
                new Font("Arial", Font.BOLD, 20), Color.BLACK);
        statementPanel.setBorder(statementBorder);
        statementPanel.setBackground(Color.WHITE); // White background for clear text
        mainPanel.add(statementPanel);

        // Mini statement transactions (inside the panel)
        miniLabel = new JLabel();
        miniLabel.setBounds(20, 30, 740, 350); // Adjusted size to fit the panel
        miniLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Font size for transactions
        statementPanel.add(miniLabel);

        // Balance label
        balanceLabel = new JLabel("Balance: ");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        balanceLabel.setForeground(new Color(0, 102, 0)); // Green color for balance
        balanceLabel.setBounds(50, 620, 400, 40);
        mainPanel.add(balanceLabel);

          back = new JButton("Back");
        back.setFont(new Font("Family", Font.BOLD, 12));
        back.setForeground(Color.BLACK);
        back.setBounds(100, 680, 100, 30);
        back.addActionListener(this);
        add(back);

        
        // Fetch and display card number
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '" + pinNumber + "'");
            if (rs.next()) {
                String cardNumber = rs.getString("cardNumber");
                cardLabel.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Fetch and display mini statement and calculate total balance
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinNumber + "'");
            StringBuilder statementBuilder = new StringBuilder("<html>");
            double totalBalance = 0;

            // Create table with column headers for the mini-statement
            statementBuilder.append("<table style='width:100%;' cellspacing='10'>")
                    .append("<tr><th align='left'>Date</th><th align='left'>Type</th><th align='right'>Amount</th></tr>");

            // Iterate through transactions and add rows to the table
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                double amount = Double.parseDouble(rs.getString("amount"));

                // Add each transaction as a row in the table with proper spacing
                statementBuilder.append("<tr>")
                        .append("<td>").append(date).append("</td>")
                        .append("<td>").append(type).append("</td>")
                        .append("<td align='right'>₹ ").append(amount).append("</td>")
                        .append("</tr>");

                // Adjust balance based on transaction type
                if (type.equalsIgnoreCase("Deposit")) {
                    totalBalance += amount; // Add deposits
                } else if (type.equalsIgnoreCase("Withdraw")) {
                    totalBalance -= amount; // Subtract withdrawals
                }
                
            }

            statementBuilder.append("</table></html>"); // Close the table and HTML
            miniLabel.setText(statementBuilder.toString());
            balanceLabel.setText("Balance: ₹ " + totalBalance);  // Display total balance
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
      
        // Set frame size, position, and visibility
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true); // Remove window decoration
        setVisible(true);
    }
     public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == back) {

            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        }
     }
    public static void main(String[] args) {
        new MiniState("");
    }
}
