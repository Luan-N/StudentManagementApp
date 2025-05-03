import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewAllStudentsButton extends JButton implements ActionListener {

    private HomePanel homePanel; // Reference to the HomePanel

    public ViewAllStudentsButton(HomePanel homePanel) {
        super("View All Students"); // Set button text
        this.homePanel = homePanel; // Initialize the panel reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to view all students
        homePanel.showStudentList(); // Call the method to show all students
    }
}