import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CourseListPanel extends JPanel {

    private JTable courseTable;
    private DefaultTableModel model;
    private final String[] columnNames = { "ID", "Course Name", "Course Description", "Units", "Capacity" };

    public CourseListPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("List of Courses", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // Initial table data load
        model = new DefaultTableModel(getCourseData(), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        courseTable = new JTable(model);
        //add tooltip to the table, for resizing the columns
        courseTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    setToolTipText(value.toString());
                }
                return c;
            }
        });

        JTableHeader header = courseTable.getTableHeader();

        header.setBackground(new Color(60, 63, 65));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(courseTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // query to get course data from the database
    private Object[][] getCourseData() {
        String query = "SELECT course_id, course_name, course_description, units, capacity FROM Course";
        return DatabaseUtilities.executeQuery(query);
    }

    // Call this method to reload data
    public void update() {
        Object[][] newData = getCourseData();
        model.setDataVector(newData, columnNames);
    }
}
