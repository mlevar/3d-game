import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CameraRefactored extends BaseWindow
{
  BitmapText text;
  int fps = 0;
  boolean running = true;

  float posX = 0, posY = 0, posZ = -10f, rotX = 0, rotY = 0, fov = 45;

  Box b;

  Camera cam1, cam2, cam3, cam4;

  /**
   * Initial setup of projection of the scene onto screen, lights etc.
   */
  protected void setupView()
  {
    // textures
    // select modulate to mix texture with color for shading; GL_REPLACE,
    // GL_MODULATE ...
    GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
        GL11.GL_MODULATE);

    initializeModels();

    // enable depth buffer (off by default)
    GL11.glEnable(GL11.GL_DEPTH_TEST);
    // enable culling of back sides of polygons
    GL11.glEnable(GL11.GL_CULL_FACE);

    // mapping from normalized to window coordinates
    GL11.glViewport(0, 0, 512, 384);

    cam1 = new Camera();
    cam2 = new Camera();
    cam3 = new Camera();
    cam4 = new Camera();
  }

  /**
   * can be used for 3D model initialization
   */
  protected void initializeModels()
  {
    text = new BitmapText();
    b = new Box();
  }

  /**
   * Resets the view of current frame
   */
  protected void resetView()
  {
    // clear color and depth buffer
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    GL11.glClearColor(1, 1, 1, 1);

    // cam.setPersp(fov, 4f/3f, 1, 30);

    GL11.glViewport(0, 0, 512, 384);

    
    cam1.setOrtho(-120 / 30, -120 / 40, 120 / 30, 120 / 40, -1, 100);
    cam1.setPosition(posX, posY, posZ);
    cam1.setRotation(rotX, rotY, 0f);
    cam1.setupView();
    
    renderFrame1();
    
    GL11.glViewport(512, 0, 512, 384);
    cam2.setPersp(fov, 4f / 3f, 1, 30);
    cam2.setPosition(posX, posY, posZ);
    cam2.setRotation(rotX, rotY, 0f);
    cam2.setupView();
    
    renderFrame2();
    
    GL11.glViewport(0, 384, 512, 512);
    
    cam3.setOrtho(-120 / 30, -120 / 40, 120 / 30, 120 / 40, -1, 100);
    cam3.setPosition(posX, posY, posZ);
    cam3.setRotation(rotX, rotY, 0f);
    cam3.setupView();
    
    renderFrame3();
    
    GL11.glViewport(512, 384, 512, 384);
    cam4.setPersp(fov,2f / 1f, 1, 30);
    cam4.setPosition(posX, posY, posZ);
    cam4.setRotation(rotX, rotY, 0f);
    cam4.setupView();
   
    renderFrame4();
  }

  /**
   * Renders current frame
   */
//  protected void renderFrame()
//  {
//    long tic = System.currentTimeMillis();
//
//    b.render3D();
//    b.setWire(true);
//    b.render3D();
//    b.setWire(false);
//
//    // HUD & Text render
//    startHUD();
//
//    GL11.glColor4f(0, 0, 0, 1);
//    float w = text.textWidth("" + fps, 15);
//    GL11.glTranslatef(1024 - w, 768 - 20, 0);
//    text.renderString("" + fps, 15);
//
//    endHUD();
//
//    long tac = System.currentTimeMillis();
//    if ((tac - tic) > 0)
//      fps = (int) (1000 / (tac - tic));
//  }
  
  protected void renderFrame1()
  {
    cam1.setupView();
    long tic = System.currentTimeMillis();

    b.render3D();
    b.setWire(true);
    b.render3D();
    b.setWire(false);

    // HUD & Text render
    startHUD();

    GL11.glColor4f(0, 0, 0, 1);
    float w = text.textWidth("" + fps, 15);
    GL11.glTranslatef(1024 - w, 768 - 20, 0);
    text.renderString("" + fps, 15);

    endHUD();

    long tac = System.currentTimeMillis();
    if ((tac - tic) > 0)
      fps = (int) (1000 / (tac - tic));
  }
  protected void renderFrame2()
  {
    cam2.setupView();
    long tic = System.currentTimeMillis();

    b.render3D();
    b.setWire(true);
    b.render3D();
    b.setWire(false);

    // HUD & Text render
    startHUD();

    GL11.glColor4f(0, 0, 0, 1);
    float w = text.textWidth("" + fps, 15);
    GL11.glTranslatef(1024 - w, 768 - 20, 0);
    text.renderString("" + fps, 15);

    endHUD();

    long tac = System.currentTimeMillis();
    if ((tac - tic) > 0)
      fps = (int) (1000 / (tac - tic));
  }
  protected void renderFrame3()
  {
    
    cam3.setupView();
    long tic = System.currentTimeMillis();

    b.render3D();
    b.setWire(true);
    b.render3D();
    b.setWire(false);

    // HUD & Text render
    startHUD();

    GL11.glColor4f(0, 0, 0, 1);
    float w = text.textWidth("" + fps, 15);
    GL11.glTranslatef(1024 - w, 768 - 20, 0);
    text.renderString("" + fps, 15);

    endHUD();

    long tac = System.currentTimeMillis();
    if ((tac - tic) > 0)
      fps = (int) (1000 / (tac - tic));
  }
  protected void renderFrame4()
  {
    cam4.setupView();
    long tic = System.currentTimeMillis();

    b.render3D();
    b.setWire(true);
    b.render3D();
    b.setWire(false);

    // HUD & Text render
    startHUD();

    GL11.glColor4f(0, 0, 0, 1);
    float w = text.textWidth("" + fps, 15);
    GL11.glTranslatef(1024 - w, 768 - 20, 0);
    text.renderString("" + fps, 15);

    endHUD();

    long tac = System.currentTimeMillis();
    if ((tac - tic) > 0)
      fps = (int) (1000 / (tac - tic));
  }

  protected void startHUD()
  {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glOrtho(0, 1024, 0, 768, -1, 1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
  }

  protected void endHUD()
  {
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
      posX -= 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
      posX += 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_UP))
      posY += 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
      posY -= 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_HOME))
      posZ -= 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_END))
      posZ += 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_Q))
      rotX++;
    if (Keyboard.isKeyDown(Keyboard.KEY_A))
      rotX--;
    if (Keyboard.isKeyDown(Keyboard.KEY_E))
      rotY++;
    if (Keyboard.isKeyDown(Keyboard.KEY_D))
      rotY--;
    if (Keyboard.isKeyDown(Keyboard.KEY_W))
      fov += 0.01;
    if (Keyboard.isKeyDown(Keyboard.KEY_S))
      fov -= 0.01;

    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
    {
      running = false;
      System.exit(0);
    }

    super.processInput();
  }

  public static void main(String[] args)
  {
    (new CameraRefactored()).execute();
  }
}
