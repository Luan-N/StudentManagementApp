import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddNewCourseButton extends JButton implements ActionListener {

    private App app;

    public AddNewCourseButton(App app) {
        super("Add new Course"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to add new course
    }
}