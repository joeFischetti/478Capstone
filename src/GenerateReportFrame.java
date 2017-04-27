//This is the frame used for generating grading reports for a class
//

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class GenerateReportFrame extends JPanel{

	private static final long serialVersionUID = 1L;

	//Create each of the panels for each different region
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
	
	//Create the jtable that will display the assignments
	//
	private JTable assignmentGrades;
	
	//Create the table model that will populate the table
	//
	private DefaultTableModel gradesTableModel;
	
	//Create a scroll pane that will hold the report
	//
	private JScrollPane tableScrollPane;
	
	//Spacers, because swing is picky
	//
	private JLabel spacer_1, spacer_2;
	
	//Create lists for the student and classes selection
	//
	private JList<CourseSection> classes;
	private JList<Student> students;
	
	//Create scroll panes for the student and classes selection
	//
	private JScrollPane classList, classRoster;
	
	//Create the buttons for the panel
	//
	private JButton 	btnCancelToHome, 
				btnPrintReport, 
				btnShowClassRoster, 
				btnShowStudentAssignments;
	
	//Create the string array for the column names of the jtable
	//
	private String[] displayColumns = {	"Student Name", 
					   	"Points", 
					   	"Possible Points", 
					   	"Percentage", 
					   	"Letter Grade", 
					   	"Rank"};
	
	public GenerateReportFrame(){
		
		//Init all the panels
		//
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topLeftContentPane = new JPanel();
		topRightContentPane = new JPanel();
		bottomPanel = new JPanel();
		homeButtonPanel = new JPanel();
		studentSelectPanel = new JPanel();
		classSelectPanel = new JPanel();
		bottomNorthPanel = new JPanel();
				
		//Init the spacers
		//
		spacer_1 = new JLabel("");
		spacer_2 = new JLabel("");
		
		//Set the layouts on all of the panels
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
		
		//Set the layout on the main window
		//
		this.setLayout(new BorderLayout());
		
		//Init the buttons and make them say things
		//
		btnCancelToHome = new JButton("Home Screen");
		btnPrintReport = new JButton("Print Report");
		btnShowStudentAssignments = new JButton("Generate Report");
		btnShowClassRoster = new JButton("Show Class Roster");
		
		//Init the jtable, and set up the table model
		//  Throw it all in the scroll pane
		//
		assignmentGrades = new JTable();
		gradesTableModel = (DefaultTableModel)assignmentGrades.getModel();
		gradesTableModel.setColumnIdentifiers(displayColumns);
		tableScrollPane = new JScrollPane(assignmentGrades);
		
		//Init the class and student lists, and put them in their own scroll
		// panes
		//
		classes = new JList<CourseSection>();
		classes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		students = new JList<Student>();
		students.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList = new JScrollPane();
		classRoster = new JScrollPane();
		classList.setViewportView(classes);
		classRoster.setViewportView(students);
		
		//Add the buttons to the bottom.  Print report will be used in version 2.0
		//
		//homeButtonPanel.add(btnPrintReport);
		homeButtonPanel.add(btnCancelToHome);
		
		//Add the class roster to the student selection panel
		//
		studentSelectPanel.add(classRoster);
		
		//And add more panels and buttons to different sections of the window
		// Most of this is self explanatory
		//
		classSelectPanel.add(classList);
		classSelectPanel.add(btnShowClassRoster, BorderLayout.SOUTH);
		topLeftContentPane.add(classSelectPanel, BorderLayout.CENTER);
		topRightContentPane.add(studentSelectPanel, BorderLayout.CENTER);
		
		topPanel.add(topLeftContentPane);
		topPanel.add(topRightContentPane);

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
	
	//Setter methods for the actual button actions, which will be passed
	// from the main program
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
	public void printActionListener(ActionListener sal){
		btnPrintReport.addActionListener(sal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnShowClassRoster.addActionListener(scal);
	}
	
	public void generateReportActionListener(ActionListener saal){
		btnShowStudentAssignments.addActionListener(saal);
	}
	
	//Set the class list model to populate the window
	//
	public void setClassList(DefaultListModel<CourseSection> input){
		classes.setModel(input);
	}
	
	//Populate the student list window
	//
	public void setStudentList(DefaultListModel<Student> input){
		students.setModel(input);
	}
	
	//Return the selected class (index)
	//
	public int getSelectedClassIndex(){
		return classes.getSelectedIndex();
	}
	
	//Return the selected student index
	//
	public int getSelectedStudentIndex(){
		return students.getSelectedIndex();
	}
	
	//Set the jtable to show the report data that was passed to it (using a 2d string array)
	//
	public void displayReport(ArrayList<String[]> input){	
		for(int i = 0; i < input.size(); i++){
			gradesTableModel.addRow(input.get(i));
		}
	}
	
	//Get the grades from the table (this actually never gets used... its copied over
	// from the enter grades window.......
	//
	public DefaultTableModel getGradesFromTable(){	
		return gradesTableModel;
	}
	
	//Clear the report from the jtable
	//
	public void clearReport(){
		gradesTableModel.setRowCount(0);
	}
	
	//Clear the whole display
	//
	public void resetDisplay(){
		students.setModel(new DefaultListModel<Student>());
		gradesTableModel.setRowCount(0);
	}
}
