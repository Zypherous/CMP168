
import java.util.Arrays;

public class Faculty extends Employee{
	
	// Declaration of class variables
	private Course[] coursesTaught;
	private int numCoursesTaught;
	private boolean isTenured;
	
	// Constructors for class
	public Faculty() {
		super();
		coursesTaught = new Course[100];
		numCoursesTaught = 0;
		isTenured = false;
	}
	// Single parameter constructor
	public Faculty(boolean isTenured) {
		super();
		coursesTaught = new Course[100];
		numCoursesTaught = 0;
		this.isTenured = isTenured;
	}
	// Two parameters constructor
	public Faculty(String deptName, boolean isTenured) {
		super(deptName);
		coursesTaught = new Course[100];
		numCoursesTaught = 0;
		this.isTenured = isTenured;
	}
	// Fully overloaded constructor
	public Faculty(String name, int birthYear, String deptName, boolean isTenured) {
		super(name, birthYear, deptName);
		coursesTaught = new Course[100];
		numCoursesTaught = 0;
		this.isTenured = isTenured;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Accessors
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isTenured() {
		return isTenured;
	}
	public int getNumCoursesTaught() {
		return numCoursesTaught;
	}
	
	public void setIsTenured(boolean isTenured) {
		this.isTenured = isTenured;
	}
	public Course getCourseTaught(int index) {
		if(this.numCoursesTaught == 0 || index >= this.getNumCoursesTaught() || index < 0/*coursesTaught[index] == null*/ ) {
			return null;
		}
		return this.coursesTaught[index];
	}
	public String getCourseTaughtAsString(int index) {
		String s = null;
		if(index <= this.numCoursesTaught && this.coursesTaught[index] != null) {
			s = this.coursesTaught[index].getCourseDept() +"-" + this.coursesTaught[index].getCourseNum();
		}else {
			s = "";
		}
		return s;
	}
	public String getAllCoursesTaughtAsString() {
		String s = "";
		for(int i = 0; i < this.getNumCoursesTaught();i++) {
//			s = this.coursesTaught[i].getCourseDept() +"-" + this.coursesTaught[i].getCourseNum();
			if(i != this.getNumCoursesTaught() -1) {
				s += this.getCourseTaughtAsString(i) + ", ";
				}
			else { s+= this.getCourseTaughtAsString(i);}
		}
		return s;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Mutators
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public void addCourseTaught(Course course) {
		if(course != null) {
			if (this.getNumCoursesTaught() < this.coursesTaught.length) {
				this.coursesTaught[this.numCoursesTaught++] = course;
			}
		}
	}
	public void addCoursesTaught(Course [] course) {
		for(int i = 0; i < course.length; i++) {
			this.addCourseTaught(course[i]);
		}
	}
	
	// Overridden Methods
	@Override
	public boolean equals(Object obj) {
		if(obj == null){ return false; }
		if(this == obj){ return true; }
		if(obj instanceof Faculty) {
			Faculty otherF = (Faculty)obj;
			if(super.equals(otherF)) {
				if(this.isTenured == otherF.isTenured) {
					if( Arrays.equals(coursesTaught, this.coursesTaught)/*this.coursesTaught == otherF.coursesTaught*/) {
						if(this.numCoursesTaught == otherF.numCoursesTaught) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	///TODO 
	// CHECK ORDER OF SORT
	@Override
	public int compareTo(Person p) {
		int parentVal = super.compareTo(p);
		if(p instanceof Faculty){
			if(this.getNumCoursesTaught() > ((Faculty)p).getNumCoursesTaught()){
				return 1;
			}
			else if(this.getNumCoursesTaught() < ((Faculty)p).getNumCoursesTaught()){
					return -1;
			}
			else {
				return 0;
			}
		}
		else {
			return parentVal;
		}
	}
	
	@Override
	public String toString() {
		String s, tenuredOrNot;
		if(this.isTenured()) {
			tenuredOrNot = "Is Tenured";
		}else {tenuredOrNot = "Not Tenured";}
		s = super.toString() + String.format(" Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s",
				tenuredOrNot, numCoursesTaught, getAllCoursesTaughtAsString());
		return s;
	}
}
