package model;

import java.util.Random;

public class MonsterGoblin extends Monster {

	private Random r;
	private int num = 4;

	public MonsterGoblin(int x, int y, int width, int height, String image,
			String name) {
		super(x, y, width, height, image, name);
		r = new Random();
	}

	@Override
	public void update(int currentTick) {
		if (currentTick % 32 == 0) {
			num = r.nextInt(5);
		}
		switch (num) {
		case 0:
			acceleration.x = 0.4;
			break;
		case 1:
			acceleration.x = -0.4;
			break;
		case 2:
			acceleration.y = 0.4;
			break;
		case 3:
			acceleration.y = -0.4;
			break;
		default:
			break;
		}
		super.update(currentTick);
	}
}
