import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private App app; // Reference to the main app
    private CardLayout cardLayout;

    public HomePanel(App app, String username) {
        this.app = app; // Initialize the app reference
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        // Create controls panel for control buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.LIGHT_GRAY);

        //Display panel for showing content
        //Create a card layout for the display panel
        cardLayout = new CardLayout();
        JPanel displayPanel = new JPanel(cardLayout);
        displayPanel.setBackground(Color.WHITE);

        // Add the StudentListPanel to the display panel
        StudentListPanel studentListPanel = new StudentListPanel();
        displayPanel.add(studentListPanel, "Student List");
        cardLayout.show(displayPanel, "Student List");

        // Create split pane with horizontal split
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, displayPanel);
        splitPane.setResizeWeight(0.33); // controlPanel gets 1/3 of the space
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);

        //Control Buttons
        controlPanel.add(new ViewAllCoursesButton(app));
        controlPanel.add(new ViewAllStudentsButton(app));
        controlPanel.add(new ViewStudentInfoButton(app));
        controlPanel.add(new AddNewCourseButton(app));
        controlPanel.add(new RegisterNewStudentButton(app));
        controlPanel.add(new AddCourseToStudentButton(app));
        controlPanel.add(new DropCourseFromStudentButton(app));
        controlPanel.add(new CompleteCourseForStudentButton(app));
        controlPanel.add(new LogOutButton(app));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    }
}
