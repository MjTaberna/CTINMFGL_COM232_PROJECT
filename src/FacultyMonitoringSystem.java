import javax.swing.*;

import coursemanagement.CourseManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacultyMonitoringSystem {

    
    public static JButton prof1;
    public static JButton prof2;
    public static JButton prof3;
    public static JPanel newPanel = new JPanel();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Faculty Monitoring System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel panel = new JPanel(null);
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(800, 600); 
        frame.setVisible(true);
        	
        // Buttons for the main panel
        JButton button1 = createImageButton("Faculty Information Management", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\FacultyInformationManagement.jpg", 70, 150, panel);
        JButton button2 = createImageButton("Course Management", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\CourseManagement.jpg", 300, 150, panel);
        JButton button3 = createImageButton("Teaching Assignments", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\TeachingAssignment.jpg", 500, 150, panel);
        JButton button4 = createImageButton("Performance Evaluation", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\PerformanceEvaluation.jpg", 150, 350, panel);
        JButton button5 = createImageButton("Administrative Tasks", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\AdministrativeTasks.jpg", 350, 350, panel);
        JButton button6 = createImageButton("Additional", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\f.jpg", 550, 350, panel);

        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                SwingUtilities.invokeLater(() -> {
                    FacultyInformation gui = new FacultyInformation();
                    gui.setVisible(true);
                });
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Course Management GUI
                SwingUtilities.invokeLater(() -> {
                    new CourseManagement();
                });
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                
                TeachingAssignment teachingAssignment = new TeachingAssignment();
                
                teachingAssignment.mainTeachingAssignment();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                SwingUtilities.invokeLater(() -> {
                    Teaching gui = new Teaching();
                    gui.setVisible(true);
                });
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                SwingUtilities.invokeLater(() -> {
                    new AdministrativeTaskApp();
                });
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    // Method for button creation
    private static JButton createImageButton(String text, String imagePath, int x, int y, JPanel panel) {
        JButton button = new JButton();
        button.setText(text);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);

        Image image = new ImageIcon(imagePath).getImage();
        Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(resizedImage));

        button.setBorder(null); 
        button.setContentAreaFilled(false); 

        
        FontMetrics metrics = button.getFontMetrics(button.getFont());
        int textWidth = metrics.stringWidth(text);
        int buttonWidth = Math.max(textWidth, 100); // ensure the button is at least as wide as the image
        button.setPreferredSize(new Dimension(buttonWidth, 140)); // set preferred size to accommodate text
        button.setBounds(x, y, buttonWidth, 140);
        panel.add(button);
        return button;
    }
}
