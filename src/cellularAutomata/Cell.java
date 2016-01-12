package cellularAutomata;

public class Cell implements Cloneable{

	private double state;
	private boolean isWall;
	
	public Cell(){};

	public Cell(float state) {
		this.state = new Float(state);
	}

	public double getState() {
		return state;
	}

	public void setState(double state) {
		this.state = state;
	}
	
	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}
}
