import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class EditStudentPopup extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, leftPanel, rightPanel;
	
	private JButton btnSubmit;
	
	private JTextField txtFirstName, txtLastName, txtAddress, txtCity, txtState, txtZip, txtDOB;
	
	private Student student;
	
	public EditStudentPopup(Student existingStudent){
		
		student = existingStudent;
		
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
				
		txtFirstName = new JTextField(student.getFirst());
		txtLastName = new JTextField(student.getLast());
		txtAddress = new JTextField(student.getAddress());
		txtCity = new JTextField(student.getCity());
		txtState = new JTextField(student.getState());
		txtZip = new JTextField(student.getZip() + "");
		txtDOB = new JTextField(student.getDOB());
		
		btnSubmit = new JButton("Submit Changes to student");
		
		leftPanel.setLayout(new GridLayout(0,1));
		leftPanel.add(new JLabel("First Name:"));
		leftPanel.add(new JLabel("Last Name:"));
		leftPanel.add(new JLabel("Address:"));
		leftPanel.add(new JLabel("City:"));
		leftPanel.add(new JLabel("State:"));
		leftPanel.add(new JLabel("Zip:"));
		leftPanel.add(new JLabel("Date of Birth:"));
		
		rightPanel.setLayout(new GridLayout(0,1));
		rightPanel.add(txtFirstName);
		rightPanel.add(txtLastName);
		rightPanel.add(txtAddress);
		rightPanel.add(txtCity);
		rightPanel.add(txtState);
		rightPanel.add(txtZip);
		rightPanel.add(txtDOB);
		
		mainPanel.setLayout(new GridLayout(0,2));
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Edit Existing Student");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	
	//Create a new student from the provided information with a matching id_num
	//	from the student provided
	//
	public Student newStudent(){
		return new Student(student.getID(), txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(), txtCity.getText(),
				txtState.getText(), txtZip.getText(), txtDOB.getText());
	}
}
