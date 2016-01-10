package cellularAutomata;

public class Algorithms {
		
	public double countState(Cell neighbourhood[]) {
		double state=0.0d;	
		double currentState = neighbourhood[0].getState();
		
		//if (currentState != 0.0f){

		for(int i = 0; i<13;i++){
			state += neighbourhood[i].getState();
		}
		
		state /= Constants.DAMPING;
		state -= currentState;
		//TODO dodac do constants
	 	state /= 1.00000000001;
		state = minmax(-0.99999f,state,0.99999f);
		//} 
		//else
		//	state =  minmax(-0.99999f,currentState,0.99999f);
		
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
		neighbourhood[12] = (x+1<99&&y+1<99) ? current[x+1][x+1]  :new Cell(0);

		
		return neighbourhood;
	}
	
	/*
	public Cell[] setNeighbourhood(Cell[][] currentAutomaton, int positionX, int positionY) {
		Cell[] neighbourhood = new Cell[13];
		
		//dwie kolumny po lewej bez "rogow"
		if(positionX==0 && positionY>=2 && positionY<Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 2][positionY];
			neighbourhood[2]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[Constants.X_DIMENSION - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==1 && positionY>=2 && positionY<Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		//dwie kolumny po prawej "bez rogow"
		if(positionX==Constants.X_DIMENSION-1 && positionY>=2 && positionY<Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[0][positionY];
			neighbourhood[4]  = currentAutomaton[1][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[0][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[0][positionY + 1];
		}
		
		if(positionX==Constants.X_DIMENSION-2 && positionY>=2 && positionY<Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[0][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];	
		}
		
		//gorne dwa wiersze bez 'rogow'
		if(positionY==0 && positionX>=2 && positionX<Constants.X_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 2];
			neighbourhood[6]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][Constants.Y_DIMENSION - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][Constants.Y_DIMENSION - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionY==1 && positionX>=2 && positionX<Constants.X_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		//dolne wiersze bez 'rogow'
		if(positionY==Constants.Y_DIMENSION-2 && positionX>=2 && positionX<Constants.X_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][0];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionY==Constants.Y_DIMENSION-1 && positionX>=2 && positionX<Constants.X_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][0];
			neighbourhood[8]  = currentAutomaton[positionX][1];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][0];
			neighbourhood[12] = currentAutomaton[positionX + 1][0];
		}
		
		//lewy gorny rog
		if (positionX==0 && positionY==0) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 2][positionY];
			neighbourhood[2]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 2];
			neighbourhood[6]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[Constants.X_DIMENSION - 1][Constants.Y_DIMENSION - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][Constants.Y_DIMENSION- 1];
			neighbourhood[11] = currentAutomaton[Constants.X_DIMENSION - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==1 && positionY==1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION-1][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION-1];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==1 && positionY==0) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION-1][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 2];
			neighbourhood[6]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][Constants.Y_DIMENSION - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][Constants.Y_DIMENSION - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==0 && positionY==1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 2][positionY];
			neighbourhood[2]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[Constants.X_DIMENSION - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		//lewy dolny rog
		if(positionX==0 && positionY==Constants.Y_DIMENSION-1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 2][positionY];
			neighbourhood[2]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][0];
			neighbourhood[8]  = currentAutomaton[positionX][1];
			neighbourhood[9]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[Constants.X_DIMENSION - 1][0];
			neighbourhood[12] = currentAutomaton[positionX + 1][0];
		}
		
		if(positionX==0 && positionY==Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 2][positionY];
			neighbourhood[2]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][0];
			neighbourhood[9]  = currentAutomaton[Constants.X_DIMENSION - 1][Constants.Y_DIMENSION - 3];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[Constants.X_DIMENSION - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==1 && positionY==Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION -1][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][0];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==1 && positionY==Constants.Y_DIMENSION - 1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[Constants.X_DIMENSION - 1][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][0];
			neighbourhood[8]  = currentAutomaton[positionX][1];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][0];
			neighbourhood[12] = currentAutomaton[positionX + 1][0];
		}
		
		//prawy gorny rog
		
		if(positionX==Constants.X_DIMENSION-1 && positionY==0) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[0][positionY];
			neighbourhood[4]  = currentAutomaton[1][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 2];
			neighbourhood[6]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][Constants.Y_DIMENSION - 1];
			neighbourhood[10]  = currentAutomaton[0][Constants.Y_DIMENSION - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[0][positionY + 1];
		}
		
		if(positionX==Constants.X_DIMENSION-2 && positionY==0) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[0][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 2];
			neighbourhood[6]  = currentAutomaton[positionX][Constants.Y_DIMENSION - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][Constants.Y_DIMENSION - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][Constants.Y_DIMENSION - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==Constants.X_DIMENSION-1 && positionY==1){
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[0][positionY];
			neighbourhood[4]  = currentAutomaton[1][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION-1];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[0][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[0][positionY + 1];
		}
		
		
		if(positionX==Constants.X_DIMENSION-2 && positionY==1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[0][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][Constants.Y_DIMENSION-1];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		//prawy dolny rog
		if(positionX==Constants.X_DIMENSION-1 && positionY==Constants.Y_DIMENSION-1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[0][positionY];
			neighbourhood[4]  = currentAutomaton[1][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][0];
			neighbourhood[8]  = currentAutomaton[positionX][1];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[0][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][0];
			neighbourhood[12] = currentAutomaton[0][0];
		}
		
		if(positionX==Constants.X_DIMENSION-2 && positionY==Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[0][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][0];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		if(positionX==Constants.X_DIMENSION-2 && positionY==Constants.Y_DIMENSION-1) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[0][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][0];
			neighbourhood[8]  = currentAutomaton[positionX][1];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][0];
			neighbourhood[12] = currentAutomaton[positionX + 1][0];
		}
		
		if(positionX==Constants.X_DIMENSION-1 && positionY==Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[0][positionY];
			neighbourhood[4]  = currentAutomaton[1][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][0];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[0][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[0][positionY + 1];
		}
		
		
		
		//'œrodek'
		if(positionX>=2 && positionX<Constants.X_DIMENSION-2 && positionY>=2 && positionY<Constants.Y_DIMENSION-2) {
			neighbourhood[0]  = currentAutomaton[positionX][positionY];
			neighbourhood[1]  = currentAutomaton[positionX - 2][positionY];
			neighbourhood[2]  = currentAutomaton[positionX - 1][positionY];
			neighbourhood[3]  = currentAutomaton[positionX + 1][positionY];
			neighbourhood[4]  = currentAutomaton[positionX + 2][positionY];
			neighbourhood[5]  = currentAutomaton[positionX][positionY - 2];
			neighbourhood[6]  = currentAutomaton[positionX][positionY - 1];
			neighbourhood[7]  = currentAutomaton[positionX][positionY + 1];
			neighbourhood[8]  = currentAutomaton[positionX][positionY + 2];
			neighbourhood[9]  = currentAutomaton[positionX - 1][positionY - 1];
			neighbourhood[10]  = currentAutomaton[positionX + 1][positionY - 1];
			neighbourhood[11] = currentAutomaton[positionX - 1][positionY + 1];
			neighbourhood[12] = currentAutomaton[positionX + 1][positionY + 1];
		}
		
		return neighbourhood;
	}
	*/
	
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
