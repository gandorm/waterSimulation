package basics;

public class position {
	private int x;
	private int y;
	private float zi, zr;
	
	public void setPosition(int xToSet, int yToSet, int zToSet){
		x = xToSet;
		y = yToSet;
		zi = zToSet;
	}
	
	public position(){
		x = 0;
		y = 0;
		zi = 0;
	}
	
	public void setZi(float z1){
		zi = z1;
	}
	public void setZr(float z1){
		zr = z1;
	}
}
