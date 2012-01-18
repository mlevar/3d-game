package gamelogic;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.GameObject;
import objects.Okolje;
import objects.Player;
import objects.Puscica;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.XRandR.Screen;
import org.lwjgl.util.vector.Vector3f;
import rendering.*;
import gamelogic.Physics;

public class ProcessInput extends RenderCamera {

	// Physics physics = new Physics();
	BitmapText text;
	boolean pause = false;
	boolean controlls = false;
	Meni m;
	Text gradLife;
	Text level;
	
	Text meni;
	Text meniItem1;
	Text meniItem2;
	Text meniItem3;
	Text meniItem4;
	
	Text controlHead;
	Text controlItem1;
	Text controlItem2;
	Text controlItem3;
	Text controlItem4;
	Text controlItem5;
	Text controlItem6;
	Text controlItem7;
	Text controlBack;
	// hide the mouse

	/**
	 * Initial setup of projection of the scene onto screen, lights etc.
	 */
	protected void setupView() {
		// text = new Text("Testni izpis", 50);
		
		gradLife = new Text("", 20);
		level = new Text("", 20);
		
		meni = new Text("Meni", 50);
		meniItem1 = new Text("[1] Resume game", 40);
		meniItem2 = new Text("[2] Restart game", 40);
		meniItem3 = new Text("[3] Controlls", 40);
		meniItem4 = new Text("[4] Exit", 40);
		
		controlHead = new Text("Controlls", 50);
		controlItem1 = new Text("W - forward", 40);
		controlItem2 = new Text("S - backward", 40);
		controlItem3 = new Text("A - left", 40);
		controlItem4 = new Text("D - right", 40);
		controlItem5 = new Text("wheel - cheange weapon", 40);
		controlItem6 = new Text("1 - weapon: sword", 40);
		controlItem7 = new Text("2 - weapon: bow", 40);
		controlBack = new Text("[5] Back", 40);
		
		super.setupView();

	}

	/**
	 * Renders current frame
	 */
	protected void renderFrame(long delta) {
		
		

		
		physics.checkCollisions(gameobjects, camera);

		super.renderFrame(delta);

		/*
		text = new BitmapText();
		String napis = "abcDDQ";
		//startHUD();

		GL11.glColor4f(0, 1, 0, 1);
		float w = text.textWidth(napis, 15);
		GL11.glTranslatef(1024 - w, 768 - 20, 0);
		text.renderString(napis, 15);

		//endHUD();
*/
		
		 	startHUD();
		 	
		 	gradLife.setContent("Castle: " + Integer.toString(camera.castleLife));
		    gradLife.setPosition(width - 150, height - 30,0);
		    gradLife.render3D();
		    
		    level.setContent("Level: " + Integer.toString(game.level));
		    level.setPosition(10, height - 30, 0);
		    level.render3D();
		    
		    if(pause && !controlls) {
			    meni.setPosition(50, height - 100, 0);
			    meni.render3D();
			    
			    meniItem1.setPosition(50, height - 180, 0);
			    meniItem1.render3D();
			    
			    meniItem2.setPosition(50, height - 250, 0);
			    meniItem2.render3D();
			    
			    meniItem3.setPosition(50, height - 320, 0);
			    meniItem3.render3D();
			    
			    meniItem4.setPosition(50, height - 390, 0);
			    meniItem4.render3D();
		    }else if(pause && controlls) {
		    	controlHead.setPosition(50, height - 100, 0);
			    controlHead.render3D();
			    
			    controlItem1.setPosition(50, height - 180, 0);
			    controlItem1.render3D();
			    
			    controlItem2.setPosition(50, height - 250, 0);
			    controlItem2.render3D();
			    
			    controlItem3.setPosition(50, height - 320, 0);
			    controlItem3.render3D();
			    
			    controlItem4.setPosition(50, height - 390, 0);
			    controlItem4.render3D();
			    
			    controlItem5.setPosition(50, height - 460, 0);
			    controlItem5.render3D();
			    
			    controlItem6.setPosition(50, height - 530, 0);
			    controlItem6.render3D();
			    
			    controlItem7.setPosition(50, height - 600, 0);
			    controlItem7.render3D();
			    
			    controlBack.setPosition(50, height - 670, 0);
			    controlBack.render3D();
		    }
		    
		    endHUD();
			
		
		
		
	}

	protected boolean checkPlayerCollision(double newX, double newZ) {
		// float x = camera.getPosition().x;
		// float z = camera.getPosition().z;
		// System.out.print(x + " -- ");
		// System.out.println(z + "--");
		// System.out.println(38-z);

		/*
		 * if(Math.abs(270 - newZ) < 0.3) { return true;
		 * 
		 * }else if(Math.abs(-7 - newZ) < 0.3) { return true; }else
		 * if(Math.abs(44 - newX) < 0.3) {
		 * 
		 * return true; }else if(Math.abs(-44 - newX) < 0.3) {
		 * 
		 * return true; } else { return false; }
		 */

		double zC1 = (newX * 8.61) - 384.78;
		double zC2 = (newX * (-8.71)) - 388.78;
		if (Math.abs(zC1 - newZ) < 0.8) {
			return true;
		} else if (Math.abs(newZ - zC2) < 0.8) {
			return true;
		} else if (newZ < -7) {
			return true;
		} else if (newZ > 258) {
			return true;
		} else {
			return false;
		}
	}

	// check if animal is shoot

	/**
	 * Processes Keyboard and Mouse input and spawns actions
	 */
	protected void processInput() {
		if (!pause) {
			
			// keep looping till the display window is closed the ESC key is
			// down
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

			camera.puscicaIzstreljena = pu.leti;
			// when passing in the distance to move
			// we times the movementSpeed with dt this is a time scale
			// so if its a slow frame u move more then a fast frame
			// so on a slow computer you move just as fast as on a fast computer

			/*
			 * float xP = camera.getPosition().x; float zP =
			 * camera.getPosition().z;
			 * 
			 * if (Keyboard.isKeyDown(Keyboard.KEY_W))// move forward {
			 * camera.walkForward(movementSpeed * dt); System.out.println("x: "
			 * + xP + " -- Z: " + zP); } if
			 * (Keyboard.isKeyDown(Keyboard.KEY_S))// move backwards {
			 * camera.walkBackwards(movementSpeed * dt);
			 * System.out.println("x: " + xP + " -- Z: " + zP); } if
			 * (Keyboard.isKeyDown(Keyboard.KEY_A))// strafe left {
			 * camera.strafeLeft(movementSpeed * dt); System.out.println("x: " +
			 * xP + " -- Z: " + zP); } if (Keyboard.isKeyDown(Keyboard.KEY_D))//
			 * strafe right { camera.strafeRight(movementSpeed * dt);
			 * System.out.println("x: " + xP + " -- Z: " + zP); }
			 */
			
			
			//start testing look vector
			/*
			float toRad1 = (float) Math.PI / 180;
			float yaw1 = camera.getYaw() * toRad1;
			float pitch1 = camera.getPitch() * toRad1;

			float xl1 = (float) Math.cos(yaw1);
			float zl1 = (float) Math.sin(yaw1);
			float yl1 = (float) Math.tan(pitch1) * zl1;

			float l1 = (float) Math.sqrt((xl1 * xl1) + (yl1 * yl1) + (zl1 * zl1));

			Vector3f look1 = new Vector3f(-zl1 / l1, -yl1 / l1, -xl1 / l1);
			//System.out.println(look1);
			
			
			System.out.println("KAMERA: " + camera.position);
			System.out.println("ZVAW: " + gameobjects[0].getPosition());
			
			GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1, 1, 1);
		    //GL11.glVertex3f( camera.position.x, camera.position.y + 1, camera.position.z);
		    //GL11.glVertex3f( camera.position.x + 10, camera.position.y + 1, camera.position.z + 10);
		    GL11.glVertex3f( 0f, 0.1f, -2f);
		    GL11.glVertex3f( 0f, 0.3f, -5f);
 
		    GL11.glEnd();
			*/
			//end testing look vector
			//System.out.println(pu.leti);
			if (Mouse.isButtonDown(0) && !camera.lok) {
				camera.hit = true;
			} else if (Mouse.isButtonDown(0) && !(pu.leti)) {
				//camera.puscicaIzstreljena = true;
				float toRad = (float) Math.PI / 180;
				float yaw = camera.getYaw() * toRad;
				float pitch = camera.getPitch() * toRad;

				float xl = (float) Math.sin(yaw);
				float zl = (float) Math.cos(yaw);
				//float yl = (float) Math.tan(pitch) * zl;
				float yl = (float) Math.sin(pitch);
				
				float l = (float) Math.sqrt((xl * xl) + (yl * yl) + (zl * zl));

				Vector3f look = new Vector3f(-xl / l, -yl / l, -zl / l);
				System.out.println("VEKTOR POGLEDA: " + look);
				
				//GL11.glBegin(GL11.GL_LINES);
				//GL11.glColor3f(1, 1, 1);				
			    //GL11.glVertex3f( -2.02f, 0f, -2.0f);
			    //GL11.glVertex3f( -0.008f, 0f, -2.0f);
			    //GL11.glEnd();
				
				float xx = look.x;
				float yy = look.y;
				float zz = look.z;
				camera.smerPuscice.x = new Float(xx);
				camera.smerPuscice.y = new Float(yy);
				camera.smerPuscice.z = new Float(zz);
				boolean hitten = physics.checkShoot(look, camera, gameobjects);
				
				pu.leti = true;
				System.out.println("X: " + camera.getPosition().x + " Y: " + camera.getPosition().y + " Z: " + camera.getPosition().z);
				//pu.setPosition(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
				pu.setPosition(-camera.getPosition().x - 0.1f, -camera.getPosition().y, -camera.getPosition().z);
				pu.setPozicijaKamere(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
				pu.setSmerLeta(-look.x, look.y, look.z);
				
				pu.setRotacija((int)(-pitch * 57.29), (int)(-yaw * 57.29), 0);
				System.err.println("Stopinj: " + (yaw * 57.29));
				
				
				if (hitten) {
					// System.out.println("ANIMAL DEAD");
				} else {
					// System.out.println("Animal live");
				}

				// System.out.println("yaw: "+yaw
				// +" :: pich: "+pitch+" :: "+look);
				// System.out.println(Math.sin(30*Math.PI/180));
				// System.out.println(merjasci[0].getPosition());

			}

			if (Mouse.getDWheel() != wheel) {
				wheel = Mouse.getDWheel();

				if (camera.lok) {
					camera.lok = false;
				} else {
					camera.lok = true;
				}
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_1))// move forward
			{
					camera.lok = false;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_2))// move forward
			{
					camera.lok = true;
			}

			// float xP = camera.getPosition().x;
			// float zP = camera.getPosition().z;
			// detekcije trkov igralca in mej okolja
			if (Keyboard.isKeyDown(Keyboard.KEY_W))// move forward
			{
				if (!checkPlayerCollision(
						camera.tryXForward(movementSpeed * dt),
						camera.tryZForward(movementSpeed * dt))) {
					camera.walkForward(movementSpeed * dt);

				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_S))// move backwards
			{
				if (!checkPlayerCollision(
						camera.tryXBackwards(movementSpeed * dt),
						camera.tryZBackwards(movementSpeed * dt))) {
					camera.walkBackwards(movementSpeed * dt);

				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A))// strafe left
			{
				if (!checkPlayerCollision(camera.tryXLeft(movementSpeed * dt),
						camera.tryZLeft(movementSpeed * dt))) {
					camera.strafeLeft(movementSpeed * dt);

				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D))// strafe right
			{
				if (!checkPlayerCollision(camera.tryXRight(movementSpeed * dt),
						camera.tryZRight(movementSpeed * dt))) {
					camera.strafeRight(movementSpeed * dt);

				}
			}

		}else if(pause && !controlls) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
				pause = false;
				for (GameObject go : gameobjects) {
					go.moving = true;
				}
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
				Display.destroy();
				(new ProcessInput()).execute();
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_3)) {
				controlls = true;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_4)) {
				isRunning = false;
			}
		}
		
		if(pause && controlls) {
			if(Keyboard.isKeyDown(Keyboard.KEY_5)) {
				controlls = false;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

			pause = true;
			for (GameObject go : gameobjects) {
				go.moving = false;
			}

		}

	}

	protected void startHUD() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1024, 0, 768, -1, 1); //TODO POPRAVIT DA JE SPREMENLJIVO
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_LIGHTING);
		
	}

	protected void endHUD() {
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();
		

	}

	public static void main(String[] args) {
		(new ProcessInput()).execute();
	}

}
