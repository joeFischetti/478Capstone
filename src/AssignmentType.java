//AssignmentType class
// This class is simply a linker class that joins the assignment to the assignment type
//
public class AssignmentType {
	private int number;
	private String description;
	
	public AssignmentType(int num, String desc){
		number = num;
		description = new String(desc);
	}
	
	public String toString(){
		return description;
	}
	
	public int numer(){
		return number;
	}
}
