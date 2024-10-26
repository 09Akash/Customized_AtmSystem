/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JLabel text, t1, t2, t3;
    JPasswordField cpin, npin, repin;
    JButton changePin, back;
    String pinNumber;

    PinChange(String pinNumber) {

        this.pinNumber = pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        text = new JLabel("Welcome to the Pin Change Section");
        text.setFont(new Font("Family", Font.BOLD, 30));
        text.setForeground(Color.BLACK);
        text.setBounds(200, 80, 900, 40);
        image.add(text);

        t1 = new JLabel("Enter the Current Pin");
        t1.setFont(new Font("Railway", Font.BOLD, 20));
        t1.setForeground(Color.WHITE);
        t1.setBounds(160, 300, 300, 20);
        image.add(t1);

        cpin = new JPasswordField();
        cpin.setFont(new Font("Railway", Font.BOLD, 20));
        cpin.setForeground(Color.BLACK);
        cpin.setBounds(160, 330, 200, 20);
        image.add(cpin);

        t2 = new JLabel("Enter the New Pin");
        t2.setFont(new Font("Railway", Font.BOLD, 20));
        t2.setForeground(Color.WHITE);
        t2.setBounds(160, 370, 200, 20);
        image.add(t2);

        npin = new JPasswordField();
        npin.setFont(new Font("Railway", Font.BOLD, 20));
        npin.setForeground(Color.BLACK);
        npin.setBounds(160, 400, 200, 20);
        image.add(npin);

        t3 = new JLabel("Re-Enter the Pin");
        t3.setFont(new Font("Family", Font.BOLD, 20));
        t3.setForeground(Color.WHITE);
        t3.setBounds(160, 450, 200, 20);
        image.add(t3);

        repin = new JPasswordField();
        repin.setFont(new Font("Railway", Font.BOLD, 20));
        repin.setForeground(Color.BLACK);
        repin.setBounds(160, 480, 200, 20);
        image.add(repin);

        changePin = new JButton("CHANGE PIN");
        changePin.setFont(new Font("Railway", Font.BOLD, 15));
        changePin.setForeground(Color.BLACK);
        changePin.setBounds(160, 530, 150, 20);
        changePin.addActionListener(this);
        image.add(changePin);

        back = new JButton("BACK");
        back.setFont(new Font("Railway", Font.BOLD, 15));
        back.setForeground(Color.BLACK);
        back.setBounds(360, 530, 150, 20);
        back.addActionListener(this);

        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changePin) {

            try {
                String cupin = cpin.getText();
                String nepin = npin.getText();
                String rpin = repin.getText();

                if (!cupin.equals(pinNumber)) {
                    JOptionPane.showMessageDialog(null, "PIN NUMBER NOT MATCH WITH CURRENT PIN");
                    return;
                }
                if (!nepin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "BOTH PIN NOT MATCH");
                    return;
                }
          
                if(nepin.equals("")){
                 JOptionPane.showMessageDialog(null, "EMPTY PIN DETECTED PLESE ENTER PIN");
                }
                if(rpin.equals("")){
                 JOptionPane.showMessageDialog(null, "PLESE REENTER PIN");
                }
                
                Conn conn = new Conn();
                String query1="update bank set pin ='"+rpin+"' where pin='"+pinNumber+"' ";
                
                String query2 ="update login set pin = '"+rpin+"' where pin='"+pinNumber+"'";
                
                String query3 ="update signupthree set pin = '"+rpin+"' where pin='"+pinNumber+"'";
                
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null, "PIN CHANGE SUCCESSFULLY");
                setVisible(false);
                new Transaction(rpin).setVisible(true);

                
                
                

            }
             catch (Exception e) {
            System.out.println(e);
             }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinNumber);
           }
       
    }

    public static void main(String args[]) {
        new PinChange("");
    }

}
