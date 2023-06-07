import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

// vn82@kent.ac.uk
// change dob till choose doctor

public class TestShowViewDetails {

    @Test
    public void testShowViewDetails() {

        // create a new instance of the class that contains the showViewDetails() method
        MyClass myClass = new MyClass();

        // invoke the method
        myClass.showViewDetails();

        // get the JFrame that was created by the method
        JFrame detailsPage = (JFrame) SwingUtilities.getWindowAncestor((Component) myClass.detailsList.get(0));

// get the JLabels and JButtons that were added to the detailsList
        JLabel nameLabel = (JLabel) myClass.detailsList.get(0);
        JLabel dobLabel = (JLabel) myClass.detailsList.get(1);
        JLabel medicalConditionsLabel = (JLabel) myClass.detailsList.get(2);
        JLabel selectedDoctorLabel = (JLabel) myClass.detailsList.get(3);
        JLabel emailLabel = (JLabel) myClass.detailsList.get(4);
        JButton changeNameButton = (JButton) myClass.detailsList.get(5);
        JButton changeDOBButton = (JButton) myClass.detailsList.get(6);
        JButton changeMedicalConditionsButton = (JButton) myClass.detailsList.get(7);
        JButton changeEmailButton = (JButton) myClass.detailsList.get(8);
        JButton chooseDoctorButton = (JButton) myClass.detailsList.get(9);

        // test the JFrame properties
        assertEquals("Details", detailsPage.getTitle());
        assertEquals(1280, detailsPage.getWidth());
        assertEquals(720, detailsPage.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, detailsPage.getDefaultCloseOperation());

        // test the JLabel texts
        assertEquals("Full name: Please set your full name", nameLabel.getText());
        assertEquals("Date of Birth: Please set your date of birth", dobLabel.getText());
        assertEquals("Medical Conditions: Please set your medical conditions", medicalConditionsLabel.getText());
        assertEquals("Selected Doctor: Please choose a doctor", selectedDoctorLabel.getText());
        assertEquals("Email Address: Please set your email address", emailLabel.getText());
// simulate a button click to change the name and test the JLabel text
        changeNameButton.doClick();
        String newName = "John Doe";
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().putClientProperty("value", newName);
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().setVisible(false);
        assertEquals("Full name: " + newName, nameLabel.getText());

        // simulate a button click to change the date of birth and test the JLabel text
        changeDOBButton.doClick();
        String newDOB = "01/01/2000";
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().putClientProperty("value", newDOB);
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().setVisible(false);
        assertEquals("Date of Birth: " + newDOB, dobLabel.getText());

        // simulate a button click to change the medical conditions and test the JLabel text
        changeMedicalConditionsButton.doClick();
        String newMedicalConditions = "None";
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().putClientProperty("value", newMedicalConditions);
        JOptionPane.getFrameForComponent(detailsPage).getGlassPane().setVisible(false);
        assertEquals("Medical Conditions: " + newMedicalConditions, medicalConditionsLabel.getText());

        // simulate a button click to change the email and test the JLabel text
        changeEmailButton.doClick();
        String newEmail
