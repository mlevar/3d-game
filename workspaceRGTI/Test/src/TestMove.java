import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

public class TestMove extends Test {

	float posX = 0, posY = 0, rotX = 0, rotY = 0, scale = 1, rotZ = 0,
			posZ = 0;

	Player camera;
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f; // length of frame
	float lastTime = 0.0f; // when the last frame was
	float time = 0.0f;

	float mouseSensitivity = 0.05f;
	float movementSpeed = 10.0f; // move 10 units per second
	
	int wheel = Mouse.getDWheel();


	

	// hide the mouse

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		


		super.setupView();
		camera = new Player(0, +1.3f, 0, 1);
		

		Mouse.setGrabbed(true);

		
		setCameraMatrix();
	}

	protected void setCameraMatrix() {
		// model view stack
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// setup view space;
		// translate to 0,2,4 and rotate 30 degrees along x
		GL11.glTranslatef(0, -2f, -4.0f);
		GL11.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame() {

		// // model view stack
		// GL11.glMatrixMode(GL11.GL_MODELVIEW);
		//
		// // save camera matrix
		// GL11.glPushMatrix();
		//
		// // TRANSLATE by X
		// GL11.glTranslatef(posX, posY, posZ);
		//
		// // ROTATE around X - bring to center, rotate, bring back
		// GL11.glTranslatef(posX, posY, posZ);
		// GL11.glRotatef(rotY, 0, 1, 0);
		// GL11.glRotatef(rotX, 1, 0, 0);
		// GL11.glRotatef(rotZ, 0, 0, 1);
		// GL11.glScalef(scale, scale, scale);
		// GL11.glTranslatef(posX, posY, posZ);



		GL11.glLoadIdentity();
		// look through the camera before you draw anything
		camera.lookThrough();
		
//		System.out.println("P:"+Float.toString(camera.position.x) +" "+Float.toString(camera.position.y)+" "+Float.toString(camera.position.z));
//		System.out.println("H:"+Float.toString(camera.hand.m_nX) +" "+Float.toString(camera.hand.m_nY)+" "+Float.toString(camera.hand.m_nZ));

		
		
		// draw pyramid
		super.renderFrame();
		camera.render();
		
		
		
		
		// discard current matrix
		GL11.glPopMatrix();
		GL11.glEnd();
		
	}
	
	

	protected boolean checkPlayerCollision(double newX, double newZ) {
		//float x = camera.getPosition().x;
		//float z = camera.getPosition().z;
		//System.out.print(x + " -- ");
		//System.out.println(z + "--");
		//System.out.println(38-z);
		
		/*
		if(Math.abs(270 - newZ) < 0.3) {
			return true;
			
		}else if(Math.abs(-7 - newZ) < 0.3) {
			return true;
		}else if(Math.abs(44 - newX) < 0.3) {
			
			return true;
		}else if(Math.abs(-44 - newX) < 0.3) {
			
			return true;
		}
		else {
			return false;
		}
		*/
		
		
		double zC1 = (newX * 8.61) - 384.78;
		double zC2 = (newX * (-8.71)) - 388.78;
		if(Math.abs(zC1 - newZ) < 0.8) {
			return true; 
		}else if(Math.abs(newZ - zC2) < 0.8) {
			return true;
		}else if(newZ < -7) {
			return true;
		}else if(newZ > 258) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//check if animal is shoot
	public boolean checkShoot(Vector3f v) {
		
		Vector3f camPos = camera.getPosition();
		for (int i=0;i<merjasci.length;i++) {
			Vector3f merPos = merjasci[i].getPosition();
			float dist = (float)Math.sqrt(Math.pow((-camPos.x - merPos.x),2) + Math.pow((camPos.z + merPos.z),2));
			Vector3f vz = new Vector3f(-(v.x*dist+camPos.x)-1, v.y*dist+camPos.y, v.z*dist-camPos.z);
			//System.out.println("Pozicija cloveka: " + camPos);
			//System.out.println("Pozicija zivali: " + merPos);
			//System.out.println("Razdalja clovek - zival: " + dist);
			//System.out.println("Vektor pogleda: " + v);
			//System.out.println("Zracunan vektor: " + vz);
			float distCA = (float)Math.sqrt(Math.pow((vz.x - merPos.x),2) + Math.pow((vz.y - merPos.y - 4.3),2) + Math.pow((vz.z - merPos.z),2));
			//System.out.println("Razdalja: " + distCA);
			if(distCA < 1f) {
				merjasci[i].alive = false;
				return true;
			}
		}
		
		for (int i=0;i<kure.length;i++) {
			Vector3f merPos = kure[i].getPosition();
			float dist = (float)Math.sqrt(Math.pow((-camPos.x - merPos.x),2) + Math.pow((camPos.z + merPos.z),2));
			Vector3f vz = new Vector3f(-(v.x*dist+camPos.x)-1, v.y*dist+camPos.y, v.z*dist-camPos.z);
			//System.out.println("Pozicija cloveka: " + camPos);
			//System.out.println("Pozicija zivali: " + merPos);
			//System.out.println("Razdalja clovek - zival: " + dist);
			//System.out.println("Vektor pogleda: " + v);
			//System.out.println("Zracunan vektor: " + vz);
			float distCA = (float)Math.sqrt(Math.pow((vz.x - merPos.x),2) + Math.pow((vz.y - merPos.y - 4.3),2) + Math.pow((vz.z - merPos.z),2));
			//System.out.println("Razdalja: " + distCA);
			if(distCA < 1f) {
				kure[i].alive = false;
				return true;
			}
		}
		
		for (int i=0;i<medvedi.length;i++) {
			Vector3f merPos = medvedi[i].getPosition();
			float dist = (float)Math.sqrt(Math.pow((-camPos.x - merPos.x),2) + Math.pow((camPos.z + merPos.z),2));
			Vector3f vz = new Vector3f(-(v.x*dist+camPos.x)-1, v.y*dist+camPos.y, v.z*dist-camPos.z);
			//System.out.println("Pozicija cloveka: " + camPos);
			//System.out.println("Pozicija zivali: " + merPos);
			//System.out.println("Razdalja clovek - zival: " + dist);
			//System.out.println("Vektor pogleda: " + v);
			//System.out.println("Zracunan vektor: " + vz);
			float distCA = (float)Math.sqrt(Math.pow((vz.x - merPos.x),2) + Math.pow((vz.y - merPos.y - 4.3),2) + Math.pow((vz.z - merPos.z),2));
			//System.out.println("Razdalja: " + distCA);
			if(distCA < 1f) {
				medvedi[i].alive = false;
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Processes Keyboard and Mouse input and spawns actions
	 */
	protected void processInput() {

	
		// keep looping till the display window is closed the ESC key is down
		time = System.currentTimeMillis();
		dt = (time - lastTime) / 1000.0f;
		lastTime = time;
		dt = 0.01f;
		// distance in mouse movement from the last getDX() call.
		dx = Mouse.getDX();
		// distance in mouse movement from the last getDY() call.
		dy = Mouse.getDY();

		// controll camera yaw from x movement fromt the mouse
		camera.yaw(dx * mouseSensitivity);
		// controll camera pitch from y movement fromt the mouse
		camera.pitch(dy * mouseSensitivity);

		// when passing in the distance to move
		// we times the movementSpeed with dt this is a time scale
		// so if its a slow frame u move more then a fast frame
		// so on a slow computer you move just as fast as on a fast computer
		
		/*
		float xP = camera.getPosition().x;
		float zP = camera.getPosition().z;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))// move forward
		{
			camera.walkForward(movementSpeed * dt);
			System.out.println("x: " + xP + " -- Z: " + zP);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))// move backwards
		{
			camera.walkBackwards(movementSpeed * dt);
			System.out.println("x: " + xP + " -- Z: " + zP);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))// strafe left
		{
			camera.strafeLeft(movementSpeed * dt);
			System.out.println("x: " + xP + " -- Z: " + zP);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))// strafe right
		{
			camera.strafeRight(movementSpeed * dt);
			System.out.println("x: " + xP + " -- Z: " + zP);
		}
		*/
		
		if(Mouse.isButtonDown(0) && !camera.lok){
			camera.hit=true;
		}else if(Mouse.isButtonDown(0)) {
			float toRad = (float)Math.PI/180;
			float yaw = camera.getYaw() * toRad;
			float pitch = camera.getPitch() * toRad;
			
			float xl = (float)Math.cos(yaw);
			float zl = (float)Math.sin(yaw);
			float yl = (float)Math.tan(pitch) * zl;
			
			float l = (float)Math.sqrt((xl*xl) + (yl*yl) + (zl*zl));
			
			Vector3f look = new Vector3f(-zl/l, -yl/l, -xl/l);
			
			boolean hitten = checkShoot(look);
			if(hitten) {
				System.out.println("ANIMAL DEAD");
			}else {
				System.out.println("Animal live");
			}
			
			//System.out.println("yaw: "+yaw +" :: pich: "+pitch+" :: "+look);
			//System.out.println(Math.sin(30*Math.PI/180));
			//System.out.println(merjasci[0].getPosition());
			
		}
		
		if(Mouse.getDWheel() != wheel) {
			wheel = Mouse.getDWheel();
			
			if(camera.lok) {
				camera.lok = false;
			}else {
				camera.lok = true;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_2))// move forward
		{
			if(camera.lok) {
				camera.lok = false;
			}else {
				camera.lok = true;
			}
		}
		
		//float xP = camera.getPosition().x;
		//float zP = camera.getPosition().z;
		// detekcije trkov igralca in mej okolja
		if (Keyboard.isKeyDown(Keyboard.KEY_W))// move forward
		{
			if(!checkPlayerCollision(camera.tryXForward(movementSpeed * dt), camera.tryZForward(movementSpeed*dt))) {
				camera.walkForward(movementSpeed * dt);
				
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))// move backwards
		{
			if(!checkPlayerCollision(camera.tryXBackwards(movementSpeed * dt), camera.tryZBackwards(movementSpeed*dt))) {
				camera.walkBackwards(movementSpeed * dt);
				
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))// strafe left
		{
			if(!checkPlayerCollision(camera.tryXLeft(movementSpeed * dt), camera.tryZLeft(movementSpeed*dt))) {
				camera.strafeLeft(movementSpeed * dt);
				
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))// strafe right
		{
			if(!checkPlayerCollision(camera.tryXRight(movementSpeed * dt), camera.tryZRight(movementSpeed*dt))) {
				camera.strafeRight(movementSpeed * dt);
				
			}
		}


		super.processInput();
	}

	public static void main(String[] args) {
		(new TestMove()).execute();
	}
}



// if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
// posX -= 0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
// posX += 0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_UP))
// posY += 0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
// posY -= 0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_Q))
// rotX++;
//
// if (Keyboard.isKeyDown(Keyboard.KEY_W))
// posZ+= 0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_S))
// posZ -= 0.01;
//
// if (Keyboard.isKeyDown(Keyboard.KEY_A))
// posX += 0.01;
//
// if (Keyboard.isKeyDown(Keyboard.KEY_D))
// posX -= 0.01;
//
// if (Keyboard.isKeyDown(Keyboard.KEY_E))
// rotY++;
//
// rotY+=0.3*Mouse.getDX();
// rotX-=0.3*Mouse.getDY();
//
// Mouse.setCursorPosition(500, 500);

// if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
// posX-=0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
// posX+=0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_UP))
// posY+=0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
// posY-=0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_Q))
// rotX++;
// if (Keyboard.isKeyDown(Keyboard.KEY_A))
// rotX--;
// if (Keyboard.isKeyDown(Keyboard.KEY_E))
// rotY++;
// if (Keyboard.isKeyDown(Keyboard.KEY_D))
// rotY--;
// if (Keyboard.isKeyDown(Keyboard.KEY_W))
// scale+=0.01;
// if (Keyboard.isKeyDown(Keyboard.KEY_S))
// scale-=0.01;
/* detekcije trkov igralca in mej okolja
if (Keyboard.isKeyDown(Keyboard.KEY_W))// move forward
{
	if(!checkPlayerCollision(camera.tryXForward(movementSpeed * dt), camera.tryZForward(movementSpeed*dt))) {
		camera.walkForward(movementSpeed * dt);
	}
}
if (Keyboard.isKeyDown(Keyboard.KEY_S))// move backwards
{
	if(!checkPlayerCollision(camera.tryXBackwards(movementSpeed * dt), camera.tryZBackwards(movementSpeed*dt))) {
		camera.walkBackwards(movementSpeed * dt);
	}
}
if (Keyboard.isKeyDown(Keyboard.KEY_A))// strafe left
{
	if(!checkPlayerCollision(camera.tryXLeft(movementSpeed * dt), camera.tryZLeft(movementSpeed*dt))) {
		camera.strafeLeft(movementSpeed * dt);
	}
}
if (Keyboard.isKeyDown(Keyboard.KEY_D))// strafe right
{
	if(!checkPlayerCollision(camera.tryXRight(movementSpeed * dt), camera.tryZRight(movementSpeed*dt))) {
		camera.strafeRight(movementSpeed * dt);
	}
}

*/
// set the modelview matrix back to the identity

// you would draw your scene here.