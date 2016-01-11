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

/*Swing*/

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Display2D {

	private long timeAtLastFrame;
	private long lastFPS;
	private int fps;
	private double time = 0;

	private JLabel lblInput;     // Declare input Label
   private JTextField tfInput;  // Declare input TextField
   private JTextField tfOutput; // Declare output TextField
   private int numberIn;       // Input number
   private int sum = 0;  
   static final int WIND_MIN = 0;
   static final int WIND_MAX = 50;
   static final int WIND_INIT = 25;// Accumulated sum, init to 0
   Canvas openglSurface = new Canvas();
   Arrow_Test test = new Arrow_Test();	
   
   static int WIND_SPEED = 0;
   static int WIND_DIRECTION = 0;
   
	private Grid grid;
	
	private List<Wave> waves;

	public void guiInit(){
		class stateChanged implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				WIND_SPEED = (int)source.getValue();
				System.out.print(WIND_SPEED);
			} 
		}
    	
         JFrame frame = new JFrame();
         frame.setLayout(new BorderLayout());
         
         frame.setVisible(true);
         frame.add(new JTextField("Hello World!"));
         openglSurface.setSize(800, 800);
         
         JPanel panel = new JPanel();
         panel.setSize(200,200);
         JButton b1 = new JButton("one");
         
         b1.setPreferredSize(new Dimension(100, 100));
         b1.setVisible(true);
         panel.add(b1);
         
         //Sila wiatru
         JPanel main = new JPanel();
         
	        JPanel wind = new JPanel();
	        
	 		lblInput = new JLabel("Wybierz sile wiatru: ");
	 		wind.setLayout(new BoxLayout(wind, BoxLayout.PAGE_AXIS));		
      
 		    JSlider windSpeed = new JSlider(JSlider.HORIZONTAL,
 		             WIND_MIN, WIND_MAX, WIND_INIT);
 		    
 			 windSpeed.setMajorTickSpacing(10);
 		     windSpeed.setMinorTickSpacing(1);
 		     windSpeed.setPaintTicks(true);
 		     windSpeed.setPaintLabels(true);		     
 		     windSpeed.addChangeListener(new stateChanged());		     
 		  	     
 		     wind.add(lblInput);
 		     wind.add(windSpeed);
	 		 wind.setPreferredSize(new Dimension(400, 200));
		     wind.setVisible(true);
		     
		   //Strzalka
		     			 
			 JPanel arrow = new JPanel();
			 arrow.setLayout(new BoxLayout(arrow, BoxLayout.PAGE_AXIS));
			     
		     arrow.setPreferredSize(new Dimension(200, 200));
		     arrow.setMinimumSize(arrow.getPreferredSize());
		     arrow.add(test,Box.createRigidArea(new Dimension(0,5)));
		     arrow.add(test.getSlider(), "Last");		     
		     
		     main.setLayout(new BorderLayout());
		     main.add(wind, BorderLayout.WEST);
		     main.add(arrow, BorderLayout.CENTER);
 		     
         
         frame.add(main, BorderLayout.SOUTH);
         frame.add(openglSurface, BorderLayout.CENTER);
         frame.setSize(1000, 1000);
         
	}
	public void start() throws LWJGLException {
		guiInit();
		Display.setParent(openglSurface);
		try {
			Display.setDisplayMode(new DisplayMode(Parameters.GRID_SIZE_X * Parameters.GRID_DISPLAY_MULTIPLIER, Parameters.GRID_SIZE_Y * Parameters.GRID_DISPLAY_MULTIPLIER));
			//Display.setDisplayMode(new DisplayMode(800, 600));
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
		
		waves = new ArrayList<Wave>();	// initialize waves
		generateWaves();


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
		
		// go through every grid point and apply height change
		for(int i = 0; i < Parameters.GRID_SIZE_X; i++) {
			for(int k = 0; k < Parameters.GRID_SIZE_Y; k++) {
				grid.getGrid()[i][k].evaluateGerstnerWaveMovement(waves, time);
				drawCell(grid.getGrid()[i][k]);
			}
		}
	}
	
	private void generateRandomWaves() {
		double amplitude, x, y;
		boolean isFirstCalculated = true;
		double xOffset = 0.0;
		double yOffset = 0.0;
		Random random = new Random();
		
		for(int i = 0; i < Parameters.WAVE_COUNT; i++) {
			amplitude = (0.1 + (random.nextDouble() * 0.7));
			if(isFirstCalculated) {
				x = (1.0 + (random.nextDouble() * 2.0));
				xOffset = x;
				y = (1.0 + (random.nextDouble() * 2.0));
				yOffset = y;
			} else {
				x = xOffset + (random.nextDouble() * 2.0);
				y = yOffset + (random.nextDouble() * 2.0);
			}
			isFirstCalculated = false;
			waves.add(new Wave(amplitude, x, y));
		}
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
		w2.setWavevector(1, 3);
		w3.setWavevector(2, 2);
		w4.setWavevector(1, 0);
		
		waves.add(w1);
		waves.add(w2);
		waves.add(w3);
		waves.add(w4);
	}
	
	private double calculateBlueIntensity(double value){
		double blueIntensity = 0;
		if (value < 0)
			return blueIntensity = 0;
		if (value > 1.7)
			return blueIntensity = 1;
		else
			return blueIntensity = (value*100)/255;	
	}

	private void drawCell(Point point) {

		// display particular cell of a wave
		// color depends on its height field
		// wow much formula very calculate
		double blueIntensity = ((Math.abs(point.getH())));
		//double blueIntensity = calculateBlueIntensity(Math.abs(point.getH()));					
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


	public static void main(String[] args) throws LWJGLException {
		Display2D display2d = new Display2D();
		display2d.start();
	}

}
