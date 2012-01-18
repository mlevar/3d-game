package objects;
import org.lwjgl.util.vector.Vector3f;

public class Puscica {
	private Vector3f position = null;
	private Obj3D puscicaModel;
	private Obj3D skydome;
	private float scale = 1;
	public boolean leti = false;
	public Vector3f smerLeta = null;
	public Vector3f pozicijaKamere = null;

	public Puscica(float x, float y, float z) {
		//position = new Vector3f(-20f, 2f, -20f);

		puscicaModel = new Obj3D("arrow.obj");
		puscicaModel.setPosition(-0.2f, -1.3f, -2f);
		puscicaModel.setRotation(0, 0, 0);
		puscicaModel.setScaling(scale, scale, scale);
		
		smerLeta = new Vector3f(0f,0f,0f);
		pozicijaKamere = new Vector3f(0f,0f,0f);
		//field.render3D();

		//skydome = new Obj3D("skydome.obj");

		//skydome.setPosition(position.x - 215.0f * 1.5f,
		//		position.y - 100.0f * 1.5f, position.z + 70.0f * 1.5f);
		//skydome.setRotation(0, 0, 0);
		//skydome.setScaling(1.5f * scale, 1.5f * scale, 1.5f * scale);
		//skydome.render3D();

	}
	
	public void setPosition(float x, float y, float z) {
		puscicaModel.setPosition(x, y, z);
	}
	
	public void setSmerLeta(float x, float y, float z) {
		smerLeta.x = x;
		smerLeta.y = y;
		smerLeta.z = z;
	}
	
	public void setRotacija(int x, int y, int z) {
		puscicaModel.setRotation(x,y,z);
	}
	
	public void setPozicijaKamere(float x, float y, float z) {
		pozicijaKamere.x = x;
		pozicijaKamere.y = y;
		pozicijaKamere.z = z;
	}

	public void render() {
		//field.setPosition(position.x - 40.0f, position.y - 3.0f,
		//		position.z + 40.0f);
		//field.setRotation(0, 0, 0);
		//field.setScaling(scale, scale, scale);
		puscicaModel.m_nX = puscicaModel.m_nX + (smerLeta.x * 2f);
		puscicaModel.m_nY = puscicaModel.m_nY + (smerLeta.y * 2f);
		puscicaModel.m_nZ = puscicaModel.m_nZ + (smerLeta.z * 2f);
		
		double razdaljaPuscicaKamera = Math.sqrt(Math.pow((pozicijaKamere.x - puscicaModel.m_nX),2) + Math.pow((pozicijaKamere.y - puscicaModel.m_nY),2) + Math.pow((pozicijaKamere.z - puscicaModel.m_nZ),2));
		if(razdaljaPuscicaKamera > 60) {
			leti = false;
		}
		
		puscicaModel.render3D();

		//skydome.setPosition(position.x - 215.0f * 1.5f,
		//		position.y - 100.0f * 1.5f, position.z + 70.0f * 1.5f);
		//skydome.setRotation(0, 0, 0);
		//skydome.setScaling(1.5f * scale, 1.5f * scale, 1.5f * scale);
		//skydome.render3D();
	}
}
