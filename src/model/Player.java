package model;

import java.awt.event.KeyEvent;
import java.io.IOException;

public final class Player extends Character {

	private int strength;
	private int intelligence;
	private int agility;
	private int dexterity;
	private int hp, hpMax;
	private int mp, mpMax;
	private String name;
	private int level;
	private int exp, expMax;
	private int state;
	private int stepInterval;
	private int lastStepTick;

	static enum STATE {
		STANDING, WALKING, ATTACK
	}

	static enum ORIENTATION {
		UP, RIGHT, DOWN, LEFT
	};


	public Player() {
	}

	public Player(int x, int y, int width, int height, String imagem,
			String name) {
		super(x, y, width, height, imagem);
		this.name = name;
		this.level = 1;
		this.expMax = 100;
		this.hpMax = 100;
		this.mpMax = 100;
		this.stepInterval = 20;
		this.state = STATE.STANDING.ordinal();
	}

	public void update(int currentTick) {
		if (InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
			acceleration.x = 0.4;
		} else if (InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)) {
			acceleration.x = -0.4;
		} else if (InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)) {
			acceleration.y = 0.4;
		} else if (InputManager.getInstance().isPressed(KeyEvent.VK_UP)) {
			acceleration.y = -0.4;
		}
		super.update();
		if (speed.y != 0 || speed.x != 0) {
			if (speed.y < 0)
				direction = ORIENTATION.UP.ordinal();
			else if (speed.y > 0)
				direction = ORIENTATION.DOWN.ordinal();
			else if (speed.x < 0)
				direction = ORIENTATION.LEFT.ordinal();
			else if (speed.x > 0)
				direction = ORIENTATION.RIGHT.ordinal();
			if (currentTick - lastStepTick > stepInterval) {
				// playSound("audios/step.wav");
				lastStepTick = currentTick;
			}
			state = STATE.WALKING.ordinal();
		} else {
			state = STATE.STANDING.ordinal();
			animates = 2;
		}
		if (currentTick % 8 == 0 && state == STATE.WALKING.ordinal())
			animates++;
	}

	public void playSound(String fileName) {
		try {
			AudioManager.getInstance().loadAudio(fileName).play();
		} catch (IOException ioe) {
		}
	}

	public int getState() {
		return state;
	}
}
