import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// vn82@kent.ac.uk
// create jpanel to define selecteddoctorlabel

public class MainPanelTest {
    @Test
    void testMakeBookingButtonActionPerformed() {
        // given
        MainPanel mainPanel = new MainPanel("test_username");
        JButton makeBookingButton = mainPanel.getMakeBookingButton();
        ActionListener mockActionListener = mock(ActionListener.class);
        makeBookingButton.addActionListener(mockActionListener);

        // when
        makeBookingButton.doClick();

        // then
        verify(mockActionListener).actionPerformed(new ActionEvent(makeBookingButton, ActionEvent.ACTION_PERFORMED, ""));
    }

    @Test
    void testChangeDoctorButtonActionPerformed() {
        // given
        MainPanel mainPanel = new MainPanel("test_username");
        JButton changeDoctorButton = mainPanel.getChangeDoctorButton();
        JLabel selectedDoctorLabel = mainPanel.getSelectedDoctorLabel();
        ActionListener mockActionListener = mock(ActionListener.class);
        changeDoctorButton.addActionListener(mockActionListener);
 // when
        changeDoctorButton.doClick();

        // then
        verify(mockActionListener).actionPerformed(new ActionEvent(changeDoctorButton, ActionEvent.ACTION_PERFORMED, ""));
        Assertions.assertTrue(selectedDoctorLabel.getText().isEmpty());
    }

    @Test
    void testViewDetailsButtonActionPerformed() {
        // given
        MainPanel mainPanel = new MainPanel("test_username");
        JButton viewDetailsButton = mainPanel.getViewDetailsButton();
        ActionListener mockActionListener = mock(ActionListener.class);
        viewDetailsButton.addActionListener(mockActionListener);

        // when
        viewDetailsButton.doClick();

        // then
        verify(mockActionListener).actionPerformed(new ActionEvent(viewDetailsButton, ActionEvent.ACTION_PERFORMED, ""));
    }

    @Test
    void testViewBookingButtonActionPerformed() {
        // given
        MainPanel mainPanel = new MainPanel("test_username");
        JButton viewBookingButton = mainPanel.getViewBookingButton();
        ActionListener mockActionListener = mock(ActionListener.class);
        viewBookingButton.addActionListener(mockActionListener);

        // when
        viewBookingButton.doClick();

        // then
        verify(mockActionListener).actionPerformed(new ActionEvent(viewBookingButton, ActionEvent.ACTION_PERFORMED, ""));
    }
}
