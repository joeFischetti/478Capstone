import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class NewAssignmentPopup extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton btnSubmit;
	
	private JList<Course> course;
	private JList<AssignmentType> assignmentType;
	
	private JScrollPane courseListPane, typeListPane;
	
	private JTextField txtAssignmentName, txtTotalPoints, txtDescription;
	
	public NewAssignmentPopup(DefaultListModel<Course> courseList, DefaultListModel<AssignmentType> assignmentTypes){
		
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
				
		txtAssignmentName = new JTextField();
		txtTotalPoints = new JTextField();
		txtDescription = new JTextField();
		
		course = new JList<Course>();
		course.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		course.setModel(courseList);
		
		assignmentType = new JList<AssignmentType>();
		assignmentType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assignmentType.setModel(assignmentTypes);
		
		courseListPane = new JScrollPane();
		typeListPane = new JScrollPane();
		
		courseListPane.setViewportView(course);
		typeListPane.setViewportView(assignmentType);
		
		btnSubmit = new JButton("Submit New Assignment");
		
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("Assignment Name:"));
		leftPanel.add(new JLabel("Course:"));
		leftPanel.add(new JLabel("Type:"));
		leftPanel.add(new JLabel("Total Points:"));
		leftPanel.add(new JLabel("Description"));
		
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtAssignmentName);
		rightPanel.add(courseListPane);
		rightPanel.add(typeListPane);
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
		return new Assignment(123, txtAssignmentName.getText(), course.getSelectedValue().toString(), 
							txtDescription.getText(), assignmentType.getSelectedIndex() + 1, 
							Integer.parseInt(txtTotalPoints.getText()));
	}
}