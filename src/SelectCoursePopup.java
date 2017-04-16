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

public class SelectCoursePopup extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	
	private JButton btnSubmit;
	
	private JList<Course> courses;
	
	private JScrollPane courseListPane;
		
	public SelectCoursePopup(DefaultListModel<Course> courseList){
		mainPanel = new JPanel();
		
				
		btnSubmit = new JButton("Add class");
		
		mainPanel.setLayout(new GridLayout(0,2));
		
		courses = new JList<Course>();
		courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courses.setModel(courseList);
		
		courseListPane = new JScrollPane();
		
		
		courseListPane.setViewportView(courses);
		
		mainPanel.add(courseListPane);
		
		this.setBounds(250, 250, 640, 480);
		this.setTitle("Add new Class");
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnSubmit, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void submitActionListener(ActionListener sal){
		btnSubmit.addActionListener(sal);
	}
	
	public int getSelectedCourseIndex(){
		return courses.getSelectedIndex();
	}
}
