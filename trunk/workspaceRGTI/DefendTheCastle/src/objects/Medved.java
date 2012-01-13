package objects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Medved extends GameObject {

	private Obj3D model;

	private float scale = 2;
	float angle;

	public Medved(float x, float y, float z, Obj3D mModel, float scale) {
		super(x, y, z, 0.004f);

		this.scale = scale;
		model = mModel;

		super.setRadius(scale* 1.4f);
		super.setBBox(scale * 0.9f, scale * 2f, scale * 1.2f);

	}

	public void move(long delta) {
		if (moving) {
			float h = (float) Math.sqrt(Math.pow(position.x - direction.x, 2)
					+ Math.pow(position.z - direction.z, 2));

			float deltax = delta * speed * Math.abs(position.x - direction.x)
					/ h;
			float deltaz = delta * speed * Math.abs(position.z - direction.z)
					/ h;
			if (0.3 > Math.abs(position.x - direction.x)) {
				deltax = 0;
			}
			if (0.3 > Math.abs(position.z - direction.z)) {
				deltaz = 0;
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
			
			if (deltax==0 && deltaz==0){
				moving=false;
			}
			
		}
		render(delta);
	}
	public void moveback(){
		this.move(-1);
	}
	public void kill() {

	}

	public void render(long delta) {
		model.setPosition(position.x, position.y, position.z);
		model.setRotation(0, angle, 0);
		model.setScaling(scale, scale, scale);
		model.render3D();
		
	}

}