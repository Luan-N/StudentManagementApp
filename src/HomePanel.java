import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private App app; // Reference to the main app

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
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);

        // Create split pane with horizontal split
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, displayPanel);
        splitPane.setResizeWeight(0.33); // controlPanel gets 1/3 of the space
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);
        controlPanel.add(new LogOutButton(app));
    }
}
