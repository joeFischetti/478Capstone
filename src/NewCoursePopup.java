import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class NewCoursePopup extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton btnSubmit;
	
	private JTextField txtCourseNum, txtCourseName, txtDescription, txtMaxStudents, txtDay;
	
	public NewCoursePopup(){
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
				
		txtCourseNum = new JTextField("ABC123");
		txtCourseName = new JTextField("Alpha Bravo Charlie");
		txtDescription = new JTextField("Generic course description");
		txtMaxStudents = new JTextField("30");
		txtDay = new JTextField("Monday and Wednesday");
		
		
		btnSubmit = new JButton("Add new course");
		
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("Course Number:"));
		leftPanel.add(new JLabel("Course Name:"));
		leftPanel.add(new JLabel("Course Description:"));
		leftPanel.add(new JLabel("Max Students:"));
		leftPanel.add(new JLabel("Meeting Days:"));
		
		
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtCourseNum);
		rightPanel.add(txtCourseName);
		rightPanel.add(txtDescription);
		rightPanel.add(txtMaxStudents);
		rightPanel.add(txtDay);
		
		
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Add New Course");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	
	//Create a new course from the provided information
	//
	public Course newCourse(){
		return new Course(txtCourseNum.getText(), txtCourseName.getText(), txtDescription.getText(), txtMaxStudents.getText(),
				txtDay.getText());
	}
}
