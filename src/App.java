import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    public String currentUser = null; //Current user of the app
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
        if (e.getActionCommand().equals("To Sign Up")) 
            showSignUpPanel();//Handle switch to Sign Up panel
        else if (e.getActionCommand().equals("To Login")) 
            showLoginPanel();//Handle switch to Login panel
    }

    public static void main(String[] args) {
        //Run the App
        SwingUtilities.invokeLater(() -> new App());
       
    }

    //When the user is logged in we want to dispaly the home panel
    public void showHomePanel(String username) {
        currentUser = username; //Set the current user
        HomePanel homePanel = new HomePanel(this, username); //Create the home panel with the username
        mainPanel.add(homePanel, "homePanel"); //Add the home panel to the main panel
        cardLayout.show(mainPanel, "homePanel"); //Show the home panel
    }
    public void showLoginPanel() {
        cardLayout.show(mainPanel, "loginPanel"); //Show the login panel
    }
    public void showSignUpPanel() {
        cardLayout.show(mainPanel, "signupPanel"); //Show the sign up panel
    }
}