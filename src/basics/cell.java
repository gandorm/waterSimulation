package basics;

public class cell extends position{
	int direction;
	int speed;
	int color;	
	position poz = new position();
	
	public void move(){
		
	}
	
	public void checkNeighborDistance(cell neighbor){
		
	}
	
	public int getColor(){
		return color;
	}
	
	public cell(){
		this.color = 1;
	}
	
	public void setColor(int i){
		color = i;
		direction = 0;
		speed = 0;
	}


}
