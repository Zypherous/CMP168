

public class Person implements Comparable<Person>{
	
	// Class Variables
	private String name;
	private int birthYear;
	
	public Person(){
		this.setBirthYear(0);
		this.setName("");
	}
	public Person(String name, int birthYear){
		this.setBirthYear(birthYear);
		this.setName(name);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Accessors
//////////////////////////////////////////////////////////////////////////////////////////////
	public String getName() {
		return this.name;
	}
	public int getBirthYear() {
		return this.birthYear;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
// Mutators
//////////////////////////////////////////////////////////////////////////////////////////////
	public void setName(String name) {
		this.name = name;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	// Overridden methods from object class
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {return false;}
		if(this == obj) {return true;}
		if(obj instanceof Person) {
			Person otherP = (Person)obj;
			if(this.getBirthYear() == otherP.getBirthYear() && this.getName() ==otherP.getName()) { return true;}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = String.format("Person: Name: %30s | Birth Year: %4d", this.getName(), this.getBirthYear());
		return s;
	}
	
	
	@Override
	public int compareTo(Person p) {
		return this.getBirthYear() > p.getBirthYear() ? 1 : this.getBirthYear() < p.getBirthYear() ? -1 : 0;
	}
}
