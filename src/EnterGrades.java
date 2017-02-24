import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class EnterGrades extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, topPanel, topLeftContentPane, bottomPanel, bottomContentPane, homeButtonPanel,
					studentSelectPanel, classSelectPanel, studentLabelPanel, classesLabelPanel;
	
	private JTable assignmentGrades;
	
	private JScrollPane tableScrollPane;
	
	private JLabel spacer_0;
	
	private JComboBox studentsDropDown, classesDropDown;
	
	private JButton btnCancelToHome, btnSaveAllGrades;
	
	private String[] displayColumns = {"Assignment Name", "Assignment Type", "Points / Total", "Percentage", "Letter Grade"};
	
	public EnterGrades(){
		
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topLeftContentPane = new JPanel();
		bottomPanel = new JPanel();
		bottomContentPane = new JPanel();
		homeButtonPanel = new JPanel();
		studentSelectPanel = new JPanel();
		classSelectPanel = new JPanel();
		studentLabelPanel = new JPanel();
		classesLabelPanel = new JPanel();
		
		
		FlowLayout flowLayout = (FlowLayout) classesLabelPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		flowLayout = (FlowLayout) studentLabelPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		
		studentsDropDown = new JComboBox<String>();
		studentsDropDown.setPrototypeDisplayValue("Select Student     ");
		
		classesDropDown = new JComboBox<String>();
		classesDropDown.setPrototypeDisplayValue("Select a Class      ");
		
		JLabel lblStudentName = new JLabel("Student Name:  ");
		JLabel lblClassNumber = new JLabel("Class Number:  ");
		JLabel lblAssignmentName = new JLabel("Assignment Name");
		JLabel lblAssignmentType = new JLabel("Assignment Type");
		JLabel lblPoints = new JLabel("Point / Total");
		JLabel lblPercentage = new JLabel("Percentage Conversion");
		JLabel lblLetterGrade = new JLabel("Letter Grade");
		JLabel lblCurrentTotal = new JLabel("Current Total");
		JLabel lblSemesterGrade = new JLabel("Semester Total");
		
		spacer_0 = new JLabel("");
		
		
		topPanel.setLayout(new GridLayout(0,2));
		mainPanel.setLayout(new GridLayout(2,0));
		topLeftContentPane.setLayout(new GridLayout(2,2));
		GridLayout gl_bottomPanel = new GridLayout();
		bottomPanel.setLayout(gl_bottomPanel);
		
		this.setLayout(new BorderLayout());
		
		btnCancelToHome = new JButton("Home Screen");
		btnSaveAllGrades = new JButton("Save All Grades");
		
		
		//TEMPORARY CODE
		String[][] assignmentData = {
				{"Quiz 1", "Quiz", "50 / 50", "100", "A"},
				{"Homework 1", "Homework", "20/25", "80", "B"}
		};
		
		assignmentGrades = new JTable(assignmentData, displayColumns);
		
		homeButtonPanel.add(btnSaveAllGrades);
		homeButtonPanel.add(btnCancelToHome);
		studentSelectPanel.add(studentsDropDown);
		
		
		classesLabelPanel.add(lblClassNumber);
		
		topLeftContentPane.add(classesLabelPanel);
		topLeftContentPane.add(classSelectPanel);
		classSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		classSelectPanel.add(classesDropDown);
		
		studentLabelPanel.add(lblStudentName);
		
		topLeftContentPane.add(studentLabelPanel);
		topLeftContentPane.add(studentSelectPanel);
				
		topPanel.add(topLeftContentPane);
		topPanel.add(spacer_0);

		tableScrollPane = new JScrollPane(assignmentGrades);
		
		bottomPanel.add(tableScrollPane);

		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
		
	}
	
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
}
