import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
 
public class TeachingAssignment extends FacultyMonitoringSystem {
 
    public static JPanel inputPanel = new JPanel();
    public static JPanel newPanel = new JPanel();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    public static JButton addButton;
    public static JButton removeButton;
    public static JLabel prof1ListLabel;
    public static JLabel prof2ListLabel;
    public static JLabel prof3ListLabel;
    
            public static void mainTeachingAssignment(){
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
 
               JFrame newFrame = new JFrame("New Teaching Assignment");
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.setSize(800, 600);
 
                    
                    JPanel newPanel = new JPanel(null);
                    newPanel.setBounds(200, 100, 600, 500); // Adjust the size and position
                    newPanel.setLayout(null);
                    newFrame.add(newPanel, BorderLayout.CENTER);
                    newFrame.setVisible(true);
                    
                    
                prof1 = createImageButton("Mr. Gonzalo", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\prof1.jpg", 50, 100, newPanel);
                prof2 = createImageButton("Mrs. Alice", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\prof2.jpg", 50, 250, newPanel);
                prof3 = createImageButton("Mrs. Nicka", "C:\\Users\\Rinoah Venedict\\Downloads\\oop\\prof3.jpg", 50, 400, newPanel);
                newPanel.revalidate();
                newPanel.repaint();
                placeComponents(newPanel);
            prof1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                inputPanel.add(prof1ListLabel);
                inputPanel.remove(prof2ListLabel);
                inputPanel.remove(prof3ListLabel);
                listModel.clear();
                String textToAdd1 = "Science";
                String textToAdd2= "Science";
                int timeStart1 = 8;
                int timeEnd1 = 12;
                int timeStart2=14;
                int timeEnd2=16;
                String input1= textToAdd1 +" "+timeStart1+" - "+timeEnd1;
                String input2= textToAdd2 +" "+timeStart2+" - "+timeEnd2;
                listModel.addElement(input1);
                listModel.addElement(input2);
                inputPanel.revalidate();
                inputPanel.repaint();
            }
        });
            prof2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                inputPanel.add(prof2ListLabel);
                inputPanel.remove(prof1ListLabel);
                inputPanel.remove(prof3ListLabel);
                listModel.clear();
                String textToAdd1 = "English";
                String textToAdd2= "Filipino";
                int timeStart1 = 7;
                int timeEnd1 = 9;
                int timeStart2=11;
                int timeEnd2=13;
                String input1= textToAdd1 +" "+timeStart1+" - "+timeEnd1;
                String input2= textToAdd2 +" "+timeStart2+" - "+timeEnd2;
                listModel.addElement(input1);
                listModel.addElement(input2);
                
                inputPanel.revalidate();
                inputPanel.repaint();
            }
        });
            prof3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                inputPanel.add(prof3ListLabel);
                inputPanel.remove(prof1ListLabel);
                inputPanel.remove(prof2ListLabel);
                listModel.clear();
                String textToAdd1 = "Math";
                String textToAdd2= "Math";
                int timeStart1 = 9;
                int timeEnd1 = 11;
                int timeStart2=16;
                int timeEnd2=20;
                String input1= textToAdd1 +" "+timeStart1+" - "+timeEnd1;
                String input2= textToAdd2 +" "+timeStart2+" - "+timeEnd2;
                listModel.addElement(input1);
                listModel.addElement(input2);
                
                inputPanel.revalidate();
                inputPanel.repaint();
            }
        });
    }
        });
 
    }
    private static JButton createImageButton(String text, String imagePath, int x, int y, JPanel newPanel) {
        JButton button = new JButton();
        button.setText(text);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
 
        Image image = new ImageIcon(imagePath).getImage();
        Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(resizedImage));
 
        button.setBorder(null); // remove border
        button.setContentAreaFilled(false); // remove background
        button.setPreferredSize(new Dimension(100, 120)); // set preferred size to accommodate text
        button.setBounds(x, y, 100, 120);
        newPanel.add(button);
        return button;
    }
 
    public static void placeComponents(JPanel newPanel) {
 
        
        inputPanel.setBounds(200, 100, 600, 500); 
        inputPanel.setLayout(null);
 
        JLabel addClass = new JLabel("-- ADD / REMOVE CLASS --");
        FontMetrics metrics = addClass.getFontMetrics(addClass.getFont());//this is to fully show text
        int textWidth = metrics.stringWidth("-- ADD / REMOVE CLASS --");
        int buttonWidth = Math.max(textWidth, 300);
        addClass.setPreferredSize(new Dimension(buttonWidth, 300)); 
        addClass.setBounds(150, 150, buttonWidth, 140);
        inputPanel.add(addClass);
        
        
        JLabel title = new JLabel();
        title.setText(" -- TEACHING ASSIGNMENT -- ");
        int textWidth3 = metrics.stringWidth(" -- TEACHING ASSIGNMENT -- ");
        int buttonWidth3 = Math.max(textWidth3, 300);
        title.setBounds(150, 1, buttonWidth3, 25);
        title.setPreferredSize(new Dimension(buttonWidth3, 300)); 
        inputPanel.add(title);
 
        prof1ListLabel = new JLabel();
        prof1ListLabel.setText("Mr. Gonzalo's Class Schedules :");
        int textWidth4 = metrics.stringWidth("Mr. Gonzalo's Class Schedules :");
        int buttonWidth4 = Math.max(textWidth4, 300);
        prof1ListLabel.setBounds(150, 50, buttonWidth4, 25);
        prof1ListLabel.setPreferredSize(new Dimension(buttonWidth4, 300)); 
       
        prof2ListLabel = new JLabel();
        prof2ListLabel.setText("Mrs.Alice's Class Schedules :");
        int textWidth5 = metrics.stringWidth("Mrs.Alice's Class Schedules :");
 
        int buttonWidth5 = Math.max(textWidth5, 300);
 
        prof2ListLabel.setBounds(150, 50, buttonWidth5, 25);
 
        prof2ListLabel.setPreferredSize(new Dimension(buttonWidth5, 300)); 
 
        prof3ListLabel = new JLabel();
 
        prof3ListLabel.setText("Mrs. Nicka's Class Schedules :");
        int textWidth6 = metrics.stringWidth("Mrs. Nicka's Class Schedules :");
        int buttonWidth6 = Math.max(textWidth6, 300);
 
        prof3ListLabel.setBounds(150, 50, buttonWidth6, 25);
 
        prof3ListLabel.setPreferredSize(new Dimension(buttonWidth6, 300)); 
 
        // Add subject label and text field
 
        JLabel subject = new JLabel("Subject :");
 
        subject.setBounds(10, 280, 80, 25); 
 
        inputPanel.add(subject);
 
        JTextField subjectTextField = new JTextField(15);
 
        subjectTextField.setBounds(65, 280, 160, 25);
 
        inputPanel.add(subjectTextField);
 
        JLabel startTime = new JLabel("Start Time :");
 
        startTime.setBounds(230, 280, 80, 25); 
 
        inputPanel.add(startTime);
 
        JTextField startTextField = new JTextField(5);
 
        startTextField.setBounds(300, 280, 160, 25); 
 
        inputPanel.add(startTextField);
 
        JLabel endTime = new JLabel("Start Time :");
 
        endTime.setBounds(230, 320, 80, 25); // Set bounds for the label
 
        inputPanel.add(endTime);
 
        JTextField endTextField = new JTextField(5);
 
        endTextField.setBounds(300, 320, 160, 25); // Set bounds for the text field
 
        inputPanel.add(endTextField);
 
        // Create and add the JList
 
        JList<String> todoList = new JList<>(listModel);
 
        todoList.setBounds(10, 70, 400, 120); // Set bounds for the JList
 
        JScrollPane listScrollPane = new JScrollPane(todoList);
 
        listScrollPane.setBounds(10, 70, 400, 120); // Set bounds for the scroll pane
 
        inputPanel.add(listScrollPane);
 
        JButton addButton = new JButton("Add");
 
        addButton.setBounds(280,380,80,25);
 
	inputPanel.add(addButton);
 
        JButton removeButton = new JButton("Remove");
 
        removeButton.setBounds(380,380,80,25);
 
	inputPanel.add(removeButton);
 
        JLabel errorLabel = new JLabel();
 
        int textWidth2 = metrics.stringWidth("INVALID TRY AGAIN");
 
        int buttonWidth2 = Math.max(textWidth2, 300);
 
        errorLabel.setBounds(150, 380, buttonWidth2, 25);
 
        errorLabel.setPreferredSize(new Dimension(buttonWidth2, 300)); // set preferred size to accommodate text
 
        inputPanel.add(errorLabel);
 
        // Add inputPanel to the main panel
 
        newPanel.add(inputPanel);
 
        addButton.addActionListener(new ActionListener() {
 
	        public void actionPerformed(ActionEvent e) {
 
	            String subject = subjectTextField.getText();
 
	            int startTime = Integer.parseInt(startTextField.getText());
 
                    int endTime = Integer.parseInt(endTextField.getText());
 
                        int timeStart1 = 8;
 
                        int timeEnd1 = 12;
 
                        int timeStart2=14;
 
                        int timeEnd2=16;
 
	            if (!subject.isEmpty()&& startTime!=timeStart1 && startTime!=timeStart2 &&startTime!=timeEnd1&& startTime!=timeEnd2&& endTime!=timeEnd1 && endTime!=timeEnd2 && endTime!=timeStart2 && endTime!=timeStart1) {
 
	            	String input = subject + " " + startTime+" - "+endTime;
 
	                listModel.addElement(input);
 
                        subjectTextField.setText("");
 
	                startTextField.setText("");
 
	                endTextField.setText("");
 
	            } else{
 
                        errorLabel.setText("INVALID TRY AGAIN");
 
                        subjectTextField.setText("");
 
	                startTextField.setText("");
 
	                endTextField.setText("");
 
                    }
 
	        }
 
	    });
 
        removeButton.addActionListener(new ActionListener() {
 
	        public void actionPerformed(ActionEvent e) {
 
	            int selectedIndex = todoList.getSelectedIndex();
 
	            if (selectedIndex!= -1) {
 
	                listModel.remove(selectedIndex);
 
	            }
 
	        }
 
	    });
 
}
 
}