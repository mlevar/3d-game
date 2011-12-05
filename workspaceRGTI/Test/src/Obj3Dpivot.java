import glmodel.GLModel;

import org.lwjgl.opengl.GL11;

public class Obj3Dpivot extends Model3D {
	protected float m_pX, m_pY, m_pZ;

	GLModel m_Obj = null;

	Obj3Dpivot(String p_strFileName) {
		try {
			m_Obj = new GLModel(p_strFileName);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void setPivot(float p_X, float p_Y, float p_Z) {
		m_pX = p_X;
		m_pY = p_Y;
		m_pZ = p_Z;
	}

	public void render3D() {
		// model view stack
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		// save current matrix
		GL11.glPushMatrix();

		// TRANSLATE
		
		if (m_rZ != 0) {
//			GL11.glTranslatef(m_pX, m_pY, m_pZ);
			GL11.glRotatef(m_rZ, 0, 0, 1);
//			GL11.glTranslatef(-m_pX, -m_pY, -m_pZ);
		}
		if (m_rY != 0) {
//			GL11.glTranslatef(m_pX, m_pY, m_pZ);
			GL11.glRotatef(m_rY, 0, 1, 0);
//			GL11.glTranslatef(-m_pX, -m_pY, -m_pZ);
		}
		if (m_rX != 0) {
//			GL11.glTranslatef(m_pX, m_pY, m_pZ);
			GL11.glRotatef(m_rX, 1, 0, 0);
//			GL11.glTranslatef(-m_pX, -m_pY, -m_pZ);
		}
		GL11.glTranslatef(m_nX, m_nY, m_nZ);
		GL11.glScalef(m_sX, m_sY, m_sZ);

		m_Obj.render();

		GL11.glPopMatrix();
	}
}
