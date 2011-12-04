import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Camera extends Model3D
{
  private boolean perspective = false;
  public float fov = 45, aspectRatio = 4f/3f, near = 1.0f, far = 30.0f;
  public float minX = -1, minY = -1, maxX = 1, maxY = 1;

  public Camera() {
  }
  
  public void setPersp(float fv, float ar, float n, float f) {
    perspective = true;
    fov = fv;
    aspectRatio = ar;
    near = n;
    far = f;
  }
  
  public void setOrtho(float mx, float my, float Mx, float My, float n, float f) {
    perspective = false;
    minX = mx;
    minY = my;
    maxX = Mx;
    maxY = My;
    near = n;
    far = f;
  }
  
  public void setupView() {
//     setup projection matrix stack
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    if (perspective) {
//       perspective projection (45% FOV, 4/3 aspect, clipping near 0, far 30);
      GLU.gluPerspective(fov, aspectRatio, near, far);
    } else {
//       orthographic projection 
      GL11.glOrtho(minX,maxX,minY,maxY,near,far);
    }
    

    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    // setup view space; 
    GL11.glTranslatef(m_nX, m_nY, m_nZ);
    
    if (m_rZ!=0)
      GL11.glRotatef(m_rZ, 0, 0, 1);
    if (m_rY!=0)
      GL11.glRotatef(m_rY, 0, 1, 0);
    if (m_rX!=0)
      GL11.glRotatef(m_rX, 1, 0, 0);
    
  }

  @Override
  public void render3D()
  {
    // TODO Auto-generated method stub
  }
}
