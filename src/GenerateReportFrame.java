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

	private JPanel mainPanel, topPanel, topLeftContentPane, topRightContentPane, bottomPanel, homeButtonPanel,
					studentSelectPanel, classSelectPanel, bottomNorthPanel;
	
	private JTable assignmentGrades;
	private DefaultTableModel gradesTableModel;
	
	private JScrollPane tableScrollPane;
	
	private JLabel spacer_1, spacer_2;
	
	private JList<CourseSection> classes;
	private JList<Student> students;
	
	private JScrollPane classList, classRoster;
	
	private JButton btnCancelToHome, btnPrintReport, btnShowClassRoster, btnShowStudentAssignments;
	
	private String[] displayColumns = {"Student Name", "Points", "Possible Points", "Percentage", "Letter Grade", "Rank"};
	
	public GenerateReportFrame(){
		
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
		btnPrintReport = new JButton("Print Report");
		btnShowStudentAssignments = new JButton("Generate Report");
		btnShowClassRoster = new JButton("Show Class Roster");
		
		
		assignmentGrades = new JTable();
		gradesTableModel = (DefaultTableModel)assignmentGrades.getModel();
		gradesTableModel.setColumnIdentifiers(displayColumns);
		tableScrollPane = new JScrollPane(assignmentGrades);
		
		classes = new JList<CourseSection>();
		classes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		students = new JList<Student>();
		students.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		classList = new JScrollPane();
		classRoster = new JScrollPane();
				
				
		
		classList.setViewportView(classes);
		classRoster.setViewportView(students);
		
		
		
		homeButtonPanel.add(btnPrintReport);
		homeButtonPanel.add(btnCancelToHome);
		
		studentSelectPanel.add(classRoster);
		
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
	
	public void setClassList(DefaultListModel<CourseSection> input){
		classes.setModel(input);
		
	}
	
	public void setStudentList(DefaultListModel<Student> input){
		students.setModel(input);
		
	}
	
	public int getSelectedClassIndex(){
		return classes.getSelectedIndex();
	}
	
	public int getSelectedStudentIndex(){
		return students.getSelectedIndex();
	}
	
	public void displayReport(ArrayList<String[]> input){
		
		for(int i = 0; i < input.size(); i++){
			gradesTableModel.addRow(input.get(i));
		}
	}
	
	public DefaultTableModel getGradesFromTable(){
		
		return gradesTableModel;
	}
	
	public void clearReport(){
		gradesTableModel.setRowCount(0);
	}
	
	public void resetDisplay(){
		students.setModel(new DefaultListModel<Student>());
		gradesTableModel.setRowCount(0);
	}
	
}
