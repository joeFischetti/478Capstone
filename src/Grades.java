
public class Grades {
	private String assignmentName, letterGrade;
	private int assignmentNum;
	private float points, totalPoints, percentage;
	
	public Grades(int assignmentNumber, String newName, float newPoints, float newTotal){
		assignmentNum = assignmentNumber;
		assignmentName = new String(newName);
		points = newPoints;
		totalPoints = newTotal;
		percentage = points / totalPoints * 100;
		
		//if/else statement sets the letter grade based on the number grade
		//
		if(percentage > 92)
			letterGrade = new String("A");
		else if(percentage > 90)
			letterGrade = new String("A-");
		else if(percentage > 87)
			letterGrade = new String("B+");
		else if(percentage > 82)
			letterGrade = new String("B");
		else if(percentage > 80)
			letterGrade = new String("B-");
		else if(percentage > 77)
			letterGrade = new String("C+");
		else if(percentage > 72)
			letterGrade = new String("C");
		else if(percentage > 70)
			letterGrade = new String("C-");
		else if(percentage > 65)
			letterGrade = new String("D");
		else
			letterGrade = new String("F");	
	}
	
	//Return an array of data for the grade
	//
	public String[] getInfo(){
		return new String[]{assignmentName, points + "", totalPoints + "", percentage + "", letterGrade};
	}
	
	//Get the number of the assignment that this grade belongs to
	//
	public int getNum(){
		return assignmentNum;	
	}
	
	//Get the number of points that the student got on the assignment
	//
	public Float getPoints(){
		return points;
	}
}
