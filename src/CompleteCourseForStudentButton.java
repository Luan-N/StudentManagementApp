import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CompleteCourseForStudentButton extends JButton implements ActionListener {

    private App app;

    public CompleteCourseForStudentButton(App app) {
        super("Complete Course For Student"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to complete an in progress course for a student
    }
}