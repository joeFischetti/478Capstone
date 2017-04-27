//This is a class for the course object
//

public class Course{

	private String course_num, name, description, day;
	private int max_students;
		
	public Course(String num){
		course_num = new String(num);
	}
		
	public Course(String num, String newName, String newDescription, String max, String newDay){
		course_num = new String(num);
		name = new String(newName);
		description = new String(newDescription);
		day = new String(newDay);
		max_students = Integer.parseInt(max);
	}
		
	
	//Getter and setter methods
	public String toString(){
		return course_num;
	}

	public String getNUM(){
		return course_num;
	}
	
	public String getName(){
		return name;
	}
		
	public String getDesc(){
		return description;
	}
		
	public String getDay(){
		return day;
	}
		
	public int getMax(){
		return max_students;
	}
}
