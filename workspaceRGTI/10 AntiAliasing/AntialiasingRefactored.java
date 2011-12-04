import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class AntialiasingRefactored extends BaseWindow 
{
  BitmapText text;
  int fps = 0; 
  boolean running = true;
  
  float posX = 0, posY = 0, posZ = 0, rotX = 0, rotY = 0, scale = 1;
  
  Triangles triangles;
  
	/**
	 * Initial setup of projection of the scene onto screen, ldights etc.
	 */
  protected void setupView()
	{    
    // textures
 // select modulate to mix texture with color for shading; GL_REPLACE, GL_MODULATE ...
    GL11.glTexEnvf( GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE );
    
    initializeModels();
    
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
    //GL11.glOrtho(-5,5,-5,5,1,30);
    // perspective projection (45% FOV, 4/3 aspect, clipping near 0, far 30);
    GLU.gluPerspective(45, 1024 / (float)768, 1.0f, 30.0f);

    setCameraMatrix();    
	}
    
  protected void setCameraMatrix()
  {
    // model view stack 
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    // setup view space; 
    GL11.glTranslatef(0, 0f, -10.0f);
  }

  /** 
   * can be used for 3D model initialization
   */
  protected void initializeModels()
  {
    text = new BitmapText();
    triangles = new Triangles(1, 0, 0);
  }
  /**
   * Resets the view of current frame
   */
  protected void resetView()
  {
    // clear color and depth buffer
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    GL11.glClearColor(1, 1, 1, 1);
  }
  
  /**
   * Renders current frame
   */
  protected void renderFrame()
  {
    long tic = System.currentTimeMillis();
    
//    Brez antialiasinga
    triangles.setPosition(-1, 0, 0);
    triangles.setColor(1, 0, 0);
    triangles.render3D();
    
    
//    Z antialiasingom
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glHint(GL11.GL_POLYGON_SMOOTH, GL11.GL_NICEST);
    GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
    
    triangles.setPosition(1, 0, 0);
    triangles.setColor(0, 0, 1);
    triangles.render3D();

    GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
    GL11.glDisable(GL11.GL_BLEND);
    
//  HUD & Text render
   startHUD();
   
   GL11.glColor4f(0, 0, 0, 1);
   float w = text.textWidth(""+fps, 15);
   GL11.glTranslatef(1024-w, 768-20, 0);
   text.renderString(""+fps, 15);
   
   endHUD();
   
   long tac = System.currentTimeMillis();
   if ((tac - tic) > 0)
     fps  = (int)(1000 / (tac - tic));
  }
  
  protected void startHUD() {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 1024, 0, 768, -1, 1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
  }
  
  protected void endHUD() {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPopMatrix();
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPopMatrix();
  }
  
  /**
   * Processes Keyboard and Mouse input and spawns actions
   */  
  protected void processInput()
  {
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
      posX-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
      posX+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_UP))
      posY+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
      posY-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_HOME))
      posZ-=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_END))
      posZ+=0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_Q))
      rotX++;
    if (Keyboard.isKeyDown(Keyboard.KEY_A))
      rotX--;    
    if (Keyboard.isKeyDown(Keyboard.KEY_E))
      rotY++;
    if (Keyboard.isKeyDown(Keyboard.KEY_D))
      rotY--;    
    if (Keyboard.isKeyDown(Keyboard.KEY_W))
      scale+=0.01;    
    if (Keyboard.isKeyDown(Keyboard.KEY_S))
      scale-=0.01;    
    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      running = false;
      System.exit(0);
    }
            
    super.processInput();
  }
  
  public static void main(String[] args) {
    (new AntialiasingRefactored()).execute();
  }  
}
