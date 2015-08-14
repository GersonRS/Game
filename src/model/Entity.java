package model;


public abstract class Entity extends GameObjects {

	static final int COLLIDING_ABOVE = 0; // acima
	static final int COLLIDING_RIGHT = 1; // direita
	static final int COLLIDING_BELOW = 2; // abaixo
	static final int COLLIDING_LEFT = 3; // esquerda
	protected Entity[] collidingEntities;

	public Entity() {
	}

	public Entity(int x, int y, int width, int height) {
		super(x, y, width, height);
		collidingEntities = new Entity[4];
	}

}
