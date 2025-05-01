import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewStudentInfoButton extends JButton implements ActionListener {

    private App app;

    public ViewStudentInfoButton(App app) {
        super("View Student Info"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to view student info
    }
}