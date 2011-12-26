package rendering;
import gamelogic.ProcessInput;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import objects.*;

public class RenderEnvironment extends BaseWindow {

	Okolje okolje;
	

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		// enable depth buffer (off by default)
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		// enable culling of back sides of polygons
		GL11.glEnable(GL11.GL_CULL_FACE);

		// mapping from normalized to window coordinates
		GL11.glViewport(0, 0, width, height);

		// setup projection matrix stack
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		GLU.gluPerspective(45, width/ (float) height, 0.01f, 900.0f);

	//	GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,	GL11.GL_MODULATE);

		
		
		okolje = new Okolje(0, 0, 0);
		super.setupView();
	}

	/**
	 * Resets the view of current frame
	 */
	protected void resetView() {

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		super.resetView();
	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {

		GL11.glColor3f(1, 1, 1);

		okolje.render();
		
		GL11.glEnd();
		super.renderFrame(delta);
		
	}
	
	

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}
}
