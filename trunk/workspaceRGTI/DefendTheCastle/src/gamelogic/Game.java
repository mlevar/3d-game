package gamelogic;

import org.lwjgl.util.vector.Vector3f;

import objects.GameObject;
import objects.Kura;
import objects.Medved;
import objects.Merjasec;
import objects.Obj3D;
import objects.Puscica;

public class Game {

	public int level;
	public Object[][] levels;
	public int castleHP;
	public GameObject[] currentSet; 
	public boolean levelStatus;
	public Obj3D kuraModel;
	public Obj3D merjasecModel;
	public Obj3D medvedModel;
	
	public Obj3D letecaPuscica;
	public Vector3f smerLetecePuscice;
	boolean leti = false;

	public Game() {
		this.level = 1;
		this.levelStatus = true;
		this.castleHP = 20;

		kuraModel = new Obj3D("kura.obj");
		merjasecModel = new Obj3D("merjasec.obj");
		medvedModel = new Obj3D("medved.obj");
		
		letecaPuscica = new Obj3D("puscica.obj");
		smerLetecePuscice = new Vector3f(-1f, -1f, -1f);
		
		createLevels();

	}

	public void nextlevel() {

	}

	public void createLevels() {
		levels = new Object[5][];

		levels[0] = level1();
		levels[1] = level2();
		levels[2] = level3();
		levels[3] = level4();
		levels[4] = level5();
	}

	public GameObject[] level0() {
		GameObject[] l = new GameObject[3];

		for (int i = 0; i < l.length; i++) {
			l[i] = new Medved(-25 + (i % 10) * 5f, -3f, -100f, medvedModel,
					2, 0.1f * i);
		}

		return l;
	}

	public GameObject[] level1() {
		GameObject[] l = new GameObject[30];
		for (int i = 0; i < l.length; i++) {
			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 2,
					0.1f * i);
		}
		
		return l;
	}

	public GameObject[] level2() {
		GameObject[] l = new GameObject[40];

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
		GameObject[] l = new GameObject[50];

		for (int i = 0; i < l.length; i++) {

			if (i % 12 == 0) {
				l[i] = new Medved(-25 + (i % 10) * 5f, -3f, -300f, medvedModel,
						2, 0.1f * i);
				continue;
			}

			if (i % 8 == 0) {
				l[i] = new Merjasec(-25 + (i % 10) * 5f, -3f, -300f,
						merjasecModel, 2, 0.3f * i);
				continue;
			}

			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 2,
					1f * i);

		}

		return l;
	}

	public GameObject[] level4() {
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
					1f * i);

		}
		return l;
	}

	public GameObject[] level5() {
		GameObject[] l = new GameObject[10];

		for (int i = 0; i < l.length; i++) {
			if (i == 9) {
				l[i] = new Medved(0, -3f, -300f, medvedModel, 25, 15);
				l[i].health = 300;
				l[i].speed = 0.002f;
				//l[i].lowerHP=20;
				continue;
			}

			l[i] = new Kura(-25 + (i % 10) * 5f, -3f, -300f, kuraModel, 7,
					0.1f * i);
			l[i].health = 10;
			l[i].speed = 0.006f;

		}

		return l;
	}

}
