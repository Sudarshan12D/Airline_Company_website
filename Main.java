import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Create a label for the title and set its font
        JLabel titleLabel = new JLabel("Sign In", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        // Create a panel for the buttons and set its layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            // Clear all components from the frame
            frame.getContentPane().removeAll();

            // Create and add a top panel for the flights label and sign out button
            JPanel topPanel = new JPanel(new BorderLayout());

            // Create and add a label for "Available Flights"
            JLabel flightsLabel = new JLabel("Available Flights", SwingConstants.CENTER);
            flightsLabel.setFont(new Font("Serif", Font.BOLD, 24));
            topPanel.add(flightsLabel, BorderLayout.CENTER);

            // Create and add a sign out button at the top right corner
            JButton signOutButton = new JButton("Sign Out");
            signOutButton.addActionListener(ev -> {
                // Restore the initial state of the frame
                frame.getContentPane().removeAll();
                frame.add(titleLabel, BorderLayout.NORTH);
                frame.add(buttonPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            });
            topPanel.add(signOutButton, BorderLayout.EAST);

            // Add the top panel to the frame
            frame.add(topPanel, BorderLayout.NORTH);

            // Retrieve flight data
            ArrayList<String> flights = FlightDataRetriever.getAvailableFlights();

            // Create a panel or other component to display flight data
            JPanel flightsPanel = new JPanel();
            flightsPanel.setLayout(new BoxLayout(flightsPanel, BoxLayout.Y_AXIS));
            for (String flightInfo : flights) {
                flightsPanel.add(new JLabel(flightInfo));
            }

            // Add the flights panel to the frame
            JScrollPane scrollPane = new JScrollPane(flightsPanel);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Refresh the frame
            frame.revalidate();
            frame.repaint();
        });

        // Create the sign up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            // Create a new frame for sign up
            JFrame signUpFrame = new JFrame("Sign Up");
            signUpFrame.setLayout(new FlowLayout());
            signUpFrame.setSize(300, 300);
            signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Add components for user information
            signUpFrame.add(new JLabel("Username:"));
            signUpFrame.add(new JTextField(20));
            signUpFrame.add(new JLabel("Email:"));
            signUpFrame.add(new JTextField(20));
            signUpFrame.add(new JLabel("Password:"));
            signUpFrame.add(new JPasswordField(20));

            // Add a submit button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(ev -> signUpFrame.dispose());
            signUpFrame.add(submitButton);

            // Display the sign-up frame
            signUpFrame.setVisible(true);
        });

        // Add buttons to the button panel
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        // Add components to the frame
        frame.add(titleLabel, BorderLayout.NORTH); // Title at the top
        frame.add(buttonPanel, BorderLayout.CENTER); // Buttons in the center

        // Set the frame visible
        frame.setVisible(true);
    }
}
