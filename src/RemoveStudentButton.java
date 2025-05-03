import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RemoveStudentButton extends JButton implements ActionListener {


    public RemoveStudentButton() {
        super("Remove Student"); // Set button text

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to remove a student from the database
        String input = JOptionPane.showInputDialog(this, "Enter Student ID:");
        DatabaseUtilities.executeUpdate("DELETE FROM Student WHERE student_id = ?" , new String[] { input });
    }
}