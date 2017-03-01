
public class Student {

	private int id_num, zip;
	private String firstName, lastName, address, city,  state, dob;
	
	
	public Student(){
		
		
		
	}
	
	public Student(int id, String first, String last){
		
		id_num = id;
		firstName = new String(first);
		lastName = new String(last);
		
	}
	
	public Student(int id, String first, String last, String newAddress, String newCity, String newState, String newZip, String newDob){
		id_num = id;
		firstName = first;
		lastName = last;
		address = newAddress;
		city = newCity;
		state = newState;
		dob = newDob;
		zip = Integer.parseInt(newZip);
	}
	
	
	public String toString(){
		return new String(lastName + ", " + firstName);
	}
	
	public int getID(){
		return id_num;

	}
	
	public String getFirst(){
		return firstName;
	}
	
	public String getLast(){
		return lastName;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public int getZip(){
		return zip;
	}
	
	public String getDOB(){
		return dob;
	}
	
}
