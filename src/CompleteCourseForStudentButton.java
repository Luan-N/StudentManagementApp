import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
        String studentId = JOptionPane.showInputDialog(app, "Enter Student ID:");
        String courseId = JOptionPane.showInputDialog(app, "Enter Course ID:");
        String grade = JOptionPane.showInputDialog(app, "Enter Grade (A-F or - for no grade):");

        if (studentId == null || courseId == null || grade == null ||
            studentId.isEmpty() || courseId.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(app, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (grade.length() != 1) {
            JOptionPane.showMessageDialog(app, "Grade must be a single character.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseUtilities.getConnection()) {
            String sql = "UPDATE Enrollment SET course_status = ?, grade = ? WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "completed");
            stmt.setString(2, grade);
            stmt.setString(3, studentId);
            stmt.setString(4, courseId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(app, "Course marked as completed successfully.");
            } else {
                JOptionPane.showMessageDialog(app, "No matching enrollment found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(app, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}