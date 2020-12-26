//class student
public class Student {

	String name;

	public Student() {
		this.name = "unknown";
	}
	
	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
        return "Student: " + name;
               
    }
}//end of class