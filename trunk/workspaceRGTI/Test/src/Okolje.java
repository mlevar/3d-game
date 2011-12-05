import org.lwjgl.util.vector.Vector3f;

public class Okolje {
	private Vector3f position = null;
	private Obj3D field;
	private Obj3D skydome;
	private float scale = 1;

	public Okolje(float x, float y, float z) {
		position = new Vector3f(x, y, z);

		field = new Obj3D("okolje.obj");
		field.setPosition(position.x - 40.0f, position.y - 3.0f,
				position.z + 40.0f);
		field.setRotation(0, 0, 0);
		field.setScaling(scale, scale, scale);
		field.render3D();

		skydome = new Obj3D("skydome.obj");

		skydome.setPosition(position.x - 215.0f * 1.5f,
				position.y - 100.0f * 1.5f, position.z + 70.0f * 1.5f);
		skydome.setRotation(0, 0, 0);
		skydome.setScaling(1.5f * scale, 1.5f * scale, 1.5f * scale);
		skydome.render3D();

	}

	public void render() {
		field.setPosition(position.x - 40.0f, position.y - 3.0f,
				position.z + 40.0f);
		field.setRotation(0, 0, 0);
		field.setScaling(scale, scale, scale);
		field.render3D();

		skydome.setPosition(position.x - 215.0f * 1.5f,
				position.y - 100.0f * 1.5f, position.z + 70.0f * 1.5f);
		skydome.setRotation(0, 0, 0);
		skydome.setScaling(1.5f * scale, 1.5f * scale, 1.5f * scale);
		skydome.render3D();
	}
}
