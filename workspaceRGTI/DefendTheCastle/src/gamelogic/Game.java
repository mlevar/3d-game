package gamelogic;

import objects.GameObject;
import objects.Kura;
import objects.Medved;
import objects.Merjasec;
import objects.Obj3D;

public class Game {

	public int level;
	public Object[][] levels;
	public int castleHP;
	public GameObject[] currentSet;
	public boolean levelStatus;
	public Obj3D kuraModel;
	public Obj3D merjasecModel;
	public Obj3D medvedModel;

	public Game() {
		this.level = 1;
		this.levelStatus = true;
		this.castleHP = 20;

		kuraModel = new Obj3D("kura.obj");
		merjasecModel = new Obj3D("merjasec.obj");
		medvedModel = new Obj3D("medved.obj");

		createLevels();

	}

	public void nextlevel() {

	}

	public void createLevels() {
		levels = new Object[5][];

		levels[0] = l1;
	}

	public GameObject[] level1() {
		GameObject[] l = new GameObject[50];

		for (int i = 0; i < l.length; i++) {
			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 2,
					0.1f * i);
		}

		return l;
	}

	public GameObject[] level2() {
		GameObject[] l = new GameObject[50];

		for (int i = 0; i < l.length; i++) {
			if (i % 8 == 0) {
				l[i] = new Merjasec(-25 + (i % 10) * 5f, -3f, -300f,
						merjasecModel, 2, 0.1f * i);
				continue;
			}

			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 2,
					0.1f * i);

		}

		return l;
	}

	public GameObject[] level3() {
		GameObject[] l = new GameObject[60];

		for (int i = 0; i < l.length; i++) {

			if (i % 12 == 0) {
				l[i] = new Medved(-25 + (i % 10) * 5f, -3f, -300f, medvedModel,
						2, 0.1f * i);
				continue;
			}

			if (i % 8 == 0) {
				l[i] = new Merjasec(-25 + (i % 10) * 5f, -3f, -300f,
						merjasecModel, 2, 0.1f * i);
				continue;
			}

			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 2,
					0.1f * i);

			// l[i] = new Medved(-50 + i * 5f, -3f, -50f, medvedModel, 2);
		}

		return l;
	}

	public GameObject[] level4() {
		GameObject[] l = new GameObject[50];

		for (int i = 0; i < l.length; i++) {

			 l[i] = new Medved(-25 + (i % 10) * 5f, -3f, -50f, medvedModel, 2,0.01f * i);
		}

		return l;
	}

	// levels:
	private Object[] l1 = { Kura.class, Kura.class, Kura.class, Kura.class,
			Kura.class };
	private Object[] l2 = { Kura.class, Kura.class, Kura.class, Kura.class,
			Kura.class };
	private Object[] l3 = { Kura.class, Kura.class, Kura.class, Kura.class,
			Kura.class };
	private Object[] l4 = { Kura.class, Kura.class, Kura.class, Kura.class,
			Kura.class };
	private Object[] l5 = { Kura.class, Kura.class, Kura.class, Kura.class,
			Kura.class };
}
