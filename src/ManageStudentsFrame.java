import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ManageStudentsFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, leftPanel, leftContentPanel, rightPanel, rightContentPanel, leftButtonPanel, rightButtonPanel,
					homeButtonPanel;
	
	private JTextField 	txtFirstname, txtLastname, txtId, 
						txtCity, txtState, txtZip, txtAddress, txtDob;
	
	private JList<Student> list;
	
	private JCheckBox includeInactive;
	
	private JButton btnLoadStudentInfo, btnSubmitAboveChanges, btnCancelToHome, btnNewStudent, btnDeleteStudent;


	public ManageStudentsFrame() {
		
		//Create each of the panels
		//	names should be self explanatory.
		//
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		leftContentPanel = new JPanel();
		leftButtonPanel = new JPanel();
		rightPanel = new JPanel();
		rightContentPanel = new JPanel();
		rightButtonPanel = new JPanel();
		homeButtonPanel = new JPanel();
		
		
		//Create the labels for each of the different pieces of student data
		//
		JLabel lblIdNumber = new JLabel("ID number");
		JLabel lblFirstName = new JLabel("First Name");
		JLabel lblLastName = new JLabel("Last Name");
		JLabel lblAddress = new JLabel("Address");
		JLabel lblCity = new JLabel("City");
		JLabel lblState = new JLabel("State");
		JLabel lblZip = new JLabel("Zip");
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		
		
		//Create each of the JText fields for different
		//	pieces of student data
		//
		txtId = new JTextField();
		txtFirstname = new JTextField();
		txtLastname = new JTextField();
		txtAddress = new JTextField();
		txtCity = new JTextField();
		txtState = new JTextField();
		txtZip = new JTextField();
		txtDob = new JTextField();
		
		includeInactive = new JCheckBox("Show Inactive");
		includeInactive.setSelected(false);
		
		//Set each panel's layout
		//
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		leftContentPanel.setLayout(new BorderLayout(0, 0));
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		rightContentPanel.setLayout(new GridLayout(8, 2, 0, 0));
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		
		//Create the buttons that are used on the panel.
		//
		btnLoadStudentInfo = new JButton("Show Student Info >>>");
		btnSubmitAboveChanges = new JButton("Modify Student info");
		btnCancelToHome = new JButton("Home Screen");
		btnNewStudent = new JButton("Add New Student");
		btnDeleteStudent = new JButton("Deactivate Selected Student");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		list = new JList<Student>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list);
		

		//Initialize the text fields with placeholder info
		//
		txtId.setText("ID");
		txtId.setColumns(10);
	
		txtFirstname.setText("first_name");
		txtFirstname.setColumns(10);
		
		txtLastname.setText("last_name");
		txtLastname.setColumns(10);
		
		txtAddress.setText("address");
		txtAddress.setColumns(10);
		
		txtCity.setText("city");
		txtCity.setColumns(10);
		
		txtState.setText("state");
		txtState.setColumns(10);
		
		txtZip.setText("zip");
		txtZip.setColumns(10);
		
		txtDob.setText("DOB");
		txtDob.setColumns(10);
		
		
		//Populate each of the panels
		//
		leftContentPanel.add(scrollPane_1);
		
		leftButtonPanel.add(includeInactive);
		leftButtonPanel.add(btnNewStudent);
		leftButtonPanel.add(btnLoadStudentInfo);
		
		
		
		rightContentPanel.add(lblIdNumber);
		rightContentPanel.add(txtId);
		rightContentPanel.add(lblFirstName);
		rightContentPanel.add(txtFirstname);
		rightContentPanel.add(lblLastName);
		rightContentPanel.add(txtLastname);
		rightContentPanel.add(lblAddress);
		rightContentPanel.add(txtAddress);
		rightContentPanel.add(lblCity);
		rightContentPanel.add(txtCity);
		rightContentPanel.add(lblState);
		rightContentPanel.add(txtState);
		rightContentPanel.add(lblZip);
		rightContentPanel.add(txtZip);
		rightContentPanel.add(lblDateOfBirth);
		rightContentPanel.add(txtDob);
		
		rightButtonPanel.add(btnSubmitAboveChanges);
		rightButtonPanel.add(btnDeleteStudent);
		
		leftPanel.add(leftContentPanel, BorderLayout.CENTER);
		leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);
		
		
		rightPanel.add(rightContentPanel, BorderLayout.CENTER);
		rightPanel.add(rightButtonPanel, BorderLayout.SOUTH);
		
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		homeButtonPanel.add(btnCancelToHome);
		
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(homeButtonPanel, BorderLayout.SOUTH);
	}
	
	
	public void loadInfoActionListener(ActionListener lal){
		btnLoadStudentInfo.addActionListener(lal);
	}
	
	public void submitChangesActionListener(ActionListener sal){
		btnSubmitAboveChanges.addActionListener(sal);
	}
	
	public void homeButtonActionListener(ActionListener hal){
		btnCancelToHome.addActionListener(hal);
	}
	
	public void newStudentActionListener(ActionListener nsal){
		btnNewStudent.addActionListener(nsal);
	}
	
	public void deleteStudentActionListener(ActionListener dsal){
		btnDeleteStudent.addActionListener(dsal);
	}
	
	public void setStudentList(DefaultListModel<Student> students){
		list.setModel(students);
		
	}
	
	public void inactiveActionListener(ActionListener ial){
		includeInactive.addActionListener(ial);
	}
	
	public int getSelectedStudentIndex(){
		return list.getSelectedIndex();
	}
	
	public void displayStudentInfo(Student selected){
		
		txtId.setText(selected.getID() + "");
		txtFirstname.setText(selected.getFirst());		
		txtLastname.setText(selected.getLast());
		txtAddress.setText(selected.getAddress());
		txtCity.setText(selected.getCity());
		txtState.setText(selected.getState());
		txtZip.setText(selected.getZip() + "");
		txtDob.setText(selected.getDOB());
		
	}
	
	public boolean includeInactive(){
		return includeInactive.isSelected();
	}
	
	public void setInactiveCheckbox(boolean input){
		includeInactive.setSelected(input);
	}
	
	public void resetDisplay(){
		txtId.setText("ID");
		txtFirstname.setText("first_name");
		txtLastname.setText("last_name");
		txtAddress.setText("address");
		txtCity.setText("city");
		txtState.setText("state");
		txtZip.setText("zip");
		txtDob.setText("DOB");
	}
}
