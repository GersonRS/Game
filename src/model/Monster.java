package model;

public abstract class Monster extends Character {

	public Monster(int x, int y, int width, int height, String image,
			String name) {
		super(x, y, width, height, image, name);
	}

	public void update(int currentTick) {
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
			state = STATE.WALKING.ordinal();
		} else {
			state = STATE.STANDING.ordinal();
			animates = 2;
		}
		if (currentTick % 8 == 0 && state == STATE.WALKING.ordinal())
			animates++;
	}
}