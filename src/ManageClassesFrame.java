import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class ManageClassesFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel, leftPanel, leftContentPanel, leftButtonPanel, rightPanel,
					rightContentPanel, rightClassInfoPanel, rightStudentListPanel, rightButtonPanel, homeButtonPanel,
					studentListButtonsPanel;
	
	private JTextField classID, txtCourseNum, txtMeetingDay, txtMaxStudents;
	
	private JButton btnDeleteClass, btnAddClass, btnLoadClassInfo, btnSubmitAboveChanges, btnCancelToHome, btnAddStudent,
					btnDeleteStudent;
	
	private JScrollPane classList, classRoster;
	
	private JList<CourseSection> list;
	private JList<Student> list2;

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
		studentListButtonsPanel = new JPanel();
				
				
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
		studentListButtonsPanel.setLayout(new GridLayout(1, 2));
		
		//Create the buttons that are used on the panel.
		//
		btnDeleteClass = new JButton("Delete Class");
		btnAddClass = new JButton("Add new Class");
		btnLoadClassInfo = new JButton("Show Class Info >>>");
		btnSubmitAboveChanges = new JButton("<<< Store Class info");
		btnCancelToHome = new JButton("Home Screen");
		btnAddStudent = new JButton("Add Student");
		btnDeleteStudent = new JButton("Delete Student");
				
		classList = new JScrollPane();
		classRoster = new JScrollPane();
				
		list = new JList<CourseSection>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		list2 = new JList<Student>();
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
				
		leftButtonPanel.add(btnAddClass);
		leftButtonPanel.add(btnLoadClassInfo);
				
				
		rightClassInfoPanel.add(lblClassNumber);
		rightClassInfoPanel.add(classID);
		rightClassInfoPanel.add(lblCourseNumber);
		rightClassInfoPanel.add(txtCourseNum);
		rightClassInfoPanel.add(lblMeetingDay);
		rightClassInfoPanel.add(txtMeetingDay);
		rightClassInfoPanel.add(lblMaxStudents);
		rightClassInfoPanel.add(txtMaxStudents);
		
		studentListButtonsPanel.add(btnAddStudent);
		studentListButtonsPanel.add(btnDeleteStudent);
		
		rightStudentListPanel.add(classRoster, BorderLayout.CENTER);
		rightStudentListPanel.add(studentListButtonsPanel, BorderLayout.SOUTH);
		
		
		//rightButtonPanel.add(btnSubmitAboveChanges);
		rightButtonPanel.add(btnDeleteClass);
			
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
	
	public void deleteClassActionListener(ActionListener dal){
		btnDeleteClass.addActionListener(dal);
	}
	
	public void addClassActionListener(ActionListener dal){
		btnAddClass.addActionListener(dal);
	}
	
	public void deleteStudentActionListener(ActionListener dal){
		btnDeleteStudent.addActionListener(dal);
	}
	
	public void addStudentActionListener(ActionListener dal){
		btnAddStudent.addActionListener(dal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnLoadClassInfo.addActionListener(scal);
	}
	
	//public void submitChangesActionListener(ActionListener sal){
	//	btnSubmitAboveChanges.addActionListener(sal);
	//}
	
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
	public int getSelectedSectionIndex(){
		return list.getSelectedIndex();
	}
	
	public int getSelectedStudentIndex(){
		return list2.getSelectedIndex();
	}
	
	public void displaySectionInfo(CourseSection section, Course course){
		classID.setText(section.getSection() + "");
		txtCourseNum.setText(course.getName());
		txtMeetingDay.setText(course.getDay());
		txtMaxStudents.setText(course.getMax() + "");
	}
	
	public void setSectionList(DefaultListModel<CourseSection> classes){
		list.setModel(classes);
	}
	
	public void setStudentList(DefaultListModel<Student> students){
		list2.setModel(students);
	}
	
	public void resetDisplay(){
		list2.setModel(new DefaultListModel<Student>());
		
		classID.setText("Class Number");
		txtCourseNum.setText("Course Name/Number");
		txtMeetingDay.setText("Meeting Day");
		txtMaxStudents.setText("Max Students");
		
	}
}
