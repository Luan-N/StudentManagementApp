import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewAllCoursesButton extends JButton implements ActionListener {

    private App app;

    public ViewAllCoursesButton(App app) {
        super("View All Courses"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to view all courses
    }
}