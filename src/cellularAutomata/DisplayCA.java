package cellularAutomata;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DisplayCA {
	  
    public void start() {
        try {
        Display.setDisplayMode(new DisplayMode(800,600));
        Display.create();
    } catch (LWJGLException e) {
        e.printStackTrace();
        System.exit(0);
    }
  
    // init OpenGL
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 800, 0, 600, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  
    while (!Display.isCloseRequested()) {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
         
        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(1.0f,1.0f,1.0f);   
        drawCA();
  
        Display.update();
    }
  
    Display.destroy();
    }
    
    public void drawCA() {


    	GL11.glBegin(GL11.GL_LINES);
		for(int i = 150; i <= 650; i += 5)
		{
			//vertical lines
			GL11.glVertex3f((float)i, 50.0f, 0.0f);
			GL11.glVertex3f((float)i, 550.0f, 0.0f);
			
			//horizontal lines
			GL11.glVertex3f(150.0f, (float)i-100, 0.0f);
			GL11.glVertex3f(650.0f, (float)i-100, 0.0f);
		}
		GL11.glEnd();
    	
    }
  
    public static void main(String[] argv) {
        DisplayCA gridExample = new DisplayCA();
        gridExample.start();
    }
}
