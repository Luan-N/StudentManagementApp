import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ViewStudentInfoButton extends JButton implements ActionListener {

    private HomePanel homePanel;

    public ViewStudentInfoButton(HomePanel homePanel) {
        super("View Student Info"); // Set button text
        this.homePanel = homePanel; // Initialize the homePanel reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to view student info
        String input = JOptionPane.showInputDialog(this, "Enter Student ID:");
        homePanel.showStudentInfo(Integer.parseInt(input)); // Call the method to show student info
    }
}