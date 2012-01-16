package gamelogic;

import java.util.List;
import java.util.Map;

import objects.*;

import org.lwjgl.util.vector.Vector3f;

import rendering.*;
import gamelogic.ProcessInput;
import rendering.RenderCamera;

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

	public boolean checkCollisions(GameObject[] gameobjects, Player p) {

		for (int i = 0; i < gameobjects.length - 1; i++) {
			if (gameobjects[i].alive == true) {

				if (checkCollisionPlayer(p, gameobjects[i])) {

					Vector3f c = gameobjects[i].getPosition();
					float s = gameobjects[i].radius;
					if (p.position.x < gameobjects[i].getPosition().x)
						gameobjects[i].setDirection(c.x - s * 0.4f, c.y, c.z
								- s * 0.4f);
					else
						gameobjects[i].setDirection(c.x + s * 0.4f, c.y, c.z
								- s * 0.4f);

				}

				for (int j = i + 1; j < gameobjects.length; j++) {
					if (gameobjects[j].alive == true) {
						if (i == j)
							continue;
						if (checkCollisionObjects(gameobjects[i],
								gameobjects[j])) {
							// gameobjects[i].collision = true;
							// gameobjects[j].collision = true;
							// gameobjects[j].moving = false;

							Vector3f c = gameobjects[i].getPosition();
							float s = gameobjects[i].radius;

							if (gameobjects[i].getPosition().x < gameobjects[j]
									.getPosition().x)

								gameobjects[i].setDirection(c.x - s * 0.4f,
										c.y, c.z - s * 0.4f);
							else
								gameobjects[i].setDirection(c.x + s * 0.4f,
										c.y, c.z - s * 0.4f);
							// gameobjects[j].setDirection(c.x-0.5f, c.y,
							// c.z+0.5f);
							// System.out.println("COLLISION DETECTED");
							
						} else {
							// gameobjects[j].moving = true;
							// gameobjects[i].collision = false;
							// gameobjects[j].collision = false;
						}
					}
				}

			}
		}

		return false;
	}

	public boolean checkCollisionPlayer(Player p, GameObject o) {

		Vector3f p1 = p.getPosition();
		Vector3f p2 = o.getPosition();

		if (Math.sqrt(Math.pow(-p1.x - p2.x, 2) + Math.pow(-p1.z - p2.z, 2)) < p.radius
				+ o.getRadius() + 0.05f)
			return true;
		return false;
	}

	public boolean checkCollisionObjects(GameObject o1, GameObject o2) {
		Vector3f p1 = o1.getPosition();
		Vector3f p2 = o2.getPosition();
		if (Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.z - p2.z, 2)) < o1
				.getRadius() + o2.getRadius() + 0.05f)
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