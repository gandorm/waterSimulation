package CellularAutomata;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
public class Main {
 
	int screenX = 600;
	int screenY = 600;
	int squareSize = 20;
	
	public float getRandomColor(){
		Random rn = new Random();
		return rn.nextFloat() + 0; // (max - min + 1)+ min
	}
    public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(screenX,screenY));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
 
	// init OpenGL
	GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glOrtho(0, screenX, 0, screenY, 1, -1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
	while (!Display.isCloseRequested()) {
	    // Clear the screen and depth buffer
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
 
	    // set the color of the quad (R,G,B,A)
	    //GL11.glColor3f(0.5f,0.5f,1.0f);
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	    
	    for(int i=0; i<(screenX/squareSize); i++){
	    	if (i%2 == 0)
	    		GL11.glColor3f(getRandomColor(),getRandomColor(),getRandomColor());
	    	else
	    		GL11.glColor3f(getRandomColor(),getRandomColor(),getRandomColor());
	    	for(int j=0; j<(screenY/squareSize); j++){	    		
			    GL11.glVertex2f(squareSize*i,squareSize*j);
				GL11.glVertex2f(i*squareSize+squareSize,squareSize*j);
				GL11.glVertex2f(i*squareSize+squareSize,squareSize*j+squareSize);
				GL11.glVertex2f(i*squareSize,squareSize*j+squareSize);
	    	}
	    }
	    
	    GL11.glEnd();
	    Display.update();
	}
 
	Display.destroy();
    }
 
    public static void main(String[] argv) {
        Main quadExample = new Main();
        quadExample.start();
    }
}