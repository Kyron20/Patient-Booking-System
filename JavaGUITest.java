import org.junit.Test;
import org.junit.Assert;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

// vn82@kent.ac.uk
// tests view bookings

public class JavaGUITest {

    @Test
    public void testViewBookings() {
        JavaGUI gui = new JavaGUI();
        ArrayList<String> bookings = new ArrayList<>();
        bookings.add("Booking 1");
        bookings.add("Booking 2");
        bookings.add("Booking 3");
        gui.bookings = bookings;
        gui.viewBookings();
        JFrame viewbookingPage = gui.viewbookingPage;
        JPanel bookingPanel = (JPanel) viewbookingPage.getContentPane().getComponent(0);
        Assert.assertEquals(bookingPanel.getComponentCount(), bookings.size());
        JButton backButton = (JButton) viewbookingPage.getContentPane().getComponent(1);
        backButton.doClick();
        Assert.assertFalse(viewbookingPage.isShowing());
    }

@Test
    public void testMainPanel() {
        JavaGUI gui = new JavaGUI();
        JPanel mainPanel = gui.mainPanel;
        Assert.assertNotNull(mainPanel);
        Assert.assertEquals(mainPanel.getLayout(), new GridLayout(2, 2));
    }

    @Test
    public void testHomePanel() {
        JavaGUI gui = new JavaGUI();
        JPanel homePanel = gui.homePanel;
        Assert.assertNotNull(homePanel);
        Assert.assertEquals(homePanel.getLayout(), new BorderLayout());
    }

    @Test
    public void testAddMainPanelToHomePanel() {
        JavaGUI gui = new JavaGUI();
        JPanel mainPanel = gui.mainPanel;
        JPanel homePanel = gui.homePanel;
        gui.addMainPanelToHomePanel();
        Assert.assertEquals(mainPanel, homePanel.getComponent(0));
    }

    @Test
    public void testMain() {
        JavaGUI.main(new String[] {});
    }

}
