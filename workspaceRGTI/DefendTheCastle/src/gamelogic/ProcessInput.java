package gamelogic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.GameObject;
import objects.Player;
import objects.Puscica;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import rendering.*;
import gamelogic.Physics;

public class ProcessInput extends RenderCamera {
					

	Physics physics = new Physics();
	Text text;
	boolean pause = false;
	Meni m;

	// hide the mouse

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		text = new Text("Testni izpis", 50);
		super.setupView();

	}


	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {
		physics.checkCollisions(gameobjects);
		
		super.renderFrame(delta);
		
		/*
		text = new BitmapText();
		String napis = "abcDDQ";
	    startHUD();
	    
	    GL11.glColor4f(0, 1, 0, 1);
	    float w = text.textWidth(napis, 15);
	    GL11.glTranslatef(1024-w, 768-20, 0);
	    text.renderString(napis, 15);
	    
	    endHUD();
		*/
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

	
	
	/**
	 * Processes Keyboard and Mouse input and spawns actions
	 */
	protected void processInput() {
		
	if(!pause) {
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
		}else if(Mouse.isButtonDown(0) && !camera.puscicaIzstreljena) {
			camera.puscicaIzstreljena = true;
			float toRad = (float)Math.PI/180;
			float yaw = camera.getYaw() * toRad;
			float pitch = camera.getPitch() * toRad;
			
			float xl = (float)Math.cos(yaw);
			float zl = (float)Math.sin(yaw);
			float yl = (float)Math.tan(pitch) * zl;
			
			float l = (float)Math.sqrt((xl*xl) + (yl*yl) + (zl*zl));
			
			Vector3f look = new Vector3f(-zl/l, -yl/l, -xl/l);
			float xx = look.x;
			float yy = look.y;
			float zz = look.z;
			camera.smerPuscice.x = new Float(xx);
			camera.smerPuscice.y = new Float(yy);
			camera.smerPuscice.z = new Float(zz);
			boolean hitten = physics.checkShoot(look,camera,gameobjects);
			if(hitten) {
				//System.out.println("ANIMAL DEAD");
			}else {
				//System.out.println("Animal live");
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

	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_P)) {
		
		pause = true;
		for (GameObject go : gameobjects) {
			go.moving = false;
		}
			
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
		pause = false;
		for (GameObject go : gameobjects) {
			go.moving = true;
		}
	}
	
	if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
		Display.destroy();
		(new ProcessInput()).execute();
	}
	
		
	    
	    
		super.processInput();
		
		startHUD();
	    text.setPosition(10, 10, 0);
	    //text.render3D();   //ce odkomentiramo ne dela!!!
	    endHUD();
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

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}
	
	
	
}
