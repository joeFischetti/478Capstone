import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class EnterGrades extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, topPanel, topLeftContentPane, topRightContentPane, bottomPanel, homeButtonPanel,
					studentSelectPanel, classSelectPanel, bottomNorthPanel;
	
	private JTable assignmentGrades;
	
	private JScrollPane tableScrollPane;
	
	private JLabel spacer_1, spacer_2;
	
	
	
	private JScrollPane classList, classRoster;
	
	private JButton btnCancelToHome, btnSaveAllGrades, btnShowClassRoster, btnShowStudentAssignments;
	
	private String[] displayColumns = {"Assignment Name", "Assignment Type", "Points / Total", "Percentage", "Letter Grade"};
	
	public EnterGrades(){
		
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topLeftContentPane = new JPanel();
		topRightContentPane = new JPanel();
		bottomPanel = new JPanel();
		homeButtonPanel = new JPanel();
		studentSelectPanel = new JPanel();
		classSelectPanel = new JPanel();
		bottomNorthPanel = new JPanel();
				
		
		spacer_1 = new JLabel("");
		spacer_2 = new JLabel("");
		
		topPanel.setLayout(new GridLayout(0,2));
		mainPanel.setLayout(new GridLayout(2,0));
		BorderLayout bl_bottomPanel = new BorderLayout();
		bl_bottomPanel.setHgap(25);
		bottomPanel.setLayout(bl_bottomPanel);
		topLeftContentPane.setLayout(new BorderLayout());
		topRightContentPane.setLayout(new BorderLayout());
		
		classSelectPanel.setLayout(new BorderLayout());
		studentSelectPanel.setLayout(new BorderLayout());
		
		this.setLayout(new BorderLayout());
		
		btnCancelToHome = new JButton("Home Screen");
		btnSaveAllGrades = new JButton("Save All Grades");
		btnShowStudentAssignments = new JButton("Show Student Assignments");
		btnShowClassRoster = new JButton("Show Class Roster");
		
		//TEMPORARY CODE
		String[][] assignmentData = {
				{"Quiz 1", "Quiz", "50 / 50", "100", "A"},
				{"Homework 1", "Homework", "20/25", "80", "B"}
		};
		
		String[] listOfClasses = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6"};
		String[] listOfStudents = {"Student 1", "Student 2", "Student 3", "Student 4", "Student 5"};
		
		classList = new JScrollPane();
		classRoster = new JScrollPane();
				
		JList<String> list = new JList<String>(listOfClasses);
		JList<String> list2 = new JList<String>(listOfStudents);
		
		classList.setViewportView(list);
		classRoster.setViewportView(list2);
		
		assignmentGrades = new JTable(assignmentData, displayColumns);
		
		homeButtonPanel.add(btnSaveAllGrades);
		homeButtonPanel.add(btnCancelToHome);
		
		studentSelectPanel.add(classRoster);
		
		classSelectPanel.add(classList);
		classSelectPanel.add(btnShowClassRoster, BorderLayout.SOUTH);
		topLeftContentPane.add(classSelectPanel, BorderLayout.CENTER);
		topRightContentPane.add(studentSelectPanel, BorderLayout.CENTER);
		
		
		topPanel.add(topLeftContentPane);
		topPanel.add(topRightContentPane);

		tableScrollPane = new JScrollPane(assignmentGrades);
		
		bottomNorthPanel.add(btnShowStudentAssignments);
		
		bottomPanel.add(tableScrollPane, BorderLayout.CENTER);
		bottomPanel.add(spacer_1, BorderLayout.EAST);
		bottomPanel.add(spacer_2, BorderLayout.WEST);
		bottomPanel.add(bottomNorthPanel, BorderLayout.NORTH);
		
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
		
	}
	
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
	public void saveActionListener(ActionListener sal){
		btnSaveAllGrades.addActionListener(sal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnShowClassRoster.addActionListener(scal);
	}
	
	public void showAssignmentsActionListener(ActionListener saal){
		btnShowStudentAssignments.addActionListener(saal);
	}
}
