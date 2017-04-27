//This is the frame used for editing assignment details
//

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class EditAssignmentPopup extends JFrame{

	private static final long serialVersionUID = 1L;

	//These are the panels required for the frame.
	//
	private JPanel mainPanel, leftPanel, rightPanel;
	
	//This is the button used for submitting the changes
	//
	private JButton btnSubmit;
	
	//These are the jtextfields used for editing the information
	//
	private JTextField txtAssignmentName, txtTotalPoints, txtDescription, course, assignmentType;
	
	//An assignment object, returned for database modification
	//
	Assignment assignment;
		
	//This is the constructor for the frame
	//
	public EditAssignmentPopup(	DefaultListModel<Course> courseList, 
					DefaultListModel<AssignmentType> assignmentTypes,
					Assignment inputAssignment){
		
		//Set the local assignment variable to be equal to the one that was passed
		//	This will allow me to populate the fields
		//
		assignment = inputAssignment;
		
		//Create the jpanels
		//
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		//Create the jtextfields and set their values to the values from the assignment
		//
		txtAssignmentName = new JTextField(assignment.getName());
		txtAssignmentName.setEnabled(false);
		txtTotalPoints = new JTextField(assignment.getPoints() + "");
		txtDescription = new JTextField(assignment.getDescription());
		course = new JTextField(assignment.getCourse());
		
		//Disable the course editing field so that it doesn't get modified
		//
		course.setEnabled(false);
		
		//Set the assignment type field
		//
		assignmentType = new JTextField(assignment.getType() + "");
		assignmentType.setEnabled(false);
		
		//Submit button
		//
		btnSubmit = new JButton("Submit Changes");
		
		//Add all of the elements to the panels
		//Left panel
		//
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("Assignment Name:"));
		leftPanel.add(new JLabel("Course:"));
		leftPanel.add(new JLabel("Type:"));
		leftPanel.add(new JLabel("Total Points:"));
		leftPanel.add(new JLabel("Description"));
		
		//right panel
		//
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtAssignmentName);
		rightPanel.add(course);
		rightPanel.add(assignmentType);
		rightPanel.add(txtTotalPoints);
		rightPanel.add(txtDescription);
		
		//Main panel that holds both panels
		//
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		//Set the window properties
		//
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Modify Assignment");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		//Make it visible
		//
		this.setVisible(true);
	}
	
	//Public setter method for the button action.  This will be passed from the main program
	//
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	//Create a new student from the provided information with a matching id_num
	//	from the student provided
	//
	public Assignment newAssignment(){
		return new Assignment(	assignment.getID(), 
				      	assignment.getName(), 
				      	assignment.getCourse(), 
					txtDescription.getText(), 
				      	assignment.getType(), 
					Integer.parseInt(txtTotalPoints.getText()));
	}
}
