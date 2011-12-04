import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;


public class BoxNew extends Model3D
{
  boolean wire = false;

  FloatBuffer m = BaseWindow.allocFloats(new float[16]);
  float[][] vs = {
      {-1.0f, -1.0f,  1.0f},
      { 1.0f, -1.0f,  1.0f},
      { 1.0f,  1.0f,  1.0f},
      {-1.0f,  1.0f,  1.0f},
      {-1.0f, -1.0f, -1.0f},
      {-1.0f,  1.0f, -1.0f},
      { 1.0f,  1.0f, -1.0f},
      { 1.0f, -1.0f, -1.0f}
  };
//  float[][] cs = new float[6][3];
  Matrix mcs = new Matrix(new double[4][6]);
  int[][] fs = {
      {4, 5, 6, 7}, {4, 7, 6, 5},
      {4, 7, 1, 0}, {4, 0, 1, 7},
      {7, 6, 2, 1}, {7, 1, 2, 6},
      {4, 0, 3, 5}, {4, 5, 3, 0},
      {0, 1, 2, 3}, {0, 3, 2, 1},
      {5, 3, 2, 6}, {5, 6, 2, 3}
  };
  
  Matrix mvm = new Matrix(new double[4][4]);
  
  float[][] centroids = new float[6][3];
  
  public BoxNew() {
    super();
    for (int i=0;i<6; i++) {
      float[] t  = calcCentroid(fs[2*i]);
      for (int j=0; j<3; j++)
        mcs.setValue(j, i, t[j]);
      mcs.setValue(3, i, 1);
    }
  }
  
  private float[] calcCentroid(int[] is) {
    float[] v = new float[3];
    for (int i=0; i<4; i++)
      for (int j=0; j<3; j++)
        v[j] += vs[is[i]][j];
    v[0] /=4; v[1] /= 4; v[2] /= 4;
    return v;
  }
  
  public void setWire(boolean wire)
  {
    this.wire = wire;
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
    if (m_rZ!=0)
      GL11.glRotatef(m_rZ, 0, 0, 1);
    if (m_rY!=0)
      GL11.glRotatef(m_rY, 0, 1, 0);
    if (m_rX!=0)
      GL11.glRotatef(m_rX, 1, 0, 0);
    if (m_sX!=1 || m_sY!=1 || m_sZ!=1)
      GL11.glScalef(m_sX, m_sY, m_sZ);

    renderModel();
    
    // discard current matrix
    GL11.glPopMatrix();
  }
  private Matrix tmvm = Matrix.multiply(mvm, mcs);
  private float[][] order = new float[2][6];
  
  private void renderModel()
  {
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    
    if (wire)
      GL11.glColor4f(0.1f, 0.2f, 0.3f, 1f);
    else
      GL11.glColor4f(0.3f, 0.6f, 0.8f, 0.5f);
    
//    Getting ModelView Matrix
    m.rewind();
    GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, m);
    for (int i=0; i<4; i++)
      for (int j=0; j<4; j++)
        mvm.setValue(j, i, m.get());

//    Multiply centroid vectors of box faces with ModelView Matrix
    tmvm = Matrix.multiply(mvm, mcs);
    
//    Get centorids Z-values
    for (int i=0; i<6; i++) {
      order[0][i] = i;
      order[1][i] = (float)tmvm.getValue(2, i);
    }
    
//    Sort centroids according to their distance from viewer
    for (int i=0; i<5; i++)
      for (int j=i+1; j<6; j++)
        if(order[1][i] > order[1][j]) {
          float tmp = order[1][i];
          order[1][i] = order[1][j];
          order[1][j] = tmp;
          tmp = order[0][i];
          order[0][i] = order[0][j];
          order[0][j] = tmp;
        }
    
//    Draw quads in correct order
    for (int i=0; i<6; i++) {
      drawQuad(fs[(int)order[0][i]*2]);
      drawQuad(fs[(int)order[0][i]*2+1]);
    }

    GL11.glDisable(GL11.GL_BLEND);
  }
  
  private void drawQuad(int[] is) {
    GL11.glBegin(GL11.GL_QUADS);
      GL11.glVertex3f(vs[is[0]][0], vs[is[0]][1], vs[is[0]][2]);
      GL11.glVertex3f(vs[is[1]][0], vs[is[1]][1], vs[is[1]][2]);
      GL11.glVertex3f(vs[is[2]][0], vs[is[2]][1], vs[is[2]][2]);
      GL11.glVertex3f(vs[is[3]][0], vs[is[3]][1], vs[is[3]][2]);
    GL11.glEnd();
  }
}
