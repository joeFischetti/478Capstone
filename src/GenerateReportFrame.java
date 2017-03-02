import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class GenerateReportFrame extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel 	mainPanel, topPanel, topLeftContentPane, topRightContentPane, 
					bottomPanel, homeButtonPanel, studentSelectPanel, 
					classSelectPanel, bottomNorthPanel;
	
	private JTable assignmentGrades;
	
	private JScrollPane tableScrollPane;
	
	private JLabel spacer_1, spacer_2;
	
	private JScrollPane classList, classRoster;
	
	private JButton btnCancelToHome, btnPrintReport, btnShowClassRoster, btnGenerateReport;
	
	private String[] displayColumns = {"Student Name", "Points", 
							"Possible Points", "Percentage", "Letter Grade", "Rank"};
	
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
		btnGenerateReport = new JButton("Show Student Assignments");
		btnShowClassRoster = new JButton("Show Class Roster");
		
		//TEMPORARY CODE
		//--------------------------------------------------------------------------
		String[][] assignmentData = {
				{"One, Student", "65", "100", "65%", "D", "2"},
				{"Two, Student", "100", "100", "100%", "A", "1"}
		};
		
		String[] listOfClasses = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6"};
		String[] listOfStudents = {"Student 1", "Student 2", "Student 3", "Student 4", "Student 5"};
		
		classList = new JScrollPane();
		classRoster = new JScrollPane();
				
		JList<String> list = new JList<String>(listOfClasses);
		JList<String> list2 = new JList<String>(listOfStudents);
		
		classList.setViewportView(list);
		classRoster.setViewportView(list2);
		
		//---------------------------------------------------------------------------
		
		assignmentGrades = new JTable(assignmentData, displayColumns);
		
		homeButtonPanel.add(btnPrintReport);
		homeButtonPanel.add(btnCancelToHome);
		
		studentSelectPanel.add(classRoster);
		
		classSelectPanel.add(classList);
		classSelectPanel.add(btnShowClassRoster, BorderLayout.SOUTH);
		topLeftContentPane.add(classSelectPanel, BorderLayout.CENTER);
		topRightContentPane.add(studentSelectPanel, BorderLayout.CENTER);
		
		
		topPanel.add(topLeftContentPane);
		topPanel.add(topRightContentPane);

		tableScrollPane = new JScrollPane(assignmentGrades);
		
		bottomNorthPanel.add(btnGenerateReport);
		
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
	
	public void printActionListener(ActionListener pal){
		btnPrintReport.addActionListener(pal);
	}
	
	public void showClassActionListener(ActionListener scal){
		btnShowClassRoster.addActionListener(scal);
	}
	
	public void generateReportActionListener(ActionListener gral){
		btnGenerateReport.addActionListener(gral);
	}
}