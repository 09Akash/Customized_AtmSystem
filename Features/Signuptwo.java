package atm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signuptwo extends JFrame implements ActionListener {

    JTextField adharNumTextField, idNumTextField, countryTextField, mobNumTextField;
    JButton next;
    JRadioButton indian, nri;
    JComboBox<String> typeId, occupationType, religionBox;
    String formno;
    JLabel occType;

    Signuptwo(String formno) {
        this.formno = formno;
        setLayout(null);
        setTitle("New Account Application: Page 2");

        // Additional Details Label
        JLabel addDetails = new JLabel("Page 2: Additional Details");
        addDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        addDetails.setBounds(290, 80, 400, 30);
        add(addDetails);

        // Aadhar Number Label and Text Field
        JLabel adharNum = new JLabel("Aadhar Number: ");
        adharNum.setFont(new Font("Raleway", Font.PLAIN, 18));
        adharNum.setBounds(100, 150, 200, 30);
        add(adharNum);

        adharNumTextField = new JTextField();
        adharNumTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        adharNumTextField.setBounds(300, 150, 400, 30);
        adharNumTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(adharNumTextField);

        // Identification Type ComboBox
        JLabel idType = new JLabel("Identification Type: ");
        idType.setFont(new Font("Raleway", Font.PLAIN, 18));
        idType.setBounds(100, 200, 200, 30);
        add(idType);

        String[] TYPEID = {"-----Choose from below-----", "Passport", "Election ID", "Pan card", "Driving License"};
        typeId = new JComboBox<>(TYPEID);
        typeId.setFont(new Font("Arial", Font.PLAIN, 16));
        typeId.setBackground(Color.WHITE);
        typeId.setBounds(300, 200, 300, 30);
        add(typeId);

        // Identification Number Label and Text Field
        JLabel idNum = new JLabel("Identification Number: ");
        idNum.setFont(new Font("Raleway", Font.PLAIN, 18));
        idNum.setBounds(100, 250, 200, 30);
        add(idNum);

        idNumTextField = new JTextField();
        idNumTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        idNumTextField.setBounds(300, 250, 400, 30);
        idNumTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(idNumTextField);

        // Occupation Type ComboBox
        occType = new JLabel("Occupation Type: ");
        occType.setFont(new Font("Raleway", Font.PLAIN, 18));
        occType.setBounds(100, 300, 200, 30);
        add(occType);

        String[] valOccupation = {"-----Choose from below-----", "Service", "Business", "Student", "Others"};
        occupationType = new JComboBox<>(valOccupation);
        occupationType.setFont(new Font("Arial", Font.PLAIN, 16));
        occupationType.setBackground(Color.WHITE);
        occupationType.setBounds(300, 300, 300, 30);
        add(occupationType);

        // Religion ComboBox
        JLabel religion = new JLabel("Religion: ");
        religion.setFont(new Font("Raleway", Font.PLAIN, 18));
        religion.setBounds(100, 350, 200, 30);
        add(religion);

        String[] valReligion = {"-----Choose from below-----", "Hindu", "Muslim", "Sikh", "Christian", "Others"};
        religionBox = new JComboBox<>(valReligion);
        religionBox.setFont(new Font("Arial", Font.PLAIN, 16));
        religionBox.setBackground(Color.WHITE);
        religionBox.setBounds(300, 350, 400, 30);
        add(religionBox);

        // Nationality Radio Buttons
        JLabel nationality = new JLabel("Nationality: ");
        nationality.setFont(new Font("Raleway", Font.PLAIN, 18));
        nationality.setBounds(100, 400, 200, 30);
        add(nationality);

        indian = new JRadioButton("Indian");
        indian.setFont(new Font("Arial", Font.PLAIN, 16));
        indian.setBackground(Color.WHITE);
        indian.setBounds(300, 400, 100, 30);
        add(indian);

        nri = new JRadioButton("NRI");
        nri.setFont(new Font("Arial", Font.PLAIN, 16));
        nri.setBackground(Color.WHITE);
        nri.setBounds(450, 400, 100, 30);
        add(nri);

        ButtonGroup nationalityGroup = new ButtonGroup();
        nationalityGroup.add(indian);
        nationalityGroup.add(nri);

        // Country of Residence Label and Text Field
        JLabel country = new JLabel("Country of Residence: ");
        country.setFont(new Font("Raleway", Font.PLAIN, 18));
        country.setBounds(100, 450, 200, 30);
        add(country);

        countryTextField = new JTextField();
        countryTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        countryTextField.setBounds(300, 450, 400, 30);
        countryTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(countryTextField);

        // Mobile Number Label and Text Field
        JLabel mobNum = new JLabel("Mob/Telephone Number:");
        mobNum.setFont(new Font("Raleway", Font.PLAIN, 18));
        mobNum.setBounds(100, 500, 250, 30);
        add(mobNum);

        mobNumTextField = new JTextField();
        mobNumTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        mobNumTextField.setBounds(300, 500, 400, 30);
        mobNumTextField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true));
        add(mobNumTextField);

        // Next Button
        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 16));
        next.setBackground(new Color(0, 128, 255));
        next.setForeground(Color.WHITE);
        next.setBounds(620, 600, 80, 40);
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.addActionListener(this);
        add(next);

        // Frame Settings
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String adhar = adharNumTextField.getText().trim();
        String sidType = (String) typeId.getSelectedItem();
        String soccupationType = (String) occupationType.getSelectedItem();
        String sreligionBox = (String) religionBox.getSelectedItem();
        String idNum = idNumTextField.getText().trim();
        String country = countryTextField.getText().trim();
        String mobNum = mobNumTextField.getText().trim();

        String nationality = null;
        if (indian.isSelected()) {
            nationality = "Indian";
        } else if (nri.isSelected()) {
            nationality = "NRI";
        }

        // Validate inputs
        if (adhar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aadhar number is mandatory.");
            return;
        } 
        if (sidType.equals("-----Choose from below-----")) {
            JOptionPane.showMessageDialog(null, "Identification type is mandatory.");
            return;
        }
        if (idNum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Identification number is mandatory.");
            return;
        }
        if (soccupationType.equals("-----Choose from below-----")) {
            JOptionPane.showMessageDialog(null, "Occupation type is mandatory.");
            return;
        }
        if (sreligionBox.equals("-----Choose from below-----")) {
            JOptionPane.showMessageDialog(null, "Religion is mandatory.");
            return;
        }
        if (nationality == null) {
            JOptionPane.showMessageDialog(null, "Nationality is mandatory.");
            return;
        }
        if (country.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country of residence is mandatory.");
            return;
        }
        if (mobNum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mobile number is mandatory.");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo (formno, adharNum, IdentificationType, IdentificationNumber, occupationType, religion, nationality, country, mobileNumber) "
                         + "VALUES ('" + formno + "','" + adhar + "','" + sidType + "','" + idNum + "','" + soccupationType + "','" 
                         + sreligionBox + "','" + nationality + "','" + country + "','" + mobNum + "')";
            c.s.executeUpdate(query);
         
            setVisible(false);
            new Signupthree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Signuptwo(""); // Example form number
    }
}
