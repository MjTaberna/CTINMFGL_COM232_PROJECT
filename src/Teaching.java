import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private String contactInfo;

    public Person(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return name;
    }
}

class FacultyMember extends Person {
    private String department;
    private String employmentHistory;

    public FacultyMember(String name, String contactInfo, String department, String employmentHistory) {
        super(name, contactInfo);
        this.department = department;
        this.employmentHistory = employmentHistory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmploymentHistory() {
        return employmentHistory;
    }

    public void setEmploymentHistory(String employmentHistory) {
        this.employmentHistory = employmentHistory;
    }
}

abstract class PerformanceEvaluation {
    protected FacultyMember facultyMember;
    protected String evaluationDate;
    protected String feedback;

    public PerformanceEvaluation(FacultyMember facultyMember, String evaluationDate) {
        this.facultyMember = facultyMember;
        this.evaluationDate = evaluationDate;
    }

    public abstract void evaluate();

    public FacultyMember getFacultyMember() {
        return facultyMember;
    }

    public String getEvaluationDate() {
        return evaluationDate;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return facultyMember.getName() + " (" + evaluationDate + ")";
    }
}

class TeachingEvaluation extends PerformanceEvaluation {
    private int teachingEffectivenessScore;

    public TeachingEvaluation(FacultyMember facultyMember, String evaluationDate, int teachingEffectivenessScore) {
        super(facultyMember, evaluationDate);
        this.teachingEffectivenessScore = teachingEffectivenessScore;
    }

    @Override
    public void evaluate() {
        feedback = "Teaching Evaluation completed with score: " + teachingEffectivenessScore;
    }

    public int getTeachingEffectivenessScore() {
        return teachingEffectivenessScore;
    }

    public void setTeachingEffectivenessScore(int teachingEffectivenessScore) {
        this.teachingEffectivenessScore = teachingEffectivenessScore;
    }
}

class ResearchEvaluation extends PerformanceEvaluation {
    private int researchProductivityScore;

    public ResearchEvaluation(FacultyMember facultyMember, String evaluationDate, int researchProductivityScore) {
        super(facultyMember, evaluationDate);
        this.researchProductivityScore = researchProductivityScore;
    }

    @Override
    public void evaluate() {
        feedback = "Research Evaluation completed with score: " + researchProductivityScore;
    }

    public int getResearchProductivityScore() {
        return researchProductivityScore;
    }

    public void setResearchProductivityScore(int researchProductivityScore) {
        this.researchProductivityScore = researchProductivityScore;
    }
}

class ServiceEvaluation extends PerformanceEvaluation {
    private int serviceContributionScore;

    public ServiceEvaluation(FacultyMember facultyMember, String evaluationDate, int serviceContributionScore) {
        super(facultyMember, evaluationDate);
        this.serviceContributionScore = serviceContributionScore;
    }

    @Override
    public void evaluate() {
        feedback = "Service Evaluation completed with score: " + serviceContributionScore;
    }

    public int getServiceContributionScore() {
        return serviceContributionScore;
    }

    public void setServiceContributionScore(int serviceContributionScore) {
        this.serviceContributionScore = serviceContributionScore;
    }
}

// Main GUI class
public class Teaching extends JFrame {
    private FacultyMember[] facultyMembers;
    private JComboBox<FacultyMember> facultyComboBox;
    private JTextField teachingScoreField;
    private JTextField researchScoreField;
    private JTextField serviceScoreField;
    private JTextArea feedbackArea;
    private JTextArea resultArea;
    private JComboBox<PerformanceEvaluation> previousEvaluationsComboBox;
    private List<PerformanceEvaluation> evaluations;

    public Teaching() {

        facultyMembers = new FacultyMember[]{
            new FacultyMember("Dr. Smith", "smith@example.com", "Computer Science", "10 years"),
            new FacultyMember("Dr. Johnson", "johnson@example.com", "Mathematics", "8 years"),
            new FacultyMember("Dr. Williams", "williams@example.com", "Physics", "12 years")
        };

        evaluations = new ArrayList<>();

        setTitle("Faculty Monitoring System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Select Faculty Member:"));
        facultyComboBox = new JComboBox<>(facultyMembers);
        inputPanel.add(facultyComboBox);

        inputPanel.add(new JLabel("Teaching Effectiveness Score:"));
        teachingScoreField = new JTextField();
        inputPanel.add(teachingScoreField);

        inputPanel.add(new JLabel("Research Productivity Score:"));
        researchScoreField = new JTextField();
        inputPanel.add(researchScoreField);

        inputPanel.add(new JLabel("Service Contribution Score:"));
        serviceScoreField = new JTextField();
        inputPanel.add(serviceScoreField);

        inputPanel.add(new JLabel("Feedback:"));
        feedbackArea = new JTextArea();
        inputPanel.add(new JScrollPane(feedbackArea));

        inputPanel.add(new JLabel("Select Previous Evaluation:"));
        previousEvaluationsComboBox = new JComboBox<>();
        inputPanel.add(previousEvaluationsComboBox);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(new EvaluateButtonListener());
        buttonPanel.add(evaluateButton);

        JButton loadButton = new JButton("Load Evaluation");
        loadButton.addActionListener(new LoadButtonListener());
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.EAST);
    }

    private class EvaluateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FacultyMember selectedFaculty = (FacultyMember) facultyComboBox.getSelectedItem();
                int teachingScore = Integer.parseInt(teachingScoreField.getText());
                int researchScore = Integer.parseInt(researchScoreField.getText());
                int serviceScore = Integer.parseInt(serviceScoreField.getText());
                String feedback = feedbackArea.getText();

                TeachingEvaluation teachingEval = new TeachingEvaluation(selectedFaculty, "2024-06-19", teachingScore);
                teachingEval.evaluate();

                ResearchEvaluation researchEval = new ResearchEvaluation(selectedFaculty, "2024-06-19", researchScore);
                researchEval.evaluate();

                ServiceEvaluation serviceEval = new ServiceEvaluation(selectedFaculty, "2024-06-19", serviceScore);
                serviceEval.evaluate();

                evaluations.add(teachingEval);
                evaluations.add(researchEval);
                evaluations.add(serviceEval);

                previousEvaluationsComboBox.addItem(teachingEval);
                previousEvaluationsComboBox.addItem(researchEval);
                previousEvaluationsComboBox.addItem(serviceEval);

                resultArea.setText("Evaluation Results for " + selectedFaculty.getName() + ":\n");
                resultArea.append(teachingEval.getFeedback() + "\n");
                resultArea.append(researchEval.getFeedback() + "\n");
                resultArea.append(serviceEval.getFeedback() + "\n");
                resultArea.append("Feedback and Recommendations:\n" + feedback);

                JOptionPane.showMessageDialog(Teaching.this, "Evaluation has been successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                teachingScoreField.setText("");
                researchScoreField.setText("");
                serviceScoreField.setText("");
                feedbackArea.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Teaching.this, "Please enter valid scores.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PerformanceEvaluation selectedEvaluation = (PerformanceEvaluation) previousEvaluationsComboBox.getSelectedItem();
            if (selectedEvaluation != null) {
                resultArea.setText("Loaded Evaluation for " + selectedEvaluation.getFacultyMember().getName() + ":\n");
                resultArea.append("Date: " + selectedEvaluation.getEvaluationDate() + "\n");
                resultArea.append(selectedEvaluation.getFeedback() + "\n");
            } else {
                JOptionPane.showMessageDialog(Teaching.this, "No evaluation selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Teaching gui = new Teaching();
            gui.setVisible(true);
        });
    }
}
