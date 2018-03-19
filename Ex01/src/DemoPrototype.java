/**
 * 
 */

/**
 * @author luong
 *
 */
abstract class Graphic implements Cloneable{
	abstract void draw();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}

class Line extends Graphic implements Cloneable{
	void draw() {
		System.out.println("Ve line!");
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}

class Curve extends Graphic implements Cloneable{
	void draw() {
		System.out.println("Ve curve!!");
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}

class GraphicTool{
	Graphic graphic;
	
	public GraphicTool(Graphic graphic) {
		super();
		this.graphic = graphic;
	}

	void manipulate(Graphic g) throws CloneNotSupportedException {
		Graphic _g = (Graphic) g.clone();
		g.draw();
	}
}

public class DemoPrototype {

	/**
	 * 
	 */
	public DemoPrototype() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Curve curve = new Curve();
		
		GraphicTool graphicTool = new GraphicTool(curve);
		graphicTool.manipulate(graphicTool.graphic);
	}

}
