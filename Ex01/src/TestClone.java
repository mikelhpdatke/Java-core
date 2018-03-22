class Employee implements Cloneable{
	private String id, name;
	
	
	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	String getName() {
		return name;
	}
	
	void setName(String newName) {
		this.name = newName;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}

public class TestClone {

	public TestClone() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Employee original = new Employee("1", "Dat");
		
		Employee clone = (Employee) original.clone();
		
		clone.setName("Trang");
		System.out.println(original.getName());
	}
}
