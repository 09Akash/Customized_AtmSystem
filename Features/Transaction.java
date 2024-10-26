package atm.system;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener {

    JButton dc, be, ms, pc, fc, wc, exit;
    String pinNumber;
    JTextField wpin, cpin;

    Transaction(String pinNumber) {
        this.pinNumber = pinNumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("PLEASE SELECT THE TRANSACTIONS");
        text.setBounds(200, 150, 700, 350);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 15));
        image.add(text);

        dc = new JButton("Deposit Cash");
        dc.setBounds(150, 420, 150, 30);
        dc.addActionListener(this);
        image.add(dc);

        pc = new JButton("Pin Change");
        pc.setBounds(150, 450, 150, 30);
        pc.addActionListener(this);
        image.add(pc);

        fc = new JButton("Fast Cash");
        fc.setBounds(150, 480, 150, 30);
        fc.addActionListener(this);
        image.add(fc);

        wc = new JButton("Withdraw Cash");
        wc.setBounds(370, 420, 150, 30);
        wc.addActionListener(this);
        image.add(wc);

        ms = new JButton("Mini statement");
        ms.setBounds(370, 450, 150, 30);
        ms.addActionListener(this);
        image.add(ms);

        be = new JButton("Balance Enquiry");
        be.setBounds(370, 480, 150, 30);
        be.addActionListener(this);
        image.add(be);

        exit = new JButton("EXIT");
        exit.setBounds(250, 520, 150, 30);
        exit.addActionListener(this);
        exit.setForeground(Color.RED);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == exit) {
            System.exit(0);

        } else if (ae.getSource() == dc) {
            setVisible(false);
            new Deposit(pinNumber).setVisible(true);
        } else if (ae.getSource() == be) {
            setVisible(false);
            new BalanceEnquiry(pinNumber).setVisible(true);
        } else if (ae.getSource() == ms) {
            setVisible(false);
            new MiniState(pinNumber).setVisible(true);
        } else if (ae.getSource() == pc) {
            setVisible(false);
            new PinChange(pinNumber).setVisible(true);
        } else if (ae.getSource() == fc) {
            setVisible(false);
            new FastCash(pinNumber).setVisible(true);
        } else if (ae.getSource() == wc ) {
            //  setVisible(false);
            boolean pinCorrect = false;
            
            
            while (!pinCorrect) {
                cpin = new JTextField();
                Object[] message = {"ENTER THE PIN:", cpin};

                int option = JOptionPane.showConfirmDialog(null, message, "Enter Pin", JOptionPane.OK_CANCEL_OPTION);

                // Check if the user clicked "OK"
                if (option == JOptionPane.OK_OPTION) {
                    String inputText = cpin.getText();
                    //  System.out.println("Input: " + inputText);
                   // String inputText = cpin.getText().trim(); // Get the entered text and trim any whitespace

                    if (inputText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter your PIN.");
                    }
                    else if (inputText.equals(pinNumber)) {
                            pinCorrect = true;
                            setVisible(false);
                            new Withdraw(pinNumber).setVisible(true);
                            

                    }
                    else {
                            JOptionPane.showMessageDialog(null, "Incorrect pin");
//                            pinCorrect = true;

                        }
//           ]

                    } else if (option == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                        break;
                    }
                }

            }
        }

    

    public static void main(String args[]) {
        new Transaction("");

    }

}
