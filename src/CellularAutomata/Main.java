package CellularAutomata;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import static org.lwjgl.glfw.GLFWvidmode.*;


public class Main {

	public boolean running = false;
	public long window;
	public int width = 800, height = 600;

	public void init() {
		this.running = true;

		if(glfwInit() != GL_TRUE) {
			System.err.println("init fails");
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		window = glfwCreateWindow(width,height,"Test",NULL, NULL);

		if (window == NULL) {
			System.err.println("window creation fails");
		}

		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,100,100);

		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}

	public void render() {
		glfwSwapBuffers(window);
	}

	public void update() {
		glfwPollEvents();
	}

	public void run() {
		init();
		while(running) {
			update();
			render();

			if(glfwWindowShouldClose(window) == GL_TRUE) {
				running = false;
			}
		}
	}

	public static void main(String args[]){
		Main main = new Main();
		main.run();
	}
}
