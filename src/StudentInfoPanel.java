import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StudentInfoPanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JTable courseTable;
    private DefaultTableModel courseTableModel;
    private final String[] columnNames = {"ID", "First Name", "Last Name", "GPA", "Units Completed", "Date of Birth", "Enrollment Date", "Enrollment Status"};
    private final String[] courseColumnNames = {"Course Name", "Units", "Status", "Grade"};

    public StudentInfoPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Student Info/Course Enrollment", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        //Student table
        studentTableModel = new DefaultTableModel(getStudentData(1), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(studentTableModel);
        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(new Color(60, 63, 65));
        header.setForeground(Color.WHITE);

        //course table
        courseTableModel = new DefaultTableModel(getCoursesOfStudent(1), courseColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        courseTable = new JTable(courseTableModel);
        JTableHeader courseHeader = courseTable.getTableHeader();
        courseHeader.setBackground(new Color(60, 63, 65));
        courseHeader.setForeground(Color.WHITE);

        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(2, 1)); // Two rows, one for each table
        tablesPanel.add(new JScrollPane(studentTable));
        tablesPanel.add(new JScrollPane(courseTable));

        add(tablesPanel, BorderLayout.CENTER);
    }

    //query to get student data from the database
    private Object[][] getStudentData(int id) {
        String query = "SELECT student_id, first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status FROM Student WHERE student_id = " + id;
        return DatabaseUtilities.executeQuery(query);
    }

    //query to get course data of a student from the database
    private Object[][] getCoursesOfStudent(int id) {
        String query = "SELECT course_name, units, course_status, grade FROM Course, Enrollment WHERE Course.course_id = Enrollment.course_id AND Enrollment.student_id = " + id;
        return DatabaseUtilities.executeQuery(query);
    }

    // Call this method to reload data
    public void update(int studentId) {
        Object[][] newStudentData = getStudentData(studentId);
        studentTableModel.setDataVector(newStudentData, columnNames);

        Object[][] newCourseData = getCoursesOfStudent(studentId);
        courseTableModel.setDataVector(newCourseData, courseColumnNames);
    }
}
