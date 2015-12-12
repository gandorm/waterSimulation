package gerstner;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<Wave> waves;

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
		
		waves = new ArrayList<Wave>();


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

	/*private void simulate(Grid grid) {
		double amplitude = 0.7;
		double[] wavevector = {2, 3};
		double omega = 2 / Math.PI;

		// go through every grid point and apply height change
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid.getGrid()[i][k].changeHeightOverTime(amplitude, wavevector, omega, time);
				drawCell(grid.getGrid()[i][k]);
			}
		}

	}*/
	
	private void simulate(Grid grid) {
		generateWaves();
		
		// go through every grid point and apply height change
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid.getGrid()[i][k].changeHeightOverTime(waves, time);
				drawCell(grid.getGrid()[i][k]);
			}
		}
		
		waves.clear();
	}
	
	private void generateWaves() {
		Wave w1 = new Wave();
		Wave w2 = new Wave();
		Wave w3 = new Wave();
		Wave w4 = new Wave();
		
		w1.setAmplitude(0.8);
		w2.setAmplitude(0.6);
		w3.setAmplitude(0.4);
		w4.setAmplitude(0.3);
		
		w1.setWavevector(1.0, 2.0);
		w2.setWavevector(1, 0);
		w3.setWavevector(1.7, 3.3);
		w4.setWavevector(0.7, 1.2);
		
		waves.add(w1);
		waves.add(w2);
		waves.add(w3);
		waves.add(w4);
	}

	private void drawCell(Point point) {

		// display particular cell of a wave
		// color depends on its height field
		// wow much formula very calculate
		double blueIntensity = ((Math.abs(point.getH())));
		System.out.println(point.getH());
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
		time += Parameters.TIME_PASS * delta;
		updateFPS();
	}


	public static void main(String[] args) {
		Display2D display2d = new Display2D();
		display2d.start();
	}

}
