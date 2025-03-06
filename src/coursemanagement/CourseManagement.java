package coursemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseManagement extends JFrame {
    private Faculty faculty1;
    private Faculty faculty2;
    private Course course1;
    private Course course2;
    private Student student1;
    private Student student2;

    public CourseManagement() {
        createGUI();
    }

    private void createGUI() {
        setTitle("Course Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());
        setSize(800, 600); 

        
        faculty1 = new Faculty(1, "Dr. Cynjhin", "PhD in Computer Science", "cynjhin@university.edu");
        faculty2 = new Faculty(2, "Dr. Drake", "PhD in Mathematics", "drake21@university.edu");
        course1 = new Course(101, "Data Structures", "Introduction to Data Structures");
        course2 = new Course(102, "Programming", "Object-Oriented Programming");
        student1 = new Student(201, "Megumi Acorda");
        student2 = new Student(202, "Blaster Silonga");

        // Create panels for faculty, courses, and students
        JPanel facultyPanel = createFacultyPanel();
        JPanel coursePanel = createCoursePanel();
        JPanel studentPanel = createStudentPanel();

        // Add panels to frame
        add(facultyPanel, BorderLayout.NORTH);
        add(coursePanel, BorderLayout.CENTER);
        add(studentPanel, BorderLayout.SOUTH);

        // Create buttons for assigning faculty to courses and enrolling students
        JPanel buttonPanel = new JPanel();
        JButton assignFacultyButton = new JButton("Assign Faculty to Course");
        assignFacultyButton.addActionListener(new AssignFacultyListener());
        buttonPanel.add(assignFacultyButton);

        JButton enrollStudentButton = new JButton("Enroll Student in Course");
        enrollStudentButton.addActionListener(new EnrollStudentListener());
        buttonPanel.add(enrollStudentButton);

        add(buttonPanel, BorderLayout.EAST);
        setVisible(true);
    }

    private JPanel createFacultyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel faculty1Label = new JLabel("Faculty 1:");
        panel.add(faculty1Label);
        JTextField faculty1Field = new JTextField(faculty1.getName());
        faculty1Field.setEditable(false);
        panel.add(faculty1Field);

        JLabel faculty2Label = new JLabel("Faculty 2:");
        panel.add(faculty2Label);
        JTextField faculty2Field = new JTextField(faculty2.getName());
        faculty2Field.setEditable(false);
        panel.add(faculty2Field);

        return panel;
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel course1Label = new JLabel("Course 1:");
        panel.add(course1Label);
        JTextField course1Field = new JTextField(course1.getTitle());
        course1Field.setEditable(false);
        panel.add(course1Field);

        JLabel course2Label = new JLabel("Course 2:");
        panel.add(course2Label);
        JTextField course2Field = new JTextField(course2.getTitle());
        course2Field.setEditable(false);
        panel.add(course2Field);

        return panel;
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel student1Label = new JLabel("Student 1:");
        panel.add(student1Label);
        JTextField student1Field = new JTextField(student1.getName());
        student1Field.setEditable(false);
        panel.add(student1Field);

        JLabel student2Label = new JLabel("Student 2:");
        panel.add(student2Label);
        JTextField student2Field = new JTextField(student2.getName());
        student2Field.setEditable(false);
        panel.add(student2Field);

        return panel;
    }

    private class AssignFacultyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Assign faculty to courses
            faculty1.assignCourse(course1);
            faculty2.assignCourse(course2);
            JOptionPane.showMessageDialog(CourseManagement.this, "Faculty assigned to courses successfully.");
        }
    }

    private class EnrollStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Enroll students in courses
            student1.enrollCourse(course1);
            student2.enrollCourse(course1);
            student2.enrollCourse(course2);
            JOptionPane.showMessageDialog(CourseManagement.this, "Students enrolled in courses successfully.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseManagement());
    }
}
