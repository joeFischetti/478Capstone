//This is the Edit course popup frame
//
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
	
	//Create the panels
	//
	private JPanel mainPanel, leftPanel, rightPanel;
	
	//Create the button
	//
	private JButton btnSubmit;
	
	//Create the entry fields
	//
	private JTextField txtCourseNum, txtCourseName, txtDescription, txtMaxStudents, txtDay;
	
	//Create a course variable that will be used for returning the course that will be modified
	//
	Course course;
	
	//The constructor.  Only takes the course as an argument
	//
	public EditCoursePopup(Course inputCourse){
		
		//Set the local course variable to be equal to the one that was passed to the frame
		//
		course = inputCourse;
		
		//Initialize the panels
		//
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		//Initialize the text fields witht the values from the course variable
		//
		txtCourseNum = new JTextField(course.getNUM());
		txtCourseNum.setEnabled(false);
		txtCourseName = new JTextField(course.getName());
		txtDescription = new JTextField(course.getDesc());
		txtMaxStudents = new JTextField(course.getMax() + "");
		txtDay = new JTextField(course.getDay());
		
		//Initialize the submit button
		//
		btnSubmit = new JButton("Submit Course Changes");
		
		//Populate the JPanels
		//Left first
		//
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("Course Number:"));
		leftPanel.add(new JLabel("Course Name:"));
		leftPanel.add(new JLabel("Course Description:"));
		leftPanel.add(new JLabel("Max Students:"));
		leftPanel.add(new JLabel("Meeting Days:"));
		
		//Then the right
		//
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtCourseNum);
		rightPanel.add(txtCourseName);
		rightPanel.add(txtDescription);
		rightPanel.add(txtMaxStudents);
		rightPanel.add(txtDay);
		
		//Then the main panel which holds the left and right panels
		//
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		//Set up the frame, and make it visible
		//
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Modify Course Info");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	//Public setter method for the submit button.  The action will be passed from the main 
	//  program
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	//Create a new course from the provided information
	//
	public Course newCourse(){
		return new Course(	course.getNUM(), 
				  	txtCourseName.getText(), 
				  	txtDescription.getText(), 
				  	txtMaxStudents.getText(),
					txtDay.getText());
	}
}
