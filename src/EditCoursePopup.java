import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EditCoursePopup extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton btnSubmit;
	
	private JTextField txtCourseNum, txtCourseName, txtDescription, txtMaxStudents, txtDay;
	
	Course course;
	
	public EditCoursePopup(Course inputCourse){
		
		course = inputCourse;
		
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
				
		txtCourseNum = new JTextField(course.getNUM());
		txtCourseNum.setEnabled(false);
		txtCourseName = new JTextField(course.getName());
		txtDescription = new JTextField(course.getDesc());
		txtMaxStudents = new JTextField(course.getMax() + "");
		txtDay = new JTextField(course.getDay());
		
		
		btnSubmit = new JButton("Submit Course Changes");
		
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
		this.setTitle("Modify Course Info");
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
		return new Course(course.getNUM(), txtCourseName.getText(), txtDescription.getText(), txtMaxStudents.getText(),
				txtDay.getText());
	}
}
