package rendering;

import gamelogic.Game;
import gamelogic.Physics;
import gamelogic.ProcessInput;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import objects.*;


public class RenderObjects extends RenderEnvironment {

	public int level = 1;
	
	public Physics physics = new Physics();
	public Game game;
	public GameObject[] gameobjects;




	IntBuffer m_Textures;

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
				GL11.GL_MODULATE);


		game = new Game();
		
		gameobjects = game.level5();


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
			else if (gameobjects[i].delayed)
				gameobjects[i].delay(delta);
		}
		
		

		GL11.glEnd();

		super.renderFrame(delta);
		
		
		

		
		
	}

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}
}
