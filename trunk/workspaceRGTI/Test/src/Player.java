import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Player {

	// First Person Camera Controller
	// http://www.lloydgoodall.com/tutorials/first-person-camera-control-with-lwjgl/

	// 3d vector to store the camera's position in
	private Vector3f position = null;
	// the rotation around the Y axis of the camera
	private float yaw = 0.0f;
	// the rotation around the X axis of the camera
	private float pitch = 0.0f;
	
	public float radius = 0f;

	public Player(float x, float y, float z, float r) {
		// instantiate position Vector3f to the x y z params.
		position = new Vector3f(x, y, z);
		radius = r;
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
		return position.x - (distance * (float) Math.sin(Math.toRadians(yaw - 90)));
	}
	
	public float tryZLeft(float distance) {
		return position.z + (distance * (float) Math.cos(Math.toRadians(yaw - 90)));
	}

	// strafes the camera right relitive to its current rotation (yaw)
	public void strafeRight(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
	}
	
	public float tryXRight(float distance) {
		return position.x - (distance * (float) Math.sin(Math.toRadians(yaw + 90)));
	}
	
	public float tryZRight(float distance) {
		return position.z + (distance * (float) Math.cos(Math.toRadians(yaw + 90)));
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
}
