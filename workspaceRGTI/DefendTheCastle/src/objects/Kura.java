package objects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import rendering.RenderObjects;

public class Kura extends GameObject {

	private Obj3D model;
	private float delay;
	private float scale = 2;
	float angle;

	public Kura(float x, float y, float z, Obj3D mModel, float scale,float delay) {

		super(x, y, z, 0.01f);

		this.scale = scale;
		model = mModel;
		this.health=1;
		this.delay = (float) delay*1000;
		
		super.setRadius(0.4f*scale);
		super.setBBox(scale * 0.4f, scale * 0.35f, scale * 0.45f);

	}

	public void move(long delta) {
		if (moving) {
			float h = (float) Math.sqrt(Math.pow(position.x - direction.x, 2)
					+ Math.pow(position.z - direction.z, 2));

			float deltax = delta * speed * Math.abs(position.x - direction.x)
					/ h;
			float deltaz = delta * speed * Math.abs(position.z - direction.z)
					/ h;
			if (0.2 > Math.abs(position.x - direction.x)) {
				deltax = 0;
			}
			if (0.2 > Math.abs(position.z - direction.z)) {
				deltaz = 0;
			}

			if(collision){
				deltax*= (int)Math.random()*3.99-1.99;
				deltaz*= (int)Math.random()*3.99-1.99;
			}
			
			
			angle = (float) (Math.atan(Math.abs(position.x - direction.x)
					/ Math.abs(position.z - direction.z)) * 180 / Math.PI);

			if (position.x > direction.x) {
				position.x -= deltax;
			} else if (position.x < direction.x) {
				position.x += deltax;
				angle = -angle;
			}
			if (position.z > direction.z) {
				position.z -= deltaz;
			} else if (position.z < direction.z) {
				position.z += deltaz;
				angle = -angle;
			}

			if (deltax == 0 && deltaz == 0) {
				if(direction.z <8){
					direction = new Vector3f(0, 0, 15);
				}
				else if (direction.z >28){
					moving = false;
					alive=false;
					lowerHP=1;
				}
				else
					direction = new Vector3f(0, 0, 30);
			}
		}
		render(delta);
	}

	public void kill() {
		
	}

	
	public void delay(long delta){
		this.delay-=delta;
		if (delay<0){
			this.delayed=false;
			this.alive=true;}
	}
	
	public void render(long delta) {
		model.setPosition(position.x, position.y, position.z);
		model.setRotation(0, angle, 0);
		model.setScaling(scale, scale, scale);
		model.render3D();
		
	}

}
