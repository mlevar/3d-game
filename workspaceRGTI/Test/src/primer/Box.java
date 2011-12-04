package primer;

import org.lwjgl.opengl.GL11;

/*
 * A simple object in the game world.
 * @Author Stephen Jones
 */
public class Box {
	public Box() {
	}

	public void draw() {
		GL11.glTranslatef(0.0f, 2.5f, -10.0f);
		GL11.glBegin(GL11.GL_QUADS);
		// Front Face
		GL11.glNormal3f(0.0f, 0.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		// Back Face
		GL11.glColor3f(1.0f, 0.1f, 0.1f);
		GL11.glNormal3f(0.0f, 0.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		// Top Face
		GL11.glColor3f(0.0f, 1.0f, 0.1f);
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		// Bottom Face
		GL11.glColor3f(0.2f, 0.1f, 9.1f);
		GL11.glNormal3f(0.0f, -1.0f, 0.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		// Right face
		GL11.glNormal3f(1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);
		// Left Face
		GL11.glNormal3f(-1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glEnd();
	}
}