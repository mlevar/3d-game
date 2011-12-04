import org.lwjgl.opengl.GL11;


public class Triangles extends Model3D
{
  
  private float[] color = {0f, 0f, 0f};

  public Triangles(float r, float g, float b) {
    color[0] = r;
    color[1] = g;
    color[2] = b;
  }
  
  public void setColor(float r, float g, float b) {
    color[0] = r;
    color[1] = g;
    color[2] = b;
  }

  public void render3D()
  {
    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);

    // save current matrix
    GL11.glPushMatrix();

    // TRANSLATE 
    GL11.glTranslatef(m_nX, m_nY, m_nZ);

    // ROTATE and SCALE
//    GL11.glTranslatef(0, 0, -5f);
    if (m_rZ!=0)
      GL11.glRotatef(m_rZ, 0, 0, 1);
    if (m_rY!=0)
      GL11.glRotatef(m_rY, 0, 1, 0);
    if (m_rX!=0)
      GL11.glRotatef(m_rX, 1, 0, 0);
    if (m_sX!=1 || m_sY!=1 || m_sZ!=1)
      GL11.glScalef(m_sX, m_sY, m_sZ);
    GL11.glTranslatef(0, 0, 0f);    

    renderModel();

    // discard current matrix
    GL11.glPopMatrix();
  }

  private void renderModel()
  {
    GL11.glBegin(GL11.GL_TRIANGLES);
      GL11.glColor3f(color[0], color[1], color[2]);
      GL11.glVertex3f(-0.7f, -0.5f, 0);
      GL11.glVertex3f(0.7f, -0.5f, 0);
      GL11.glVertex3f(0f, 1f, 0);
    GL11.glEnd();
  }
}
