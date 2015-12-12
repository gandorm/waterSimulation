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
		float currentState = neighbourhood[4].getState();
		
		if (currentState != 0){
		for (Cell cell : neighbourhood) {
			state += cell.getState();
		}
		
		state /= Constants.DAMPING;
		state -= currentState;
		
		// TODO do contatnts
		state /= 1.05;
		
		//TODO tu powinien byc minimax -1,state,1?
		} else state = currentState;
		
		return state;
	}

	public Cell[] setNeighbourhood(Cell[][] currentAutomaton, int positionX, int positionY) {
		Cell[] neighbourhood = new Cell[12];
		
		neighbourhood[0]  = currentAutomaton[positionX - 2][positionY];
		neighbourhood[1]  = currentAutomaton[positionX - 1][positionY];
		neighbourhood[2]  = currentAutomaton[positionX + 1][positionY];
		neighbourhood[3]  = currentAutomaton[positionX + 2][positionY];
		neighbourhood[4]  = currentAutomaton[positionX][positionY - 2];
		neighbourhood[5]  = currentAutomaton[positionX][positionY - 1];
		neighbourhood[6]  = currentAutomaton[positionX][positionY + 1];
		neighbourhood[7]  = currentAutomaton[positionX][positionY + 2];
		neighbourhood[8]  = currentAutomaton[positionX - 1][positionY - 1];
		neighbourhood[9]  = currentAutomaton[positionX + 1][positionY - 1];
		neighbourhood[10] = currentAutomaton[positionX - 1][positionY + 1];
		neighbourhood[11] = currentAutomaton[positionX + 1][positionY + 1];
		
		
		
		
		return neighbourhood;
	}
}
