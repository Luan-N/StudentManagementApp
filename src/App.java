import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Login loginPanel;
    private SignUp signUpPanel;

    public App() {
        // layouts
        setTitle("Template App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create Login Panel and pass the ActionListener for switching
        loginPanel = new Login(this);
        mainPanel.add(loginPanel, "loginPanel");

        // Create Signup Panel and pass the ActionListener for switching
        signUpPanel = new SignUp(this);
        mainPanel.add(signUpPanel, "signupPanel");

        add(mainPanel, BorderLayout.CENTER);

        // Show Login Page by default
        cardLayout.show(mainPanel, "loginPanel");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("To Sign Up")) {
            cardLayout.show(mainPanel, "signupPanel");//Handle switch to Sign Up panel
        } else if (e.getActionCommand().equals("To Login")) {
            cardLayout.show(mainPanel, "loginPanel");//Handle switch to Login panel
        }
    }

    public static void main(String[] args) {
        //Run the App
        SwingUtilities.invokeLater(() -> new App());
    }
}