import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DropCourseFromStudentButton extends JButton implements ActionListener {

    private App app;

    public DropCourseFromStudentButton(App app) {
        super("Drop Course From Student"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String studentId = JOptionPane.showInputDialog(app, "Enter Student ID:");
        String courseId = JOptionPane.showInputDialog(app, "Enter Course ID:");

        if (studentId == null || courseId == null || studentId.isEmpty() || courseId.isEmpty()) {
            JOptionPane.showMessageDialog(app, "Student ID and Course ID are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseUtilities.getConnection()) {
            String sql = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(app, "Course dropped successfully for student.");
            } else {
                JOptionPane.showMessageDialog(app, "No matching enrollment found.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(app, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}