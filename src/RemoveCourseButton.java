import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RemoveCourseButton extends JButton implements ActionListener {


    public RemoveCourseButton() {
        super("Remove Course"); // Set button text

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to remove a course from the database
        String input = JOptionPane.showInputDialog(this, "Enter Course ID:");
        DatabaseUtilities.executeUpdate("DELETE FROM Course WHERE course_id = ?" , new String[] { input });
    }
}