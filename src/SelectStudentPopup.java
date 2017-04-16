import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SelectStudentPopup extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	
	private JButton btnSubmit;
	
	private JList<Student> students;
	
	private JScrollPane studentListPane;
		
	public SelectStudentPopup(DefaultListModel<Student> studentList){
		mainPanel = new JPanel();
		
				
		btnSubmit = new JButton("Add student");
		
		mainPanel.setLayout(new GridLayout(0,2));
		
		students = new JList<Student>();
		students.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		students.setModel(studentList);
		
		studentListPane = new JScrollPane();
		
		
		studentListPane.setViewportView(students);
		
		mainPanel.add(studentListPane);
		
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Add Student to Class");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	public int getSelectedStudentIndex(){
		return students.getSelectedIndex();
	}
	
}
