import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

// vn82@kent.ac.uk
// jbutton to close the page tillcreate jframe for homepage

public class MyAppTest {

  @Test
  public void testShowChooseDoctor() {
    // Create a mock JLabel for the selected doctor label
    JLabel selectedDoctorLabel = mock(JLabel.class);

    // Create an instance of the application
    MyApp app = new MyApp();

    // Call the showChooseDoctor() method
    app.showChooseDoctor("username", selectedDoctorLabel);

    // Get the doctor panel from the JFrame
    JFrame doctorPage = (JFrame) TestUtils.getChildNamed(app.getFrames()[0], "Choose Doctor");
    JPanel doctorPanel = (JPanel) TestUtils.getChildNamed(doctorPage.getContentPane(), null);
// Verify that the doctor panel has the correct layout
    assertEquals(0, doctorPanel.getLayout().getHgap());
    assertEquals(2, doctorPanel.getLayout().getColumns());
    assertEquals(10, doctorPanel.getLayout().getVgap());
    assertEquals(3, doctorPanel.getComponentCount());

    // Verify that each doctor label and button is displayed correctly
    for (int i = 0; i < doctorPanel.getComponentCount(); i += 2) {
      JLabel doctorLabel = (JLabel) doctorPanel.getComponent(i);
      assertEquals("Dr. John Smith", doctorLabel.getText());

      JButton chooseButton = (JButton) doctorPanel.getComponent(i + 1);
      assertEquals("Choose Doctor", chooseButton.getText());

      // Click the choose button
      chooseButton.doClick();

      // Verify that the selected doctor label was updated correctly
      ArgumentCaptor<String> selectedDoctorCaptor = ArgumentCaptor.forClass(String.class);
      verify(selectedDoctorLabel).setText(selectedDoctorCaptor.capture());
      assertEquals("Selected Doctor: Dr. John Smith", selectedDoctorCaptor.getValue());

      // Verify that the doctor selection window was closed
      assertEquals(0, app.getFrames().length);
    }
  }
// Utility class for finding a child component of a container by name
  static class TestUtils {
    public static Component getChildNamed(Component parent, String name) {
      if (name == null || parent.getName() == null) {
        if (name == parent.getName()) {
          return parent;
        }
      } else if (name.equals(parent.getName())) {
        return parent;
      }

      if (parent instanceof Container) {
        Component[] children = ((Container) parent).getComponents();
        for (int i = 0; i < children.length; ++i) {
          Component child = getChildNamed(children[i], name);
          if (child != null) {
            return child;
          }
        }
      }

      return null;
    }
  }

}
