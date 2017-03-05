import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class WelcomeFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel mainPanel, middleThird, buttonPanel;
	private JButton btnManageStudents, btnManageClasses, btnManageCourses, btnManageAssignments,
				btnEnterGrades, btnGenerateReports;
	private BufferedImage logo;
	
	
	public WelcomeFrame(){
		
		//Create the spacer JLabels for setting up component positioning
		//
		JLabel spacer = new JLabel("");
		JLabel spacer_1 = new JLabel("");
		JLabel spacer_2 = new JLabel("");
		JLabel spacer_4 = new JLabel("");
		JLabel spacer_5 = new JLabel("");
		JLabel spacer_6 = new JLabel("");
		JLabel spacer_7 = new JLabel("");
		JLabel spacer_8 = new JLabel("");

		
		//Create the JPanels that hold components.
		//	mainPanel is the entire frame
		//	middleThird is the middle third (vertically) that shows the buttons and the logo
		//
		mainPanel = new JPanel();
		middleThird = new JPanel();
		buttonPanel = new JPanel();
		
		//Create the buttons that go to each of the different main functions
		//
		btnManageStudents = new JButton("Manage Students");
		btnManageCourses = new JButton("Manage Courses");
		btnManageClasses = new JButton("Manage Classes");
		btnEnterGrades = new JButton("Enter Grades");
		btnGenerateReports = new JButton("Generate Reports");
		btnManageAssignments = new JButton("Manage Assignments");
		
		
		//Begin building the layout:
		//	Set mainPanel layout to GridLayout with 3 rows and 1 column
		//	buttonPanel layout to GridLayout with variable rows and 2 columns
		//
		mainPanel.setLayout(new GridLayout(3, 1, 0, 0));
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 10));
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		//Add the buttons to the buttonPanel
		//	Spacers are added to shift the buttons further to the left
		//
		buttonPanel.add(btnManageStudents);
		buttonPanel.add(spacer);
		buttonPanel.add(btnManageCourses);
		buttonPanel.add(spacer_1);
		buttonPanel.add(btnManageClasses);
		buttonPanel.add(spacer_2);
		buttonPanel.add(btnEnterGrades);
		buttonPanel.add(spacer_4);
		buttonPanel.add(btnGenerateReports);
		buttonPanel.add(spacer_5);
		
		
		//Add the button panel to the middleThird,
		//	and add a spacer to middleThird.  This breaks the middle third into
		//	two halves for spacing purposes
		//
		middleThird.setLayout(new GridLayout(1, 2, 0, 0));
		middleThird.add(buttonPanel);
		
		//Import the logo file
		try{
			logo = ImageIO.read(new File("res/Team RamRod Logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logo));
			middleThird.add(logoLabel);
		}
		
		catch(Exception e){
			System.err.println(e);
			middleThird.add(spacer_6);
		}
				
		
		

		
		//Add components to the mainPanel
		//	spacer_7 adds a blank spacer to the top third, 
		//	middleThird is the main content holder,
		//	and spacer_8 is the bottom third
		//
		mainPanel.add(spacer_7);
		mainPanel.add(middleThird);
		mainPanel.add(spacer_8);
		
		
		//Add the main panel to the frame
		//
		this.add(mainPanel);
	}
	
	
	//These are setter methods for each of the buttons.  This allows all control code to be placed
	//	in the main program instead of in this class
	//
	public void manageStudentsActionListener(ActionListener msal){
		btnManageStudents.addActionListener(msal);
	}
	
	public void manageClassesActionListener(ActionListener mcal){
		btnManageClasses.addActionListener(mcal);
	}
	
	public void manageCoursesActionListener(ActionListener mcal){
		btnManageCourses.addActionListener(mcal);
	}
	
	public void manageAssignmentsActionListener(ActionListener maal){
		btnManageAssignments.addActionListener(maal);
	}
	
	public void enterGradesActionListener(ActionListener egal){
		btnEnterGrades.addActionListener(egal);
	}
	
	public void generateReportsActionListener(ActionListener gral){
		btnGenerateReports.addActionListener(gral);
	}
	
	
	
}
