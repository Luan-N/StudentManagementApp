import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupLink;
    private App app; //Reference to the main app

    public Login(App app) {
        this.app = app; //Initialize the app reference
        //Layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create username field, password field, login, and to sign up componnents
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupLink = new JButton("To Sign Up");
        signupLink.setBorderPainted(false);
        signupLink.setOpaque(false);
        signupLink.setBackground(Color.WHITE);
        signupLink.setForeground(Color.BLUE);
        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signupLink.addActionListener(app); // switchLisner(App.java) will be notified
        loginButton.addActionListener(this);//This will be notified

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

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        // To Sign Up link
        gbc.gridy = 3;
        add(signupLink, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Handle login action
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            //check for empty fields   
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            //Check if the username and password are correct
            String storedPassword = DatabaseUtilities.getPasswordByUsername(username);
            if (storedPassword == null) {
                JOptionPane.showMessageDialog(this, "Username not found!");
                return;
            } else if (!storedPassword.equals(password)) {
                JOptionPane.showMessageDialog(this, "Incorrect password!");
                return;
            }

            // If login is successful, show a success message
            JOptionPane.showMessageDialog(this, "Welcome " + username + "!");
            app.showHomePanel(username); //Show the home panel with the username
        }
    }

    //Getters methods
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}