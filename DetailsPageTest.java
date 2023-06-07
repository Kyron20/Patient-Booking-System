import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

// vn82@kent.ac.uk
// show view details till change the username button

public class DetailsPageTest {

    @Test
    public void testShowViewDetails() {
        // Create a new instance of the DetailsPage class
        DetailsPage detailsPage = new DetailsPage();

        // Call the showViewDetails() method
        detailsPage.showViewDetails();

        // Get the JFrame component
        JFrame frame = detailsPage.getFrame();

        // Verify that the JFrame component is not null
        assertNotNull(frame);

        // Verify the JFrame size
        assertEquals(1280, frame.getWidth());
        assertEquals(720, frame.getHeight());
        // Get the list of components in the JFrame
        Component[] components = frame.getContentPane().getComponents();

        // Verify that there are 5 JLabel components in the JFrame
        int expectedLabelCount = 5;
        int actualLabelCount = 0;
        for (Component component : components) {
            if (component instanceof JLabel) {
                actualLabelCount++;
            }
        }
        assertEquals(expectedLabelCount, actualLabelCount);

        // Verify that the JLabel components have the correct text
        String expectedNameLabelText = "Full name: Please set your full name";
        String expectedDobLabelText = "Date of Birth: Please set your date of birth";
        String expectedMedicalConditionsLabelText = "Medical Conditions: Please set your medical conditions";
        String expectedEmailLabelText = "Email Address: Please set your email address";

        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String labelText = label.getText();
                if (labelText.startsWith("Full name")) {
                    assertEquals(expectedNameLabelText, labelText);
                } else if (labelText.startsWith("Date of Birth")) {
                    assertEquals(expectedDobLabelText, labelText);
                } else if (labelText.startsWith("Medical Conditions")) {
                    assertEquals(expectedMedicalConditionsLabelText, labelText);
                } else if (labelText.startsWith("Email Address")) {
                    assertEquals(expectedEmailLabelText, labelText);
                }
            }
        }
        // Verify that there is 1 JButton component in the JFrame
        int expectedButtonCount = 1;
        int actualButtonCount = 0;
        for (Component component : components) {
            if (component instanceof JButton) {
                actualButtonCount++;
            }
        }
        assertEquals(expectedButtonCount, actualButtonCount);

        // Click the Change Name button and verify the text change
        String newName = "John Doe";
        JButton changeNameButton = (JButton) components[components.length - 1]; // The last component is the JButton
        changeNameButton.doClick();
        assertEquals("Full name: " + newName, expectedNameLabelText);
    }
}
