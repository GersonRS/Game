package model;

public abstract class Personagem extends Entidade {
	
	protected int anima;
	protected int velocidade;
	protected int estado;

	public Personagem() {
	}
	
	public Personagem(int x, int y, int width, int height, String imagem, int velocidade) {
		super(x, y, width, height, imagem);
		this.velocidade = velocidade;
	}

	public abstract void mover(int direcao);

	public int getAnima() {
		return anima;
	}

	public void setAnima(int anima) {
		this.anima = anima;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getDirecao() {
		return estado;
	}

	public void setDirecao(int direcao) {
		this.estado = direcao;
	}
}