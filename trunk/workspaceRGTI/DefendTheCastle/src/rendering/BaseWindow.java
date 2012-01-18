package rendering;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;

import gamelogic.ProcessInput;

import java.nio.*;

public class BaseWindow {

	protected static boolean isRunning = false;
	protected static int width = 1024;
	protected static int height = 768;
	protected static long lastFPS;
	protected static int fps;
	protected static long lastFrame;

	public static void main(String[] args) {
		// What version of OpenGL is supported?

		// Start our program

		(new ProcessInput()).execute();
	}

	/**
	 * Initializes display and enters main loop
	 */
	protected void execute() {
		try {
			initDisplay();
		} catch (LWJGLException e) {
			System.err.println("Can't open display.");
			System.exit(0);
		}

		BaseWindow.isRunning = true;
		mainLoop();
		Display.destroy();
	}

	/**
	 * Main loop: renders and processes input events
	 */
	protected void mainLoop() {
		// setup camera and lights
		setupView();
		int delta = getDelta();
		lastFrame-=1;
		while (BaseWindow.isRunning) {
			
			delta = getDelta();
			
			// reset view
			resetView();

			// let subsystem paint
			renderFrame(delta);

			// process input events
			processInput(delta);

			// update window contents and process input messages
			Display.update();
			Display.sync(80);
		}
	}

	/**
	 * Initial setup of projection of the scene onto screen, lights, etc.
	 */
	protected void setupView() {
		lastFPS = getTime();
	}

	/**
	 * Resets the view of current frame
	 */
	protected void resetView() {
		
	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {
		updateFPS();
	}

	/**
	 * Processes Keyboard and Mouse input and spawns actions
	 */
	
	protected void processInput(long delta) {
		/*
		if (Display.isCloseRequested()
				|| Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			
			this.isRunning = false;
		}
		*/
	}
	 
	/**
	 * Finds best 1024x768 display mode and sets it
	 * 
	 * @throws LWJGLException
	 */
	protected void initDisplay() throws LWJGLException {
		DisplayMode bestMode = null;
		DisplayMode[] dm = Display.getAvailableDisplayModes(); // finds best
																// display mode
																// from
																// available
		for (int nI = 0; nI < dm.length; nI++) {
			DisplayMode mode = dm[nI];
			if (mode.getWidth() == width && mode.getHeight() == height
					&& mode.getFrequency() <= 85) {
				if (bestMode == null
						|| (mode.getBitsPerPixel() >= bestMode
								.getBitsPerPixel() && mode.getFrequency() > bestMode
								.getFrequency()))
					bestMode = mode;

			}
		}

		Display.setDisplayMode(bestMode);
		// FSAA
		Display.create(new PixelFormat(8, 8, 8, 4));
		// No FSAA
		// Display.create();
		Display.setTitle(this.getClass().getName());

		// output
		System.out.println("GL_VERSION: " + GL11.glGetString(GL11.GL_VERSION));
		System.out.println("GL_VENDOR: " + GL11.glGetString(GL11.GL_VENDOR));
		System.out
				.println("GL_RENDERER: " + GL11.glGetString(GL11.GL_RENDERER));
	}

	/**
	 * Utils for creating native buffers
	 * 
	 * @throws LWJGLException
	 */
	// ignore for now:
	public static ByteBuffer allocBytes(int howmany) {
		return ByteBuffer.allocateDirect(howmany)
				.order(ByteOrder.nativeOrder());
	}

	public static IntBuffer allocInts(int howmany) {
		return ByteBuffer.allocateDirect(howmany)
				.order(ByteOrder.nativeOrder()).asIntBuffer();
	}

	public static FloatBuffer allocFloats(int howmany) {
		return ByteBuffer.allocateDirect(howmany)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
	}

	public static ByteBuffer allocBytes(byte[] bytearray) {
		ByteBuffer bb = ByteBuffer.allocateDirect(bytearray.length * 1).order(
				ByteOrder.nativeOrder());
		bb.put(bytearray).flip();
		return bb;
	}

	public static IntBuffer allocInts(int[] intarray) {
		IntBuffer ib = ByteBuffer.allocateDirect(intarray.length * 4)
				.order(ByteOrder.nativeOrder()).asIntBuffer();
		ib.put(intarray).flip();
		return ib;
	}

	public static FloatBuffer allocFloats(float[] floatarray) {
		FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(floatarray).flip();
		return fb;
	}

	public long getTime() {
		return (Sys.getTime() * 1000)/ Sys.getTimerResolution();// 
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0; // reset the FPS counter
			lastFPS += 1000; // add one second
		}
		fps++;
	}
	public int getDelta() {
	    long time = getTime();
//	    System.out.println("time:"+time);
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
}
