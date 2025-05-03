import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StudentListPanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel model;
    private final String[] columnNames = {"ID", "First Name", "Last Name", "Units Completed", "Enrollment Status"};

    public StudentListPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("List of Students", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // Initial table data load
        model = new DefaultTableModel(getStudentData(), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(model);
        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(new Color(60, 63, 65));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    //query to get student data from the database
    private Object[][] getStudentData() {
        String query = "SELECT student_id, first_name, last_name, units_completed, enrollment_status FROM Student";
        return DatabaseUtilities.executeQuery(query);
    }
    

    // Call this method to reload data
    public void update() {
        Object[][] newData = getStudentData();
        model.setDataVector(newData, columnNames);
    }
}
