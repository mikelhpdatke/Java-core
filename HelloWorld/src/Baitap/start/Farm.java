/**
 * 
 */
package Baitap.start;


import java.util.Scanner;
/**
 * @author luong
 *
 */
class Card {
	private int a = 9;
	Card() {
		gotoSleep();
	}
	
	void gotoSleep() {
		System.out.println("A di ngu day :))!");
	}
}

public class HelloWorld {

	/**
	 * 
	 */
	public HelloWorld() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
		}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 0, b = 0;
		Scanner scanner = new Scanner(System.in);
		//a = scanner.nextInt();
		//b = scanner.nextInt();
		//System.out.println(GCD(a, b));
		int[] arr = {1, 2, 3};
		for(int i = 0; i < 3; i++) System.out.printf("%d ", arr[i]);
	
		Card[] tmp = new Card[5];
		
	}

}
