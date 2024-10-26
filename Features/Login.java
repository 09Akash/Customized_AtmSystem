package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {

    JButton signup, login, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("ATM Machine");
        setLayout(null);

        // Logo Setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Welcome Text
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setBounds(200, 40, 400, 40);
        text.setFont(new Font("Osward", Font.BOLD, 36));
        text.setForeground(new Color(34, 139, 34)); // Dark green color
        add(text);

        // Card Number Label
        JLabel cardno = new JLabel("CARD NO :");
        cardno.setBounds(120, 150, 190, 40);
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setForeground(Color.DARK_GRAY);
        add(cardno);

        // Card Number Field
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 40);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 18));
        cardTextField.setForeground(Color.BLACK);
        add(cardTextField);

        // PIN Label
        JLabel pin = new JLabel("PIN :");
        pin.setBounds(120, 220, 190, 40);
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setForeground(Color.DARK_GRAY);
        add(pin);

        // PIN Field
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 40);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 18));
        pinTextField.setForeground(Color.BLACK);
        add(pinTextField);

        // Login Button
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 120, 40);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(new Color(34, 139, 34));
        login.setForeground(Color.WHITE);
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                login.setCursor(new Cursor(Cursor.HAND_CURSOR));

        login.setFocusPainted(false);
        login.addActionListener(this);
        add(login);

        // Clear Button
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 120, 40);
        clear.setFont(new Font("Arial", Font.BOLD, 16));
        clear.setBackground(new Color(220, 20, 60));
        clear.setForeground(Color.WHITE);
        clear.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        clear.setFocusPainted(false);
        clear.addActionListener(this);
                clear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(clear);

        // Signup Button
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 370, 250, 40);
        signup.setFont(new Font("Arial", Font.BOLD, 16));
        signup.setBackground(new Color(0, 128, 255));
        signup.setForeground(Color.WHITE);
        signup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        signup.setFocusPainted(false);
                signup.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signup.addActionListener(this);
        add(signup);

        // Background Color
        getContentPane().setBackground(Color.WHITE);

        // Frame settings
        setSize(800, 480);
        setLocation(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinNumber = pinTextField.getText();
            String query = "SELECT * FROM Login WHERE cardNumber = '" + cardnumber + "' AND pin = '" + pinNumber + "'";

            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transaction(pinNumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
