import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LogOutButton extends JButton implements ActionListener {

    private App app;

    public LogOutButton(App app) {
        super("Log Out"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        app.currentUser = null; // Clear the current user
        app.showLoginPanel(); // Show the login panel again
    }
}