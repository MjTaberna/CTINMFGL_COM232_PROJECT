import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrativeTaskApp {
   private JFrame frame;
   private JPanel panel;
   private JButton facultyLoginButton;
   private JButton adminLoginButton;
   private JLabel welcomeLabel;

   public AdministrativeTaskApp() {
       // Create frame
       frame = new JFrame("Administrative Task Management System");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 200);

       // Create panel
       panel = new JPanel();
       frame.add(panel);
       placeComponents(panel);

       // Set frame visibility
       frame.setVisible(true);
   }

   private void placeComponents(JPanel panel) {
       panel.setLayout(null);

       // Welcome label
       welcomeLabel = new JLabel("Welcome to the Administrative Task Management System");
       welcomeLabel.setBounds(50, 20, 300, 25);
       panel.add(welcomeLabel);

       // Faculty login button
       facultyLoginButton = new JButton("Faculty Login");
       facultyLoginButton.setBounds(50, 70, 130, 25);
       panel.add(facultyLoginButton);
       facultyLoginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showFacultyLogin();
           }
       });

       // Admin login button
       adminLoginButton = new JButton("Admin Login");
       adminLoginButton.setBounds(200, 70, 130, 25);
       panel.add(adminLoginButton);
       adminLoginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showAdminLogin();
           }
       });
   }

   private void showFacultyLogin() {
       JFrame loginFrame = new JFrame("Faculty Login");
       loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       loginFrame.setSize(300, 200);

       JPanel loginPanel = new JPanel();
       loginFrame.add(loginPanel);
       placeFacultyLoginComponents(loginPanel);

       loginFrame.setVisible(true);
   }

   private void placeFacultyLoginComponents(JPanel panel) {
       panel.setLayout(null);

       JLabel userLabel = new JLabel("User");
       userLabel.setBounds(10, 20, 80, 25);
       panel.add(userLabel);

       JTextField userText = new JTextField(20);
       userText.setBounds(100, 20, 165, 25);
       panel.add(userText);

       JLabel passwordLabel = new JLabel("Password");
       passwordLabel.setBounds(10, 50, 80, 25);
       panel.add(passwordLabel);

       JPasswordField passwordText = new JPasswordField(20);
       passwordText.setBounds(100, 50, 165, 25);
       panel.add(passwordText);

       JButton loginButton = new JButton("login");
       loginButton.setBounds(10, 80, 80, 25);
       panel.add(loginButton);
       loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showFacultyDashboard();
           }
       });
   }

   private void showAdminLogin() {
       JFrame loginFrame = new JFrame("Admin Login");
       loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       loginFrame.setSize(300, 200);

       JPanel loginPanel = new JPanel();
       loginFrame.add(loginPanel);
       placeAdminLoginComponents(loginPanel);

       loginFrame.setVisible(true);
   }

   private void placeAdminLoginComponents(JPanel panel) {
       panel.setLayout(null);

       JLabel userLabel = new JLabel("User");
       userLabel.setBounds(10, 20, 80, 25);
       panel.add(userLabel);

       JTextField userText = new JTextField(20);
       userText.setBounds(100, 20, 165, 25);
       panel.add(userText);

       JLabel passwordLabel = new JLabel("Password");
       passwordLabel.setBounds(10, 50, 80, 25);
       panel.add(passwordLabel);

       JPasswordField passwordText = new JPasswordField(20);
       passwordText.setBounds(100, 50, 165, 25);
       panel.add(passwordText);

       JButton loginButton = new JButton("login");
       loginButton.setBounds(10, 80, 80, 25);
       panel.add(loginButton);
       loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showAdminDashboard();
           }
       });
   }

   private void showFacultyDashboard() {
       JFrame dashboardFrame = new JFrame("Faculty Dashboard");
       dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       dashboardFrame.setSize(300, 200);

       JPanel dashboardPanel = new JPanel();
       dashboardFrame.add(dashboardPanel);
       placeFacultyDashboardComponents(dashboardPanel);

       dashboardFrame.setVisible(true);
   }

   private void placeFacultyDashboardComponents(JPanel panel) {
       panel.setLayout(null);

       JLabel dashboardLabel = new JLabel("Faculty Dashboard");
       dashboardLabel.setBounds(10, 20, 150, 25);
       panel.add(dashboardLabel);

       JButton applyLeaveButton = new JButton("Apply for Leave");
       applyLeaveButton.setBounds(10, 50, 150, 25);
       panel.add(applyLeaveButton);
       applyLeaveButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               applyForLeave();
           }
       });

       JButton viewTasksButton = new JButton("View Tasks");
       viewTasksButton.setBounds(10, 80, 150, 25);
       panel.add(viewTasksButton);
       viewTasksButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               viewTasks();
           }
       });
   }

   private void showAdminDashboard() {
       JFrame dashboardFrame = new JFrame("Admin Dashboard");
       dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       dashboardFrame.setSize(300, 200);

       JPanel dashboardPanel = new JPanel();
       dashboardFrame.add(dashboardPanel);
       placeAdminDashboardComponents(dashboardPanel);

       dashboardFrame.setVisible(true);
   }

   private void placeAdminDashboardComponents(JPanel panel) {
       panel.setLayout(null);

       JLabel dashboardLabel = new JLabel("Admin Dashboard");
       dashboardLabel.setBounds(10, 20, 150, 25);
       panel.add(dashboardLabel);

       JButton approveLeaveButton = new JButton("Approve Leave");
       approveLeaveButton.setBounds(10, 50, 150, 25);
       panel.add(approveLeaveButton);
       approveLeaveButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               approveLeave();
           }
       });

       JButton generateReportButton = new JButton("Generate Report");
       generateReportButton.setBounds(10, 80, 150, 25);
       panel.add(generateReportButton);
       generateReportButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               generateReport();
           }
       });
   }

   private void applyForLeave() {
       JOptionPane.showMessageDialog(frame, "Leave request submitted.");
   }

   private void viewTasks() {
       JOptionPane.showMessageDialog(frame, "Viewing tasks.");
   }

   private void approveLeave() {
       JOptionPane.showMessageDialog(frame, "Leave request approved.");
   }

   private void generateReport() {
       JOptionPane.showMessageDialog(frame, "Generating report.");
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               new AdministrativeTaskApp();
           }
       });
   }
}
