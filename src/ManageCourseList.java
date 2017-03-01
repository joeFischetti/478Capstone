import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ManageCourseList extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel, leftPanel, leftContentPanel, leftButtonPanel, rightPanel,
	rightContentPanel, rightClassInfoPanel, rightAssignmentListPanel, rightButtonPanel, homeButtonPanel,
			assignmentButtonsPanel;

	private JTextField txtCourseNumber, txtCourseName, txtCourseDescription, txtMaxStudents, txtMeetingDay;

	private JButton btnDeleteCourse, btnLoadCourseInfo, btnSubmitAboveChanges, btnCancelToHome, btnNewAssignment,
			btnDeleteAssignment, btnAddCourse;

	private JScrollPane courseList, assignmentList;
	


	public ManageCourseList(){
		//Create each of the panels
		//	names should be self explanatory.
		//
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		leftContentPanel = new JPanel();
		leftButtonPanel = new JPanel();
		rightPanel = new JPanel();
		rightContentPanel = new JPanel();
		rightClassInfoPanel = new JPanel();
		rightAssignmentListPanel = new JPanel();
		rightButtonPanel = new JPanel();
		homeButtonPanel = new JPanel();
		assignmentButtonsPanel = new JPanel();
						
						
		//Create the labels for each of the different pieces of course data
		//
		JLabel lblCourseNumber = new JLabel("Course Number");
		JLabel lblCourseName = new JLabel("Course Name");
		JLabel lblCourseDescription = new JLabel("Course Description");
		JLabel lblMeetingDay = new JLabel("Meeting Day");
		JLabel lblMaxStudents = new JLabel("Max Students");
						
						
		//Create each of the JText fields for different
		//	pieces of course data
		//
		txtCourseNumber = new JTextField();
		txtCourseName = new JTextField();
		txtCourseDescription = new JTextField();
		txtMaxStudents = new JTextField();
		txtMeetingDay = new JTextField();
			
						
					
		//Set each panel's layout
		//
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
						
		leftContentPanel.setLayout(new BorderLayout(0, 0));
		leftPanel.setLayout(new BorderLayout(0, 0));
						
		rightContentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		rightClassInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));
		rightAssignmentListPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		assignmentButtonsPanel.setLayout(new GridLayout(1, 2));
						
				
		//Create the buttons that are used on the panel.
		//
		btnDeleteCourse = new JButton("Delete Selected Course");
		btnAddCourse = new JButton("Add New Course");
		btnLoadCourseInfo = new JButton("Show Course Info >>>");
		btnSubmitAboveChanges = new JButton("<<< Store Course info");
		btnCancelToHome = new JButton("Home Screen");
		btnNewAssignment = new JButton("Create Assignment");
		btnDeleteAssignment = new JButton("Delete Selected Assignment");

						
		//TEMPORARY CODE*******************
		String[] listOfCourses = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5", "Course 6"};
		String[] listOfAssignments = {"Assignment1", "Assignment 2", "Assignment 3"};
				
		courseList = new JScrollPane();
		assignmentList = new JScrollPane();
						
		JList<String> list = new JList<String>(listOfCourses);
		JList<String> list2 = new JList<String>(listOfAssignments);
				
		courseList.setViewportView(list);
		assignmentList.setViewportView(list2);

		//Initialize the text fields with placeholder info
		//
		txtCourseNumber.setText("Course Number");
		txtCourseNumber.setColumns(10);
					
		txtCourseName.setText("Course Name");
		txtCourseName.setColumns(10);
						
		txtCourseDescription.setText("Course Description");
		txtCourseDescription.setColumns(10);
						
		txtMaxStudents.setText("Max Students");
		txtMaxStudents.setColumns(10);
					
		txtMeetingDay.setText("Meeting Day");
		txtMeetingDay.setColumns(5);
					
		//Populate each of the panels
		//
		leftContentPanel.add(courseList);
						
		leftButtonPanel.add(btnAddCourse);
		leftButtonPanel.add(btnLoadCourseInfo);
						
						
		rightClassInfoPanel.add(lblCourseNumber);
		rightClassInfoPanel.add(txtCourseNumber);
		rightClassInfoPanel.add(lblCourseName);
		rightClassInfoPanel.add(txtCourseName);
		rightClassInfoPanel.add(lblCourseDescription);
		rightClassInfoPanel.add(txtCourseDescription);
		rightClassInfoPanel.add(lblMeetingDay);
		rightClassInfoPanel.add(txtMeetingDay);
		rightClassInfoPanel.add(lblMaxStudents);
		rightClassInfoPanel.add(txtMaxStudents);
				
		rightButtonPanel.add(btnSubmitAboveChanges);
		rightButtonPanel.add(btnDeleteCourse);
					
		leftPanel.add(leftContentPanel, BorderLayout.CENTER);
		leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);
		
		assignmentButtonsPanel.add(btnNewAssignment);
		assignmentButtonsPanel.add(btnDeleteAssignment);

		rightAssignmentListPanel.add(assignmentList);
		rightAssignmentListPanel.add(assignmentButtonsPanel, BorderLayout.SOUTH);		
	

		rightContentPanel.add(rightClassInfoPanel);
		rightContentPanel.add(rightAssignmentListPanel);
				
		rightPanel.add(rightContentPanel, BorderLayout.CENTER);
		rightPanel.add(rightButtonPanel, BorderLayout.SOUTH);
						
						
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
						
		homeButtonPanel.add(btnCancelToHome);
					
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
	}
			
	public void deleteCourseActionListener(ActionListener dcal){
		btnDeleteCourse.addActionListener(dcal);
	}
	
	public void addCourseActionListener(ActionListener acal){
		btnAddCourse.addActionListener(acal);
	}
	
	public void deleteAssignmentActionListener(ActionListener daal){
		btnDeleteAssignment.addActionListener(daal);
	}
	
	public void addAssignmentActionListener(ActionListener aaal){
		btnNewAssignment.addActionListener(aaal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnLoadCourseInfo.addActionListener(scal);
	}
			
	public void submitChangesActionListener(ActionListener sal){
		btnSubmitAboveChanges.addActionListener(sal);
	}
			
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
}
