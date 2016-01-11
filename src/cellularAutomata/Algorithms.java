package cellularAutomata;

public class Algorithms {
		
	public double countState(Cell neighbourhood[],double oldVal) {
		double state=0.0d;	
		
		for(int i = 1; i<13;i++){
			state += neighbourhood[i].getState();
		}
		
		state /= 6.0d;
		state -= oldVal;	
		state /= 1.05d;
		state = minmax(-1.0f,state,1.0f);
		state = state - (state / Constants.DAMPING);

		//state /= Constants.DAMPING;

		return state;
	}
	
	public Cell[] setNeighbourhood(Cell[][] current,int x,int y) {
		Cell [] neighbourhood = new Cell[13];
		
		neighbourhood[0]  = current[x][y];
		neighbourhood[1]  = (x-2>0)          ? current[x-2][y]    :new Cell(0);
		neighbourhood[2]  = (x-1>0)          ? current[x-1][y]    :new Cell(0);
		neighbourhood[3]  = (x+1<99)         ? current[x+1][y]    :new Cell(0);
		neighbourhood[4]  = (x+2<99)         ? current[x+2][y]    :new Cell(0);
		neighbourhood[5]  = (y-2>0)          ? current[x][y-2]    :new Cell(0);
		neighbourhood[6]  = (y-1>0)          ? current[x][y-1]    :new Cell(0);
		neighbourhood[7]  = (y+1<99)         ? current[x][y+1]    :new Cell(0);
		neighbourhood[8]  = (y+2<99)         ? current[x][y+2]    :new Cell(0);
		neighbourhood[9]  = (x-1>0 &&y-1>0)  ? current[x-1][y-1]  :new Cell(0);
		neighbourhood[10] = (x-1>0 &&y+1<99) ? current[x-1][y+1]  :new Cell(0);
		neighbourhood[11] = (x+1<99&&y-1>0)  ? current[x+1][y-1]  :new Cell(0);
		neighbourhood[12] = (x+1<99&&y+1<99) ? current[x+1][y+1]  :new Cell(0);

		
		return neighbourhood;
	}
	
	private double minmax(double leftBound, double value, double rightBound) {
		if (value <= leftBound) {
			return leftBound;
		}
		else if (value >= rightBound) {
			return rightBound;
		}
		else return value;
		
	}
	
	public static Cell[][] deepAutomatonClone(Cell[][]input) {
		if (input==null) {
			return null;
		}
		
		Cell[][] result = new Cell[Constants.X_DIMENSION][];
		for(int i=0;i<input.length;i++){
			result[i]=input[i].clone();
			for(int b=0;b<input[i].length;b++) {
				result[i][b] = new Cell(input[i][b]); 
			}
		}

		return result;
	}
}
