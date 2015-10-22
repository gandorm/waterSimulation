package basics;

public class cell extends position{
	int direction;
	int speed;
	cell[] neighbor = new cell[9];	
	
	public void move(){
		
	}
	
	public void checkNeighborDistance(cell neighbor){
		if (neighbor.x > x+1){
			
		} 
	}
	
	public void setPosition(int xToSet, int yToSet){
		x = xToSet;
		y = yToSet;
	}
}
