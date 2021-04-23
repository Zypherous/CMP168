
public class Employee extends Person {

	private String deptName;
	private static int numEmployees = 0;
	private int employeeID = 0;
		
		
	public Employee() {
		super();
		this.setDeptName("");
		numEmployees++;
		this.employeeID = numEmployees;
		
		
	}
	public Employee(String deptName) {
		super();
		this.setDeptName(deptName);
		numEmployees++;
		this.employeeID = numEmployees;
	}
	public Employee(String name, int birthYear, String deptName) {
		super(name, birthYear);
		this.setDeptName(deptName);
		numEmployees++;
		this.employeeID =numEmployees;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Accessors and Mutators
//////////////////////////////////////////////////////////////////////////////////////////////
	public String getDeptName() {
		return this.deptName;
	}
	
	public int getEmployeeID() {
		return this.employeeID;
	}
	
	public static int getNumEmployees() {
		return numEmployees;
	}
	
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	
	
	// Overridden Methods
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){ return false; }
		if(this == obj){ return true; }
		if(obj instanceof Employee) {
			Employee otherE = (Employee)obj;
			if(super.equals(otherE)) {
				if(this.employeeID == otherE.employeeID) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String s = super.toString() + String.format(" Employee: Department: %20s | Employee Number: %3d", deptName, employeeID);
		return s;
	}
	
	@Override
	public int compareTo(Person p) {
		int parentVal = super.compareTo(p);
		if(p instanceof Employee){
			if(this.getEmployeeID() > ((Employee)p).getEmployeeID()){
				return 1;
			}
		
			else if(this.getEmployeeID() < ((Employee)p).getEmployeeID()){
				return -1;
			}
			
				else{return 0;}
		}
		else {
			return parentVal;
		}
	}
	
}

