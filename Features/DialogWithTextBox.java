import javax.swing.*;

public class DialogWithTextBox {
    public static void main(String[] args) {
        // Create a JTextField
        JTextField textField = new JTextField();

        // Create an array containing the message and the text field
        Object[] message = {
            "ENTER THE PIN:", textField
        };

        // Show the input dialog using JOptionPane
        int option = JOptionPane.showConfirmDialog(null, message, "Input Dialog", JOptionPane.OK_CANCEL_OPTION);

        // Check if the user clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            String inputText = textField.getText();
            System.out.println("Input: " + inputText);
        } else {
            System.out.println("User canceled the input.");
        }
    }
}
