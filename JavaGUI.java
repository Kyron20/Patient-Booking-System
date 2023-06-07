import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JButton;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// 22/03/2023 - Register as a new patient functionaility - pgm21@kent.ac.uk
// 22/03/2023 - The new updated JavaGUI featuring registration, Login and home page forwarding complete - kr397@kent.ac.uk
// 22/03/2023 1pm - Working on home page and the layout with what's going to be featured on it - kr397@kent.ac.uk
// 23/03/2023 - View bookings functionality - re255@kent.ac.uk
// 23/03/2023 3:40pm - What is completed:  Make Bookings, view bookings, view details, choose doctor (it's in view details tab) - kr397@kent.ac.uk
// 06/04/22 5pm - Made buttons public so the test cases could access them - pgm21@kent.ac.uk
// 07/04/22 - Reschedule booking functionality - pgm21@kent.ac.uk
// 07/04/22 - View past booking functionality - pgm21@kent.ac.uk
public class JavaGUI extends JFrame {
    public JTextField userField;
    public JPasswordField passField;
    public JButton createButton;
    public JButton loginButton;
    public JButton viewButton;
    public HashMap<String, String> users;
    ArrayList<String> bookings = new ArrayList<String>(); //for view bookings. store the bookings in an array makes it easier.
    JLabel selectedDoctorLabel = new JLabel("Selected Doctor: Dr. John Smith");
    public JavaGUI() {
        // Create JFrame
        super("Patient Create/Login System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize user HashMap
        users = new HashMap<String, String>();

        // Create JPanel
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Add username field
        JLabel userLabel = new JLabel("Patient Name:");
        panel.add(userLabel);
        userField = new JTextField(20);
        panel.add(userField);

        // Add password field
        JLabel passLabel = new JLabel("Password:");
        panel.add(passLabel);
        passField = new JPasswordField(20);
        panel.add(passField);

        // Add create account button
        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userField.getText().trim();
                    String password = new String(passField.getPassword()).trim();

                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Username and password cannot be empty. Please try again.");
                    } else {
                        users.put(username, password);
                        JOptionPane.showMessageDialog(null, "Account created for user " + username);
                    }
                }
        });
        panel.add(createButton);

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userField.getText();
                    String password = new String(passField.getPassword());
                    if (users.containsKey(username) && users.get(username).equals(password)) {
                        JOptionPane.showMessageDialog(null, "User " + username + " logged in successfully");
                        showHomePage(username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed");
                    }
                }
        });

        JButton viewButton = new JButton("View Details");
        viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showViewDetails();
                }
        });

        panel.add(loginButton);
        // Add panel to JFrame
        add(panel);

        // Show JFrame
        setVisible(true);

    }

    private void showViewDetails() {
        final String[] name = {"Please set your full name"};
        final String[] dob = {"Please set your date of birth"};
        final String[] medicalConditions = {"Please set your medical conditions"};
        final String[] selectedDoctor = {"Please choose a doctor"};
        final String[] email = {"Please set your email address"};

        JFrame detailsPage = new JFrame("Details");
        detailsPage.setSize(1280, 720);
        detailsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel for name, DOB, medical conditions and selected doctor
        JLabel nameLabel = new JLabel("Full name: " + name[0]);
        JLabel dobLabel = new JLabel("Date of Birth: " + dob[0]);
        JLabel medicalConditionsLabel = new JLabel("Medical Conditions: " + medicalConditions[0]);
        JLabel emailLabel = new JLabel("Email Address: " + email[0]);

        // List to hold buttons and labels
        ArrayList<Component> detailsList = new ArrayList<>();
        detailsList.add(nameLabel);
        detailsList.add(dobLabel);
        detailsList.add(medicalConditionsLabel);
        detailsList.add(selectedDoctorLabel);
        detailsList.add(emailLabel);

        // JButton to change the username
        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName = JOptionPane.showInputDialog(detailsPage, "Enter your new name:");
                    if (newName != null && !newName.isEmpty()) {
                        name[0] = newName;
                        nameLabel.setText("Full name: " + name[0]);
                    }
                }
        });
        detailsList.add(changeNameButton);

        // JButton to change the user's date of birth
        JButton changeDOBButton = new JButton("Change Date of Birth");
        changeDOBButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newDOB = JOptionPane.showInputDialog(detailsPage, "Enter your new date of birth:");
                    if (newDOB != null && !newDOB.isEmpty()) {
                        dob[0] = newDOB;
                        dobLabel.setText("Date of Birth: " + dob[0]);
                    }
                }
        });
        detailsList.add(changeDOBButton);

        // JButton to change the user's medical conditions
        JButton changeMedicalConditionsButton = new JButton("Change Medical Conditions");
        changeMedicalConditionsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newMedicalConditions = JOptionPane.showInputDialog(detailsPage, "Enter your new medical conditions:");
                    if (newMedicalConditions != null && !newMedicalConditions.isEmpty()) {
                        medicalConditions[0] = newMedicalConditions;
                        medicalConditionsLabel.setText("Medical Conditions: " + medicalConditions[0]);
                    }
                }
        });
        detailsList.add(changeMedicalConditionsButton);

        // JButton to change the user's email address
        JButton changeEmailButton = new JButton("Change Email");
        changeEmailButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newEmail = JOptionPane.showInputDialog(detailsPage, "Enter your new email address:");
                    if (newEmail != null && !newEmail.isEmpty()) {
                        // Assuming email format validation is not required
                        email[0] = newEmail;
                        emailLabel.setText("Email: " + email[0]);
                    }
                }
        });
        detailsList.add(changeEmailButton);

        //Jbutton that shows the change in the doctor from Choose Doctor.

        // JButton to choose a doctor
        JButton chooseDoctorButton = new JButton("Choose Doctor");
        chooseDoctorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showChooseDoctor(name[0], selectedDoctorLabel); // Pass the selectedDoctorLabel as an argument
                }
        });
        detailsList.add(chooseDoctorButton);

        // JButton to close the page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailsPage.dispose();
                }
        });
        detailsList.add(backButton);

        // Add detailsList to frame using BorderLayout
        JPanel detailsPanel = new JPanel(new GridLayout(detailsList.size(), 1));
        for (Component component : detailsList) {
            detailsPanel.add(component);
        }
        detailsPage.add(detailsPanel, BorderLayout.CENTER);
        detailsPage.setVisible(true);

    }

    private void showChooseDoctor(String username, JLabel selectedDoctorLabel) {
        JFrame doctorPage = new JFrame("Choose Doctor");
        doctorPage.setSize(1280, 720);
        doctorPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Create JPanel for doctor selection page
        JPanel doctorPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Create array of doctors
        String[] doctors = {"Dr. John Smith", "Dr. Sam Reeves", "Dr. David Hanes"};
    
        // Add each doctor to the panel with a "Choose Doctor" button
        for (String doctor : doctors) {
            JLabel doctorLabel = new JLabel(doctor);
            doctorPanel.add(doctorLabel);
            JButton chooseButton = new JButton("Choose Doctor");
            doctorPanel.add(chooseButton);
            final String chosenDoctor = doctor; // Create final variable for chosen doctor
            chooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Set the selected doctor label to the chosen doctor's name
                    selectedDoctorLabel.setText("Selected Doctor: " + chosenDoctor);
                    doctorPanel.repaint();
                    doctorPanel.revalidate();
                    // Close the doctor selection window
                    doctorPage.dispose();
                }
            });
        }
    
        // Add doctor selection panel to JFrame
        doctorPage.add(doctorPanel);
    
        // Show JFrame
        doctorPage.setVisible(true);
        }
    private void showHomePage(String username) {
        // Create JFrame for home page
        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setSize(1280, 720);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel for home page
        JPanel homePanel = new JPanel(new BorderLayout());

        // Create JPanel for header
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Add welcome message
        JLabel welcomeLabel = new JLabel("Welcome " + username + ", you are logged in");
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        // Add logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    homeFrame.dispose();
                }
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);

        // Add header panel to home panel
        homePanel.add(headerPanel, BorderLayout.NORTH);

        // Create JPanel 
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));

        // Add "View Activity"
        JButton viewActivityButton = new JButton("View Activity");
        mainPanel.add(viewActivityButton);

        // Add "View Details" 
        JButton viewDetailsButton = new JButton("Change and View Details");
        mainPanel.add(viewDetailsButton);

        // Add "Change Doctor" 
        JButton changeDoctorButton = new JButton("Choose Doctor");
        

        //Add "View Booking"
        JButton viewBookingButton = new JButton("View Booking");
        mainPanel.add(viewBookingButton);

        // Add "Make Booking"
        JButton makeBookingButton = new JButton("Make Booking");
        mainPanel.add(makeBookingButton);

        // Add "Reschedule Booking"
        JButton rescheduleBookingButton = new JButton("Reschedule Booking");
        mainPanel.add(rescheduleBookingButton);
        rescheduleBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rescheduleBooking();
            }
        });

        // Add "View Past Booking"
        JButton pastBookingButton = new JButton("View Past Booking");
        mainPanel.add(pastBookingButton);
        pastBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPastBookings();
            }
        });


        // Define selectedDoctorLabel
        JLabel selectedDoctorLabel = new JLabel("");

        changeDoctorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showChooseDoctor(username, selectedDoctorLabel);
                }
        });
        viewDetailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showViewDetails();
                }
        });

        viewBookingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewBookings();
                }
        });

        makeBookingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Prompt the user to select a date and time for the booking
                    LocalDateTime dateTime = LocalDateTime.of(2023, 4, 1, 9, 0); // This is just a placeholder value
                    String date = JOptionPane.showInputDialog(homeFrame, "Enter the date (dd/mm/yyyy) for your booking:");
                    if (date == null) { // User clicked cancel
                        return;
                    }
                    String time = JOptionPane.showInputDialog(homeFrame, "Enter the time (hh:mm) for your booking:");
                    if (time == null) { // User clicked cancel
                        return;
                    }
                    try {
                        dateTime = LocalDateTime.of(
                            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                        );
                        LocalTime startTime = LocalTime.of(9, 0);
                        LocalTime endTime = LocalTime.of(21, 0);
                        if (dateTime.toLocalTime().isBefore(startTime) || dateTime.toLocalTime().isAfter(endTime)) {
                            JOptionPane.showMessageDialog(homeFrame, "Invalid time. The bookings go from 9am to 9pm. Please select a time within this range.");
                            return;
                        }
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(homeFrame, "Invalid date or time format. Please enter in the format dd/mm/yyyy and hh:mm.");
                        return;
                    }
                    JOptionPane.showMessageDialog(homeFrame, "Booking made for " +  dateTime + " You and your doctor should now have a confirmation email about the booking.");

                    bookings.add("Booking made for " + dateTime + " You and your doctor should now have a confirmation email about the booking.");
                }
        });

        // Add main panel to home panel
        homePanel.add(mainPanel, BorderLayout.CENTER);

        // Add home panel to home frame
        homeFrame.add(homePanel);

        // Show home frame
        homeFrame.setVisible(true);
    }



    private void rescheduleBooking(){
        JFrame viewbookingPage = new JFrame ("View Bookings");
        viewbookingPage.setSize(1280, 720);
        viewbookingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the bookings
        JPanel bookingPanel = new JPanel(new GridLayout(bookings.size(), 1));

        // Add each booking to the JPanel
        for (String booking : bookings) {
            JButton rescheduleButton = new JButton(booking);
            bookingPanel.add(rescheduleButton, BorderLayout.CENTER);
            rescheduleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Prompt the user to select a date and time for the booking
                    LocalDateTime dateTime = LocalDateTime.of(2023, 4, 1, 9, 0); // This is just a placeholder value
                    String date = JOptionPane.showInputDialog(viewbookingPage, "Enter the date (dd/mm/yyyy) for your booking:");
                    if (date == null) { // User clicked cancel
                        return;
                    }
                    String time = JOptionPane.showInputDialog(viewbookingPage, "Enter the time (hh:mm) for your booking:");
                    if (time == null) { // User clicked cancel
                        return;
                    }
                    try {
                        dateTime = LocalDateTime.of(
                                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                        );
                        LocalTime startTime = LocalTime.of(9, 0);
                        LocalTime endTime = LocalTime.of(21, 0);
                        if (dateTime.toLocalTime().isBefore(startTime) || dateTime.toLocalTime().isAfter(endTime)) {
                            JOptionPane.showMessageDialog(viewbookingPage, "Invalid time. The bookings go from 9am to 9pm. Please select a time within this range.");
                            return;
                        }
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(viewbookingPage, "Invalid date or time format. Please enter in the format dd/mm/yyyy and hh:mm.");
                        return;
                    }
                    JOptionPane.showMessageDialog(viewbookingPage, "Booking made for " +  dateTime + " You and your doctor should now have a confirmation email about the booking.");

                    bookings.add("Booking made for " + dateTime + " You and your doctor should now have a confirmation email about the booking.");
                }
            });
        }

        // Add the bookingPanel to the viewbookingPage
        viewbookingPage.add(bookingPanel, BorderLayout.CENTER);

        // Add a back button to the viewbookingPage
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewbookingPage.dispose();

                }
        });
        viewbookingPage.add(backButton, BorderLayout.SOUTH);

        viewbookingPage.setVisible(true);
    }

    private void viewBookings(){
        JFrame viewbookingPage = new JFrame ("View Bookings");
        viewbookingPage.setSize(1280, 720);
        viewbookingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the bookings
        JPanel bookingPanel = new JPanel(new GridLayout(bookings.size(), 1));

        // Add each booking to the JPanel
        for (String booking : bookings) {
            JLabel bookingLabel = new JLabel(booking);
            bookingPanel.add(bookingLabel);
        }

        // Add the bookingPanel to the viewbookingPage
        viewbookingPage.add(bookingPanel, BorderLayout.CENTER);

        // Add a back button to the viewbookingPage
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewbookingPage.dispose();

            }
        });
        viewbookingPage.add(backButton, BorderLayout.SOUTH);

        viewbookingPage.setVisible(true);
    }

    private void viewPastBookings(){
        JFrame pastbookingPage = new JFrame ("View Past Bookings");
        pastbookingPage.setSize(1280, 720);
        pastbookingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the bookings
        JPanel pastbookingPanel = new JPanel(new GridLayout(bookings.size(), 1));

        JLabel pastbooking = new JLabel ("06/04/2022 - Dr John Smith - Patient was diagnosed with asthma and prescribed inhalers");
        pastbookingPanel.add(pastbooking);

        // Add the bookingPanel to the viewbookingPage
        pastbookingPage.add(pastbookingPanel, BorderLayout.CENTER);

        // Add a back button to the viewbookingPage
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pastbookingPage.dispose();

            }
        });
        pastbookingPage.add(backButton, BorderLayout.SOUTH);

        pastbookingPage.setVisible(true);
    }

    // Starts the User Interface through main args.
    public static void main(String[] args) {
        JavaGUI gui = new JavaGUI();

    }
}

