package atm.system;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
//import java.sql.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField damount;
    JButton deposit, back;
    String pinnumber;
 JLabel Mtext , text;
    Deposit(String pinnumber) {
  
        
        this.pinnumber = pinnumber;
        
       
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
         Mtext = new JLabel("Welcome to the Deposit Section");
        Mtext.setFont(new Font("Family", Font.BOLD, 30));
        Mtext.setForeground(Color.BLACK);
        Mtext.setBounds(200, 80, 900, 40);
        image.add(Mtext);

        text = new JLabel("Enter the Amount you want to Deposit : ");
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 300, 70);
        image.add(text);

        damount = new JTextField();
        damount.setForeground(Color.BLACK);
        damount.setFont(new Font("Osward", Font.BOLD, 15));
        damount.setBounds(170, 360, 300, 30);
        image.add(damount);

        deposit = new JButton("DEPOSIT");
        deposit.setForeground(Color.BLACK);
        deposit.setBounds(190, 450, 100, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBounds(350, 450, 100, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {
            String number = damount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "PLESE ENTER THE AMOUNT");
            }
            else{
                try{
                Conn conn = new Conn();
                String query ="insert into bank values('"+pinnumber+"','"+date+"','Deposit' ,'"+number+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs" +number+"   Deposited Successfully");
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
                }
                catch(Exception e){
                    System.out.print(e);
                }
            }

        } else if (ae.getSource() == back) {

            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Deposit("");
    }
}
