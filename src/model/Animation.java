package model;

public class Animation extends GameObjects {

	private int currentImageIndex;
	private int indexInc;
	private int speed;
	private boolean loop, visible;
	private int imageSize;
	private String image;

	public Animation(int x, int y, int width, int height, int speed,
			boolean loop, int imageSize, String image) {
		super(x, y, width, height);
		this.speed = speed;
		this.loop = loop;
		this.imageSize = imageSize;
		this.image = image;
		this.currentImageIndex = 0;
		this.indexInc = 1;
		this.visible = true;
	}

	public void update(int currentTick) {
		if (currentTick % speed == 0) {
			currentImageIndex += indexInc;
			if (currentImageIndex == imageSize || currentImageIndex == -1) {
				if (loop) {
					currentImageIndex = 0;
				} else {
					visible = false;
				}
			}
		}
	}

	public boolean finished() {
		return currentImageIndex == imageSize;
	}

	public int getCurrentImageIndex() {
		return currentImageIndex;
	}

	public String getImage() {
		return image;
	}

	public int getImageSize() {
		return imageSize;
	}

	public boolean isVisible() {
		return visible;
	}
}
