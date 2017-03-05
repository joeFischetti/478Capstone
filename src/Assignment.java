
public class Assignment {
	private String name, course, description;
	private int id, type_num, total_points;
	
	public Assignment(int newId, String newName, String newCourse, String newDescription, int type, int points){
		id = newId;
		name = new String(newName);
		course = new String(newCourse);
		description = new String(newDescription);
		type_num = type;
		total_points = points;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCourse(){
		return course;
	}
	
	public int getType(){
		return type_num;
	}
	
	public int getPoints(){
		return total_points;
	}
	
	public int getID(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	public String toString(){
		return name;
	}
}
