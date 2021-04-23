
/**
 * Student class which is a child of Person class.
 * 
 * @author Jonathan Rosario
 *
 */


public class Student extends Person {

/////////////////////////////////////////////////////////////////	
// Member variables
/////////////////////////////////////////////////////////////////
	
	private static int numStudents = 0;
	private int studentID;
	private Course[] coursesTaken;
	private int numCoursesTaken;
	private boolean isGraduate;
	private String major;
	
	/**
	 * Default constructor for Student. Sets array size to 50 as per ZyBooks specifications,
	 * isGraduate to false, automatically increments static int numStudents and sets id to new
	 * value. Sets Major to undeclared, and numCoursesTaken to 0.
	 */
	public Student() {
		super();
		coursesTaken = new Course[50];
		this.isGraduate = false;
		++numStudents;
		this.studentID = numStudents;
		this.setMajor("undeclared");
		numCoursesTaken = 0;
	}
	
	/**
	 * Constructor for Student. Sets array size to 50 as per ZyBooks specifications,
	 * isGraduate to parameter val, automatically increments static int numStudents and sets id to new
	 * value. Sets Major to undeclared, and numCoursesTaken to 0.
	 * @param isGraduate - Argument that sets isGraduate to true or false upon creation of Student object.
	 */
	public Student(boolean isGraduate) {
		super();
		coursesTaken = new Course[50];
		this.isGraduate = isGraduate;
		++numStudents;
		this.studentID = numStudents;
		this.setMajor("undeclared");
		numCoursesTaken = 0;
	}
	/**
	 * Default constructor for Student. Sets array size to 50 as per ZyBooks specifications,
	 * isGraduate to parameter val, automatically increments static int numStudents and sets id to new
	 * value. Sets Major to major value, and numCoursesTaken to 0.
	 * @param major - String containing value for major when constructing object
	 * @param isGraduate - Argument that sets isGraduate to true or false upon creation of Student object.
	 */
	public Student(String major, boolean isGraduate) {
		super();
		coursesTaken = new Course[50];
		this.isGraduate = isGraduate;
		this.setMajor(major);
		++numStudents;
		this.studentID = numStudents;
		numCoursesTaken = 0;
	}
	
	/**
	 * Fully overloaded constructor for Student. 
	 * @param name - Sets value of student's name to name
	 * @param birthYear - Sets value of student's birth year to birthYear
	 * @param major- Sets value of student's major to major
	 * @param isGraduate - Sets value of student's gradutate status to isGraduate
	 */
	public Student(String name, int birthYear, String major, boolean isGraduate) {
		super(name, birthYear);
		coursesTaken = new Course[50];
		this.isGraduate = isGraduate;
		this.setMajor(major);
		++numStudents;
		this.studentID = numStudents;
		numCoursesTaken = 0;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Accessors
//////////////////////////////////////////////////////////////////////////////////////////////	
	public boolean isGraduate() {
		return this.isGraduate;
	}
	public int getNumCoursesTaken() {
		return this.numCoursesTaken;
	}
	public static int getNumStudents() {
		return numStudents;
	}
	public int getStudentID() {
		return this.studentID;
	}
	public String getMajor() {
			return this.major;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Mutators
//////////////////////////////////////////////////////////////////////////////////////////////
	public void setIsGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void addCourseTaken(Course c) {
		if(c != null) {
			if(this.getNumCoursesTaken() < this.coursesTaken.length) {
				this.coursesTaken[this.numCoursesTaken++] = c;
			}
		}
	}
	public void addCoursesTaken(Course [] courses) {
		for(int i = 0; i < courses.length;i++) {
			this.addCourseTaken(courses[i]);
		}
	}
	
	/**
	 * Checks for valid index before and then returns the course at specified index
	 * @param index - integer representing index of course to be returned
	 * @return An Course object
	 */
	public Course getCourseTaken(int index) {
		if(index < numCoursesTaken && index >= 0) {
			return coursesTaken[index];
		}else {
			return null;
		}
	}
	
	/**
	 * Takes course information and formats it as a string
	 * @param index - Integer containing index of course
	 * @return A String that is formatted for printing out details of Course object
	 */
	public String getCourseTakenAsString(int index) {
		String s = null;
		if(index <= numCoursesTaken && this.coursesTaken[index] != null) {
			s = this.coursesTaken[index].getCourseDept() +"-" + this.coursesTaken[index].getCourseNum();
		}else {
			s = "";
		}
		return s;
	}
	
	/**
	 * Uses getCourseTakenAsString and loops through the array of courses creating a string with all courses taken
	 * @return formatted string with all information from coursesTaken
	 */
	public String getAllCoursesTakenAsString() {
		String s = "";
		for(int i =0;i < numCoursesTaken; i++) {
			if(i != numCoursesTaken -1) {
			s += this.getCourseTakenAsString(i) + ", ";
			}
			else { s+= this.getCourseTakenAsString(i);}
		}
		return s;
	}
	
	/**
	 * Iterates through coursesTaken to sum up credits of all courses
	 * @return An integer value total of all courses taken
	 */
	public int getNumCredits() {
		int credits = 0;
		for(int i = 0; i < this.getNumCoursesTaken();i++) {
			credits = credits + this.coursesTaken[i].getNumCredits() ;
		}
		return credits;
	}
	
	// Overridden implementations
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {return false;}
		if(this == obj) {return true;}
		if(obj instanceof Student) {
			Student otherS = (Student)obj;
			if(super.equals(otherS)) {
				if(this.getStudentID() == otherS.getStudentID()) {
					if(this.getMajor() == otherS.getMajor()){
						if(this.getNumCoursesTaken() == otherS.getNumCoursesTaken()) {
							if(this.isGraduate() == otherS.isGraduate()) {
								for(int i = 0; i < this.getNumCoursesTaken(); i++) {
									if(this.coursesTaken[i] != otherS.coursesTaken[i]) {
										return false;
									}
								}
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String gradOrNo;
		if(this.isGraduate()) {
			gradOrNo = "Graduate";
		}else {
			gradOrNo = "Undergraduate";
		}
		String s = super.toString() + String.format(" Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s",
				studentID, major, gradOrNo, numCoursesTaken, getAllCoursesTakenAsString());
		return s;
	}
	
	//TODO 
	// CHECK ORDER OF SORT
	@Override
	public int compareTo(Person p) {
		int parentVal = super.compareTo(p);
		if(p instanceof Student){
			if(this.getNumCredits() > ((Student)p).getNumCredits()){
				return 1;
			}
			else if(this.getNumCredits() < ((Student)p).getNumCredits()){
					return -1;
			}
			else{
				return 0;
			}
		}
		else {
			return parentVal;
		}
	}
}
