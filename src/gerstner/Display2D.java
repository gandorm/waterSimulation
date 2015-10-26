package gerstner;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Display2D {

	private long timeAtLastFrame;
	private long lastFPS;
	private int fps;
	private double time = 0;

	private Grid grid;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(Parameters.GRID_SIZE_X * Parameters.GRID_DISPLAY_MULTIPLIER, Parameters.GRID_SIZE_Y * Parameters.GRID_DISPLAY_MULTIPLIER));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}


		initGL();						// initialize OpenGL
		getDelta();						// call once before loop to initialize lastFrame
		lastFPS = getTime();			// call before loop to initialize FPS timer

		grid = new Grid();				// initialize grid
		grid.init();


		while(!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60);			// cap at 60fps
		}

		Display.destroy();
	}

	private void renderGL() {
		// Clear the screen and the depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		simulate(grid);

	}

	private void simulate(Grid grid) {
		double amplitude = 1.0;
		double[] wavevector = {1,1};
		double omega = 2 / Math.PI;

		// go through every grid point and apply height change
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid.getGrid()[i][k].changeHeightOverTime(amplitude, wavevector, omega, time);
				drawCell(grid.getGrid()[i][k]);
			}

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

	private void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Parameters.GRID_SIZE_X * Parameters.GRID_DISPLAY_MULTIPLIER, 0, Parameters.GRID_SIZE_Y * Parameters.GRID_DISPLAY_MULTIPLIER, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - timeAtLastFrame);
		timeAtLastFrame = time;

		return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void update(int delta) {
		// let the time pass
		time += 0.15 * delta;
		updateFPS();
	}


	public static void main(String[] args) {
		Display2D display2d = new Display2D();
		display2d.start();
	}

}
