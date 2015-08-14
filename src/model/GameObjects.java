package model;

import java.awt.geom.Rectangle2D;

public class GameObjects {

	protected Rectangle2D.Double pos;

	public GameObjects() {
	}

	public GameObjects(int x, int y, int width, int height) {
		this.pos = new Rectangle2D.Double(x, y, width, height);
	}

	public Rectangle2D.Double getPos() {
		return pos;
	}

}
