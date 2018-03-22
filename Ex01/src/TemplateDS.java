/**
 * 
 */

/**
 * @author luong
 *
 */
abstract class Shape {
	protected int x, y;
	
	public void moveTo(int x1, int y1) {
		this.x = x1;
		this.y = y1;
	}
	
	abstract public void erase();
	abstract public void draw();
	
}



public class TemplateDS {

	/**
	 * 
	 */
	public TemplateDS() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
