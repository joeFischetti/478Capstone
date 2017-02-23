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

public class ManageClassesFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel, leftPanel, leftContentPanel, leftButtonPanel, rightPanel,
					rightContentPanel, rightClassInfoPanel, rightStudentListPanel, rightButtonPanel, homeButtonPanel;
	
	private JTextField classID, txtCourseNum, txtMeetingDay, txtMaxStudents;
	
	private JButton btnDeleteClass, btnLoadClassInfo, btnSubmitAboveChanges, btnCancelToHome;
	
	private JScrollPane classList, classRoster;

	public ManageClassesFrame(){
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
		rightStudentListPanel = new JPanel();
		rightButtonPanel = new JPanel();
		homeButtonPanel = new JPanel();
				
				
		//Create the labels for each of the different pieces of class data
		//
		JLabel lblClassNumber = new JLabel("Class Number");
		JLabel lblCourseNumber = new JLabel("Course Number");
		JLabel lblMeetingDay = new JLabel("Meeting Day");
		JLabel lblMaxStudents = new JLabel("Max Students");
				
				
		//Create each of the JText fields for different
		//	pieces of class data
		//
		classID = new JTextField();
		txtCourseNum = new JTextField();
		txtMeetingDay = new JTextField();
		txtMaxStudents = new JTextField();
	
				
			
		//Set each panel's layout
		//
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
				
		leftContentPanel.setLayout(new BorderLayout(0, 0));
		leftPanel.setLayout(new BorderLayout(0, 0));
				
		rightContentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		rightClassInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));
		rightStudentListPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout(0, 0));
				
		
		//Create the buttons that are used on the panel.
		//
		btnDeleteClass = new JButton("Delete Class");
		btnLoadClassInfo = new JButton("Show Class Info >>>");
		btnSubmitAboveChanges = new JButton("<<< Store Class info");
		btnCancelToHome = new JButton("Home Screen");
				
				
		//TEMPORARY CODE*******************
		String[] listOfClasses = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6"};
		String[] listOfStudents = {"Student 1", "Student 2", "Student 3", "Student 4", "Student 5"};
		
		classList = new JScrollPane();
		classRoster = new JScrollPane();
				
		JList<String> list = new JList<String>(listOfClasses);
		JList<String> list2 = new JList<String>(listOfStudents);
		
		classList.setViewportView(list);
		classRoster.setViewportView(list2);		

		//Initialize the text fields with placeholder info
		//
		classID.setText("Class Number");
		classID.setColumns(10);
			
		txtCourseNum.setText("Course Name/Number");
		txtCourseNum.setColumns(10);
				
		txtMeetingDay.setText("Meeting Day");
		txtMeetingDay.setColumns(10);
				
		txtMaxStudents.setText("Max Students");
		txtMaxStudents.setColumns(10);
			
				
				
		//Populate each of the panels
		//
		leftContentPanel.add(classList);
				
		leftButtonPanel.add(btnDeleteClass);
		leftButtonPanel.add(btnLoadClassInfo);
				
				
		rightClassInfoPanel.add(lblClassNumber);
		rightClassInfoPanel.add(classID);
		rightClassInfoPanel.add(lblCourseNumber);
		rightClassInfoPanel.add(txtCourseNum);
		rightClassInfoPanel.add(lblMeetingDay);
		rightClassInfoPanel.add(txtMeetingDay);
		rightClassInfoPanel.add(lblMaxStudents);
		rightClassInfoPanel.add(txtMaxStudents);
		
		rightStudentListPanel.add(classRoster, BorderLayout.CENTER);
		
		rightButtonPanel.add(btnSubmitAboveChanges);
				
			
		leftPanel.add(leftContentPanel, BorderLayout.CENTER);
		leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);
				
				
		rightContentPanel.add(rightClassInfoPanel);
		rightContentPanel.add(rightStudentListPanel);
		
		rightPanel.add(rightContentPanel, BorderLayout.CENTER);
		rightPanel.add(rightButtonPanel, BorderLayout.SOUTH);
				
				
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
				
		homeButtonPanel.add(btnCancelToHome);
				
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
	}
	
	public void deleteButtonActionListener(ActionListener dal){
		btnDeleteClass.addActionListener(dal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnLoadClassInfo.addActionListener(scal);
	}
	
	public void submitChangesActionListener(ActionListener sal){
		btnSubmitAboveChanges.addActionListener(sal);
	}
	
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
}
