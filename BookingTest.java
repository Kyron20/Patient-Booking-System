import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// vn82@kent.ac.uk
// makebookingbutton test

class BookingTest {
    private JFrame homeFrame;
    private JButton makeBookingButton;
    private Booking booking;

    @BeforeEach
    void setUp() {
        homeFrame = new JFrame();
        makeBookingButton = new JButton("Make Booking");
        booking = new Booking();
        homeFrame.add(makeBookingButton);
    }

    @Test
    void testValidBooking() {
        String date = "07/04/2023";
        String time = "14:30";

        // Mock user input
        mockUserInput(new String[]{date, time});

// Click the button to make a booking
        makeBookingButton.doClick();

        // Verify that the booking was made
        Assertions.assertEquals("Booking made for 2023-04-07T14:30 You and your doctor should now have a confirmation email about the booking.", booking.getBookings().get(0));
    }

    @Test
    void testInvalidDateBooking() {
        String date = "07-04-2023";
        String time = "14:30";

        // Mock user input
        mockUserInput(new String[]{date, time});

        // Click the button to make a booking
        makeBookingButton.doClick();

        // Verify that the booking was not made
        Assertions.assertTrue(booking.getBookings().isEmpty());
    }

    @Test
    void testInvalidTimeBooking() {
        String date = "07/04/2023";
        String time = "22:00";

        // Mock user input
        mockUserInput(new String[]{date, time});

        // Click the button to make a booking
        makeBookingButton.doClick();

 // Verify that the booking was not made
        Assertions.assertTrue(booking.getBookings().isEmpty());
    }

    private void mockUserInput(String[] inputs) {
        // Mock the user input by simulating a JOptionPane input dialog
        JOptionPane.showInputDialog(homeFrame, "Enter the date (dd/mm/yyyy) for your booking:");
        for (String input : inputs) {
            JOptionPane.setInputValue(input);
        }
        JOptionPane.showInputDialog(homeFrame, "Enter the time (hh:mm) for your booking:");
        for (String input : inputs) {
            JOptionPane.setInputValue(input);
        }
    }
}
