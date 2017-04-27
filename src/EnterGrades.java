//This is the enter grades frame
//

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class EnterGrades extends JPanel{

	private static final long serialVersionUID = 1L;

	//Create the JPanels for the different areas of the frame
	//
	private JPanel 	mainPanel, 
			topPanel, 
			topLeftContentPane, 
			topRightContentPane, 
			bottomPanel, 
			homeButtonPanel,
			studentSelectPanel, 
			classSelectPanel, 
			bottomNorthPanel;
	
	//Create the JTable for the assignments and their grades
	//
	private JTable assignmentGrades;
	
	//The table model that will be applied to the JTable
	//
	private DefaultTableModel gradesTableModel;
	
	//The scroll pane that will hold the assignments panel (in case there are 
	// too many assignments for the panel.
	//
	private JScrollPane tableScrollPane;
	
	//Spacers
	//
	private JLabel 	spacer_1, 
			spacer_2;
	
	//The list of classes that will be selectable
	//
	private JList<CourseSection> classes;
	
	//The list of students which will only be updated after selecting a class
	//
	private JList<Student> students;
	
	//The scroll panes for the class list and the roster
	//
	private JScrollPane 	classList, 
				classRoster;
	
	//All of the buttons that show up on the screen
	//  names are self explanatory
	//
	private JButton 	btnCancelToHome, 
				btnSaveAllGrades, 
				btnShowClassRoster, 
				btnShowStudentAssignments, 
				btnSelectNewClass;
	
	//The string array that will be used for the table columns
	//
	private String[] displayColumns = {	"Assignment Name", 
						"Points", 
						"Total Points", 
						"Percentage", 
						"Letter Grade"};
	
	public EnterGrades(){
		
		//Initialize each of the jpanels
		///
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topLeftContentPane = new JPanel();
		topRightContentPane = new JPanel();
		bottomPanel = new JPanel();
		homeButtonPanel = new JPanel();
		studentSelectPanel = new JPanel();
		classSelectPanel = new JPanel();
		bottomNorthPanel = new JPanel();
					
		//Initialize the spacers (these will be used as placeholders
		// because swing is picky
		//
		spacer_1 = new JLabel("");
		spacer_2 = new JLabel("");
		
		//Set the layout methods for each of the jpanels
		//
		topPanel.setLayout(new GridLayout(0,2));
		mainPanel.setLayout(new GridLayout(2,0));
		BorderLayout bl_bottomPanel = new BorderLayout();
		bl_bottomPanel.setHgap(25);
		bottomPanel.setLayout(bl_bottomPanel);
		topLeftContentPane.setLayout(new BorderLayout());
		topRightContentPane.setLayout(new BorderLayout());
		classSelectPanel.setLayout(new BorderLayout());
		studentSelectPanel.setLayout(new BorderLayout());
		
		//Set the main window's layout
		//
		this.setLayout(new BorderLayout());
		
		//Init the buttons and give them text
		//
		btnCancelToHome = new JButton("Home Screen");
		btnSaveAllGrades = new JButton("Save All Grades");
		btnShowStudentAssignments = new JButton("Show Student Assignments");
		btnShowClassRoster = new JButton("Show Class Roster");
		btnSelectNewClass = new JButton("Select New Class");
		
		//Init the table, set the table model, apply the table model to the table
		// and put the table in the scroll pane
		//
		assignmentGrades = new JTable();
		gradesTableModel = (DefaultTableModel)assignmentGrades.getModel();
		gradesTableModel.setColumnIdentifiers(displayColumns);
		tableScrollPane = new JScrollPane(assignmentGrades);
		
		//Init the class list
		//
		classes = new JList<CourseSection>();
		classes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Init the student list
		//
		students = new JList<Student>();
		students.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//init the scroll panes for the student and class lists
		//
		classList = new JScrollPane();
		classRoster = new JScrollPane();		
		classList.setViewportView(classes);
		classRoster.setViewportView(students);
		
		//Add the buttons to the main portion of the panel
		//
		homeButtonPanel.add(btnSaveAllGrades);
		homeButtonPanel.add(btnCancelToHome);
		
		//Add the various parts and pieces to their respective panels
		//  Do I have to type it all out?
		//
		studentSelectPanel.add(classRoster);
		classSelectPanel.add(classList);
		classSelectPanel.add(btnShowClassRoster, BorderLayout.SOUTH);
		topLeftContentPane.add(classSelectPanel, BorderLayout.CENTER);
		topRightContentPane.add(studentSelectPanel, BorderLayout.CENTER);
		
		topPanel.add(topLeftContentPane);
		topPanel.add(topRightContentPane);
		
		bottomNorthPanel.add(btnSelectNewClass);
		bottomNorthPanel.add(btnShowStudentAssignments);
		
		bottomPanel.add(tableScrollPane, BorderLayout.CENTER);
		bottomPanel.add(spacer_1, BorderLayout.EAST);
		bottomPanel.add(spacer_2, BorderLayout.WEST);
		bottomPanel.add(bottomNorthPanel, BorderLayout.NORTH);
		
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		
		//Add the main and home button panels to the actual framme
		//
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
		
	}
	
	//Action listener setter methods for each of the buttons on the panel
	//
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
	public void saveActionListener(ActionListener sal){
		btnSaveAllGrades.addActionListener(sal);
	}
	
	public void selectNewClassActionListener(ActionListener sal){
		btnSelectNewClass.addActionListener(sal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnShowClassRoster.addActionListener(scal);
	}
	
	public void showAssignmentsActionListener(ActionListener saal){
		btnShowStudentAssignments.addActionListener(saal);
	}
	
	//Set the model for the class list (this updates the list)
	//
	public void setClassList(DefaultListModel<CourseSection> input){
		classes.setModel(input);
		
	}
	
	//Same thing for the student list
	//
	public void setStudentList(DefaultListModel<Student> input){
		students.setModel(input);
		
	}
	
	//return the index of the currently selected class as an integer
	//
	public int getSelectedClassIndex(){
		return classes.getSelectedIndex();
	}
	
	//return the index of the currently selected student as an ineger/
	//
	public int getSelectedStudentIndex(){
		return students.getSelectedIndex();
	}
	
	//Display the assignments, which really means take the 2d string array and put it in the
	// the table model one by one
	//
	public void displayAssignments(String[][] input){
		for(int i = 0; i < input.length; i++){
			gradesTableModel.addRow(input[i]);
		}
	}
	
	//Return the table model from the list of grades so that the main program
	// can take some action on them
	//
	public DefaultTableModel getGradesFromTable(){
		
		return gradesTableModel;
	}
	
	//Clear out all of the visible assignments
	//
	public void clearAssignments(){
		gradesTableModel.setRowCount(0);
	}
	
	//Reset the display, which clears out anything and everything
	//
	public void resetDisplay(){
		students.setModel(new DefaultListModel<Student>());
		classes.setEnabled(true);
		gradesTableModel.setRowCount(0);	
	}
	
	//Disable selection of classes (so that people don't break things when
	// they try to save grades after selecting a different class)
	//
	public void enableClassSelection(boolean input){
		classes.setEnabled(input);
	}
}
