
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
}
