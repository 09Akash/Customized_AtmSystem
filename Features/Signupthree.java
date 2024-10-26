package atm.system;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Signupthree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox s1, s2, s3, s4, s5, s6, s7;
    String formno;
    JButton submit, cancel;

    Signupthree(String formno) {
        this.formno = formno;

        setLayout(null);

        JLabel l1 = new JLabel("PAGE 3: Account Details:");
        l1.setFont(new Font("Railway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 30);
        add(l1);

        JLabel Atype = new JLabel("Account Type:");
        Atype.setFont(new Font("Railway", Font.BOLD, 22));
        Atype.setBounds(100, 140, 250, 30);
        add(Atype);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Railway", Font.BOLD, 18));
        r1.setBounds(100, 200, 250, 30);
        add(r1);

        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Railway", Font.BOLD, 18));
        r2.setBounds(350, 200, 250, 30);
        add(r2);

        r3 = new JRadioButton("Fixed Account");
        r3.setFont(new Font("Railway", Font.BOLD, 18));
        r3.setBounds(600, 200, 250, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit");
        r4.setFont(new Font("Railway", Font.BOLD, 18));
        r4.setBounds(900, 200, 250, 30);
        add(r4);

        ButtonGroup groupAcc = new ButtonGroup();
        groupAcc.add(r1);
        groupAcc.add(r2);
        groupAcc.add(r3);
        groupAcc.add(r4);

        JLabel cardNo = new JLabel("Card Number:");
        cardNo.setFont(new Font("Railway", Font.BOLD, 22));
        cardNo.setBounds(100, 250, 250, 30);
        add(cardNo);

        JLabel cnumber = new JLabel("XXXX XXXX XXXX 3184");
        cnumber.setFont(new Font("Railway", Font.BOLD, 22));
        cnumber.setBounds(280, 250, 280, 30);
        add(cnumber);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Railway", Font.BOLD, 22));
        pin.setBounds(100, 300, 280, 30);
        add(pin);

        JLabel cpin = new JLabel("XXXX");
        cpin.setFont(new Font("Railway", Font.BOLD, 22));
        cpin.setBounds(280, 300, 280, 30);
        add(cpin);

        JLabel aservices = new JLabel("Services Needed:");
        aservices.setFont(new Font("Railway", Font.BOLD, 22));
        aservices.setBounds(100, 380, 350, 30);
        add(aservices);

        s1 = new JCheckBox("ATM Card");
        s1.setFont(new Font("Railway", Font.BOLD, 18));
        s1.setBounds(100, 420, 250, 30);
        add(s1);

        s2 = new JCheckBox("Internet Banking");
        s2.setFont(new Font("Railway", Font.BOLD, 18));
        s2.setBounds(360, 420, 200, 30);
        add(s2);

        s3 = new JCheckBox("Mobile Banking");
        s3.setFont(new Font("Railway", Font.BOLD, 18));
        s3.setBounds(600, 420, 250, 30);
        add(s3);

        s4 = new JCheckBox("Email Alerts");
        s4.setFont(new Font("Railway", Font.BOLD, 18));
        s4.setBounds(900, 420, 250, 30);
        add(s4);

        s5 = new JCheckBox("Cheque Book");
        s5.setFont(new Font("Railway", Font.BOLD, 18));
        s5.setBounds(1200, 420, 250, 30);
        add(s5);

        s6 = new JCheckBox("E-Statement");
        s6.setFont(new Font("Railway", Font.BOLD, 18));
        s6.setBounds(100, 450, 250, 30);
        add(s6);

        s7 = new JCheckBox("I declare that the above details are correct");
        s7.setFont(new Font("Railway", Font.BOLD, 18));
        s7.setBounds(100, 520, 700, 30);
        add(s7);

        submit = new JButton("Submit");
        submit.setFont(new Font("Railway", Font.BOLD, 22));
        submit.setBounds(100, 580, 200, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Railway", Font.BOLD, 22));
        cancel.setBounds(600, 580, 200, 30);
        cancel.addActionListener(this);
        add(cancel);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {
            String AType = null;

            // Ensure account type is selected
            if (r1.isSelected()) {
                AType = "Saving Account";
            } else if (r2.isSelected()) {
                AType = "Current Account";
            } else if (r3.isSelected()) {
                AType = "Fixed Account";
            } else if (r4.isSelected()) {
                AType = "Recurring Deposit";
            }

            Random random = new Random();
            String cardNumber = "" + Math.abs((random.nextLong() % 90000000L) + 5030936000000000L);
            String pin = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            // Collect selected services
            StringBuilder facility = new StringBuilder();

            boolean serviceSelected = false;
            if (s1.isSelected()) {
                facility.append("ATM Card ");
                serviceSelected = true;
            }
            if (s2.isSelected()) {
                facility.append("Internet Banking ");
                serviceSelected = true;
            }
            if (s3.isSelected()) {
                facility.append("Mobile Banking ");
                serviceSelected = true;
            }
            if (s4.isSelected()) {
                facility.append("Email Alerts ");
                serviceSelected = true;
            }
            if (s5.isSelected()) {
                facility.append("Cheque Book ");
                serviceSelected = true;
            }
            if (s6.isSelected()) {
                facility.append("E-Statement ");
                serviceSelected = true;
            }

            try {
                // Check for all required fields
                if (AType == null) {
                    JOptionPane.showMessageDialog(null, "Account type is required.");
                } else if (!serviceSelected) {
                    JOptionPane.showMessageDialog(null, "At least one service must be selected.");
                } else if (!s7.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please declare that the information is correct.");
                } else {
                    Conn conn = new Conn();
                    String query1 = "INSERT INTO Signupthree VALUES('" + formno + "','" + AType + "','" + cardNumber + "','" + pin + "','" + facility + "')";
                    conn.s.executeUpdate(query1);

                    String query2 = "INSERT INTO login VALUES('" + formno + "','" + cardNumber + "','" + pin + "')";
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPin: " + pin + "\nPlease keep this information safe.");

                    setVisible(false);
                    new Login();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Signupthree("");
    }
}
