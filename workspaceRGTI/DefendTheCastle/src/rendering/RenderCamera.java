package rendering;
import gamelogic.ProcessInput;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import objects.*;

public class RenderCamera extends RenderObjects {

	public float posX = 0, posY = 0, rotX = 0, rotY = 0, scale = 1, rotZ = 0,
			posZ = 0;

	public Player camera;
	public 	float dx = 0.0f;
	public 	float dy = 0.0f;
	public float dt = 0.0f; // length of frame
	public float lastTime = 0.0f; // when the last frame was
	public float time = 0.0f;

	public 	float mouseSensitivity = 0.05f;
	public 	float movementSpeed = 10.0f; // move 10 units per second
	
	public int wheel = Mouse.getDWheel();


	

	// hide the mouse

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		


		super.setupView();
		camera = new Player(0, +1.3f, 0, 1);
		

		Mouse.setGrabbed(true);

		
		setCameraMatrix();
	}

	protected void setCameraMatrix() {
		// model view stack
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// setup view space;
		// translate to 0,2,4 and rotate 30 degrees along x
		GL11.glTranslatef(0, -2f, -4.0f);
		GL11.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {

	

		GL11.glLoadIdentity();

		camera.lookThrough();
		

		super.renderFrame(delta);
		camera.render(delta);
		
		
		
		// discard current matrix
		GL11.glPopMatrix();
		GL11.glEnd();
		
	}
	
	

	/**
	 * Processes Keyboard and Mouse input and spawns actions
	 */
	protected void processInput() {

		super.processInput();
	}

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}
}


