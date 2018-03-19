/**
 * 
 */
import java.lang.Math;
import java.util.Scanner;
/**
 * @author luong
 *
 */
public class SoNguyen {
	private int value = 1;
	/**
	 * 
	 */
	SoNguyen() {
		// TODO Auto-generated constructor stub
		System.out.println("Hello World!");
	}

	public SoNguyen(int value) {
		this.value = value;
	}
	
//	public String toString(SoNguyen x) {
//		return x.toString();
//	}
	 
	public static String toString(SoNguyen x) {
		return x.toString();
	}
	
	public String toBinary() {
		return Integer.toBinaryString(value);
	}
	
	public static SoNguyen GCD(SoNguyen x, SoNguyen y) {
		if (y.value == 0) return x;
		else return GCD(new SoNguyen(y.value), new SoNguyen(x.value % y.value));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SoNguyen a = new SoNguyen(12);
//		SoNguyen b = new SoNguyen(8);
//		System.out.println(GCD(a, b).value);
		Scanner scanner = new Scanner(System.in);
		SoNguyen a = new SoNguyen(scanner.nextInt());
		SoNguyen b = new SoNguyen(scanner.nextInt());
		System.out.println(GCD(a, b).value);
	}
	
}
