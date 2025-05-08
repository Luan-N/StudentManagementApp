import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class AddCourseToStudentButton extends JButton implements ActionListener {

    private App app;

    public AddCourseToStudentButton(App app) {
        super("Add Course To Student"); // Set button text
        this.app = app; // Initialize the app reference

        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Implement the logic to add a course to a student
        String studentId = JOptionPane.showInputDialog(app, "Enter Student ID:");
        String courseId = JOptionPane.showInputDialog(app, "Enter Course ID:");
        String grade = JOptionPane.showInputDialog(app, "Enter Initial Grade (A-F or - if not yet graded):");

        if (studentId == null || courseId == null || grade == null ||
            studentId.isEmpty() || courseId.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(app, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (grade.length() != 1 || !grade.matches("[A-Fa-f\\-]")) {
            JOptionPane.showMessageDialog(app, "Grade must be a single letter A-F or '-'.", "Invalid Grade", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseUtilities.getConnection()) {
            String sql = "INSERT INTO Enrollment (student_id, course_id, course_status, grade) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.setString(3, "in_progress");
            stmt.setString(4, grade.toUpperCase());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(app, "Student successfully enrolled with grade.");
            } else {
                JOptionPane.showMessageDialog(app, "Enrollment failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(app, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}