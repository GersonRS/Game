package model;

import java.awt.geom.Point2D;

public abstract class Character extends Entidade {

	protected Point2D.Double speed;
	protected Point2D.Double acceleration;
	protected Point2D.Double maxSpeed;
	protected double friction;
	protected int animates;
	protected int direction;

	public Character() {
	}

	public Character(int x, int y, int width, int height, String imagem) {
		super(x, y, width, height, imagem);
		speed = new Point2D.Double();
		acceleration = new Point2D.Double();
		maxSpeed = new Point2D.Double(3, 3);
		friction = 0.3;
	}

	public void update() {
		speed.x += acceleration.x;
		if (speed.x < -maxSpeed.x) {
			speed.x = -maxSpeed.x;
		} else if (speed.x > maxSpeed.x) {
			speed.x = maxSpeed.x;
		}
		speed.y += acceleration.y;
		if (speed.y < -maxSpeed.y) {
			speed.y = -maxSpeed.y;
		} else if (speed.y > maxSpeed.y) {
			speed.y = maxSpeed.y;
		}

		if (speed.y < 0) {
			speed.y += friction;
			if (speed.y > 0) {
				speed.y = 0;
			}
		} else if (speed.y > 0) {
			speed.y -= friction;
			if (speed.y < 0) {
				speed.y = 0;
			}
		}

		if (speed.x < 0) {
			speed.x += friction;
			if (speed.x > 0) {
				speed.x = 0;
			}
		} else if (speed.x > 0) {
			speed.x -= friction;
			if (speed.x < 0) {
				speed.x = 0;
			}
		}
		pos.x += speed.x;
		pos.y += speed.y;
		acceleration.x = 0;
		acceleration.y = 0;
	}

	public int getDirection() {
		return direction;
	}

	public int getAnimates() {
		return animates;
	}
}