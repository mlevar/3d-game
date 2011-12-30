import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;

public class DisplayLists extends Refactored 
{

  int box;
  TexturedCube m_Obj;
  IntBuffer m_Textures;
  
  protected void setupView()
  {
    super.setupView();
    // wireframe display
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
   
    // smooth shading - Gouraud
    GL11.glShadeModel(GL11.GL_SMOOTH);
    
    // lights
    GL11.glEnable(GL11.GL_LIGHTING);
    GL11.glEnable(GL11.GL_LIGHT0);

    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, allocFloats(new float[] { 2f, 2f, 2.0f, 0.0f}));
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, allocFloats(new float[] { 0.2f, 0.2f, 0.2f, 0.0f}));
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE , allocFloats(new float[] { 0.2f, 0.2f, 0.2f, 0.0f}));
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR , allocFloats(new float[] { 0.5f, 0.5f, 0.5f, 0.0f}));
    GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_LINEAR_ATTENUATION , 0.1f);
    
    // textures
    GL11.glEnable(GL11.GL_COLOR_MATERIAL);
    // enable 2D textures 
    GL11.glEnable(GL11.GL_TEXTURE_2D);
 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
    GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );    
 }

  protected void initializeModels()
  {
    m_Obj = new TexturedCube();
    box = GL11.glGenLists(1);
      GL11.glNewList(box, GL11.GL_COMPILE);
        m_Obj.render3D();
      GL11.glEndList();

    m_Textures= Texture.loadTextures2D(new String[] { "checker2.jpg" });    
  } 
    
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {
    GL11.glPushMatrix();
    GL11.glTranslatef(-0.6f, 1.5f, 0f);
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.15f * i, 0.15f * j, 0f);
        GL11.glRotatef(i*10f, 0, 1, 0);
        GL11.glScalef(0.05f, 0.05f, 0.05f);

        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, allocFloats(new float[] { 1.0f, 1.0f, 1.0f, 0.0f}));
        GL11.glColor3f(i/10f, j/10f, 0.1f);
        
        GL11.glCallList(box);
        
        GL11.glPopMatrix();
      }
    GL11.glPopMatrix();
  }
  
  /**
   * Processes Keyboard and Mouse input and spawns actions
   */  
  protected void processInput()
  {
    super.processInput();
  }
  
  public static void main(String[] args) {
    (new DisplayLists()).execute();
  }  
}
