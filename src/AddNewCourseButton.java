import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AddNewCourseButton extends JButton implements ActionListener {

    public AddNewCourseButton(App app) {
        super("Add new Course"); // Set button text
        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(this, "Enter Course Name:");
        if (name == null || name.trim().isEmpty()) return;

        String description = JOptionPane.showInputDialog(this, "Enter Course Description:");
        if (description == null || description.trim().isEmpty()) return;

        String unitsStr = JOptionPane.showInputDialog(this, "Enter Number of Units:");
        if (unitsStr == null || unitsStr.trim().isEmpty()) return;

        String capacityStr = JOptionPane.showInputDialog(this, "Enter Course Capacity:");
        if (capacityStr == null || capacityStr.trim().isEmpty()) return;

        try {
            int units = Integer.parseInt(unitsStr.trim());
            int capacity = Integer.parseInt(capacityStr.trim());

            String sql = "INSERT INTO Course (course_name, course_description, units, capacity) VALUES (?, ?, ?, ?)";
            String[] params = { name.trim(), description.trim(), String.valueOf(units), String.valueOf(capacity) };

            DatabaseUtilities.executeUpdate(sql, params);

            JOptionPane.showMessageDialog(this, "Course added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Units and Capacity must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to add course: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}