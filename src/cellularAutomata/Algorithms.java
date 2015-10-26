package cellularAutomata;

public class Algorithms {
	
	public Cell[][] updateAutomaton(Cell[][] currentAutomaton) {
		Cell[][] evolvedAutomaton = new Cell[Constants.X_DIMENSION][Constants.Y_DIMENSION];
		
		//zakres taki, zeby nie bylo skrajnych komorek
		for (int x = 1; x < Constants.X_DIMENSION - 1; x++) {
			for (int y = 1; y < Constants.Y_DIMENSION - 1; y++) {
				countState(setNeighbourhood(currentAutomaton, x, y));
			}
		}
		
		return evolvedAutomaton;
	}
	
	public float countState(Cell neighbourhood[]) {
		float state=0.0f;	
		float currentState = neighbourhood[4].getState();
		
		state = (neighbourhood[1].getState() + neighbourhood[3].getState() +
				 neighbourhood[5].getState() + neighbourhood[7].getState())/2
				 - currentState;
		
		return state * Constants.DAMPING;
	}

	public Cell[] setNeighbourhood(Cell[][] currentAutomaton, int positionX, int positionY) {
		Cell[] neighbourhood = new Cell[8];
		neighbourhood[1] = currentAutomaton[positionX][positionY - 1];
		neighbourhood[3] = currentAutomaton[positionX - 1][positionY];
		neighbourhood[5] = currentAutomaton[positionX + 1][positionY - 1];
		neighbourhood[7] = currentAutomaton[positionX][positionY + 1];
		return neighbourhood;
	}
}
