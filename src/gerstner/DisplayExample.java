package gerstner;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class DisplayExample {

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
		}
	}
}
