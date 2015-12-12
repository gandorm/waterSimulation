package cellularAutomata;

public class Automaton {

	private Cell[][] automaton;
	
	public void init() {
		automaton = new Cell[Constants.X_DIMENSION][Constants.Y_DIMENSION];
		for (int x = 0; x < Constants.X_DIMENSION; x++) {
			for (int y = 0; y < Constants.Y_DIMENSION; y++) {
				automaton[x][y] = new Cell();
				automaton[x][y].setState(-1.0f);
			}
		}
		
		for (int i = 1;i<99;i++) {
			automaton[1][i].setState(0.6f);
		}
			
		for (int i = 0; i<100; i++) {
			int border=1;
			
			if(i<=10) {
				border = 80;
			}
			
			if(i==11) border = 79;
			if(i==12) border = 78;
			if(i==13) border = 77;
			if(i==14) border = 76;
			
			if(i>14 && i<=40){
				border = 75;
			}
			
			if(i==41) border = 76;
			if(i==42) border = 77;
			if(i==43) border = 78;
			if(i==44) border = 79;
			if(i==45) border = 80;
			if(i==46) border = 81;
			if(i==47) border = 82;
			if(i==48) border = 83;
			if(i==49) border = 84;
			
			if(i>49 && i <=70) {
				border = 85;
			}
			
			if(i==71) border = 84;
			if(i==72) border = 83;
			if(i==73) border = 82;
			if(i==74) border = 81;
			
			if(i>74 && i <100) {
				border = 80;
			}
			
			for(int j=border;j<100;j++) {
				automaton[j][i].setState(0.0f);
			}
			
		}
		
	}

	public Cell[][] getAutomaton() {
		return automaton;
	}

	public void setAutomaton(Cell[][] automaton) {
		this.automaton = automaton;
	}
	
	
	
}
