import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JPanel implements ActionListener {

    private static final String permissionCode = "1234"; //Permission code to create a staff account
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField permissionField;
    private JButton signupButton;
    private JButton loginLink;
    private App app; //Reference to the main app

    public SignUp(App app) {
        this.app = app; //Initialize the app reference
        //Layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JLabel perrmissionLabel = new JLabel("Permission Code:");
        permissionField = new JTextField(20);
        signupButton = new JButton("Sign Up");
        loginLink = new JButton("To Login");
        loginLink.setBorderPainted(false);
        loginLink.setOpaque(false);
        loginLink.setBackground(Color.WHITE);
        loginLink.setForeground(Color.BLUE);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.addActionListener(app); // Use the provided listener
        signupButton.addActionListener(this); // Use the provided listener

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Permission Code
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(perrmissionLabel, gbc);
        gbc.gridx = 1;
        add(permissionField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signupButton, gbc);

        gbc.gridy = 5;
        add(loginLink, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signupButton) {
            // Handle sign up logic here
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String permission = permissionField.getText();

            // Check for empty fields
            if (username.isEmpty() || password.isEmpty() || permission.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Check if the permission code is valid
            if (!permission.equals(permissionCode)) {//For simplicity, 1234 is the permission code to create a staff account
                JOptionPane.showMessageDialog(this, "Invalid permission code!");
                return;
            }

            // Check if the username already exists
            String existingPassword = DatabaseUtilities.getPasswordByUsername(username);
            if (existingPassword != null) {
                JOptionPane.showMessageDialog(this, "Username already exists! Please choose a different username.");
                return;
            }
            // Add the user to the database
            if (!DatabaseUtilities.addUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Error adding user! Please try again.");
                return;
            }

            //reset the fields
            usernameField.setText(""); passwordField.setText(""); permissionField.setText("");

            // If sign up is successful, show a success message
            JOptionPane.showMessageDialog(this, "Welcome " + username + "!");
            app.showHomePanel(username); // Show the home panel with the username

        }
    }
}