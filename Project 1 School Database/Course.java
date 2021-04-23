

public class Course implements Comparable<Course>{
	
	// Class variables
	private boolean isGraduateCourse;
	private int courseNum;
	private String courseDept;
	private int numCredits;
	
	// Constructor
	public Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits) {
		this.isGraduateCourse = isGraduateCourse;
		this.courseNum = courseNum;
		this.courseDept = courseDept;
		this.numCredits = numCredits;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Accessors
//////////////////////////////////////////////////////////////////////////////////////////////
	public boolean isGraduateCourse() {
		return this.isGraduateCourse;
	}
	public int getCourseNum() {
		return this.courseNum;
	}
	public String getCourseDept() {
		return this.courseDept;
	}
	public int getNumCredits() {
		return this.numCredits;
	}
	public String getCourseName() {
		String s = "";
		if(isGraduateCourse()) {
			s += "G" + getCourseDept() + getCourseNum();
		}
		else { s+="U" + getCourseDept() + getCourseNum();}
		return s;
	}
	
	// Overridden Methods
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {return false;}
		if(this == obj) {return true;}
		if(obj instanceof Course) {
			Course otherC = (Course)obj;
			if(isGraduateCourse == otherC.isGraduateCourse()) {
				if(courseDept.equalsIgnoreCase(otherC.getCourseDept())) {
					if(courseNum == otherC.getCourseNum()) {
						if(numCredits == otherC.numCredits) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String isGraduateCourse = "";
		if(this.isGraduateCourse) {
			isGraduateCourse = "Graduate";
		}
		else {
			isGraduateCourse = "Undergraduate";
		}
		String 	s = String.format("Course: %3s-%3d | Number of Credits: %02d | %s" , courseDept, courseNum, numCredits, isGraduateCourse);
		return s;
	}

	@Override
	public int compareTo(Course c) {
		return this.getCourseNum() > c.getCourseNum() ? 1 : this.getCourseNum() < c.getCourseNum() ? -1 : 0;
	}
}
