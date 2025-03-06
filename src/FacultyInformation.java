import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacultyInformation extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField departmentField;
    private JTextField emailField;
    private JTextArea displayArea;

    public FacultyInformation() {
        setTitle("Faculty Information Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        inputPanel.add(departmentField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String department = departmentField.getText();
                String email = emailField.getText();

                FacultyInformation faculty = new FacultyInformation(name, age, department, email);

                displayArea.append("Name: " + faculty.getName() + "\n");
                displayArea.append("Age: " + faculty.getAge() + "\n");
                displayArea.append("Department: " + faculty.getDepartment() + "\n");
                displayArea.append("Email: " + faculty.getEmail() + "\n\n");

                // Clear input fields
                nameField.setText("");
                ageField.setText("");
                departmentField.setText("");
                emailField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(FacultyInformation.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(FacultyInformation.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FacultyInformation gui = new FacultyInformation();
            gui.setVisible(true);
        });
    }
}
