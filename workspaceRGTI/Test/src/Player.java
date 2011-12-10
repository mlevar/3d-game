import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Player {

	// First Person Camera Controller
	// http://www.lloydgoodall.com/tutorials/first-person-camera-control-with-lwjgl/

	Obj3Dpivot hand;
	Obj3Dpivot sword;
	Obj3Dpivot bow;
	Obj3Dpivot arrow;
	boolean hit = false;
	int hitc = 0;
	boolean lok = false;
	
	int castleLife = 100;
	
	
	
	

	// 3d vector to store the camera's position in
	public Vector3f position = null;
	public Vector3f offset = new Vector3f(0.3f, -2.7f, -1f);
	// the rotation around the Y axis of the camera
	private float yaw = 0.0f;
	// the rotation around the X axis of the camera
	private float pitch = 0.0f;

	public float radius = 0f;

	public Player(float x, float y, float z, float r) {
		// instantiate position Vector3f to the x y z params.
		position = new Vector3f(x, y, z);
		radius = r;

		hand = new Obj3Dpivot("hand.obj");
		// hand.setPosition(0.2f,-0.2f,-1.5f );
		// hand.setPivot(offset.x,offset.y,offset.z);
		// hand.setRotation(0, 0, 0);
		// hand.setScaling(1f, 1f, 1f);
		// hand.render3D();

		//sword = new Obj3Dpivot("mec.obj");
		sword = new Obj3Dpivot("mec.obj");
		bow = new Obj3Dpivot("bow.obj");
		arrow = new Obj3Dpivot("arrow.obj");
		
		// hand.setPosition(0.2f,-0.2f,-1.5f );
		// sword.setPivot(offset.x,offset.y,offset.z);
		// sword.setRotation(0, 0, 0);
		// sword.setScaling(1f, 1f, 1f);
		// sword.render3D();
	}

	// increment the camera's current yaw rotation
	public void yaw(float amount) {
		// increment the yaw by the amount param
		yaw += amount;
	}

	// increment the camera's current yaw rotation
	public void pitch(float amount) {
		// increment the pitch by the amount param
		pitch -= amount;
	}

	// moves the camera forward relative to its current rotation (yaw)
	public void walkForward(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw));
	}

	public float tryXForward(float distance) {
		return position.x - (distance * (float) Math.sin(Math.toRadians(yaw)));
	}

	public float tryZForward(float distance) {
		return position.z + (distance * (float) Math.cos(Math.toRadians(yaw)));
	}

	// moves the camera backward relative to its current rotation (yaw)
	public void walkBackwards(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(yaw));
		position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
	}

	public float tryXBackwards(float distance) {
		return position.x + (distance * (float) Math.sin(Math.toRadians(yaw)));
	}

	public float tryZBackwards(float distance) {
		return position.z - (distance * (float) Math.cos(Math.toRadians(yaw)));
	}

	// strafes the camera left relitive to its current rotation (yaw)
	public void strafeLeft(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
	}

	public float tryXLeft(float distance) {
		return position.x
				- (distance * (float) Math.sin(Math.toRadians(yaw - 90)));
	}

	public float tryZLeft(float distance) {
		return position.z
				+ (distance * (float) Math.cos(Math.toRadians(yaw - 90)));
	}

	// strafes the camera right relitive to its current rotation (yaw)
	public void strafeRight(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
	}

	public float tryXRight(float distance) {
		return position.x
				- (distance * (float) Math.sin(Math.toRadians(yaw + 90)));
	}

	public float tryZRight(float distance) {
		return position.z
				+ (distance * (float) Math.cos(Math.toRadians(yaw + 90)));
	}

	public Vector3f getPosition() {
		return position;
	}

	// translates and rotate the matrix so that it looks through the camera
	// this dose basic what gluLookAt() does
	public void lookThrough() {
		// roatate the pitch around the X axis
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		// roatate the yaw around the Y axis
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		// translate to the position vector's location
		GL11.glTranslatef(position.x, position.y, position.z);

	}

	public void render() {
		
		

		if ((hit || hitc > 0) && !lok) {
			GL11.glLoadIdentity();
			hand.setPosition(0.2f, -0.2f, -1f);
			hand.setRotation(0.5f * hitc, 0, 0);
			hand.render3D();

			sword.setPosition(0.2f, -0.15f, -1f);
			sword.setRotation(0.5f * hitc, 0, 0);
			sword.render3D();

			hitc += 1;
			if (hitc == 90)
				hit = false;

			if (!hit) {
				hitc -= 3;
			}
			System.out.println(hitc);
		}else if(lok) {
			GL11.glLoadIdentity();
			//hand.setPosition(0.2f, -0.2f, -1f);
			hand.setPosition(0.2f, -0.2f, -1f);
			hand.setRotation(0, 0, 0);
			hand.render3D();
			
			//bow.setPosition(0.2f, -0.15f, -1f);
			bow.setPosition(0.055f, -0.15f, -1.045f);
			bow.setRotation(0, -7, 0);
			bow.render3D();
			
			//arrow.setPosition(0.2f, -0.11f, -1.6f);
			arrow.setPosition(0.055f, -0.11f, -1.645f);
			arrow.setRotation(0, -7, 0);
			arrow.render3D();
			
			//cross
			GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1, 1, 1);
			
		    GL11.glVertex3f( -0.02f, 0f, -2.0f);
		    GL11.glVertex3f( -0.008f, 0f, -2.0f);
		    
		    GL11.glVertex3f( 0.008f,  0f, -2.0f);
		    GL11.glVertex3f( 0.02f,  0f, -2.0f);
		    
		    GL11.glVertex3f( 0f, 0.02f, -2.0f);
		    GL11.glVertex3f( 0f, 0.008f, -2.0f);
		    
		    GL11.glVertex3f( 0f,  -0.008f, -2.0f);
		    GL11.glVertex3f( 0f,  -0.02f, -2.0f);
		    
		    GL11.glEnd();
		    
		    
			
		}else {
			GL11.glLoadIdentity();
			hand.setPosition(0.2f, -0.2f, -1f);
			// hand.setPivot(offset.x,offset.y,offset.z);
			 hand.setRotation(-40, 0, 0);
			hand.setScaling(1f, 1f, 1f);
			hand.render3D();

			sword.setPosition(0.2f, -0.15f, -1f);
			// sword.setPivot(offset.x,offset.y,offset.z);
			sword.setRotation(-40, 0, 0);
			sword.setScaling(1f, 1f, 1f);
			sword.render3D();
		}
	}
	
	
}
