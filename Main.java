import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;


public class Main {


    private static JLabel selectedSeatsLabel;
    static ArrayList<String> selectedSeats = new ArrayList<>();

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
            ArrayList<Object[]> flightData = FlightDataRetriever.getAvailableFlights();

           
            Object[][] data = new Object[flightData.size()][6]; // Adjusted for 6 columns
        
            // Copy the flight data to the data array and add a "Book" button to each row
            for (int i = 0; i < flightData.size(); i++) {
                System.arraycopy(flightData.get(i), 0, data[i], 0, 5);
                data[i][5] = "View Seats"; // Place a "Book" placeholder that will be replaced with a button
            }

            String[] columnNames = {"Flight ID", "Origin", "Destination", "Departure", "Arrival", "Select Seats"};

            // Create a DefaultTableModel with the new column
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                public boolean isCellEditable(int row, int column) {
                    return column == 5; // Only the "Select Flight" column is editable
                }
                public Class<?> getColumnClass(int column) {
                    return String.class; // Set the class for all cells to String for simplicity
                }
            };
            // Create the table and add it to a scroll pane
            JTable table = new JTable(model);

            
            // Add a mouse listener to handle clicks on the "Book" button
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                    int row = e.getY() / table.getRowHeight();
            
                    if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                        if ("View Seats".equals(table.getValueAt(row, column))) {
                            // Create and display the seat selection frame
                            JFrame seatsFrame = new JFrame("Select Seats");
                            seatsFrame.setLayout(new BorderLayout());
                            seatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            seatsFrame.setSize(950, 500);
                            
                            // Create a label for displaying selected seats
                            selectedSeatsLabel = new JLabel("<html>Seats selected: </html>");
                            selectedSeatsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
                            selectedSeatsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                            // Main panel with GridBagLayout
                            JPanel mainPanel = new JPanel(new GridBagLayout());
                            mainPanel.setBorder(BorderFactory.createTitledBorder("Select Seats"));

                            GridBagConstraints seatsGbc = new GridBagConstraints();
                            seatsGbc.insets = new Insets(5, 5, 5, 5);

                            // Panel for seats with GridLayout
                            JPanel seatsPanel = new JPanel(new GridLayout(6, 7, 10, 10)); // 8 rows, 7 cols for spacers
                            seatsPanel.setPreferredSize(new Dimension(600, 300));

                            // Method to create a label with a colored box
                            Function<String, JPanel> createColorInfoPanel = (String text) -> {
                                JPanel panel = new JPanel();
                                panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
                            
                                JLabel colorLabel = new JLabel();
                                colorLabel.setPreferredSize(new Dimension(15, 15));
                                colorLabel.setOpaque(true);
                            
                                // Set color based on the text
                                switch (text) {
                                    case "Business Class $1000":
                                        colorLabel.setBackground(Color.ORANGE);
                                        break;
                                    case "Economy Class $500":
                                        colorLabel.setBackground(Color.BLUE);
                                        break;
                                    case "First Class $700":
                                        colorLabel.setBackground(Color.YELLOW);
                                        break;
                                    case "Selected Seats":
                                        colorLabel.setBackground(Color.GREEN);
                                        break;
                                    case "Reserved Seats":
                                        colorLabel.setBackground(Color.GRAY);
                                        break;
                                }
                            
                                JLabel textLabel = new JLabel(text);
                                panel.add(colorLabel);
                                panel.add(textLabel);
                            
                                return panel;
                            };

                            // Info panel for class colors
                            JPanel infoPanel = new JPanel();
                            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                            infoPanel.setBorder(BorderFactory.createTitledBorder("Seat Information"));

                            // Add color-coded labels to info panel
                            infoPanel.add(createColorInfoPanel.apply("Business Class $1000"));
                            infoPanel.add(createColorInfoPanel.apply("Economy Class $500"));
                            infoPanel.add(createColorInfoPanel.apply("First Class $700"));
                            infoPanel.add(createColorInfoPanel.apply("Selected Seats"));
                            infoPanel.add(createColorInfoPanel.apply("Reserved Seats"));


                            // ..................................CONTINUE BUTTON HERE .........................................
                            JButton continueButton = new JButton("Continue");
                            continueButton.setAlignmentX(Component.CENTER_ALIGNMENT); // To align the button in the center of the box layout
                            continueButton.setBackground(new Color(0, 153, 0)); // Set the button color to green
                            continueButton.setForeground(Color.WHITE); // Set the text color to white
                            continueButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Check if any seats have been selected
                                    if (selectedSeats.isEmpty()) {
                                        JOptionPane.showMessageDialog(frame, "No seats selected.", "None Selected", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        // Code to execute when Continue button is clicked and at least one seat is selected
                                        System.out.println("Continue button clicked with selected seats: " + selectedSeats);
                                        // Implement your logic here
                                    }
                                }
                            });

                            // Add the Continue button just below the infoPanel
                            infoPanel.add(Box.createVerticalStrut(10)); // Add some space between the last label and the button
                            infoPanel.add(continueButton);
                            
                            // Add selectedSeatsLabel to the top panel
                            topPanel.add(selectedSeatsLabel, BorderLayout.SOUTH);
                            // Buttons for each seat with fixed size
                            for (int i = 1; i <= 36; i++) {
                                JButton seatButton = new JButton("Seat " + i);
                                seatButton.setPreferredSize(new Dimension(80, 40));


                                // Assign a default action command as "NOT_SELECTED"
                                seatButton.setActionCommand("NOT_SELECTED");

                                // Set color based on the class of the seat
                                Color originalColor;
                                if (i <= 12) { // First class
                                    originalColor = Color.YELLOW;
                                } else if (i <= 30) { // Economy class
                                    originalColor = Color.BLUE;
                                } else { // Business class
                                    originalColor = Color.ORANGE;
                                }

                                seatButton.setBackground(originalColor);

                                // Set action command to store the original color
                                seatButton.setActionCommand(originalColor.toString());
                                
                                seatButton.addActionListener(seatEvent -> {
                                    JButton clickedSeat = (JButton) seatEvent.getSource();
                                    Color currentColor = clickedSeat.getBackground();
                                    String seatText = clickedSeat.getText();

                                    // If the current color is not green, change it to green
                                    if (!currentColor.equals(Color.GREEN)) {
                                        clickedSeat.setBackground(Color.GREEN);
                                        selectedSeats.add(seatText);
                                    } else {
                                        // Parse the original color from the action command and change back to it
                                        clickedSeat.setBackground(originalColor);
                                        selectedSeats.remove(seatText);
                                    }

                                    String selectedSeatsText = "<html>Seats selected: " + String.join(", ", selectedSeats) + "</html>";
                                    selectedSeatsLabel.setText(selectedSeatsText);
                                });
                                seatsPanel.add(seatButton);
                                

                                // Add spacer after every third seat in a row
                                if (i % 3 == 0 && i % 6 != 0) {
                                    JPanel spacer = new JPanel();
                                    spacer.setOpaque(false);
                                    spacer.setPreferredSize(new Dimension(20, 40));
                                    seatsPanel.add(spacer);
                                }
                            }

                            seatsFrame.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    selectedSeats.clear(); // Clear the list of selected seats
                                    selectedSeatsLabel.setText("<html>Seats selected: </html>"); // Reset the label text
                                    // If you have any other clean-up code to run when the window closes, include it here
                                }
                            });

                           

                            // Add seatsPanel to mainPanel
                            seatsGbc.gridx = 0;
                            seatsGbc.gridy = 0;
                            mainPanel.add(seatsPanel, seatsGbc);

                            // Add the info panel to the main panel
                            seatsGbc.gridx = 1; // Set to the second column
                            seatsGbc.gridy = 0;
                            seatsGbc.anchor = GridBagConstraints.NORTH; // Align to the top
                            mainPanel.add(infoPanel, seatsGbc);

                            // Add mainPanel to the frame
                            seatsFrame.add(mainPanel, BorderLayout.CENTER);

                            // Add the selectedSeatsLabel to the bottom of the seatsFrame
                            seatsFrame.add(selectedSeatsLabel, BorderLayout.SOUTH);
                            //seatsFrame.add(selectedSeatsScrollPane, BorderLayout.SOUTH);

                            //seatsFrame.pack();
                            seatsFrame.setLocationRelativeTo(null);
                            seatsFrame.setVisible(true);
                        }
                    }
                }
            });


            //table.setAutoCreateRowSorter(true); // Allow sorting of columns
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
