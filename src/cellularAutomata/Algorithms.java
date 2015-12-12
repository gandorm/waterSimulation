package cellularAutomata;

public class Algorithms {
	
	/*
	public float smooth(Cell neighbourhood[]) {
		float currentState = neighbourhood[4].getState();
		
		currentState = (neighbourhood[1].getState() + neighbourhood[3].getState() +
				 		neighbourhood[5].getState() + neighbourhood[7].getState())/4;
		
		return currentState;
	}
	*/
	
	public float countState(Cell neighbourhood[]) {
		float state=0.0f;	
		float currentState = neighbourhood[0].getState();
		
		if (currentState != 0.0f){

		for(int i = 1; i<13;i++){
			state += neighbourhood[i].getState();
		}
		
		state /= Constants.DAMPING;
		state -= currentState;
		
		// TODO do contatnts
		state /= 1.05;
		
		//TODO tu powinien byc minimax -1,state,1?
		state = minmax(-1.0f,state,1.0f);
		if (state<-1 || state>1) System.out.println("Witaj w dupie: " + state);
		} else state = currentState;
		
		return state;
	}

	public Cell[] setNeighbourhood(Cell[][] currentAutomaton, int positionX, int positionY) {
		Cell[] neighbourhood = new Cell[13];
		
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
				
		return neighbourhood;
	}
	private float minmax(float leftBound, float value, float rightBound) {
		if (value <= leftBound) {
			return leftBound;
		}
		else if (value >= rightBound) {
			return rightBound;
		}
		else return value;
		
	}
}
