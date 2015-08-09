package model;

public abstract class Entidade {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String imagem;
	
	public Entidade() {
	}

	public Entidade(int x, int y, int width, int height, String imagem) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imagem = imagem;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
