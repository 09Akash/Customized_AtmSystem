package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Withdraw extends JFrame implements ActionListener {

    JLabel text, start;
    JTextField damount;
    JButton Withdraw, back;
    String pinNumber;

    Withdraw(String pinNumber) {
        this.pinNumber = pinNumber;
        setTitle("Atm Machine");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        setLayout(null);
        text = new JLabel("Welcome to the Withdraw Section");
        text.setFont(new Font("Family", Font.BOLD, 30));
        text.setForeground(Color.BLACK);
        text.setBounds(200, 80, 900, 40);
        image.add(text);

        start = new JLabel("Enter the Amount you want to Withdraw");
        start.setBounds(200, 150, 700, 350);
        start.setForeground(Color.WHITE);
        start.setFont(new Font("System", Font.BOLD, 15));
        image.add(start);

        damount = new JTextField();
        damount.setFont(new Font("Family", Font.BOLD, 20));
        damount.setForeground(Color.BLACK);
        damount.setBounds(200, 380, 200, 30);
        image.add(damount);

        Withdraw = new JButton("Withdraw");
        Withdraw.setFont(new Font("Family", Font.BOLD, 12));
        Withdraw.setForeground(Color.BLACK);
        Withdraw.setBounds(200, 480, 100, 30);
        Withdraw.addActionListener(this);
        image.add(Withdraw);

        back = new JButton("Back");
        back.setFont(new Font("Family", Font.BOLD, 12));
        back.setForeground(Color.BLACK);
        back.setBounds(380, 480, 100, 30);
        back.addActionListener(this);

        image.add(back);

     setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == back) {

            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        }

         if (ae.getSource() == Withdraw) {
    String number = damount.getText();
    java.util.Date date = new java.util.Date();
    
    if (number.equals("")) {
        JOptionPane.showMessageDialog(null, "PLEASE ENTER THE AMOUNT");
    } else {
        try {
            Conn conn = new Conn();
            
            // Step 1: Fetch balance from the bank table and calculate total balance
            ResultSet rs = conn.s.executeQuery("select * from bank where pin='" + pinNumber + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else if(rs.getString("type").equals("Withdraw")) {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            // Step 2: Check if balance is sufficient for withdrawal
            if (balance < Integer.parseInt(number)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // Step 3: Proceed with withdrawal if balance is sufficient
            String query = "insert into bank values('" + pinNumber + "','" + date + "','Withdraw' ,'" + number + "')";
            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrawn Successfully");
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}


        
    }

    public static void main(String[] args) {
        new Withdraw("");
    }
}
