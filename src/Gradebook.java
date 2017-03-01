import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Gradebook extends JFrame{


	private static final long serialVersionUID = 1L;
	
	//Create each of the panels
	//
	private WelcomeFrame welcome;
	private ManageStudentsFrame manageStudents;
	private ManageClassesFrame manageClasses;
	private ManageCourseList manageCourses;
	private EnterGrades enterGrades;
	private GenerateReportFrame reportPanel;
	private NewStudentPopup newStudentPanel;
	
	
	private JPanel applicationPanel;
		
	
	private DefaultListModel<Student> studentList;
	
	private Student selectedStudent;
	
	private CardLayout mainCL;
	
	//String constants used for the card layout
	//
	private static String 	WELCOME = "welcome", 
							MANAGESTUDENTS = "manage students", 
							MANAGECLASSES = "manage classes", 
							MANAGECOURSES = "manage courses",
							ENTERGRADES = "enter grades",
							REPORTS = "generate reports";
	
	
	public static void main(String args[]){
		
		//Run a new gradebook
		//
		new Gradebook();
		
	}
	
	
	public Gradebook(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1024, 768);
		this.setLayout(new BorderLayout());
		
		//Create the welcome frame, and add the action listeners
		//	for each button
		//
		welcome = new WelcomeFrame();
		welcome.manageStudentsActionListener(new ShowStudentCard());
		welcome.manageClassesActionListener(new ShowClassesCard());
		welcome.manageCoursesActionListener(new ShowCoursesCard());
		welcome.manageAssignmentsActionListener(new ShowAssignmentsCard());
		welcome.enterGradesActionListener(new ShowGradesCard());
		welcome.generateReportsActionListener(new GenerateReportsCard());
		
		
		//Create the manage students frame and add action listeners
		//	for each button
		//
		manageStudents = new ManageStudentsFrame();
		manageStudents.loadInfoActionListener(new LoadStudentInfo());
		manageStudents.submitChangesActionListener(new SubmitStudentChanges());
		manageStudents.homeButtonActionListener(new ShowWelcomeScreen());
		manageStudents.newStudentActionListener(new CreateNewStudent());
		manageStudents.deleteStudentActionListener(new DeleteStudent());
		
		
		//Create the manage classes frame and add action listeners
		//	for each button
		//
		manageClasses = new ManageClassesFrame();
		manageClasses.homeButtonActionListener(new ShowWelcomeScreen());
		manageClasses.deleteClassActionListener(new DeleteClass());
		manageClasses.addClassActionListener(new AddClass());
		manageClasses.deleteStudentActionListener(new RemoveStudentFromClass());
		manageClasses.addStudentActionListener(new AddStudentToClass());
		manageClasses.showClassActionListener(new ManageShowClass());
		manageClasses.submitChangesActionListener(new ManageSubmitChanges());

		
		
		//Create the manage courses frame and add action listeners
		//	for each button
		//
		manageCourses = new ManageCourseList();
		manageCourses.homeButtonActionListener(new ShowWelcomeScreen());
		manageCourses.deleteCourseActionListener(new DeleteCourse());
		manageCourses.addCourseActionListener(new AddCourse());
		manageCourses.deleteAssignmentActionListener(new DeleteAssignment());
		manageCourses.addAssignmentActionListener(new AddAssignment());
		manageCourses.showClassActionListener(new CoursesShowClass());
		manageCourses.submitChangesActionListener(new CoursesSubmitChanges());
		
		//Create the enter grades frame and add action listeners
		//	for each button
		//
		enterGrades = new EnterGrades();
		enterGrades.homeButtonActionListener(new ShowWelcomeScreen());
		enterGrades.saveActionListener(new SaveGrades());
		enterGrades.showClassActionListener(new GradesShowClass());
		enterGrades.showAssignmentsActionListener(new GradesShowAssignments());
		
		//Create the report panel and add action listeners for
		//	each button
		//
		reportPanel = new GenerateReportFrame();
		reportPanel.homeButtonActionListener(new ShowWelcomeScreen());
		reportPanel.printActionListener(new PrintReport());
		reportPanel.showClassActionListener(new ReportsShowClass());
		reportPanel.generateReportActionListener(new GenerateReport());
		
		//Create the cardlayout, and initialize it with
		//	each of the different panels.  Also set up the
		//	constants used for flipping panels
		//
		mainCL = new CardLayout();
		applicationPanel = new JPanel(mainCL);
		applicationPanel.add(welcome, WELCOME);
		applicationPanel.add(manageCourses, MANAGECOURSES);
		applicationPanel.add(manageClasses, MANAGECLASSES);
		applicationPanel.add(manageStudents, MANAGESTUDENTS);
		applicationPanel.add(enterGrades, ENTERGRADES);
		applicationPanel.add(reportPanel, REPORTS);
		
		
		//Add the main panel to the center of the screen and set visible
		this.add(applicationPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
		studentList = new DefaultListModel<Student>();
		
	}
	
	
	//Show the manage student frame
	//
	class ShowStudentCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, MANAGESTUDENTS);
			
			//Clear the student list
			//
			studentList.removeAllElements();
			
			//get the list of students from the database and repopulate the studentList
			//
			loadStudentsFromDB();
			
			//Update the student list window
			manageStudents.setStudentList(studentList);
			
		}
	};
	
	
	//Show the manage classes frame
	//
	class ShowClassesCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, MANAGECLASSES);
		}
	};
	
	
	//Show the manage courses frame
	//
	class ShowCoursesCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, MANAGECOURSES);
		}
	};
	
	class ShowAssignmentsCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	};
	
	class ShowGradesCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, ENTERGRADES);
		}
	};
	
	class GenerateReportsCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, REPORTS);
		}
	};
	
	class LoadStudentInfo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//get currently selected member from the manage students panel
			//
			int currentSelectedStudent = manageStudents.getSelectedStudentIndex();
			
			selectedStudent = studentList.getElementAt(currentSelectedStudent);
			
			try{
				Connection c = null;
				Statement stmt = null;
				
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:bin/Capstone");
				
				
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE "
											+ "id_num = '" + selectedStudent.getID() + "';");
			
				while(rs.next()){
					selectedStudent = new Student(Integer.parseInt(rs.getString("id_num")), 
											rs.getString("first_name"), 
											rs.getString("last_name"),
											rs.getString("address"),
											rs.getString("city"),
											rs.getString("state"),
											rs.getString("zip"),
											rs.getString("dob"));
				
				}
				
				
				rs.close();
				stmt.close();
				c.close();
				
				manageStudents.displayStudentInfo(selectedStudent);
				
			}
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
			
		}
	};
	
	class SubmitStudentChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	};
	
	
	//Show the welcome screen/home page
	//
	class ShowWelcomeScreen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, WELCOME);
		}
	};
	
	
	class PrintReport implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class ReportsShowClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class GenerateReport implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class CreateNewStudent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Open the popup window to create a new student
			//
			newStudentPanel = new NewStudentPopup();
			
			//Set what the submit button does in the new student popup
			//
			newStudentPanel.submitActionListener(new SubmitNewStudent());
		}
	}
	
	class SubmitNewStudent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get a new student object from the popup window
			//
			Student newStudent = newStudentPanel.newStudent();
			
			Connection c = null;
			Statement stmt = null;
			
			
			//Connect to the database
			try{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:bin/Capstone");
				stmt = c.createStatement();
		
				
				//Insert into the database the new student information
				//
				stmt.executeUpdate("INSERT INTO student (first_name, last_name, address, city, state, zip, dob) VALUES ('"
							+ newStudent.getFirst() + "', '"
							+ newStudent.getLast() + "', '"
							+ newStudent.getAddress() + "', '"
							+ newStudent.getCity() + "', '"
							+ newStudent.getState() + "', '"
							+ newStudent.getZip() + "', '"
							+ newStudent.getDOB() + "');");
				
			
				stmt.close();
				c.close();
				
				//Close the new student popup
				//
				newStudentPanel.dispose();
				
				//Clear the student list
				//
				studentList.removeAllElements();
				
				//get the list of students from the database and re-populate the studentList
				//
				loadStudentsFromDB();
				
				//Update the student list window
				manageStudents.setStudentList(studentList);
			}
			
			catch(Exception e2){
				System.err.println(e2);
				
			}
		}
	}
	
	
	class DeleteStudent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class DeleteClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class AddClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class RemoveStudentFromClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class AddStudentToClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class ManageShowClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class ManageSubmitChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class SaveGrades implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class GradesShowClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class GradesShowAssignments implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class DeleteCourse implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class AddCourse implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class DeleteAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class AddAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class CoursesShowClass implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	
	class CoursesSubmitChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public boolean loadStudentsFromDB(){
		
		try{
			Connection c = null;
			Statement stmt = null;
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:bin/Capstone");
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_num, last_name, first_name, active FROM student ORDER BY last_name asc;");
		
			while(rs.next()){
				if(rs.getString("active").equals("1")){
					studentList.addElement(
						new Student(Integer.parseInt(rs.getString("id_num")), 
										rs.getString("first_name"), 
										rs.getString("last_name")));
			
				}
			}
			
			rs.close();
			stmt.close();
			c.close();
			
			return true;
		}
		
		catch(Exception e){
			
			System.err.println(e);
			
			return false;
		}
		
	}
	
}
