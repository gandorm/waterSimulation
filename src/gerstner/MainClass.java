package gerstner;

public class MainClass {

	public static void main(String[] args) {

		Gerstner gerstner = new Gerstner();
		gerstner.init();
		gerstner.simulate();

		Grid grid = new Grid();
		grid.init();
		grid.printGridCoordinatesToConsole();
	}

}
