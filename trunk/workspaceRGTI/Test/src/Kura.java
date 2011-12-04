import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Kura  {

	private Vector3f position = null;
	private Obj3D model;
	private float speed = 0.05f;
	private Vector3f direction = new Vector3f(0, 0, 0);;
	private float scale = 2;
	public boolean alive;
	private boolean moving;
	public float radius = 0f;

	public Kura(float x, float y, float z, float r, Obj3D kModel) {

		position = new Vector3f(x, y, z);
		radius=r;
		alive=true;
		moving=true;
		
		//model = new Obj3D("kura.obj");
		model = kModel;
		model.setPosition(position.x,position.y,position.z);
		model.setRotation(0, 0, 0);
		model.setScaling(scale, scale, scale);
		model.render3D();
	}

	public void move() {
		if (position.x > direction.x) {
			position.x -= speed*0.1;
			
		} else if (position.x < direction.x) {
			position.x += speed*0.1;
		}

		if (position.z > direction.z) {
			position.z -= speed;
		} else if (position.z < direction.z) {
			position.z += speed;
		}
		
		model.setPosition(position.x,position.y,position.z);
		model.setRotation(0, 0, 0);
		model.setScaling(scale, scale, scale);
		model.render3D();
	}
}
