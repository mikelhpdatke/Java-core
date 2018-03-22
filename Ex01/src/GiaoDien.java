/**
 * 
 */

/**
 * @author luong
 *
 */

interface CanFlight{
	static final int MAXHIGH = 1;
	void flight();
}

interface CanSwim{
	static final int MAXDEPTH = 2;
	void swim();
}


abstract class Animal{
	String name;
	Animal(String name) {
		this.name = name;
	}
	abstract void introduce();
}

class Bird extends Animal implements CanFlight{
	String name;
	Bird(String name) {
		super(name);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	void introduce() {
		// TODO Auto-generated method stub
		System.out.println(name + " is Bird");
	}

	@Override
	public void flight() {
		// TODO Auto-generated method stub
		
	}
}

class Fish extends Animal implements CanSwim{
	String name;
	Fish(String name) {
		super(name);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void introduce() {
		// TODO Auto-generated method stub
		System.out.println(name + " is Fish");
	}
	
}
public class GiaoDien {

	/**
	 * 
	 */
	public GiaoDien() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bird Dat = new Bird("WTF");
		System.out.println(Bird.MAXHIGH);
		Animal[] arr = new Animal[10];
		arr[0] = new Bird("Dat");
		arr[1] = new Fish("T");
		for(int i = 0; i <= 1; i++)
			arr[i].introduce();
	}

}
