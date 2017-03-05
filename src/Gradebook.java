import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
	private EditStudentPopup editStudentPopup;
	private NewAssignmentPopup newAssignmentPopup;
	private NewCoursePopup newCoursePopup;
	private EditCoursePopup editCoursePopup;
	private EditAssignmentPopup editAssignmentPopup;
	
	
	//Create the main application panel
	//
	private JPanel applicationPanel;
		
	
	//Create the list models that will be used on each panel
	//	studentList is used for a listing of students
	//	courseList is used for a listing of courses
	//	classList is used for a listing of classes
	//	assignmentList is used to list the assignments for a given course
	//	assignmentTypeList is a list of assignment types
	//
	private DefaultListModel<Student> studentList;
	private DefaultListModel<Course> courseList;
	private DefaultListModel<CourseSection> classList;
	private DefaultListModel<Assignment> assignmentList;
	private DefaultListModel<AssignmentType> assignmentTypeList;
	
	
	//Create a student variable used for misc actions
	//
	private Student selectedStudent;
	
	
	//Create a course variable
	//
	private Course selectedCourse;
	
	
	//Create the cardlayout used for swapping the currently
	//	viewed panel
	//
	private CardLayout mainCL;
	
	
	//String constants used for the card layout
	//
	private static String 	WELCOME = "welcome", 
							MANAGESTUDENTS = "manage students", 
							MANAGECLASSES = "manage classes", 
							MANAGECOURSES = "manage courses",
							ENTERGRADES = "enter grades",
							REPORTS = "generate reports";
	
	
	//main method called at program start
	//
	public static void main(String args[]){
		
		//Run a new gradebook
		//
		new Gradebook();
		
	}
	
	
	//New Gradebook constructor
	//
	public Gradebook(){

		//Create the frame, with resolution 1024x768
		//
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
		manageStudents.inactiveActionListener(new IncludeInactiveStudents());
		
		
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
		manageCourses.showCourseActionListener(new LoadCourseInfo());
		manageCourses.modCourseActionListener(new ModifyCourse());
		manageCourses.modAssignmentActionListener(new ModifyAssignment());
		
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
		//
		this.add(applicationPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
		
		//Initialize each of the list models
		//
		studentList = new DefaultListModel<Student>();
		courseList = new DefaultListModel<Course>();
		classList = new DefaultListModel<CourseSection>();
		assignmentList = new DefaultListModel<Assignment>();
		assignmentTypeList = new DefaultListModel<AssignmentType>();
		
		
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
			
			//get the list of students from the database and populate the studentList
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
			
			//Clear the course list
			//
			courseList.removeAllElements();
			
			//get the list of students from the database and repopulate the studentList
			//
			loadCoursesFromDB();
			
			//Update the student list window
			manageCourses.setCourseList(courseList);
			
		}
	};
	

	//Show the enter grades frame
	//
	class ShowGradesCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, ENTERGRADES);
		}
	};
	
	
	//Show the generate reports frame
	//
	class GenerateReportsCard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, REPORTS);
		}
	};
	
	
	//Load student info from the list selection into the 
	//	student detail pane on the right side
	//
	class LoadStudentInfo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//get currently selected member from the manage students panel
			//
			int currentSelectedStudent = manageStudents.getSelectedStudentIndex();
			selectedStudent = studentList.getElementAt(currentSelectedStudent);
			
			
			//Connect to the database to load the rest of the student's info
			//	into the selectedStudent variable
			//
			try{
				Connection c = null;
				Statement stmt = null;
				
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
				
				
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
				
			}
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
			
			//Update the detail pane with the currently selected student's information
			//
			manageStudents.displayStudentInfo(selectedStudent);
			
		}
	};
	
	
	
	class LoadCourseInfo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//get currently selected member from the manage students panel
			//
			int currentSelectedCourse = manageCourses.getSelectedCourseIndex();
			selectedCourse = courseList.getElementAt(currentSelectedCourse);
			
			
			//Connect to the database to load the rest of the student's info
			//	into the selectedStudent variable
			//
			try{
				Connection c = null;
				Statement stmt = null;
				
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
				
				
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM course WHERE "
											+ "course_num = '" + selectedCourse.getNUM() + "';");
			
				while(rs.next()){
					selectedCourse = new Course(rs.getString("course_num"), 
											rs.getString("name"), 
											rs.getString("description"),
											rs.getString("max_stu"),
											rs.getString("day"));
				
				}
				
				
				rs.close();
				stmt.close();
				c.close();
				
			}
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
			
			//Update the detail pane with the currently selected student's information
			//
			manageCourses.displayCourseInfo(selectedCourse);
			loadAssignmentsForCourse();
			
			
		}
	}
	
	
	
	//ActionListener for the include deleted students checkbox 
	//	on the manage students page
	//
	class IncludeInactiveStudents implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Clear the student list
			//
			studentList.removeAllElements();
			
			//If the checkbox is selected, run the method that loads ALL
			//  If the checkbox is unchecked, run the method that only loads the 
			//	students marked as active
			//
			if(manageStudents.includeInactive()){
				loadAllStudentsFromDB();
			}
			
			else{
				loadStudentsFromDB();
			}
			
			//Update the student list window
			manageStudents.setStudentList(studentList);
			
		}
	}


	//Launch a popup window that contains the currently selected
	//	student's information for editing
	//
	class SubmitStudentChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			try{
				Connection c = null;
				Statement stmt = null;
				
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
				
				
				stmt = c.createStatement();
				
				//get currently selected member from the manage students panel
				//
				int currentSelectedStudent = manageStudents.getSelectedStudentIndex();
				selectedStudent = studentList.getElementAt(currentSelectedStudent);
				
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
				
				
				//Update the detail page to show the currently selected
				//	student detail
				//
				manageStudents.displayStudentInfo(selectedStudent);
				
				
				//Launch a new popup window that contains the currently
				//	selected student's information
				//
				editStudentPopup = new EditStudentPopup(selectedStudent);
				editStudentPopup.submitActionListener(new EditSubmitChanges());
				
						
			}
			
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
			
			
		}
	};
	
	
	//Submit changes button on the edit student popup window
	//
	class EditSubmitChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Prompt the user for confirmation
			//
			if(confirmationDialog("Are you sure you want to modify the student record?\nThis will activate inactive students")){
				
				
				try{
					Connection c = null;
					Statement stmt = null;
						
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
							
					stmt = c.createStatement();
					
					
					//Create a new (temporary) student with the information from
					//	the edit window
					//
					Student modifyStudent = editStudentPopup.newStudent();
					
					
					//Update the student in the database with all of the new information
					//	the id_num is used for matching the student being edited
					//	with the student in the db
					//
					stmt.executeUpdate("UPDATE student SET " +
										"first_name = '" + modifyStudent.getFirst() + "', " +
										"last_name = '" + modifyStudent.getLast() + "', " +
										"address = '" + modifyStudent.getAddress() + "', " +
										"city = '" + modifyStudent.getCity() + "', " +
										"state = '" + modifyStudent.getState() + "', " +
										"zip = '" + modifyStudent.getZip() + "', " +
										"dob = '" + modifyStudent.getDOB() + "', " +
										"active = 1 " +
										"WHERE id_num = '" + modifyStudent.getID() + "';");	
					
					stmt.close();
					c.close();
				
					//Clear the student list
					//
					studentList.removeAllElements();
					
					//get the list of students from the database and re-populate the studentList
					//
					loadStudentsFromDB();
					
					//Update the student list window
					manageStudents.setStudentList(studentList);
					
				}
				
				
				catch(Exception e1)
				{
					System.err.println(e1);
				}		
			}
			
			else{
				
				
			}
			
			editStudentPopup.dispose();
		}
	}
	
	
	//Show the welcome screen/home page
	//
	class ShowWelcomeScreen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			//Get the cardlayout from the applicationPanel, and 
			//	show the correct frame
			//
			CardLayout cl = (CardLayout)applicationPanel.getLayout();
			cl.show(applicationPanel, WELCOME);
			
			manageStudents.resetDisplay();
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
			
			if(confirmationDialog("Are you sure you want to add this student?")){
				//Connect to the database
				try{
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
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
	}
	
	
	class DeleteStudent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			try{
			
				//get currently selected member from the manage students panel
				//
				int currentSelectedStudent = manageStudents.getSelectedStudentIndex();
				
				selectedStudent = studentList.getElementAt(currentSelectedStudent);
				
				manageStudents.displayStudentInfo(selectedStudent);
				
				
				//Present popup window, and if the user selects yes, remove the currently
				//	selected member from the db
				//
				if(confirmationDialog("Are you sure you want to delete this student?")){
				
					Connection c = null;
					Statement stmt = null;
					
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
					
					
					stmt = c.createStatement();
					stmt.executeUpdate("UPDATE student SET active = 0 WHERE id_num = '" + 
										selectedStudent.getID() + "';");	
				
					stmt.close();
					c.close();
			
					//Clear the student list
					//
					studentList.removeAllElements();
				
					//get the list of students from the database and re-populate the studentList
					//
					loadStudentsFromDB();
				
					//Update the student list window
					manageStudents.setStudentList(studentList);
				
				}
			}
			
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
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
			
try{
				
				//get currently selected member from the manage students panel
				//
				int currentSelectedCourse = manageCourses.getSelectedCourseIndex();
				
				Course selectedCourse = courseList.getElementAt(currentSelectedCourse);
				
				
				
				//Present popup window, and if the user selects yes, remove the currently
				//	selected member from the db
				//
				if(confirmationDialog("Are you sure you want to delete this assignment?")){
				
					Connection c = null;
					Statement stmt = null;
					
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
					
					
					stmt = c.createStatement();
					stmt.executeUpdate("UPDATE course SET active = 0 "
										+ "WHERE course_num = '" + selectedCourse.getNUM() + "';");
	
				
					stmt.close();
					c.close();
			
					//Clear the student list
					//
					courseList.removeAllElements();
				
					//get the list of students from the database and re-populate the studentList
					//
					loadCoursesFromDB();
				
					//Update the student list window
					manageCourses.resetDisplay();
					manageCourses.setCourseList(courseList);
					
				
				}
			}
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
			
			
		}
	}
	
	
	class AddCourse implements ActionListener{
		public void actionPerformed(ActionEvent e){
			newCoursePopup = new NewCoursePopup();
			newCoursePopup.submitActionListener(new SubmitNewCourse());
		}
	}
	
	class SubmitNewCourse implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(confirmationDialog("Are you sure you wish to create this new course?")){
				
				Course newCourse = newCoursePopup.newCourse();
				
				Connection c = null;
				Statement stmt = null;
				
				
				try{
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
					stmt = c.createStatement();
		
				
					//Insert into the database the new student information
					//
					stmt.executeUpdate("INSERT INTO course (course_num, name, description, max_stu, day) VALUES ('"
							+ newCourse.getNUM() + "', '"
							+ newCourse.getName() + "', '"
							+ newCourse.getDesc() + "', '"
							+ newCourse.getMax() + "', '"
							+ newCourse.getDay() + "');");
				
			
					stmt.close();
					c.close();
				
					newCoursePopup.dispose();
					courseList.removeAllElements();
					loadCoursesFromDB();
					manageCourses.setCourseList(courseList);
				}
			
				catch(Exception e2){
					System.err.println(e2);
				
				}
				
				
				
			}
		}
	}
	
	class DeleteAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				
				//get currently selected member from the manage students panel
				//
				int currentSelectedAssignment = manageCourses.getSelectedAssignmentIndex();
				
				Assignment selectedAssignment = assignmentList.getElementAt(currentSelectedAssignment);
				
				
				
				//Present popup window, and if the user selects yes, remove the currently
				//	selected member from the db
				//
				if(confirmationDialog("Are you sure you want to delete this assignment?")){
				
					Connection c = null;
					Statement stmt = null;
					
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
					
					
					stmt = c.createStatement();
					stmt.executeUpdate("UPDATE assignment SET active = 0 "
										+ "WHERE name = '" + selectedAssignment.getName() + "'"
										+ " AND course = '" + selectedAssignment.getCourse() +  "';");	
				
					stmt.close();
					c.close();
			
					//Clear the student list
					//
					assignmentList.removeAllElements();
				
					//get the list of students from the database and re-populate the studentList
					//
					loadAssignmentsForCourse();
				
					//Update the student list window
					manageCourses.setAssignmentList(assignmentList);
				
				}
			}
			
			catch(Exception e1)
			{
				System.err.println(e1);
			}
		}
	}
	
	
	class AddAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			loadAssignmentTypes();
			newAssignmentPopup = new NewAssignmentPopup(courseList, assignmentTypeList);
			newAssignmentPopup.submitActionListener(new SubmitAssignment());
		}
	}
	
	class SubmitAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Get a new student object from the popup window
			//
			Assignment newAssignment = newAssignmentPopup.newAssignment();
			
			Connection c = null;
			Statement stmt = null;
			
			if(confirmationDialog("Are you sure you want to create this assignment?")){
				//Connect to the database
				try{
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
					stmt = c.createStatement();
		
				
					//Insert into the database the new student information
					//
					stmt.executeUpdate("INSERT INTO assignment (name, course, type_num, total_points, description) VALUES ('"
							+ newAssignment.getName() + "', '"
							+ newAssignment.getCourse() + "', '"
							+ newAssignment.getType() + "', '"
							+ newAssignment.getPoints() + "', '"
							+ newAssignment.getDescription() + "');");
				
			
					stmt.close();
					c.close();
				
					//Close the new student popup
					//
					newAssignmentPopup.dispose();
				
					loadAssignmentsForCourse();
					
				}
			
				catch(Exception e2){
					System.err.println(e2);
				
				}
			}
			
			
			
		}
	}
	
	
	class ModifyCourse implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int courseSelected = manageCourses.getSelectedCourseIndex();
			Course currentCourse = courseList.getElementAt(courseSelected);
			
			
			editCoursePopup = new EditCoursePopup(currentCourse);
			editCoursePopup.submitActionListener(new SubmitCourseChanges());
		}
	}
	
	class SubmitCourseChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Prompt the user for confirmation
			//
			if(confirmationDialog("Are you sure you want to modify the course details?")){
				
				
				try{
					Connection c = null;
					Statement stmt = null;
						
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
							
					stmt = c.createStatement();
					
					
					Course newCourse = editCoursePopup.newCourse();
					
					
					//Update the student in the database with all of the new information
					//	the id_num is used for matching the student being edited
					//	with the student in the db
					//
					stmt.executeUpdate("UPDATE course SET " +
										"name = '" + newCourse.getName() + "', " +
										"description = '" + newCourse.getDesc() + "', " +
										"max_stu = '" + newCourse.getMax() + "', " +
										"day = '" + newCourse.getDay() + "', " +
										"active = 1 " +
										"WHERE course_num = '" + newCourse.getNUM() + "';");	
					
					stmt.close();
					c.close();
				
					//Clear the student list
					//
					courseList.removeAllElements();
					
					//get the list of students from the database and re-populate the studentList
					//
					loadCoursesFromDB();
					
					//Update the student list window
					manageCourses.setCourseList(courseList);
					manageCourses.resetDisplay();
					
					editCoursePopup.dispose();
					
				}
				
				
				catch(Exception e1)
				{
					System.err.println(e1);
				}		
			}
		}
	}
	
	class ModifyAssignment implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int assignmentSelected = manageCourses.getSelectedAssignmentIndex();
			Assignment currentAssignment = assignmentList.getElementAt(assignmentSelected);
			
			editAssignmentPopup = new EditAssignmentPopup(courseList, assignmentTypeList, currentAssignment);
			editAssignmentPopup.submitActionListener(new SubmitAssignmentChanges());
		}
	}
	
	class SubmitAssignmentChanges implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Prompt the user for confirmation
			//
			if(confirmationDialog("Are you sure you want to modify the course details?")){
				
				Assignment newAssignment = editAssignmentPopup.newAssignment();
				
				try{
					Connection c = null;
					Statement stmt = null;
						
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
							
					stmt = c.createStatement();
					
					
					
					
					
					//Update the student in the database with all of the new information
					//	the id_num is used for matching the student being edited
					//	with the student in the db
					//
					stmt.executeUpdate("UPDATE assignment SET " +
										"total_points = '" + newAssignment.getPoints() + "', " +
										"description = '" + newAssignment.getDescription() + "', " +
										"active = 1 " +
										"WHERE name = '" + newAssignment.getName() + "' AND " +
										"assignment_id = '" + newAssignment.getID() + "' AND " +
										"course = '" + newAssignment.getCourse() + "';");	
					
					stmt.close();
					c.close();
				
					//Clear the student list
					//
					assignmentList.removeAllElements();
					
								
					//Update the student list window
					manageCourses.setAssignmentList(assignmentList);
					manageCourses.resetDisplay();
					
					editAssignmentPopup.dispose();
					
				}
				
				
				catch(Exception e1)
				{
					System.err.println(e1);
					
				}		
			}
		}
	}
	
	
	//Connect to the database and load the student list.  Only include active students
	//	and only fetch their first name, last name, and id number
	//
	public boolean loadStudentsFromDB(){
		
		try{
			Connection c = null;
			Statement stmt = null;
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
			
			
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
			
			manageStudents.setInactiveCheckbox(false);
			
			return true;
		}
		
		catch(Exception e){
			
			System.err.println(e);
			
			return false;
		}
		
	}
	
	//Connect to the database and load the student list.  Only include active students
	//	and only fetch their first name, last name, and id number
	//
	public boolean loadAllStudentsFromDB(){
			
		try{
			Connection c = null;
			Statement stmt = null;
				
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
				
				
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_num, last_name, first_name, active FROM student ORDER BY last_name asc;");
			
			while(rs.next()){
				studentList.addElement(
						new Student(Integer.parseInt(rs.getString("id_num")), 
											rs.getString("first_name"), 
											rs.getString("last_name")));
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
	
	
	public boolean loadCoursesFromDB(){
		
		try{
			Connection c = null;
			Statement stmt = null;
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM course;");
		
			while(rs.next()){
				if(Integer.parseInt(rs.getString("active")) == 1){
				courseList.addElement(
						new Course(rs.getString("course_num"),
									rs.getString("name"),
									rs.getString("description"),
									rs.getString("max_stu"),
									rs.getString("day")));
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
	
	//Create a popup window with the message in it.  If the user selects
	//	yes, return true, if they select no return false
	//
	public boolean confirmationDialog(String message){
		
		int decision = JOptionPane.showConfirmDialog(this.getContentPane(), message, "Confirm", JOptionPane.YES_NO_OPTION);
		
		if(decision == 1){
			return false;
		}
		
		else
			return true;
		
	}
	
	
	//Load the assignments from the database for the currently selected course
	//
	public boolean loadAssignmentsForCourse(){
		
		assignmentList.removeAllElements();
			
		try{
			Connection c = null;
			Statement stmt = null;
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
			
			
			
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT assignment_id, name, course, type_num, total_points, assignment.description, assignment.active "
							+ "FROM assignment, assignment_type " 
							+ "WHERE assignment.course = '"
							+ selectedCourse.getNUM() + "' "
							+ "AND assignment.type_num = assignment_type.num;");
		
			
			while(rs.next()){
				if(Integer.parseInt(rs.getString("active")) == 1){
					assignmentList.addElement(
						new Assignment(	Integer.parseInt(rs.getString("assignment_id")),
										rs.getString("name"),
										rs.getString("course"),
										rs.getString("description"),
										Integer.parseInt(rs.getString("type_num")),
										Integer.parseInt(rs.getString("total_points"))));
				}
			}
			
					
			rs.close();
			stmt.close();
			c.close();
			
			manageCourses.setAssignmentList(assignmentList);
			
			return true;
		}
		
		catch(Exception e){
			
			System.err.println(e);
			
			return false;
		}
		
	}
	
public boolean loadAssignmentTypes(){
		
		assignmentTypeList.removeAllElements();
			
		try{
			Connection c = null;
			Statement stmt = null;
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:res/Capstone");
			
			
			
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM assignment_type;");
		
			
						
			while(rs.next()){
				assignmentTypeList.addElement(
						new AssignmentType(Integer.parseInt(rs.getString("num")),
										rs.getString("description")));
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
