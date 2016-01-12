package cellularAutomata;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayCA {
	  
	Automaton automaton;
	Automaton bufor;
	Algorithms algorithms;
	int checker = 0;
	int speed = 150;
	int frequencyInt = 50;
	
   private JLabel lblInput, lblInput2;     // Declare input Label
   private JTextField tfInput;  		   // Declare input TextField
   private JTextField tfOutput; 		   // Declare output TextField
   private int numberIn;       			   // Input number
   private int sum = 0;   
   static final int WIND_MIN = 25;
   static final int WIND_MAX = 300;
   static final int WIND_INIT = 100;	   // Accumulated sum, init to 0
	
	
    public void start() throws LWJGLException {
    	
    	class stateChanged implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				speed = source.getValue();
			} 
		}
    	
    	class stateChangedFreq implements ChangeListener{
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider sourceFreq = (JSlider)e.getSource();
				frequencyInt = sourceFreq.getValue();
			} 
		}
    	
    	 Canvas openglSurface = new Canvas();
         JFrame frame = new JFrame();
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.setLayout(new BorderLayout());
         
         frame.setVisible(true);
         openglSurface.setSize(800, 800);
         
         JPanel panel = new JPanel();
        
         
         //Sila wiatru
         JPanel main = new JPanel();
         
	        JPanel wind = new JPanel();
	        
	 		lblInput = new JLabel("Wybierz predkosc symulacji: ");
	 		wind.setLayout(new BoxLayout(wind, BoxLayout.PAGE_AXIS));		
      
 		    JSlider windSpeed = new JSlider(JSlider.HORIZONTAL,
 		             WIND_MIN, WIND_MAX, WIND_INIT);
 		    
 			 windSpeed.setMajorTickSpacing(30);
 		     windSpeed.setMinorTickSpacing(1);
 		     windSpeed.setPaintTicks(true);
 		     windSpeed.setPaintLabels(true);		     
 		     windSpeed.addChangeListener(new stateChanged());	
 		     
 		     wind.add(lblInput);
		     wind.add(windSpeed);
	 		 wind.setPreferredSize(new Dimension(400, 200));
		     wind.setVisible(true);
 		  	/*cz. generowania*/
 		     
		     JPanel frequency = new JPanel();
		     frequency.setLayout(new BoxLayout(frequency, BoxLayout.PAGE_AXIS));
 		    JLabel lblInput2 = new JLabel("Wybierz czestotliwosc: ");
 		    	      
 		    JSlider freq = new JSlider(JSlider.HORIZONTAL,
 		             WIND_MIN, WIND_MAX, WIND_INIT);
 		    
 		    freq.setMajorTickSpacing(30);
 		   	freq.setMinorTickSpacing(1);
 		  	freq.setPaintTicks(true);
 		 	freq.setPaintLabels(true);		     
 			freq.addChangeListener(new stateChangedFreq());
 			
 			frequency.add(lblInput2);
 			frequency.add(freq);
 			frequency.setPreferredSize(new Dimension(400, 200));
 			frequency.setVisible(true);
 		     
 			 
 		   //Check box
 		   
 		  JCheckBox chinButton = new JCheckBox("Chin"); 
 		    chinButton.setSelected(true);
 		    chinButton.addItemListener(null);
 		    
 		    //Buttons
		   JButton rain = new JButton("Reset");
		   rain.setActionCommand(null);
		   JButton waveGen = new JButton("Generuj Fale");
		   waveGen.setActionCommand(null);
			
		   //pomocniczy jpanel 
		   JPanel d = new JPanel();
		   d.setLayout(new BorderLayout());
		   d.add(rain, BorderLayout.WEST);
		   d.add(waveGen, BorderLayout.CENTER);
		   d.add(chinButton, BorderLayout.EAST);
		   d.setPreferredSize(new Dimension(400, 200));
		   d.setVisible(true);
		   
		     main.setLayout(new BorderLayout());
		     main.add(wind, BorderLayout.WEST);
		     main.add(freq, BorderLayout.CENTER);
			 main.add(d, BorderLayout.NORTH);
		         
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
  
    automaton = new Automaton();
    bufor = new Automaton();
    algorithms = new Algorithms();
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 700, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  
    while (!Display.isCloseRequested()) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        GL11.glColor3f(1.0f,1.0f,1.0f);   
        
        drawAutomaton();
        cellUpdate();
        
        if(checker==frequencyInt) {
        		generateWave();
        		checker=0;
        }
        checker++;        
        
        Display.update();
        try{
        TimeUnit.MILLISECONDS.sleep(speed);
        } catch (Exception e){};
    }
  
    Display.destroy();
    }
    
    public void generateWave() {
    	for(int i = 0 ; i<100;i++) {
    		automaton.getAutomaton()[1][i].setState(1.0f);
    	}
    }
    
    public void resetCa(){
        automaton = new Automaton();
        bufor = new Automaton();
        frequencyInt = 0;
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
	    for(int x = 2; x <98; x ++)
		{
	    	for(int y=2;y<98;y++){	 	    	
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
	    for(int x = 0; x < Constants.X_DIMENSION ; x++)
		{
	    	for(int y=0;y<Constants.Y_DIMENSION; y++){
	    		if(!bufor.getAutomaton()[x][y].isWall()){
	    			double state = algorithms.countState(algorithms.setNeighbourhood(bufor.getAutomaton(), x, y),automaton.getAutomaton()[x][y].getState());
	    			automaton.getAutomaton()[x][y].setState(state);
	    			automaton.getAutomaton()[x][y].setWall(automaton.getAutomaton()[x][y].isWall());
	    		}
	    	}
		}
	    
	    for(int x = 0; x < Constants.X_DIMENSION ; x++)
		{
	    	for(int y=0;y<Constants.Y_DIMENSION; y++){
	    		double state = bufor.getAutomaton()[x][y].getState();
	    		bufor.getAutomaton()[x][y].setState(automaton.getAutomaton()[x][y].getState());
	    		automaton.getAutomaton()[x][y].setState(state);
			}
		}
    }
    
    public static void main(String[] argv) throws LWJGLException {
        DisplayCA gridExample = new DisplayCA();
        gridExample.start();
    }
}
