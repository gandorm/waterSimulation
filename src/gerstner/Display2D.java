package gerstner;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Display2D {

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(Parameters.GRID_SIZE_X * Parameters.GRID_DISPLAY_MULTIPLIER, Parameters.GRID_SIZE_Y * Parameters.GRID_DISPLAY_MULTIPLIER));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// init OpenGl
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Parameters.GRID_SIZE_X * Parameters.GRID_DISPLAY_MULTIPLIER, 0, Parameters.GRID_SIZE_Y * Parameters.GRID_DISPLAY_MULTIPLIER, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		// init Grid
		Grid grid = new Grid();
		grid.init();

		while(!Display.isCloseRequested()) {

			// Clear the screen and depth buffer
			//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			simulate(grid);

			Display.update();
		}

		Display.destroy();
	}

	public void simulate(Grid grid) {
		double amplitude = 1.0;
		double[] wavevector = {1,1};
		double omega = 2 / Math.PI;
		double time = 0;

		// go through every grid point and apply height change
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid.getGrid()[i][k].changeHeightOverTime(amplitude, wavevector, omega, time);
				drawCell(grid.getGrid()[i][k]);
			}
			time++;
		}
	}

	private void drawCell(Point point) {

		// display particular cell of a wave
		// color depends on its height field
		// wow much formula very calculate
		double blueIntensity = ((point.getH() + 1) * 0.5);
		glColor3d(0.0, 0.0, blueIntensity);
		glBegin(GL_QUADS);
			glVertex2d(point.getX(), point.getY());																					// bottom left
			glVertex2d(point.getX(), point.getY() + Parameters.GRID_DISPLAY_MULTIPLIER);											// top left
			glVertex2d(point.getX() + Parameters.GRID_DISPLAY_MULTIPLIER, point.getY() + Parameters.GRID_DISPLAY_MULTIPLIER);		// top right
			glVertex2d(point.getX() + Parameters.GRID_DISPLAY_MULTIPLIER, point.getY());											// bottom right
		}


	public static void main(String[] args) {
		Display2D display2d = new Display2D();
		Grid grid = new Grid();
		grid.init();
		display2d.start();
	}

}
