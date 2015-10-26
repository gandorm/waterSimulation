package cellularAutomata;

public class Automaton {

	private Cell[][] automaton;
	
	public void init() {
		automaton = new Cell[Constants.X_DIMENSION][Constants.Y_DIMENSION];
		for (int x = 0; x < Constants.X_DIMENSION; x++) {
			for (int y = 0; y < Constants.Y_DIMENSION; y++) {
				automaton[x][y] = new Cell();
				automaton[x][y].setState(0.0f);
			}
		}
		/*
		for(int i = 1;i<99;i++) {
			automaton[50][i].setState(1.0f);
		}
		*/
		automaton[50][50].setState(1.0f);
		automaton[50][51].setState(1.0f);
		automaton[51][50].setState(1.0f);
		automaton[51][51].setState(1.0f);
	}

	public Cell[][] getAutomaton() {
		return automaton;
	}

	public void setAutomaton(Cell[][] automaton) {
		this.automaton = automaton;
	}
	
	
	
}
