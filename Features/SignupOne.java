package atm.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, mailTextField, addressTextField, cityTextField, stateTextField, pincodeTextField;
    JButton next;
    JRadioButton male, female, trans, married, single, other;
    JDateChooser dateChooser;

    SignupOne() {
        setTitle("Sign Up Form");
        setLayout(null);

        // Generate random application number
        Random ran = new Random();
        long random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        // Form Number Label
        JLabel formno = new JLabel("APPLICATION FORM NO: " + random);
        formno.setFont(new Font("Osward", Font.BOLD, 28));
        formno.setForeground(new Color(0, 128, 255)); // Blue color for form number
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        // Personal Details Header
        JLabel personDetails = new JLabel("Page 1: Personal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personDetails.setBounds(290, 80, 400, 30);
        add(personDetails);

        // Name Label and Text Field
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.PLAIN, 18));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameTextField.setBounds(300, 140, 400, 30);
        nameTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(nameTextField);

        // Father's Name Label and Text Field
        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", Font.PLAIN, 18));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        fnameTextField.setBounds(300, 190, 400, 30);
        fnameTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(fnameTextField);

        // Date of Birth Label and Date Chooser
        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.PLAIN, 18));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(dateChooser);

        // Gender Label and Radio Buttons
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.PLAIN, 18));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 100, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 290, 100, 30);
        female.setBackground(Color.WHITE);
        add(female);

        trans = new JRadioButton("Transgender");
        trans.setBounds(600, 290, 130, 30);
        trans.setBackground(Color.WHITE);
        add(trans);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
        gendergroup.add(trans);

        // Email Label and Text Field
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.PLAIN, 18));
        email.setBounds(100, 340, 200, 30);
        add(email);

        mailTextField = new JTextField();
        mailTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        mailTextField.setBounds(300, 340, 400, 30);
        mailTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(mailTextField);

        // Marital Status Label and Radio Buttons
        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway", Font.PLAIN, 18));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);

        single = new JRadioButton("Single");
        single.setBounds(450, 390, 100, 30);
        single.setBackground(Color.WHITE);
        add(single);

        other = new JRadioButton("Other");
        other.setBounds(600, 390, 100, 30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(single);
        maritalgroup.add(other);

        // Address Label and Text Field
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.PLAIN, 18));
        address.setBounds(100, 440, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        addressTextField.setBounds(300, 440, 400, 30);
        addressTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(addressTextField);

        // City Label and Text Field
        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.PLAIN, 18));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        cityTextField.setBounds(300, 490, 400, 30);
        cityTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(cityTextField);

        // State Label and Text Field
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.PLAIN, 18));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        stateTextField.setBounds(300, 540, 400, 30);
        stateTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(stateTextField);

        // Pincode Label and Text Field
        JLabel pincode = new JLabel("Pincode:");
        pincode.setFont(new Font("Raleway", Font.PLAIN, 18));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        pincodeTextField.setBounds(300, 590, 400, 30);
        pincodeTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(pincodeTextField);

        // Next Button
        next = new JButton("Next");
        next.setBackground(new Color(0, 128, 255));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 16));
        next.setBounds(620, 660, 80, 40);
        next.setFocusPainted(false);
        next.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        next.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        next.addActionListener(this);
        add(next);

        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();

        Date dobDate = dateChooser.getDate();
        String dob = null;
        if (dobDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dob = sdf.format(dobDate);
        }

        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        } else if (trans.isSelected()) {
            gender = "Trans";
        }

        String email = mailTextField.getText();
        String marital = null;

        if (married.isSelected()) {
            marital = "Married";
        } else if (single.isSelected()) {
            marital = "Single";
        } else if (other.isSelected()) {
            marital = "Other";
        }

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pincodeTextField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Mandatory");
            } else {
                Conn c = new Conn();
                String query = "INSERT INTO signup (formno, name, fname, dob, gender, email, marital, address, city, state, pincode) VALUES ('"
                        + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','" 
                        + marital + "','" + address + "','" + city + "','" + state + "','" + pin + "')";
                c.s.executeUpdate(query);
                setVisible(false);
                new Signuptwo(formno).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
