import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(String username) {
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(welcomeLabel, BorderLayout.NORTH);
    }
}