import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewAllCoursesButton extends JButton implements ActionListener {

    private HomePanel homePanel;

    public ViewAllCoursesButton(HomePanel homePanel) {
        super("View All Courses"); // Set button text
        this.homePanel = homePanel; // Initialize the homePanel reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to view all courses
        homePanel.showCourseList(); // Call the method to show all courses
    }
}