package rendering;

import gamelogic.ProcessInput;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import objects.*;

public class RenderObjects extends RenderEnvironment {

	public GameObject[] gameobjects;

	public Obj3D kuraModel;
	public Obj3D merjasecModel;
	public Obj3D medvedModel;
	

	IntBuffer m_Textures;

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
				GL11.GL_MODULATE);

		kuraModel = new Obj3D("kura.obj");
		merjasecModel = new Obj3D("merjasec.obj");
		medvedModel = new Obj3D("medved.obj");

		gameobjects = new GameObject[5];

		for (int i = 3; i < 4; i++) {
			gameobjects[i] = new Merjasec(-50 + i * 5f, -3f, -50f,
					merjasecModel, 2);
			gameobjects[i].setDirection(0, 0, 0);
		}
		for (int i = 0; i < 3; i++) {
			gameobjects[i] = new Kura(-50 + i * 5f, -3f, -50f, kuraModel, 2);
		}

		for (int i = 4; i < 5; i++) {
			gameobjects[i] = new Medved(-50 + i * 5f, -3f, -50f, medvedModel, 2);
		}

		super.setupView();

	}

	/**
	 * Resets the view of current frame
	 */

	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {

		GL11.glColor3f(1, 1, 1);

		for (int i = 0; i < gameobjects.length; i++) {
			if (gameobjects[i].alive)
				gameobjects[i].move(delta);
		}
		
		

		GL11.glEnd();

		super.renderFrame(delta);
	}

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}
}
