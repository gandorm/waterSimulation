package cellularAutomata;

public class Cell implements Cloneable{

	private float state;
	
	public Cell(){};
	
	public Cell(Cell that) {
		this.state = new Float(that.state);
	}

	public float getState() {
		return state;
	}

	public void setState(float state) {
		this.state = state;
	}
	
	@Override
	protected Cell clone() throws CloneNotSupportedException{
		return new Cell(this);
	}
}
