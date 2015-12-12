package cellularAutomata;

import java.util.concurrent.TimeUnit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayCA {
	  
	Automaton automaton;
	Algorithms algorithms;
	
    public void start() {
        try {
        Display.setDisplayMode(new DisplayMode(800,700));
        Display.create();
    } catch (LWJGLException e) {
        e.printStackTrace();
        System.exit(0);
    }
  
    // init OpenGL
    automaton = new Automaton();
    automaton.init();
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
       // cellUpdate();
        
        Display.update();
        try{
        TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e){};
    }
  
    Display.destroy();
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
	    	GL11.glColor3f(1, 1, 1);
	    	if (automaton.getAutomaton()[x][y].getState() < 0.00000000000001f && automaton.getAutomaton()[x][y].getState() > 0.00000000000000001f) {
	    		GL11.glColor3f(0.9f,0.443f,0.232f);																			
	    	}
	    	if (automaton.getAutomaton()[x][y].getState() < 0.00000000000000001f && automaton.getAutomaton()[x][y].getState() > 0.00000000000000000001f) {
	    		GL11.glColor3f(0.4f,0.443f,0.232f);
	    	}
	    	if (automaton.getAutomaton()[x][y].getState() < 0.0000000001f && automaton.getAutomaton()[x][y].getState() > 0.00000000000001f) {
	    		GL11.glColor3f(0.1f,0.443f,0.232f);
	    	}
	    	
	    	if (automaton.getAutomaton()[x][y].getState() == 0.0f) {
	    		GL11.glColor3f(0.8f,0.8f,0.0f);
	    	}
	    	/*
	    	if (automaton.getAutomaton()[x][y].getState() > 0.00000000000001f && automaton.getAutomaton()[x][y].getState() > 0.00000000000000001f) {
	    		GL11.glColor3f(0.9f,0.443f,0.232f);
	    	}*/
	    	
	    	GL11.glVertex2i(gridSize*(x + startX - 1) + padding_half , gridSize*(y + startY - 1) + padding_half ); //bottom-left vertex	    	
	        GL11.glVertex2i(gridSize*(x + startX - 1) + padding_half , gridSize*(y + startY)     - padding_half ); //top-left vertex
	        GL11.glVertex2i(gridSize*(x + startX ) 	  - padding_half , gridSize*(y + startY)     - padding_half ); //top-right vertex
	        GL11.glVertex2i(gridSize*(x + startX ) 	  - padding_half , gridSize*(y + startY -1)  + padding_half ); //bottom-right vertex
	        
			}
	    	
		}
        GL11.glEnd();
	    
    }
    
    public void cellUpdate(){
	    for(int x = 1; x < 99; x ++)
		{
	    	for(int y=1;y<99;y++){
	    		float state = algorithms.countState(algorithms.setNeighbourhood(automaton.getAutomaton(), x, y));
	    		automaton.getAutomaton()[x][y].setState(state);
			}
		}
	    
	    /*
	    for(int x = 1; x < 99; x ++)
		{
	    	for(int y=1;y<99;y++){
	    		//float state = algorithms.smooth(algorithms.setNeighbourhood(automaton.getAutomaton(), x, y));
	    		automaton.getAutomaton()[x][y].setState(state);
			}
		}
		*/
    }
    
    public static void main(String[] argv) {
        DisplayCA gridExample = new DisplayCA();
        gridExample.start();
    }
}
