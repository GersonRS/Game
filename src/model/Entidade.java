package model;

import java.awt.geom.Rectangle2D;

public abstract class Entidade {

	static final int COLLIDING_ABOVE = 0; // acima
	static final int COLLIDING_RIGHT = 1; // direita
	static final int COLLIDING_BELOW = 2; // abaixo
	static final int COLLIDING_LEFT = 3; // esquerda
	protected Rectangle2D.Double pos;
	protected Entidade[] collidingEntities;
	protected String imagem;

	public Entidade() {
	}

	public Entidade(int x, int y, int width, int height, String imagem) {
		super();
		this.imagem = imagem;
		this.pos = new Rectangle2D.Double(x, y, width, height);
		collidingEntities = new Entidade[4];
	}

	public Rectangle2D.Double getPos() {
		return pos;
	}

	public void setPos(Rectangle2D.Double pos) {
		this.pos = pos;
	}

	public Entidade[] getCollidingEntities() {
		return collidingEntities;
	}

	public void setCollidingEntities(Entidade[] collidingEntities) {
		this.collidingEntities = collidingEntities;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
