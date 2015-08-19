package model;

import java.awt.geom.Rectangle2D;


public abstract class Entity{

	static final int COLLIDING_ABOVE = 0; // acima
	static final int COLLIDING_RIGHT = 1; // direita
	static final int COLLIDING_BELOW = 2; // abaixo
	static final int COLLIDING_LEFT = 3; // esquerda
	protected Entity[] collidingEntities;
	protected Rectangle2D.Double pos;

	public Entity() {
	}

	public Entity(int x, int y, int width, int height) {
		this.pos = new Rectangle2D.Double(x, y, width, height);
		collidingEntities = new Entity[4];
	}
	
	public Rectangle2D.Double getPos() {
		return pos;
	}
}
