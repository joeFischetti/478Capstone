//This is the popup window used for editing students
//

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EditStudentPopup extends JFrame{

	private static final long serialVersionUID = 1L;

	//Create the jpanels for different regions
	//
	private JPanel mainPanel, leftPanel, rightPanel;
	
	//Create the submit button
	//
	private JButton btnSubmit;
	
	//Create the jtext fields for the various pieces of information that will be displayed
	//
	private JTextField txtFirstName, txtLastName, txtAddress, txtCity, txtState, txtZip, txtDOB;
	
	//Create a student object which will be returned after the user has made their
	// changes
	private Student student;
	
	public EditStudentPopup(Student existingStudent){
		
		//Set the local student variable equal to the values that were passed to it
		//
		student = existingStudent;
		
		//Init the jpanels
		//
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		//Init the text fields, using the values from the student variable
		//
		txtFirstName = new JTextField(student.getFirst());
		txtLastName = new JTextField(student.getLast());
		txtAddress = new JTextField(student.getAddress());
		txtCity = new JTextField(student.getCity());
		txtState = new JTextField(student.getState());
		txtZip = new JTextField(student.getZip() + "");
		txtDOB = new JTextField(student.getDOB());
		
		//init the button and make it say something
		//
		btnSubmit = new JButton("Submit Changes to student");
		
		//Set the panel layouts and add things to them
		//
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("First Name:"));
		leftPanel.add(new JLabel("Last Name:"));
		leftPanel.add(new JLabel("Address:"));
		leftPanel.add(new JLabel("City:"));
		leftPanel.add(new JLabel("State:"));
		leftPanel.add(new JLabel("Zip:"));
		leftPanel.add(new JLabel("Date of Birth:"));
		
		//More
		//
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtFirstName);
		rightPanel.add(txtLastName);
		rightPanel.add(txtAddress);
		rightPanel.add(txtCity);
		rightPanel.add(txtState);
		rightPanel.add(txtZip);
		rightPanel.add(txtDOB);
		
		//More
		//
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		//Init the frame, and make it visible
		//
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Edit Existing Student");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	//Make the submit button do things (passed from the main program
	//
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	//Create and return a new student from the provided information with a matching id_num
	//	from the student provided
	//
	public Student newStudent(){
		return new Student(	student.getID(), 
				   	txtFirstName.getText(), 
				   	txtLastName.getText(), 
				   	txtAddress.getText(), 
				   	txtCity.getText(),
					txtState.getText(), 
				   	txtZip.getText(), 
				   	txtDOB.getText());
	}
}
