package objects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public abstract class GameObject {

	protected Vector3f position = null;
	protected float length;
	protected float height;
	protected float width;
	protected float speed;
	protected float radius;
	protected Vector3f direction = new Vector3f(0, 0, 0);;

	public boolean alive;
	public boolean moving;

	public GameObject(float x, float y, float z, float speed) {
		this.position = new Vector3f(x, y, z);
	
		this.alive = true;
		this.moving = true;
		this.speed=speed;

	}
	public void setRadius(float r){
		this.radius = r;
	}
	public void setBBox(float length, float width, float height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public void setDirection(float x, float y, float z) {
		direction = new Vector3f(x, y, z);
	}

	public Vector3f getPosition() {
		return position;
	}
	public float[] getAABB(){
		float[] f = new float[6];
		f[0]=position.x-length/2;
		f[1]=position.x+length/2;
		f[2]=position.z+width/2;
		f[3]=position.z+width/2;
		f[4]=position.y;
		f[5]=position.y+height;
		return f;
	}
	public float getRadius(){
		return this.radius;
	}
	public abstract void move(long delta);

	public abstract void kill() ;

	public abstract void render(long delta);

}
