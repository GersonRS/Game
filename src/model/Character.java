package model;

import java.awt.geom.Point2D;

public abstract class Character extends Entity {

	protected int strength;
	protected int intelligence;
	protected int agility;
	protected int dexterity;
	protected int hp, hpMax;
	protected int mp, mpMax;
	protected String name;
	protected int level;
	protected int exp, expMax;
	protected int state;
	protected Point2D.Double speed;
	protected Point2D.Double acceleration;
	protected Point2D.Double maxSpeed;
	protected double friction;
	protected String image;
	protected int animates;
	protected int direction;

	static enum STATE {
		STANDING, WALKING, ATTACK
	}

	static enum ORIENTATION {
		UP, RIGHT, DOWN, LEFT
	};
	
	public Character() {
	}

	public Character(int x, int y, int width, int height, String image,String name) {
		super(x, y, width, height);
		this.image = image;
		this.name = name;
		this.level = 1;
		this.expMax = 100;
		this.hpMax = 100;
		this.mpMax = 100;
		speed = new Point2D.Double();
		acceleration = new Point2D.Double();
		maxSpeed = new Point2D.Double(2, 2);
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

	public int getAnimates() {
		return animates;
	}

	public int getDirection() {
		return direction;
	}

	public String getImage() {
		return image;
	}

	public Point2D.Double getSpeed() {
		return speed;
	}

}