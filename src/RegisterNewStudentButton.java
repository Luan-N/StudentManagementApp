import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RegisterNewStudentButton extends JButton implements ActionListener {


    public RegisterNewStudentButton(App app) {
        super("Register New Student"); // Set button text
        // Add action listener to handle log out action
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = JOptionPane.showInputDialog(this, "Enter Student's First Name:");
        if (firstName == null || firstName.trim().isEmpty()) return;

        String lastName = JOptionPane.showInputDialog(this, "Enter Student's Last Name:");
        if (lastName == null || lastName.trim().isEmpty()) return;

        String gpaStr = JOptionPane.showInputDialog(this, "Enter Student's GPA (0-4):");
        if (gpaStr == null || gpaStr.trim().isEmpty()) return;

        String unitsStr = JOptionPane.showInputDialog(this, "Enter Units Completed:");
        if (unitsStr == null || unitsStr.trim().isEmpty()) return;

        String birthStr = JOptionPane.showInputDialog(this, "Enter Date of Birth (YYYY-MM-DD):");
        if (birthStr == null || birthStr.trim().isEmpty()) return;

        String enrollmentDateStr = JOptionPane.showInputDialog(this, "Enter Enrollment Date (YYYY-MM-DD):");
        if (enrollmentDateStr == null || enrollmentDateStr.trim().isEmpty()) return;

        String status = (String) JOptionPane.showInputDialog(this,
                "Select Enrollment Status:",
                "Enrollment Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"active", "dismissed", "graduated"},
                "active");

        if (status == null) return;

        try {
            double gpa = Double.parseDouble(gpaStr.trim());
            int unitsCompleted = Integer.parseInt(unitsStr.trim());
            LocalDate birth = LocalDate.parse(birthStr.trim());
            LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr.trim());

            if (gpa < 0 || gpa > 4) {
                throw new IllegalArgumentException("GPA must be between 0 and 4");
            }

            if (unitsCompleted < 0) {
                throw new IllegalArgumentException("Units completed cannot be negative");
            }

            if (enrollmentDate.isBefore(birth)) {
                throw new IllegalArgumentException("Enrollment date cannot be before date of birth");
            }

            String sql = "INSERT INTO Student (first_name, last_name, GPA, units_completed, " +
                       "date_of_birth, enrollment_date, enrollment_status) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
            String[] params = {
                firstName.trim(),
                lastName.trim(),
                String.valueOf(gpa),
                String.valueOf(unitsCompleted),
                birth.toString(),
                enrollmentDate.toString(),
                status
            };

            DatabaseUtilities.executeUpdate(sql, params);
            JOptionPane.showMessageDialog(this, "Student registered successfully!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for GPA and Units", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Please enter dates in YYYY-MM-DD format (e.g., 2000-01-15)", 
                "Date Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to register student: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}