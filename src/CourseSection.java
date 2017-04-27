//Class used for creating a single course section
//
public class CourseSection {
	private int section_number;
	private String course_num;
	
	public CourseSection(int section, String course){
		section_number = section;
		course_num = new String(course);
	}

	//Public getter methods
	//
	public int getSection(){
		return section_number;
	}
	
	public String getCourse(){
		return course_num;
	}
	
	public String toString(){
		return new String("Class number: " + section_number + " in course " + course_num);
	}
}
