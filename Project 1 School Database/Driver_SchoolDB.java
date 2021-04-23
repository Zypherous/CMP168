/******* YOUTUBE VIDEO LINK*****************\
 *                                          
 * 
 * https://youtu.be/ZiPvgofbjfY
 * 
 * 
 ********************************************/




/** 
 * @author Jonathan Rosario
 * @version 1.0
 * CMP 168 Spring 2021
 * Professor Sofianos
 * 
 * Project 1: School Database - A simple database project with the ability to
 * add in students, faculty, general staff, and courses.
 */


// Packages required for driver code and interactive menu which is currently commented out.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class Driver_SchoolDB {
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Member Variables for Driver_SchoolDB
////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// ArrayLists for storing the various objects. ArrayList was chosen for the built in functions and
	// ease of adding in without need for keeping track of how many elements have already been added in.
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<Faculty> facultyMembers = new ArrayList<Faculty>();
	private static ArrayList<GeneralStaff> generalStaff = new ArrayList<GeneralStaff>();
	private static ArrayList<Student> students = new ArrayList<Student>();
	
	// Variables for array size for scalability
	private static final int STUDENT_COURSE_SIZE = 50;
	private static final int FACULTY_COURSE_SIZE = 100;

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Main method of driver code 
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {

		// Declaring variables for file input and output
		String fileName = "SchoolDB_Initial.txt";
		Scanner inStream = null;
		String s = "";
		int type = 0;
		
		// Attempt to open the stream to begin parsing for information
		try{
		    inStream = new Scanner (new File (fileName));
		    
		    // Looping through file as long as there is a next line
		    while(inStream.hasNextLine()) {
			    s =  inStream.nextLine();
			    System.out.println(s);
			    
			    // Splitting line into a String array
			    String [] toParse = splitIntoArray(s);
			    
			    // See method description below for details on how determinedObject works. 
			    type = determineObject(toParse[0]);
			    
			    // Switch block based on type's value to parse for correct information
			    switch(type) {
			    case 0:
			    	Course c = null;
			    	if(toParse.length>1) {
				    	String [] info = toParse[1].trim().replaceAll(" ","").split(",");
						c = parseCourse(info);
			    	}
			    	courses.add(c);
			    	break;
			    case 1:
			    	Student st = null;
			    	if(toParse.length>1) {
						String[] info = toParse[1].trim()/* .replaceAll(" ","") */.split(",");
			    		st = parseStudent(info);
			    	}
			    	else {
			    		st = new Student();
			    	}
			    	students.add(st);
			    	break;
			    case 2:
			    	Faculty f = null;
			    	if(toParse.length>1) {
						String[] info = toParse[1].trim()./* replaceAll(" ",""). */split(",");
			    		f = parseFaculty(info);
			    	}
			    	else {
			    		f = new Faculty();
			    	}
			    	facultyMembers.add(f);
			    	break;
			    case 3:
			    	GeneralStaff gs = null;
			    	if(toParse.length>1) {
						String[] info = toParse[1].trim()/* .replaceAll(" ","") */.split(",");
			    		gs = parseGeneralStaff(info);
			    	}
			    	else {
			    		gs = new GeneralStaff();
			    	}
			    	generalStaff.add(gs);
			    	break;
			    default:
			    	break;
			    }
		    }
		}
		
		// If file is not found, an exception is thrown so application does not terminate imediately.
		catch(FileNotFoundException e){
		    System.err.println("Could not read from file "+fileName+"\n"+ e.getMessage());
		}
		finally{
		    if(inStream !=null){
		        inStream.close();
		    }           
		}
		System.out.println();
		
		// See method details below.
		printSampleOutput();
		
		// ================================================================================
		// Interactive Menu Code**********************************************************
		// Uncomment the two lines below to use interactive menu
		// code beneath is testing code, you can use it if you like, just put the two lines below, 
		// below the testing code at end of main to make use after uncommenting.
		// ================================================================================
				
				//interactiveMenu();
				
				// Being polite and letting user know the program has terminated
				//System.out.println("Goodbye!");

		
				
				
				
				
				
				
				
				
				
				// ================================================================================
				// Testing Code**********************************************************
				// ================================================================================
		
		/*
		 * // Test Objects for testing my interactive Menu (Prevents the need for
		 * constantly re-entering // values into the arrayLists
		 * 
		 * 
		 * // Creating three GeneralStaff with default constructor, just to populate the
		 * ArrayList GeneralStaff ag = new GeneralStaff(); GeneralStaff bg = new
		 * GeneralStaff(); GeneralStaff cg = new GeneralStaff();
		 * 
		 * generalStaff.add(cg); generalStaff.add(bg); generalStaff.add(ag);
		 * 
		 * //Creating Courses to add to courses ArrayList in order to save time when
		 * testing methods and //debugging. Course ac = new Course(false, 100, "CMP",
		 * 3); Course bc = new Course(false, 200, "CMP", 3); Course cc = new
		 * Course(false, 300, "CMP", 3); courses.add(ac); courses.add(bc);
		 * courses.add(cc);
		 * 
		 * 
		 * // Creating Faculty to add to facultyMembers ArrayList in order to save time
		 * when testing methods and // debugging. Faculty af = new
		 * Faculty("Aob",1934,"Comp Sci",true); Faculty bf = new
		 * Faculty("Bob",1944,"Comp Sci",true); Faculty cf = new
		 * Faculty("Cob",1984,"Comp Sci",true);
		 * 
		 * facultyMembers.add(af); facultyMembers.add(bf); facultyMembers.add(cf);
		 * 
		 * // Creating Students to add to students ArrayList in order to save time when
		 * testing methods and // debugging. Student as = new
		 * Student("Aob Stu",1994,"Comp Sci",true); Student bs = new
		 * Student("Bob Stu",1944,"Comp Sci",true); Student cs = new
		 * Student("Cob Stu",1984,"Comp Sci",true);
		 * 
		 * students.add(bs); students.add(as); students.add(cs);
		 * 
		 * // Adding some courses to a few students for testing purposes.
		 * 
		 * cs.addCourseTaken(bc); cs.addCourseTaken(ac); bs.addCourseTaken(cc);
		 * bs.addCourseTaken(ac); bs.addCourseTaken(bc);
		 * 
		 * 
		 * // Adding some courses to a few Faculty for testing purposes.
		 * af.addCourseTaught(ac); af.addCourseTaught(bc); bf.addCourseTaught(cc);
		 * af.addCourseTaught(cc);
		 * 
		 * // Printing out Student objects in students printStudents();
		 * 
		 * // Using built-in sorting function. Sorts based on the comparaTo implemented
		 * in the classes. Collections.sort(students); Collections.sort(courses);
		 * Collections.sort(facultyMembers); Collections.sort(generalStaff);
		 * 
		 * // Testing getNumCredits then printing students again to determine if the
		 * collections sort worked. System.out.println(bs.getNumCredits());
		 * printStudents();
		 */
	
		
		
	

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Driver Methods 
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Takes a string and splits it on ":" after trimming off trailing whitespace. 
	 * @param - line String of data that needs to be parsed.
	 * @return an array with the object type and data separated.
	 */
	private static String[] splitIntoArray(String line) {
		String[] tokenizedString = line.trim().split(":");
		return tokenizedString;
	}

	
	// ************* NOTE ACTUALLY FORGOT TO USE THIS AND DID IT MANUALLY IN THE CODE BELOW*************
//	/**
//	 * Takes the second value from originally split string and further splits it after trimming 
//	 * the trailing whitespace, if any, then finding all the " " (spaces) and replacing it with "" (no spaces)
//	 * before splitting it on ",".
//	 * @param splitArray - String of data to further be parsed
//	 * @return an array with the data needed to create various objects.
//	 */
//	private static String[] getInfoFromArray(String splitArray) {
//		String[] info = splitArray.trim().replaceAll(" ", "").split(",");
//		return info;
//	}

	/**
	 * Looks at the first value of the initial "splitIntoArray" and determines what kind of object it
	 * is by checking it versus a series of if else statements. 
	 * @param line - String of data containing object type
	 * @return an integer to be used in a switch statement.
	 */
	private static int determineObject(String line) {
		int type = -1;
		String ts[] = splitIntoArray(line);
		if (ts[0].equalsIgnoreCase("Course")) {
			type = 0;
		} else if (ts[0].equalsIgnoreCase("Student")) {
			type = 1;
		} else if (ts[0].equalsIgnoreCase("Faculty")) {
			type = 2;
		} else if (ts[0].equalsIgnoreCase("GeneralStaff")) {
			type = 3;
		} else {
			type = -1;
		}
		return type;
	}

	/**
	 * Takes in parsed data in form of String array then determines values to construct
	 * a Course object. CVS file format is known and it checks the value based on a known
	 * order. Converts String "true" to boolean, parses integers from string, and capitalizes
	 * the course department. Error message prints out if invalid data format which is used for
	 * user entry in interactive portion of program.
	 * @param cts - Array of Strings that contains the information to create a course (boolean, integer, String, integer)
	 * @return An Course object representing course to be created
	 */
	private static Course parseCourse(String[] cts) {
		Course c = null;
		boolean isGraduate = false;
		int courseNum = 0;
		String courseDept = "";
		int numCredits = 0;
		if (cts[0].equalsIgnoreCase("true")) {
			isGraduate = true;
		}
		try {
			courseNum = Integer.parseInt(cts[1]);
			courseDept = cts[2].toUpperCase();
			numCredits = Integer.parseInt(cts[3]);
			c = new Course(isGraduate, courseNum, courseDept, numCredits);
		}catch(NumberFormatException e) {
			System.out.println("Invalid data entry must be: \"g/u,###,LLL,#");
		}
		return c;
	}

	/**
	 * Takes in parsed data in form of String array then determines values to construct
	 * a Faculty object. CVS file format is known and it checks the value based on a known
	 * order. Converts String "true" to boolean, parses integers from string. Checks length
	 * of array to determine the constructor to use.
	 * Error message prints out if invalid data format which is used for
	 * user entry in interactive portion of program.
	 * @param fts - Array of Strings that contains the information to create a Faculty object should be in
	 * lengths of 0(empty), 1(boolean), 2(string,boolean) , or 4(String, integer, string, boolean)
	 * @return Faculty object created with proper arguments
	 */
	private static Faculty parseFaculty(String[] fts) {
		Faculty f = null;
		boolean isTenured = false;
		try {
			switch (fts.length) {
				case 1:
					isTenured = false;
					if (fts[0].equalsIgnoreCase("true")) {
						isTenured = true;
					}
					f = new Faculty(isTenured);
					break;
				case 2:
					if (fts[1].equalsIgnoreCase("true")) {
						isTenured = true;
					}
					f = new Faculty(fts[0], isTenured);
					break;
				case 4:
					if (fts[3].equalsIgnoreCase("true")) {
						isTenured = true;
					}
					int birthYear = Integer.parseInt(fts[1]);
					f = new Faculty(fts[0], birthYear, fts[2], isTenured);
					break;
				default:
					f = new Faculty();
					break;
			}
		}
		catch(NumberFormatException e) {
		System.out.println("Invalid data entry must be"
				+ "\n no argument:    ()"
				+ "\n one argument:   (t)"
				+ "\n two arguments:  (cmp,nt)"
				+ "\n four arguments: (bob,1988,cmp,t)");
		}
		return f;
	}

	/**
	 * Takes in parsed data in form of String array then determines values to construct
	 * a GeneralStaff object. CVS file format is known and it checks the value based on a known
	 * order. Parses integers from string. Checks length of array to determine the constructor to use.
	 * Error message prints out if invalid data format which is used for
	 * user entry in interactive portion of program.
	 * @param fts - Array of Strings that contains the information to create a GeneralStaff object should be in
	 * lengths of 0(empty), 1(String), 2(string,String) , or 4(String, integer, string, String)
	 * @return GeneralStaff object created with proper arguments
	 */
	private static GeneralStaff parseGeneralStaff(String[] gts) {
		GeneralStaff g = null;
		try {
			switch (gts.length) {
			case 1:
				g = new GeneralStaff(gts[0]);
				break;
			case 2:
				g = new GeneralStaff(gts[0], gts[1]);
				break;
			case 4:
				int birthYear = Integer.parseInt(gts[1]);
				g = new GeneralStaff(gts[0], birthYear, gts[2], gts[3]);
				break;
			default:
				g = new GeneralStaff();
				break;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid data entry must be"
					+ "\n no argument:    ()"
					+ "\n one argument:   (sweep)"
					+ "\n two arguments:  (cmp,advise)"
					+ "\n four arguments: (bob,1988,cmp,clean dishes)");
			
		}
		return g;
	}

	/**
	 * Takes in parsed data in form of String array then determines values to construct
	 * a Student object. CVS file format is known and it checks the value based on a known
	 * order. Converts String "true" to boolean, parses integers from string. Checks length
	 * of array to determine the constructor to use.
	 * Error message prints out if invalid data format which is used for
	 * user entry in interactive portion of program.
	 * @param fts - Array of Strings that contains the information to create a Student object should be in
	 * lengths of 0(empty), 1(boolean), 2(string,boolean) , or 4(String, integer, string, boolean)
	 * @return Student object created with proper arguments
	 */
	private static Student parseStudent(String[] sts) {
		Student s = null;
		boolean isGraduate = false;
		try {
			switch (sts.length) {
			case 1:
				if (sts[0].equalsIgnoreCase("true")) {
					isGraduate = true;
				}
				s = new Student(isGraduate);
				break;
			case 2:
				if (sts[1].equalsIgnoreCase("true")) {
					isGraduate = true;
				}
				s = new Student(sts[0], isGraduate);
				break;
			case 4:
				int birthYear = Integer.parseInt(sts[1]);
				if (sts[3].equalsIgnoreCase("true")) {
					isGraduate = true;
				}
				s = new Student(sts[0], birthYear, sts[2], isGraduate);
				break;
			default:
				s = new Student();
				break;
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid data entry must be"
					+ "\nno argument:    ()"
					+ "\none argument:   (g)"
					+ "\ntwo arguments:  (cmp,u)"
					+ "\nfour arguments: (bob,1988,cmp,u)");
		}
		return s;
	}
	
	/**
	 * Method to print out database information in a specific format. Iterates through 
	 * each ArrayList in turn and prints to console.
	 */
	private static void printSampleOutput() {
		System.out.println("**************************************************************");
		System.out.println("SCHOOL DATABASE INFO:\n");

		System.out.println("************************************************");
		System.out.println("COURSES:");
		for (Course c : courses) {
			System.out.println(c.toString());
		}
		System.out.println("************************************************\n"
				+ "************************************************");
		System.out.println("PERSONS:");
		System.out.println("************************************************\n"
				+ "************************************************");
		System.out.println("EMPLOYEES:");
		System.out.println("************************************************\n"
				+ "************************************************");
		System.out.println("GENERAL STAFF:");
		for (GeneralStaff gs : generalStaff) {
			System.out.println(gs.toString());
		}
		System.out.println("************************************************\n"
				+ "************************************************");
		System.out.println("FACULTY:");
		for (Faculty f : facultyMembers) {
			System.out.println(f.toString());
		}
		System.out.println("************************************************\n"
				+ "************************************************");
		System.out.println("STUDENTS:");
		for (Student s : students) {
			System.out.println(s.toString());
		}
		System.out.println("************************************************\n"
				+ "**************************************************************\n");
	}

	// ================================================================================
	// Interactive Menu Code
	// ================================================================================

	/**
	 * Main code for menu functionality. Opens up a scanner for user input
	 */
	private static void interactiveMenu() {
		
		// Open Scanner for user input
		Scanner sc = new Scanner(System.in);
		
		// Boolean to stay in main menu loop until user types in sentinel value
		boolean exit = false;
		
		// Integer holding user choice
		int choice = -1;
		
		// Main loop for menu until user exits
		do{
			
			// Prints out menu options and user prompt
			System.out.println("\nSchool Database Menu:\n"
					+          "**********************");
			do{
				System.out.println("Enter in a number(-1 to exit)"
						+ "\n/********************************************************************************************\\"
						+ "\n| 0 : Create Course                       |    6 : Check if Faculty Teaches Course           |"          
						+ "\n| 1 : Create Faculty                      |    7 : Which Faculty Teaches Most?               |"
						+ "\n| 2 : Create GeneralStaff                 |    8 : Which Course is Maximum?                  |"
						+ "\n| 3 : Create Student                      |    9 : Which Course is Minimum?                  |"
						+ "\n| 4 : Add Courses to Faculty or Student   |    10: Which Student Has Most and Least Credits? |"
						+ "\n| 5 : Get Course at Index Fac/Stu         |    11: Print All Objects to Console              |"
						+ "\n\\********************************************************************************************/");						
				System.out.println();
				
				// Check for valid input from user
				while (!sc.hasNextInt()) {
			        System.out.println("Invalid input");
			        sc.next();
				}
				choice = sc.nextInt();
			}while(choice <-1 && choice>11);
			
			// Switch block that will determine what happens based on the user choice. Certain options check for whether ArrayList is empty or not.
			// Some cases sort based on each individual class' compareTo() implementation
			switch(choice) {
			case -1:
				exit = true;
				break;
			case 0:
				createCourseObj(sc);
				break;
			case 1:
				createFacultyObj(sc);
				break;
			case 2:
				createGeneralStaffObj(sc);
				break;
			case 3:
				createStudentObj(sc);
				break;
			case 4:
				if(!courses.isEmpty()) {
					addCourseArr(sc);
				}
				else {
					System.out.println("Courses is empty! Add some courses first!");
				}
				break;
			case 5:
				if(!facultyMembers.isEmpty() && !students.isEmpty()) {
					studentOrFacultyIndex(sc);
				}
				else {
					System.out.println("Students or FacultyMembers is empty! Add some students or faculty first!");
				}
				break;
			case 6:
				if(!facultyMembers.isEmpty()) {
					checkIfTeaches(sc);
				}
				else {
					System.out.println("FacultyMembers is empty! Add some faculty first!");
				}
				break;
			case 7:
				if(!facultyMembers.isEmpty()) {
					int most = 0;
					String name = "";
					for(Faculty f : facultyMembers) {
						if(f.getNumCoursesTaught() > most) {
							most = f.getNumCoursesTaught();
							name = f.getName();
						}
					}
					System.out.printf("%s teaches the most courses. They teach %s courses.%n", name, most);
				}
				else {
					System.out.println("FacultyMembers is empty! Add some faculty first!");
				}
				break;
			case 8:
				if(!courses.isEmpty()) {
					int max = 0;
					String maxName = "";
					for(Course c : courses) {
						if(c.getCourseNum()> max) {
							max = c.getCourseNum();
							maxName = c.getCourseName();
						}
					}
					System.out.printf("%n%s is the max course. Course number is: %s%n",maxName, max);
				}
				else {
					System.out.println("Courses is empty! Add some courses first!");
				}
				break;
			case 9:
				if(!courses.isEmpty()) {
					int min = 99999;
					String minName = "";
					for(Course c : courses) {
						if(c.getCourseNum()< min) {
							min = c.getCourseNum();
							minName = c.getCourseName();
						}
					}
					System.out.printf("%n%s is the min course. Course number is: %s%n",minName, min);
				}
				else {
					System.out.println("Courses is empty! Add some courses first!");
				}
				break;
			case 10:
				if(!students.isEmpty()) {
					int minStu = 9999, maxStu = 0;
					String minStuName = "", maxStuName = "";
					for(Student s: students) {
						if(minStu > s.getNumCredits()) {
							minStu = s.getNumCredits();
							minStuName = s.getName();
						}
						if(maxStu < s.getNumCredits()) {
							maxStu = s.getNumCredits();
							maxStuName = s.getName();
						}
					}
					System.out.printf("%s has the most credits, total of %d%n%s has the least credits, total of %d%n",maxStuName,maxStu,minStuName,minStu);
				}
				else {
					System.out.println("Students is empty! Add some students first!");
				}
				break;
			case 11:
				printSampleOutput();
				break;
			default:
				System.out.println("How did we get here?");
				break;
			}
		}while(!exit);
		printFile();
		sc.close();
	}
	
	/**
	 * Takes in the scanner created by the interactive menu to get more user input. Asks user how many student objects they would like to create.
	 * checks for valid input. Calls parseStudent to parse and construct student object. For user clarity, instructs user to use g or u for graduate or
	 * undergraduate, then checks the string and converts it into the proper boolean. Adds constructed object to end of array and sorts the list.
	 * @param sc - Scanner object to take user input
	 */
	private static void createStudentObj(Scanner sc) {
		
		int numStudent = 0;
		String userInput = "";
		String inputTokens [] = null;
		// User prompts for correct formatting when adding a student
		System.out.println("Create Student with the following format: g or u for graduate  or undergraduate status\n"
				+ "\nno argument:    ()"
				+ "\none argument:   g/u (g)"
				+ "\ntwo arguments:  major, t/nt (cmp,u)"
				+ "\nfour arguments: name, birth year, major, g/u (bob,1988,cmp,u)\n");
		System.out.println("How many Student would you like to make?");
		
		// Input validation
		do {
		    System.out.print("Please enter a number from 0 to 3: ");
		    while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
		    }
		    numStudent = sc.nextInt();
		} while (numStudent < 0 && numStudent > 3);
		sc.nextLine();
		boolean valid = true;
		
		// Loops through based on number of students to be made
		for(int i = 0; i < numStudent; i++) {
			System.out.printf("Enter in Student number %d out of %d: ", i+1, numStudent);
			Student c = null;
			
			// Input validation for information to construct student
			do {
				
				userInput = sc.nextLine().trim();
				inputTokens = userInput.trim().split(",");
				if(inputTokens.length == 1 && inputTokens[0].isEmpty()) {
					c = parseStudent(inputTokens);
				}
				if(inputTokens.length == 1 && !inputTokens[0].isEmpty()) {
					if(inputTokens[0].equalsIgnoreCase("g")) {
						inputTokens[0] = "true";
					}else {
						inputTokens[0] = "false";
					}
					c = parseStudent(inputTokens);
				}
				if(inputTokens.length == 2){
					if(inputTokens[1].equalsIgnoreCase("t")) {
						inputTokens[1] = "true";
					}else {
						inputTokens[1] = "false";
					}
					c = parseStudent(inputTokens);
				}
				if(inputTokens.length == 4) {
					if(inputTokens[3].equalsIgnoreCase("t")) {
						inputTokens[3] = "true";
					}else {
						inputTokens[3] = "false";
					}
					c = parseStudent(inputTokens);
				}
				if(c == null) {
					valid = false;
					System.out.println("Please re-enter information.");
				}
				else {
					valid = true;
				}
			}while(!valid);
			
			// Makes sure that null objects are not added
			if(c != null) {
				students.add(c);
				System.out.println("Student added." + "\n" +c.toString());
				Collections.sort(students);
			}else {
				System.out.println("Failed to add Student");
			}
		}
	}

	/**Takes in the scanner created by the interactive menu to get more user input. Asks user how many faculty objects they would like to create.
	 * checks for valid input. Calls parseFaculty to parse and construct faculty object. For user clarity, instructs user to use t or nt for tenured or
	 * or not tenured, then checks the string and converts it into the proper boolean. Adds constructed object to end of array and sorts the list.
	 * @param sc - Scanner object to take user input
	 */
	private static void createFacultyObj(Scanner sc) {
		// Similar structure to createStudentObj, see comments there for any confusion
		int numFaculty = 0;
		String userInput = "";
		String inputTokens [] = null;
		System.out.println("Create faculty with the following format: t or nt for tenure status\n"
				+ "\nno argument:    ()"
				+ "\none argument:   t/nt (t)"
				+ "\ntwo argyments:  deptname, t/nt (cmp,nt)"
				+ "\nfour arguments: name, birth year, dept name, t/nt (bob,1988,cmp,t)\n");
		System.out.println("How many Faculty would you like to make?");
		do {
		    System.out.print("Please enter a number from 0 to 3: ");
		    while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
		    }
		    numFaculty = sc.nextInt();
		} while (numFaculty < 0 && numFaculty > 3);
		sc.nextLine();
		boolean valid = true;
		for(int i = 0; i < numFaculty; i++) {
			System.out.printf("Enter in faculty number %d out of %d: ", i+1, numFaculty);
			Faculty c = null;
			do {
				
				userInput = sc.nextLine().trim();
				inputTokens = userInput.trim().split(",");
				if(inputTokens.length == 1 && inputTokens[0].isEmpty()) {
					c = parseFaculty(inputTokens);
				}
				if(inputTokens.length == 1 && !inputTokens[0].isEmpty()) {
					if(inputTokens[0].equalsIgnoreCase("t")) {
						inputTokens[0] = "true";
					}else {
						inputTokens[0] = "false";
					}
					c = parseFaculty(inputTokens);
				}
				if(inputTokens.length == 2){
					if(inputTokens[1].equalsIgnoreCase("t")) {
						inputTokens[1] = "true";
					}else {
						inputTokens[1] = "false";
					}
					c = parseFaculty(inputTokens);
				}
				if(inputTokens.length == 4) {
					if(inputTokens[3].equalsIgnoreCase("t")) {
						inputTokens[3] = "true";
					}else {
						inputTokens[3] = "false";
					}
					c = parseFaculty(inputTokens);
				}
				if(c == null) {
					valid = false;
					System.out.println("Please re-enter information.");
				}
				else {
					valid = true;
				}
			}while(!valid);
			if(c != null) {
				facultyMembers.add(c);
				Collections.sort(facultyMembers);
				System.out.println("Faculty added" + "\n" +c.toString());
			}else {
				System.out.println("Failed to add Faculty");
			}
		}
	}
	
	/**Takes in the scanner created by the interactive menu to get more user input. Asks user how many GeneralStaff objects they would like to create.
	 * checks for valid input. Calls parseGeneralStaff to parse and construct generalstaff object. For user clarity. Adds constructed object to end of 
	 * array and sorts the list.
	 * @param sc - Scanner object to take user input
	 */
	private static void createGeneralStaffObj(Scanner sc) {
		// Similar structure to createStudentObj, see comments there for any confusion
		int numGS = 0;
		String userInput = "";
		String inputTokens [] = null;
		System.out.println("Create general staff with the following format:\n"
				+ "\nno argument:    ()"
				+ "\none argument:   duty (stare at students)"
				+ "\ntwo arguments:  deptname, duty (cmp,count computers)"
				+ "\nfour arguments: name, birth year, dept name, duty (bob,1988,cmp,clean)\n");
		System.out.println("How many General Staff would you like to make?");
		do {
		    System.out.print("Please enter a number from 0 to 3: ");
		    while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
		    }
		    numGS = sc.nextInt();
		} while (numGS < 0 && numGS > 3);
		sc.nextLine();
		boolean valid = true;
		for(int i = 0; i < numGS; i++) {
			System.out.printf("Enter in general staff number %d out of %d: ", i+1, numGS);
			GeneralStaff c = null;
			do {
				userInput = sc.nextLine().trim();
				inputTokens = userInput.trim().split(",");
				if(inputTokens.length == 1 && inputTokens[0].isEmpty()) {
					c = parseGeneralStaff(inputTokens);
				}
				if(inputTokens.length == 1 && !inputTokens[0].isEmpty()) {
					c = parseGeneralStaff(inputTokens);
				}
				if(inputTokens.length == 2){
					c = parseGeneralStaff(inputTokens);
				}
				if(inputTokens.length == 4) {
					c = parseGeneralStaff(inputTokens);
				}
				if(c == null) {
					valid = false;
					System.out.println("Please re-enter information.");
				}
				else {
					valid = true;
				}
			}while(!valid);
			if(c != null) {
				generalStaff.add(c);
				Collections.sort(generalStaff);
				System.out.println("General Staff added" +  "\n" +c.toString());
			}else {
				System.out.println("Failed to add GeneralStaff");
			}
		}
	}
	
	/**
	 * Takes in the scanner created by the interactive menu to get more user input. Asks user how many course objects they would like to create.
	 * checks for valid input. Calls parseCourse to parse and construct course object. For user clarity, instructs user to use g or u for graduate or
	 * undergraduate, then checks the string and converts it into the proper boolean. Adds constructed object to end of array and sorts the list.
	 * @param sc - Scanner object to take user input
	 */
	private static void createCourseObj(Scanner sc) {
		// Similar structure to createStudentObj, see comments there for any confusion. Biggest difference is there isn't a need to check
		// the length of the token array for anything other than 4.
		int numCourses = 0;
		String userInput = "";
		String inputTokens [] = null;
		System.out.println("Create course with the following format: u or g for graduate\n"
				+ "course number, department name, and number of credits. Example:\n" 
				+ "u,345,CMP,4");
		System.out.println("How many courses would you like to make?");
		do {
		    System.out.print("Please enter a number from 0 to 3: ");
		    while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
		    }
		    numCourses = sc.nextInt();
		} while (numCourses < 0 && numCourses > 3);
		sc.nextLine();
		boolean valid = true;
		for(int i = 0; i < numCourses; i++) {
			System.out.printf("Enter in course number %d out of %d: ", i+1, numCourses);
			Course c = null;
			do {
				
				userInput = sc.nextLine().trim();
				inputTokens = userInput.trim().split(",");
				if(inputTokens[0].equalsIgnoreCase("u")) {
					inputTokens[0] = "false";
				}
				else {
					inputTokens[0] = "true";
				}
				if(inputTokens.length == 4) {
					c = parseCourse(inputTokens);
				}
				if(c == null) {
					valid = false;
					System.out.println("Please re-enter information.");
				}
				else {
					valid = true;
				}
			}while(!valid);
			if(c != null) {
				courses.add(c);
				Collections.sort(courses);
				System.out.println("Course added: "  + "\n" +c.toString());
			}else {
				System.out.println("Failed to add Course");
			}
			
		}
		//sc.nextLine();
	}
	
	/**
	 * Through a series of prompts and input validation, asks user for number of courses they would like to add (0-2), whether it is being added to a student
	 * or faculty, displays the courses available to add, prompts user to choose a specific student/faculty, then adds the courses through a course [] to
	 * selected object. Uses helper methods printCourses(), chooseStudent() or chooseFaculty(). 
	 * @param sc - Scanner created by interactive menu to take in user input
	 */
	private static void addCourseArr(Scanner sc) {
		int choice = -1;
		int numCoursesToAdd = 0;
		do{
			System.out.println("Enter number of Courses to add (0-2)");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			numCoursesToAdd = sc.nextInt();
		}while(numCoursesToAdd<0 && numCoursesToAdd>2);
		Course [] toAdd = new Course[numCoursesToAdd];
		do{
			System.out.println("Enter in a number(-1 to exit\n)"
					+ "\n0: Student"
					+ "\n1: Faculty");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice <-1 && choice>1);
		
		switch(choice) {
		
		case 0:
			for(int i = 0; i < numCoursesToAdd; i++) {
				System.out.println("Input choice of course to add, -1 to exit");
				printCourses();
				do{
					System.out.println("Enter in a number(-1 to exit\n)");
					System.out.println();
					while (!sc.hasNextInt()) {
				        System.out.println("Invalid input");
				        sc.next();
					}
					choice = sc.nextInt();
				}while(choice <-1 && choice>courses.size()-1);
				if(choice == -1) {
					break;
				}else {
					toAdd[i] = courses.get(choice);
				}
			}
			chooseStudent(sc).addCoursesTaken(toAdd);
			Collections.sort(students);
			break;
			
		case 1:
			for(int i = 0; i < numCoursesToAdd; i++) {
				System.out.println("Input choice of course to add, -1 to exit");
				printCourses();
				do {
					System.out.println("Enter in a number(-1 to exit\n)");
					System.out.println();
					while (!sc.hasNextInt()) {
			        System.out.println("Invalid input");
			        sc.next();
				}
				choice = sc.nextInt();
				}while(choice <-1 && choice>courses.size()-1);
			if(choice == -1) {
				break;
			}else {
				toAdd[i] = courses.get(choice);
			}
			}
			chooseFaculty(sc).addCoursesTaught(toAdd);
			Collections.sort(facultyMembers);
			break;
			
		default:
			System.out.println("How did we get here?");
			break;
		}
	}
	
	/**
	 * Uses helper method to display all faculty in facultyMembers. Prompts user for input to select faculty.
	 * @param sc - Scanner created by menu to take in user input.
	 * @return A Faculty object based on the user choice.
	 */
	private static Faculty chooseFaculty(Scanner sc) {
		int choice = -1;
		System.out.println("Input choice of Faculty");
		printFaculty();
		do{
			System.out.println("Enter in a number");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice <0 && choice>facultyMembers.size()-1);
		return facultyMembers.get(choice);
	}
	
	/**
	 * Uses helper method to display all students in students. Prompts user for input to select student.
	 * @param sc - Scanner created by menu to take in user input.
	 * @return A Student object based on the user choice.
	 */
	private static Student chooseStudent(Scanner sc) {
		int choice = -1;
		System.out.println("Input choice of Student");
		printStudents();
		do{
			System.out.println("Enter in a number");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice <0 && choice>students.size()-1);
		return students.get(choice);
	}
	
	/**
	 * Iterates through the courses arraListy and uses the toString() method to print to console all courses.
	 */
	private static void printCourses() {
		for(Course c : courses) {
			System.out.println("" +courses.indexOf(c) + " " + c.toString() );
		}
	}
	
	/**
	 * Iterates through the students arrayList and uses the toString() method to print to console all students.
	 */
	private static void printStudents() {
		for(Student c : students) {
			System.out.println("" +students.indexOf(c) + " " + c.toString() );
		}
	}
	
	/**
	 * Iterates through the faculty arrayList and uses the toString() method to print to console all faculty.
	 */
	private static void printFaculty() {
		for(Faculty c : facultyMembers) {
			System.out.println("" +facultyMembers.indexOf(c) + " " + c.toString() );
		}
	}
	
	/**
	 * Takes user input to return a course at specified index of a Faculty or Student. Uses various helper methods.
	 * @param sc - Scanner created by menu to take in user input
	 */
	private static void studentOrFacultyIndex(Scanner sc) {
		int choice = -1;
		do{
			System.out.println("Enter in a number"
					+ "\n0: Student"
					+ "\n1: Faculty");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice <0 && choice>1);
		if(choice == 0) {
			indexChoiceStudentCourse(sc);
		}else if (choice == 1) {
			indexChoiceFacultyCourse(sc);
		}else {
			System.out.println("How did we get here? - Student or Faculty Index");
		}
	}
	
	/**
	 * Takes user input to return a course at specified index of a Faculty. Uses various helper methods.
	 * @param sc - Scanner created by menu to take in user input
	 */
	private static void indexChoiceStudentCourse(Scanner sc) {
		int choice = 0;
		Student s = chooseStudent(sc);
		System.out.println("Input index of Student courses taken to check (MAX is "+ (STUDENT_COURSE_SIZE-1) +")");
		do{
			System.out.println("Enter in a number(0-" + (STUDENT_COURSE_SIZE-1)+")");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice < 0 && choice > STUDENT_COURSE_SIZE-1);
		System.out.println(s.getName()
		        + "\n       Course at index chosen: " + s.getCourseTaken(choice));
	}
	
	/**
	 * Takes user input to return a course at specified index of a Faculty. Uses various helper methods.
	 * @param sc - Scanner created by menu to take in user input
	 */
	private static void indexChoiceFacultyCourse(Scanner sc) {
		int choice = 0;
		Faculty s = chooseFaculty(sc);
		System.out.println("Input index of faculty courses taught to check (MAX is "+ (FACULTY_COURSE_SIZE-1) +")");
		do{
			System.out.println("Enter in a number(0-" + (FACULTY_COURSE_SIZE-1)+")");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice < 0 && choice > FACULTY_COURSE_SIZE-1);
		System.out.println(s.getName()
				        + "\n       Course at index chosen: " + s.getCourseTaught(choice));
	}
	
	/**
	 * Takes user input through chooseFaculty() and courseFromCat() to check if faculty teaches course.
	 * @param sc - Scanner created by menu to take in user input
	 */
	private static void checkIfTeaches(Scanner sc) {
		Faculty s = chooseFaculty(sc);
		Course c = courseFromCat(sc);
		boolean teaches = false;
		for(int i = 0; i < s.getNumCoursesTaught();i++) {
			if(s.getCourseTaught(i).equals(c)) {
				teaches = true;
			}
		}
		System.out.printf("%s teaches %s? %b%n", s.getName(), c.getCourseName(), teaches);
	}
	
	/**
	 * Takes user input to select a course from list of displayed courses. Uses printCourses();
	 * @param sc - Scanner created by menu to take in user input
	 * @return Course based on user input.
	 */
	private static Course courseFromCat(Scanner sc) {
		Course c = null;
		int choice = 0;
		System.out.println("Choose a course from catalouge:");
		printCourses();
		do{
			System.out.println("Enter in a number(0-" + (courses.size()-1)+")");
			System.out.println();
			while (!sc.hasNextInt()) {
		        System.out.println("Invalid input");
		        sc.next();
			}
			choice = sc.nextInt();
		}while(choice < 0 && choice > (courses.size()-1));
		c = courses.get(choice);
		return c;
	}

	/**
	 * Opens scanner to take in input to be passed to a printwriter. Iterates through each ArrayList, printing out contents
	 * line by line into a txt file that is formatted in a near CVS like file.
	 */
	private static void printFile() {
		Scanner inStream = null;
		PrintWriter outStream = null;
		String fileName = "";
		String fileContents = "";
		fileName = "School_DB.txt";
		try{
			outStream = new PrintWriter(fileName);
			for(Course c:courses) {
				fileContents = ("Course: " + c.isGraduateCourse() + "," + c.getCourseNum() + "," + c.getCourseDept() + "," + c.getNumCredits());
				outStream.println(fileContents);
			}
			outStream.println(System.lineSeparator());
			for(Student s: students) {
				fileContents = ("Student: " + s.getName() + "," + s.getBirthYear()+","+s.getMajor()+","+s.isGraduate());
				outStream.println(fileContents);
			}
			outStream.println(System.lineSeparator());
			for(Faculty f: facultyMembers) {
				fileContents = ("Faculty: " + f.getName() + "," + f.getBirthYear()+"," + f.getDeptName() +","+ f.isTenured()
				+ "\nCourses Taught: " + f.getAllCoursesTaughtAsString() + "\n");
				outStream.println(fileContents);
			}
			outStream.println(System.lineSeparator());
			for(GeneralStaff gs: generalStaff) {
				fileContents = ("GeneralStaff: " + gs.getName() + "," + gs.getBirthYear()+","+ gs.getDeptName()+","+ gs.getDuty());
				outStream.println(fileContents);
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found " + fileName);
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
			if(outStream != null) {
				outStream.close();
			}
		}
		
	}
}