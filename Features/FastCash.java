package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton rs100, rs200, rs500, rs1000, rs2000, rs5000, back;
    JLabel Mtext, text;
    String pinNumber;

    FastCash(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        Mtext = new JLabel("Welcome to the Fast Cash Section");
        Mtext.setFont(new Font("Family", Font.BOLD, 30));
        Mtext.setForeground(Color.BLACK);
        Mtext.setBounds(200, 80, 900, 40);
        image.add(Mtext);

        text = new JLabel("SELECT THE AMOUNT YOU WANT TO TAKE OUT ");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Family", Font.BOLD, 12));
        text.setBounds(200, 300, 300, 70);
        image.add(text);

        rs100 = new JButton("Rs 100");
        rs100.setBounds(150, 420, 150, 30);
        rs100.addActionListener(this);
        image.add(rs100);

        rs200 = new JButton("Rs 200");
        rs200.setBounds(150, 450, 150, 30);
        rs200.addActionListener(this);
        image.add(rs200);

        rs500 = new JButton("RS 500");
        rs500.setBounds(150, 480, 150, 30);
        rs500.addActionListener(this);
        image.add(rs500);

        rs1000 = new JButton("Rs 1000");
        rs1000.setBounds(370, 420, 150, 30);
        rs1000.addActionListener(this);
        image.add(rs1000);

        rs2000 = new JButton("Rs 2000");
        rs2000.setBounds(370, 450, 150, 30);
        rs2000.addActionListener(this);
        image.add(rs2000);

        rs5000 = new JButton("Rs 5000");
        rs5000.setBounds(370, 480, 150, 30);
        rs5000.addActionListener(this);
        image.add(rs5000);

        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBounds(380, 520, 100, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3); // Get withdrawal amount
            Conn c = new Conn();
            try {
                // Step 1: Fetch balance from the bank table
                ResultSet rs = c.s.executeQuery("select * from bank where pin='" + pinNumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                // Step 2: Debugging info for balance and requested amount
//                System.out.println("Current balance: " + balance);
//                System.out.println("Requested withdrawal amount: " + amount);

                // Step 3: Check if balance is sufficient for withdrawal
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Step 4: Proceed with withdrawal if balance is sufficient
                Date date = new Date();
                String query = "insert into bank values ('" + pinNumber + "','" + date + "','Withdraw','" + amount + "')";
                c.s.execute(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                setVisible(false);
                new Transaction(pinNumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
