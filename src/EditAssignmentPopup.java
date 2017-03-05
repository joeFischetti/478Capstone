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

	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton btnSubmit;
	
	private JTextField txtAssignmentName, txtTotalPoints, txtDescription, course, assignmentType;
	
	Assignment assignment;
	
	public EditAssignmentPopup(	DefaultListModel<Course> courseList, 
								DefaultListModel<AssignmentType> assignmentTypes,
								Assignment inputAssignment){
		
		assignment = inputAssignment;
		
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
				
		txtAssignmentName = new JTextField(assignment.getName());
		txtAssignmentName.setEnabled(false);
		txtTotalPoints = new JTextField(assignment.getPoints() + "");
		txtDescription = new JTextField(assignment.getDescription());
		
		course = new JTextField(assignment.getCourse());
		course.setEnabled(false);
		
		assignmentType = new JTextField(assignment.getType() + "");
		assignmentType.setEnabled(false);
		
		btnSubmit = new JButton("Submit Changes");
		
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("Assignment Name:"));
		leftPanel.add(new JLabel("Course:"));
		leftPanel.add(new JLabel("Type:"));
		leftPanel.add(new JLabel("Total Points:"));
		leftPanel.add(new JLabel("Description"));
		
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtAssignmentName);
		rightPanel.add(course);
		rightPanel.add(assignmentType);
		rightPanel.add(txtTotalPoints);
		rightPanel.add(txtDescription);
		
		
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Create New Assignment");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	
	//Create a new student from the provided information with a matching id_num
	//	from the student provided
	//
	public Assignment newAssignment(){
		return new Assignment(assignment.getID(), assignment.getName(), assignment.getCourse(), 
							txtDescription.getText(), assignment.getType(), 
							Integer.parseInt(txtTotalPoints.getText()));
	}
}