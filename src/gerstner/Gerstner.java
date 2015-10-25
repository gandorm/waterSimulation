package gerstner;

public class Gerstner {

	private Grid surface;

	public void init() {
		this.surface = new Grid();
		this.surface.init();
	}

	public void simulate() {
		double amplitude = 1.0;
		double[] wavevector = {1,1};
		double omega = 2 / Math.PI;
		double time = 0;

		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				surface.getGrid()[i][k].changeHeightOverTime(amplitude, wavevector, omega, time);
			}
			time++;
			surface.printGridToConsole();
		}

	}
}
