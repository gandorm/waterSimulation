package cellularAutomata;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayCA {
	  
	Automaton automaton;
	Algorithms algorithms;
	int checker = 0;
	
   private JLabel lblInput;     // Declare input Label
   private JTextField tfInput;  // Declare input TextField
   private JTextField tfOutput; // Declare output TextField
   private int numberIn;       // Input number
   private int sum = 0;  
   static final int WIND_MIN = 0;
   static final int WIND_MAX = 50;
   static final int WIND_INIT = 25;// Accumulated sum, init to 0
	
	
    public void start() throws LWJGLException {
    	
    	class stateChanged implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				System.out.print((int)source.getValue());
			} 
		}
    	
    	 Canvas openglSurface = new Canvas();
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
			  
			 
		     Arrow_Test test = new Arrow_Test();	     
		    
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
         Display.setParent(openglSurface);
	                
        try {
        Display.setDisplayMode(new DisplayMode(400,400));
        Display.create();
    } catch (LWJGLException e) {
        e.printStackTrace();
        System.exit(0);
    }
  
    // init OpenGL
    automaton = new Automaton();
    algorithms = new Algorithms();
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 700, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  
    while (!Display.isCloseRequested()) {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
         
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(1.0f,1.0f,1.0f);   
        
        
        drawAutomaton();
        cellUpdate();
        
        //if(checker==5) {
        ////generateTides();
       
        //	automaton.getAutomaton()[34][12].setState(0.7f);
        //	automaton.getAutomaton()[34][13].setState(0.7f);
        //	automaton.getAutomaton()[34][11].setState(0.7f);
        //	
    	//	automaton.getAutomaton()[25][27].setState(0.7f);
    	//	automaton.getAutomaton()[26][26].setState(0.7f);
    	//	automaton.getAutomaton()[27][25].setState(0.7f);
    	//	automaton.getAutomaton()[28][24].setState(0.7f);
    	//	automaton.getAutomaton()[29][23].setState(0.7f);
        //		
        //checker=0;
        //}
        //checker++;        
        automaton.getAutomaton()[34][12].setState(0.7f);
        
        
        Display.update();
        try{
        TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e){};
    }
  
    Display.destroy();
    }
  
    public void generateTides() {    	
    	automaton.getAutomaton()[12][13].setState(0.2f);
    	automaton.getAutomaton()[13][12].setState(0.2f);
    	automaton.getAutomaton()[14][13].setState(0.2f);
    	automaton.getAutomaton()[13][14].setState(0.2f);
    }
  
    public void actionPerformed(ActionEvent evt) {
        // Get the String entered into the TextField tfInput, convert to int
        numberIn = Integer.parseInt(tfInput.getText());
        sum += numberIn;      // Accumulate numbers entered into sum
        tfInput.setText("");  // Clear input TextField
        tfOutput.setText(sum + ""); // Display sum on the output TextField
                                    // convert int to String
     }
    
    public void draw() {
    	int gridSize = 6;
    	int padding_half = 1;
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glColor3f(1, 0, 0);
	    for(int x = 20; x < 120; x ++)
		{
	    	for(int y=10;y<110;y++){
	    	GL11.glVertex2i(gridSize*(x-1) + padding_half , gridSize*(y-1) + padding_half ); //bottom-left vertex	    	
	        GL11.glVertex2i(gridSize*(x-1) + padding_half , gridSize*y     - padding_half ); //top-left vertex
	        GL11.glVertex2i(gridSize*x     - padding_half , gridSize*y     - padding_half ); //top-right vertex
	        GL11.glVertex2i(gridSize*x     - padding_half , gridSize*(y-1) + padding_half ); //bottom-right vertex
			}
		}
        GL11.glEnd();
    }
    
    public void drawAutomaton() {
    	int gridSize = 6;
    	int padding_half = 1;
    	int startX = 20;
    	int startY = 10;
    	
	    GL11.glBegin(GL11.GL_QUADS);
	    for(int x = 0; x < 100; x ++)
		{
	    	for(int y=0;y<100;y++){	 	    	
	    	//Normalizacja
	    	if(!automaton.getAutomaton()[x][y].isWall()){
	    		double stan = automaton.getAutomaton()[x][y].getState();
	    		float state = (float) Math.sqrt(Math.abs(stan)) * (stan>0 ? +1 : -1);
	    		GL11.glColor3f(0,0,(state + 1)/2);
	    	}
	    	else
		    GL11.glColor3f(0.8f,0.8f,0);
  	
	    	
	    	GL11.glVertex2i(gridSize*(x + startX - 1) + padding_half , gridSize*(y + startY - 1) + padding_half ); //bottom-left vertex	    	
	        GL11.glVertex2i(gridSize*(x + startX - 1) + padding_half , gridSize*(y + startY)     - padding_half ); //top-left vertex
	        GL11.glVertex2i(gridSize*(x + startX ) 	  - padding_half , gridSize*(y + startY)     - padding_half ); //top-right vertex
	        GL11.glVertex2i(gridSize*(x + startX ) 	  - padding_half , gridSize*(y + startY -1)  + padding_half ); //bottom-right vertex
	        
			}
	    	
		}
        GL11.glEnd();
	    
    }
    
    public void cellUpdate(){

    	Cell[][] b = Algorithms.deepAutomatonClone(automaton.getAutomaton());

	    for(int x = Constants.X_DIMENSION -1; x >= 0; x--)
		{
	    	for(int y=Constants.Y_DIMENSION-1;y>=0;y--){
	    		double state = algorithms.countState(algorithms.setNeighbourhood(automaton.getAutomaton(), x, y));
	    		automaton.getAutomaton()[x][y].setState(state);
			}
		}
	    
	    
    }
    
    public static void main(String[] argv) throws LWJGLException {
        DisplayCA gridExample = new DisplayCA();
        gridExample.start();
    }
}
