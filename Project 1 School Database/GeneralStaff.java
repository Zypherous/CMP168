
public class GeneralStaff extends Employee{
	private String duty;
	
	// Constructors
	public GeneralStaff() {
		super();
		this.duty = "";
	}
	public GeneralStaff(String duty) {
		super();
		this.duty = duty;
	}
	public GeneralStaff(String deptName, String duty) {
		super(deptName);
		this.duty = duty;
	}
	public GeneralStaff(String name, int birthYear, String deptName, String duty) {
		super(name, birthYear, deptName);
		this.duty = duty;
	}
	
	// Accessor
	public String getDuty(){
		return this.duty;
	}
	
	// Overridden Methods
	@Override
	public boolean equals(Object obj) {
		if(obj == null){ return false; }
		if(this == obj){ return true; }
		if(obj instanceof GeneralStaff) {
			GeneralStaff otherG = (GeneralStaff)obj;
			if(super.equals(otherG)) {
				if(this.getDuty() == otherG.getDuty()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + String.format(" GeneralStaff: Duty: %10s", duty);
		return s;
	}
}
