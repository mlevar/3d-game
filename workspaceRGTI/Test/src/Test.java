import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import org.ode4j.ode.*;

public class Test extends BaseWindow {

	Okolje okolje;
	Kura[] kure;
	Merjasec[] merjasci;
	Medved[] medvedi;
	
	Obj3D kuraModel;
	Obj3D merjasecModel;
	Obj3D medvedModel;


	IntBuffer m_Textures;
	
	

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		// enable depth buffer (off by default)
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		// enable culling of back sides of polygons
		GL11.glEnable(GL11.GL_CULL_FACE);

		// mapping from normalized to window coordinates
		GL11.glViewport(0, 0, 1024, 768);

		// setup projection matrix stack
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		// orthographic projection
		// GL11.glOrtho(-5,5,-5,5,1,30);
		// perspective projection (45% FOV, 4/3 aspect, clipping near 1, far
		// 30);
		GLU.gluPerspective(45, 1024 / (float) 768, 0.01f, 900.0f);

		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
				GL11.GL_MODULATE);

		okolje = new Okolje(0, 0, 0);

		kuraModel = new Obj3D("kura.obj");
		merjasecModel = new Obj3D("merjasec.obj");
		medvedModel = new Obj3D("medved.obj");
		
		kure = new Kura[3];
		for (int i=0;i<kure.length;i++) {
			kure[i]= new Kura(-20+i*20f, -3f, -300f, 1f, kuraModel);
		}
		merjasci = new Merjasec[2];
		for (int i=0;i<merjasci.length;i++) {
			merjasci[i]= new Merjasec(-20+i*40f, -3f, -300f, 1f, merjasecModel);
		}
		medvedi = new Medved[1];
		for (int i=0;i<medvedi.length;i++) {
			medvedi[i]= new Medved(0+i*30, -3f, -300f, 1f, medvedModel);
		}

		m_Textures = Texture.loadTextures2D(new String[] { "ceiling.jpg" });
		
		

	}

	/**
	 * Resets the view of current frame
	 */
	protected void resetView() {
		// clear color and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame() {

		// GL11.glBegin(GL11.GL_TRIANGLES); // draw independent triangles
		// GL11.glColor3f(0, 0, 1);
		// GL11.glVertex3f(-1.0f, -0.5f, -4.0f); // lower left vertex
		// GL11.glVertex3f( 0.0f, 0.5f, -4.0f); // upper vertex
		// GL11.glVertex3f( 1.0f, -0.5f, -4.0f); // lower right vertex
		//
		// GL11.glColor3f(1, 0, 0);
		// GL11.glVertex3d(-1.0f, -0.5f, -4.0f); // lower left vertex
		// GL11.glVertex3f( 1.0f, -0.5f, -4.0f); // lower right vertex
		// GL11.glVertex3f( 0.0f, -0.5f, -3.0f); // lower front vertex
		//
		// GL11.glColor3f(1, 1, 0);
		// GL11.glVertex3f(-1.0f, -0.5f, -4.0f); // lower left vertex
		// GL11.glVertex3f( 0.0f, -0.5f, -3.0f); // lower front vertex
		// GL11.glVertex3f( 0.0f, 0.5f, -4.0f); // upper vertex
		//
		// GL11.glColor3f(0, 1, 1);
		// GL11.glVertex3f( 1.0f, -0.5f, -4.0f); // lower right vertex
		// GL11.glVertex3f( 0.0f, 0.5f, -4.0f); // upper vertex
		// GL11.glVertex3f( 0.0f, -0.5f, -3.0f); // lower front vertex

		GL11.glColor3f(1, 1, 1);

		for (int i = 0; i < kure.length; i++) {
			if(kure[i].alive)
				kure[i].move();
		}
		for (int i = 0; i < merjasci.length; i++) {
			if (merjasci[i].alive)
				merjasci[i].move();
		}
		for (int i = 0; i < medvedi.length; i++) {
			if(medvedi[i].alive)
				medvedi[i].move();
		}

		okolje.render();
		
		
		

		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Textures.get(0));
		/*
		 * // space GL11.glBegin(GL11.GL_QUADS); GL11.glColor3f(0.5f, 0.2f,
		 * 0.1f); GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-58.0f, -3.0f,
		 * 32.0f); GL11.glTexCoord2f(0.0f, 1.0f); GL11.glVertex3f(58.0f, -3.0f,
		 * 32.0f); GL11.glTexCoord2f(1.0f, 0.0f); GL11.glVertex3f(58.0f, -3.0f,
		 * -38.0f); GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-58.0f,
		 * -3.0f, -38.0f);
		 * 
		 * GL11.glColor3f(0.5f, 0.1f, 0.5f); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(-58.0f, -3.0f, -38.0f); GL11.glTexCoord2f(0.0f,
		 * 1.0f); GL11.glVertex3f(58.0f, -3.0f, -38.0f); GL11.glTexCoord2f(1.0f,
		 * 0.0f); GL11.glVertex3f(58.0f, 13.0f, -38.0f); GL11.glTexCoord2f(1.0f,
		 * 1.0f); GL11.glVertex3f(-58.0f, 13.0f, -38.0f);
		 * 
		 * GL11.glColor3f(0.1f, 0.5f, 0.5f); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(-58.0f, -3.0f, 32.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(-58.0f, -3.0f, -38.0f); GL11.glTexCoord2f(1.0f,
		 * 0.0f); GL11.glVertex3f(-58.0f, 13.0f, -38.0f);
		 * GL11.glTexCoord2f(1.0f, 1.0f); GL11.glVertex3f(-58.0f, 13.0f, 32.0f);
		 * 
		 * GL11.glColor3f(0.5f, 0.5f, 0.1f); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(58.0f, -3.0f, 32.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(58.0f, 13.0f, 32.0f); GL11.glTexCoord2f(1.0f, 0.0f);
		 * GL11.glVertex3f(58.0f, 13.0f, -38.0f); GL11.glTexCoord2f(1.0f, 1.0f);
		 * GL11.glVertex3f(58.0f, -3.0f, -38.0f);
		 * 
		 * GL11.glColor3f(0.1f, 0.5f, 0.1f); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(-58.0f, -3.0f, 32.0f); GL11.glTexCoord2f(1.0f, 0.0f);
		 * GL11.glVertex3f(-58.0f, 13.0f, 32.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(58.0f, 13.0f, 32.0f); GL11.glTexCoord2f(1.0f, 1.0f);
		 * GL11.glVertex3f(58.0f, -3.0f, 32.0f);
		 * 
		 * // cube GL11.glColor3f(0, 1, 1); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(-4.0f, -3.0f, -12.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(4.0f, -3.0f, -12.0f); GL11.glTexCoord2f(1.0f, 0.0f);
		 * GL11.glVertex3f(4.0f, 3.0f, -12.0f); GL11.glTexCoord2f(1.0f, 1.0f);
		 * GL11.glVertex3f(-4.0f, 3.0f, -12.0f); GL11.glColor3f(0, 1, 1);
		 * GL11.glVertex3f(-4.0f, 3.0f, -12.0f); GL11.glVertex3f(4.0f, 3.0f,
		 * -12.0f); GL11.glVertex3f(4.0f, -3.0f, -12.0f); GL11.glVertex3f(-4.0f,
		 * -3.0f, -12.0f);
		 * 
		 * GL11.glColor3f(1, 1, 0); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(-4.0f, -3.0f, -4.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(-4.0f, -3.0f, -12.0f); GL11.glTexCoord2f(1.0f, 0.0f);
		 * GL11.glVertex3f(-4.0f, 3.0f, -12.0f); GL11.glTexCoord2f(1.0f, 1.0f);
		 * GL11.glVertex3f(-4.0f, 3.0f, -4.0f); GL11.glColor3f(1, 1, 0);
		 * GL11.glVertex3f(-4.0f, 3.0f, -4.0f); GL11.glVertex3f(-4.0f, 3.0f,
		 * -12.0f); GL11.glVertex3f(-4.0f, -3.0f, -12.0f);
		 * GL11.glVertex3f(-4.0f, -3.0f, -4.0f);
		 * 
		 * GL11.glColor3f(0, 1, 0); GL11.glTexCoord2f(0.0f, 0.0f);
		 * GL11.glVertex3f(4.0f, -3.0f, -4.0f); GL11.glTexCoord2f(0.0f, 1.0f);
		 * GL11.glVertex3f(4.0f, 3.0f, -4.0f); GL11.glTexCoord2f(1.0f, 0.0f);
		 * GL11.glVertex3f(4.0f, 3.0f, -12.0f); GL11.glTexCoord2f(1.0f, 1.0f);
		 * GL11.glVertex3f(4.0f, -3.0f, -12.0f); GL11.glColor3f(0, 1, 0);
		 * GL11.glVertex3f(4.0f, -3.0f, -12.0f); GL11.glVertex3f(4.0f, 3.0f,
		 * -12.0f); GL11.glVertex3f(4.0f, 3.0f, -4.0f); GL11.glVertex3f(4.0f,
		 * -3.0f, -4.0f);
		 */
		GL11.glEnd();
		
		
	}
	
	

	public static void main(String[] args) {
		(new TestMove()).execute();
	}
}
