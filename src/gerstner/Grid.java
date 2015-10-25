package gerstner;

public class Grid {

	private Point[][] grid;

	public void init() {
		grid = new Point[Parameters.GRID_SIZE_X][Parameters.GRID_SIZE_Y];

		// Populating grid with points, default height = 0
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid[i][k] = new Point(i, k, 0);
			}
		}
	}

	public void printGridToConsole() {
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				System.out.print(this.grid[i][k] + " ");
			}
			System.out.println();
		}
	}
}
