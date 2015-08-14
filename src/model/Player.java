package model;

import java.awt.event.KeyEvent;
import java.io.IOException;

public final class Player extends Character {

	private int gold;
	private int state;
	private int stepInterval;
	private int lastStepTick;

	public Player() {
	}

	public Player(int x, int y, int width, int height, String imagem,
			String name) {
		super(x, y, width, height, imagem, name);
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

	public int getGold() {
		return gold;
	}

	public void addGold(int gold) {
		this.gold += gold;
	}

	public void subtractGold(int gold) {
		this.gold -= gold;
	}

}
