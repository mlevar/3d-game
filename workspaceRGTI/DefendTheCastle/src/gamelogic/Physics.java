package gamelogic;

import java.util.List;
import java.util.Map;

import objects.*;

import org.lwjgl.util.vector.Vector3f;

import rendering.*;
import gamelogic.ProcessInput;

public class Physics {
	/*
	 * private int n; private final List<GameObject> list; private final
	 * List<Float> xAxis; private final List<Float> zAxis; private final
	 * List<Float> yAxis;
	 * 
	 * private final Map<Pair<GameObject>> overlap; private final
	 * Set<Pair<GameObject>>
	 */

	public Physics() {

	}

	public boolean checkCollisions(GameObject[] gameobjects) {
	for(int i=0;i<gameobjects.length-1;i++){
			
			for(int j=i+1;j<gameobjects.length;j++){
				if(i==j)continue;
				if(checkCollisionObjects(gameobjects[i],gameobjects[j])){
					gameobjects[i].moving=false;
				}else{
					gameobjects[i].moving=true;
				}
				
			}
		}
		
		
		return false;
	}
	public boolean checkCollisionObjects(GameObject o1,GameObject o2){
		Vector3f p1 = o1.getPosition();
		Vector3f p2 = o2.getPosition();
		if(Math.sqrt(Math.pow(p1.x-p2.x, 2)+Math.pow(p1.z-p2.z,2))<o1.getRadius()+o2.getRadius()+0.2f)
			return true;
		return false;
	}

	public boolean checkShoot(Vector3f v, Player camera,
			GameObject[] gameobjects) {

		Vector3f camPos = camera.getPosition();
		for (int i = 0; i < gameobjects.length; i++) {
			Vector3f merPos = gameobjects[i].getPosition();
			float dist = (float) Math.sqrt(Math.pow((-camPos.x - merPos.x), 2)
					+ Math.pow((camPos.z + merPos.z), 2));
			Vector3f vz = new Vector3f(-(v.x * dist + camPos.x) - 1, v.y * dist
					+ camPos.y, v.z * dist - camPos.z);
			// System.out.println("Pozicija cloveka: " + camPos);
			// System.out.println("Pozicija zivali: " + merPos);
			// System.out.println("Razdalja clovek - zival: " + dist);
			// System.out.println("Vektor pogleda: " + v);
			// System.out.println("Zracunan vektor: " + vz);
			float distCA = (float) Math.sqrt(Math.pow((vz.x - merPos.x), 2)
					+ Math.pow((vz.y - merPos.y - 4.3), 2)
					+ Math.pow((vz.z - merPos.z), 2));
			// System.out.println("Razdalja: " + distCA);
			if (distCA < 1f) {
				gameobjects[i].alive = false;
				return true;
			}
		}

		return false;
	}

	public void addObject(GameObject o) {
		float[] f = o.getAABB();
	}
}