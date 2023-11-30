import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {

    private static JLabel selectedSeatsLabel;
    static ArrayList<String> selectedSeats = new ArrayList<>();
    public static RegisteredUser currentUser = null;
    private static JButton loginButton;
    private static JButton signOutButton;
    private static JButton signUpButton;
    private static JButton membershipButton;
    private static JLabel welcomeLabel;
    private static JButton myBookingsButton;

    public static void main(String[] args) {

        loginButton = new JButton("Login");
        signUpButton = new JButton("Signup");
        signOutButton = new JButton("Sign Out");
        signOutButton.setVisible(false);

        membershipButton = new JButton("Sign Up for Membership");
        membershipButton.setVisible(false);

        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        welcomeLabel.setOpaque(false);

        // Inside the main method, where you initialize other buttons
        myBookingsButton = new JButton("My Bookings");
        myBookingsButton.setVisible(false); // Initially, the button is not visible

        // Initialize Database
        FlightList availableFlights = FlightDataRetriever.loadAllData();

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

        // buttonPanel.add(welcomeLabel, BorderLayout.WEST);

        membershipButton.addActionListener(e -> {
            // Create a new frame for membership details
            JFrame membershipFrame = new JFrame("Membership Registration");
            membershipFrame.setLayout(null); // Setting layout to null for absolute positioning

            // Define a constant email
            String constantEmail = currentUser.getEmail(); // Replace with the actual email

            // Add a label for email
            JLabel emailLabel = new JLabel("Email: " + constantEmail);
            emailLabel.setBounds(10, 10, 280, 25); // Set bounds (x, y, width, height)
            membershipFrame.add(emailLabel);

            // Add a label and text field for credit card number
            JLabel cardLabel = new JLabel("Credit Card Number:");
            cardLabel.setBounds(10, 45, 280, 25);
            membershipFrame.add(cardLabel);

            JTextField cardField = new JTextField(20);
            cardField.setBounds(10, 70, 280, 25);
            membershipFrame.add(cardField);

            // Add a register button
            JButton registerButton = new JButton("Register");
            registerButton.setBounds(10, 105, 100, 25);
            membershipFrame.add(registerButton);

            // Set the size of the frame
            membershipFrame.setSize(300, 200);

            // Set frame behavior and make it visible
            membershipFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            membershipFrame.setVisible(true);

            // Add action listener to register button
            // Add action listener to register button
            registerButton.addActionListener(registerEvent -> {
                String creditCard = cardField.getText().trim();

                if (creditCard.isEmpty()) {
                    JOptionPane.showMessageDialog(membershipFrame, "Please enter credit card information.");
                } else if (!creditCard.matches("\\d{16}")) {
                    JOptionPane.showMessageDialog(membershipFrame, "Credit card number must be a 16-digit number.");
                } else {
                    // Using the constant email
                    String email = constantEmail;

                    // Logic to handle membership registration
                    long registrationResult = UserHandler.handleMembership(email, creditCard);

                    if (registrationResult != -1) { // Assuming -1 indicates failure
                        // Immediately update the currentUser object to reflect new membership status
                        currentUser.setIsMember(true); // Assuming there is a setIsMember method
                        System.out.println("isMember: " + currentUser.getIsMember());
                        // Immediately reflect the changes in the UI
                        signOutButton.setVisible(false); // Hide the sign out button
                        loginButton.setVisible(true); // Show the login button
                        membershipButton.setVisible(false); // Hide membership button as the user is now a member

                        // Prompt user to sign in again as a member
                        JOptionPane.showMessageDialog(membershipFrame, "Membership registered. Please sign in again.",
                                "Membership Registered", JOptionPane.INFORMATION_MESSAGE);

                        // Reset the current user to null to enforce re-login
                        currentUser = null;

                        membershipFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(membershipFrame, "Registration failed. Please try again.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        });

        // Create the login button
        JButton viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.addActionListener(e -> {
            // Clear all components from the frame
            frame.getContentPane().removeAll();

            // Create and add a top panel for the flights label and sign out button
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.add(welcomeLabel, BorderLayout.WEST);

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

            // ..............................LOGIN EVENT Listener..........................................................

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
                        JOptionPane.showMessageDialog(loginFrame, "Email or password cannot be empty.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        String userPasswordString = new String(loginPasswordField.getPassword());

                        currentUser = UserHandler.handleLogin(
                                loginEmailField.getText(),
                                userPasswordString);

                        if (currentUser == null) {
                            // Throw fail login popup --------------------
                            JOptionPane.showMessageDialog(loginFrame, "Login incorrect. Please try again.",
                                    "Login Error", JOptionPane.ERROR_MESSAGE);
                            return; // Add return to prevent closing the login frame
                        }
                        if (currentUser != null) {
                            loginButton.setVisible(false); // Hide the login button
                            signUpButton.setVisible(false);
                            signOutButton.setVisible(true); // Show the sign out button
                            welcomeLabel.setText("Welcome " + currentUser.getEmail());
                            myBookingsButton.setVisible(true);

                            // Check if the user is already a member
                            if (currentUser.getIsMember() == true) {
                                membershipButton.setVisible(false); // Hide membership button for members
                            } else {
                                membershipButton.setVisible(true); // Show for non-members
                            }

                            loginFrame.dispose();
                        } else {
                            // If login failed
                            JOptionPane.showMessageDialog(loginFrame, "Login incorrect. Please try again.",
                                    "Login Error", JOptionPane.ERROR_MESSAGE);
                        }
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
            

            // ..............................SIGN OUT EVENT Listener..........................................................
            signOutButton.addActionListener(signOutEvent -> {
                currentUser = null; // Reset the current user
                signOutButton.setVisible(false); // Hide the sign out button
                loginButton.setVisible(true); // Show the login button
                myBookingsButton.setVisible(false);
                signUpButton.setVisible(true);
                membershipButton.setVisible(false);
                membershipButton.setVisible(false);

                welcomeLabel.setText(""); // Clear the welcome label

            });

            authButtonsPanel.add(loginButton);
            authButtonsPanel.add(myBookingsButton);
            authButtonsPanel.add(signOutButton);
            authButtonsPanel.add(membershipButton);

            // ....................................SIGNUP EVENT Listener...................................................
            
            signUpButton.addActionListener(ev -> {
                // Create a new frame for sign up
                JFrame signUpFrame = new JFrame("Sign Up");
                signUpFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame
                signUpFrame.setSize(400, 300); // Initial size
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

                // First Name label and text field
                gbc.gridx = 0; // Reset to first column
                gbc.gridy++; // Move to next row
                centerPanel.add(new JLabel("First Name:"), gbc);
                gbc.gridx++; // Move to the next column
                JTextField firstNameField = new JTextField(20); // Width set to 10
                centerPanel.add(firstNameField, gbc);

                // Last Name label and text field
                gbc.gridx = 0;
                gbc.gridy++; // Move to the next row
                centerPanel.add(new JLabel("Last Name:"), gbc);
                gbc.gridx++; // Move to the next column
                JTextField lastNameField = new JTextField(20); // Width set to 10
                centerPanel.add(lastNameField, gbc);

                // Reset column index and increment row index for the address field
                gbc.gridx = 0; // Reset to first column
                gbc.gridy++; // Move to the next row for the address field

                // Address label and text field
                centerPanel.add(new JLabel("Address:"), gbc);
                gbc.gridx++; // Move to the next column
                gbc.gridwidth = GridBagConstraints.REMAINDER; // This will make the address field span the rest of the row
                JTextField addressField = new JTextField(20);
                centerPanel.add(addressField, gbc);

                // Reset gridwidth for subsequent components
                gbc.gridwidth = 1;

                // Submit button constraints
                gbc.gridx = 0; // Start at the first column
                gbc.gridy++; // Move to the next row after the text fields
                gbc.gridwidth = 2; // Span across two columns
                gbc.weightx = 0; // Do not let it grow horizontally

                gbc.fill = GridBagConstraints.NONE; // Do not resize the button
                gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally in its cell
                gbc.insets = new Insets(20, 0, 10, 0); // Top padding of 20, bottom padding of 10// Submit button
                                                       // constraints
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
                    if (emailField.getText().trim().isEmpty() || passwordField.getPassword().length == 0 || firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() || addressField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(signUpFrame, "you cannot have empty fields", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!emailField.getText().trim().endsWith("@gmail.com")) {
                        JOptionPane.showMessageDialog(signUpFrame, "Please sign up with a Gmail account.", "Email Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        
                        // If not empty, proceed with your submission logic
                        UserHandler.handleRegistration(
                                emailField.getText(),
                                passwordField.getPassword(),
                                firstNameField.getText(),
                                lastNameField.getText(),
                                addressField.getText());
                        JOptionPane.showMessageDialog(signUpFrame, "Account Created Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
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

            authButtonsPanel.add(signUpButton);
            topPanel.add(authButtonsPanel, BorderLayout.EAST);

            // Add the top panel to the frame
            frame.add(topPanel, BorderLayout.NORTH);

            // .................................... Retrieve flight
            // data......................................................
            ArrayList<Object[]> flightData = availableFlights.getAllFlightInfo();
            Object[][] data = new Object[flightData.size()][6]; // Adjusted for 6 columns

            // Copy the flight data to the data array and add a "Book" button to each row
            for (int i = 0; i < flightData.size(); i++) {
                System.arraycopy(flightData.get(i), 0, data[i], 0, 5);
                data[i][5] = "View Seats"; // Place a "Book" placeholder that will be replaced with a button
            }

            String[] columnNames = { "Flight ID", "Origin", "Destination", "Departure", "Arrival", "Select Seats" };

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
            JTable table = new JTable(model) {

                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    if (column == 5 && "View Seats".equals(getValueAt(row, column))) {
                        c.setForeground(Color.BLUE);
                        c.setFont(new Font("Serif", Font.BOLD, 12)); // Set the font for the "View Seats" text
                    } else {
                        c.setForeground(Color.BLACK); // Reset to the default text color for other cells
                    }
                    return c;
                }
            };

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
                                        colorLabel.setBackground(Color.PINK);
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

                            // ..................................CONTINUE BUTTON HERE.........................................
                            JButton continueButton = new JButton("Continue");
                            continueButton.setAlignmentX(Component.CENTER_ALIGNMENT); // To align the button in the center of the box layout
                            continueButton.setBackground(new Color(0, 153, 0)); // Set the button color to green
                            continueButton.setForeground(Color.WHITE); // Set the text color to white
                            continueButton.addActionListener(new ActionListener() {
                                Object[] flightInfo = flightData.get(row);

                                public void actionPerformed(ActionEvent e) {
                                    // Check if any seats have been selected
                                    if (selectedSeats.isEmpty()) {
                                        JOptionPane.showMessageDialog(frame, "No seats selected.", "None Selected",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        int totalCost = 0;
                                        for (String seatNumber : selectedSeats) {
                                            // Assuming seatNumber is a string that can be parsed as an integer index.
                                            int index = Integer.parseInt(seatNumber) - 1; // If seat numbers start from 1, adjust index to 0-based.
                                            int seatPrice = availableFlights.getFlightItinerary(row).getPlane()
                                                    .getListOfSeats().get(index).getPrice();
                                            totalCost += seatPrice;
                                        }
    
                                        String selectedFlightId = table.getValueAt(row, 0).toString(); // This gets the flight ID from the table.

                                        // Now you have the total cost, you can pass it to your createCheckoutFrame or use it as needed.
                                        createCheckoutFrame(flightInfo, totalCost, availableFlights, selectedFlightId, row);
                                        
                                    }
                                }
                            });

                            // Add the Continue button just below the infoPanel
                            infoPanel.add(Box.createVerticalStrut(10)); // Add some space between the last label and the// button
                            infoPanel.add(continueButton);

                            
                            // Fetch the seat data from your backend
                            ArrayList<Seat> seats = availableFlights.getFlightItinerary(row).getPlane().getListOfSeats();
                            int counter = 0;
                            
                            
                            // ...............................selectedSeatsLabel..................................................
                            topPanel.add(selectedSeatsLabel, BorderLayout.SOUTH);
                            // Buttons for each seat with fixed size
                            // Iterate through the list of Seat objects
                            for (Seat seat : seats) {
                                JButton seatButton = new JButton(seat.getSeatNumber());
                                seatButton.setPreferredSize(new Dimension(80, 40));

                                // Set color based on the seat type and booking status
                                Color colorToSet;
                                
                                if (seat.getIsBooked()) {
                                    colorToSet = Color.GRAY; // Indicate that the seat is already booked
                                } else {
                                    switch (seat.getSeatType()) {
                                        case "firstClass":
                                            colorToSet = Color.YELLOW;
                                            break;
                                        case "economy":
                                            colorToSet = Color.PINK;
                                            break;
                                        case "business":
                                            colorToSet = Color.ORANGE;
                                            break;
                                        default:
                                            colorToSet = Color.LIGHT_GRAY;
                                    }
                                }

                                seatButton.setBackground(colorToSet);
                                seatButton.putClientProperty("originalColor", colorToSet);

                                seatButton.addActionListener(seatEvent -> {
                                    JButton clickedSeat = (JButton) seatEvent.getSource();
                                    Color originalColor = (Color) clickedSeat.getClientProperty("originalColor");
                                    String seatText = clickedSeat.getText();

                                    // Toggle seat selection based on color
                                    if (!clickedSeat.getBackground().equals(Color.GREEN)
                                            && !clickedSeat.getBackground().equals(Color.GRAY)) {
                                        clickedSeat.setBackground(Color.GREEN);
                                        selectedSeats.add(seatText);
                                    } else if (clickedSeat.getBackground().equals(Color.GREEN)) {
                                        clickedSeat.setBackground(originalColor);
                                        selectedSeats.remove(seatText);
                                    }

                                    // Sort the selectedSeats list numerically
                                    selectedSeats.sort((s1, s2) -> {
                                        int num1 = Integer.parseInt(s1);
                                        int num2 = Integer.parseInt(s2);
                                        return Integer.compare(num1, num2);
                                    });

                                    String selectedSeatsText = "<html>Seats selected: "
                                            + String.join(", ", selectedSeats) + "</html>";
                                    selectedSeatsLabel.setText(selectedSeatsText);
                                });

                                seatsPanel.add(seatButton);
                                counter++;

                                // Add spacer after every third seat in a row
                                if (counter % 3 == 0 && counter % 6 != 0) {
                                    JPanel spacer = new JPanel();
                                    spacer.setOpaque(false);
                                    spacer.setPreferredSize(new Dimension(20, 40));
                                    seatsPanel.add(spacer);
                                }
                            }

                            seatsFrame.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    // Clear the list of selected seats
                                    selectedSeats.clear();

                                    // Reset the appearance of all seat buttons
                                    for (Component comp : seatsPanel.getComponents()) {
                                        if (comp instanceof JButton) {
                                            JButton button = (JButton) comp;
                                            button.setBackground((Color) button.getClientProperty("originalColor"));
                                        }
                                    }

                                    // Reset the label text
                                    selectedSeatsLabel.setText("<html>Seats selected: </html>");
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
                            // seatsFrame.add(selectedSeatsScrollPane, BorderLayout.SOUTH);

                            // seatsFrame.pack();
                            seatsFrame.setLocationRelativeTo(null);
                            seatsFrame.setVisible(true);

                        }
                    }
                }
            });

            // table.setAutoCreateRowSorter(true); // Allow sorting of columns
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
    private static void createCheckoutFrame(Object[] flightInfo, int totalCost, FlightList availableFlights, String selectedFlightId, int row) {
        JFrame checkoutFrame = new JFrame("Checkout");
        checkoutFrame.setLayout(new BorderLayout());
        checkoutFrame.setSize(600, 400);
        checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel checkoutPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel checkoutTitleLabel = new JLabel("Checkout Details", SwingConstants.CENTER);
        checkoutTitleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        checkoutPanel.add(checkoutTitleLabel, gbc);
        gbc.gridy++;

        // Displaying flight information
        JLabel departureLabel = new JLabel("Departure: " + flightInfo[1].toString());
        checkoutPanel.add(departureLabel, gbc);
        gbc.gridy++;

        JLabel arrivalLabel = new JLabel("Arrival: " + flightInfo[2].toString());
        checkoutPanel.add(arrivalLabel, gbc);
        gbc.gridy++;

        JLabel departureTimeLabel = new JLabel("Departure Time: " + flightInfo[3].toString());
        checkoutPanel.add(departureTimeLabel, gbc);
        gbc.gridy++;

        JLabel arrivalTimeLabel = new JLabel("Arrival Time: " + flightInfo[4].toString());
        checkoutPanel.add(arrivalTimeLabel, gbc);
        gbc.gridy++;

        // Divider line
        JSeparator separator = new JSeparator();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        checkoutPanel.add(separator, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Reset to default
        gbc.gridwidth = 1; // Reset to default

        // Selected seats information
        JLabel selectedSeatsLabel = new JLabel("Selected Seats: " + String.join(", ", selectedSeats));
        checkoutPanel.add(selectedSeatsLabel, gbc);
        gbc.gridy++;

        // Example price calculation
        JLabel totalPriceLabel = new JLabel("Total Price: $" + totalCost);
        checkoutPanel.add(totalPriceLabel, gbc);
        gbc.gridy++;

        // Payment information fields (simplified for example)
        checkoutPanel.add(new JLabel("Card Number:"), gbc);
        gbc.gridx++;
        JTextField cardNumberField = new JTextField(20);
        checkoutPanel.add(cardNumberField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JCheckBox insuranceCheckBox = new JCheckBox("Cancellation Insurance");
        insuranceCheckBox.addItemListener(e -> {
            // Check if the checkbox is checked
            boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
            int updatedTotalCost = selected ? totalCost + 100 : totalCost;
            
            // Update the total price label
            totalPriceLabel.setText("Total Price: $" + updatedTotalCost);
        });
        gbc.gridwidth = 2;
        checkoutPanel.add(insuranceCheckBox, gbc);
        gbc.gridy++;

        // Checkout button
        JButton checkoutButton = new JButton("Complete Purchase");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (currentUser == null) {
                    JOptionPane.showMessageDialog(checkoutFrame, "Log in or Sign up to Book ticket", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Early return to prevent further execution
                }
                // Retrieve the total price from the selectedSeats and flightInfo
                int totalPrice = totalCost;
                
                boolean cancellationInsurance = insuranceCheckBox.isSelected();
                if (cancellationInsurance) {
                    totalPrice += 100;
                }


                // Get the credit card number from the currentUser object
                String creditCardNumber = currentUser.getCreditCardNumber();
                
                // Get the email from the currentUser object
                String userEmail = currentUser.getEmail();
                
                // Get the flight ID from the FlightItinerary object
                //String flightId = String.valueOf(availableFlights.getFlightItinerary(selectedFlightId).getId());

                System.out.println("Selected Flight ID: " + selectedFlightId);

                // Get the seat IDs from the selectedSeats array
                ArrayList<String> seatIds = new ArrayList<>(selectedSeats);

                // Call the complete purchase method
                if (currentUser.getIsMember()){

                    Booker.handleBooking(totalPrice, creditCardNumber, userEmail, selectedFlightId, seatIds, cancellationInsurance, availableFlights.getFlightItinerary(row).getPlane());
                }
                else {
                    Booker.handleBooking(totalPrice, cardNumberField.getText(), userEmail, selectedFlightId, seatIds, cancellationInsurance, availableFlights.getFlightItinerary(row).getPlane());

                }
            }
        });
        
        checkoutPanel.add(checkoutButton, gbc);
        checkoutFrame.add(checkoutPanel, BorderLayout.CENTER);
        checkoutFrame.pack();
        checkoutFrame.setLocationRelativeTo(null);
        checkoutFrame.setVisible(true);
    }
    
}