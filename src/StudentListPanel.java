import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StudentListPanel extends JPanel {

    public StudentListPanel() {
        setLayout(new BorderLayout());

        // Create a label to display a header above the table
        JLabel titleLabel = new JLabel("List of Students", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        titleLabel.setForeground(Color.BLACK); // Set the text color
        titleLabel.setBackground(Color.LIGHT_GRAY); // Set background color
        titleLabel.setOpaque(true); // Make the label background visible

        // Add the title label to the top of the panel
        add(titleLabel, BorderLayout.NORTH);

        String query = "SELECT student_id, first_name, last_name, units_completed, enrollment_status FROM Student";

        // Get the data from the database and convert it to a 2D array
        Object[][] data = DatabaseUtilities.executeQuery(query);

        // Column Names
        String[] columnNames = {"ID", "First Name", "Last Name", "Units Completed", "Status"};

        // Create table model with the data
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make the cells non-editable
            }
        };

        // Create JTable with the model
        JTable studentTable = new JTable(model);

        // Customize the table header to make it stand out
        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(new Color(60, 63, 65));  // Darker background color for header
        header.setForeground(Color.WHITE);  // White text color for contrast

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Add the scroll pane (with table) to the center of the panel
        add(scrollPane, BorderLayout.CENTER);
    }
}