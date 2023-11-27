import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Senn Airways");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLayout(new BorderLayout());

        // Replace the below path with the actual path to your background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("plane.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);

        // Create a label for the title and set its font
        JLabel titleLabel = new JLabel("Welcome to Senn Airways", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setOpaque(false);

        // Create a panel for the buttons and set its layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);

        // Create the login button
        JButton viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.addActionListener(e -> {
            // Clear all components from the frame
            frame.getContentPane().removeAll();

            // Create and add a top panel for the flights label and sign out button
            JPanel topPanel = new JPanel(new BorderLayout());

            // Create and add a label for "Available Flights"
            JLabel flightsLabel = new JLabel("Available Flights", SwingConstants.CENTER);
            flightsLabel.setFont(new Font("Serif", Font.BOLD, 24));
            topPanel.add(flightsLabel, BorderLayout.CENTER);


            // Create and add "Login" and "Signup" buttons at the top right corner
            JPanel authButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            
            topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 0, 10, 0) // Adds padding above and below the header
            ));

            //..............................LOGIN EVENT Listener..........................................................
            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(loginEvent -> {
                // Create a new frame for login
                JFrame loginFrame = new JFrame("Login");
                loginFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame
                loginFrame.setSize(350, 220); // Initial size
                loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
                // Panel that contains the actual login form
                JPanel loginCenterPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbcLogin = new GridBagConstraints();
                gbcLogin.insets = new Insets(10, 10, 10, 10);
                gbcLogin.anchor = GridBagConstraints.CENTER;
                gbcLogin.gridx = 0;
                gbcLogin.gridy = 0;
            
                // Title "Login"
                JLabel loginTitleLabel = new JLabel("Login");
                loginTitleLabel.setFont(new Font(loginTitleLabel.getFont().getName(), Font.BOLD, 18)); // Set font to bold and size 18
                gbcLogin.gridwidth = 2; // This component will span two columns
                gbcLogin.gridx = 0; // Align to the first column
                gbcLogin.gridy = 0; // Place it on the first row
                gbcLogin.anchor = GridBagConstraints.CENTER; // Center alignment within its cell
                loginCenterPanel.add(loginTitleLabel, gbcLogin);
            
                // Reset settings for the next components
                gbcLogin.gridwidth = 1; // Reset to default
                gbcLogin.gridy++; // Increment row for the next component
                gbcLogin.anchor = GridBagConstraints.WEST; // Align subsequent components to the west
            
                // Email label and text field
                loginCenterPanel.add(new JLabel("Email:"), gbcLogin);
                gbcLogin.gridx++; // Move to the next column
                JTextField loginEmailField = new JTextField(20);
                loginCenterPanel.add(loginEmailField, gbcLogin);
            
                // Password label and text field
                gbcLogin.gridx = 0; // Reset to first column
                gbcLogin.gridy++; // Move to next row
                loginCenterPanel.add(new JLabel("Password:"), gbcLogin);
                gbcLogin.gridx++; // Move to the next column
                JPasswordField loginPasswordField = new JPasswordField(20);
                loginCenterPanel.add(loginPasswordField, gbcLogin);
            
                // Login button constraints
                gbcLogin.gridx = 0; // Start at the first column
                gbcLogin.gridy++; // Move to the next row after the text fields
                gbcLogin.gridwidth = 2; // Span across two columns
                gbcLogin.weightx = 0; // Do not let it grow horizontally
                gbcLogin.fill = GridBagConstraints.NONE; // Do not resize the button
                gbcLogin.anchor = GridBagConstraints.CENTER; // Center the button horizontally in its cell
                gbcLogin.insets = new Insets(20, 0, 10, 0); // Top padding of 20, bottom padding of 10
            
                // Create and add the login button
                JButton loginSubmitButton = new JButton("Submit");
                loginSubmitButton.addActionListener(loginSubmitEvent -> {
                    if (loginEmailField.getText().trim().isEmpty() || loginPasswordField.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(loginFrame, "Email or password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        /// Here you would handle the login
                        // For now, we'll just close the login window
                        loginFrame.dispose();
                    }
                    
                });
                loginCenterPanel.add(loginSubmitButton, gbcLogin);
            
                // Add the loginCenterPanel to the frame
                loginFrame.add(loginCenterPanel, BorderLayout.CENTER);
            
                // Display the login frame
                loginFrame.pack(); // Pack the frame to respect preferred sizes
                loginFrame.setLocationRelativeTo(null); // Center on screen
                loginFrame.setVisible(true);
            });



            //....................................SIGNUP EVENT Listener...................................................
            JButton signUpButton = new JButton("Signup");
            signUpButton.addActionListener(ev -> {
                // Create a new frame for sign up
                JFrame signUpFrame = new JFrame("Sign Up");
                signUpFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame
                signUpFrame.setSize(350, 220); // Initial size
                signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
                // Panel that contains the actual sign up form
                JPanel centerPanel = new JPanel(new GridBagLayout());
            
                // Constraints for the components
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridx = 0;
                gbc.gridy = 0;
            
                // Title "Create new user"
                JLabel signuptitleLabel = new JLabel("Create new user");
                signuptitleLabel.setFont(new Font(signuptitleLabel.getFont().getName(), Font.BOLD, 18)); // Set font to bold and size 18
                gbc.gridwidth = 2; // This component will span two columns
                gbc.gridx = 0; // Align to the first column
                gbc.gridy = 0; // Place it on the first row
                gbc.anchor = GridBagConstraints.CENTER; // Center alignment within its cell
                centerPanel.add(signuptitleLabel, gbc);
            
                // Reset settings for the next components
                gbc.gridwidth = 1; // Reset to default
                gbc.gridy++; // Increment row for the next component
                gbc.anchor = GridBagConstraints.WEST; // Align subsequent components to the west
            
                // Email label and text field
                centerPanel.add(new JLabel("Email:"), gbc);
                gbc.gridx++; // Move to the next column
                JTextField emailField = new JTextField(20);
                centerPanel.add(emailField, gbc);
            
                // Password label and text field
                gbc.gridx = 0; // Reset to first column
                gbc.gridy++; // Move to next row
                centerPanel.add(new JLabel("Password:"), gbc);
                gbc.gridx++; // Move to the next column
                JPasswordField passwordField = new JPasswordField(20);
                centerPanel.add(passwordField, gbc);
            
                // Submit button constraints
                gbc.gridx = 0; // Start at the first column
                gbc.gridy++; // Move to the next row after the text fields
                gbc.gridwidth = 2; // Span across two columns
                gbc.weightx = 0; // Do not let it grow horizontally
                gbc.fill = GridBagConstraints.NONE; // Do not resize the button
                gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally in its cell
                gbc.insets = new Insets(20, 0, 10, 0); // Top padding of 20, bottom padding of 10

                // Create and add the submit button
                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    if (emailField.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(signUpFrame, "Email or password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // If not empty, proceed with your submission logic
                        signUpFrame.dispose();
                    }
                });

                centerPanel.add(submitButton, gbc);
            
                // Add the centerPanel to the frame
                signUpFrame.add(centerPanel, BorderLayout.CENTER);
            
                // Create transparent border panels to center the form
                JPanel top = new JPanel();
                top.setOpaque(false);
                signUpFrame.add(top, BorderLayout.NORTH);
            
                JPanel bottom = new JPanel();
                bottom.setOpaque(false);
                signUpFrame.add(bottom, BorderLayout.SOUTH);
            
                JPanel left = new JPanel();
                left.setOpaque(false);
                signUpFrame.add(left, BorderLayout.WEST);
            
                JPanel right = new JPanel();
                right.setOpaque(false);
                signUpFrame.add(right, BorderLayout.EAST);
            
                // Display the sign-up frame
                signUpFrame.pack(); // Pack the frame to respect preferred sizes
                signUpFrame.setLocationRelativeTo(null); // Center on screen
                signUpFrame.setVisible(true);
            });
            
            
            authButtonsPanel.add(loginButton);
            authButtonsPanel.add(signUpButton);
            topPanel.add(authButtonsPanel, BorderLayout.EAST);

            // Add the top panel to the frame
            frame.add(topPanel, BorderLayout.NORTH);

            //.................................... Retrieve flight data......................................................
            ArrayList<String> flightDataStrings = FlightDataRetriever.getAvailableFlights();

            String[] columnNames = {"Flight ID", "Origin", "Destination", "Departure", "Arrival"};
            Object[][] data = new Object[flightDataStrings.size()][columnNames.length];

            // Parse the flight data strings into the data array for the JTable
            for (int i = 0; i < flightDataStrings.size(); i++) {
                String[] flightInfo = flightDataStrings.get(i).split(", ");
                for (int j = 0; j < flightInfo.length; j++) {
                    // Assuming each piece of flight information follows the format "Key: Value"
                    String[] entry = flightInfo[j].split(": ");
                    if (entry.length > 1) {
                        data[i][j] = entry[1].trim(); 
                    }
                }
            }

            // Create the table and add it to a scroll pane
            JTable table = new JTable(data, columnNames);
            table.setAutoCreateRowSorter(true); // Allow sorting of columns
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            // Add the table to the frame
            frame.add(scrollPane, BorderLayout.CENTER);

            // Refresh the frame
            frame.revalidate();
            frame.repaint();
        });

        
        // Add buttons to the button panel
        buttonPanel.add(viewFlightsButton);


        // Add components to the frame
        frame.add(titleLabel, BorderLayout.NORTH); // Title at the top
        frame.add(buttonPanel, BorderLayout.CENTER); // Buttons in the center

        // Set the frame visible
        frame.setVisible(true);
    }
}
